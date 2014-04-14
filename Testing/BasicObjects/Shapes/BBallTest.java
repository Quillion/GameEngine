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

public class BBallTest
{
	@Test
	public void testCopy()
	{
		BBall ball = new BBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));
		ball.setOffset(3);
		BBall copy = ball.copy();
		ball.setCoordinates(new Point(55, 75));
		ball.setSize(new Dimensions(15, 15));
		ball.setOffset(1);
		Assert.assertTrue(copy.getX() != ball.getX());
		Assert.assertTrue(copy.getY() != ball.getY());
		Assert.assertTrue(copy.getRadius() != ball.getRadius());
		Assert.assertTrue(copy.getOffset() != ball.getOffset());
	}

	@Test
	public void testEquals()
	{
		BBall ball1 = new BBall();
		ball1.setCoordinates(new Point(50, 70));
		ball1.setSize(new Dimensions(10, 10));
		ball1.setOffset(3);
		BBall ball2 = new BBall();
		ball2.setCoordinates(new Point(51, 70));
		ball2.setSize(new Dimensions(10, 10));
		ball2.setOffset(3);
		BBall ball3 = new BBall();
		ball3.setCoordinates(new Point(50, 70));
		ball3.setSize(new Dimensions(10, 10));
		ball3.setOffset(5);
		BBall ball4 = new BBall();
		ball4.setCoordinates(new Point(50, 70));
		ball4.setSize(new Dimensions(10, 10));
		ball4.setOffset(3);
		Ball ball5 = ball1.copy();
		Assert.assertFalse(ball1.equals(ball2));
		Assert.assertFalse(ball1.equals(ball3));
		Assert.assertTrue(ball1.equals(ball4));
		Assert.assertTrue(ball1.equals(ball5));
		Assert.assertFalse(ball1.equals(new Object()));
	}

	@Test
	public void testPoints()
	{
		BBall ball = new BBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setRadius(15);
		ball.setOffset(5);

		Point point = ball.getTopLeft();
		Assert.assertTrue(MathEngine.equals(point.getX(), 40d)); // 50 - 15 + 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 60d)); // 70 - 15 + 5

		point = ball.getTopRight();
		Assert.assertTrue(MathEngine.equals(point.getX(), 60d)); // 50 - 15 - 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 60d)); // 70 - 15 + 5

		point = ball.getBottomLeft();
		Assert.assertTrue(MathEngine.equals(point.getX(), 40d)); // 50 - 15 + 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 80d)); // 70 + 15 - 5

		point = ball.getBottomRight();
		Assert.assertTrue(MathEngine.equals(point.getX(), 60d)); // 50 + 15 - 5
		Assert.assertTrue(MathEngine.equals(point.getY(), 80d)); // 70 + 15 - 5
	}

	@Test
	public void testOffset()
	{
		BBall ball = new BBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));
		ball.setOffset(3);
		Assert.assertTrue(ball.getOffset() == 3);
		ball.incrementOffset(2);
		Assert.assertTrue(ball.getOffset() == 5);
	}

	@Test
	public void testCopyAndMove()
	{
		BBall ball = new BBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setRadius(15);
		ball.setOffset(5);
		BBall copy = ball.copy();
		Ball parent = ball.getBall();

		ball.move(copy.getCenter());
		ball.incrementWidth(copy.getHeight());
		ball.incrementHeight(copy.getHeight());
		ball.setOffset(3);
		parent.move(copy.getCenter());

		Assert.assertTrue(copy.getX() == 50);
		Assert.assertTrue(copy.getY() == 70);
		Assert.assertTrue(copy.getWidth() == 30);
		Assert.assertTrue(copy.getHeight() == 30);
		Assert.assertTrue(copy.getOffset() == 5);
		Assert.assertTrue(ball.getX() == 100); // 50 + 50
		Assert.assertTrue(ball.getY() == 140); // 70 + 70
		Assert.assertTrue(ball.getRadius() == 45); // 15 + 15 + 15
		Assert.assertTrue(ball.getOffset() == 3);
		Assert.assertTrue(parent.getX() == 100); // 50 + 50
		Assert.assertTrue(parent.getY() == 140); // 70 + 70
	}
}
