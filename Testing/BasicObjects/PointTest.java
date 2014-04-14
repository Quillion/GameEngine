package basicObjects;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import junit.framework.Assert;
import logic.MathEngine;
import org.junit.Test;

public class PointTest
{
	@Test
	public void testChangeLocation()
	{
		Point point1 = new Point(3, 5);
		point1.changeLocation(4.5, 6.5);
		Assert.assertTrue(MathEngine.equals(point1.getX(), 4.5));
		Assert.assertTrue(MathEngine.equals(point1.getY(), 6.5));
		Point point2 = new Point(2.345, 1.234);
		point1.changeLocation(point2);
		Assert.assertTrue(MathEngine.equals(point1.getX(), 2.345));
		Assert.assertTrue(MathEngine.equals(point1.getY(), 1.234));
	}

	@Test
	public void testCopy()
	{
		Point point1 = new Point(3, 5);
		Point point2 = point1.copy();
		point1.reverse();
		Assert.assertTrue(MathEngine.equals(point2.getX(), 3d));
		Assert.assertTrue(MathEngine.equals(point2.getY(), 5d));
	}

	@Test
	public void testEquals()
	{
		Point point1 = new Point(1, 2);
		Point point2 = new Point(1.1, 2);
		Point point3 = new Point(1, 2.1);
		Point point4 = new Point(1, 2);
		Assert.assertFalse(point1.equals(point2));
		Assert.assertFalse(point1.equals(point3));
		Assert.assertTrue(point1.equals(point4));
		Assert.assertFalse(point1.equals(new Object()));
	}

	@Test
	public void testIncrement()
	{
		Point point = new Point(1, 3);
		point.incrementX(2.357);
		point.incrementY(4.753);
		Assert.assertTrue(MathEngine.equals(point.getX(), 3.357));
		Assert.assertTrue(MathEngine.equals(point.getY(), 7.753));
	}

	@Test
	public void testMove()
	{
		Point point = new Point(0, 0);
		Point vector = new Point(3.5, 5.5);
		point.move(vector);
		Assert.assertTrue(MathEngine.equals(point.getX(), 3.5));
		Assert.assertTrue(MathEngine.equals(point.getY(), 5.5));
		point.move(3.7, 5.9);
		Assert.assertTrue(MathEngine.equals(point.getX(), 7.2));
		Assert.assertTrue(MathEngine.equals(point.getY(), 11.4));
	}

	@Test
	public void testReverse()
	{
		Point point = new Point(-3, -5);
		point.reverse();
		Assert.assertTrue(MathEngine.equals(point.getX(), 3));
		Assert.assertTrue(MathEngine.equals(point.getY(), 5));
		point.reverseX();
		point.reverseY();
		Assert.assertTrue(MathEngine.equals(point.getX(), -3));
		Assert.assertTrue(MathEngine.equals(point.getY(), -5));
	}
}
