package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicSprite.BPlatform;
import BasicSprite.Platform;
import platformer.BasicCharacter;
import platformer.BasicSprite.MCharacter;
import platformer.ExtendedShapes.MBControls;

import java.awt.*;


/**
 * Class for attaching camera to the game screen.
 */
public class Camera extends BBox
{
	/**
	 * We can't have a camera without few parameters first right?
	 * So let's initialize.
	 *
	 * @param leftRightBoundary The left and right boundary which has to be passed for the camera to start moving.
	 * @param topBottomBoundary The up and down boundary to pass for the camera to move up or down.
	 */
	public Camera(int leftRightBoundary, int topBottomBoundary)
	{
		super();
		setOffsets(new Dimensions(leftRightBoundary, topBottomBoundary));
	}

	/**
	 * Update camera position based on the coordinates which it should be looking at.
	 *
	 * @param x The x coordinate of the object.
	 * @param y Teh y coordinate of the object.
	 */
	public void updateCamera(int x, int y)
	{
		int horizontalDifference = x - this.getX();
		int verticalDifference = y - this.getY();

		// RIGHT SIDE IS PASSED
		if (horizontalDifference > (this.getWidth() - this.getHorizontalOffset()))
		{
			this.incrementX(x - this.getRightX());
		}
		// LEFT SIDE IS PASSED
		else if (horizontalDifference < this.getHorizontalOffset())
		{
			this.incrementX(x - this.getLeftX());
		}

		// DOWN IS PASSED
		if (verticalDifference > (this.getHeight() - this.getVerticalOffset()))
		{
			this.incrementY(y - this.getBottomY());
		}
		// UP IS PASSED
		else if (verticalDifference < this.getVerticalOffset())
		{
			this.incrementY(y - this.getTopY());
		}
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
		g.drawRect(box.getX() - this.getX(),
				box.getY() - this.getY(),
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
		g.drawRect(box.getX() - this.getX(),
				box.getY() - this.getY(),
				box.getWidth(),
				box.getHeight());
		g.setColor(Color.GRAY);
		g.drawRect(box.getLeftX() - this.getX(),
				box.getTopY() - this.getY(),
				box.getRightX() - box.getLeftX(),
				box.getBottomY() - box.getTopY());
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
		g.drawRect(box.getX() - this.getX(),
				box.getY() - this.getY(),
				box.getWidth(),
				box.getHeight());
		g.setColor(Color.GRAY);
		g.drawRect(box.getLeftX() - this.getX(),
				box.getTopY() - this.getY(),
				box.getRightX() - box.getLeftX(),
				box.getBottomY() - box.getTopY());
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(box.getCenterX() - this.getX(),
				box.getCenterY() - this.getY(),
				(int) (box.getCenterX() + box.getXVector() * 5 - this.getX()),
				(int) (box.getCenterY() + box.getYVector() * 5 - this.getY()));
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
		{
			g.drawImage(box.getImage(),
					null,
					box.getX() - this.getX() + this.getCenterX(),
					box.getY() - this.getY() + this.getCenterY());

		}
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
		{
			g.drawImage(box.getImage(),
					null,
					box.getX() - this.getX() + this.getCenterX(),
					box.getY() - this.getY() + this.getCenterY());
		}
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

	/**
	 * Well the camera will always be fixed at location 0, 0(not really).
	 * So we will just draw it there.
	 *
	 * @param g graphics where the box will be drawn into.
	 */
	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.GRAY);
		g.drawRect(this.getHorizontalOffset(),
				this.getVerticalOffset(),
				this.getWidth() - this.getHorizontalOffset() * 2,
				this.getHeight() - this.getVerticalOffset() * 2);
	}
}
