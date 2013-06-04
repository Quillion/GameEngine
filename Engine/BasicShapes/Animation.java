package BasicShapes;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.image.BufferedImage;

public class Animation
{
	private int size_;
	private BufferedImage [] images_;
	private int refreshRate_;
	private int currentFrame_;
	private int count_;

	public Animation(int size, int refreshRate)
	{
		this.size_ = size;
		this.images_ = new BufferedImage[size];
		this.setRefreshRate(refreshRate);
		this.reset();
	}

	public void setRefreshRate(int refreshRate)
	{
		this.refreshRate_ = refreshRate;
	}

	public int getRefreshRate()
	{
		return this.refreshRate_;
	}

	public int getSize()
	{
		return this.size_;
	}

	public BufferedImage getImage(int index)
	{
		if(index < this.getSize() && index >= 0)
			return images_[index];
		return null;
	}

	public boolean addImage(BufferedImage image)
	{
		for(int i = 0; i < this.getSize(); i++)
		{
			if(this.getImage(i) == null)
			{
				this.images_[i] = image;
				return true;
			}
		}
		return false;
	}

	public boolean addImage(BufferedImage image, int index)
	{
		if(index >= this.getSize() && index < 0)
			return false;
		this.images_[index] = image;
		return true;
	}

	public void reset()
	{
		this.currentFrame_ = 0;
		this.count_ = 0;
	}

	public BufferedImage getImage()
	{
		this.count_++;
		if(this.count_ > this.getRefreshRate())
		{
			this.count_ = 0;
			this.currentFrame_++;
			if(this.currentFrame_ >= this.getSize())
				this.currentFrame_ = 0;
		}
		return this.images_[this.currentFrame_];
	}
}
