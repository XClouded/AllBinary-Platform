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
package org.allbinary.game.ai.scroller;

import java.util.Hashtable;

import org.allbinary.ai.ArtificialIntelligenceInterface;
import org.allbinary.game.ai.ArrayAI;
import org.allbinary.game.ai.ArtificialIntelligenceInterfaceFactoryInterface;
import org.allbinary.game.input.GameInput;
import org.allbinary.layer.AllBinaryLayer;

public class RandomPatrolFlockingFiringAIFactory
    implements ArtificialIntelligenceInterfaceFactoryInterface
{
    public ArtificialIntelligenceInterface getInstance(
          Hashtable hashtable, AllBinaryLayer ownerLayerInterface, GameInput gameInput)
          throws Exception
    {
        ArtificialIntelligenceInterface[] artificialIntelligenceInterface =
            new ArtificialIntelligenceInterface[2];
        
        artificialIntelligenceInterface[0] = 
            new RandomPatrolAI(hashtable, ownerLayerInterface, gameInput);

        artificialIntelligenceInterface[1] = 
            new TimedFireAI(1800, ownerLayerInterface, gameInput);

        //artificialIntelligenceInterface[2] = 
          //  new FlockingAI(hashtable, ownerLayerInterface, gameInput);
        
    	return new ArrayAI(artificialIntelligenceInterface, ownerLayerInterface, gameInput);
    }
}
