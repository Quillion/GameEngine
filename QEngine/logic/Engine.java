package logic;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Point;
import platformer.BasicCharacter;
import platformer.ExtendedShapes.MBControls;
import platformer.ExtendedShapes.MControls;

/**
 * All of the collision detections and determining certain actions and such are going here.
 * This class is for basic collision detection.
 * There will be one for platformers, dungeon crawlers and such that are more specific to
 * that type of game.
 */
public class Engine
{

/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/******  LOGIC  ********************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/

	/**
	 * Actions to take before checking for collisions,
	 * Will check movement and gravity.
	 *
	 * @param character moving object whose vectors need to be upgraded.
	 */
	public static void preUpdate(MControls character)
	{
		if (character.getRight())
		{
			Engine.moveRight(character);
		}
		else if (character.getLeft())
		{
			Engine.moveLeft(character);
		}
		else
		{
			Engine.rest(character);
		}

		// action for falling down
		if (!character.isStanding())
		{
			character.incrementYVector(character.getGravity());
		}
	}

	/**
	 * Actions to take after collisions were checked for,
	 * This simply adds Vectors to the position.
	 *
	 * @param character moving object whose position should be updated.
	 */
	public static void postUpdate(MControls character)
	{
		character.move();
	}

	/**
	 * Actions to take before checking for collisions,
	 * Will check movement and gravity.
	 *
	 * @param character moving object whose vectors need to be upgraded.
	 */
	public static void preUpdate(MBControls character)
	{
		if (character.getRight())
		{
			Engine.moveRight(character);
		}
		else if (character.getLeft())
		{
			Engine.moveLeft(character);
		}
		else
		{
			Engine.rest(character);
		}

		// action for falling down
		if (!character.isStanding())
		{
			character.incrementYVector(character.getGravity());
		}
	}

	/**
	 * Actions to take after collisions were checked for,
	 * This simply adds Vectors to the position.
	 *
	 * @param character moving object whose position should be updated.
	 */
	public static void postUpdate(MBControls character)
	{
		character.move();
	}

/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/******  PHYSICS  ******************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/

	/**
	 * Makes the given character jump.
	 *
	 * @param character object that you would like to jump.
	 */
	public static void jump(MControls character)
	{
		if (character.isStanding())
		{
			character.setYVector(-character.getJump());
		}
	}

	/**
	 * Makes character move left,
	 * Character will never exceed it's max given velocity,
	 * and will accelerate by factor of accelerate while on ground and
	 * factor of air friction while in air.
	 *
	 * @param character object that you want to move left.
	 */
	public static void moveLeft(MControls character)
	{
		if (character.isStanding())
		{
			if (character.getXVector() < -character.getMaxSpeed())
				character.setXVector(-character.getMaxSpeed());
			else
				character.incrementXVector(-character.getAcceleration());
		}
		else
		{
			if (character.getXVector() < -character.getMaxSpeed())
				character.setXVector(-character.getMaxSpeed());
			else
				character.incrementXVector(-character.getAirFriction());
		}
	}

	/**
	 * Makes character move right,
	 * Character will never exceed it's max given velocity,
	 * and will accelerate by factor of accelerate while on ground and
	 * factor of air friction while in air.
	 *
	 * @param character object that you want to move right.
	 */
	public static void moveRight(MControls character)
	{
		if (character.isStanding())
		{
			if (character.getXVector() > character.getMaxSpeed())
				character.setXVector(character.getMaxSpeed());
			else
				character.incrementXVector(character.getAcceleration());
		}
		else
		{
			if (character.getXVector() > character.getMaxSpeed())
				character.setXVector(character.getMaxSpeed());
			else
				character.incrementXVector(character.getAirFriction());
		}
	}

