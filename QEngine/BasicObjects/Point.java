package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

/**
 * Basic Point. Contains x and y coordinate which are both double. Can represent vectors.
 */
public class Point
{
	/**
	 * Well any point on 2D plane needs two points.
	 */
	private double x, y;

	/**
	 * We can not have a Point without coordinates, so let's specify them.
	 * @param x The x coordinate of the point.
	 * @param y The y coordinate of the point.
	 */
	public Point(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Tells you the coordinate of this point on x axis.
	 * @return The x coordinate of this point.
	 */
	public double getX()
	{
		return x;
	}

	/**
	 * Sets the x coordinate of this point to specified value.
	 * @param x What the new x coordinate should be.
	 */
	public void setX(double x)
	{
		this.x = x;
	}

	/**
	 * Tells you the y coordinate of this point.
	 * @return The y coordinate of this point.
	 */
	public double getY()
	{
		return y;
	}

	/**
	 * Sets the y coordinate value of this point to what you tell it to.
	 * @param y What you would like the new y coordinate to be.
	 */
	public void setY(double y)
	{
		this.y = y;
	}

	/**
	 * Increments the x coordinate of this point by what you want.
	 * @param amount The vector that will affect the x coordinate of this point.
	 */
	public void incrementX(double amount)
	{
		this.x += amount;
	}

	/**
	 * Moves this point's y value by the given vector.
	 * @param amount The y vector that will move this point.
	 */
	public void incrementY(double amount)
	{
		this.y += amount;
	}

	/**
	 * Applies the given vector on this point.
	 * @param vector Vector that will determine by how much to move this point.
	 */
	public void move(Point vector)
	{
		incrementX(vector.getX());
		incrementY(vector.getY());
	}

	/**
	 * Simple changes location of this point to specified location.
	 * @param x The new x coordinate of this point.
	 * @param y The new y coordinate of this point.
	 */
	public void changeLocation(double x, double y)
	{
		this.setX(x);
		this.setY(y);
	}
}
