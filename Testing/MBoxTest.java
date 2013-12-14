/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.BBox;
import BasicObjects.Dimensions;
import BasicObjects.MBox;
import BasicObjects.Point;
import junit.framework.Assert;
import org.junit.Test;

public class MBoxTest
{
	private MBox box;

	@Test
	public void testVectors()
	{
		box = new MBox();
		box.setCoordinates(new Point(50, 70));
		box.setSize(new Dimensions(15, 30));
		box.setOffsets(new Dimensions(5, 10));
		box.setVector(new Point(3, 7));

		box.move();
		Assert.assertEquals(53, box.getX()); // 50 + 3
		Assert.assertEquals(77, box.getY()); // 70 + 7

		box.reverseXVector();
		box.move();
		Assert.assertEquals(50, box.getX()); // 53 - 3
		Assert.assertEquals(84, box.getY()); // 77 + 7

		box.reverseYVector();
		box.move();
		Assert.assertEquals(47, box.getX()); // 50 - 3
		Assert.assertEquals(77, box.getY()); // 84 - 7

		box.reverseVector();
		box.move();
		Assert.assertEquals(50, box.getX()); // 47 + 3
		Assert.assertEquals(84, box.getY()); // 77 + 7

		box.setVector(new Point(5, -7));
		box.move();
		Assert.assertEquals(55, box.getX()); // 50 + 5
		Assert.assertEquals(77, box.getY()); // 84 - 7

		box.incrementXVector(-10);
		box.incrementYVector(14);
		box.move();
		Assert.assertEquals(50, box.getX()); // 55 + 5 - 10
		Assert.assertEquals(84, box.getY()); // 77 - 7 + 14
	}

	@Test
	public void testCopy()
	{
		box = new MBox();
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
		Assert.assertEquals(43, box.getX()); // 50 + 3 - 10
		Assert.assertEquals(91, box.getY()); // 70 + 7 + 14
		Assert.assertEquals(53, copy.getX()); // 50 + 3
		Assert.assertEquals(77, copy.getY()); // 70 + 7
		Assert.assertEquals(47, parent.getX()); // 50 - 3
		Assert.assertEquals(63, parent.getY()); // 70 - 7
	}
}
