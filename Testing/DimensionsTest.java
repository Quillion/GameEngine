/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import junit.framework.Assert;
import org.junit.Test;

public class DimensionsTest
{
	@Test
	public void testCopyAndIncrement()
	{
		Dimensions size = new Dimensions(10, 30);
		Dimensions copy = size.copy();
		size.incrementHeight(copy.getHeight());
		size.incrementWidth(copy.getWidth());
		Assert.assertEquals(10, copy.getWidth());
		Assert.assertEquals(30, copy.getHeight());
		Assert.assertEquals(20, size.getWidth()); // 10 + 10
		Assert.assertEquals(60, size.getHeight()); // 30 + 30
	}
}
