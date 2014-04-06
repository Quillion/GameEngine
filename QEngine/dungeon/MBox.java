package dungeon;

import BasicObjects.Key;
import Constants.Constants;

/**
 * User: Edgar
 * Date: 1/15/14
 * Time: 8:07 PM
 */
public class MBox extends BasicObjects.Shapes.MBox
{
	private Key[] keys;
	private double acceleration;
	private double maxSpeed;
	private double friction;

	public MBox()
	{
		super();
		this.keys = new Key[Constants.Direction.Total.getValue()];
		for (int i = 0; i < this.keys.length; i++)
			this.keys[i] = new Key(0);
		this.acceleration = 0d;
		this.maxSpeed = 0d;
		this.friction = 0d;
	}

	public void setKeys(int[] keys)
	{
		for (int i = 0; i < keys.length; i++)
		{
			this.keys[i].setValue(keys[i]);
		}
	}

	public void setKeys(int left, int up, int right, int down)
	{
		this.keys[Constants.Direction.Left.getValue()].setValue(left);
		this.keys[Constants.Direction.Up.getValue()].setValue(up);
		this.keys[Constants.Direction.Right.getValue()].setValue(right);
		this.keys[Constants.Direction.Down.getValue()].setValue(down);
	}

	public int getKey(Constants.Direction direction)
	{
		return this.keys[direction.getValue()].getValue();
	}

	public void press(Constants.Direction direction)
	{
		this.keys[direction.getValue()].press();
	}

	public void release(Constants.Direction direction)
	{
		this.keys[direction.getValue()].release();
	}

	public boolean isPressed(Constants.Direction direction)
	{
		return this.keys[direction.getValue()].isPressed();
	}

	public double getAcceleration()
	{
		return this.acceleration;
	}

	public void setAcceleration(double acceleration)
	{
		this.acceleration = acceleration;
	}

	public void incrementAcceleration(double acceleration)
	{
		this.acceleration += acceleration;
	}

	public double getMaxSpeed()
	{
		return this.maxSpeed;
	}

	public void setMaxSpeed(double maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}

	public void incrementMaxSpeed(double maxSpeed)
	{
		this.maxSpeed += maxSpeed;
	}

	public double getFriction()
	{
		return this.friction;
	}

	public void setFriction(double friction)
	{
		this.friction = friction;
	}

	public void incrementFriction(double friction)
	{
		this.friction += friction;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\n");
		sb.append("Keys:").append("\n");
		sb.append("Left: |").append(getKey(Constants.Direction.Left)).append("|");
		sb.append(" Up: |").append(getKey(Constants.Direction.Up)).append("|");
		sb.append(" Right: |").append(getKey(Constants.Direction.Right)).append("|");
		sb.append(" Down: |").append(getKey(Constants.Direction.Down)).append("|\n");
		sb.append("Etc:").append("\n");
		sb.append("Max Speed: |").append(getMaxSpeed()).append("|");
		sb.append(" Acceleration: |").append(getAcceleration()).append("|");
		sb.append(" Friction: |").append(getFriction()).append("|");
		return sb.toString();
	}
}
