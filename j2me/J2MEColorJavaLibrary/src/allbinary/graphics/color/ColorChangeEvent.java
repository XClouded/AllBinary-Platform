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
package allbinary.graphics.color;

import allbinary.logic.basic.util.event.AllBinaryEventObject;

public class ColorChangeEvent extends AllBinaryEventObject
{
    private BasicColor basicColor;
    
    public ColorChangeEvent(Object object)
    {
        super(object);
    }

    public void setBasicColor(BasicColor basicColor)
    {
        this.basicColor = basicColor;
    }

    public BasicColor getBasicColor()
    {
        return basicColor;
    }
}
