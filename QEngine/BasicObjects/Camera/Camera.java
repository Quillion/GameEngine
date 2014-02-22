package BasicObjects.camera;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import BasicObjects.Item;
import BasicObjects.shapes.*;
import BasicSprite.BPlatform;
import BasicSprite.Platform;
import logic.CollisionEngine;
import platformer.BasicCharacter;
import platformer.BasicSprite.MCharacter;
import platformer.ExtendedShapes.MBControls;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Class for attaching camera to the game screen.
 */
public class Camera extends BBox
{
	private BufferedImage view;
	private Graphics2D g;

	/**
	 * We can't have a camera without few parameters first right?
	 * So let's initialize.
	 */
	public Camera()
	{
		super();
	}

	/**
	 * Sets the size of the camera to the given dimensions. Size is drawing area.
	 *
	 * @param size The new Size for the camera.
	 */
	@Override
	public void setSize(Dimensions size)
	{
		super.setSize(size);
		this.view = new BufferedImage(super.getWidth(), super.getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		this.g = (Graphics2D) (view.getGraphics());
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
			this.incrementX(x - this.getX() - this.getWidth() + this.getHorizontalOffset());
		}
		// LEFT SIDE IS PASSED
		else if (horizontalDifference < this.getHorizontalOffset())
		{
			this.incrementX(x - this.getX() - this.getHorizontalOffset());
		}

		// DOWN IS PASSED
		if (verticalDifference > (this.getHeight() - this.getVerticalOffset()))
		{
			this.incrementY(y - this.getY() - this.getHeight() + this.getVerticalOffset());
		}
		// UP IS PASSED
		else if (verticalDifference < this.getVerticalOffset())
		{
			this.incrementY(y - this.getY() - this.getVerticalOffset());
		}
	}

	/**
	 * Draws the given point to the location relative to camera.
	 *
	 * @param point Which point you want drawn.
	 */
	private void drawPoint(BasicObjects.Point point)
	{
		g.setColor(Color.GREEN);
		g.drawRect((int) (point.getX() - 1) - this.getX(), (int) (point.getY() - 1) - this.getY(), 2, 2);
	}

