package BasicShapes;

import Constants.QConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

public class Field
{
	private int id_;
	private List<Field> neighbors_;
	private int type_;

	public Field(int id)
	{
		this.id_ = id;
		this.neighbors_ = new ArrayList<Field>();
		this.type_ = QConstants.NONE;
	}

	public void setType(int type)
	{
		this.type_ = type;
	}

	public int getType()
	{
		return this.type_;
	}

	public int getId()
	{
		return this.id_;
	}

	/**
	 * Creates connection between supplied field and this field.
	 * Will not work if you try to add field that is already connected to it.
	 * Also will not work if you try to add this field to itself.
	 * @param field, field object to which to connect this object.
	 * @return true if the fields connected, false otherwise.
	 */
	public boolean addField(Field field)
	{
		// CHECK TO SEE IF THE FIELD IS NOT ALREADY CONNECTED
		for(Field neighbor : this.neighbors_)
			if (field.getId() == neighbor.getId())
				return false;
		// CHECK TO SEE IF THE FIELD IS NOT THE SAME FIELD
		if(this.getId() == field.getId())
			return false;
		// FIELDS WERE NOT THE SAME, SO ADD THEM
		this.neighbors_.add(field);
		return true;
	}

	/**
	 * Tell you how many neighbors this field has.
	 * @return how many fields are attached to this one.
	 */
	public int getNumOfNeighbors()
	{
		return this.neighbors_.size();
	}

	/**
	 * Returns the field at a given index.
	 * If index is less than 0 or bigger than the given size you get null.
	 * So don't be stupid!
	 * @param index the index of the neighbor you want to get.
	 * @return null if you are stupid, the field otherwise.
	 */
	public Field getFieldByIndex(int index)
	{
		if(index < this.getNumOfNeighbors() || index >= 0)
			return this.neighbors_.get(index);
		else
			return null;
	}

	/**
	 * Returns the field by a given id.
	 * If there is no field attached by the given id, then null is returned.
	 * @param id the neighbor's id of the field you want.
	 * @return null if no neighbor with given id is found. Field with id otherwise.
	 */
	public Field getFieldById(int id)
	{
		for(Field neighbor : this.neighbors_)
			if(neighbor.getId() == id)
				return neighbor;
			return null;
	}

	/**
	 * Removes field by the given index.
	 * If index is invalid then nothing will happen.
	 * @param index true if the field was removed, false otherwise.
	 */
	public boolean removeFieldByIndex(int index)
	{
		if(index >= this.getNumOfNeighbors() || index < 0)
			return false;
		this.neighbors_.remove(index);
		return true;
	}

	public void removeFieldById(int index)
	{
		this.neighbors_.remove(index);
	}

	public List<Field> getNeighbors()
	{
		return this.neighbors_;
	}
}
