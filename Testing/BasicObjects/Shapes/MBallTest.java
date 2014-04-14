package basicObjects.shapes;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Dimensions;
import basicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class MBallTest
{
	@Test
	public void testCopy()
	{
		MBall ball = new MBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));
		ball.setOffset(3);
		ball.setVector(1.5, 1.7);
		MBall copy = ball.copy();
		ball.setCoordinates(new Point(55, 75));
		ball.setSize(new Dimensions(15, 15));
		ball.setOffset(1);
		ball.setVector(1.7, 1.9);
		Assert.assertTrue(copy.getX() != ball.getX());
		Assert.assertTrue(copy.getY() != ball.getY());
		Assert.assertTrue(copy.getRadius() != ball.getRadius());
		Assert.assertTrue(copy.getOffset() != ball.getOffset());
		Assert.assertTrue(copy.getXVector() != ball.getXVector());
		Assert.assertTrue(copy.getYVector() != ball.getYVector());
	}

	@Test
	public void testEquals()
	{
		MBall ball1 = new MBall();
		ball1.setCoordinates(new Point(50, 70));
		ball1.setSize(new Dimensions(10, 10));
		ball1.setOffset(3);
		ball1.setVector(1.7, 1.9);
		MBall ball2 = new MBall();
		ball2.setCoordinates(new Point(51, 70));
		ball2.setSize(new Dimensions(10, 10));
		ball2.setOffset(3);
		ball2.setVector(1.7, 1.9);
		MBall ball3 = new MBall();
		ball3.setCoordinates(new Point(50, 70));
		ball3.setSize(new Dimensions(10, 10));
		ball3.setOffset(3);
		ball3.setVector(1.7, 1.9);
		BBall ball4 = ball1.copy();
		Ball ball5 = ball1.copy();
		Assert.assertFalse(ball1.equals(ball2));
		Assert.assertTrue(ball1.equals(ball3));
		Assert.assertTrue(ball1.equals(ball4));
		Assert.assertTrue(ball1.equals(ball5));
		Assert.assertFalse(ball1.equals(new Object()));
	}

	@Test
	public void testVectors()
	{
		MBall ball = new MBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(15, 30));
		ball.setOffset(5);
		ball.setVector(new Point(3, 7));

		ball.move();
		Assert.assertTrue(ball.getX() == 53); // 50 + 3
		Assert.assertTrue(ball.getY() == 77); // 70 + 7

		ball.reverseXVector();
		ball.move();
		Assert.assertTrue(ball.getX() == 50); // 53 - 3
		Assert.assertTrue(ball.getY() == 84); // 77 + 7

		ball.reverseYVector();
		ball.move();
		Assert.assertTrue(ball.getX() == 47); // 50 - 3
		Assert.assertTrue(ball.getY() == 77); // 84 - 7

		ball.reverseVector();
		ball.move();
		Assert.assertTrue(ball.getX() == 50); // 47 + 3
		Assert.assertTrue(ball.getY() == 84); // 77 + 7

		ball.setVector(new Point(5, -7));
		ball.move();
		Assert.assertTrue(ball.getX() == 55); // 50 + 5
		Assert.assertTrue(ball.getY() == 77); // 84 - 7

		ball.incrementXVector(-10);
		ball.incrementYVector(14);
		ball.move();
		Assert.assertTrue(ball.getX() == 50); // 55 + 5 - 10
		Assert.assertTrue(ball.getY() == 84); // 77 - 7 + 14
	}

	@Test
	public void testCopyAndMove()
	{
		MBall ball = new MBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(15, 30));
		ball.setOffset(5);
		ball.setVector(new Point(3, 7));
		MBall copy = ball.copy();
		BBall parent = ball.getBBall();

		ball.incrementXVector(-10);
		ball.incrementYVector(14);
		copy.setXVector(5);
		copy.setYVector(3);
		ball.move();
		copy.move();
		copy.reverseVector();
		parent.move(copy.getVector());
		Assert.assertTrue(ball.getX() == 43); // 50 + 3 - 10
		Assert.assertTrue(ball.getY() == 91); // 70 + 7 + 14
		Assert.assertTrue(copy.getX() == 55); // 50 + 5
		Assert.assertTrue(copy.getY() == 73); // 70 + 3
		Assert.assertTrue(parent.getX() == 45); // 50 - 5
		Assert.assertTrue(parent.getY() == 67); // 70 - 3
	}
}