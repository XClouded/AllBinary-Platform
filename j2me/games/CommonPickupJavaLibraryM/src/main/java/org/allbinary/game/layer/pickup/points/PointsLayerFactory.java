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
package org.allbinary.game.layer.pickup.points;

import org.allbinary.layer.AllBinaryLayer;
import org.allbinary.layer.AllBinaryLayerFactoryInterface;

/**
 *
 * @author user
 */
public class PointsLayerFactory 
implements AllBinaryLayerFactoryInterface
{
	private final int points;
	
	public PointsLayerFactory(int points)
	{
		this.points = points;
	}
	
   public AllBinaryLayer getInstance() 
      throws Exception
   {
      return new PointsLayer(points);
   }
}
