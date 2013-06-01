package Logic;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class QImageExtractor
{
	private BufferedImage image_;
	private File file_;

	public QImageExtractor(String image)
	{
		loadImage(image);
	}

	public boolean loadImage(String image)
	{
		file_ = new File(image);
		if(file_.exists())
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

	public BufferedImage getImage()
	{
		return this.image_;
	}

	public BufferedImage getImage(int x, int y, int width, int height)
	{
		return image_.getSubimage(x, y, width, height);
	}
}
