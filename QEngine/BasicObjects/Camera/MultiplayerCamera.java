package BasicObjects.camera;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import BasicObjects.shapes.*;
import abstracts.Shape;
import logic.MathEngine;

import java.awt.*;

/**
 * This camera will work for two players.
 * It has four modes of keeping track of the players.
 * You can easily follow and copy this code for three or four players.
 */
public class MultiplayerCamera
{
	public enum Mode
	{
		One, Vertical, Horizontal, Smart
	}

	private Mode mode;
	private Mode subMode;

	private FollowingCamera horizontal1;
	private FollowingCamera horizontal2;
	private FollowingCamera vertical1;
	private FollowingCamera vertical2;

	private Camera camera;

	private Shape player1, player2;

	private Dimensions size;

	/**
	 * Basic constructor. Since this is a two player camera,
	 * then we should provide two players accordingly.
	 *
	 * @param player1 First player, the one who will be on top or the left, depending on camera mode.
	 * @param player2 Second player, the one who will be on right or bottom, depending on camera mode.
	 */
	public MultiplayerCamera(Shape player1, Shape player2)
	{
		this.player1 = player1;
		this.player2 = player2;

		this.horizontal1 = new FollowingCamera(this.player1);
		this.horizontal2 = new FollowingCamera(this.player2);
		this.vertical1 = new FollowingCamera(this.player1);
		this.vertical2 = new FollowingCamera(this.player2);

		this.camera = new Camera();

		this.mode = Mode.Horizontal;
		this.subMode = Mode.One;
		this.size = new Dimensions(0, 0);
	}

	/**
	 * Sets the camera mode to one of the given modes.
	 *
	 * @param mode Which mode the camera should operate in.
	 */
	public void setMode(Mode mode)
	{
		this.mode = mode;
	}

	/**
	 * Sets the size of the given camera.
	 *
	 * @param size    The size of the screen itself, or camera view.
	 * @param offsets The box within which the player will be contained.
	 */
	public void setSize(Dimensions size, Dimensions offsets)
	{
		this.size = size;

		// SETTING THE SIZE
		this.horizontal1.setSize(new Dimensions(this.size.getWidth(), this.size.getHeight() / 2));
		this.horizontal2.setSize(new Dimensions(this.size.getWidth(), this.size.getHeight() / 2));
		this.vertical1.setSize(new Dimensions(this.size.getWidth() / 2, this.size.getHeight()));
		this.vertical2.setSize(new Dimensions(this.size.getWidth() / 2, this.size.getHeight()));
		this.camera.setSize(this.size);

		// SETTING THE OFFSET
		this.horizontal1.setOffsets(new Dimensions(offsets.getWidth(), offsets.getHeight() / 2));
		this.horizontal2.setOffsets(new Dimensions(offsets.getWidth(), offsets.getHeight() / 2));
		this.vertical1.setOffsets(new Dimensions(offsets.getWidth() / 2, offsets.getHeight()));
		this.vertical2.setOffsets(new Dimensions(offsets.getWidth() / 2, offsets.getHeight()));
		this.camera.setOffsets(new Dimensions(offsets.getWidth(), offsets.getHeight()));
	}

