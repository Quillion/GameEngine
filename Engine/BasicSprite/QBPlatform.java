package BasicSprite;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicShapes.QBBox;
import utils.QImageProcessor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Platform with bounding box.
 * This type of platform does not contain any animation.
 */
public class QBPlatform extends QBBox
{
	private BufferedImage image_;

	/**
	 * Constructor for the platform, originally contains no image.
	 */
	public QBPlatform()
	{
		super();
		image_ = null;
	}

	/**
	 * Set the image to whatever is passed to us. The width and height will be changed accordingly.
	 *
	 * @param image the image for this platform.
	 */
	public void setImage(BufferedImage image)
	{
		this.image_ = image;
		super.setWidth(image_.getWidth());
		super.setHeight(image_.getHeight());
	}

	/**
	 * Load the image from a given path.
	 *
	 * @return true if image loaded successfully, false otherwise.
	 */
	public boolean loadImage(String path)
	{
		this.image_ = QImageProcessor.loadImage(path);

		if(this.image_ == null)
			return false;

		super.setWidth(image_.getWidth());
		super.setHeight(image_.getHeight());

		return true;
	}

	/**
	 * Returns this platform's image.
	 *
	 * @return image of this platform.
	 */
	public BufferedImage getImage()
	{
		return this.image_;
	}

	/**
	 * Draws image for the platform to the specified canvas.
	 *
	 * @param g Graphics onto which to draw the image to.
	 */
	public void draw(Graphics2D g)
	{
		g.drawImage(this.getImage(), null, super.getX(), super.getY());
	}
}
