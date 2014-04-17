package platformer;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Key;
import constants.Constants;
import logic.MathEngine;

/**
 * MBox used for platformers. Contains acceleration, friction, speed limit,
 * gravity, jump, and remembers keys pressed.
 */
public class MBox extends basicObjects.shapes.MBox
{
	private Key[]   keys;
	private double  acceleration;
	private double  maxSpeed;
	private double  friction;
	private double  jump;
	private double  gravity;
	private boolean standing;

	/**
	 * Constructs MBox. Sets keys, acceleration, max speed and friction to
	 * zero.
	 */
	public MBox()
	{
		super();
		this.keys = new Key[Constants.Direction.Total.getValue()];
		for (int i = 0; i < this.keys.length; i++)
		{
			this.keys[i] = new Key(0);
		}
		this.setAcceleration(0d);
		this.setMaxSpeed(0d);
		this.setFriction(0d);
		this.setJump(0d);
		this.setGravity(0d);
		this.setStanding(false);
	}

	/**
	 * Sets the keys to the given values.
	 *
	 * @param keys
	 * 		new values for the keys.
	 */
	public void setKeys(int[] keys)
	{
		if (keys.length != Constants.Direction.Total.getValue())
		{
			return;
		}
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

	/**
	 * Increments acceleration by a given value. Can be used for buffs and
	 * debufs.
	 *
	 * @param value
	 * 		The value by which to increment acceleration.
	 */
	public void incrementAcceleration(double value)
	{
		this.acceleration += value;
	}

	/**
	 * Gives you the max speed that the given box can achieve.
	 *
	 * @return The max speed of this box.
	 */
	public double getMaxSpeed()
	{
		return this.maxSpeed;
	}

	/**
	 * Sets the maximum speed of this object to the given value.
	 *
	 * @param maxSpeed
	 * 		The new max speed of this object.
	 */
	public void setMaxSpeed(double maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}

	/**
	 * Increments the max speed of this object by a specified value.
	 *
	 * @param value
	 * 		Value by which to increment the max speed of this object.
	 */
	public void incrementMaxSpeed(double value)
	{
		this.maxSpeed += value;
	}

	/**
	 * Tells you what is the current friction of this box with the ground, and
	 * air.
	 *
	 * @return Friction of this object while moving on the ground, or being in
	 * the air.
	 */
	public double getFriction()
	{
		return this.friction;
	}

	/**
	 * Sets the friction of this box by a specified value.
	 *
	 * @param friction
	 * 		The new friction of this given box.
	 */
	public void setFriction(double friction)
	{
		this.friction = friction;
	}

	/**
	 * Increments the friction of this box by a given value.
	 *
	 * @param value
	 * 		The value by which to increment friction.
	 */
	public void incrementFriction(double value)
	{
		this.friction += value;
	}

	/**
	 * Returns you the strength with which this box can jump.
	 *
	 * @return This box's jumping strength/magnitude.
	 */
	public double getJump()
	{
		return this.jump;
	}

	/**
	 * Sets this box's jumping strength to the specified value.
	 *
	 * @param jump
	 * 		New value for this box's jumping strength.
	 */
	public void setJump(double jump)
	{
		this.jump = jump;
	}

	/**
	 * Increments this box's jumping magnitude by the given value.
	 *
	 * @param value
	 * 		How much to increase this box's jumping strength by.
	 */
	public void incrementJump(double value)
	{
		this.jump += value;
	}

	/**
	 * The gravity that is affecting this given box.
	 *
	 * @return What gravity this box is under.
	 */
	public double getGravity()
	{
		return gravity;
	}

	/**
	 * Sets the gravity for this box to the specified value.
	 *
	 * @param gravity
	 * 		New gravity that will be affecting this box.
	 */
	public void setGravity(double gravity)
	{
		this.gravity = gravity;
	}

	/**
	 * Increments the gravity of this box by a given value.
	 *
	 * @param value
	 * 		How much to increment gravity of this box by.
	 */
	public void incrementGravity(double value)
	{
		this.gravity += value;
	}

	/**
	 * If character is currently standing on some surface and is not falling,
	 * then this will return true. It will return false otherwise.
	 *
	 * @return True if character is standing, false otherwise.
	 */
	public boolean isStanding()
	{
		return this.standing;
	}

	/**
	 * Sets the standing position of this character to the given value.
	 *
	 * @param standing
	 * 		True if you think the character is standing on the solid ground, false
	 * 		if he is falling down.
	 */
	public void setStanding(boolean standing)
	{
		this.standing = standing;
	}

	/**
	 * Gives you a string which represents stats and states of this object.
	 *
	 * @return String explaining this object.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString()).append("\n");
		sb.append("Keys:").append("\n");
		sb.append("Left: |").append(getKey(Constants.Direction.Left))
		  .append("|");
		sb.append(" Up: |").append(getKey(Constants.Direction.Up)).append("|");
		sb.append(" Right: |").append(getKey(Constants.Direction.Right))
		  .append("|");
		sb.append(" Down: |").append(getKey(Constants.Direction.Down))
		  .append("|\n");
		sb.append("Etc:").append("\n");
		sb.append("Max Speed: |").append(getMaxSpeed()).append("|");
		sb.append(" Acceleration: |").append(getAcceleration()).append("|");
		sb.append(" Friction: |").append(getFriction()).append("|");
		sb.append(" Gravity: |").append(getGravity()).append("|");
		return sb.toString();
	}

	/**
	 * Returns a new copy of this object which has values in different memory
	 * location. Pretty much performs a deep copy.
	 *
	 * @return A new copy of this Movement Box object.
	 */
	@Override
	public MBox copy()
	{
		MBox box = new MBox();
		box.setX(this.getX());
		box.setY(this.getY());
		box.setWidth(this.getWidth());
		box.setHeight(this.getHeight());
		box.setHorizontalOffset(this.getHorizontalOffset());
		box.setVerticalOffset(this.getVerticalOffset());
		box.setVector(this.getVector().copy());
		box.setKeys(this.getKey(Constants.Direction.Left),
					this.getKey(Constants.Direction.Up),
					this.getKey(Constants.Direction.Right),
					this.getKey(Constants.Direction.Down));
		box.setAcceleration(this.getAcceleration());
		box.setMaxSpeed(this.getMaxSpeed());
		box.setFriction(this.getFriction());
		box.setGravity(this.getGravity());
		box.setJump(this.getJump());
		return box;
	}

	/**
	 * Compares the given object to this moving box.
	 *
	 * @param object
	 * 		The object to which to compare this shape to.
	 *
	 * @return True if given object has same values as this box, false
	 * otherwise.
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object instanceof MBox)
		{
			MBox box = (MBox) object;
			return super.equals(object) &&
					this.getKey(Constants.Direction.Left) ==
							box.getKey(Constants.Direction.Left) &&
					this.getKey(Constants.Direction.Up) ==
							box.getKey(Constants.Direction.Up) &&
					this.getKey(Constants.Direction.Right) ==
							box.getKey(Constants.Direction.Right) &&
					this.getKey(Constants.Direction.Down) ==
							box.getKey(Constants.Direction.Down) &&
					MathEngine.equals(this.getAcceleration(),
									  box.getAcceleration()) &&
					MathEngine.equals(this.getMaxSpeed(),
									  box.getMaxSpeed()) &&
					MathEngine.equals(this.getFriction(),
									  box.getFriction()) &&
					MathEngine.equals(this.getGravity(),
									  box.getGravity()) &&
					MathEngine.equals(this.getJump(),
									  box.getJump());
		}
		return false;
	}
}
