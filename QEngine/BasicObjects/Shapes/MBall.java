package BasicObjects.shapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Point;

import java.awt.*;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */
public class MBall extends BBall
{
	private Point vector;

	public MBall()
	{
		super();
		this.vector = new Point(0, 0);
	}

	/**
	 * Sets the x vector to whatever you want.
	 * Useful if you want to launch an object at high speed.
	 *
	 * @param vector vector value to which x vector will be set to.
	 */
	public void setXVector(double vector)
	{
		this.vector.setX(vector);
	}

	/**
	 * Returns a double which represents x vector.
	 *
	 * @return value of x vector.
	 */
	public double getXVector()
	{
		return this.vector.getX();
	}

	/**
	 * Increments x vector by a value of vector.
	 *
	 * @param vector value by which x vector will be incremented.
	 */
	public void incrementXVector(double vector)
	{
		this.vector.incrementX(vector);
	}

	/**
	 * Reverses the X vector of this ball.
	 */
	public void reverseXVector()
	{
		this.vector.reverseX();
	}

	/**
	 * Sets the y vector to whatever you want.
	 * Useful if you want to launch an object at high speed.
	 *
	 * @param vector vector value to which y vector will be set to.
	 */
	public void setYVector(double vector)
	{
		this.vector.setY(vector);
	}

	/**
	 * Returns a double which represents y vector.
	 *
	 * @return value of y vector.
	 */
	public double getYVector()
	{
		return this.vector.getY();
	}

	/**
	 * Increments y vector by a value of vector.
	 *
	 * @param vector value by which y vector will be incremented.
	 */
	public void incrementYVector(double vector)
	{
		this.vector.incrementY(vector);
	}

	/**
	 * Reverses the y vector of this ball.
	 */
	public void reverseYVector()
	{
		this.vector.reverseY();
	}

	/**
	 * Gives you a Point that represents the vector of this character.
	 *
	 * @return Vector of this given character.
	 */
	public Point getVector()
	{
		return this.vector;
	}

	/**
	 * Sets the vector of this object to the newly given vector;
	 *
	 * @param vector The new Vector to assign to this object.
	 */
	public void setVector(Point vector)
	{
		this.vector.changeLocation(vector);
	}

	/**
	 * Applies this character's vector to itself and moves it by the vector's value.
	 *
	 * @return The instance of this ball after it has been moved.
	 */
	public MBall move()
	{
		this.move(this.vector);
		return this;
	}

	/**
	 * Reverses the y and x vector of this ball.
	 * Would be good to use for bouncing.
	 */
	public void reverseVector()
	{
		this.vector.reverse();
	}

	/**
	 * Draws the box into to the graphics passed,
	 * box outline will be black color.
	 * The bounding box will be dark gray color.
	 * The Vector line will be light gray line.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	@Override
	public void draw(Graphics2D g)
	{
		this.drawBall(g);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(this.getCenterX(),
				this.getCenterY(),
				(int) (this.getCenterX() + this.getXVector() * 5),
				(int) (this.getCenterY() + this.getYVector() * 5));
	}

	/**
	 * Draws the box into to the graphics passed,
	 * box outline will be black color.
	 * The bounding box will be gray color.
	 *
	 * @param g graphics where the bounding box will be drawn into.
	 */
	@Override
	public void drawBall(Graphics2D g)
	{
		super.draw(g);
	}

	/**
	 * Returns a copy of it's parent which is bounding Box. The copy is brand new object.
	 *
	 * @return Brand new bounding box which is a copy of the bounding box this object is built on.
	 */
	public BBall getBBall()
	{
		return super.copy();
	}

	/**
	 * Returns a brand new copy of this Movement Box object. With a new memory location.
	 *
	 * @return A new copy of this Movement Box object.
	 */
	public MBall copy()
	{
		MBall ball = new MBall();
		ball.setX(this.getX());
		ball.setY(this.getY());
		ball.setWidth(this.getWidth());
		ball.setHeight(this.getHeight());
		ball.setOffset(this.getOffset());
		ball.setVector(this.getVector().copy());
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
		sb.append(super.toString()).append("\n");
		sb.append("Vector:").append("|\n");
		sb.append("x: |").append(getXVector()).append("|");
		sb.append(" y: |").append(getYVector()).append("|");
		return sb.toString();
	}
}
