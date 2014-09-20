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
package allbinary.media.graphics.geography.pathfinding;

import org.allbinary.logic.basic.string.CommonSeps;
import org.allbinary.logic.basic.string.StringMaker;
import org.allbinary.media.graphics.geography.map.GeographicMapCellPosition;

public class PathFindingNode
{
   private PathFindingNode parent;

   private GeographicMapCellPosition geographicMapCellPosition;
   
   public PathFindingNode(PathFindingNode parent,
      GeographicMapCellPosition geographicMapCellPosition) 
      throws Exception
   {
      this.setParent(parent);
      this.setGeographicMapCellPosition(geographicMapCellPosition);

      /*
      if(this.getParent() == null)
      {
         LogUtil.put(LogFactory.getInstance("No Parent", this, CommonStrings.getInstance().CONSTRUCTOR));
      }
      */

      if(this.getGeographicMapCellPosition() == null)
      {
         throw new Exception("No GeographicMapCellPosition");
      }

   }

   public PathFindingNode getParent()
   {
      return parent;
   }

   private void setParent(PathFindingNode parent)
   {
      this.parent = parent;
   }

   public GeographicMapCellPosition getGeographicMapCellPosition()
   {
      return geographicMapCellPosition;
   }

   public void setGeographicMapCellPosition(GeographicMapCellPosition geographicMapCellPosition)
   {
      this.geographicMapCellPosition = geographicMapCellPosition;
   }
   
   public String toString()
   {
       StringMaker stringBuffer = new StringMaker();
      
      stringBuffer.append(this.getClass().getName());
      stringBuffer.append(": ");
      stringBuffer.append(" Path: ");
      stringBuffer.append(this.getGeographicMapCellPosition().toString());
     
      PathFindingNode pathFindingNode = this.parent;
      while(pathFindingNode != null)
      {
         stringBuffer.append(pathFindingNode.getGeographicMapCellPosition().toString());
         stringBuffer.append(CommonSeps.getInstance().SPACE);
         pathFindingNode = pathFindingNode.parent;
      }
      
      return stringBuffer.toString();
   }
}
