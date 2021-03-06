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
package org.allbinary.graphics.displayable;

import javax.microedition.lcdui.Displayable;

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.CommonStrings;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.logic.communication.log.LogFactory;
import org.allbinary.logic.communication.log.LogUtil;
import org.allbinary.logic.communication.log.PreLogUtil;
import org.allbinary.logic.system.os.OperatingSystemFactory;
import org.allbinary.logic.system.os.OperatingSystemInterface;
import org.allbinary.graphics.SpacialStrings;
import org.allbinary.graphics.displayable.event.DisplayChangeEvent;
import org.allbinary.graphics.displayable.event.DisplayChangeEventHandler;

public class DisplayInfoSingleton {

    private static final DisplayInfoSingleton SINGLETON = new DisplayInfoSingleton();

    public final String ORIENTATION = "ORIENTATION";

    public int lastWidth;
    public int lastHeight;
    private int lastHalfWidth;
    private int lastHalfHeight;

    private int top;
    private int left;
    private int fullWidth;
    private int fullHeight;

    public static final DisplayInfoSingleton getInstance() {
        return SINGLETON;
    }

    public int getLastHalfWidth() {
        return lastHalfWidth;
    }

    public int getLastHalfHeight() {
        return lastHalfHeight;
    }

    public int getLastWidth() {
        return lastWidth;
    }

    public void setLastWidth(int aLastWidth) {
        if (this.lastWidth != aLastWidth) {
            OperatingSystemInterface operatingSystemInterface
                    = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

            if (operatingSystemInterface.isOverScan()) {
                this.fullWidth = aLastWidth;
                aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
                this.left = (this.getFullWidth() - aLastWidth) >> 1;
            }

            lastWidth = aLastWidth;
            lastHalfWidth = (lastWidth >> 1);
            this.fire();
        }
    }

    public int getLastHeight() {
        return lastHeight;
    }

    public void setLastHeight(int aLastHeight) {
        if (this.lastHeight != aLastHeight) {
            OperatingSystemInterface operatingSystemInterface
                    = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

            if (operatingSystemInterface.isOverScan()) {
                this.fullHeight = aLastHeight;
                aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;
                this.top = (this.getFullHeight() - aLastHeight) >> 1;
            }

            lastHeight = aLastHeight;
            lastHalfHeight = (lastHeight >> 1);
            this.fire();
        }
    }

    public boolean isPortrait(
            int lastWidth, int lastHeight) {
        if (lastHeight > lastWidth) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isPortrait() {
        return this.isPortrait(this.getLastWidth(), this.getLastHeight());
    }

    private final DisplayChangeEvent displayChangeEvent = new DisplayChangeEvent(this);

    private void fire() {
        try {
            LogUtil.put(LogFactory.getInstance(this.toString(), this, CommonStrings.getInstance().UPDATE));
            //PreLogUtil.put("Display Change Event" + this.toString(), this, CommonStrings.getInstance().UPDATE);
            DisplayChangeEventHandler.getInstance().fireEvent(displayChangeEvent);
        } catch (Exception e) {
            PreLogUtil.put(CommonStrings.getInstance().EXCEPTION, this, "fire", e);
        }
    }

    public void update(Displayable displayable) {
        int aLastWidth = displayable.getWidth();
        int aLastHeight = displayable.getHeight();

        //The getters fire and set on change by calling the setters of this class
        if (this.lastHeight != aLastHeight || this.lastWidth != aLastWidth) {
            OperatingSystemInterface operatingSystemInterface
                    = OperatingSystemFactory.getInstance().getOperatingSystemInstance();

            if (operatingSystemInterface.isOverScan()) {
                this.fullHeight = aLastHeight;
                this.fullWidth = aLastWidth;
                aLastWidth = aLastWidth * operatingSystemInterface.getOverScanXPercent() / 100;
                aLastHeight = aLastHeight * operatingSystemInterface.getOverScanYPercent() / 100;
                this.left = (this.getFullWidth() - aLastWidth) >> 1;
                this.top = (this.getFullHeight() - aLastHeight) >> 1;
            }
            //leave remarked

            lastWidth = aLastWidth;
            lastHalfWidth = (lastWidth >> 1);

            lastHeight = aLastHeight;
            lastHalfHeight = (lastHeight >> 1);

            this.fire();
        }
    }

    private final String DISPLAY_INFO = "Display Info: ";
    private final String LAST = "last";
    private final String LAST_HALF = "lastHalf";

    public String toString() {
        StringMaker stringBuffer = new StringMaker();
        stringBuffer.append(DISPLAY_INFO);
        stringBuffer.append(LAST);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(lastWidth);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(LAST);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(lastHeight);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(LAST_HALF);
        stringBuffer.append(SpacialStrings.getInstance().WIDTH_LABEL);
        stringBuffer.append(lastHalfWidth);
        stringBuffer.append(CommonSeps.getInstance().SPACE);
        stringBuffer.append(LAST_HALF);
        stringBuffer.append(SpacialStrings.getInstance().HEIGHT_LABEL);
        stringBuffer.append(lastHalfHeight);

        return stringBuffer.toString();
    }

    /**
     * @return the top
     */
    public int getTop() {
        return top;
    }

    /**
     * @return the left
     */
    public int getLeft() {
        return left;
    }

    /**
     * @return the fullWidth
     */
    public int getFullWidth() {
        return fullWidth;
    }

    /**
     * @return the fullHeight
     */
    public int getFullHeight() {
        return fullHeight;
    }
}
