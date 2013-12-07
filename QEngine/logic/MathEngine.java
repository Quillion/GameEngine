package logic;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Point;

public class MathEngine
{
	public static double distance(Point one, Point two)
	{
		return Math.sqrt(Math.pow(deltaX(one, two), 2) + Math.pow(deltaY(one, two), 2));
	}

	public static Point absDistance(Point one, Point two)
	{
		return new Point(Math.abs(one.getX() - two.getX()), Math.abs(one.getY() - one.getY()));
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
