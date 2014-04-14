package basicObjects;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import junit.framework.Assert;
import org.junit.Test;

public class DimensionsTest
{
	@Test
	public void testCopy()
	{
		Dimensions size = new Dimensions(10, 30);
		Dimensions copy = size.copy();
		size.setDimensions(90, 70);
		Assert.assertTrue(copy.getWidth() == 10);
		Assert.assertTrue(copy.getHeight() == 30);
	}

	@Test
	public void testEquals()
	{
		Dimensions size1 = new Dimensions(10, 30);
		Dimensions size2 = new Dimensions(11, 30);
		Dimensions size3 = new Dimensions(10, 31);
		Dimensions size4 = new Dimensions(10, 30);
		Assert.assertFalse(size1.equals(size2));
		Assert.assertFalse(size1.equals(size3));
		Assert.assertTrue(size1.equals(size4));
		Assert.assertFalse(size1.equals(new Object()));
	}

	@Test
	public void testIncrement()
	{
		Dimensions size = new Dimensions(10, 30);
		size.incrementHeight(30);
		size.incrementWidth(10);
		Assert.assertTrue(size.getWidth() == 20); // 10 + 10
		Assert.assertTrue(size.getHeight() == 60); // 30 + 30
	}

	@Test
	public void testCopyAndIncrement()
	{
		Dimensions size = new Dimensions(10, 30);
		Dimensions copy = size.copy();
		size.incrementHeight(copy.getHeight());
		size.incrementWidth(copy.getWidth());
		Assert.assertTrue(copy.getWidth() == 10);
		Assert.assertTrue(copy.getHeight() == 30);
		Assert.assertTrue(size.getWidth() == 20); // 10 + 10
		Assert.assertTrue(size.getHeight() == 60); // 30 + 30
	}
}
