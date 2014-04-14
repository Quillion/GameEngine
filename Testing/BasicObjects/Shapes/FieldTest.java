package basicObjects.shapes;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import constants.Constants;
import junit.framework.Assert;
import org.junit.Test;

public class FieldTest
{
	@Test
	public void testCopy()
	{
		Field field = new Field(11);
		Field random = new Field(11);
		Field neighbor = new Field(15);
		field.setType(Constants.FieldShape.FOUR_SIDED);

		field.addField(random);
		field.addField(neighbor);

		Field copy = field.copy();

		field.setType(Constants.FieldShape.SIX_SIDED);
		random = new Field(13);
		neighbor = new Field(17);
		field.addField(random);
		field.addField(neighbor);

		Assert.assertTrue(field.getId() == copy.getId());
		Assert.assertTrue(field.getType() != copy.getType());
		Assert.assertTrue(
				field.getNumOfNeighbors() != copy.getNumOfNeighbors());
	}

	@Test
	public void testEquals()
	{
		Field field = new Field(11);
		Field twin = new Field(11);
		Field neighbor1 = new Field(17);
		Field neighbor2 = new Field(15);
		field.setType(Constants.FieldShape.FOUR_SIDED);
		twin.setType(Constants.FieldShape.FOUR_SIDED);

		Assert.assertTrue(field.addField(neighbor1));
		Assert.assertTrue(field.addField(neighbor2));
		Assert.assertTrue(twin.addField(neighbor1));
		Assert.assertTrue(twin.addField(neighbor2));

		Assert.assertTrue(field.equals(twin));
		Assert.assertFalse(field.equals(neighbor1));
		Assert.assertFalse(field.equals(neighbor2));
		Assert.assertFalse(neighbor1.equals(neighbor2));
		neighbor1.addField(neighbor2);
		Assert.assertFalse(neighbor1.equals(neighbor2));
		neighbor2 = neighbor1.copy(17);
		Assert.assertTrue(neighbor1.equals(neighbor2));
	}

	@Test
	public void testType()
	{
		Field field = new Field(11);
		Assert.assertTrue(field.getType() == Constants.FieldShape.NONE);

		field.setType(Constants.FieldShape.FOUR_SIDED);
		Assert.assertTrue(field.getType() == Constants.FieldShape.FOUR_SIDED);
	}

	@Test
	public void testNeighbors()
	{
		Field field = new Field(11);
		Field random = new Field(11);
		Field neighbor = new Field(15);

		field.addField(random);
		field.addField(neighbor);
		Assert.assertTrue(field.getNumOfNeighbors() == 1);
		Assert.assertTrue(random.getNeighbors().size() == 0);

		random = new Field(15);
		field.addField(random);
		Assert.assertTrue(field.getNumOfNeighbors() == 1);
		Assert.assertTrue(field.getFieldById(11) == null);

		random = new Field(17);
		field.addField(random);
		Assert.assertTrue(field.getFieldByIndex(3) == null);
		Assert.assertTrue(field.getFieldByIndex(1).getId() == 17);

		Assert.assertFalse(neighbor.removeFieldById(9));
		Assert.assertTrue(field.getNumOfNeighbors() == 2);
		Assert.assertTrue(neighbor.removeFieldById(11));
		Assert.assertTrue(field.getNumOfNeighbors() == 1);
	}
}
