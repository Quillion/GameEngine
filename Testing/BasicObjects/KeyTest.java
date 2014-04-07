package basicObjects; /**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import junit.framework.Assert;
import org.junit.Test;

public class KeyTest
{
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
