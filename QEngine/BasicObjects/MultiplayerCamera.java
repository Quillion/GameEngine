package BasicObjects;

import abstracts.Shape;

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
			this.horizontal1.updateCamera();
			this.horizontal2.updateCamera();
		}
	}

	public void draw(Box box)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
	}

	public void draw(BBox box)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
	}

	public void draw(MBox box)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(box);
			this.horizontal2.draw(box);
		}
	}

	public void draw(Ball ball)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
	}

	public void draw(BBall ball)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
	}

	public void draw(MBall ball)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(ball);
			this.horizontal2.draw(ball);
		}
	}

	public void draw(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(g, 0, 0);
			this.horizontal2.draw(g, 0, this.horizontal1.getHeight());
		}
	}

	public void drawClear(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawClear(g, 0, 0);
			this.horizontal2.drawClear(g, 0, this.horizontal1.getHeight());
		}
	}

	public void drawWhite(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawWhite(g, 0, 0);
			this.horizontal2.drawWhite(g, 0, this.horizontal1.getHeight());
		}
	}

	public void draw(Graphics2D g, int x, int y)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.draw(g, x, y);
			this.horizontal2.draw(g, x, y);
		}
	}

	public void drawClear(Graphics2D g, int x, int y)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawClear(g, x, y);
			this.horizontal2.drawClear(g, x, y);
		}
	}

	public void drawWhite(Graphics2D g, int x, int y)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawWhite(g, x, y);
			this.horizontal2.drawWhite(g, x, y);
		}
	}

	public void drawCamera(Graphics2D g)
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.horizontal1.drawCamera(g);
			this.horizontal2.drawCamera(g);
		}
	}
}
