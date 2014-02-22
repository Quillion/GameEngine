package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

/**
 * Represents a physical keyboard key.
 * Contains value for the said key and a boolean representing key state.
 */
public class Key
{
	private boolean pressed;
	private int value;

	/**
	 * Creates a key with a given value.
	 *
	 * @param value What value this key is.
	 */
	public Key(int value)
	{
		this.value = value;
		this.release();
	}

	/**
	 * Returns the value of this key.
	 *
	 * @return the value this key is assigned.
	 */
	public int getValue()
	{
		return this.value;
	}

	/**
	 * Sets the value for this key.
	 *
	 * @param value What value you want this key to have.
	 */
	public void setValue(int value)
	{
		this.value = value;
	}

	/**
	 * If you pressed the key then call this method.
	 * Sets pressed value to true.
	 */
	public void press()
	{
		this.pressed = true;
	}

	/**
	 * If you release key then call this method.
	 * Sets the pressed value to false.
	 */
	public void release()
	{
		this.pressed = false;
	}

	/**
	 * Tells you if the key is currently pressed.
	 *
	 * @return True if the key is pressed, false otherwise.
	 */
	public boolean isPressed()
	{
		return this.pressed;
	}
}
