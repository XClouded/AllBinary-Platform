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
package views.business.context.modules.storefront.customizer.includes.style.theme;

import org.allbinary.logic.communication.log.LogFactory;
import java.util.HashMap;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import org.allbinary.logic.communication.http.request.NameSpaceRequestParams;

import org.allbinary.logic.visual.theme.ThemeValidation;

import org.allbinary.data.tree.dom.DomNodeInterface;
import org.allbinary.logic.visual.transform.info.TransformInfoInterface;
import org.allbinary.logic.control.validate.ValidationComponentInterface;

import org.allbinary.logic.communication.log.LogUtil;

import views.business.context.modules.storefront.customizer.CustomizerUtil;

import views.business.context.modules.storefront.customizer.StoreCustomizerComponentUtil;

public class InsertThemeValidationView extends ThemeCustomizerView implements ValidationComponentInterface
{
   private HashMap requestHashMap;
   
   public InsertThemeValidationView(TransformInfoInterface transformInfoInterface) throws Exception
   {
      super(transformInfoInterface);

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Constructor",this,"started"));
      }
      
      this.requestHashMap =
         new NameSpaceRequestParams(this.getPageContext()).toHashMap();

      if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
      {
         LogUtil.put(LogFactory.getInstance("Constructor",this,"begin loading theme"));
      }
   }

    public Document toXmlDoc() throws Exception
    {
        return null;
    }

   public Boolean isValid()
   {
      try
      {
         Boolean isValid = Boolean.TRUE;

         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEW))
         {
            LogUtil.put(LogFactory.getInstance("Started Validation",this,"isValid()"));
         }

         ThemeValidation themeValidation = 
            new ThemeValidation(this.getTransformInfoInterface(), requestHashMap);
         
         if(themeValidation.isValid() == Boolean.FALSE)
         {
            isValid = Boolean.FALSE;
         }
                  
         this.validationInterface = themeValidation.getCssStyleValidation();

         isValid = this.validationInterface.isValid();

         if(isValid == Boolean.TRUE)
         {
            //Insert XML into the view specified by the Object Config for this view
            CustomizerUtil.insert(
               this.getTransformInfoInterface(),
               (DomNodeInterface) this.validationInterface);
         }
         
         return isValid;
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to validate",this,"isValid()",e));
         }
         return Boolean.FALSE;
      }
   }
   
   public String validationInfo()
   {
      try
      {
         StringBuffer stringBuffer = new StringBuffer();
         
         //stringBuffer.append(this.body.validationInfo());
         
         return stringBuffer.toString();
      }
      catch(Exception e)
      {
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.VIEWERROR))
         {
            LogUtil.put(LogFactory.getInstance("Failed to generate validation error info",this,"validationInfo()",e));
         }
         return "Error Getting Validation Info";
      }
   }
   
   public Document toValidationInfoDoc()
   {
      return null;
   }
   
   public Node toValidationInfoNode(Document document)
   {
      return null;
   }
   
   public String view() throws Exception
   {
      try
      {
         return StoreCustomizerComponentUtil.generate(this.getTransformInfoInterface());
      }
      catch(Exception e)
      {
         String error = "Failed to view";
         if(abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERERROR))
         {
            LogUtil.put(LogFactory.getInstance(error,this,"view()",e));
         }
         throw e;
      }
   }
}