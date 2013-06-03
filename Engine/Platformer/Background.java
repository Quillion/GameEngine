package Platformer;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Background
{
	private BufferedImage background_;
	private BufferedImage midground_;
	private List<BufferedImage> foregroundObjects_;

	private int worldHeight_, worldWidth_;
	private int screenHeight_, screenWidth_;

	public Background(int worldWidth, int worldHeight, int screenWidth, int screenHeight)
	{
		foregroundObjects_ = new ArrayList<BufferedImage>();

		this.worldWidth_ = worldWidth;
		this.worldHeight_ = worldHeight;
		this.screenWidth_ = screenWidth;
		this.screenHeight_ = screenHeight;
	}

	public void setBackground(BufferedImage background)
	{
		this.background_ = background;
	}

	public void setMidGround(BufferedImage midGround)
	{
		this.midground_ = midGround;
	}

	public void addForegroundObject(BufferedImage object)
	{
		this.foregroundObjects_.add(object);
	}
}
