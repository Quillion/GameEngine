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

public class Background
{
	private BufferedImage background_;
	private BufferedImage midground_;
	private BufferedImage foreground_;
	private int x_, y_;
	private List<BufferedImage> foregroundObjects_;

	private int worldHeight_, worldWidth_;
	private int backgroundShift_, midgroundShift_;

	private QCamera camera;

	public Background(QCamera camera, int x, int y, int worldWidth, int worldHeight)
	{
		this.foregroundObjects_ = new ArrayList<BufferedImage>();
		this.setBackgroundShift(3);
		this.setMidgroundShift(2);

		this.camera = camera;

		this.x_ = x;
		this.y_ = y;

		this.worldWidth_ = worldWidth;
		this.worldHeight_ = worldHeight;
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
		int width = this.worldWidth_ - this.x_;
		int height = this.worldHeight_ - this.y_;

		this.background_ = QImageProcessor.resize(this.background_, width/this.backgroundShift_, height/this.backgroundShift_);

		while(this.midground_.getWidth() < width/this.midgroundShift_)
			this.midground_ = QImageProcessor.constructHorizontal(this.midground_, this.midground_);

		Random fate = new Random();
		int type = this.midground_.getType() == 0? BufferedImage.TYPE_INT_ARGB : this.midground_.getType();
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

	public void draw(Graphics2D g)
	{
		int x = this.camera.getCenterX() - this.x_;
		int y = this.camera.getCenterY() - this.y_;
		g.drawImage(this.background_, x/this.backgroundShift_, y/this.backgroundShift_, null);
		g.drawImage(this.midground_, x/this.midgroundShift_, y/this.midgroundShift_, null);
	}
}
