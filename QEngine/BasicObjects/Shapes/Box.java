package basicObjects.shapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Dimensions;
import basicObjects.Point;
import abstracts.Shape;

/**
 * A simple Box. Has all the parameters you would expect box to have.
 */
public class Box extends Shape
{
	/**
	 * A box will always have a point and dimension.
	 */
	private Point      coordinates;
	private Dimensions size;

	/**
	 * A constructor for the box. Box does not contain any offsets, it is just a box. All the values are set to 0 on
	 * creation.
	 */
	public Box()
	{
		this.coordinates = new Point(0, 0);
		this.size = new Dimensions(0, 0);
	}

	/**
	 * Sets the value of x to whatever you choose to.
	 *
	 * @param x
	 * 		the value to which x will be set to.
	 */
	@Override
	public void setX(double x)
	{
		this.coordinates.setX(x);
	}

	/**
	 * Return x value of the object. I return int because it is easier to use for collision detection.
	 *
	 * @return returns the x value of the object.
	 */
	@Override
	public int getX()
	{
		return (int) this.coordinates.getX();
	}

	/**
	 * Increment the object's x value, very useful for movement.
	 *
	 * @param x
	 * 		the value by which to increment x by.
	 */
	@Override
	public void incrementX(double x)
	{
		this.coordinates.incrementX(x);
	}

	/**
	 * Sets the value of y to whatever you choose to.
	 *
	 * @param y
	 * 		the value to which y will be set to.
	 */
	@Override
	public void setY(double y)
	{
		this.coordinates.setY(y);
	}

	/**
	 * Return y value of the object. I return int because it is easier to use for collision detection.
	 *
	 * @return returns the y value of the object.
	 */
	@Override
	public int getY()
	{
		return (int) this.coordinates.getY();
	}

	/**
	 * Increment the object's y value, very useful for movement.
	 *
	 * @param y
	 * 		the value by which to increment y by.
	 */
	@Override
	public void incrementY(double y)
	{
		this.coordinates.incrementY(y);
	}

	/**
	 * Moves this box by the given vector.
	 *
	 * @param vector
	 * 		The vector that should be applied to this box.
	 */
	@Override
	public void move(Point vector)
	{
		this.coordinates.move(vector);
	}

	/**
	 * Changes the coordinates of this box to specified value.
	 *
	 * @param point
	 * 		The new coordinates of this box.
	 */
	@Override
	public void setCoordinates(Point point)
	{
		this.coordinates.changeLocation(point);
	}

	/**
	 * Sets the width value to whatever you choose to.
	 *
	 * @param width
	 * 		the value to which object's width will be set to.
	 */
	@Override
	public void setWidth(int width)
	{
		this.size.setWidth(width);
	}

	/**
	 * returns the object's width.
	 *
	 * @return the width of this object.
	 */
	@Override
	public int getWidth()
	{
		return this.size.getWidth();
	}

	/**
	 * Increments the object's width by a set amount.
	 *
	 * @param amount
	 * 		how much to increment this object's width by.
	 */
	@Override
	public void incrementWidth(int amount)
	{
		this.size.incrementWidth(amount);
	}

	/**
	 * Sets the height value to whatever you choose to.
	 *
	 * @param height
	 * 		the value to which object's height will be set to.
	 */
	@Override
	public void setHeight(int height)
	{
		this.size.setHeight(height);
	}

	/**
	 * returns the object's height.
	 *
	 * @return the width of this object.
	 */
	@Override
	public int getHeight()
	{
		return this.size.getHeight();
	}

	/**
	 * Increments the object's height by a set amount.
	 *
	 * @param amount
	 * 		how much to increment this object's height by.
	 */
	@Override
	public void incrementHeight(int amount)
	{
		this.size.incrementHeight(amount);
	}

	/**
	 * Sets the size of this box to the new given Dimensions.
	 *
	 * @param size
	 * 		The new Size for this object.
	 */
	@Override
	public void setSize(Dimensions size)
	{
		this.size.setDimensions(size);
	}

	/**
	 * Returns the object's left x coordinate, is used for collision detection checking.
	 *
	 * @return the object's left x coordinate.
	 */
	@Override
	public int getLeftX()
	{
		return this.getX();
	}

	/**
	 * Returns the object's right x coordinate, is used for collision detection checking.
	 *
	 * @return the object's right x coordinate(which is x+width).
	 */
	@Override
	public int getRightX()
	{
		return (this.getX() + this.getWidth());
	}

	/**
	 * Returns the object's top y coordinate, is used for collision detection checking.
	 *
	 * @return the object's top y coordinate.
	 */
	@Override
	public int getTopY()
	{
		return this.getY();
	}

	/**
	 * Returns the object's bottom y coordinate, is used for collision detection checking.
	 *
	 * @return the object's bottom y coordinate(which is y+height).
	 */
	@Override
	public int getBottomY()
	{
		return (this.getY() + this.getHeight());
	}

	/**
	 * Gives you the top left corner of the box.
	 *
	 * @return Point which represents top left corner of the box.
	 */
	@Override
	public Point getTopLeft()
	{
		return new Point(this.getLeftX(), this.getTopY());
	}

	/**
	 * Gives you the top right corner of the box.
	 *
	 * @return Point which represents top right corner of the box.
	 */
	@Override
	public Point getTopRight()
	{
		return new Point(this.getRightX(), this.getTopY());
	}

	/**
	 * Gives you the bottom left corner of the box.
	 *
	 * @return Point which represents bottom left corner of the box.
	 */
	@Override
	public Point getBottomLeft()
	{
		return new Point(this.getLeftX(), this.getBottomY());
	}

	/**
	 * Gives you the bottom right corner of the box.
	 *
	 * @return Point which represents bottom right corner of the box.
	 */
	@Override
	public Point getBottomRight()
	{
		return new Point(this.getRightX(), this.getBottomY());
	}

	/**
	 * Returns the object's center x coordinate. Is used for whatever you want, but it is costly.
	 *
	 * @return the object's center x coordinate.
	 */
	@Override
	public int getCenterX()
	{
		return (this.getX() + this.getWidth() / 2);
	}

	/**
	 * Returns the object's center y coordinate. Is used for whatever you want, but it is costly.
	 *
	 * @return the object's center y coordinate.
	 */
	@Override
	public int getCenterY()
	{
		return (this.getY() + this.getHeight() / 2);
	}

	/**
	 * Returns a point which is the center of this Box.
	 *
	 * @return Point which represents the center of this box.
	 */
	@Override
	public Point getCenter()
	{
		return new Point(this.getCenterX(), this.getCenterY());
	}

	/**
	 * Copies the box parameters and stores it into new memory location and returns the said box.
	 *
	 * @return Newly created Box object that has same values as this current box object.
	 */
	public Box copy()
	{
		Box box = new Box();
		box.setCoordinates(this.coordinates.copy());
		box.setSize(this.size.copy());
		return box;
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
		sb.append("Coordinates:\n");
		sb.append("x: |").append(getX()).append("|");
		sb.append(" y: |").append(getY()).append("|\n");
		sb.append("Size:\n");
		sb.append("width: |").append(getWidth()).append("|");
		sb.append(" height: |").append(getHeight()).append("|");
		return sb.toString();
	}
}