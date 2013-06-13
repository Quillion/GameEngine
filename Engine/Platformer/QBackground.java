package Platformer;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import Logic.QCamera;
import Logic.QImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QBackground
{
	private BufferedImage background_;
	private BufferedImage midground_;
	private BufferedImage foreground_;
	private int x_, y_;
	private List<BufferedImage> foregroundObjects_;

	private int worldHeight_, worldWidth_;
	private int backgroundShift_, midgroundShift_;

	public QBackground(int x, int y, int worldWidth, int worldHeight, int screenWidth, int screenHeight)
	{
		this.foregroundObjects_ = new ArrayList<BufferedImage>();
		this.setBackgroundShift(3);
		this.setMidgroundShift(2);

		this.x_ = x;
		this.y_ = y;

		this.worldWidth_ = worldWidth + screenWidth*2;
		this.worldHeight_ = worldHeight + screenHeight;
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

	public void generateBackground(int density)
	{
		this.background_ = QImageProcessor.resize(this.background_,
													this.worldWidth_/this.backgroundShift_,
													this.worldHeight_/this.backgroundShift_);

		// temp manipulation
		this.foreground_ = this.midground_;
		while(this.foreground_.getWidth() < this.worldWidth_/this.midgroundShift_)
			this.foreground_ = QImageProcessor.constructHorizontal(this.foreground_, this.midground_);
		this.midground_ = this.foreground_;

		Random fate = new Random();
		int type = this.foreground_.getType() == 0? BufferedImage.TYPE_INT_ARGB : this.foreground_.getType();
		this.foreground_ = new BufferedImage(this.worldWidth_, this.worldHeight_, type);
		Graphics g = this.foreground_.getGraphics();

		for (int i = 0; i < density; i++)
		{
			int image = fate.nextInt(this.foregroundObjects_.size());
			int x = fate.nextInt(this.worldWidth_);
			g.drawImage(this.foregroundObjects_.get(image),
						this.x_ + x,
						this.y_ + this.worldHeight_ - this.foregroundObjects_.get(image).getHeight(),
						null);
		}
	}

	public void setBackgroundShift(int backgroundShift)
	{
		this.backgroundShift_ = backgroundShift;
	}

	public void setMidgroundShift(int midgroundShift)
	{
		this.midgroundShift_ = midgroundShift;
	}

	public void draw(Graphics2D g, QCamera camera)
	{
		int x = this.x_ - camera.getX();
		int y = this.y_ - camera.getY() + this.worldHeight_;
		g.drawImage(this.background_, x/this.backgroundShift_, y/this.backgroundShift_, null);
		g.drawImage(this.midground_, x/this.midgroundShift_, y/this.midgroundShift_, null);
		g.drawImage(this.foreground_, x, 0-camera.getY(), null);
	}
}
