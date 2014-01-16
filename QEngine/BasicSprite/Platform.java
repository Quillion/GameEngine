package BasicSprite;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Shapes.Box;
import utils.ImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Platform with box.
 * This platform is very simplistic and does not contain
 * any bounding box. Additionally this platform has an image,
 * but you do not have to initialize it, instead you can
 * initialize a color and a colorful platform will be drawn.
 */
public class Platform extends Box
{
	private BufferedImage image;
	private Color color;

	/**
	 * Constructor for the platform, originally contains no image,
	 * and color is set to black.
	 */
	public Platform()
	{
		super();
		this.image = null;
		this.setColor(Color.BLACK);
	}

	/**
	 * Sets the color of the platform to whatever you want.
	 *
	 * @param color what color the new platform will be.
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}

	/**
	 * Returns the color to which this platform is set to.
	 *
	 * @return color of this platform.
	 */
	public Color getColor()
	{
		return this.color;
	}

	/**
	 * Set the image to whatever is passed to us. The width and height will be changed accordingly.
	 *
	 * @param image the image for this platform.
	 */
	public void setImage(BufferedImage image)
	{
		this.image = image;
		super.setWidth(image.getWidth());
		super.setHeight(image.getHeight());
	}

	/**
	 * Load the image from a given path,
	 *
	 * @return true if image loaded successfully, false otherwise.
	 */
	public boolean loadImage(String path)
	{
		this.image = ImageProcessor.loadImage(path);

		if(this.image == null)
			return false;

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
	 */
	public void draw(Graphics2D g)
	{
		if (this.image == null)
		{
			g.setColor(this.getColor());
			g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		}
		else
			g.drawImage(this.getImage(), null, super.getX(), super.getY());
	}
}
