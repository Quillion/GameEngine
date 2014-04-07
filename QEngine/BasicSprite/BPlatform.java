package basicSprite;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.shapes.BBox;
import utils.ImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Platform with bounding box. This type of platform does not contain any animation.
 */
public class BPlatform extends BBox
{
	private BufferedImage image;

	/**
	 * Constructor for the platform, originally contains no image.
	 */
	public BPlatform()
	{
		super();
		this.image = null;
	}

	/**
	 * Set the image to whatever is passed to us. The width and height will be changed accordingly.
	 *
	 * @param image
	 * 		the image for this platform.
	 */
	public void setImage(BufferedImage image)
	{
		this.image = image;
		super.setWidth(image.getWidth());
		super.setHeight(image.getHeight());
	}

	/**
	 * Load the image from a given path.
	 *
	 * @return true if image loaded successfully, false otherwise.
	 */
	public boolean loadImage(String path)
	{
		this.image = ImageProcessor.loadImage(path);

		if (this.image == null)
		{
			return false;
		}

		super.setWidth(this.image.getWidth());
		super.setHeight(this.image.getHeight());

		return true;
	}

	/**
	 * Returns this platform's image.
	 *
	 * @return image of this platform.
	 */
	public BufferedImage getImage()
	{
		return this.image;
	}

	/**
	 * Draws image for the platform to the specified canvas.
	 *
	 * @param g
	 * 		Graphics onto which to draw the image to.
	 */
	public void draw(Graphics2D g)
	{
		g.drawImage(this.getImage(), null, super.getX(), super.getY());
	}
}
