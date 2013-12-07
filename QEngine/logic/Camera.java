package logic;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.*;
import BasicObjects.Point;
import BasicSprite.BPlatform;
import BasicSprite.Platform;
import platformer.BasicCharacter;
import platformer.BasicSprite.MCharacter;
import platformer.ExtendedShapes.MBControls;
import platformer.ExtendedShapes.MControls;
import platformer.MovingShapes.MBBox;
import platformer.MovingShapes.MBox;

import java.awt.*;


/**
 * Class for attaching camera to the game screen.
 */
public class Camera
{
	private Point coordinates;
	private Dimensions boundaries;
	private Point center;

	/**
	 * We can't have a camera without few parameters first right?
	 * So let's initialize.
	 *
	 * @param leftRightBoundary The left and right boundary which has to be passed for the camera to start moving.
	 * @param topBottomBoundary The up and down boundary to pass for the camera to move up or down.
	 * @param centerX           Center x coordinate of the camera.
	 * @param centerY           Center y coordinate of the camera.
	 */
	public Camera(int leftRightBoundary, int topBottomBoundary, int centerX, int centerY)
	{
		this.boundaries = new Dimensions(topBottomBoundary, leftRightBoundary);
		this.center = new Point(centerX, centerY);
		this.coordinates = new Point(0, 0);
	}

	/**
	 * In case if you ever need to change boundaries from what you originally set them to.
	 *
	 * @param leftRightBoundary The left and right boundary after which the camera will move.
	 * @param topBottomBoundary The top and bottom boundary after which camera will also move.
	 */
	public void setBoundaries(int leftRightBoundary, int topBottomBoundary)
	{
		this.boundaries.setHorizontal(leftRightBoundary);
		this.boundaries.setVertical(topBottomBoundary);
	}

	/**
	 * Once again if you ever need to change the center of the camera.
	 *
	 * @param x Center x coordinate of the camera.
	 * @param y Center y coordinate of the camera.
	 */
	public void setCenter(int x, int y)
	{
		this.center.changeLocation(x, y);
	}

	/**
	 * Update camera position based on the coordinates which it should be looking at.
	 *
	 * @param x The x coordinate of the object.
	 * @param y Teh y coordinate of the object.
	 */
	public void updateCamera(int x, int y)
	{
		int horizontalDifference = this.getX() - x;
		int verticalDifference = this.getY() - y;

		// RIGHT SIDE IS PASSED
		if (horizontalDifference < -this.getHorizontalBoundary())
		{
			this.incrementX(x - this.getRightSide());
		}
		// LEFT SIDE IS PASSED
		else if (horizontalDifference > this.getHorizontalBoundary())
		{
			this.incrementX(x - this.getLeftSide());
		}

		// DOWN IS PASSED
		if (verticalDifference < -this.getVerticalBoundary())
		{
			this.incrementY(y - this.getBottomSide());
		}
		// UP IS PASSED
		else if (verticalDifference > this.getVerticalBoundary())
		{
			this.incrementY(y - this.getTopSide());
		}
	}

	/**
	 * Returns the current x coordinate of this camera.
	 *
	 * @return The center x coordinate of this camera.
	 */
	public int getX()
	{
		return (int)this.coordinates.getX();
	}

	/**
	 * Returns the current y coordinate of this camera.
	 *
	 * @return The center y coordinate of this camera.
	 */
	public int getY()
	{
		return (int)this.coordinates.getY();
	}

	/**
	 * Changes the x coordinate of this camera from what it was.
	 *
	 * @param x The new x coordinate of the camera.
	 */
	public void setX(int x)
	{
		this.coordinates.setX(x);
	}

	/**
	 * Changes the y coordinate of this camera from what it was.
	 *
	 * @param y The new center y coordinate of the camera.
	 */
	public void setY(int y)
	{
		this.coordinates.setY(y);
	}

	/**
	 * Increment the center x coordinate of the camera by the given amount.
	 *
	 * @param amount The amount by which you would like to shift camera by horizontally.
	 */
	public void incrementX(int amount)
	{
		this.coordinates.incrementX(amount);
	}

