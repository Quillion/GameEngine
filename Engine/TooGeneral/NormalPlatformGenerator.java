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

/**
 * Class for drawing a platform for the platformer.
 */
public class NormalPlatformGenerator
{
	private QImageExtractor extractor;
	private Random fate;

	/**
	 * Simple constructor, you do not have to do anything.
	 */
	public NormalPlatformGenerator()
	{
		extractor = new QImageExtractor("Images/Platforms/dragonroadtiles.png");
		fate = new Random();
	}

	/**
	 * Returns one of the 4 random images for the top of the platform.
	 * @return The top of the platform image.
	 */
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

	/**
	 * Returns one of the 4 random images for the middile of the platform.
	 * @return The middle of the platform image.
	 */
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

	/**
	 * Returns one of the 3 images for the bottom of the platform.
	 * @return The image for the bottom of platform.
	 */
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

	/**
	 * Returns an image for the top of a wall.
	 * I personally don't like the image too much.
	 * @return The image for the top of the wall.
	 */
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

	/**
	 * Returns one of the 2 images for the bottom of the wall.
	 * Again I dislike the image.
	 * @return The image for the bottom of the wall.
	 */
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

	/**
	 * Return one of the 2 images for the left side of the wall.
	 * @return The image for the left of the wall.
	 */
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

	/**
	 * Returns one of the two images for the right of the wall.
	 * @return The image for the right of the wall.
	 */
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

	/**
	 * Return the image for the ground.
	 * It is composed of platform top, platform middle and platform bottom.
	 * @return Image for the ground.
	 */
	public BufferedImage getGround()
	{
	    return QImageProcessor.constructVertical(
	            QImageProcessor.constructVertical(this.getPlatformTop(), this.getPlatformMiddle()),
				this.getPlatformBottom());
	}

	/**
	 * Returns the image for the platform.
	 * It consists of a combination of platform top and platform bottom.
	 * @return Image for the platform.
	 */
	public BufferedImage getPlatform()
	{
		return QImageProcessor.constructVertical(this.getPlatformTop(), this.getPlatformBottom());
	}

	/**
	 * Returns image for the wall.
	 * It is composed of wall left and wall right.
	 * @return Image for the wall.
	 */
	public BufferedImage getWall()
	{
		return QImageProcessor.constructHorizontal(this.getWallLeft(), this.getWallRight());
	}
}
