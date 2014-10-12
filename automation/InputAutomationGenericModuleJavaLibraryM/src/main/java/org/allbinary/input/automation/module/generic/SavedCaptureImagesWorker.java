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
package org.allbinary.input.automation.module.generic;

import java.io.File;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import org.allbinary.input.automation.module.generic.configuration.profile.SavedCaptureGenericProfileDataWorkerType;

import org.allbinary.input.media.image.capture.CaptureWorkerInterface;
import org.allbinary.input.media.image.capture.CapturedBufferedImagesCacheSingleton;
import org.allbinary.input.media.image.capture.CapturedImageWorkerResultsEvent;
import org.allbinary.input.media.image.capture.ProcessingFrameIndexFactory;
import org.allbinary.input.media.image.capture.ScreenScavangerRobot;
import org.allbinary.logic.basic.util.event.handler.BasicEventHandler;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.java.number.LongUtil;
import org.allbinary.logic.visual.media.MediaDataFactory;
import org.allbinary.media.image.cache.BufferedImageFrameCacheable;
import org.allbinary.time.TimeDelayHelper;

public class SavedCaptureImagesWorker
    extends BasicEventHandler
    implements CaptureWorkerInterface
{
    private static long index;
    
    private boolean running;
    
    private ScreenScavangerRobot screenScavangerRobot;
    
    private SavedCaptureGenericProfileDataWorkerType savedCaptureGenericProfileDataWorkerType;
    
    public SavedCaptureImagesWorker(
        SavedCaptureGenericProfileDataWorkerType savedCaptureGenericProfileDataWorkerType) 
        throws Exception
    {
        this.savedCaptureGenericProfileDataWorkerType = 
            savedCaptureGenericProfileDataWorkerType;
        this.screenScavangerRobot = new ScreenScavangerRobot();
        index = ProcessingFrameIndexFactory.next();
    }
    
    public void setThread(Thread thread)throws Exception
    {
        
    }
    
    public synchronized boolean isRunning()
    {
        return running;
    }
    
    public synchronized void setRunning(boolean running)
    {
        this.running = running;
    }
    
    public void run()
    {
        try
        {
            LogUtil.put(LogFactory.getInstance("Start", this, "run"));
            
            this.setRunning(true);
            
            TimeDelayHelper timeHelper = new TimeDelayHelper(1000);
            
            while(this.isRunning())
            {
                timeHelper.setStartTime();
                
                Long frame = new Long(index);
                
                StringBuffer filePathStringBuffer = new StringBuffer();
                
                filePathStringBuffer.append(
                    this.savedCaptureGenericProfileDataWorkerType.getPath());
                filePathStringBuffer.append(LongUtil.fillIn(frame.toString()));
                filePathStringBuffer.append(MediaDataFactory.getInstance().JPG.getExtension());
                
                String filePath = filePathStringBuffer.toString();
                
                LogUtil.put(LogFactory.getInstance("Loading Image File Path: " + filePath, this, "run"));
                
                File file = new File(filePath);
                if(file.isFile())
                {
                    BufferedImage bufferedImage = ImageIO.read(file);
                    
                    index++;
                    
                    CapturedBufferedImagesCacheSingleton.getInstance().add(
                        new BufferedImageFrameCacheable(bufferedImage, frame));
                    
                    CapturedImageWorkerResultsEvent capturedImageEvent =
                        new CapturedImageWorkerResultsEvent(
                        this, frame, bufferedImage);
                    
                    this.fireEvent(capturedImageEvent);
                }
                else
                {
                    LogUtil.put(LogFactory.getInstance(
                        "Could Not Load File: " + filePath, this, "run"));
                }
                
                LogUtil.put(LogFactory.getInstance(
                    "Time Elapsed: " + timeHelper.getElapsed(), this, "run"));
                
                this.setRunning(false);
            }
            
            LogUtil.put(LogFactory.getInstance("End", this, "run"));
            
        }
        catch (Exception e)
        {
            LogUtil.put(LogFactory.getInstance("Exception", this, "run", e));
        }
    }
}
