package BasicObjects;

import abstracts.Shape;

import java.awt.*;
import java.awt.image.BufferedImage;

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

	private Camera camera;
	private Camera camera1;
	private Camera camera2;

	private BufferedImage view;
	private BufferedImage view1;
	private BufferedImage view2;

	private Graphics2D g;
	private Graphics2D g1;
	private Graphics2D g2;

	private Shape player1, player2;

	private Dimensions size;

	public MultiplayerCamera(Shape player1, Shape player2)
	{
		this.player1 = player1;
		this.player2 = player2;

		this.camera = new Camera();
		this.camera1 = new Camera();
		this.camera2 = new Camera();

		this.mode = Mode.Horizontal;
		this.size = new Dimensions(0, 0);
	}

	public void setMode(Mode mode)
	{
		this.mode = mode;
		initialize();
	}

	public void setSize(Dimensions size)
	{
		this.size = size;
		initialize();
	}

	private void initialize()
	{
		if (this.mode.equals(Mode.Horizontal))
		{
			this.camera1.setSize(new Dimensions(this.size.getWidth(), this.size.getHeight() / 2));
			this.camera2.setSize(new Dimensions(this.size.getWidth(), this.size.getHeight() / 2));
			this.view1 = new BufferedImage(this.size.getWidth(),
					this.size.getHeight() / 2,
					BufferedImage.TYPE_INT_ARGB);
			this.view2 = new BufferedImage(this.size.getWidth(),
					this.size.getHeight() / 2,
					BufferedImage.TYPE_INT_ARGB);
			this.g1 = (Graphics2D) (view1.getGraphics());
			this.g2 = (Graphics2D) (view2.getGraphics());
		}
	}
}
