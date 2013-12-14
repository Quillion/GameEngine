package abstracts;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import BasicObjects.Point;

import java.awt.*;

/**
 * A simple abstract shape that will exist in a
 * 2D world and will as such have 2D properties.
 */
public abstract class Shape
{
	/**
	 * Sets the value of x to whatever you choose to.
	 *
	 * @param x the value to which x will be set to.
	 */
	public abstract void setX(double x);

	/**
	 * Return x value of the shape.
	 * I return int because it is easier to use for collision detection.
	 *
	 * @return returns the x value of the shape.
	 */
	public abstract int getX();

	/**
	 * Increment the shape's x value, very useful for movement.
	 *
	 * @param x the value by which to increment x by.
	 */
	public abstract void incrementX(double x);

	/**
	 * Sets the value of y to whatever you choose to.
	 *
	 * @param y the value to which y will be set to.
	 */
	public abstract void setY(double y);

	/**
	 * Return y value of the shape.
	 * I return int because it is easier to use for collision detection.
	 *
	 * @return returns the y value of the shape.
	 */
	public abstract int getY();

	/**
	 * Increment the shape's y value, very useful for movement.
	 *
	 * @param y the value by which to increment y by.
	 */
	public abstract void incrementY(double y);

	/**
	 * Moves this shape by the given vector.
	 *
	 * @param vector The vector that should be applied to this shape.
	 */
	public abstract void move(Point vector);

	/**
	 * Sets the top left corner of this object(coordinates) to the given point.
	 *
	 * @param point The new coordinates of this shape.
	 */
	public abstract void setCoordinates(Point point);

	/**
	 * Returns the shape's left x coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's left x coordinate.
	 */
	public abstract int getLeftX();

	/**
	 * Returns the shape's right x coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's right x coordinate(which is x+width).
	 */
	public abstract int getRightX();

	/**
	 * Returns the shape's top y coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's top y coordinate.
	 */
	public abstract int getTopY();

	/**
	 * returns the shape's bottom y coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's bottom y coordinate(which is y+height).
	 */
	public abstract int getBottomY();

	/**
	 * Gives you the top left corner of the shape.
	 *
	 * @return Point which represents top left corner of the shape.
	 */
	public abstract Point getTopLeft();

	/**
	 * Gives you the top right corner of the shape.
	 *
	 * @return Point which represents top right corner of the shape.
	 */
	public abstract Point getTopRight();

	/**
	 * Gives you the bottom left corner of the shape.
	 *
	 * @return Point which represents bottom left corner of the shape.
	 */
	public abstract Point getBottomLeft();

	/**
	 * Gives you the bottom right corner of the shape.
	 *
	 * @return Point which represents bottom right corner of the shape.
	 */
	public abstract Point getBottomRight();

	/**
	 * Returns the shape's center x coordinate.
	 * Is used for whatever you want, but it is costly.
	 *
	 * @return the shape's center x coordinate.
	 */
	public abstract int getCenterX();

	/**
	 * Returns the shape's center y coordinate.
	 * Is used for whatever you want, but it is costly.
	 *
	 * @return the shape's center y coordinate.
	 */
	public abstract int getCenterY();

	/**
	 * Returns a point which is the center of this shape.
	 *
	 * @return Point which represents the center of this shape.
	 */
	public abstract Point getCenter();

	/**
	 * Sets the width value to whatever you choose to.
	 *
	 * @param width the value to which shape's width will be set to.
	 */
	public abstract void setWidth(int width);

	/**
	 * returns the shape's width.
	 *
	 * @return the width of this shape.
	 */
	public abstract int getWidth();

	/**
	 * Increments the shape's width by a set amount.
	 *
	 * @param amount how much to increment this shape's width by.
	 */
	public abstract void incrementWidth(int amount);

	/**
	 * Sets the height value to whatever you choose to.
	 *
	 * @param height the value to which shape's height will be set to.
	 */
	public abstract void setHeight(int height);

	/**
	 * returns the shape's height.
	 *
	 * @return the width of this shape.
	 */
	public abstract int getHeight();

	/**
	 * Increments the shape's height by a set amount.
	 *
	 * @param amount how much to increment this shape's height by.
	 */
	public abstract void incrementHeight(int amount);

	/**
	 * Sets the size of this object to the given dimension.
	 *
	 * @param size The new Size for this object.
	 */
	public abstract void setSize(Dimensions size);

	/**
	 * Same as drawBox.
	 *
	 * @param g graphics where the shape will be drawn into.
	 */
	public abstract void draw(Graphics2D g);
}
