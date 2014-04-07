package basicObjects.shapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Point;

/**
 * A basic ball that has a bounding box around it. It is not oval, so remember that.
 */
public class BBall extends Ball
{
	/**
	 * A bounding ball has vertical and horizontal offsets.
	 */
	private int offset;

	/**
	 * A constructor for the bounding box BBox extends Box so it has same properties but has offsets which will be used
	 * in collision detection.
	 */
	public BBall()
	{
		super();
		this.offset = 0;
	}

	/**
	 * Returns the offset of the ball. Since it is a round circular ball, then offset is constant irregardless of
	 * direction.
	 *
	 * @return Offset of this ball.
	 */
	public int getOffset()
	{
		return this.offset;
	}

	/**
	 * Sets the offset of this ball to given value. However since it is a ball, offset will always be the same.
	 *
	 * @param offset
	 * 		The new offset of this ball.
	 */
	public void setOffset(int offset)
	{
		this.offset = offset;
	}

	/**
	 * Returns you the radius of this BBall with the offset considered.
	 *
	 * @return Radius minus the offset of this ball.
	 */
	public int getOffsetRadius()
	{
		return this.getRadius() - this.getOffset();
	}

	/**
	 * Returns the object's left x coordinate with offset, is used for collision detection checking.
	 *
	 * @return the object's left x coordinate with offset.
	 */
	@Override
	public int getLeftX()
	{
		return (this.getX() - this.getOffsetRadius());
	}

	/**
	 * Returns the object's right x coordinate with offset, is used for collision detection checking.
	 *
	 * @return the object's right x coordinate(which is x+width) with offset.
	 */
	@Override
	public int getRightX()
	{
		return (this.getX() + this.getOffsetRadius());
	}

	/**
	 * Returns the object's top y coordinate with offset, is used for collision detection checking.
	 *
	 * @return the object's top y coordinate.
	 */
	@Override
	public int getTopY()
	{
		return (this.getY() - this.getOffsetRadius());
	}

	/**
	 * Returns the object's bottom y coordinate with offset, is used for collision detection checking.
	 *
	 * @return the object's bottom y coordinate(which is y+height) with offset.
	 */
	@Override
	public int getBottomY()
	{
		return (this.getY() + this.getOffsetRadius());
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
	 * Returns This object's box object as a new object. Simply calls copy of the parent class.
	 *
	 * @return new Box which is part of this Bounding Box object.
	 */
	public Ball getBall()
	{
		return super.copy();
	}

	/**
	 * Returns a new copy of this BoundingBox.
	 *
	 * @return A new Bounding Box object which has same values as this bounding box object.
	 */
	@Override
	public BBall copy()
	{
		BBall ball = new BBall();
		ball.setRadius(this.getRadius());
		ball.setCoordinates(this.getCenter().copy());
		ball.setOffset(this.getOffset());
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
		sb.append("Offset: |").append(getOffset()).append("|");
		return sb.toString();
	}
}
