package Logic;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

public class QImageProcessor
{
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
}
