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
package org.allbinary.game.layer.resources;

import abcs.logic.basic.string.CommonStrings;
import abcs.logic.basic.string.StringMaker;
import abcs.logic.communication.log.LogFactory;
import abcs.logic.communication.log.LogUtil;
import allbinary.game.configuration.GameConfigurationCentral;

public class BasicGameResources
{
    protected static final StringMaker stringBuffer = new StringMaker();
    
    public String RESOURCE;
    public String RESOURCE_DESTROY;

    protected BasicGameResources()
    {
    }

    public void init(String ROOT, String[] SIZE)
    {
        try
        {
            int scale = GameConfigurationCentral.getInstance().SCALE.getValue().intValue();

            if (scale > 1 && scale - 1 < SIZE.length)
            {
                this.init(ROOT, SIZE[scale - 1]);
            } else
            {
                throw new Exception("Invalid Value: " + scale);
            }

            //LogUtil.put(LogFactory.getInstance("Resource: " + this.RESOURCE + " Destroy: " + this.RESOURCE_DESTROY, this, CommonStrings.getInstance().INIT));
        } catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance(CommonStrings.getInstance().EXCEPTION, this, CommonStrings.getInstance().INIT, e));
        }
    }

    protected String getString()
        throws Exception
    {
        return GameGraphicsResourceUtil.getInstance().getName();
    }
    
    protected void init(String ROOT, String sizeString) 
        throws Exception
    {
        final String DESTROY = "_destroy";

        String string = this.getString();
        
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(string);
        stringBuffer.append(sizeString);

        this.RESOURCE = stringBuffer.toString();
        
        stringBuffer.delete(0, stringBuffer.length());
        stringBuffer.append(ROOT);
        stringBuffer.append(DESTROY);
        stringBuffer.append(string);
        stringBuffer.append(sizeString);
        
        this.RESOURCE_DESTROY = stringBuffer.toString();
    }
}
