package BasicShapes;

import java.awt.image.BufferedImage;

/**
 * User: Edgar
 * Date: 11/12/13
 * Time: 9:04 PM
 */
public class Item extends QBBox
{
	private Animation animation;

	public Item()
	{
		super();
	}

	public void setAnimation(int size, int refreshRate)
	{
		animation = new Animation(size, refreshRate);
	}

	public boolean addImage(BufferedImage image)
	{
		return animation.addImage(image);
	}

	public BufferedImage getImage()
	{
		return animation.getImage();
	}
}