	/**
	 * If character is not moving left or right then we decelerate him.
	 *
	 * @param character that stopped moving.
	 */
	public static void rest(MControls character)
	{
		// if character is standing on a platform
		if (character.isStanding())
		{
			// if character is moving right
			if (character.getXVector() > 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() < character.getGroundFriction())
					character.setXVector(0);
					// if character is still strongly moving right
				else
					character.incrementXVector(-character.getGroundFriction());
			}
			// if character is moving left
			else if (character.getXVector() < 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() > -character.getGroundFriction())
					character.setXVector(0);
					// if character is still strongly moving left
				else
					character.incrementXVector(character.getGroundFriction());
			}
		}
		// if character is in the air
		else
		{
			// if character is moving right
			if (character.getXVector() > 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() < character.getAirFriction())
					character.setXVector(0);
					// if character is still strongly moving right
				else
					character.incrementXVector(-character.getAirFriction());
			}
			// if character is moving left
			else if (character.getXVector() < 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() > -character.getAirFriction())
					character.setXVector(0);
					// if character is still strongly moving left
				else
					character.incrementXVector(character.getAirFriction());
			}
		}
	}

	/**
	 * Makes the given character jump.
	 *
	 * @param character object that you would like to jump.
	 */
	public static void jump(MBControls character)
	{
		if (character.isStanding())
		{
			character.incrementY(-1);
			character.setYVector(-character.getJump());
		}
	}

	/**
	 * Makes character move left,
	 * Character will never exceed it's max given velocity,
	 * and will accelerate by factor of accelerate while on ground and
	 * factor of air friction while in air.
	 *
	 * @param character object that you want to move left.
	 */
	public static void moveLeft(MBControls character)
	{
		if (character.isStanding())
		{
			if (character.getXVector() < -character.getMaxSpeed())
				character.setXVector(-character.getMaxSpeed());
			else
				character.incrementXVector(-character.getAcceleration());
		}
		else
		{
			if (character.getXVector() < -character.getMaxSpeed())
				character.setXVector(-character.getMaxSpeed());
			else
				character.incrementXVector(-character.getAirFriction());
		}
	}

	/**
	 * Makes character move right,
	 * Character will never exceed it's max given velocity,
	 * and will accelerate by factor of accelerate while on ground and
	 * factor of air friction while in air.
	 *
	 * @param character object that you want to move right.
	 */
	public static void moveRight(MBControls character)
	{
		if (character.isStanding())
		{
			if (character.getXVector() > character.getMaxSpeed())
				character.setXVector(character.getMaxSpeed());
			else
				character.incrementXVector(character.getAcceleration());
		}
		else
		{
			if (character.getXVector() > character.getMaxSpeed())
				character.setXVector(character.getMaxSpeed());
			else
				character.incrementXVector(character.getAirFriction());
		}
	}

	/**
	 * If character is not moving left or right then we decelerate him.
	 *
	 * @param character that stopped moving.
	 */
	public static void rest(MBControls character)
	{
		// if character is standing on a platform
		if (character.isStanding())
		{
			// if character is moving right
			if (character.getXVector() > 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() < character.getGroundFriction())
					character.setXVector(0);
					// if character is still strongly moving right
				else
					character.incrementXVector(-character.getGroundFriction());
			}
			// if character is moving left
			else if (character.getXVector() < 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() > -character.getGroundFriction())
					character.setXVector(0);
					// if character is still strongly moving left
				else
					character.incrementXVector(character.getGroundFriction());
			}
		}
		// if character is in the air
		else
		{
			// if character is moving right
			if (character.getXVector() > 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() < character.getAirFriction())
					character.setXVector(0);
					// if character is still strongly moving right
				else
					character.incrementXVector(-character.getAirFriction());
			}
			// if character is moving left
			else if (character.getXVector() < 0)
			{
				// if character is almost stopped and friction will make him go the other way
				if (character.getXVector() > -character.getAirFriction())
					character.setXVector(0);
					// if character is still strongly moving left
				else
					character.incrementXVector(character.getAirFriction());
			}
		}
	}

