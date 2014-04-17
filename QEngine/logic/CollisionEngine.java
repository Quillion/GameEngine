package logic;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Point;
import basicObjects.shapes.*;
import constants.Constants;

public class CollisionEngine
{
	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall
	 * 		first object that will be used in collision checking.
	 * @param point
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(Box wall, Point point)
	{
		return ((wall.getTopY() <= point.getY()) && (wall.getBottomY() >= point
				.getY()) &&
				(wall.getLeftX() <= point.getX()) && (wall.getRightX() >= point
				.getX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall
	 * 		first object that will be used in collision checking.
	 * @param point
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(BBox wall, Point point)
	{
		return ((wall.getTopY() <= point.getY()) && (wall.getBottomY() >= point
				.getY()) &&
				(wall.getLeftX() <= point.getX()) && (wall.getRightX() >= point
				.getX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param ball
	 * 		first object that will be used in collision checking.
	 * @param point
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(Ball ball, Point point)
	{
		return MathEngine.distance(ball.getCenter(), point) <= ball.getRadius();
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param ball
	 * 		first object that will be used in collision checking.
	 * @param point
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(BBall ball, Point point)
	{
		return MathEngine.distance(ball.getCenter(), point) <= ball
				.getOffsetRadius();
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall1
	 * 		first object that will be used in collision checking.
	 * @param wall2
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(Box wall1, Box wall2)
	{
		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1
				.getBottomY() >= wall2.getTopY()) &&
				(wall1.getLeftX() <= wall2.getRightX()) && (wall1
				.getRightX() >= wall2.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall1
	 * 		first object that will be used in collision checking.
	 * @param wall2
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(BBox wall1, Box wall2)
	{
		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1
				.getBottomY() >= wall2.getTopY()) &&
				(wall1.getLeftX() <= wall2.getRightX()) && (wall1
				.getRightX() >= wall2.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall1
	 * 		first object that will be used in collision checking.
	 * @param wall2
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(BBox wall1, BBox wall2)
	{
		return ((wall1.getTopY() <= wall2.getBottomY()) && (wall1
				.getBottomY() >= wall2.getTopY()) &&
				(wall1.getLeftX() <= wall2.getRightX()) && (wall1
				.getRightX() >= wall2.getLeftX()));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall
	 * 		first object that will be used in collision checking.
	 * @param ball
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(Box wall, Ball ball)
	{
		double hypotenuse = MathEngine
				.hypotenuse(wall.getCenter(), ball.getCenter());
		double distance = ball.getRadius() < hypotenuse ? ball
				.getRadius() : hypotenuse;
		return collision(wall, MathEngine.getEdgeAt(
				MathEngine.angleRad(ball.getCenter(), wall.getCenter()),
				ball.getCenter(),
				distance));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall
	 * 		first object that will be used in collision checking.
	 * @param ball
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(BBox wall, Ball ball)
	{
		double hypotenuse = MathEngine
				.hypotenuse(wall.getCenter(), ball.getCenter());
		double distance = ball.getRadius() < hypotenuse ? ball
				.getRadius() : hypotenuse;
		System.out.println(distance);
		return collision(wall, MathEngine.getEdgeAt(
				MathEngine.angleRad(ball.getCenter(), wall.getCenter()),
				ball.getCenter(),
				distance));
	}

	/**
	 * Checks to see if the objects have already collided.
	 *
	 * @param wall
	 * 		first object that will be used in collision checking.
	 * @param ball
	 * 		second object that will be used in collision checking.
	 *
	 * @return true if the object have collided and false if they didn't.
	 */
	public static boolean collision(BBox wall, BBall ball)
	{
		double hypotenuse = MathEngine
				.hypotenuse(wall.getCenter(), ball.getCenter());
		double distance = ball.getOffsetRadius() < hypotenuse ?
				ball.getOffsetRadius() : hypotenuse;
		return collision(wall, MathEngine.getEdgeAt(
				MathEngine.angleRad(ball.getCenter(), wall.getCenter()),
				ball.getCenter(),
				distance));
	}

	/**
	 * Checks for collision between two balls.
	 *
	 * @param ball1
	 * 		The first ball.
	 * @param ball2
	 * 		The second ball.
	 *
	 * @return True if the balls have collided, false otherwise.
	 */
	public static boolean collision(Ball ball1, Ball ball2)
	{
		return MathEngine
				.distance(ball1.getCenter(), ball2.getCenter()) <= (ball1
				.getRadius() + ball2.getRadius());
	}

	/**
	 * Checks for collision between two balls.
	 *
	 * @param ball1
	 * 		The first ball.
	 * @param ball2
	 * 		The second ball.
	 *
	 * @return True if the balls have collided, false otherwise.
	 */
	public static boolean collision(BBall ball1, Ball ball2)
	{
		return MathEngine
				.distance(ball1.getCenter(), ball2.getCenter()) <= (ball1
				.getOffsetRadius() + ball2
				.getRadius());
	}

	/**
	 * Checks for collision between two balls.
	 *
	 * @param ball1
	 * 		The first ball.
	 * @param ball2
	 * 		The second ball.
	 *
	 * @return True if the balls have collided, false otherwise.
	 */
	public static boolean collision(BBall ball1, BBall ball2)
	{
		return MathEngine
				.distance(ball1.getCenter(), ball2.getCenter()) <= (ball1
				.getOffsetRadius() + ball2
				.getOffsetRadius());
	}

	/**
	 * Checks to see if character's sides have collision with the given wall
	 * object.
	 *
	 * @param character
	 * 		object which you want to check collision of.
	 * @param wall
	 * 		object with which you want to check collision.
	 *
	 * @return LEFT if  character's left side has collided with the wall, RIGHT
	 * if character's right side has collided with the wall, NONE if no
	 * collision happened.
	 */
	public static Constants.Direction horizontalCollision(MBox character,
														  Box wall)
	{
		// make sure that the character will be within top and bottom of the wall
		if ((character.getBottomY() + character.getYVector()) > wall
				.getTopY() &&
				(character.getTopY() + character.getYVector()) < wall
						.getBottomY())
		{
			//left collision
			if ((character.getLeftX() + character.getXVector()) <= wall
					.getRightX() &&
					character.getLeftX() >= wall.getRightX())
			{
				return Constants.Direction.Left;
			}
			// right collision
			else if ((character.getRightX() + character.getXVector()) >= wall
					.getLeftX() &&
					character.getRightX() <= wall.getLeftX())
			{
				return Constants.Direction.Right;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks to see if character's sides have collision with the given wall
	 * object.
	 *
	 * @param character
	 * 		object which you want to check collision of.
	 * @param wall
	 * 		object with which you want to check collision.
	 *
	 * @return LEFT if  character's left side has collided with the wall, RIGHT
	 * if character's right side has collided with the wall, NONE if no
	 * collision happened.
	 */
	public static Constants.Direction horizontalCollision(MBox character,
														  BBox wall)
	{
		// make sure that the character will be within top and bottom of the wall
		if ((character.getBottomY() + character.getYVector()) > wall
				.getTopY() &&
				(character.getTopY() + character.getYVector()) < wall
						.getBottomY())
		{
			//left collision
			if ((character.getLeftX() + character.getXVector()) <= wall
					.getRightX() &&
					character.getLeftX() >= wall.getRightX())
			{
				return Constants.Direction.Left;
			}
			// right collision
			else if ((character.getRightX() + character.getXVector()) >= wall
					.getLeftX() &&
					character.getRightX() <= wall.getLeftX())
			{
				return Constants.Direction.Right;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks for collision of character1's sides against character2.
	 *
	 * @param character1
	 * 		movable object whose collision you want to check for.
	 * @param character2
	 * 		movable object whose collision you want to check character2 against.
	 *
	 * @return LEFT if character1's left side hits character2, RIGHT if
	 * character1's right side hits character2, NONE if no side collision
	 * happens.
	 */
	public static Constants.Direction horizontalCollision(MBox character1,
														  MBox character2)
	{
		if ((character1.getBottomY() + character1.getYVector()) > (character2
				.getTopY() + character2.getYVector()) &&
				(character1.getTopY() + character1.getYVector()) < (character2
						.getBottomY() + character2.getYVector()))
		{
			// left collision
			if ((character1.getLeftX() + character1.getXVector()) <= (character2
					.getRightX() + character2
					.getXVector()) &&
					character1.getLeftX() >= character2.getRightX())
			{
				return Constants.Direction.Left;
			}
			// right collision
			else if ((character1.getRightX() + character1
					.getXVector()) >= (character2.getLeftX() + character2
					.getXVector()) &&
					character1.getRightX() <= character2.getLeftX())
			{
				return Constants.Direction.Right;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks for collision of character1's sides against character2.
	 *
	 * @param character
	 * 		movable object whose collision you want to check for.
	 * @param ball
	 * 		ball in relation to which this objects' orientation will be checked.
	 *
	 * @return LEFT if character1's left side hits character2, RIGHT if
	 * character1's right side hits character2, NONE if no side collision
	 * happens.
	 */
	public static Constants.Direction horizontalOrientation(MBall character,
															Ball ball)
	{
		if (character.getBottomY() > ball.getTopY() &&
				character.getTopY() < ball.getBottomY())
		{
			// left collision
			if (character.getCenterX() > ball.getCenterX())
			{
				return Constants.Direction.Left;
			}
			// right collision
			else if (character.getCenterX() < ball.getCenterX())
			{
				return Constants.Direction.Right;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks for collision of character1's sides against character2.
	 *
	 * @param character
	 * 		movable object whose collision you want to check for.
	 * @param ball
	 * 		ball in relation to which this objects' orientation will be checked.
	 *
	 * @return LEFT if character1's left side hits character2, RIGHT if
	 * character1's right side hits character2, NONE if no side collision
	 * happens.
	 */
	public static Constants.Direction horizontalOrientation(MBall character,
															BBall ball)
	{
		if (character.getBottomY() > ball.getTopY() &&
				character.getTopY() < ball.getBottomY())
		{
			// left collision
			if (character.getCenterX() > ball.getCenterX())
			{
				return Constants.Direction.Left;
			}
			// right collision
			else if (character.getCenterX() < ball.getCenterX())
			{
				return Constants.Direction.Right;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks for collision of character1's sides against character2.
	 *
	 * @param character1
	 * 		movable object whose collision you want to check for.
	 * @param character2
	 * 		character2 in relation to which this objects' orientation will be
	 * 		checked.
	 *
	 * @return LEFT if character1's left side hits character2, RIGHT if
	 * character1's right side hits character2, NONE if no side collision
	 * happens.
	 */
	public static Constants.Direction horizontalOrientation(MBall character1,
															MBall character2)
	{
		if (character1.getBottomY() > character2.getTopY() &&
				character1.getTopY() < character2.getBottomY())
		{
			// left collision
			if (character1.getCenterX() > character2.getCenterX())
			{
				return Constants.Direction.Left;
			}
			// right collision
			else if (character1.getCenterX() < character2.getCenterX())
			{
				return Constants.Direction.Right;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks to see if character's top and bottom have collision with the given
	 * wall object.
	 *
	 * @param character
	 * 		object which you want to check collision of.
	 * @param wall
	 * 		object with which you want to check collision.
	 *
	 * @return DOWN if  character's bottom side has collided with the wall (he
	 * landed), RIGHT if character's top side has collided with the wall (his
	 * head hit the ceiling), NONE if no collision happened.
	 */
	public static Constants.Direction verticalCollision(MBox character,
														Box wall)
	{
		if ((character.getLeftX() + character.getXVector()) < wall
				.getRightX() &&
				(character.getRightX() + character.getXVector()) > wall
						.getLeftX())
		{
			// bottom collision
			if ((character.getBottomY() + character.getYVector()) >= wall
					.getTopY() &&
					character.getBottomY() <= wall.getTopY())
			{
				return Constants.Direction.Down;
			}
			// top collision
			else if ((character.getTopY() + character.getYVector()) <= wall
					.getBottomY() &&
					character.getTopY() >= wall.getBottomY())
			{
				return Constants.Direction.Up;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks to see if character's top and bottom have collision with the given
	 * wall object.
	 *
	 * @param character
	 * 		object which you want to check collision of.
	 * @param wall
	 * 		object with which you want to check collision.
	 *
	 * @return DOWN if  character's bottom side has collided with the wall (he
	 * landed), RIGHT if character's top side has collided with the wall (his
	 * head hit the ceiling), NONE if no collision happened.
	 */
	public static Constants.Direction verticalCollision(MBox character,
														BBox wall)
	{
		if ((character.getLeftX() + character.getXVector()) < wall
				.getRightX() &&
				(character.getRightX() + character.getXVector()) > wall
						.getLeftX())
		{
			// bottom collision
			if ((character.getBottomY() + character.getYVector()) >= wall
					.getTopY() &&
					character.getBottomY() <= wall.getTopY())
			{
				return Constants.Direction.Down;
			}
			// top collision
			else if ((character.getTopY() + character.getYVector()) <= wall
					.getBottomY() &&
					character.getTopY() >= wall.getBottomY())
			{
				return Constants.Direction.Up;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Check for collision of character1's top and bottom against character2.
	 *
	 * @param character1
	 * 		movable object whose collision you want to check.
	 * @param character2
	 * 		movable object whose collision you want to check against.
	 *
	 * @return DOWN if character1's feet hit character2's head, UP if
	 * character2's feet hit character1's head, NONE if no vertical collision
	 * happens.
	 */
	public static Constants.Direction verticalCollision(MBox character1,
														MBox character2)
	{
		if ((character1.getLeftX() + character1.getXVector()) < (character2
				.getRightX() + character2.getXVector()) &&
				(character1.getRightX() + character1.getXVector()) > (character2
						.getLeftX() + character2.getXVector()))
		{
			// bottom collision
			if ((character1.getBottomY() + character1
					.getYVector()) >= (character2.getTopY() + character2
					.getYVector()) &&
					character1.getBottomY() <= character2.getTopY())
			{
				return Constants.Direction.Down;
			}
			// top collision
			else if ((character1.getTopY() + character1
					.getYVector()) <= (character2.getBottomY() + character2
					.getYVector()) &&
					character1.getTopY() >= character2.getBottomY())
			{
				return Constants.Direction.Up;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks to see if character's top and bottom have collision with the given
	 * ball object.
	 *
	 * @param character
	 * 		object which you want to check collision of.
	 * @param ball
	 * 		object with which you want to check collision.
	 *
	 * @return DOWN if  character's bottom side has collided with the wall (he
	 * landed), RIGHT if character's top side has collided with the wall (his
	 * head hit the ceiling), NONE if no collision happened.
	 */
	public static Constants.Direction verticalOrientation(MBall character,
														  Ball ball)
	{
		if (character.getLeftX() < ball.getRightX() &&
				character.getRightX() > ball.getLeftX())
		{
			// bottom collision
			if (character.getCenterY() < ball.getCenterY())
			{
				return Constants.Direction.Down;
			}
			// top collision
			else if (character.getCenterY() > ball.getCenterY())
			{
				return Constants.Direction.Up;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Checks to see if character's top and bottom have collision with the given
	 * ball object.
	 *
	 * @param character
	 * 		object which you want to check collision of.
	 * @param ball
	 * 		object with which you want to check collision.
	 *
	 * @return DOWN if  character's bottom side has collided with the wall (he
	 * landed), RIGHT if character's top side has collided with the wall (his
	 * head hit the ceiling), NONE if no collision happened.
	 */
	public static Constants.Direction verticalOrientation(MBox character,
														  BBall ball)
	{
		if (character.getLeftX() < ball.getRightX() &&
				character.getRightX() > ball.getLeftX())
		{
			// bottom collision
			if (character.getCenterY() < ball.getCenterY())
			{
				return Constants.Direction.Down;
			}
			// top collision
			else if (character.getCenterY() > ball.getCenterY())
			{
				return Constants.Direction.Up;
			}
		}
		return Constants.Direction.None;
	}

	/**
	 * Check for collision of character1's top and bottom against character2.
	 *
	 * @param character1
	 * 		movable object whose collision you want to check.
	 * @param character2
	 * 		movable object whose collision you want to check against.
	 *
	 * @return DOWN if character1's feet hit character2's head, UP if
	 * character2's feet hit character1's head, NONE if no vertical collision
	 * happens.
	 */
	public static Constants.Direction verticalOrientation(MBox character1,
														  MBall character2)
	{
		if (character1.getLeftX() < character2.getRightX() &&
				character1.getRightX() > character2.getLeftX())
		{
			// bottom collision
			if (character1.getCenterY() < character2.getCenterY())
			{
				return Constants.Direction.Down;
			}
			// top collision
			else if (character1.getCenterY() > character2.getCenterY())
			{
				return Constants.Direction.Up;
			}
		}
		return Constants.Direction.None;
	}
}
