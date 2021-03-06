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
package org.allbinary.game.layer.weapon;

import org.allbinary.physics.movement.Movement;

import org.allbinary.animation.Animation;
import org.allbinary.graphics.Rectangle;
import org.allbinary.view.ViewPosition;
import org.allbinary.game.multiplayer.layer.RemoteInfo;

public class SimpleWeaponLayer extends WeaponLayer
{
    public SimpleWeaponLayer(Movement movement,
            Animation animationInterface,
            Rectangle rectangle, ViewPosition viewPosition)
            throws Exception
    {
        super(movement, animationInterface, rectangle, viewPosition);
        
        this.setCollidableInferface(new CollidableWeaponBehavior(this, true));
    }

    public SimpleWeaponLayer(Movement movement,
            Animation animationInterface,
            Animation destroyedAnimationInterface,
            Rectangle rectangle, ViewPosition viewPosition)
            throws Exception
    {
        super(movement, animationInterface, destroyedAnimationInterface, rectangle, viewPosition);
        
        this.setCollidableInferface(new CollidableWeaponBehavior(this, true));
    }

    public SimpleWeaponLayer(RemoteInfo remoteInfo, int multiPlayerType, 
            Movement movement,
            Animation animationInterface,
            Animation destroyedAnimationInterface,
            Rectangle rectangle, ViewPosition viewPosition)
            throws Exception
    {
        super(remoteInfo, multiPlayerType, movement, animationInterface, destroyedAnimationInterface, rectangle, viewPosition);
        
        this.setCollidableInferface(new CollidableWeaponBehavior(this, true));
    }
}
