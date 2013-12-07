package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstracts.Shape;

import java.awt.*;

/**
 * A simple ball that is completely round. This is not an oval so keep that in mind.
 */
public class Ball extends Shape
{
	/**
	 * Any ball has a center coordinate and radius.
	 */
	private Point center;
	private int radius;

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
	 * @param x the value to which x will be set to.
	 */
	public void setX(double x)
	{
		this.center.setX(x);
	}

	/**
	 * Return x value of the object.
	 * I return int because it is easier to use for collision detection.
	 *
	 * @return returns the x value of the object.
	 */
	public int getX()
	{
		return (int) this.center.getX();
	}

	/**
	 * Increment the object's x value, very useful for movement.
	 *
	 * @param x the value by which to increment x by.
	 */
	public void incrementX(double x)
	{
		this.center.incrementX(x);
	}

	/**
	 * Sets the value of y to whatever you choose to.
	 *
	 * @param y the value to which y will be set to.
	 */
	public void setY(double y)
	{
		this.center.setY(y);
	}

	/**
	 * Return y value of the object.
	 * I return int because it is easier to use for collision detection.
	 *
	 * @return returns the y value of the object.
	 */
	public int getY()
	{
		return (int) this.center.getY();
	}

	/**
	 * Increment the object's y value, very useful for movement.
	 *
	 * @param y the value by which to increment y by.
	 */
	public void incrementY(double y)
	{
		this.center.incrementY(y);
	}

	/**
	 * Tells you the current center of this ball.
	 *
	 * @return The center of this ball.
	 */
	public Point getCenter()
	{
		return this.center;
	}

	/**
	 * Sets the width value to whatever you choose to.
	 *
	 * @param width the value to which shape's width will be set to.
	 */
	public void setWidth(int width)
	{
		this.setRadius(width);
	}

	/**
	 * returns the shape's width.
	 *
	 * @return the width of this shape.
	 */
	public int getWidth()
	{
		return this.getRadius();
	}

	/**
	 * Increments the shape's width by a set amount.
	 *
	 * @param amount how much to increment this shape's width by.
	 */
	public void incrementWidth(int amount)
	{
		this.incrementRadius(amount);
	}

	/**
	 * Sets the height value to whatever you choose to.
	 *
	 * @param height the value to which shape's height will be set to.
	 */
	public void setHeight(int height)
	{
		this.setRadius(height);
	}

	/**
	 * returns the shape's height.
	 *
	 * @return the width of this shape.
	 */
	public int getHeight()
	{
		return this.getRadius();
	}

	/**
	 * Increments the shape's height by a set amount.
	 *
	 * @param amount how much to increment this shape's height by.
	 */
	public void incrementHeight(int amount)
	{
		this.incrementRadius(amount);
	}

	/**
	 * Moves this box by the given vector.
	 *
	 * @param vector The vector that should be applied to this box.
	 */
	public void move(Point vector)
	{
		this.center.move(vector);
	}

	/**
	 * Returns the shape's left x coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's left x coordinate.
	 */
	public int getLeftX()
	{
		return (this.getX() - this.getRadius());
	}

	/**
	 * Returns the shape's right x coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's right x coordinate(which is x+width).
	 */
	public int getRightX()
	{
		return (this.getX() + this.getWidth());
	}

	/**
	 * Returns the shape's top y coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's top y coordinate.
	 */
	public int getTopY()
	{
		return (this.getY() - this.getHeight());
	}

	/**
	 * Returns the shape's bottom y coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the shape's bottom y coordinate(which is y+height).
	 */
	public int getBottomY()
	{
		return (this.getY() + this.getHeight());
	}

	/**
	 * Gives you the top left corner of the shape.
	 *
	 * @return Point which represents top left corner of the shape.
	 */
	public Point getTopLeft()
	{
		return new Point(this.getLeftX(), this.getTopY());
	}

	/**
	 * Gives you the top right corner of the shape.
	 *
	 * @return Point which represents top right corner of the shape.
	 */
	public Point getTopRight()
	{
		return new Point(this.getRightX(), this.getTopY());
	}

	/**
	 * Gives you the bottom left corner of the shape.
	 *
	 * @return Point which represents bottom left corner of the shape.
	 */
	public Point getBottomLeft()
	{
		return new Point(this.getLeftX(), this.getBottomY());
	}

	/**
	 * Gives you the bottom right corner of the shape.
	 *
	 * @return Point which represents bottom right corner of the shape.
	 */
	public Point getBottomRight()
	{
		return new Point(this.getRightX(), this.getBottomY());
	}

	/**
	 * Returns the shape's center x coordinate.
	 * Is used for whatever you want, but it is costly.
	 *
	 * @return the shape's center x coordinate.
	 */
	public int getCenterX()
	{
		return this.getX();
	}

	/**
	 * Returns the shape's center y coordinate.
	 * Is used for whatever you want, but it is costly.
	 *
	 * @return the shape's center y coordinate.
	 */
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
		return radius;
	}

	/**
	 * Sets the radius of the ball to the given value.
	 *
	 * @param radius The new radius of this ball.
	 */
	public void setRadius(int radius)
	{
		this.radius = radius;
	}

	/**
	 * Increments the ball's radius. This will probably make for cool effect.
	 *
	 * @param amount How much to increment radius by.
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
	 * Draws the ball into to the graphics passed,
	 * box outline will be black color.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	public void drawBall(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.drawOval(this.getX() - this.getRadius(), this.getY() - this.getRadius(), this.getDiameter(), this.getDiameter());
	}

	/**
	 * Same as drawBall.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	public void draw(Graphics2D g)
	{
		this.drawBall(g);
	}
}
