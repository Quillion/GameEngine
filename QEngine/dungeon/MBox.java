package dungeon;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Key;
import constants.Constants;

/**
 * MBox used for dungeons. Contains acceleration, friction, speed limit, and remembers keys pressed.
 */
public class MBox extends basicObjects.shapes.MBox
{
	private Key[]  keys;
	private double acceleration;
	private double maxSpeed;
	private double friction;

	/**
	 * Constructs MBox. Sets keys, acceleration, max speed and friction to zero.
	 */
	public MBox()
	{
		super();
		this.keys = new Key[Constants.Direction.Total.getValue()];
		for (int i = 0; i < this.keys.length; i++)
		{
			this.keys[i] = new Key(0);
		}
		this.acceleration = 0d;
		this.maxSpeed = 0d;
		this.friction = 0d;
	}

	/**
	 * Sets the keys to the given values.
	 *
	 * @param keys
	 * 		new values for the keys.
	 */
	public void setKeys(int[] keys)
	{
		for (int i = 0; i < keys.length; i++)
		{
			this.keys[i].setValue(keys[i]);
		}
	}

	/**
	 * Sets the keys to the given values.
	 *
	 * @param left
	 * 		The key value for left.
	 * @param up
	 * 		The key value for up.
	 * @param right
	 * 		The key value for right.
	 * @param down
	 * 		The key value for down.
	 */
	public void setKeys(int left, int up, int right, int down)
	{
		this.keys[Constants.Direction.Left.getValue()].setValue(left);
		this.keys[Constants.Direction.Up.getValue()].setValue(up);
		this.keys[Constants.Direction.Right.getValue()].setValue(right);
		this.keys[Constants.Direction.Down.getValue()].setValue(down);
	}

	/**
	 * Returns the key for the given direction.
	 *
	 * @param direction
	 * 		Which direction key to return.
	 *
	 * @return Key for the given value.
	 */
	public int getKey(Constants.Direction direction)
	{
		return this.keys[direction.getValue()].getValue();
	}

	/**
	 * Sets the value for the given key to press.
	 *
	 * @param direction
	 * 		Which direction the key was pressed.
	 */
	public void press(Constants.Direction direction)
	{
		this.keys[direction.getValue()].press();
	}

	/**
	 * Sets the value for the given key to released.
	 *
	 * @param direction
	 * 		Which directional key to release.
	 */
	public void release(Constants.Direction direction)
	{
		this.keys[direction.getValue()].release();
	}

	/**
	 * Tells you if the given key is pressed.
	 *
	 * @param direction
	 * 		The direction which you want to check key for.
	 *
	 * @return True if the given directional key is pressed, false otherwise.
	 */
	public boolean isPressed(Constants.Direction direction)
	{
		return this.keys[direction.getValue()].isPressed();
	}

	/**
	 * Tells you the acceleration of the given MBox.
	 *
	 * @return Value representing acceleration for this box.
	 */
	public double getAcceleration()
	{
		return this.acceleration;
	}

	/**
	 * Sets the acceleration for this box to the given value.
	 *
	 * @param acceleration
	 * 		New acceleration for the given box.
	 */
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
