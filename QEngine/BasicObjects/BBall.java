package BasicObjects;

import java.awt.*;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

public class BBall extends Ball
{
	/**
	 * A bounding ball has vertical and horizontal offsets.
	 */
	private int offset;

	/**
	 * A constructor for the bounding box
	 * BBox extends Box so it has same properties
	 * but has offsets which will be used  in collision detection.
	 */
	public BBall()
	{
		super();
		this.offset = 0;
	}

	public int getOffset()
	{
		return offset;
	}

	public void setOffset(int offset)
	{
		this.offset = offset;
	}

	/**
	 * Returns the object's left x coordinate with offset,
	 * is used for collision detection checking.
	 *
	 * @return the object's left x coordinate with offset.
	 */
	@Override
	public int getLeftX()
	{
		return (super.getLeftX() + this.getOffset());
	}

	/**
	 * Returns the object's right x coordinate with offset,
	 * is used for collision detection checking.
	 *
	 * @return the object's right x coordinate(which is x+width) with offset.
	 */
	@Override
	public int getRightX()
	{
		return (super.getRightX() - this.getOffset());
	}

	/**
	 * Returns the object's top y coordinate with offset,
	 * is used for collision detection checking.
	 *
	 * @return the object's top y coordinate.
	 */
	@Override
	public int getTopY()
	{
		return (super.getTopY() + this.getOffset());
	}

	/**
	 * Returns the object's bottom y coordinate with offset,
	 * is used for collision detection checking.
	 *
	 * @return the object's bottom y coordinate(which is y+height) with offset.
	 */
	@Override
	public int getBottomY()
	{
		return (super.getBottomY() - this.getOffset());
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
	 * Draws the box into to the graphics passed, box outline will be black color.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	@Override
	public void draw(Graphics2D g)
	{
		this.drawBall(g);
		g.setColor(Color.GRAY);
		g.drawOval(this.getLeftX(), this.getTopY(), this.getDiameter() - this.offset * 2, this.getDiameter() - this.offset * 2);
	}

	/**
	 * Draws the box into to the graphics passed,
	 * box outline will be black color.
	 * The bounding box will be gray color.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	@Override
	public void drawBall(Graphics2D g)
	{
		super.draw(g);
	}

	/**
	 * Returns This object's box object as a new object.
	 * Simply calls copy of the parent class.
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
}
