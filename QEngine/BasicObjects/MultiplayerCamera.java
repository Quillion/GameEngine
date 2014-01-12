package BasicObjects;

import abstracts.Shape;
import logic.MathEngine;

import java.awt.*;

/**
 * User: Edgar
 * Date: 1/2/14
 * Time: 7:52 PM
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

	public void setMode(Mode mode)
	{
		this.mode = mode;
	}

	public void setSize(Dimensions size, Dimensions offsets)
	{
		this.size = size;

		this.horizontal1.setSize(new Dimensions(this.size.getWidth(), this.size.getHeight() / 2));
		this.horizontal2.setSize(new Dimensions(this.size.getWidth(), this.size.getHeight() / 2));
		this.vertical1.setSize(new Dimensions(this.size.getWidth() / 2, this.size.getHeight()));
		this.vertical2.setSize(new Dimensions(this.size.getWidth() / 2, this.size.getHeight()));
		this.camera.setSize(this.size);

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
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.updateCamera();
			this.horizontal2.updateCamera();
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.updateCamera();
			this.vertical2.updateCamera();
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.updateCamera(
					(this.player1.getCenterX() + this.player2.getCenterX()) / 2,
					(this.player1.getCenterY() + this.player2.getCenterY()) / 2);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			double x = Math.abs(MathEngine.deltaX(this.player1.getCenter(), this.player2.getCenter()));
			double y = Math.abs(MathEngine.deltaY(this.player1.getCenter(), this.player2.getCenter()));
			double cameraX = camera.getWidth() - camera.getHorizontalOffset() * 2;
			double cameraY = camera.getHeight() - camera.getVerticalOffset() * 2;
			if ((x < cameraX) && (y < cameraY))
			{
				this.subMode = Mode.One;
				this.camera.updateCamera(
						(this.player1.getCenterX() + this.player2.getCenterX()) / 2,
						(this.player1.getCenterY() + this.player2.getCenterY()) / 2);
			}
			else
			{
				if (x < y)
				{
					this.subMode = Mode.Horizontal;
					this.horizontal1.updateCamera();
					this.horizontal2.updateCamera();
				}
				else
				{
					this.subMode = Mode.Vertical;
					this.vertical1.updateCamera();
					this.vertical2.updateCamera();
				}
			}
		}
	}

	public void draw(Box box)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(box);
			this.vertical2.draw(box);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(box);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(box);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(box);
				this.horizontal2.draw(box);
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(box);
				this.vertical2.draw(box);
			}
		}
	}

	public void draw(BBox box)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(box);
			this.vertical2.draw(box);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(box);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(box);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(box);
				this.horizontal2.draw(box);
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(box);
				this.vertical2.draw(box);
			}
		}
	}

	public void draw(MBox box)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(box);
			this.vertical2.draw(box);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(box);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(box);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(box);
				this.horizontal2.draw(box);
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(box);
				this.vertical2.draw(box);
			}
		}
	}

	public void draw(Ball ball)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(ball);
			this.vertical2.draw(ball);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(ball);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(ball);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(ball);
				this.horizontal2.draw(ball);
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(ball);
				this.vertical2.draw(ball);
			}
		}
	}

	public void draw(BBall ball)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(ball);
			this.vertical2.draw(ball);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(ball);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(ball);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(ball);
				this.horizontal2.draw(ball);
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(ball);
				this.vertical2.draw(ball);
			}
		}
	}

	public void draw(MBall ball)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(ball);
			this.vertical2.draw(ball);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(ball);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(ball);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.draw(ball);
				this.horizontal2.draw(ball);
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.draw(ball);
				this.vertical2.draw(ball);
			}
		}
	}

	public void draw(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(g, 0, 0);
			this.horizontal2.draw(g, 0, this.horizontal1.getHeight());
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.draw(g, 0, 0);
			this.vertical2.draw(g, this.vertical1.getWidth(), 0);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.draw(g);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.draw(g);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				if (this.player1.getY() < this.player2.getY())
				{
					this.horizontal1.draw(g, 0, 0);
					this.horizontal2.draw(g, 0, this.horizontal1.getHeight());
				}
				else
				{
					this.horizontal1.draw(g, 0, this.horizontal2.getHeight());
					this.horizontal2.draw(g, 0, 0);
				}
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				if (this.player1.getX() < this.player2.getX())
				{
					this.vertical1.draw(g, 0, 0);
					this.vertical2.draw(g, this.vertical1.getWidth(), 0);
				}
				else
				{
					this.vertical1.draw(g, this.vertical2.getWidth(), 0);
					this.vertical2.draw(g, 0, 0);
				}
			}
		}
	}

	public void drawClear(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawClear(g, 0, 0);
			this.horizontal2.drawClear(g, 0, this.horizontal1.getHeight());
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.drawClear(g, 0, 0);
			this.vertical2.drawClear(g, this.vertical1.getWidth(), 0);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.drawClear(g);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.drawClear(g);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				if (this.player1.getY() < this.player2.getY())
				{
					this.horizontal1.drawClear(g, 0, 0);
					this.horizontal2.drawClear(g, 0, this.horizontal1.getHeight());
				}
				else
				{
					this.horizontal1.drawClear(g, 0, this.horizontal2.getHeight());
					this.horizontal2.drawClear(g, 0, 0);
				}
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				if (this.player1.getX() < this.player2.getX())
				{
					this.vertical1.drawClear(g, 0, 0);
					this.vertical2.drawClear(g, this.vertical1.getWidth(), 0);
				}
				else
				{
					this.vertical1.drawClear(g, this.vertical2.getWidth(), 0);
					this.vertical2.drawClear(g, 0, 0);
				}
			}
		}
	}

	public void drawWhite(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawWhite(g, 0, 0);
			this.horizontal2.drawWhite(g, 0, this.horizontal1.getHeight());
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.drawWhite(g, 0, 0);
			this.vertical2.drawWhite(g, this.vertical1.getWidth(), 0);
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.drawWhite(g);
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.drawWhite(g);
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				if (this.player1.getY() < this.player2.getY())
				{
					this.horizontal1.drawWhite(g, 0, 0);
					this.horizontal2.drawWhite(g, 0, this.horizontal1.getHeight());
				}
				else
				{
					this.horizontal1.drawWhite(g, 0, this.horizontal2.getHeight());
					this.horizontal2.drawWhite(g, 0, 0);
				}
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				if (this.player1.getX() < this.player2.getX())
				{
					this.vertical1.drawWhite(g, 0, 0);
					this.vertical2.drawWhite(g, this.vertical1.getWidth(), 0);
				}
				else
				{
					this.vertical1.drawWhite(g, this.vertical2.getWidth(), 0);
					this.vertical2.drawWhite(g, 0, 0);
				}
			}
		}
	}

	public void drawCamera(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawCamera();
			this.horizontal2.drawCamera();
		}
		else if (this.mode.equals(Mode.Vertical))
		{
			this.vertical1.drawCamera();
			this.vertical2.drawCamera();
		}
		else if (this.mode.equals(Mode.One))
		{
			this.camera.drawCamera();
			this.camera.draw(new Point(
					(this.player1.getCenterX() + this.player2.getCenterX()) / 2 - 1,
					(this.player1.getCenterY() + this.player2.getCenterY()) / 2 - 1
			));
		}
		else if (this.mode.equals(Mode.Smart))
		{
			if (this.subMode.equals(Mode.One))
			{
				this.camera.drawCamera();
				this.camera.draw(new Point(
						(this.player1.getCenterX() + this.player2.getCenterX()) / 2 - 1,
						(this.player1.getCenterY() + this.player2.getCenterY()) / 2 - 1
				));
			}
			else if (this.subMode.equals(Mode.Horizontal))
			{
				this.horizontal1.drawCamera();
				this.horizontal2.drawCamera();
			}
			else if (this.subMode.equals(Mode.Vertical))
			{
				this.vertical1.drawCamera();
				this.vertical2.drawCamera();
			}
		}
	}
}
