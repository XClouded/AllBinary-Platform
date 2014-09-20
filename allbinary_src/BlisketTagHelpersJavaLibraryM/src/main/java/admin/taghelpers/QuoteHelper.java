/*
* AllBinary Open License Version 1
* Copyright (c) 2011 AllBinary
* 
* By agreeing to this license you and any business entity you represent are
* legally bound to the AllBinary Open License Version 1 legal agreement.
* 
* You may obtain the AllBinary Open License Version 1 legal agreement from
* AllBinary or the root directory of AllBinary's AllBinary Platform repository.
* 
* Created By: Travis Berthelot
* 
*/
package admin.taghelpers;

import java.util.HashMap;
import java.util.Vector;

import javax.servlet.jsp.PageContext;

import org.allbinary.business.installer.Portion;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.business.context.modules.storefront.StoreFrontData;
import org.allbinary.business.context.modules.storefront.StoreFrontFactory;
import org.allbinary.business.context.modules.storefront.StoreFrontInterface;
import org.allbinary.business.user.UserInterface;
import org.allbinary.business.user.quoterequest.QuoteRequest;
import org.allbinary.data.tables.user.UserEntityFactory;
import org.allbinary.data.tables.user.quoterequest.QuoteRequestEntity;
import org.allbinary.data.tables.user.quoterequest.QuoteRequestEntityFactory;
import org.allbinary.logic.communication.http.request.session.WeblisketSession;
import org.allbinary.logic.communication.smtp.event.UserEmailEventNameData;
import org.allbinary.logic.communication.smtp.event.handler.UserEmailEventHandler;
import org.allbinary.logic.communication.smtp.event.handler.factory.AdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.StoreAdminUserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.event.handler.factory.UserEmailEventHandlerSingletons;
import org.allbinary.logic.communication.smtp.info.BasicEmailInfo;
import org.allbinary.logic.communication.smtp.info.EmailInfo;
import org.allbinary.logic.communication.smtp.info.StoreEmailInfo;


public class QuoteHelper implements BasicTableInterface
{
   private final WeblisketSession weblisketSession;
   
   private final StoreFrontInterface storeFrontInterface;

   private final Portion portion;
   
   public QuoteHelper(HashMap hashMap, PageContext pageContext)
   {
      String storeName = (String) hashMap.get(StoreFrontData.getInstance().NAME);
      
      if(storeName!=null)
      {
         this.storeFrontInterface = StoreFrontFactory.getInstance(storeName);
      }
      else
      {
    	  this.storeFrontInterface = null;
      }
      
      this.weblisketSession =
         new WeblisketSession(hashMap, pageContext);
      
      this.portion = new Portion(hashMap);
   }

