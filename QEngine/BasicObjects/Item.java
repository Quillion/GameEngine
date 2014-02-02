package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.shapes.BBox;

import java.awt.image.BufferedImage;

/**
 * This is exactly like animation, the only difference is that since this extends
 * BBox then it has certain bounds and can be checked for collision with other items.
 */
public class Item extends BBox
{
	private Animation animation;

	/**
	 * Basic constructor does nothing fancy.
	 * Once item is created you will still have to declare all its variables.
	 */
	public Item()
	{
		super();
	}

	/**
	 * We can't have animation without the number of frames and how to quickly to refresh them can we?
	 *
	 * @param size        How many frames the animation has.
	 * @param refreshRate How quickly each frame will change.
	 */
	public void setAnimation(int size, int refreshRate)
	{
		this.animation = new Animation(size, refreshRate);
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
		return this.animation.addImage(image);
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
		return this.animation.getImage();
	}
}
