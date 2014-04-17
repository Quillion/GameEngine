package logic.platformer;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.shapes.BBox;
import constants.Constants;
import platformer.MBox;

/**
 * Tests for collision for platformer.
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
			if (character.getGravity() > 0)
			{
				character.setStanding(true);
			}
		}
		else if (direction.equals(Constants.Direction.Up) && character
				.getYVector() < 0)
		{
			character.setYVector(0);
			character.setY(wall.getBottomY() - character.getVerticalOffset());
			if (character.getGravity() < 0)
			{
				character.setStanding(true);
			}
		}
	}

	public static void platformCollision(MBox character, BBox wall)
	{
		Constants.Direction direction = CollisionEngine
				.verticalCollision(character, wall);
		if (direction.equals(Constants.Direction.Down) && character
				.getYVector() > 0)
		{
			if (character.getGravity() > 0)
			{
				character.setYVector(0);
				character
						.setY(wall.getTopY() - character.getHeight() + character
								.getVerticalOffset());
				character.setStanding(true);
			}
		}
		else if (direction.equals(Constants.Direction.Up) && character
				.getYVector() < 0)
		{
			if (character.getGravity() < 0)
			{
				character.setYVector(0);
				character.setY(wall.getBottomY() - character
						.getVerticalOffset());
				character.setStanding(true);
			}
		}
	}
}
