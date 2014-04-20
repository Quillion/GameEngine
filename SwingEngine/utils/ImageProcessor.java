package utils;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.net.URL;

/**
 * Class for gluing images together, and more image manipulation stuff.
 */
public class ImageProcessor
{
	/**
	 * Will attach both images Top to bottom.
	 *
	 * @param top
	 * 		The top of the image you want.
	 * @param bottom
	 * 		The bottom of the image you want.
	 *
	 * @return Attached image.
	 */
	public static BufferedImage constructVertical(BufferedImage top,
												  BufferedImage bottom)
	{
		BufferedImage combined = new BufferedImage(
				top.getWidth(),
				top.getHeight() + bottom.getHeight(),
				BufferedImage.TYPE_INT_ARGB
		);
		Graphics g = combined.getGraphics();
		g.drawImage(top, 0, 0, null);
		g.drawImage(bottom, 0, top.getHeight(), null);
		return combined;
	}

	/**
	 * Will attach both images side by side.
	 *
	 * @param left
	 * 		The left side of the image you want.
	 * @param right
	 * 		The right side of the image you want.
	 *
	 * @return Two images attached side by side.
	 */
	public static BufferedImage constructHorizontal(BufferedImage left,
													BufferedImage right)
	{
		BufferedImage combined = new BufferedImage(
				left.getWidth() + right.getWidth(),
				left.getHeight(),
				BufferedImage.TYPE_INT_ARGB
		);
		Graphics g = combined.getGraphics();
		g.drawImage(left, 0, 0, null);
		g.drawImage(right, left.getWidth(), 0, null);
		return combined;
	}

	/**
	 * Extracts a given part from a given image.
	 *
	 * @param image
	 * 		Image from which you would like to extract a part.
	 * @param x
	 * 		the x coordinate of the newly extracted image.
	 * @param y
	 * 		the y coordinate of the newly extracted image within the given image.
	 * @param width
	 * 		The width of the newly extracted image.
	 * @param height
	 * 		The height of the newly extracted image.
	 *
	 * @return The extracted image that you desired.
	 */
	public static BufferedImage extractImage(BufferedImage image, int x, int y,
											 int width, int height)
	{
		return image.getSubimage(x, y, width, height);
	}

	/**
	 * Flips the image around its y axis.
	 *
	 * @param image
	 * 		The image that you want flipped.
	 *
	 * @return The new image that is a copy of given image flipped around its y
	 * axis.
	 */
	public static BufferedImage flipVertically(BufferedImage image)
	{
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-image.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx,
													 AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(image, null);
	}

	/**
	 * Flips the image around its x axis.
	 *
	 * @param image
	 * 		The image that you want flipped.
	 *
	 * @return The new image that is a copy of given image flipped around its x
	 * axis.
	 */
	public static BufferedImage flipHorizontally(BufferedImage image)
	{
		AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
		tx.translate(0, -image.getHeight(null));
		AffineTransformOp op = new AffineTransformOp(tx,
													 AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		return op.filter(image, null);
	}

	/**
	 * Resizes image to the specified width and height. The original image is
	 * not touched so you are safe.
	 *
	 * @param image
	 * 		The image you want resized.
	 * @param width
	 * 		The new width of the image.
	 * @param height
	 * 		The new height of the image.
	 *
	 * @return Newly resized image.
	 */
	public static BufferedImage resize(BufferedImage image, int width,
									   int height)
	{
		int type = image.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : image
				.getType();
		BufferedImage resizedImage = new BufferedImage(width, height, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		g.dispose();
		g.setComposite(AlphaComposite.Src);

		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						   RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
						   RenderingHints.VALUE_RENDER_QUALITY);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						   RenderingHints.VALUE_ANTIALIAS_ON);

		return resizedImage;
	}

	/**
	 * Load the image from a given path.
	 *
	 * @return true if image loaded successfully, false otherwise.
	 */
	public static BufferedImage loadImage(String path)
	{
		URL url = ImageProcessor.class.getResource(path);

		BufferedImage image;
		try
		{
			image = ImageIO.read(url);
		}
		catch (Exception e)
		{
			return null;
		}

		return image;
	}
}
