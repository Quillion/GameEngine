package BasicObjects;

/**
 * User: Edgar
 * Date: 2/2/14
 * Time: 7:40 AM
 */
public class Key
{
	private boolean pressed;
	private int value;

	public Key(int value)
	{
		this.value = value;
		this.release();
	}

	public int getValue()
	{
		return this.value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public void press()
	{
		this.pressed = true;
	}

	public void release()
	{
		this.pressed = false;
	}

	public boolean isPressed()
	{
		return this.pressed;
	}
}
