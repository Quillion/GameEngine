package TooGeneral;

import Logic.QImageExtractor;
import Logic.QImageProcessor;

import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

public class NormalPlatformGenerator
{
	private QImageExtractor extractor;
	private Random fate;

	public NormalPlatformGenerator()
	{
		extractor = new QImageExtractor("Images/Platforms/dragonroadtiles.png");
		fate = new Random();
	}

	public BufferedImage getPlatformTop()
	{
		int choice = fate.nextInt(4);
		switch (choice)
		{
			case 0:
				return extractor.getImage(265, 87, 90, 37);
			case 1:
				return extractor.getImage(365, 87, 90, 37);
			case 2:
				return extractor.getImage(465, 87, 90, 37);
			case 3:
				return extractor.getImage(16, 222, 90, 37);
			default:
				return extractor.getImage(265, 87, 90, 37);
		}
	}

	public BufferedImage getPlatformMiddle()
	{
		int choice = fate.nextInt(4);
		switch (choice)
		{
			case 0:
				return extractor.getImage(12, 10, 90, 58);
			case 1:
				return extractor.getImage(111, 10, 90, 58);
			case 2:
				return extractor.getImage(210, 10, 90, 58);
			case 3:
				return extractor.getImage(309, 10, 90, 58);
			default:
				return extractor.getImage(12, 10, 90, 58);
		}
	}

	public BufferedImage getPlatformBottom()
	{
		int choice = fate.nextInt(3);
		switch (choice)
		{
			case 0:
				return extractor.getImage(14, 144, 90, 31);
			case 1:
				return extractor.getImage(114, 144, 90, 31);
			case 2:
				return extractor.getImage(213, 144, 90, 31);
			default:
				return extractor.getImage(14, 144, 90, 31);
		}
	}

	public BufferedImage getWallTop()
	{
		int choice = fate.nextInt(2);
		switch (choice)
		{
			case 0:
				return extractor.getImage(121, 87, 67, 32);
			case 1:
				return extractor.getImage(191, 87, 67, 32);
			default:
				return extractor.getImage(121, 87, 67, 32);
		}
	}

	public BufferedImage getWallBottom()
	{
		int choice = fate.nextInt(2);
		switch (choice)
		{
			case 0:
				return extractor.getImage(6, 88, 47, 32);
			case 1:
				return extractor.getImage(58, 88, 47, 32);
			default:
				return extractor.getImage(6, 88, 47, 32);
		}
	}

	public BufferedImage getWallLeft()
	{
		int choice = fate.nextInt(2);
		switch (choice)
		{
			case 0:
				return extractor.getImage(318, 144, 30, 60);
			case 1:
				return extractor.getImage(356, 144, 30, 60);
			default:
				return extractor.getImage(318, 144, 30, 60);
		}
	}

	public BufferedImage getWallRight()
	{
		int choice = fate.nextInt(2);
		switch (choice)
		{
			case 0:
				return extractor.getImage(400, 144, 37, 60);
			case 1:
				return extractor.getImage(448, 144, 37, 60);
			default:
				return extractor.getImage(400, 144, 37, 60);
		}
	}

	public BufferedImage getGround()
	{
	    return QImageProcessor.constructVertical(
	            QImageProcessor.constructVertical(this.getPlatformTop(), this.getPlatformMiddle()),
				this.getPlatformBottom());
	}

	public BufferedImage getPlatform()
	{
		return QImageProcessor.constructVertical(this.getPlatformTop(), this.getPlatformBottom());
	}

	public BufferedImage getWall()
	{
		return QImageProcessor.constructHorizontal(this.getWallLeft(), this.getWallRight());
	}
}
