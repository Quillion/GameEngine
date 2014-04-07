package basicObjects; /**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import junit.framework.Assert;
import org.junit.Test;

public class PointTest
{
	@Test
	public void testMove()
	{
		Point point = new Point(0, 0);
		Point vector = new Point(3, 5);
		point.move(vector);
		Assert.assertEquals(3d, point.getX());
		Assert.assertEquals(5d, point.getY());
	}

	@Test
	public void testReverse()
	{
		Point point = new Point(-3, -5);
		point.reverse();
		Assert.assertEquals(3d, point.getX());
		Assert.assertEquals(5d, point.getY());
	}

	@Test
	public void testCopy()
	{
		Point point = new Point(3, 5);
		Point vector = point.copy();
		point.reverse();
		Assert.assertEquals(3d, vector.getX());
		Assert.assertEquals(5d, vector.getY());
	}
}
