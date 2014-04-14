package basicObjects;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import junit.framework.Assert;
import org.junit.Test;

public class KeyTest
{
	@Test
	public void testCopy()
	{
		Key key1 = new Key(33);
		Key key2 = key1.copy();
		key1.setValue(35);
		key1.press();
		Assert.assertTrue(key1.getValue() != key2.getValue());
		Assert.assertFalse(key2.isPressed());
		Assert.assertTrue(key1.isPressed());
	}

	@Test
	public void testEquals()
	{
		Key key1 = new Key(33);
		Key key2 = new Key(35);
		Key key3 = new Key(33);
		Assert.assertFalse(key1.equals(key2));
		Assert.assertTrue(key1.equals(key3));
		Assert.assertFalse(key1.equals(new Object()));
	}

	@Test
	public void testPressing()
	{
		Key key = new Key(33);
		Assert.assertTrue(!key.isPressed());
		key.press();
		Assert.assertTrue(key.isPressed());
		key.release();
		Assert.assertTrue(!key.isPressed());
	}
}
