package logic;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.*;
import constants.Constants;
import platformer.BasicSprite.MCharacter;

public class CollisionEngine
{
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param wall first object that will be used in collision checking.
//	 * @param point second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(Box wall, Point point)
//	{
//		return ((wall.getTopY() <= point.getY()) && (wall.getBottomY() >= point.getY()) &&
//				(wall.getLeftX() <= point.getX()) && (wall.getRightX() >= point.getX()));
//	}
//
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param wall first object that will be used in collision checking.
//	 * @param point second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(BBox wall, Point point)
//	{
//		return ((wall.getTopY() <= point.getY()) && (wall.getBottomY() >= point.getY()) &&
//				(wall.getLeftX() <= point.getX()) && (wall.getRightX() >= point.getX()));
//	}
//
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param ball first object that will be used in collision checking.
//	 * @param point second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(Ball ball, Point point)
//	{
//		return MathEngine.distance(ball.getCenter(), point) <= ball.getRadius();
//	}
//
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param wall1 first object that will be used in collision checking.
//	 * @param wall2 second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(Box wall1, Box wall2)
//	{
//		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1.getBottomY() >= wall2.getTopY()) &&
//				(wall1.getLeftX() <= wall2.getRightX()) && (wall1.getRightX() >= wall2.getLeftX()));
//	}
//
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param wall1 first object that will be used in collision checking.
//	 * @param wall2 second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(Box wall1, BBox wall2)
//	{
//		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1.getBottomY() >= wall2.getTopY()) &&
//				(wall1.getLeftX() <= wall2.getRightX()) && (wall1.getRightX() >= wall2.getLeftX()));
//	}
//
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param wall first object that will be used in collision checking.
//	 * @param ball second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(Box wall, Ball ball)
//	{
//		return collision(wall, ball.getCenter()) ||
//				collision(ball, wall.getTopLeft()) ||
//				collision(ball, wall.getTopRight()) ||
//				collision(ball, wall.getBottomLeft()) ||
//				collision(ball, wall.getBottomRight());
//	}
//
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param wall1 first object that will be used in collision checking.
//	 * @param wall2 second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(BBox wall1, BBox wall2)
//	{
//		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1.getBottomY() >= wall2.getTopY()) &&
//				(wall1.getLeftX() <= wall2.getRightX()) && (wall1.getRightX() >= wall2.getLeftX()));
//	}
//
//	/**
//	 * Checks to see if the objects have already collided.
//	 *
//	 * @param wall first object that will be used in collision checking.
//	 * @param ball second object that will be used in collision checking.
//	 * @return true if the object have collided and false if they didn't.
//	 */
//	public static boolean collision(BBox wall, Ball ball)
//	{
//		return collision(wall, ball.getCenter()) ||
//				collision(ball, wall.getTopLeft()) ||
//				collision(ball, wall.getTopRight()) ||
//				collision(ball, wall.getBottomLeft()) ||
//				collision(ball, wall.getBottomRight());
//	}
//
//	/**
//	 * Checks for collision between two balls.
//	 * @param ball1 The first ball.
//	 * @param ball2 The second ball.
//	 * @return True if the balls have collided, false otherwise.
//	 */
//	public static boolean collision(Ball ball1, Ball ball2)
//	{
//		return MathEngine.distance(ball1.getCenter(), ball2.getCenter()) < (ball1.getRadius() + ball2.getRadius());
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MCharacter character, Box wall)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
//				(character.getTopY() + character.getYVector()) < wall.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
//					character.getLeftX() >= wall.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
//					character.getRightX() <= wall.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MCharacter character, BBox wall)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
//				(character.getTopY() + character.getYVector()) < wall.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
//					character.getLeftX() >= wall.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
//					character.getRightX() <= wall.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param ball      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MBox character, Ball ball)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > ball.getTopY() &&
//				(character.getTopY() + character.getYVector()) < ball.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= ball.getRightX() &&
//					character.getLeftX() >= ball.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= ball.getLeftX() &&
//					character.getRightX() <= ball.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBox character1, MBox character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBox character1, MBBox character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBox character1, MBall character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MBBox character, Box wall)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
//				(character.getTopY() + character.getYVector()) < wall.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
//					character.getLeftX() >= wall.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
//					character.getRightX() <= wall.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MBBox character, BBox wall)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
//				(character.getTopY() + character.getYVector()) < wall.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
//					character.getLeftX() >= wall.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
//					character.getRightX() <= wall.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param ball      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MBBox character, Ball ball)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > ball.getTopY() &&
//				(character.getTopY() + character.getYVector()) < ball.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= ball.getRightX() &&
//					character.getLeftX() >= ball.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= ball.getLeftX() &&
//					character.getRightX() <= ball.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBBox character1, MBox character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBBox character1, MBBox character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBBox character1, MBall character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MBall character, Box wall)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
//				(character.getTopY() + character.getYVector()) < wall.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
//					character.getLeftX() >= wall.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
//					character.getRightX() <= wall.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MBall character, BBox wall)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > wall.getTopY() &&
//				(character.getTopY() + character.getYVector()) < wall.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= wall.getRightX() &&
//					character.getLeftX() >= wall.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= wall.getLeftX() &&
//					character.getRightX() <= wall.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's sides have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param ball      object with which you want to check collision.
//	 * @return LEFT if  character's left side has collided with the wall,
//	 *         RIGHT if character's right side has collided with the wall,
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction horizontalCollision(MBall character, Ball ball)
//	{
//		// make sure that the character will be within top and bottom of the wall
//		if ((character.getBottomY() + character.getYVector()) > ball.getTopY() &&
//				(character.getTopY() + character.getYVector()) < ball.getBottomY())
//		{
//			//left collision
//			if ((character.getLeftX() + character.getXVector()) <= ball.getRightX() &&
//					character.getLeftX() >= ball.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character.getRightX() + character.getXVector()) >= ball.getLeftX() &&
//					character.getRightX() <= ball.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBall character1, MBox character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBall character1, MBBox character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks for collision of character1's sides against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check for.
//	 * @param character2 movable object whose collision you want to check character2 against.
//	 * @return LEFT if chaacter1's left side hits character2,
//	 *         RIGHT if character1's right side hits character2,
//	 *         NONE if no side collision happens.
//	 */
//	public static Constants.Direction horizontalCollision(MBall character1, MBall character2)
//	{
//		if ((character1.getBottomY() + character1.getYVector()) > (character2.getTopY() + character2.getYVector()) &&
//				(character1.getTopY() + character1.getYVector()) < (character2.getBottomY() + character2.getYVector()))
//		{
//			// left collision
//			if ((character1.getLeftX() + character1.getXVector()) <= (character2.getRightX() + character2.getXVector()) &&
//					character1.getLeftX() >= character2.getRightX())
//			{
//				return Constants.Direction.Left;
//			}
//			// right collision
//			else if ((character1.getRightX() + character1.getXVector()) >= (character2.getLeftX() + character2.getXVector()) &&
//					character1.getRightX() <= character2.getLeftX())
//			{
//				return Constants.Direction.Right;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given wall object.
//	 *
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MCharacter character, Box wall)
//	{
//		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
//				(character.getRightX() + character.getXVector()) > wall.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
//					character.getBottomY() <= wall.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
//					character.getTopY() >= wall.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given wall object.
//	 *
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MCharacter character, BBox wall)
//	{
//		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
//				(character.getRightX() + character.getXVector()) > wall.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
//					character.getBottomY() <= wall.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
//					character.getTopY() >= wall.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given ball object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param ball      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MBox character, Ball ball)
//	{
//		if ((character.getLeftX() + character.getXVector()) < ball.getRightX() &&
//				(character.getRightX() + character.getXVector()) > ball.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= ball.getTopY() &&
//					character.getBottomY() <= ball.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= ball.getBottomY() &&
//					character.getTopY() >= ball.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBox character1, MBox character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBox character1, MBBox character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBox character1, MBall character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MBBox character, Box wall)
//	{
//		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
//				(character.getRightX() + character.getXVector()) > wall.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
//					character.getBottomY() <= wall.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
//					character.getTopY() >= wall.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MBBox character, BBox wall)
//	{
//		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
//				(character.getRightX() + character.getXVector()) > wall.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
//					character.getBottomY() <= wall.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
//					character.getTopY() >= wall.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given ball object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param ball      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MBBox character, Ball ball)
//	{
//		if ((character.getLeftX() + character.getXVector()) < ball.getRightX() &&
//				(character.getRightX() + character.getXVector()) > ball.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= ball.getTopY() &&
//					character.getBottomY() <= ball.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= ball.getBottomY() &&
//					character.getTopY() >= ball.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBBox character1, MBox character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBBox character1, MBBox character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBBox character1, MBall character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MBall character, Box wall)
//	{
//		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
//				(character.getRightX() + character.getXVector()) > wall.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
//					character.getBottomY() <= wall.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
//					character.getTopY() >= wall.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given wall object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param wall      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MBall character, BBox wall)
//	{
//		if ((character.getLeftX() + character.getXVector()) < wall.getRightX() &&
//				(character.getRightX() + character.getXVector()) > wall.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= wall.getTopY() &&
//					character.getBottomY() <= wall.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= wall.getBottomY() &&
//					character.getTopY() >= wall.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Checks to see if character's top and bottom have collision with the given ball object.
//	 *
//	 * @param character object which you want to check collision of.
//	 * @param ball      object with which you want to check collision.
//	 * @return DOWN if  character's bottom side has collided with the wall (he landed),
//	 *         RIGHT if character's top side has collided with the wall (his head hit the ceiling),
//	 *         NONE if no collision happened.
//	 */
//	public static Constants.Direction verticalCollision(MBall character, Ball ball)
//	{
//		if ((character.getLeftX() + character.getXVector()) < ball.getRightX() &&
//				(character.getRightX() + character.getXVector()) > ball.getLeftX())
//		{
//			// bottom collision
//			if ((character.getBottomY() + character.getYVector()) >= ball.getTopY() &&
//					character.getBottomY() <= ball.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character.getTopY() + character.getYVector()) <= ball.getBottomY() &&
//					character.getTopY() >= ball.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBall character1, MBox character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBall character1, MBBox character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
//
//	/**
//	 * Check for collision of character1's top and bottom against character2.
//	 *
//	 * @param character1 movable object whose collision you want to check.
//	 * @param character2 movable object whose collision you want to check against.
//	 * @return DOWN if character1's feet hit character2's head,
//	 *         UP if character2's feet hit character1's head,
//	 *         NONE if no vertical collision happens.
//	 */
//	public static Constants.Direction verticalCollision(MBall character1, MBall character2)
//	{
//		if ((character1.getLeftX() + character1.getXVector()) < (character2.getRightX() + character2.getXVector()) &&
//				(character1.getRightX() + character1.getXVector()) > (character2.getLeftX() + character2.getXVector()))
//		{
//			// bottom collision
//			if ((character1.getBottomY() + character1.getYVector()) >= (character2.getTopY() + character2.getYVector()) &&
//					character1.getBottomY() <= character2.getTopY())
//			{
//				return Constants.Direction.Down;
//			}
//			// top collision
//			else if ((character1.getTopY() + character1.getYVector()) <= (character2.getBottomY() + character2.getYVector()) &&
//					character1.getTopY() >= character2.getBottomY())
//			{
//				return Constants.Direction.Up;
//			}
//		}
//		return Constants.Direction.None;
//	}
}
