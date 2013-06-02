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

	public boolean addField(Field field)
	{
		for(Field neighbor : neighbors_)
			if (field.getId() == neighbor.getId())
				return false;
		if(this.getId() == field.getId())
			return false;
		this.neighbors_.add(field);
		return true;
	}

	public int getNumOfNeighbors()
	{
		return this.neighbors_.size();
	}

	public Field getField(int index)
	{
		if(index < this.getNumOfNeighbors() || index >= 0)
			return this.neighbors_.get(index);
		else
			return null;
	}

	public void removeField(int index)
	{
		this.neighbors_.remove(index);
	}

	public List<Field> getNeighbors()
	{
		return this.neighbors_;
	}
}
