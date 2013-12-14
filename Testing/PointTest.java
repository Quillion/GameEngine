/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class PointTest
{
	private Point point;
	private Point vector;

	@Test
	public void testMove()
	{
		point = new Point(0, 0);
		vector = new Point(3, 5);
		point.move(vector);
		Assert.assertEquals(3d, point.getX());
		Assert.assertEquals(5d, point.getY());
	}

	@Test
	public void testReverse()
	{
		point = new Point(-3, -5);
		point.reverse();
		Assert.assertEquals(3d, point.getX());
		Assert.assertEquals(5d, point.getY());
	}

	@Test
	public void testCopy()
	{
		point = new Point(3, 5);
		vector = point.copy();
		point.reverse();
		Assert.assertEquals(3d, vector.getX());
		Assert.assertEquals(5d, vector.getY());
	}
}
