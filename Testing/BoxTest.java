/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.shapes.Box;
import BasicObjects.Dimensions;
import BasicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class BoxTest
{
	private Box box;

	@Test
	public void testPoints()
	{
		box = new Box();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(10, 30));

		Point point = box.getTopLeft();
		Assert.assertEquals(50d, point.getX());
		Assert.assertEquals(70d, point.getY());

		point = box.getTopRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 10
		Assert.assertEquals(70d, point.getY());

		point = box.getBottomLeft();
		Assert.assertEquals(50d, point.getX());
		Assert.assertEquals(100d, point.getY()); // 70 + 30

		point = box.getBottomRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 10
		Assert.assertEquals(100d, point.getY()); // 70 + 30

		point = box.getCenter();
		Assert.assertEquals(55d, point.getX());
		Assert.assertEquals(85d, point.getY());
	}

	@Test
	public void testCopy()
	{
		box = new Box();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(10, 30));
		Box copy = box.copy();

		box.move(copy.getCenter());
		box.incrementWidth(copy.getHeight());
		box.incrementHeight(copy.getHeight());

		Assert.assertEquals(50, copy.getX());
		Assert.assertEquals(70, copy.getY());
		Assert.assertEquals(10, copy.getWidth());
		Assert.assertEquals(30, copy.getHeight());
		Assert.assertEquals(105, box.getX()); // 50 + 50 + 10/2
		Assert.assertEquals(155, box.getY()); // 70 + 70 + 30/2
		Assert.assertEquals(40, box.getWidth());// 10 + 30
		Assert.assertEquals(60, box.getHeight());// 30 + 30
	}
}