/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/******  KEY ACTION  ***************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/

	/**
	 * Actions for key presses.
	 * Changes boolean values according to key presses to do things.
	 *
	 * @param key       what key was pressed.
	 * @param character object whose key press to look out for.
	 */
	public static void keyPressed(int key, MControls character)
	{
		if (key == character.getLeftKey())
		{
			character.setLeft(true);
			character.setRight(false);
		}
		else if (key == character.getRightKey())
		{
			character.setLeft(false);
			character.setRight(true);
		}

		if (key == character.getJumpKey())
		{
			Engine.jump(character);
		}
	}

	/**
	 * Actions for key releases.
	 * Changes boolean values according to key presses to do things.
	 *
	 * @param key       what key was released.
	 * @param character object whose key release to look out for.
	 */
	public static void keyReleased(int key, MControls character)
	{
		if (key == character.getLeftKey())
		{
			character.setLeft(false);
		}
		else if (key == character.getRightKey())
		{
			character.setRight(false);
		}
	}

	/**
	 * Actions for key presses.
	 * Changes boolean values according to key presses to do things.
	 *
	 * @param key       what key was pressed.
	 * @param character object whose key press to look out for.
	 */
	public static void keyPressed(int key, MBControls character)
	{
		if (key == character.getLeftKey())
		{
			character.setLeft(true);
			character.setRight(false);
		}
		else if (key == character.getRightKey())
		{
			character.setLeft(false);
			character.setRight(true);
		}

		if (key == character.getJumpKey())
		{
			Engine.jump(character);
		}
	}

	/**
	 * Actions for key releases.
	 * Changes boolean values according to key presses to do things.
	 *
	 * @param key       what key was released.
	 * @param character object whose key release to look out for.
	 */
	public static void keyReleased(int key, MBControls character)
	{
		if (key == character.getLeftKey())
		{
			character.setLeft(false);
		}
		else if (key == character.getRightKey())
		{
			character.setRight(false);
		}
	}

	/**
	 * Actions for key presses.
	 * Changes boolean values according to key presses to do things.
	 *
	 * @param key       what key was pressed.
	 * @param character object whose key press to look out for.
	 */
	public static void keyPressed(int key, BasicCharacter character)
	{
		if (key == character.getLeftKey())
		{
			character.setLeft(true);
			character.setRight(false);
			character.setLeftPressed(true);
		}
		else if (key == character.getRightKey())
		{
			character.setLeft(false);
			character.setRight(true);
			character.setLeftPressed(false);
		}

		if (key == character.getJumpKey())
		{
			Engine.jump(character);
		}
	}

	/**
	 * Actions for key releases.
	 * Changes boolean values according to key presses to do things.
	 *
	 * @param key       what key was released.
	 * @param character object whose key release to look out for.
	 */
	public static void keyReleased(int key, BasicCharacter character)
	{
		if (key == character.getLeftKey())
		{
			character.setLeft(false);
		}
		else if (key == character.getRightKey())
		{
			character.setRight(false);
		}
	}

/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/******  EVENTS  *******************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/

	/**
	 * Returns a random number using Math.random method.
	 * Using Random class is better,
	 * but if it is just one time occurrence and you do not want to create a class then use this method.
	 *
	 * @param min The minimum number you want.
	 * @param max The maximum number you want.
	 * @return Random number between the ranges of min and max.
	 */
	public static int random(int min, int max)
	{
		return (min + (int) (Math.random() * ((max - min) + 1)));
	}

/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/*******  MATH  ********************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/

	public static double distance(Point one, Point two)
	{
		return Math.sqrt(Math.pow(deltaX(one, two), 2) + Math.pow(deltaY(one, two), 2));
	}

	public static double deltaY(Point one, Point two)
	{
		return one.getY() - two.getY();
	}

	public static double deltaX(Point one, Point two)
	{
		return one.getX() - two.getX();
	}
}
