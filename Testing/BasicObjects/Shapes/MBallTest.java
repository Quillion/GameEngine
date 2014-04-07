package basicObjects.shapes; /**
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
	private MBall ball;

	@Test
	public void testVectors()
	{
		ball = new MBall();
		ball.setCoordinates(new Point(50, 70));
		ball.setSize(new Dimensions(15, 30));
		ball.setOffset(5);
		ball.setVector(new Point(3, 7));

		ball.move();
		Assert.assertEquals(53, ball.getX()); // 50 + 3
		Assert.assertEquals(77, ball.getY()); // 70 + 7

		ball.reverseXVector();
		ball.move();
		Assert.assertEquals(50, ball.getX()); // 53 - 3
		Assert.assertEquals(84, ball.getY()); // 77 + 7

		ball.reverseYVector();
		ball.move();
		Assert.assertEquals(47, ball.getX()); // 50 - 3
		Assert.assertEquals(77, ball.getY()); // 84 - 7

		ball.reverseVector();
		ball.move();
		Assert.assertEquals(50, ball.getX()); // 47 + 3
		Assert.assertEquals(84, ball.getY()); // 77 + 7

		ball.setVector(new Point(5, -7));
		ball.move();
		Assert.assertEquals(55, ball.getX()); // 50 + 5
		Assert.assertEquals(77, ball.getY()); // 84 - 7

		ball.incrementXVector(-10);
		ball.incrementYVector(14);
		ball.move();
		Assert.assertEquals(50, ball.getX()); // 55 + 5 - 10
		Assert.assertEquals(84, ball.getY()); // 77 - 7 + 14
	}

	@Test
	public void testCopy()
	{
		ball = new MBall();
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
		Assert.assertEquals(43, ball.getX()); // 50 + 3 - 10
		Assert.assertEquals(91, ball.getY()); // 70 + 7 + 14
		Assert.assertEquals(55, copy.getX()); // 50 + 5
		Assert.assertEquals(73, copy.getY()); // 70 + 3
		Assert.assertEquals(45, parent.getX()); // 50 - 5
		Assert.assertEquals(67, parent.getY()); // 70 - 3
	}
}