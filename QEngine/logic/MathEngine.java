package logic;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Point;

public class MathEngine
{
	/**
	 * Gives the distance between the two points.
	 *
	 * @param one First point to be used in this math.
	 * @param two Second point to be used.
	 * @return double which represents the distance between the two points.
	 */
	public static double distance(Point one, Point two)
	{
		return Math.sqrt(Math.pow(deltaX(one, two), 2) + Math.pow(deltaY(one, two), 2));
	}

	/**
	 * Returns a double which is distance from origin to the given point.
	 *
	 * @param point The point which you want to know distance of from the origin.
	 * @return Distance of this point from origin.
	 */
	public static double hypotenuse(Point point)
	{
		return Math.sqrt(Math.pow(point.getX(), 2) + Math.pow(point.getY(), 2));
	}

	/**
	 * Returns a point which is a representation of absolute distance between two points.
	 *
	 * @param one First point.
	 * @param two Second point.
	 * @return A point that is absolute difference between two points.
	 */
	public static Point absDistance(Point one, Point two)
	{
		return new Point(Math.abs(one.getX() - two.getX()), Math.abs(one.getY() - two.getY()));
	}

	/**
	 * Gives you the difference of y coordinates between two given points.
	 *
	 * @param one First point.
	 * @param two Second point whose y coordinate will be used.
	 * @return Difference between two y coordinates of the given points.
	 */
	public static double deltaY(Point one, Point two)
	{
		return one.getY() - two.getY();
	}

	/**
	 * Gives you the difference of x coordinates between two given points.
	 *
	 * @param one First point whose x coordinate will be used.
	 * @param two Second point.
	 * @return Difference between two x coordinates of the given points.
	 */
	public static double deltaX(Point one, Point two)
	{
		return one.getX() - two.getX();
	}

	/**
	 * Look up atan if need be.
	 *
	 * @param one First point.
	 * @param two Second point to compare to.
	 * @return The angle that the two points form.
	 */
	public static double angleRad(Point one, Point two)
	{
		return Math.atan2(deltaY(two, one), deltaX(two, one));
	}


	/**
	 * Look up atan if need be.
	 * Tells you shortest angle the two points form.
	 *
	 * @param one First point.
	 * @param two Second point to compare to.
	 * @return The angle that the two points form.
	 */
	public static double angleDeg(Point one, Point two)
	{
		return Math.toDegrees(angleRad(one, two));
	}
}
