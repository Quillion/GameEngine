package logic.dungeon;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.shapes.BBox;
import constants.Constants;
import dungeon.MBox;

/**
 * Tests for collision for dungeon crawler.
 */
public class CollisionEngine extends logic.CollisionEngine
{
	public static void wallCollision(MBox character, BBox wall)
	{
		Constants.Direction direction = CollisionEngine
				.horizontalCollision(character, wall);
		if (direction.equals(Constants.Direction.Right) && character
				.getXVector() > 0)
		{
			character.setXVector(0);
			character.setX(wall.getLeftX() - character.getWidth() + character
					.getHorizontalOffset());
		}
		else if (direction.equals(Constants.Direction.Left) && character
				.getXVector() < 0)
		{
			character.setXVector(0);
			character.setX(wall.getRightX() - character.getHorizontalOffset());
		}
		direction = CollisionEngine.verticalCollision(character, wall);
		if (direction.equals(Constants.Direction.Down) && character
				.getYVector() > 0)
		{
			character.setYVector(0);
			character.setY(wall.getTopY() - character.getHeight() + character
					.getVerticalOffset());
		}
		else if (direction.equals(Constants.Direction.Up) && character
				.getYVector() < 0)
		{
			character.setYVector(0);
			character.setY(wall.getBottomY() - character.getVerticalOffset());
		}
	}
}
