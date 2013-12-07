package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

/**
 * Basic dimension that stores values either fo size, or some kind of
 * boundaries or offsets. All the values used are int of course unlike Point.
 */
public class Dimensions
{
	/**
	 * What kind of dimension will not have width/horizontal and height/vertical.
	 */
	private int width, height;

	/**
	 * In order to construct dimension we need its two values.
	 *
	 * @param width  Width/Horizontal of this dimension.
	 * @param height Height/vertical of this dimension.
	 */
	public Dimensions(int width, int height)
	{
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * Returns the width of this dimension. Similar to getHorizontal.
	 *
	 * @return The Width of this dimension.
	 */
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * Sets the width of this dimension to given value. Similar to setHorizontal.
	 *
	 * @param width The new width of this dimension.
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * Returns the height of the given dimension. Similar to getVertical.
	 *
	 * @return Height of this dimension.
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * Sets the height of the dimension to the given value. Similar to setVertical.
	 *
	 * @param height The new height for this dimension.
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * Increments the width of the dimension by the given value.
	 *
	 * @param amount How much to increment width by.
	 */
	public void incrementWidth(int amount)
	{
		this.width += amount;
	}

	/**
	 * Increments the height of the dimension by the given value.
	 *
	 * @param amount How much to increment height by.
	 */
	public void incrementHeight(int amount)
	{
		this.height += amount;
	}

	/**
	 * Returns the vertical of the given dimension. Similar to getHeight.
	 *
	 * @return vertical of this dimension.
	 */
	public int getVertical()
	{
		return this.height;
	}

	/**
	 * Returns the horizontal of this dimension. Similar to getWidth.
	 *
	 * @return The horizontal of this dimension.
	 */
	public int getHorizontal()
	{
		return this.width;
	}

	/**
	 * Sets the vertical of the dimension to the given value. Similar to setHeight.
	 *
	 * @param height The new vertical for this dimension.
	 */
	public void setVertical(int vertical)
	{
		this.height = vertical;
	}

	/**
	 * Sets the horizontal of this dimension to given value. Similar to setWidth.
	 *
	 * @param horizontal The new horizontal of this dimension.
	 */
	public void setHorizontal(int horizontal)
	{
		this.width = horizontal;
	}
}
