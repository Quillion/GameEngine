package basicObjects;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import logic.MathEngine;

/**
 * Basic Point. Contains x and y coordinate which are both double. Can represent
 * vectors.
 */
public class Point
{
	/**
	 * Well any point on 2D plane needs two points.
	 */
	private double x, y;

	/**
	 * We can not have a Point without coordinates, so let's specify them.
	 *
	 * @param x
	 * 		The x coordinate of the point.
	 * @param y
	 * 		The y coordinate of the point.
	 */
	public Point(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Tells you the coordinate of this point on x axis.
	 *
	 * @return The x coordinate of this point.
	 */
	public double getX()
	{
		return this.x;
	}

	/**
	 * Sets the x coordinate of this point to specified value.
	 *
	 * @param x
	 * 		What the new x coordinate should be.
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * Increments the x coordinate of this point by what you want.
	 *
	 * @param amount
	 * 		The vector that will affect the x coordinate of this point.
	 */
	public void incrementX(double amount)
	{
		this.x += amount;
	}

	/**
	 * Reverses the direction of the x vector. This is mainly used when the
	 * point object is being treated as a vector.
	 */
	public void reverseX()
	{
		this.x = -this.getX();
	}

	/**
	 * Tells you the y coordinate of this point.
	 *
	 * @return The y coordinate of this point.
	 */
	public double getY()
	{
		return this.y;
	}

	/**
	 * Sets the y coordinate value of this point to what you tell it to.
	 *
	 * @param y
	 * 		What you would like the new y coordinate to be.
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/**
	 * Moves this point's y value by the given vector.
	 *
	 * @param amount
	 * 		The y vector that will move this point.
	 */
	public void incrementY(double amount)
	{
		this.y += amount;
	}

	/**
	 * Reverses the direction of the y vector. This is mainly used when the
	 * point object is being treated as a vector.
	 */
	public void reverseY()
	{
		this.y = -this.getY();
	}

	/**
	 * Applies the given vector on this point.
	 *
	 * @param vector
	 * 		Vector that will determine by how much to move this point.
	 *
	 * @return The instance of the point after it has been moved.
	 */
	public Point move(Point vector)
	{
		this.incrementX(vector.getX());
		this.incrementY(vector.getY());
		return this;
	}

	/**
	 * Applies the given vector on this point.
	 *
	 * @param x
	 * 		The x value by which incrementation will be done.
	 * @param y
	 * 		The y value by which incrementation will be done.
	 *
	 * @return The instance of the point after it has been moved.
	 */
	public Point move(double x, double y)
	{
		this.incrementX(x);
		this.incrementY(y);
		return this;
	}

	/**
	 * If this object is being treated as a vector, this function will reverse
	 * the vector.
	 */
	public void reverse()
	{
		this.reverseX();
		this.reverseY();
	}

	/**
	 * Simple changes location of this point to specified location.
	 *
	 * @param x
	 * 		The new x coordinate of this point.
	 * @param y
	 * 		The new y coordinate of this point.
	 */
	public void changeLocation(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Simple changes location of this point to specified Point. Only values get
	 * copied over but not the memory location.
	 *
	 * @param point
	 * 		The new coordinates of this point.
	 */
	public void changeLocation(Point point)
	{
		this.changeLocation(point.getX(), point.getY());
	}

	/**
	 * Creates a new Point that has the exact same coordinates as this point,
	 * but different memory location.
	 *
	 * @return Point that is the same as this point but does not share location.
	 */
	public Point copy()
	{
		return new Point(this.getX(), this.getY());
	}

	/**
	 * Returns a string representation tha explains everything about a given
	 * Object.
	 *
	 * @return String which contains all the info about the object.
	 */
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Coordinates:\n");
		sb.append("x: |").append(getX()).append("|");
		sb.append(" y: |").append(getY()).append("|");
		return sb.toString();
	}

	/**
	 * Compares this point with a given object. You do realize hopefully that
	 * the given object should at least be of type point right?
	 *
	 * @param object
	 * 		The object to which to compare this point to.
	 *
	 * @return True if this object is the same point, false otherwise.
	 */
	@Override
	public boolean equals(Object object)
	{
		if (object instanceof Point)
		{
			Point point = (Point) object;
			return (MathEngine.equals(this.getX(), point.getX()) &&
					MathEngine.equals(this.getY(), point.getY()));
		}
		return false;
	}
}