	/**
	 * Draws the box relative to where the camera is.
	 *
	 * @param box Box which you want drawn relative to camera.
	 */
	private void drawBox(Box box)
	{
		g.setColor(Color.BLUE);
		g.drawRect(box.getX() - this.getX(),
				box.getY() - this.getY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws box with a collision box relative to the camera.
	 *
	 * @param box Bounding box that you want drawn relative to camera.
	 */
	private void drawBBox(BBox box)
	{
		this.drawBox(box);
		g.setColor(Color.GREEN);
		g.drawRect(box.getLeftX() - this.getX(),
				box.getTopY() - this.getY(),
				box.getRightX() - box.getLeftX(),
				box.getBottomY() - box.getTopY());
	}

	/**
	 * Draws box with vectors relative to the camera.
	 *
	 * @param box Moving box that you wanr drawn relative to the camera.
	 */
	private void drawMBox(MBox box)
	{
		this.drawBBox(box);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(box.getCenterX() - this.getX(),
				box.getCenterY() - this.getY(),
				(int) (box.getCenterX() + box.getXVector() * 5 - this.getX()),
				(int) (box.getCenterY() + box.getYVector() * 5 - this.getY()));
	}

	/**
	 * Draws ball relative to the camera.
	 *
	 * @param ball The ball that you want drawn in the camera.
	 */
	private void drawBall(Ball ball)
	{
		g.setColor(Color.BLUE);
		g.drawOval(ball.getX() - ball.getRadius() - this.getX(),
				ball.getY() - ball.getRadius() - this.getY(),
				ball.getDiameter(),
				ball.getDiameter());
		g.drawOval(ball.getCenterX() - 1 - this.getX(),
				this.getCenterY() - 1 - this.getY(),
				3,
				3);
	}

	/**
	 * Draws bounding ball relative to this camera.
	 *
	 * @param ball The bounding ball that you want drawn relative to this camera.
	 */
	private void drawBBall(BBall ball)
	{
		this.drawBall(ball);
		g.setColor(Color.GREEN);
		g.drawOval(ball.getLeftX(),
				ball.getTopY(),
				ball.getDiameter() - ball.getOffset() * 2,
				ball.getDiameter() - ball.getOffset() * 2);
	}

	/**
	 * Draws ball with vectors relative to this camera.
	 *
	 * @param ball Moving ball that you want drawn within this camera.
	 */
	private void drawMBall(MBall ball)
	{
		this.drawBBall(ball);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(ball.getCenterX(),
				ball.getCenterY(),
				(int) (ball.getCenterX() + ball.getXVector() * 5),
				(int) (ball.getCenterY() + ball.getYVector() * 5));
	}

	/**
	 * Draws the given point to the location relative to camera.
	 *
	 * @param point Which point you want drawn.
	 */
	public void draw(BasicObjects.Point point)
	{
		if (CollisionEngine.collision(this, point))
		{
			this.drawPoint(point);
		}
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param box The box you would like drawn.
	 */
	public void draw(Box box)
	{
		if (CollisionEngine.collision(this, box))
		{
			this.drawBox(box);
		}
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param box The box you would like drawn.
	 */
	public void draw(BBox box)
	{
		if (CollisionEngine.collision(this, box))
		{
			this.drawBBox(box);
		}
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param box The box you would like drawn.
	 */
	public void draw(MBox box)
	{
		if (CollisionEngine.collision(this, box))
		{
			this.drawMBox(box);
		}
	}

	/**
	 * Draws ball relative to the camera.
	 *
	 * @param ball The ball that you want drawn in the camera.
	 */
	public void draw(Ball ball)
	{
		if (CollisionEngine.collision(this, ball))
		{
			this.drawBall(ball);
		}
	}

	/**
	 * Draws bounding ball relative to this camera.
	 *
	 * @param ball The bounding ball that you want drawn relative to this camera.
	 */
	public void draw(BBall ball)
	{
		if (CollisionEngine.collision(this, ball))
		{
			this.drawBBall(ball);
		}
	}

	/**
	 * Draws ball with vectors relative to this camera.
	 *
	 * @param ball Moving ball that you want drawn within this camera.
	 */
	public void draw(MBall ball)
	{
		if (CollisionEngine.collision(this, ball))
		{
			this.drawMBall(ball);
		}
	}

	/**
	 * Draws given box with its coordinates shifted, so that it is in accordance with the camera.
	 * Much better than default method of box's drawing with slightly higher cost.
	 *
	 * @param box The box you would like drawn.
	 */
	public void draw(Platform box)
	{
		// if platform has no image then we will draw wire frame
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
	 * @param box The box you would like drawn.
	 */
	public void draw(BPlatform box)
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
	 * @param box The box you would like drawn.
	 */
	public void draw(MCharacter box)
	{
		// if character has no image then we will draw wire frame
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
	 * @param box The box you would like drawn.
	 */
	public void draw(MBControls box)
	{
		g.setColor(Color.BLUE);
		g.drawRect(box.getX() - this.getX() + this.getCenterX(),
				box.getY() - this.getY() + this.getCenterY(),
				box.getWidth(),
				box.getHeight());
	}

	/**
	 * Draws the character. There is very little logic here, main logic is in getImage done by Character.
	 *
	 * @param character The character you want to be drawn.
	 */
	public void draw(BasicCharacter character)
	{
		g.drawImage(character.getImage(),
				null,
				character.getX() - this.getX() + this.getCenterX(),
				character.getY() - this.getY() + this.getCenterY());
	}

	/**
	 * Draws the given item relative to the camera.
	 *
	 * @param item Draws this item relative to this camera.
	 */
	public void draw(Item item)
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
		g.drawImage(view, null, 0, 0);
	}

	/**
	 * Draws the camera picture onto the screen, and then clears it out.
	 *
	 * @param g Graphics onto which to draw camera's view.
	 */
	public void drawClear(Graphics2D g)
	{
		g.drawImage(view, null, 0, 0);
		this.g.clearRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Draws the camera's view to the given graphics, and the colors the whole camera white.
	 *
	 * @param g Graphics onto which to draw camera's view.
	 */
	public void drawWhite(Graphics2D g)
	{
		g.drawImage(view, null, 0, 0);
		this.g.setColor(Color.WHITE);
		this.g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Well the camera will always be fixed at location 0, 0(not really).
	 * So we will just draw it there.
	 *
	 * @param g graphics where the box will be drawn into.
	 * @param x the x coordinate where you want to draw this camera onto the given graphics.
	 * @param y the y coordinate where you want to draw this camera onto the given graphics.
	 */
	public void draw(Graphics2D g, int x, int y)
	{
		g.drawImage(view, null, x, y);
	}

	/**
	 * Draws camera's view on the given graphics at the specified location.
	 * This will allow you to draw camera for multiple players or with an offset,
	 * or you can create shaking effect. After drawing camera's image is cleared.
	 *
	 * @param g The graphics onto which camera's view will be drawn.
	 * @param x The x coordinate on which to draw camera.
	 * @param y The y coordinate on which to draw camera.
	 */
	public void drawClear(Graphics2D g, int x, int y)
	{
		g.drawImage(view, null, x, y);
		this.g.clearRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Draws camera's view on the given graphics at the specified location.
	 * This will allow you to draw camera for multiple players or with an offset,
	 * or you can create shaking effect. After drawing camera's image is colored white.
	 *
	 * @param g The graphics onto which camera's view will be drawn.
	 * @param x The x coordinate on which to draw camera.
	 * @param y The y coordinate on which to draw camera.
	 */
	public void drawWhite(Graphics2D g, int x, int y)
	{
		g.drawImage(view, null, x, y);
		this.g.setColor(Color.WHITE);
		this.g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Well the camera will always be fixed at location 0, 0(not really).
	 * So we will just draw it there.
	 */
	public void drawCamera()
	{
		this.g.setColor(Color.CYAN);
		this.g.drawRect(0, 0, this.getWidth(), this.getHeight());
		this.g.setColor(Color.GREEN);
		this.g.drawRect(this.getHorizontalOffset(),
				this.getVerticalOffset(),
				this.getWidth() - this.getHorizontalOffset() * 2,
				this.getHeight() - this.getVerticalOffset() * 2);
	}

	/**
	 * Returns the object's left x coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the object's left x coordinate.
	 */
	@Override
	public int getLeftX()
	{
		return (this.getX() - this.getHorizontalOffset());
	}

	/**
	 * Returns the object's right x coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the object's right x coordinate(which is x+width).
	 */
	@Override
	public int getRightX()
	{
		return (this.getX() + this.getWidth() + this.getHorizontalOffset());
	}

	/**
	 * Returns the object's top y coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the object's top y coordinate.
	 */
	@Override
	public int getTopY()
	{
		return (this.getY() - this.getVerticalOffset());
	}

	/**
	 * Returns the object's bottom y coordinate,
	 * is used for collision detection checking.
	 *
	 * @return the object's bottom y coordinate(which is y+height).
	 */
	@Override
	public int getBottomY()
	{
		return (this.getY() + this.getHeight() + this.getVerticalOffset());
	}

	/**
	 * Gives you the top left corner of the box.
	 *
	 * @return Point which represents top left corner of the box.
	 */
	@Override
	public BasicObjects.Point getTopLeft()
	{
		return new BasicObjects.Point(this.getLeftX(), this.getTopY());
	}

	/**
	 * Gives you the top right corner of the box.
	 *
	 * @return Point which represents top right corner of the box.
	 */
	@Override
	public BasicObjects.Point getTopRight()
	{
		return new BasicObjects.Point(this.getRightX(), this.getTopY());
	}

	/**
	 * Gives you the bottom left corner of the box.
	 *
	 * @return Point which represents bottom left corner of the box.
	 */
	@Override
	public BasicObjects.Point getBottomLeft()
	{
		return new BasicObjects.Point(this.getLeftX(), this.getBottomY());
	}

	/**
	 * Gives you the bottom right corner of the box.
	 *
	 * @return Point which represents bottom right corner of the box.
	 */
	@Override
	public BasicObjects.Point getBottomRight()
	{
		return new BasicObjects.Point(this.getRightX(), this.getBottomY());
	}
}
