package BasicObjects.Shapes; /**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Shapes.BBall;
import BasicObjects.Shapes.Ball;
import BasicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class BBallTest
{
	@Test
	public void testBoundingBox()
	{
		BBall ball = new BBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setRadius(15);
		ball.setOffset(5);

		Point point = ball.getTopLeft();
		Assert.assertEquals(40d, point.getX()); // 50 - 15 + 5
		Assert.assertEquals(60d, point.getY()); // 70 - 15 + 5

		point = ball.getTopRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 15 - 5
		Assert.assertEquals(60d, point.getY()); // 70 - 15 + 5

		point = ball.getBottomLeft();
		Assert.assertEquals(40d, point.getX()); // 50 - 15 + 5
		Assert.assertEquals(80d, point.getY()); // 70 + 15 - 5

		point = ball.getBottomRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 15 - 5
		Assert.assertEquals(80d, point.getY()); // 70 + 15 - 5
	}

	@Test
	public void testCopy()
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

		Assert.assertEquals(50, copy.getX());
		Assert.assertEquals(70, copy.getY());
		Assert.assertEquals(30, copy.getWidth());
		Assert.assertEquals(30, copy.getHeight());
		Assert.assertEquals(5, copy.getOffset());
		Assert.assertEquals(100, ball.getX()); // 50 + 50
		Assert.assertEquals(140, ball.getY()); // 70 + 70
		Assert.assertEquals(45, ball.getRadius()); // 15 + 15 + 15
		Assert.assertEquals(3, ball.getOffset());
		Assert.assertEquals(100, parent.getX()); // 50 + 50
		Assert.assertEquals(140, parent.getY()); // 70 + 70
	}
}
