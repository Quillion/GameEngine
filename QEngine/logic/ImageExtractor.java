package logic;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Basic image extraction class. You load whatever image you desire,
 * and then you can use given functions to slice and dice the image.
 */
public class ImageExtractor
{
	private BufferedImage image_;

	/**
	 * Specify the path to the image and it will be loaded.
	 * Please load pngs only.
	 *
	 * @param image The path where the image you want to load is located.
	 */
	public ImageExtractor(String image)
	{
		loadImage(image);
	}

	/**
	 * Attach the given Buffered image to this object.
	 * Why would you want this method is beyond me, but enjoy.
	 *
	 * @param image The image that you want to have sliced and diced.
	 */
	public ImageExtractor(BufferedImage image)
	{
		this.image_ = image;
	}

	/**
	 * Load the image given the path.
	 * Png only please.
	 *
	 * @param image The path where the image is located.
	 * @return True if loading was successful, false otherwise. You will also have error printed if false.
	 */
	public boolean loadImage(String image)
	{
		File file_ = new File(image);
		if (file_.exists())
		{
			try
			{
				image_ = ImageIO.read(file_);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		else
			return false;
		return true;
	}

	/**
	 * Returns the image that is currently loaded in this object.
	 *
	 * @return Image, which is the current image loaded into this object.
	 */
	public BufferedImage getImage()
	{
		return this.image_;
	}

	/**
	 * Slice and dice method for this object.
	 *
	 * @param x      the left x location.
	 * @param y      the top y location.
	 * @param width  width of the image you want extracted.
	 * @param height height of the image you want extracted.
	 * @return the extracted part of the image you wanted.
	 */
	public BufferedImage getImage(int x, int y, int width, int height)
	{
		return image_.getSubimage(x, y, width, height);
	}
}
