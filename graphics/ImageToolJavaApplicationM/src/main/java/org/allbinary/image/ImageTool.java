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
package org.allbinary.image;

import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.image.gui.ImageToolJFrame;
import org.allbinary.logic.communication.log.LogFactory;

public class ImageTool
{
   public ImageTool()
   {
   }
   
   public static void main(String[] args)
   {
      java.awt.EventQueue.invokeLater(new Runnable()
      {
         public void run()
         {
            try
            {
               LogUtil.put(LogFactory.getInstance("Running", "Main", "run"));
               new ImageToolJFrame().setVisible(true);
            }
            catch(Exception e)
            {
               String error = "Error";
               LogUtil.put(LogFactory.getInstance(error, "Main", "run", e));
            }
         }
      });
   }
   
}
