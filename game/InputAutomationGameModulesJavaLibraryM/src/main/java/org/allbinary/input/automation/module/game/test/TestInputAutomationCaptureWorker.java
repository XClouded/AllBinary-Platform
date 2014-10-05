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
package org.allbinary.input.automation.module.game.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.allbinary.logic.communication.log.LogUtil;

import org.allbinary.input.automation.module.AbstractInputAutomationWorker;
import org.allbinary.input.automation.module.InputAutomationActionInterface;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileAction;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.GenericProfileActions;
import org.allbinary.input.automation.module.generic.configuration.profile.actions.script.GenericProfileActionScript;
import org.allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.util.cache.AutomaticCacheInterface;
import org.allbinary.media.image.comparison.ImageComparatorConstraintsInterface;
import org.allbinary.media.image.comparison.motion.MotionRectangleConstraintsInterface;

public class TestInputAutomationCaptureWorker
    extends AbstractInputAutomationWorker
{
    //private final Rectangle rectangle = new Rectangle(0, 0, 1024, 768);
    //private TimeHelper timeHelper;
    
    private GenericProfileActions genericProfileActions;
    
    public TestInputAutomationCaptureWorker(
        InputAutomationActionInterface inputAutomationActionInterface,
        GenericProfileActions genericProfileActions,
        ImageComparatorConstraintsInterface imageComparatorConstraintsInterface,
        MotionRectangleConstraintsInterface motionRectangleConstraintsInterface)
        throws Exception
    {
        super(inputAutomationActionInterface);
        
        LogUtil.put(LogFactory.getInstance("GenericInputAutomationCaptureWorker", this, "Constructor"));
        
        this.setGenericProfileActions(genericProfileActions);
    }

    public void processDataWorkerResults()
        throws Exception
    {
        AutomaticCacheInterface cacheInterface = (AutomaticCacheInterface)
            CapturedBufferedImagesCacheSingleton.getInstance();

        if(cacheInterface.keySet().size() > 0)
        {
            LogUtil.put(LogFactory.getInstance("Image Available so processing", this, "processDataWorkerResults"));
            
            Object object = cacheInterface.keySet().toArray()[0];
            //BufferedImageFrameCacheable capturedBufferedImageCacheable = 
                //(BufferedImageFrameCacheable) cacheInterface.get(object);
            
            HashMap hashMap = this.getGenericProfileActions().getHashMap();
            Set set = hashMap.keySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext())
            {                
                String actionNameString = (String) iterator.next();
                GenericProfileAction genericProfileAction =
                    (GenericProfileAction) hashMap.get(actionNameString);
                GenericProfileActionScript genericProfileActionScript =
                    genericProfileAction.getGenericProfileActionScript();
                Vector vector = genericProfileActionScript.getProfileActionConditionInterfaceVector();
                //CaptureWorkerUtil.processProfileActionConditions(vector,
                  //  capturedBufferedImageCacheable);
            }
            
            //was remove before
            cacheInterface.get(object);
        }
        else
        {
            LogUtil.put(LogFactory.getInstance("Image Not Available", this, "processDataWorkerResults"));
        }
    }
    
    public void process() throws Exception
    {
        LogUtil.put(LogFactory.getInstance("Start", this, "process"));
        
        this.startDataWorkers();
        this.processDataWorkerResults();
    }

    public GenericProfileActions getGenericProfileActions()
    {
        return genericProfileActions;
    }
    
    public void setGenericProfileActions(GenericProfileActions genericProfileActions)
    {
        this.genericProfileActions = genericProfileActions;
    }
}
