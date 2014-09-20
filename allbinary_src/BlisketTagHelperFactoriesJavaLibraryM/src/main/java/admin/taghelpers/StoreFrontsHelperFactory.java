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

import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import java.util.HashMap;

import javax.servlet.jsp.PageContext;


import org.allbinary.logic.system.security.licensing.LicensingException;


public class StoreFrontsHelperFactory implements TagHelperFactoryInterface
{   
   public StoreFrontsHelperFactory()
   {
   }
   
   public Object getInstance(
      HashMap hashMap, PageContext pageContext) 
      throws LicensingException
   {
        try
        {
            return new StoreFrontsHelper(hashMap, pageContext);
        } catch (Exception e)
        {
            if (abcs.logic.communication.log.config.type.LogConfigTypes.LOGGING.contains(
                abcs.logic.communication.log.config.type.LogConfigType.TAGHELPERFACTORYERROR))
            {
                String error = "Failed To Get Instance";

                LogUtil.put(LogFactory.getInstance(error, this, "getInstance(HashMap, PageContext)", e));
            }
            return null;
        }
   }  
}
