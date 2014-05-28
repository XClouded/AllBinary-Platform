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
package allbinary.game.layer.hud.basic.time;

import javax.microedition.lcdui.Graphics;

import allbinary.game.graphics.hud.BasicHud;
import allbinary.game.graphics.hud.BasicHudFactory;
import allbinary.graphics.color.BasicColor;
import allbinary.graphics.font.MyFont;
import allbinary.logic.math.PrimitiveLongSingleton;

public class TimeHudWidget extends BasicHud
{
    // private final String TIME_STRING = "Time ";
    private final char[] TIME_STRING = { 'T', 'i', 'm', 'e', ' ' };

    private int offset;

    // private String string = StringUtil.getInstance();
    private char[] string = PrimitiveLongSingleton.getInstance().ZERO;
    private int totalDigits = 1;

    private final Timer timer;

    public TimeHudWidget(int location, int direction, int maxHeight, int maxWidth, int bufferZone,
            BasicColor basicColor, Timer timer) throws Exception
    {
        super(location, direction, maxHeight, maxWidth, bufferZone, basicColor);

        this.timer = timer;

        this.set();

        this.offset = (this.getCharWidth() * this.TIME_STRING.length) + (this.getCharWidth() * 3);

        if (direction == 0)
        {
            throw new Exception(BasicHudFactory.getInstance().DIRECTION_EXCEPTION);
        }
    }

    public TimeHudWidget(int location, int direction, int maxWidth, BasicColor basicColor,
            Timer timer) throws Exception
    {
        this(location, direction, 14, maxWidth, 2, basicColor, timer);
    }

    public TimeHudWidget(int location, int direction, BasicColor basicColor, Timer timer)
            throws Exception
    {
        this(location, direction, 14, MyFont.getInstance().defaultFont.getSize() * 5, 2, basicColor, timer);
    }

    public void update()
    {
        this.timer.update();
        this.set();
    }

    private void set()
    {
        // this.string = trackTimer;
        this.string = timer.getTimeChars();

        totalDigits = timer.getCurrentTotalDigits();
    }

    public void paint(Graphics graphics)
    {
        super.paint(graphics, TIME_STRING, 0, TIME_STRING.length, string, 0, totalDigits, offset);
        // super.paint(graphics, TIME_STRING, string, offset);
    }

    /*
    public void setTrackTimer(Timer trackTimer)
    {
        this.trackTimer = trackTimer;
    }
    */

    public Timer getTimer()
    {
        return timer;
    }
}