	/**
	 * Increment the center y coordinate of the camera by the given amount.
	 *
	 * @param amount The amount by which you would like to shift camera by vertically.
	 */
	public void incrementY(int amount)
	{
		this.coordinates.incrementY(amount);
	}

	/**
	 * Gives you the horizontal boundary of this camera.
	 *
	 * @return horizontal boundary of this camera.
	 */
	public int getHorizontalBoundary()
	{
		return this.boundaries.getHorizontal();
	}

	/**
	 * Gives you the vertical boundary of this camera.
	 *
	 * @return vertical boundary of this camera.
	 */
	public int getVerticalBoundary()
	{
		return this.boundaries.getVertical();
	}

	/**
	 * The center x coordinate of this camera.
	 *
	 * @return The center x coordinate of this camera.
	 */
	public int getCenterX()
	{
		return (int)this.center.getX();
	}

	/**
	 * The center y coordinate of this camera.
	 *
	 * @return The center y coordinate of this camera.
	 */
	public int getCenterY()
	{
		return (int)this.center.getY();
	}

	/**
	 * The left side boundary of this camera, once it is exceeded do what you want.
	 *
	 * @return The left boundary of the camera.
	 */
	public int getLeftSide()
	{
		return this.getX() - this.getHorizontalBoundary();
	}

	/**
	 * The right side boundary of this camera, once it is exceeded do what you want.
	 *
	 * @return The right boundary of the camera.
	 */
	public int getRightSide()
	{
		return this.getX() + this.getHorizontalBoundary();
	}

	/**
	 * The top boundary of this camera, once it is exceeded do what you want (hint: move it up).
	 *
	 * @return The top boundary of the camera.
	 */
	public int getTopSide()
	{
		return this.getY() - this.getVerticalBoundary();
	}

	/**
	 * The bottom boundary of this camera, once it is exceeded do what you want.
	 *
	 * @return The bottom boundary of the camera.
	 */
	public int getBottomSide()
	{
		return this.getY() + this.getVerticalBoundary();
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, Box box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, BBox box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, MBox box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, MBBox box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, Platform box)
	{
		if (box.getImage() == null)
		{
			g.setColor(box.getColor());
			g.fillRect(box.getX() - this.getX() + this.getCenterX(),
					box.getY() - this.getY() + this.getCenterY(),
					box.getWidth(),
					box.getHeight());
		}
		else
			g.drawImage(box.getImage(),
					null,
					box.getX() - this.getX() + this.getCenterX(),
					box.getY() - this.getY() + this.getCenterY());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, BPlatform box)
	{
		g.drawImage(box.getImage(),
				null,
				box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, MCharacter box)
	{
		if (box.getImage() == null)
		{
			g.setColor(box.getColor());
			g.fillRect(box.getX() - this.getX() + this.getCenterX(),
					box.getY() - this.getY() + this.getCenterY(),
					box.getWidth(),
					box.getHeight());
		}
		else
			g.drawImage(box.getImage(),
					null,
					box.getX() - this.getX() + this.getCenterX(),
					box.getY() - this.getY() + this.getCenterY());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, MBControls box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 * WAY TOO MUCH COPY PASTING WAS DONE FOR THESE COMMENTS. Damn.
	 *
	 * @param g   the graphics to which to draw.
	 * @param box The box you would like drawn.
	 */
	public void draw(Graphics2D g, MControls box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws the character. There is very little logic here, main logic is in getImage done by Character.
	 *
	 * @param g         the graphics to which to draw to.
	 * @param character The character you want to be drawn.
	 */
	public void draw(Graphics2D g, BasicCharacter character)
	{
		g.drawImage(character.getImage(),
				null,
				character.getX() - this.getX() + this.getCenterX(),
				character.getY() - this.getY() + this.getCenterY());
	}

	public void draw(Graphics2D g, Item item)
	{
		g.drawImage(item.getImage(),
				null,
				item.getX() - this.getX() + this.getCenterX(),
				item.getY() - this.getY() + this.getCenterY());
	}
}
