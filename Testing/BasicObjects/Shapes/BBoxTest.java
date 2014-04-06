package BasicObjects.Shapes; /**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import BasicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class BBoxTest
{
	@Test
	public void testBoundingBox()
	{
		BBox box = new BBox();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(15, 30));
		box.setOffsets(new Dimensions(5, 10));

		Point point = box.getTopLeft();
		Assert.assertEquals(55d, point.getX()); // 50 + 5
		Assert.assertEquals(80d, point.getY()); // 70 + 10

		point = box.getTopRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 15 - 5
		Assert.assertEquals(80d, point.getY()); // 70 + 10

		point = box.getBottomLeft();
		Assert.assertEquals(55d, point.getX()); // 50 + 5
		Assert.assertEquals(90d, point.getY()); // 70 + 30 - 10

		point = box.getBottomRight();
		Assert.assertEquals(60d, point.getX()); // 50 + 15 - 5
		Assert.assertEquals(90d, point.getY()); // 70 + 30 - 10
	}

	@Test
	public void testCopy()
	{
		BBox box = new BBox();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(15, 30));
		box.setOffsets(new Dimensions(5, 10));
		BBox copy = box.copy();

		box.move(copy.getCenter());
		box.incrementWidth(copy.getHeight());
		box.incrementHeight(copy.getHeight());
		box.setOffsets(new Dimensions(3, 3));

		Assert.assertEquals(50, copy.getX());
		Assert.assertEquals(70, copy.getY());
		Assert.assertEquals(15, copy.getWidth());
		Assert.assertEquals(30, copy.getHeight());
		Assert.assertEquals(5, copy.getHorizontalOffset());
		Assert.assertEquals(10, copy.getVerticalOffset());
		Assert.assertEquals(107, box.getX()); // 50 + 50 + 7
		Assert.assertEquals(155, box.getY()); // 70 + 70 + 15
		Assert.assertEquals(45, box.getWidth()); // 15 + 30
		Assert.assertEquals(60, box.getHeight()); // 30 + 30
		Assert.assertEquals(3, box.getVerticalOffset());
		Assert.assertEquals(3, box.getHorizontalOffset());
	}
}
