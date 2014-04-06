package BasicObjects.Shapes; /**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import BasicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class BallTest
{
	@Test
	public void testPoints()
	{
		Ball ball = new Ball();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(10, 10));

		Point point = ball.getTopLeft();
		Assert.assertEquals(45d, point.getX()); // 50 - 5
		Assert.assertEquals(65d, point.getY()); // 70 - 5

		point = ball.getTopRight();
		Assert.assertEquals(55d, point.getX()); // 50 + 5
		Assert.assertEquals(65d, point.getY()); // 70 - 5

		point = ball.getBottomLeft();
		Assert.assertEquals(45d, point.getX()); // 50 - 5
		Assert.assertEquals(75d, point.getY()); // 70 + 5

		point = ball.getBottomRight();
		Assert.assertEquals(55d, point.getX()); // 50 + 5
		Assert.assertEquals(75d, point.getY()); // 70 + 5

		point = ball.getCenter();
		Assert.assertEquals(50d, point.getX());
		Assert.assertEquals(70d, point.getY());
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
		Assert.assertEquals(10, ball.getRadius()); // 5 + 5
		ball.incrementHeight(copy.getHeight());
		Assert.assertEquals(15, ball.getRadius()); // 10 + 5
		ball.incrementRadius(ball.getRadius());

		Assert.assertEquals(50, copy.getX());
		Assert.assertEquals(70, copy.getY());
		Assert.assertEquals(5, copy.getRadius());
		Assert.assertEquals(100, ball.getX()); // 50 + 50
		Assert.assertEquals(140, ball.getY()); // 70 + 70
		Assert.assertEquals(30, ball.getRadius()); // 15 + 15
	}
}
