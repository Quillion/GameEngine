package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

/**
 * Basic dimension that stores values either fo size, or some kind of boundaries or offsets. All the values used are int
 * of course unlike Point.
 */
public class Dimensions
{
	/**
	 * What kind of dimension will not have width and height.
	 */
	private int width, height;

	/**
	 * In order to construct dimension we need its two values.
	 *
	 * @param width
	 * 		Width of this dimension.
	 * @param height
	 * 		Height of this dimension.
	 */
	public Dimensions(int width, int height)
	{
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * Returns the width of this dimension.
	 *
	 * @return The Width of this dimension.
	 */
	public int getWidth()
	{
		return this.width;
	}

	/**
	 * Sets the width of this dimension to given value.
	 *
	 * @param width
	 * 		The new width of this dimension.
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

	/**
	 * Returns the height of the given dimension.
	 *
	 * @return Height of this dimension.
	 */
	public int getHeight()
	{
		return this.height;
	}

	/**
	 * Sets the height of the dimension to the given value.
	 *
	 * @param height
	 * 		The new height for this dimension.
	 */
	public void setHeight(int height)
	{
		this.height = height;
	}

	/**
	 * Increments the width of the dimension by the given value.
	 *
	 * @param amount
	 * 		How much to increment width by.
	 */
	public void incrementWidth(int amount)
	{
		this.width += amount;
	}

	/**
	 * Increments the height of the dimension by the given value.
	 *
	 * @param amount
	 * 		How much to increment height by.
	 */
	public void incrementHeight(int amount)
	{
		this.height += amount;
	}

	/**
	 * Sets this dimension to the newly specified value.
	 *
	 * @param width
	 * 		The new width of this dimension.
	 * @param height
	 * 		The new Height of this dimension.
	 */
	public void setDimensions(int width, int height)
	{
		this.setWidth(width);
		this.setHeight(height);
	}

	/**
	 * Sets This dimension to the given Dimension. Only values get copied over but not the memory location.
	 *
	 * @param dimensions
	 * 		The new Dimension whose values to copy.
	 */
	public void setDimensions(Dimensions dimensions)
	{
		this.setDimensions(dimensions.getWidth(), dimensions.getHeight());
	}

	/**
	 * Copies the values of this Dimension and returns you new Dimension object.
	 *
	 * @return New Dimensions object that takes up new memory spot.
	 */
	public Dimensions copy()
	{
		return new Dimensions(getWidth(), getHeight());
	}

	/**
	 * Returns a string representation tha explains everything about a given Object.
	 *
	 * @return String which contains all the info about the object.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Size:\n");
		sb.append("width: |").append(getWidth()).append("|");
		sb.append(" height: |").append(getHeight()).append("|");
		return sb.toString();
	}
}
