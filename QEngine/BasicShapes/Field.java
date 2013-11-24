package BasicShapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import Constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * This object is going to be used for sim like games, and other board games.
 * It is extremely basic, but has enough things to be useful.
 */
public class Field
{
	private int id_;
	private List<Field> neighbors_;
	private int type_;

	// THIS IS GOING TO BE A SUPER USEFUL FIELD WHEN WE WILL HAVE AI
	// TRAVERSE THE FIELDS FOR OPTIMAL PATHS. I CAN THINK UP OF AT LEAST
	// THREE OTHER USES FOR THIS VARIABLE, SO LET's KEEP IT, TRUST ME.
	public int garbage;

	/**
	 * Basic constructor.
	 * Each field should have id that is unique to it.
	 * Please do not create fields with duplicate ids.
	 *
	 * @param id The unique id of this field.
	 */
	public Field(int id)
	{
		this.id_ = id;
		this.neighbors_ = new ArrayList<Field>();
		this.type_ = Constants.NONE;
	}

	/**
	 * Sets the type of the field to what you want.
	 *
	 * @param type What type of field it is.
	 */
	public void setType(int type)
	{
		this.type_ = type;
	}

	/**
	 * Returns what type of field this is.
	 * Value for the types of field are stored in Constants.
	 *
	 * @return The type of field this is.
	 */
	public int getType()
	{
		return this.type_;
	}

	/**
	 * Returns id of this field.
	 * It will almost always be unique, unless design is somewhat stupid.
	 *
	 * @return Id that represents this field.
	 */
	public int getId()
	{
		return this.id_;
	}

	/**
	 * Creates connection between supplied field and this field.
	 * Will not work if you try to add field that is already connected to it.
	 * Also will not work if you try to add this field to itself.
	 *
	 * @param field field object to which to connect this object.
	 * @return true if the fields connected, false otherwise.
	 */
	public boolean addField(Field field)
	{
		// CHECK TO SEE IF THE FIELD IS NOT ALREADY CONNECTED
		for (Field neighbor : this.neighbors_)
			if (field.getId() == neighbor.getId())
				return false;
		// CHECK TO SEE IF THE FIELD IS NOT THE SAME FIELD
		if (this.getId() == field.getId())
			return false;
		// FIELDS WERE NOT THE SAME, SO ADD THEM
		this.neighbors_.add(field);
		return true;
	}

	/**
	 * Tell you how many neighbors this field has.
	 *
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
	 *
	 * @param index the index of the neighbor you want to get.
	 * @return null if you are stupid, the field otherwise.
	 */
	public Field getFieldByIndex(int index)
	{
		if (index < this.getNumOfNeighbors() && index >= 0)
			return this.neighbors_.get(index);
		return null;
	}

	/**
	 * Returns the field by a given id.
	 * If there is no field attached by the given id, then null is returned.
	 *
	 * @param id the neighbor's id of the field you want.
	 * @return null if no neighbor with given id is found. Field with id otherwise.
	 */
	public Field getFieldById(int id)
	{
		for (Field neighbor : this.neighbors_)
			if (neighbor.getId() == id)
				return neighbor;
		return null;
	}

	/**
	 * Removes field by the given index.
	 * If index is invalid then nothing will happen.
	 *
	 * @param index true if the field was removed, false otherwise.
	 */
	public boolean removeFieldByIndex(int index)
	{
		if (index >= this.getNumOfNeighbors() || index < 0)
			return false;
		this.neighbors_.remove(index);
		return true;
	}

	/**
	 * Removes field by the given id.
	 * If index is invalid then nothing will happen.
	 * Yes this method is costly, live with it. Why the heck would you even go about removing fields anyway?
	 *
	 * @param id true if the field was removed, false otherwise.
	 */
	public boolean removeFieldById(int id)
	{
		for (int i = 0; i < this.getNumOfNeighbors(); i++)
			if (this.getFieldByIndex(i).getId() == id)
				return this.removeFieldByIndex(i);
		return false;
	}

	/**
	 * Returns all the neighbors attached to this field.
	 *
	 * @return List which contains all the neighbors of this field.
	 */
	public List<Field> getNeighbors()
	{
		return this.neighbors_;
	}
}
