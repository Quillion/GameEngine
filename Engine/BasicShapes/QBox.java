package BasicShapes;

/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Graphics2D;
import java.awt.Color;

/**
 * Box
 */
public class QBox
{
    private double x_, y_;
    private int width_, height_;

    /**
     * A constructor for the box
     * QBox does not contain any offsets, it is just a box
     * All the values are set to 0 on creation
     */
    public QBox()
    {
    	this.setX(0);
    	this.setY(0);
    	this.setWidth(0);
    	this.setHeight(0);
    }

    /**
     * Sets the value of x to whatever you choose to
     * @param x the value to which x will be set to
     */
    public void setX(double x)
    {
        this.x_ = x;
    }

    /**
     * Return x value of the object
     * I return int because it is easier to use for collision detection
     * @return returns the x value of the object
     */
    public int getX()
    {
        return (int)this.x_;
    }

    /**
     * Increment the object's x value, very useful for movement
     * @param x the value by which to increment x by
     */
    public void incrementX(double x)
    {
        this.x_ += x;
    }

    /**
     * Sets the value of y to whatever you choose to
     * @param y the value to which y will be set to
     */
    public void setY(double y)
    {
        this.y_ = y;
    }

    /**
     * Return y value of the object
     * I return int because it is easier to use for collision detection
     * @return returns the y value of the object
     */
    public int getY()
    {
        return (int)this.y_;
    }

    /**
     * Increment the object's y value, very useful for movement
     * @param y the value by which to increment y by
     */
    public void incrementY(double y)
    {
        this.y_ += y;
    }

    /**
     * Sets the width value to whatever you choose to
     * @param width the value to which objetc's width will be set to
     */
    public void setWidth(int width)
    {
        this.width_ = width;
    }

    /**
     * returns the object's width
     * @return the width of this object
     */
    public int getWidth()
    {
        return this.width_;
    }

    /**
     * Increments the object's width by a set amount
     * @param amount how much to increment this object's width by
     */
    public void incrementWidth(int amount)
    {
        this.width_ += amount;
    }

    /**
     * Sets the height value to whatever you choose to
     * @param height the value to which object's height will be set to
     */
    public void setHeight(int height)
    {
        this.height_ = height;
    }

    /**
     * returns the objetc's height
     * @return the width of this object
     */
    public int getHeight()
    {
        return this.height_;
    }

    /**
     * Increments the object's height by a set amount
     * @param amount how much to increment this object's height by
     */
    public void incrementHeight(int amount)
    {
        this.height_ += amount;
    }

    /**
     * returns the object's left x coordinate,
     * is used for collision detection checking
     * @return the object's left x coordinate
     */
    public int getLeftX()
    {
        return (int)this.x_;
    }

    /**
     * returns the object's right x coordinate,
     * is used for collision detection checking
     * @return the object's right x coordinate(which is x+widht)
     */
    public int getRightX()
    {
        return (this.getX() + this.getWidth());
    }

    /**
     * returns the object's top y coordinate,
     * is used for collision detection checking.
     * @return the object's top y coordinate.
     */
    public int getTopY()
    {
        return (int)this.y_;
    }

    /**
     * Returns the object's bottom y coordinate,
     * is used for collision detection checking.
     * @return the object's bottom y coordinate(which is y+height).
     */
    public int getBottomY()
    {
        return (this.getY() + this.getHeight());
    }

	/**
	 * Returns the object's center x coordinate.
	 * Is used for whatever you want, but it is costly.
	 * @return the object's center x coordinate.
	 */
	public int getCenterX()
	{
		return (this.getX() + this.getWidth()/2);
	}

	/**
	 * Returns the object's center y coordinate.
	 * Is used for whatever you want, but it is costly.
	 * @return the object's center y coordinate.
	 */
	public int getCenterY()
	{
		return (this.getY() + this.getHeight()/2);
	}

    /**
     * Draws the box into to the graphics passed,
     * box outline will be black color.
     * @param g graphics where the box will be drawn into.
     */
    public void drawBox(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    /**
     * Same as drawBox.
     * @param g graphics where the box will be drawn into.
     */
    public void draw(Graphics2D g)
    {
        this.drawBox(g);
    }
}