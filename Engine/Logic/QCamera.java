package Logic;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Color;
import java.awt.Graphics2D;

import BasicShapes.QBBox;
import BasicShapes.QBox;
import BasicSprite.QBPlatform;
import BasicSprite.QMCharacter;
import BasicSprite.QPlatform;
import ExtendedShapes.QBMControls;
import ExtendedShapes.QMControls;
import MovingShapes.QBMBox;
import MovingShapes.QMBox;
import Platformer.BasicCharacter;

/**
 * Class for attaching camera to the game screen.
 */
public class QCamera
{
    private int x_, y_;
    private int horizontalBoundary_, verticalBoundary_;
	private int centerX_, centerY_;

	/**
	 * We can't have a camera without few parameters first right?
	 * So let's initialize.
	 * @param leftRightBoundary The left and right boundary which has to be passed for the camera to start moving.
	 * @param topBottomBoundary The up and down boundary to pass for the camera to move up or down.
	 * @param centerX Center x coordinate of the camera.
	 * @param centerY Center y coordinate of the camera.
	 */
    public QCamera(int leftRightBoundary, int topBottomBoundary, int centerX, int centerY)
    {
        this.setBoundaries(leftRightBoundary, topBottomBoundary);
		this.setCenter(centerX, centerY);
        this.setX(0);
        this.setY(0);
    }

	/**
	 * In case if you ever need to change boundaries from what you originally set them to.
	 * @param leftRightBoundary The left and right boundary after which the camera will move.
	 * @param topBottomBoundary The top and bottom boundary after which camera will also move.
	 */
    public void setBoundaries(int leftRightBoundary, int topBottomBoundary)
    {
        this.horizontalBoundary_ = leftRightBoundary;
        this.verticalBoundary_ = topBottomBoundary;
    }

	/**
	 * Once again if you ever need to change the center of the camera.
	 * @param x Center x coordinate of the camera.
	 * @param y Center y coordinate of the camera.
	 */
	public void setCenter(int x, int y)
	{
		this.centerX_ = x;
		this.centerY_ = y;
	}

	/**
	 * Update camera position based on the coordinates which it should be looking at.
	 * @param x The x coordinate of the object.
	 * @param y Teh y coordinate of the object.
	 */
    public void updateCamera(int x, int y)
    {
        int horizontalDifference = this.getX() - x;
        int verticalDifference = this.getY() - y;

		// RIGHT SIDE IS PASSED
        if(horizontalDifference < -this.getHorizontalBoundary())
        {
            this.incrementX(x - this.getRightSide());
        }
		// LEFT SIDE IS PASSED
        else if(horizontalDifference > this.getHorizontalBoundary())
        {
			this.incrementX(x - this.getLeftSide());
        }

		// DOWN IS PASSED
        if(verticalDifference < -this.getVerticalBoundary())
        {
            this.incrementY(y - this.getBottomSide());
        }
		// UP IS PASSED
        else if(verticalDifference > this.getVerticalBoundary())
        {
            this.incrementY(y - this.getTopSide());
        }
    }

	/**
	 * Returns the current x coordinate of this camera.
	 * @return The center x coordinate of this camera.
	 */
    public int getX()
    {
        return this.x_;
    }

	/**
	 * Returns the current y coordinate of this camera.
	 * @return The center y coordinate of this camera.
	 */
    public int getY()
    {
        return this.y_;
    }

	/**
	 * Changes the x coordinate of this camera from what it was.
	 * @param x The new x coordinate of the camera.
	 */
    public void setX(int x)
    {
        this.x_ = x;
    }

	/**
	 * Changes the y coordinate of this camera from what it was.
	 * @param y The new center y coordinate of the camera.
	 */
    public void setY(int y)
    {
        this.y_ = y;
    }

	/**
	 * Increment the center x coordinate of the camera by the given amount.
	 * @param amount The amount by which you would like to shift camera by horizontally.
	 */
    public void incrementX(int amount)
    {
        this.x_ += amount;
    }

	/**
	 * Increment the center y coordinate of the camera by the given amount.
	 * @param amount The amount by which you would like to shift camera by vertically.
	 */
    public void incrementY(int amount)
    {
        this.y_ += amount;
    }

	/**
	 * Gives you the horizontal boundary of this camera.
	 * @return horizontal boundary of this camera.
	 */
    public int getHorizontalBoundary()
    {
        return this.horizontalBoundary_;
    }

	/**
	 * Gives you the vertical boundary of this camera.
	 * @return vertical boundary of this camera.
	 */
    public int getVerticalBoundary()
    {
        return this.verticalBoundary_;
    }

	/**
	 * The center x coordinate of this camera.
	 * @return The center x coordinate of this camera.
	 */
	public int getCenterX()
	{
		return this.centerX_;
	}

	/**
	 * The center y coordinate of this camera.
	 * @return The center y coordinate of this camera.
	 */
	public int getCenterY()
	{
		return this.centerY_;
	}

	/**
	 * The left side boundary of this camera, once it is exceeded do what you want.
	 * @return The left boundary of the camera.
	 */
	public int getLeftSide()
	{
		return this.getX() - this.getHorizontalBoundary();
	}

	/**
	 * The right side boundary of this camera, once it is exceeded do what you want.
	 * @return The right boundary of the camera.
	 */
	public int getRightSide()
	{
		return this.getX() + this.getHorizontalBoundary();
	}

	/**
	 * The top boundary of this camera, once it is exceeded do what you want (hint: move it up).
	 * @return The top boundary of the camera.
	 */
	public int getTopSide()
	{
		return this.getY() - this.getVerticalBoundary();
	}

	/**
	 * The bottom boundary of this camera, once it is exceeded do what you want.
	 * @return The bottom boundary of the camera.
	 */
	public int getBottomSide()
	{
		return this.getY() + this.getVerticalBoundary();
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QBBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QMBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QBMBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QPlatform box)
    {
        if(box.getImage() == null)
        {
            g.setColor(box.getColor());
            g.fillRect(	box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY(),
						box.getWidth(),
						box.getHeight());
        }
        else
            g.drawImage(box.getImage(),
						null,
						box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QBPlatform box)
    {
		g.drawImage(box.getImage(),
					null,
					box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QMCharacter box)
    {
        if(box.getImage() == null)
        {
            g.setColor(box.getColor());
			g.fillRect(	box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY(),
						box.getWidth(),
						box.getHeight());
        }
        else
			g.drawImage(box.getImage(),
						null,
						box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QBMControls box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * WAY TOO MUCH COPY PASTING WAS DONE FOR THESE COMMENTS. Damn.
	 * @param g the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
    public void draw(Graphics2D g, QMControls box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

	/**
	 * Draws the character. There is very little logic here, main logic is in getImage done by Character.
	 * @param g the graphics to which to draw to.
	 * @param character The character you want to be drawn.
	 */
	public void draw(Graphics2D g, BasicCharacter character)
	{
		g.drawImage(character.getImage(),
					null,
					character.getX()-this.getX()+this.getCenterX(),
					character.getY()-this.getY()+this.getCenterY());
	}
}
