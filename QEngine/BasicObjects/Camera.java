package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicSprite.BPlatform;
import BasicSprite.Platform;
import logic.CollisionEngine;
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
	 */
	public Camera()
	{
		super();
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

	private void drawBox(Graphics2D g, Box box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX() - this.getX(),
				box.getY() - this.getY(),
				box.getWidth(),
				box.getHeight());
	}

	private void drawBBox(Graphics2D g, BBox box)
	{
		this.drawBox(g, box);
		g.setColor(Color.GRAY);
		g.drawRect(box.getLeftX() - this.getX(),
				box.getTopY() - this.getY(),
				box.getRightX() - box.getLeftX(),
				box.getBottomY() - box.getTopY());
	}

	private void drawMBox(Graphics2D g, MBox box)
	{
		this.drawBBox(g, box);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(box.getCenterX() - this.getX(),
				box.getCenterY() - this.getY(),
				(int) (box.getCenterX() + box.getXVector() * 5 - this.getX()),
				(int) (box.getCenterY() + box.getYVector() * 5 - this.getY()));
	}

	private void drawBall(Graphics2D g, Ball ball)
	{
		g.setColor(Color.BLACK);
		g.drawOval(ball.getX() - ball.getRadius() - this.getX(),
				ball.getY() - ball.getRadius() - this.getY(),
				ball.getDiameter(),
				ball.getDiameter());
		g.drawOval(ball.getCenterX() - 1 - this.getX(),
				this.getCenterY() - 1 - this.getY(),
				3,
				3);
	}

	private void drawBBall(Graphics2D g, BBall ball)
	{
		this.drawBall(g, ball);
		g.setColor(Color.GRAY);
		g.drawOval(ball.getLeftX(),
				ball.getTopY(),
				ball.getDiameter() - ball.getOffset() * 2,
				ball.getDiameter() - ball.getOffset() * 2);
	}

	private void drawMBall(Graphics2D g, MBall ball)
	{
		this.drawBBall(g, ball);
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(ball.getCenterX(),
				ball.getCenterY(),
				(int) (ball.getCenterX() + ball.getXVector() * 5),
				(int) (ball.getCenterY() + ball.getYVector() * 5));
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
		if (CollisionEngine.collision(this, box))
		{
			this.drawBox(g, box);
		}
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
		if (CollisionEngine.collision(this, box))
		{
			this.drawBBox(g, box);
		}
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
		if (CollisionEngine.collision(this, box))
		{
			this.drawMBox(g, box);
		}
	}

	public void draw(Graphics2D g, Ball ball)
	{
		if (CollisionEngine.collision(this, ball))
		{
			this.drawBall(g, ball);
		}
	}

	public void draw(Graphics2D g, BBall ball)
	{
		if (CollisionEngine.collision(this, ball))
		{
			this.drawBBall(g, ball);
		}
	}

	public void draw(Graphics2D g, MBall ball)
	{
		if (CollisionEngine.collision(this, ball))
		{
			this.drawMBall(g, ball);
		}
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
}
