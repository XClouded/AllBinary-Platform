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
package org.allbinary.logic.communication.log.config;

import org.allbinary.logic.communication.log.config.LogConfigData;
import org.allbinary.logic.communication.log.config.LogConfigsData;
import java.util.Iterator;
import java.util.Vector;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.allbinary.data.tree.dom.document.DomDocumentHelper;
import org.allbinary.data.tree.dom.DomSearchHelper;
import org.allbinary.globals.URLGLOBALS;
import org.allbinary.logic.basic.io.AbFileLocalInputStream;

import org.allbinary.logic.basic.io.file.AbFile;

import org.allbinary.logic.basic.path.AbPath;
import org.allbinary.globals.PATH_GLOBALS;

public class LoggingInitInfo
{
   private static final String INITFILENAME = "logConfig.xml";
   
   private static final String PACKAGE = PATH_GLOBALS.getInstance().INIT_PATH;
   
   private static Vector logConfigInfoVector = null;
   
   private static boolean hasRead = false;
   
   public LoggingInitInfo()
   {
   }

   public synchronized static Document getDoc() throws Exception
   {
      AbPath FILEABPATH =
	 new AbPath(URLGLOBALS.getWebappPath() + PACKAGE, INITFILENAME);
      System.out.println("File Path: " + FILEABPATH);
      
      AbFile file = new AbFile(FILEABPATH);

      Document document = 
         DomDocumentHelper.create(new AbFileLocalInputStream(file));
      
      return document;
   }
   
   public synchronized static void write() throws Exception
   {
      try
      {
         //write new logConfigFile
         
	 hasRead = false;
      }
      catch(Exception e)
      {
	 throw e;
      }
   }
   
   private synchronized static void read() throws Exception
   {
      try
      {
         Document document = LoggingInitInfo.getDoc();

         NodeList logConfigsNodeList =
            document.getElementsByTagName(LogConfigsData.getInstance().NAME);

         Node logConfigsNode = logConfigsNodeList.item(0);
         
         Vector logConfigNodeVector = 
            DomSearchHelper.getAllNodes(
               LogConfigData.getInstance().NAME, logConfigsNode.getChildNodes());
	    
         LoggingInitInfo.logConfigInfoVector = new Vector();
	    
         Iterator iter = logConfigNodeVector.iterator();
         while(iter.hasNext())
         {
            Node node = (Node) iter.next();
            LogConfig logConfigInfo = new LogConfig(node);
            LoggingInitInfo.logConfigInfoVector.add(logConfigInfo);
         }

      }
      catch(Exception e)
      {
         throw e;
      }
   }
   
   public synchronized static void setHasRead(boolean value)
   {
      LoggingInitInfo.hasRead = value;
   }
   
   private synchronized static void updateIfNeeded() throws Exception
   {
      if(!hasRead)
      {
	 LoggingInitInfo.read();
	 hasRead = true;
	 if(LoggingInitInfo.logConfigInfoVector == null)
	 {
	    throw new Exception("Read Failed");
	 }
      }
   }
   
   public boolean isValid()
   {
      try
      {
	 return true;
      }
      catch(Exception e)
      {
	 return false;
      }
   }
   
   public synchronized static void set(Vector logConfigInfoVector)
   {
      LoggingInitInfo.logConfigInfoVector = logConfigInfoVector;
   }

   public static Vector get() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      return LoggingInitInfo.logConfigInfoVector;
   }

   /*
   public static Vector getTypeNameVector() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      
      Vector allLogTypeVector = new Vector();
      Iterator iter = LoggingInitInfo.logConfigInfoVector.iterator();
      
      while(iter.hasNext())
      {
         LogConfig logConfigInfo = (LogConfig) iter.next();
         Vector logTypeVector = logConfigInfo.getTypeVector();
         
         if(logConfigInfo.isEnabled())
         {
            allLogTypeVector.addAll(logTypeVector);
         }
      }
      return allLogTypeVector;
   }
    */

   public static Vector getTypeVector() throws Exception
   {
      LoggingInitInfo.updateIfNeeded();
      
      Vector allLogTypeVector = new Vector();
      Iterator iter = LoggingInitInfo.logConfigInfoVector.iterator();
      
      while(iter.hasNext())
      {
         LogConfig logConfigInfo = (LogConfig) iter.next();
         Vector logTypeVector = logConfigInfo.getTypeVector();

         if(logConfigInfo.isEnabled())
         {
            allLogTypeVector.addAll(logTypeVector);
         }
      }
      return allLogTypeVector;
   }
   
   public int getNumberOfLogConfigs() throws Exception
   {
      return LoggingInitInfo.logConfigInfoVector.size();
   }
   
   public String toString()
   {
      try
      {
         Document document = LoggingInitInfo.getDoc();
         return DomDocumentHelper.toString(document);
      }
      catch(Exception e)
      {
	 return "Error";
      }
   }
}
