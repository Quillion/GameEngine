package basicObjects.shapes;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Dimensions;
import basicObjects.Point;
import junit.framework.Assert;
import logic.MathEngine;
import org.junit.Test;

public class BallTest
{
	@Test
	public void testCopy()
	{
		Ball ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));
		Ball copy = ball.copy();
		ball.setCoordinates(new Point(55, 75));
		ball.setSize(new Dimensions(15, 15));
		Assert.assertTrue(copy.getX() != ball.getX());
		Assert.assertTrue(copy.getY() != ball.getY());
		Assert.assertTrue(copy.getRadius() != ball.getRadius());
	}

	@Test
	public void testEquals()
	{
		Ball ball1 = new Ball();
		ball1.setCoordinates(new Point(50, 70));
		ball1.setSize(new Dimensions(10, 10));
		Ball ball2 = new Ball();
		ball2.setCoordinates(new Point(51, 70));
		ball2.setSize(new Dimensions(10, 10));
		Ball ball3 = new Ball();
		ball3.setCoordinates(new Point(50, 71));
		ball3.setSize(new Dimensions(11, 10));
		Ball ball4 = new Ball();
		ball4.setCoordinates(new Point(50, 70));
		ball4.setSize(new Dimensions(10, 11));
		Ball ball5 = new Ball();
		ball5.setCoordinates(new Point(50, 70));
		ball5.setSize(new Dimensions(10, 10));
		Assert.assertFalse(ball1.equals(ball2));
		Assert.assertFalse(ball1.equals(ball3));
		Assert.assertFalse(ball1.equals(ball4));
		Assert.assertTrue(ball1.equals(ball5));
		Assert.assertFalse(ball1.equals(new Object()));
	}

	@Test
	public void testPoints()
	{
		Ball ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));

		Point point = ball.getTopLeft();
		Assert.assertTrue(MathEngine.equals(point.getX(), 45d)); // 50 - 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 65d)); // 70 - 5

		point = ball.getTopRight();
		Assert.assertTrue(MathEngine.equals(point.getX(), 55d)); // 50 + 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 65d)); // 70 - 5

		point = ball.getBottomLeft();
		Assert.assertTrue(MathEngine.equals(point.getX(), 45d)); // 50 - 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 75d)); // 70 + 5

		point = ball.getBottomRight();
		Assert.assertTrue(MathEngine.equals(point.getX(), 55d)); // 50 + 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 75d)); // 70 + 5

		point = ball.getCenter();
		Assert.assertTrue(MathEngine.equals(point.getX(), 50d));
		Assert.assertTrue(MathEngine.equals(point.getY(), 70d));
	}

	@Test
	public void testIncrementSize()
	{
		Ball ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));
		Assert.assertTrue(ball.getHeight() == 10);
		ball.incrementHeight(8);
		Assert.assertTrue(ball.getWidth() == 18);
		ball.incrementWidth(12);
		Assert.assertTrue(ball.getRadius() == 15);
		ball.incrementRadius(14);
		Assert.assertTrue(ball.getRadius() == 29);
		ball.incrementRadius(3);
		Assert.assertTrue(ball.getDiameter() == 64);
	}

	@Test
	public void testMove()
	{
		Ball ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));
		Point vector = new Point(15, 5);
		ball.move(vector);
		Assert.assertTrue(ball.getX() == 65);
		Assert.assertTrue(ball.getY() == 75);
		ball.move(10, 8);
		Assert.assertTrue(ball.getX() == 75);
		Assert.assertTrue(ball.getY() == 83);
	}

	@Test
	public void testCopyAndMove()
	{
		Ball ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));
		Ball copy = ball.copy();

		ball.move(copy.getCenter());
		ball.incrementWidth(copy.getHeight());
		Assert.assertTrue(ball.getRadius() == 10); // 5 + 5
		ball.incrementHeight(copy.getHeight());
		Assert.assertTrue(ball.getRadius() == 15); // 10 + 5
		ball.incrementRadius(ball.getRadius());

		Assert.assertTrue(copy.getX() == 50);
		Assert.assertTrue(copy.getY() == 70);
		Assert.assertTrue(copy.getRadius() == 5);
		Assert.assertTrue(ball.getX() == 100); // 50 + 50
		Assert.assertTrue(ball.getY() == 140); // 70 + 70
		Assert.assertTrue(ball.getRadius() == 30); // 15 + 15
	}
}
