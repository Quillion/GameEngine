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

public class BBoxTest
{
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

		Assert.assertTrue(copy.getX() != box.getX());
		Assert.assertTrue(copy.getY() != box.getY());
		Assert.assertTrue(copy.getWidth() != box.getWidth());
		Assert.assertTrue(copy.getHeight() != box.getHeight());
		Assert.assertTrue(copy.getVerticalOffset() != box.getVerticalOffset());
		Assert.assertTrue(
				copy.getHorizontalOffset() != box.getHorizontalOffset());
	}

	@Test
	public void testEquals()
	{
		BBox box1 = new BBox();
		box1.setCoordinates(new Point(50, 70));
		box1.setSize(new Dimensions(10, 17));
		box1.setOffsets(3, 5);
		BBox box2 = new BBox();
		box2.setCoordinates(new Point(50, 71));
		box2.setSize(new Dimensions(10, 17));
		box2.setOffsets(3, 5);
		BBox box3 = new BBox();
		box3.setCoordinates(new Point(50, 70));
		box3.setSize(new Dimensions(10, 17));
		box3.setOffsets(7, 5);
		BBox box4 = new BBox();
		box4.setCoordinates(new Point(50, 70));
		box4.setSize(new Dimensions(10, 17));
		box4.setOffsets(3, 5);
		Box box5 = box1.copy();
		Assert.assertFalse(box1.equals(box2));
		Assert.assertFalse(box1.equals(box3));
		Assert.assertTrue(box1.equals(box4));
		Assert.assertTrue(box1.equals(box5));
		Assert.assertFalse(box1.equals(new Object()));
	}

	@Test
	public void testPoints()
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
	public void testOffset()
	{
		BBox box = new BBox();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(10, 17));
		box.setOffsets(3, 5);
		Assert.assertTrue(box.getVerticalOffset() == 5);
		Assert.assertTrue(box.getHorizontalOffset() == 3);
		box.incrementVerticalOffset(2);
		box.incrementHorizontalOffset(5);
		Assert.assertTrue(box.getVerticalOffset() == 7);
		Assert.assertTrue(box.getHorizontalOffset() == 8);
	}

	@Test
	public void testCopyAndMove()
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

		Assert.assertTrue(copy.getX() == 50);
		Assert.assertTrue(copy.getY() == 70);
		Assert.assertTrue(copy.getWidth() == 15);
		Assert.assertTrue(copy.getHeight() == 30);
		Assert.assertTrue(copy.getHorizontalOffset() == 5);
		Assert.assertTrue(copy.getVerticalOffset() == 10);
		Assert.assertTrue(box.getX() == 107); // 50 + 50 + 7
		Assert.assertTrue(box.getY() == 155); // 70 + 70 + 15
		Assert.assertTrue(box.getWidth() == 45); // 15 + 30
		Assert.assertTrue(box.getHeight() == 60); // 30 + 30
		Assert.assertTrue(box.getVerticalOffset() == 3);
		Assert.assertTrue(box.getHorizontalOffset() == 3);
	}
}
