package Logic;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

/**
 * Class for gluing images together, and more image manipulation stuff.
 */
public class QImageProcessor
{
	/**
	 * Will attach both images Top to bottom.
	 * @param top The top of the image you want.
	 * @param bottom The bottom of the image you want.
	 * @return Attached image.
	 */
	public static BufferedImage constructVertical(BufferedImage top, BufferedImage bottom)
	{
		BufferedImage combined = new BufferedImage(
				top.getWidth(),
				top.getHeight()+bottom.getHeight(),
				BufferedImage.TYPE_INT_ARGB
		);
		Graphics g = combined.getGraphics();
		g.drawImage(top, 0, 0, null);
		g.drawImage(bottom, 0, top.getHeight(), null);
		return combined;
	}

	/**
	 * Will attach both images side by side.
	 * @param left The left side of the image you want.
	 * @param right The right side of the image you want.
	 * @return Two images attached side by side.
	 */
	public static BufferedImage constructHorizontal(BufferedImage left, BufferedImage right)
	{
		BufferedImage combined = new BufferedImage(
				left.getWidth()+right.getWidth(),
				left.getHeight(),
				BufferedImage.TYPE_INT_ARGB
		);
		Graphics g = combined.getGraphics();
		g.drawImage(left, 0, 0, null);
		g.drawImage(right, left.getWidth(), 0, null);
		return combined;
	}

	public static BufferedImage extractImage(BufferedImage image, int x, int y, int width, int height)
	{
		return image.getSubimage(x, y, width, height);
	}
}