   private void emailUser(QuoteRequest quoteRequest) throws Exception
   {
      UserInterface user =
         UserEntityFactory.getInstance().getUser(quoteRequest.getUserName());

      String userEmailSubject = "Quote Request Receipt";
      
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append("This is just a verification email. ");
      stringBuffer.append("We usually respond to quote request within 24 hours.");
      stringBuffer.append("\n\nThank You For Your Business.");
      
      String userEmailTextBody = stringBuffer.toString();

      BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
         new StoreEmailInfo(this.storeFrontInterface, userEmailSubject, userEmailTextBody);

      EmailInfo emailInfo = new EmailInfo(basicEmailInfo);

      //Send response to customer
      UserEmailEventHandler userEmailEventHandler =
         UserEmailEventHandlerSingletons.getInstance(
         UserEmailEventNameData.QUOTEREQUEST, user);

      userEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.QUOTEREQUEST, emailInfo);
   }

   private void emailAdmins(QuoteRequest quoteRequest) throws Exception
   {
      String adminEmailSubject = "Quote Request";
      
      StringBuffer stringBuffer = new StringBuffer();
      
      stringBuffer.append("\nUserName: ");
      stringBuffer.append(quoteRequest.getUserName());
      stringBuffer.append("\nProject Info: \n");
      stringBuffer.append(quoteRequest.getProjectInfo());
      stringBuffer.append("\nUser Comments: \n");
      stringBuffer.append(quoteRequest.getUserComments());
      stringBuffer.append("\nBudget: \n");
      stringBuffer.append(quoteRequest.getBudget());
      stringBuffer.append("\nTime Frame: \n");
      stringBuffer.append(quoteRequest.getTimeFrame());
      stringBuffer.append("\nComments: \n");
      stringBuffer.append(quoteRequest.getComments());
      
      String adminEmailTextBody = stringBuffer.toString();
      
      BasicEmailInfo basicEmailInfo = (BasicEmailInfo)
         new StoreEmailInfo(this.storeFrontInterface, 
         adminEmailSubject, adminEmailTextBody);
      
      EmailInfo emailInfo = new EmailInfo(basicEmailInfo);
      
      //send request to store admins if subscribed to handler name for review and response
      UserEmailEventHandler storeAdminUserEmailEventHandler =
         AdminUserEmailEventHandlerSingletons.getInstance(
         UserEmailEventNameData.QUOTEREQUEST);
      
      UserEmailEventHandler adminUserEmailEventHandler =
         StoreAdminUserEmailEventHandlerSingletons.getInstance(
         UserEmailEventNameData.QUOTEREQUEST, this.storeFrontInterface);
      
      storeAdminUserEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.QUOTEREQUEST, emailInfo);
      adminUserEmailEventHandler.receiveEmailInfo(UserEmailEventNameData.QUOTEREQUEST, emailInfo);
   }
   
   public String email() throws Exception
   {
      try
      {
         final QuoteRequestEntity quoteRequestEntity =
            QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance();

         final String userName = this.weblisketSession.getUserName();
         
         Vector vector = quoteRequestEntity.getIds(userName);
         
         int id = 0;
         
         //get last quote request
         for(int index = 0; index < vector.size(); index++)
         {
        	 int nextId = ((Integer) vector.get(index)).intValue();
        	 if(id < nextId)
        	 {
        		 id = nextId;
        	 }
         }

         QuoteRequest quoteRequest = quoteRequestEntity.get(userName, id);
         
         if(quoteRequest != null)
         {
            this.emailUser(quoteRequest);
            this.emailAdmins(quoteRequest);
            
            return "Thank You For Your Business.<p/>";
         }
         else
         {
            throw new Exception("No Quote Request");
         }
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.EMAILLOGGINGERROR))
         {
            LogUtil.put(LogFactory.getInstance("Command Failed", this, "email", e));
         }
         return "Thank You For Your Business.<p>";
      }
   }
   
   public String drop()
   {
      try
      {
         String success = QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().dropTable();
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "drop()"));
         }
         return success;
      }
      catch(Exception e)
      {
         String error = "Failed to drop QuoteRequest table";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "drop()", e));
         }
         return error;
      }
   }
   
   public String create()
   {
      try
      {
         String success = QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().createTable();
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "create()"));
         }
         
         return success;
      }
      catch(Exception e)
      {
         String error= "Failed to create new QuoteRequest table";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "create()", e));
         }
         return error;
      }
   }
   
   public String restore()
   {
      try
      {
         String success = "Restore Successful";
         String result = QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().restoreTable(this.portion);
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "restore()"));
         }
         
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to restore backup";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "restore()", e));
         }
         
         return error;
      }
   }
   
   public String backup()
   {
      try
      {
         String success = "Restore Successful";
         String result = QuoteRequestEntityFactory.getInstance().getQuoteRequestEntityInstance().backupTable();
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGS))
         {
            LogUtil.put(LogFactory.getInstance(success, this, "backup()"));
         }
         return result;
      }
      catch(Exception e)
      {
         String error = "Failed to make backup";
         
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.SQLTAGSERROR))
         {
            LogUtil.put(LogFactory.getInstance(error, this, "backup()", e));
         }
         return error;
      }
   }
}
