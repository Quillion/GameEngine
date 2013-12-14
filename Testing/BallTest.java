/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Ball;
import BasicObjects.Dimensions;
import BasicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class BallTest
{
	private Ball ball;

	@Test
	public void testPoints()
	{
		ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));

		Point point = ball.getTopLeft();
		Assert.assertEquals(40d, point.getX()); // 50 - 10
		Assert.assertEquals(60d, point.getY()); // 70 - 10

		point = ball.getTopRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 10
		Assert.assertEquals(60d, point.getY()); // 70 - 10

		point = ball.getBottomLeft();
		Assert.assertEquals(40d, point.getX()); // 50 - 10
		Assert.assertEquals(80d, point.getY()); // 70 + 10

		point = ball.getBottomRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 10
		Assert.assertEquals(80d, point.getY()); // 70 + 10

		point = ball.getCenter();
		Assert.assertEquals(50d, point.getX());
		Assert.assertEquals(70d, point.getY());
	}

	@Test
	public void testCopy()
	{
		ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 30));
		Ball copy = ball.copy();

		ball.move(copy.getCenter());
		ball.incrementWidth(copy.getHeight());
		Assert.assertEquals(20, ball.getRadius()); // 10 + 10
		ball.incrementHeight(copy.getHeight());
		Assert.assertEquals(30, ball.getRadius()); // 20 + 10
		ball.incrementRadius(ball.getRadius());

		Assert.assertEquals(50, copy.getX());
		Assert.assertEquals(70, copy.getY());
		Assert.assertEquals(10, copy.getRadius());
		Assert.assertEquals(100, ball.getX()); // 50 + 50
		Assert.assertEquals(140, ball.getY()); // 70 + 70
		Assert.assertEquals(60, ball.getRadius()); // 30 + 30
	}
}
