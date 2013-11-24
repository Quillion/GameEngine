package BasicShapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import java.awt.image.BufferedImage;

/**
 * Stores bunch of images to form an animation.
 */
public class Animation
{
	private int size_;
	private BufferedImage[] images_;
	private int refreshRate_;
	private int currentFrame_;
	private int count_;

	/**
	 * Well we can't have animation without the number of frames and how to quickly to refresh them can we?
	 *
	 * @param size        How many frames the animation has.
	 * @param refreshRate How quickly each frame will change.
	 */
	public Animation(int size, int refreshRate)
	{
		this.size_ = size;
		this.images_ = new BufferedImage[size];
		this.setRefreshRate(refreshRate);
		this.reset();
	}

	/**
	 * Change the refresh rate,
	 * for example if you have walking animation and you did speed buff,
	 * you would want walking animation to increase too.
	 *
	 * @param refreshRate The new refresh rate for the animation.
	 */
	public void setRefreshRate(int refreshRate)
	{
		this.refreshRate_ = refreshRate;
	}

	/**
	 * Returns how quickly the given animation changes frames.
	 * I don't know why you would want it, but there ya go.
	 *
	 * @return The refresh rate of this animation.
	 */
	public int getRefreshRate()
	{
		return this.refreshRate_;
	}

	/**
	 * Returns how many frames the animation has.
	 *
	 * @return The amount of frames animation has.
	 */
	public int getSize()
	{
		return this.size_;
	}

	/**
	 * Returns the image at the given index.
	 * If incorrect index is specified (less than zero or more than the size), then null is returned.
	 *
	 * @param index The index of tha frame you want.
	 * @return null if index is invalid, the frame at the given index if it is correct.
	 */
	public BufferedImage getImage(int index)
	{
		if (index < this.getSize() && index >= 0)
			return images_[index];
		return null;
	}

	/**
	 * Add the given image to the animation.
	 * If Animation can not handle more frames than initially set,
	 * then the image will not be added.
	 *
	 * @param image Image that you would like to add.
	 * @return True if the image was added to the animation, false otherwise.
	 */
	public boolean addImage(BufferedImage image)
	{
		for (int i = 0; i < this.getSize(); i++)
		{
			if (this.getImage(i) == null)
			{
				this.images_[i] = image;
				return true;
			}
		}
		return false;
	}

	/**
	 * Adds image at the given index.
	 * If index is invalid value (bigger than size or less than zero),
	 * then false is returned and the image is not added.
	 * Otherwise image is added and true is returned.
	 * Very useful method to override image in the animation.
	 *
	 * @param image Image that you would like to add to the animation.
	 * @param index Index at which to add the given image.
	 * @return True if the image was added, and false otherwise.
	 */
	public boolean addImage(BufferedImage image, int index)
	{
		if (index >= this.getSize() && index < 0)
			return false;
		this.images_[index] = image;
		return true;
	}

	/**
	 * Resets the animation to frame zero and frame refresh zero.
	 */
	public void reset()
	{
		this.currentFrame_ = 0;
		this.count_ = 0;
	}

	/**
	 * Returns the image that you request.
	 * Keep calling this method and the image will keep on changing.
	 * This is the animation method.
	 * If animation is incomplete be careful when calling this method.
	 *
	 * @return Different image every time depending on the frame and time.
	 */
	public BufferedImage getImage()
	{
		this.count_++;
		if (this.count_ > this.getRefreshRate())
		{
			this.count_ = 0;
			this.currentFrame_++;
			if (this.currentFrame_ >= this.getSize())
				this.currentFrame_ = 0;
		}
		return this.images_[this.currentFrame_];
	}
}
