package basicObjects.shapes;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Dimensions;
import basicObjects.Point;
import junit.framework.Assert;
import logic.MathEngine;
import org.junit.Test;

public class BoxTest
{
	@Test
	public void testCopy()
	{
		Box box = new Box();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(10, 30));
		Box copy = box.copy();

		box.move(copy.getCenter());
		box.incrementWidth(copy.getHeight());
		box.incrementHeight(copy.getHeight());

		Assert.assertTrue(copy.getX() != box.getX());
		Assert.assertTrue(copy.getY() != box.getY());
		Assert.assertTrue(copy.getWidth() != box.getWidth());
		Assert.assertTrue(copy.getHeight() != box.getHeight());
	}

	@Test
	public void testEquals()
	{
		Box box1 = new Box();
		box1.setCoordinates(new Point(50, 70));
		box1.setSize(new Dimensions(10, 30));
		Box box2 = new Box();
		box2.setCoordinates(new Point(50, 71));
		box2.setSize(new Dimensions(10, 30));
		Box box3 = new Box();
		box3.setCoordinates(new Point(51, 70));
		box3.setSize(new Dimensions(10, 31));
		Box box4 = new Box();
		box4.setCoordinates(new Point(50, 70));
		box4.setSize(new Dimensions(11, 30));
		Box box5 = new Box();
		box5.setCoordinates(new Point(50, 70));
		box5.setSize(new Dimensions(10, 30));
		Assert.assertFalse(box1.equals(box2));
		Assert.assertFalse(box1.equals(box3));
		Assert.assertFalse(box1.equals(box4));
		Assert.assertTrue(box1.equals(box5));
		Assert.assertFalse(box1.equals(new Object()));
	}

	@Test
	public void testPoints()
	{
		Box box = new Box();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(10, 30));

		Point point = box.getTopLeft();
		Assert.assertTrue(MathEngine.equals(point.getX(), 50d));
		Assert.assertTrue(MathEngine.equals(point.getY(), 70d));

		point = box.getTopRight();
		Assert.assertTrue(MathEngine.equals(point.getX(), 60d)); // 50 + 10
		Assert.assertTrue(MathEngine.equals(point.getY(), 70d));

		point = box.getBottomLeft();
		Assert.assertTrue(MathEngine.equals(point.getX(), 50d));
		Assert.assertTrue(MathEngine.equals(point.getY(), 100d)); // 70 + 30

		point = box.getBottomRight();
		Assert.assertTrue(MathEngine.equals(point.getX(), 60d)); // 50 + 10
		Assert.assertTrue(MathEngine.equals(point.getY(), 100d)); // 70 + 30

		point = box.getCenter();
		Assert.assertTrue(MathEngine.equals(point.getX(), 55d));
		Assert.assertTrue(MathEngine.equals(point.getY(), 85d));
	}

	@Test
	public void testIncrementSize()
	{
		Box box = new Box();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(30, 20));
		Assert.assertTrue(box.getHeight() == 20);
		Assert.assertTrue(box.getWidth() == 30);
		box.incrementHeight(9);
		Assert.assertTrue(box.getHeight() == 29);
		box.incrementWidth(15);
		Assert.assertTrue(box.getWidth() == 45);
	}

	@Test
	public void testMove()
	{
		Box box = new Box();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(30, 20));
		Point vector = new Point(15, 5);
		box.move(vector);
		Assert.assertTrue(box.getX() == 65);
		Assert.assertTrue(box.getY() == 75);
		box.move(10, 8);
		Assert.assertTrue(box.getX() == 75);
		Assert.assertTrue(box.getY() == 83);
	}

	@Test
	public void testCopyAndMove()
	{
		Box box = new Box();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(30, 20));
		Box copy = box.copy();

		box.move(copy.getCenter());
		box.incrementWidth(copy.getHeight());
		Assert.assertTrue(box.getWidth() == 50); // 20 + 30
		box.incrementHeight(copy.getHeight());
		Assert.assertTrue(box.getHeight() == 40); // 20 + 20

		Assert.assertTrue(copy.getX() == 50);
		Assert.assertTrue(copy.getY() == 70);
		Assert.assertTrue(copy.getWidth() == 30);
		Assert.assertTrue(copy.getHeight() == 20);
		Assert.assertTrue(box.getX() == 115); // 50 + 50 + 15
		Assert.assertTrue(box.getY() == 150); // 70 + 70 + 10
	}
}
