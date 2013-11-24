package logic;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicShapes.BBox;
import BasicShapes.Box;
import Constants.Constants;
import platformer.BasicCharacter;
import platformer.ExtendedShapes.MBControls;
import platformer.ExtendedShapes.MControls;
import platformer.MovingShapes.MBBox;
import platformer.MovingShapes.MBox;

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
		character.incrementX(character.getXVector());
		character.incrementY(character.getYVector());
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
		character.incrementX(character.getXVector());
		character.incrementY(character.getYVector());
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
/******  COLLISIONS  ***************************************************************************************************************************************/
/***********************************************************************************************************************************************************/
/***********************************************************************************************************************************************************/

	/**
	 * Checks to see if character's sides have collision with the given wall object.
	 *
	 * @param character object which you want to check collision of.
	 * @param wall      object with which you want to check collision.
	 * @return LEFT if  character's left side has collided with the wall,
	 *         RIGHT if character's right side has collided with the wall,
	 *         NONE if no collision happened.
	 */
	public static int horizontalCollision(MBox character, Box wall)
	{
		// make sure that the character will be within top and bottom of the wall
		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
				(character.getTopY() + character.getYVector()) < wall.getBottomY())
		{
			//left collision
			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
					character.getLeftX() >= wall.getRightX())
			{
				return Constants.LEFT;
			}
			// right collision
			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
					character.getRightX() <= wall.getLeftX())
			{
				return Constants.RIGHT;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Checks to see if character's top and bottom have collision with the given wall object.
	 *
	 * @param character object which you want to check collision of.
	 * @param wall      object with which you want to check collision.
	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
	 *         NONE if no collision happened.
	 */
	public static int verticalCollision(MBox character, Box wall)
	{
		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
				(character.getRightX() + character.getXVector()) > wall.getLeftX())
		{
			// bottom collision
			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
					character.getBottomY() <= wall.getTopY())
			{
				return Constants.DOWN;
			}
			// top collision
			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
					character.getTopY() >= wall.getBottomY())
			{
				return Constants.UP;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Checks for collision of character1's sides against character2.
	 *
	 * @param character1 movable object whose collision you want to check for.
	 * @param character2 movable object whose collision you want to check character2 against.
	 * @return LEFT if chaacter1's left side hits character2,
	 *         RIGHT if character1's right side hits character2,
	 *         NONE if no side collision happens.
	 */
	public static int horizontalCollision(MBox character1, MBox character2)
	{
		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
		{
			// left collision
			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
					character1.getLeftX() >= character2.getRightX())
			{
				return Constants.LEFT;
			}
			// right collision
			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
					character1.getRightX() <= character2.getLeftX())
			{
				return Constants.RIGHT;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Check for collision of character1's top and bottom against character2.
	 *
	 * @param character1 movable object whose collision you want to check.
	 * @param character2 movable object whose collision you want to check against.
	 * @return DOWN if character1's feet hit character2's head,
	 *         UP if character2's feet hit character1's head,
	 *         NONE if no vertical collision happens.
	 */
	public static int verticalCollision(MBox character1, MBox character2)
	{
		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
		{
			// bottom collision
			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
					character1.getBottomY() <= character2.getTopY())
			{
				return Constants.DOWN;
			}
			// top collision
			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
					character1.getTopY() >= character2.getBottomY())
			{
				return Constants.UP;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Checks to see if character's sides have collision with the given wall object.
	 *
	 * @param character object which you want to check collision of.
	 * @param wall      object with which you want to check collision.
	 * @return LEFT if  character's left side has collided with the wall,
	 *         RIGHT if character's right side has collided with the wall,
	 *         NONE if no collision happened.
	 */
	public static int horizontalCollision(MBBox character, BBox wall)
	{
		// make sure that the character will be within top and bottom of the wall
		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
				(character.getTopY() + character.getYVector()) < wall.getBottomY())
		{
			//left collision
			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
					character.getLeftX() >= wall.getRightX())
			{
				return Constants.LEFT;
			}
			// right collision
			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
					character.getRightX() <= wall.getLeftX())
			{
				return Constants.RIGHT;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Checks to see if character's top and bottom have collision with the given wall object.
	 *
	 * @param character object which you want to check collision of.
	 * @param wall      object with which you want to check collision.
	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
	 *         NONE if no collision happened.
	 */
	public static int verticalCollision(MBBox character, BBox wall)
	{
		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
				(character.getRightX() + character.getXVector()) > wall.getLeftX())
		{
			// bottom collision
			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
					character.getBottomY() <= wall.getTopY())
			{
				return Constants.DOWN;
			}
			// top collision
			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
					character.getTopY() >= wall.getBottomY())
			{
				return Constants.UP;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Checks for collision of character1's sides against character2.
	 *
	 * @param character1 movable object whose collision you want to check for.
	 * @param character2 movable object whose collision you want to check character2 against.
	 * @return LEFT if character1's left side hits character2,
	 *         RIGHT if character1's right side hits character2,
	 *         NONE if no side collision happens.
	 */
	public static int horizontalCollision(MBBox character1, MBBox character2)
	{
		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
		{
			// left collision
			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
					character1.getLeftX() >= character2.getRightX())
			{
				return Constants.LEFT;
			}
			// right collision
			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
					character1.getRightX() <= character2.getLeftX())
			{
				return Constants.RIGHT;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Check for collision of character1's top and bottom against character2.
	 *
	 * @param character1 movable object whose collision you want to check.
	 * @param character2 movable object whose collision you want to check against.
	 * @return DOWN if character1's feet hit character2's head,
	 *         UP if character2's feet hit character1's head,
	 *         NONE if no vertical collision happens.
	 */
	public static int verticalCollision(MBBox character1, MBBox character2)
	{
		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
		{
			// bottom collision
			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
					character1.getBottomY() <= character2.getTopY())
			{
				return Constants.DOWN;
			}
			// top collision
			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
					character1.getTopY() >= character2.getBottomY())
			{
				return Constants.UP;
			}
		}
		return Constants.NONE;
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall1 first object that will be used in collision checking.
	 * @param wall2 second object that will be used in collision checking.
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(Box wall1, Box wall2)
	{
		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1.getBottomY() >= wall2.getTopY()) &&
				(wall1.getLeftX() <= wall2.getRightX()) && (wall1.getRightX() >= wall2.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall1 first object that will be used in collision checking.
	 * @param wall2 second object that will be used in collision checking.
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(BBox wall1, BBox wall2)
	{
		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1.getBottomY() >= wall2.getTopY()) &&
				(wall1.getLeftX() <= wall2.getRightX()) && (wall1.getRightX() >= wall2.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param character moving object that will be used in collision checking.
	 * @param wall      wall object that will be used in collision checking.
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(MBox character, Box wall)
	{
		return ((character.getTopY() <= wall.getBottomY()) && (character.getBottomY() >= wall.getTopY()) &&
				(character.getLeftX() <= wall.getRightX()) && (character.getRightX() >= wall.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param character moving object that will be used in collision checking.
	 * @param wall      wall object that will be used in collision checking.
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(MBBox character, Box wall)
	{
		return ((character.getTopY() <= wall.getBottomY()) && (character.getBottomY() >= wall.getTopY()) &&
				(character.getLeftX() <= wall.getRightX()) && (character.getRightX() >= wall.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param character moving object that will be used in collision checking.
	 * @param wall      wall object that will be used in collision checking.
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(MBBox character, BBox wall)
	{
		return ((character.getTopY() <= wall.getBottomY()) && (character.getBottomY() >= wall.getTopY()) &&
				(character.getLeftX() <= wall.getRightX()) && (character.getRightX() >= wall.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param character1 first moving object that will be used in collision checking.
	 * @param character2 second moving object that will be used in collision checking.
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(MBox character1, MBox character2)
	{
		return ((character1.getTopY() <= character2.getBottomY()) && (character1.getBottomY() >= character2.getTopY()) &&
				(character1.getLeftX() <= character2.getRightX()) && (character1.getRightX() >= character2.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param character1 first moving object that will be used in collision checking.
	 * @param character2 second moving object that will be used in collision checking.
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(MBBox character1, MBBox character2)
	{
		return ((character1.getTopY() <= character2.getBottomY()) && (character1.getBottomY() >= character2.getTopY()) &&
				(character1.getLeftX() <= character2.getRightX()) && (character1.getRightX() >= character2.getLeftX()));
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
}
