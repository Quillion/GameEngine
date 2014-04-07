/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.shapes.Field;
import constants.Constants;
import junit.framework.Assert;
import org.junit.Test;

public class FieldTest
{
	private Field field;
	private Field random;
	private Field neighbor;

	@Test
	public void testType()
	{
		field = new Field(11);
		Assert.assertEquals(Constants.FieldShape.NONE, field.getType());

		field.setType(Constants.FieldShape.FOUR_SIDED);
		Assert.assertNotSame(Constants.FieldShape.NONE, field.getType());
	}

	@Test
	public void testNeighbors()
	{
		field = new Field(11);
		random = new Field(11);
		neighbor = new Field(15);

		field.addField(random);
		field.addField(neighbor);
		Assert.assertEquals(1, field.getNumOfNeighbors());
		Assert.assertEquals(0, random.getNeighbors().size());

		random = new Field(15);
		field.addField(random);
		Assert.assertEquals(1, field.getNumOfNeighbors());
		Assert.assertNull(field.getFieldById(11));

		random = new Field(17);
		field.addField(random);
		Assert.assertNull(field.getFieldByIndex(3));
		Assert.assertEquals(17, field.getFieldByIndex(1).getId());

		Assert.assertFalse(neighbor.removeFieldById(9));
		Assert.assertEquals(2, field.getNumOfNeighbors());
		Assert.assertTrue(neighbor.removeFieldById(11));
		Assert.assertEquals(1, field.getNumOfNeighbors());
	}
}
