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

public class MBoxTest
{
	@Test
	public void testCopy()
	{
		MBox box = new MBox();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(15, 30));
		box.setOffsets(new Dimensions(5, 10));
		box.setVector(1.5, 1.7);
		MBox copy = box.copy();

		box.move(copy.getCenter());
		box.incrementWidth(copy.getHeight());
		box.incrementHeight(copy.getHeight());
		box.setOffsets(new Dimensions(3, 3));
		box.setVector(1.7, 1.9);

		Assert.assertTrue(copy.getX() != box.getX());
		Assert.assertTrue(copy.getY() != box.getY());
		Assert.assertTrue(copy.getWidth() != box.getWidth());
		Assert.assertTrue(copy.getHeight() != box.getHeight());
		Assert.assertTrue(copy.getVerticalOffset() != box.getVerticalOffset());
		Assert.assertTrue(
				copy.getHorizontalOffset() != box.getHorizontalOffset());
		Assert.assertTrue(copy.getXVector() != box.getXVector());
		Assert.assertTrue(copy.getYVector() != box.getYVector());
	}

	@Test
	public void testEquals()
	{
		MBox box1 = new MBox();
		box1.setCoordinates(new Point(50, 70));
		box1.setSize(new Dimensions(10, 17));
		box1.setOffsets(3, 5);
		box1.setVector(1.5, 1.7);
		MBox box2 = new MBox();
		box2.setCoordinates(new Point(50, 71));
		box2.setSize(new Dimensions(10, 17));
		box2.setOffsets(3, 5);
		box2.setVector(1.5, 1.9);
		MBox box3 = new MBox();
		box3.setCoordinates(new Point(50, 70));
		box3.setSize(new Dimensions(10, 17));
		box3.setOffsets(3, 5);
		box3.setVector(1.5, 1.7);
		BBox box4 = box1.copy();
		Box box5 = box1.copy();
		Assert.assertFalse(box1.equals(box2));
		Assert.assertTrue(box1.equals(box3));
		Assert.assertTrue(box1.equals(box4));
		Assert.assertTrue(box1.equals(box5));
		Assert.assertFalse(box1.equals(new Object()));
	}

	@Test
	public void testVectors()
	{
		MBox box = new MBox();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(15, 30));
		box.setOffsets(new Dimensions(5, 10));
		box.setVector(new Point(3, 7));

		box.move();
		Assert.assertTrue(box.getX() == 53); // 50 + 3
		Assert.assertTrue(box.getY() == 77); // 70 + 7

		box.reverseXVector();
		box.move();
		Assert.assertTrue(box.getX() == 50); // 53 - 3
		Assert.assertTrue(box.getY() == 84); // 77 + 7

		box.reverseYVector();
		box.move();
		Assert.assertTrue(box.getX() == 47); // 50 - 3
		Assert.assertTrue(box.getY() == 77); // 84 - 7

		box.reverseVector();
		box.move();
		Assert.assertTrue(box.getX() == 50); // 47 + 3
		Assert.assertTrue(box.getY() == 84); // 77 + 7

		box.setVector(new Point(5, -7));
		box.move();
		Assert.assertTrue(box.getX() == 55); // 50 + 5
		Assert.assertTrue(box.getY() == 77); // 84 - 7

		box.incrementXVector(-10);
		box.incrementYVector(14);
		box.move();
		Assert.assertTrue(box.getX() == 50); // 55 + 5 - 10
		Assert.assertTrue(box.getY() == 84); // 77 - 7 + 14
	}

	@Test
	public void testCopyAndMove()
	{
		MBox box = new MBox();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(15, 30));
		box.setOffsets(new Dimensions(5, 10));
		box.setVector(new Point(3, 7));
		MBox copy = box.copy();
		BBox parent = box.getBBox();

		box.incrementXVector(-10);
		box.incrementYVector(14);
		box.move();
		copy.move();
		copy.reverseVector();
		parent.move(copy.getVector());
		Assert.assertTrue(box.getX() == 43); // 50 + 3 - 10
		Assert.assertTrue(box.getY() == 91); // 70 + 7 + 14
		Assert.assertTrue(copy.getX() == 53); // 50 + 3
		Assert.assertTrue(copy.getY() == 77); // 70 + 7
		Assert.assertTrue(parent.getX() == 47); // 50 - 3
		Assert.assertTrue(parent.getY() == 63); // 70 - 7
	}
}
