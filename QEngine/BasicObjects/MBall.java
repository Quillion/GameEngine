package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

public class MBall extends Ball
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
	 * Gives you a Point that represents the vector of this character.
	 *
	 * @return Vector of this given character.
	 */
	public Point getVector()
	{
		return this.vector;
	}

	/**
	 * Applies this character's vector to itself and moves it by the vector's value.
	 */
	public void move()
	{
		this.move(this.vector);
	}
}