	/**
	 * Update camera position based on the character's coordinates which it should be looking at.
	 */
	public void updateCamera()
	{
		// UPDATE THE HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.updateCamera();
			this.horizontal2.updateCamera();
		}
		// UPDATE THE VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.updateCamera();
			this.vertical2.updateCamera();
		}
		// UPDATE ONE CENTRALIZED CAMERA
		else if (this.mode.equals(Mode.One))
		{
			this.camera.updateCamera(
					(this.player1.getCenterX() + this.player2.getCenterX()) / 2,
					(this.player1.getCenterY() + this.player2.getCenterY()) / 2);
		}
		// CHOOSE WHICH MODE WE ARE IN
		else if (this.mode.equals(Mode.Smart))
		{
			// FIND DISTANCE BETWEEN TWO CHARACTERS AND SEE IF THEY ARE CLOSE TO EACH OTHER
			double x = Math.abs(MathEngine.deltaX(this.player1.getCenter(), this.player2.getCenter()));
			double y = Math.abs(MathEngine.deltaY(this.player1.getCenter(), this.player2.getCenter()));
			double cameraX = camera.getWidth() - camera.getHorizontalOffset() * 2;
			double cameraY = camera.getHeight() - camera.getVerticalOffset() * 2;
			// THEY ARE CLOSE TO EACH OTHER
			if ((x < cameraX) && (y < cameraY))
			{
				this.subMode = Mode.One;
				this.camera.updateCamera(
						(this.player1.getCenterX() + this.player2.getCenterX()) / 2,
						(this.player1.getCenterY() + this.player2.getCenterY()) / 2);
			}
			// THEY ARE TOO FAR AWAY
			else
			{
				// THEY ARE HORIZONTALLY FAR AWAY
				if (x < y)
				{
					this.subMode = Mode.Horizontal;
					this.horizontal1.updateCamera();
					this.horizontal2.updateCamera();
				}
				// THEY ARE VERTICALLY FURTHER
				else
				{
					this.subMode = Mode.Vertical;
					this.vertical1.updateCamera();
					this.vertical2.updateCamera();
				}
			}
		}
	}

	/**
	 * Draws box if it is within sight of either of the cameras.
	 *
	 * @param box Box which you would like to draw.
	 */
	public void draw(Box box)
	{
		// TRY DRAWING ON HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
		// TRY DRAWING ON VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(box);
			this.vertical2.draw(box);
		}
		// TRY DRAWING ON ONE CAMERA
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(box);
		}
		// DECIDE WHICH CAMERA TO DRAW IT ON
		else if (this.mode.equals(Mode.Smart))
		{
			// CURRENTLY IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(box);
			}
			// CURRENTLY IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(box);
				this.horizontal2.draw(box);
			}
			// CURRENTLY IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(box);
				this.vertical2.draw(box);
			}
		}
	}

	/**
	 * Draws bounding box if it is within sight of either of the cameras.
	 *
	 * @param box Bounding Box which you would like to draw.
	 */
	public void draw(BBox box)
	{
		// TRY DRAWING ON HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
		// TRY DRAWING ON VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(box);
			this.vertical2.draw(box);
		}
		// TRY DRAWING ON ONE CAMERA
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(box);
		}
		// DECIDE WHICH CAMERA TO DRAW IT ON
		else if (this.mode.equals(Mode.Smart))
		{
			// CURRENTLY IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(box);
			}
			// CURRENTLY IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(box);
				this.horizontal2.draw(box);
			}
			// CURRENTLY IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(box);
				this.vertical2.draw(box);
			}
		}
	}

	/**
	 * Draws moving box if it is within sight of either of the cameras.
	 *
	 * @param box Moving Box which you would like to draw.
	 */
	public void draw(MBox box)
	{
		// TRY DRAWING ON HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
		// TRY DRAWING ON VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(box);
			this.vertical2.draw(box);
		}
		// TRY DRAWING ON ONE CAMERA
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(box);
		}
		// DECIDE WHICH CAMERA TO DRAW IT ON
		else if (this.mode.equals(Mode.Smart))
		{
			// CURRENTLY IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(box);
			}
			// CURRENTLY IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(box);
				this.horizontal2.draw(box);
			}
			// CURRENTLY IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(box);
				this.vertical2.draw(box);
			}
		}
	}

	/**
	 * Draws Ball if it is within sight of either of the cameras.
	 *
	 * @param ball Ball which you would like to draw.
	 */
	public void draw(Ball ball)
	{
		// TRY DRAWING ON HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
		// TRY DRAWING ON VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(ball);
			this.vertical2.draw(ball);
		}
		// TRY DRAWING ON ONE CAMERA
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(ball);
		}
		// DECIDE WHICH CAMERA TO DRAW IT ON
		else if (this.mode.equals(Mode.Smart))
		{
			// CURRENTLY IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(ball);
			}
			// CURRENTLY IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(ball);
				this.horizontal2.draw(ball);
			}
			// CURRENTLY IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(ball);
				this.vertical2.draw(ball);
			}
		}
	}

	/**
	 * Draws bounding ball if it is within sight of either of the cameras.
	 *
	 * @param ball Bounding Ball which you would like to draw.
	 */
	public void draw(BBall ball)
	{
		// TRY DRAWING ON HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
		// TRY DRAWING ON VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(ball);
			this.vertical2.draw(ball);
		}
		// TRY DRAWING ON ONE CAMERA
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(ball);
		}
		// DECIDE WHICH CAMERA TO DRAW IT ON
		else if (this.mode.equals(Mode.Smart))
		{
			// CURRENTLY IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(ball);
			}
			// CURRENTLY IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(ball);
				this.horizontal2.draw(ball);
			}
			// CURRENTLY IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(ball);
				this.vertical2.draw(ball);
			}
		}
	}

	/**
	 * Draws moving ball if it is within sight of either of the cameras.
	 *
	 * @param ball Moving Ball which you would like to draw.
	 */
	public void draw(MBall ball)
	{
		// TRY DRAWING ON HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
		// TRY DRAWING ON VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(ball);
			this.vertical2.draw(ball);
		}
		// TRY DRAWING ON ONE CAMERA
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(ball);
		}
		// DECIDE WHICH CAMERA TO DRAW IT ON
		else if (this.mode.equals(Mode.Smart))
		{
			// CURRENTLY IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(ball);
			}
			// CURRENTLY IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(ball);
				this.horizontal2.draw(ball);
			}
			// CURRENTLY IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(ball);
				this.vertical2.draw(ball);
			}
		}
	}

	/**
	 * Draws the camera to the given graphics.
	 *
	 * @param g Graphics onto which you would like camera to be drown to.
	 */
	public void draw(Graphics2D g)
	{
		// TRY DRAWING HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(g, 0, 0);
			this.horizontal2.draw(g, 0, this.horizontal1.getHeight());
		}
		// TRY DRAWING VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(g, 0, 0);
			this.vertical2.draw(g, this.vertical1.getWidth(), 0);
		}
		// TRY DRAWING ONE CAMERAS
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(g);
		}
		// DECIDE WHICH CAMERA TO DRAW
		else if (this.mode.equals(Mode.Smart))
		{
			// IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(g);
			}
			// IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				// PLAYER 1 CAMERA SHOULD BE ABOVE PLAYER 2 CAMERA SINCE HE IS HIGHER
				if (this.player1.getY() < this.player2.getY())
				{
					this.horizontal1.draw(g, 0, 0);
					this.horizontal2.draw(g, 0, this.horizontal1.getHeight());
				}
				// PLAYER 2 CAMERA SHOULD BE ABOVE PLAYER 1 CAMERA SINCE HE IS HIGHER
				else
				{
					this.horizontal1.draw(g, 0, this.horizontal2.getHeight());
					this.horizontal2.draw(g, 0, 0);
				}
			}
			// IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				// PLAYER 1 CAMERA SHOULD BE TO THE LEFT PLAYER 2 CAMERA SINCE HE IS TO THE LEFT
				if (this.player1.getX() < this.player2.getX())
				{
					this.vertical1.draw(g, 0, 0);
					this.vertical2.draw(g, this.vertical1.getWidth(), 0);
				}
				// PLAYER 1 CAMERA SHOULD BE TO THE RIGHT PLAYER 2 CAMERA SINCE HE IS TO THE RIGHT
				else
				{
					this.vertical1.draw(g, this.vertical2.getWidth(), 0);
					this.vertical2.draw(g, 0, 0);
				}
			}
		}
	}

	/**
	 * Draws the camera to the given graphics, and then clear contents of the camera.
	 *
	 * @param g Graphics onto which you would like camera to be drown to.
	 */
	public void drawClear(Graphics2D g)
	{
		// TRY DRAWING HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawClear(g, 0, 0);
			this.horizontal2.drawClear(g, 0, this.horizontal1.getHeight());
		}
		// TRY DRAWING VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.drawClear(g, 0, 0);
			this.vertical2.drawClear(g, this.vertical1.getWidth(), 0);
		}
		// TRY DRAWING ONE CAMERAS
		else if (this.mode.equals(Mode.One))
		{
			this.camera.drawClear(g);
		}
		// DECIDE WHICH CAMERA TO DRAW AND WHERE
		else if (this.mode.equals(Mode.Smart))
		{
			// IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.drawClear(g);
			}
			// IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				// PLAYER 1 CAMERA SHOULD BE ABOVE PLAYER 2 CAMERA SINCE HE IS HIGHER
				if (this.player1.getY() < this.player2.getY())
				{
					this.horizontal1.drawClear(g, 0, 0);
					this.horizontal2.drawClear(g, 0, this.horizontal1.getHeight());
				}
				// PLAYER 2 CAMERA SHOULD BE ABOVE PLAYER 1 CAMERA SINCE HE IS HIGHER
				else
				{
					this.horizontal1.drawClear(g, 0, this.horizontal2.getHeight());
					this.horizontal2.drawClear(g, 0, 0);
				}
			}
			// IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				// PLAYER 1 CAMERA SHOULD BE TO THE LEFT PLAYER 2 CAMERA SINCE HE IS TO THE LEFT
				if (this.player1.getX() < this.player2.getX())
				{
					this.vertical1.drawClear(g, 0, 0);
					this.vertical2.drawClear(g, this.vertical1.getWidth(), 0);
				}
				// PLAYER 1 CAMERA SHOULD BE TO THE RIGHT PLAYER 2 CAMERA SINCE HE IS TO THE RIGHT
				else
				{
					this.vertical1.drawClear(g, this.vertical2.getWidth(), 0);
					this.vertical2.drawClear(g, 0, 0);
				}
			}
		}
	}

	/**
	 * Draws the camera to the given graphics, and then color camera view white.
	 *
	 * @param g Graphics onto which you would like camera to be drown to.
	 */
	public void drawWhite(Graphics2D g)
	{
		// TRY DRAWING HORIZONTAL CAMERAS
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawWhite(g, 0, 0);
			this.horizontal2.drawWhite(g, 0, this.horizontal1.getHeight());
		}
		// TRY DRAWING VERTICAL CAMERAS
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.drawWhite(g, 0, 0);
			this.vertical2.drawWhite(g, this.vertical1.getWidth(), 0);
		}
		// TRY DRAWING ONE CAMERAS
		else if (this.mode.equals(Mode.One))
		{
			this.camera.drawWhite(g);
		}
		// DECIDE WHICH CAMERA TO DRAW AND WHERE
		else if (this.mode.equals(Mode.Smart))
		{
			// IN ONE MODE
			if (this.subMode.equals(Mode.One))
			{
				this.camera.drawWhite(g);
			}
			// IN HORIZONTAL MODE
			else if (this.subMode.equals(Mode.Horizontal))
			{
				// PLAYER 1 CAMERA SHOULD BE ABOVE PLAYER 2 CAMERA SINCE HE IS HIGHER
				if (this.player1.getY() < this.player2.getY())
				{
					this.horizontal1.drawWhite(g, 0, 0);
					this.horizontal2.drawWhite(g, 0, this.horizontal1.getHeight());
				}
				// PLAYER 2 CAMERA SHOULD BE ABOVE PLAYER 1 CAMERA SINCE HE IS HIGHER
				else
				{
					this.horizontal1.drawWhite(g, 0, this.horizontal2.getHeight());
					this.horizontal2.drawWhite(g, 0, 0);
				}
			}
			// IN VERTICAL MODE
			else if (this.subMode.equals(Mode.Vertical))
			{
				// PLAYER 1 CAMERA SHOULD BE TO THE LEFT PLAYER 2 CAMERA SINCE HE IS TO THE LEFT
				if (this.player1.getX() < this.player2.getX())
				{
					this.vertical1.drawWhite(g, 0, 0);
					this.vertical2.drawWhite(g, this.vertical1.getWidth(), 0);
				}
				// PLAYER 1 CAMERA SHOULD BE TO THE RIGHT PLAYER 2 CAMERA SINCE HE IS TO THE RIGHT
				else
				{
					this.vertical1.drawWhite(g, this.vertical2.getWidth(), 0);
					this.vertical2.drawWhite(g, 0, 0);
				}
			}
		}
	}

	/**
	 * Draw camera properties and such.
	 * That means draw camera offsets, and points that indicate boundaries and such.
	 * If you do not want to see what camera is thinking then do not call this method.
	 */
	public void drawCamera()
	{
		// WE ARE IN HORIZONTAL MODE, SO WE DRAW HORIZONTAL CAMERA PROPERTIES
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawCamera();
			this.horizontal2.drawCamera();
		}
		// WE ARE IN VERTICAL MODE, SO WE DRAW VERTICAL CAMERA PROPERTIES
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.drawCamera();
			this.vertical2.drawCamera();
		}
		// WE ARE IN ONE MODE THERE FORE WE DRAW ONE CAMERA
		// AND A POINT WHICH IS MEDIAN BETWEEN TWO CHARACTERS
		else if (this.mode.equals(Mode.One))
		{
			this.camera.drawCamera();
			this.camera.draw(new BasicObjects.Point(
					(this.player1.getCenterX() + this.player2.getCenterX()) / 2 - 1,
					(this.player1.getCenterY() + this.player2.getCenterY()) / 2 - 1
			));
		}
		// WE ARE IN SMART MODE, LET'S SEE WHAT CAMERA WE WILL DRAW
		else if (this.mode.equals(Mode.Smart))
		{
			// WE ARE IN ONE MODE THERE FORE WE DRAW ONE CAMERA
			// AND A POINT WHICH IS MEDIAN BETWEEN TWO CHARACTERS
			if (this.subMode.equals(Mode.One))
			{
				this.camera.drawCamera();
				this.camera.draw(new BasicObjects.Point(
						(this.player1.getCenterX() + this.player2.getCenterX()) / 2 - 1,
						(this.player1.getCenterY() + this.player2.getCenterY()) / 2 - 1
				));
			}
			// WE ARE IN HORIZONTAL MODE, SO WE DRAW HORIZONTAL CAMERA PROPERTIES
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.drawCamera();
				this.horizontal2.drawCamera();
			}
			// WE ARE IN VERTICAL MODE, SO WE DRAW VERTICAL CAMERA PROPERTIES
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.drawCamera();
				this.vertical2.drawCamera();
			}
		}
	}
}
