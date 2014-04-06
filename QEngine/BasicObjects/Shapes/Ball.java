package BasicObjects.Shapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import BasicObjects.Point;
import abstracts.Shape;

/**
 * A simple ball that is completely round. This is not an oval so keep that in mind.
 */
public class Ball extends Shape
{
	/**
	 * Any ball has a center coordinate and radius.
	 */
	private Point center;
	private int   radius;

	/**
	 * A basic constructor. Sets center to 0, 0 and radius to 0 by default.
	 */
	public Ball()
	{
		this.center = new Point(0, 0);
		this.setRadius(0);
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
		this.center.setX(x);
	}

	/**
	 * Return x value of the object. I return int because it is easier to use for collision detection.
	 *
	 * @return the x coordinate of the object.
	 */
	@Override
	public int getX()
	{
		return (int) this.center.getX();
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
		this.center.incrementX(x);
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
		this.center.setY(y);
	}

	/**
	 * Return y value of the object. I return int because it is easier to use for collision detection.
	 *
	 * @return the y coordinate of the object.
	 */
	@Override
	public int getY()
	{
		return (int) this.center.getY();
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
		this.center.incrementY(y);
	}

	/**
	 * Tells you the current center of this ball.
	 *
	 * @return The center of this ball.
	 */
	@Override
	public Point getCenter()
	{
		return this.center;
	}

	/**
	 * Sets the width value to whatever you choose to.
	 *
	 * @param width
	 * 		the value to which shape's width will be set to.
	 */
	@Override
	public void setWidth(int width)
	{
		this.setDiameter(width);
	}

	/**
	 * returns the shape's width.
	 *
	 * @return the width of this shape.
	 */
	@Override
	public int getWidth()
	{
		return this.getDiameter();
	}

	/**
	 * Increments the shape's width by a set amount.
	 *
	 * @param amount
	 * 		how much to increment this shape's width by.
	 */
	@Override
	public void incrementWidth(int amount)
	{
		this.incrementRadius(amount / 2);
	}

	/**
	 * Sets the height value to whatever you choose to.
	 *
	 * @param height
	 * 		the value to which shape's height will be set to.
	 */
	@Override
	public void setHeight(int height)
	{
		this.setRadius(height / 2);
	}

	/**
	 * Returns the shape's height.
	 *
	 * @return the width of this shape.
	 */
	@Override
	public int getHeight()
	{
		return this.getDiameter();
	}

	/**
	 * Increments the shape's height by a set amount. Keep in mind this is a circle. So height and width is same.
	 *
	 * @param amount
	 * 		how much to increment this shape's height by.
	 */
	@Override
	public void incrementHeight(int amount)
	{
		this.incrementRadius(amount / 2);
	}

	/**
	 * Must I say that a ball must be round and as such will always only have one size?
	 *
	 * @param size
	 * 		The new Size for this object.
	 */
	@Override
	public void setSize(Dimensions size)
	{
		if (size.getWidth() == size.getHeight())
		{
			this.setRadius(size.getWidth() / 2);
		}
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
		this.center.move(vector);
	}

	/**
	 * Sets the coordinates of this ball. Ball's coordinates have to do with it's center.
	 *
	 * @param point
	 * 		The new coordinates of this ball.
	 */
	@Override
	public void setCoordinates(Point point)
	{
		this.center.changeLocation(point);
	}

	/**
	 * Returns the shape's left x coordinate, is used for collision detection checking.
	 *
	 * @return the shape's left x coordinate.
	 */
	@Override
	public int getLeftX()
	{
		return (this.getX() - this.getRadius());
	}

	/**
	 * Returns the shape's right x coordinate, is used for collision detection checking.
	 *
	 * @return the shape's right x coordinate(which is x+width).
	 */
	@Override
	public int getRightX()
	{
		return (this.getX() + this.getRadius());
	}

	/**
	 * Returns the shape's top y coordinate, is used for collision detection checking.
	 *
	 * @return the shape's top y coordinate.
	 */
	@Override
	public int getTopY()
	{
		return (this.getY() - this.getRadius());
	}

	/**
	 * Returns the shape's bottom y coordinate, is used for collision detection checking.
	 *
	 * @return the shape's bottom y coordinate(which is y+height).
	 */
	@Override
	public int getBottomY()
	{
		return (this.getY() + this.getRadius());
	}

	/**
	 * Gives you the top left corner of the shape.
	 *
	 * @return Point which represents top left corner of the shape.
	 */
	@Override
	public Point getTopLeft()
	{
		return new Point(this.getLeftX(), this.getTopY());
	}

	/**
	 * Gives you the top right corner of the shape.
	 *
	 * @return Point which represents top right corner of the shape.
	 */
	@Override
	public Point getTopRight()
	{
		return new Point(this.getRightX(), this.getTopY());
	}

	/**
	 * Gives you the bottom left corner of the shape.
	 *
	 * @return Point which represents bottom left corner of the shape.
	 */
	@Override
	public Point getBottomLeft()
	{
		return new Point(this.getLeftX(), this.getBottomY());
	}

	/**
	 * Gives you the bottom right corner of the shape.
	 *
	 * @return Point which represents bottom right corner of the shape.
	 */
	@Override
	public Point getBottomRight()
	{
		return new Point(this.getRightX(), this.getBottomY());
	}

	/**
	 * Returns the shape's center x coordinate. Is used for whatever you want, but it is costly.
	 *
	 * @return the shape's center x coordinate.
	 */
	@Override
	public int getCenterX()
	{
		return this.getX();
	}

	/**
	 * Returns the shape's center y coordinate. Is used for whatever you want, but it is costly.
	 *
	 * @return the shape's center y coordinate.
	 */
	@Override
	public int getCenterY()
	{
		return this.getY();
	}

	/**
	 * Tells you the radius of the given ball.
	 *
	 * @return the radius of the given ball.
	 */
	public int getRadius()
	{
		return this.radius;
	}

	/**
	 * Sets the radius of the ball to the given value.
	 *
	 * @param radius
	 * 		The new radius of this ball.
	 */
	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	/**
	 * Increments the ball's radius. This will probably make for cool effect.
	 *
	 * @param amount
	 * 		How much to increment radius by.
	 */
	public void incrementRadius(int amount)
	{
		this.radius += amount;
	}

	/**
	 * Returns the diameter of this ball.
	 *
	 * @return Radius times two which is the diameter.
	 */
	public int getDiameter()
	{
		return this.getRadius() * 2;
	}

	/**
	 * Sets the diameter of this circle to whatever you would like to
	 *
	 * @param diameter
	 * 		The diameter for this circle.
	 */
	public void setDiameter(int diameter)
	{
		this.setRadius(diameter / 2);
	}

	/**
	 * Returns a brand new copy of this Ball and it will have all the same values.
	 *
	 * @return A new copy of this ball object.
	 */
	public Ball copy()
	{
		Ball ball = new Ball();
		ball.setRadius(this.getRadius());
		ball.setCoordinates(this.getCenter().copy());
		return ball;
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
		sb.append("Radius: |").append(getRadius()).append("|");
		return sb.toString();
	}
}
