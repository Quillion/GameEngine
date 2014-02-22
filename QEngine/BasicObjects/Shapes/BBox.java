package BasicObjects.shapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import BasicObjects.Point;

import java.awt.*;

/**
 * A Box that contains a Bounding/Collision Box.
 */
public class BBox extends Box
{
	/**
	 * A bounding box has vertical and horizontal offsets.
	 */
	private Dimensions offsets;

	/**
	 * A constructor for the bounding box
	 * BBox extends Box so it has same properties
	 * but has offsets which will be used  in collision detection.
	 */
	public BBox()
	{
		super();
		this.offsets = new Dimensions(0, 0);
	}

	/**
	 * Sets the horizontal offset to whatever value is passed.
	 *
	 * @param offset value to which horizontal offset will be set to.
	 */
	public void setHorizontalOffset(int offset)
	{
		this.offsets.setWidth(offset);
	}

	/**
	 * Returns the horizontal offset of this object.
	 *
	 * @return horizontal offset of this object.
	 */
	public int getHorizontalOffset()
	{
		return this.offsets.getWidth();
	}

	/**
	 * Sets the vertical offset to whatever value is passed.
	 *
	 * @param offset value to which vertical offset will be set to.
	 */
	public void setVerticalOffset(int offset)
	{
		this.offsets.setHeight(offset);
	}

	/**
	 * Returns the vertical offset of this object.
	 *
	 * @return vertical offset of this object.
	 */
	public int getVerticalOffset()
	{
		return this.offsets.getHeight();
	}

	/**
	 * Sets the offset pf this Bounding Box to the newly given values.
	 *
	 * @param dimensions The new Dimension that will be this Bound Box's offsets.
	 */
	public void setOffsets(Dimensions dimensions)
	{
		this.offsets.setDimensions(dimensions);
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
		return (super.getLeftX() + this.getHorizontalOffset());
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
		return (super.getRightX() - this.getHorizontalOffset());
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
		return (super.getTopY() + this.getVerticalOffset());
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
		return (super.getBottomY() - this.getVerticalOffset());
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
	 * Draws the box into to the graphics passed,
	 * box outline will be black color.
	 * The bounding box will be gray color.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	@Override
	public void draw(Graphics2D g)
	{
		super.drawBox(g);
		g.setColor(Color.GRAY);
		g.drawRect(this.getLeftX(), this.getTopY(), this.getRightX() - this.getLeftX(), this.getBottomY() - this.getTopY());
	}

	/**
	 * Draws the box into to the graphics passed, box outline will be black color.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	@Override
	public void drawBox(Graphics2D g)
	{
		super.drawBox(g);
	}

	/**
	 * Returns This object's box object as a new object.
	 * Simply calls copy of the parent class.
	 *
	 * @return new Box which is part of this Bounding Box object.
	 */
	public Box getBox()
	{
		return super.copy();
	}

	/**
	 * Returns a new copy of this BoundingBox.
	 *
	 * @return A new Bounding Box object which has same values as this bounding box object.
	 */
	@Override
	public BBox copy()
	{
		BBox box = new BBox();
		box.setX(this.getX());
		box.setY(this.getY());
		box.setWidth(this.getWidth());
		box.setHeight(this.getHeight());
		box.setOffsets(this.offsets.copy());
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
		sb.append(super.toString()).append("\n");
		sb.append("Offset:\n");
		sb.append("vertical: |").append(getVerticalOffset()).append("|");
		sb.append(" horizontal: |").append(getHorizontalOffset()).append("|");
		return sb.toString();
	}
}
