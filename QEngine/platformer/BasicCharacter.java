package platformer;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Animation;
import constants.Constants;
import platformer.ExtendedShapes.MBControls;
import utils.ImageProcessor;

import java.awt.image.BufferedImage;

/**
 * This is a very basic character.
 * It has very basic, walk, stand and jump images.
 * And that is all.
 * You can use it and enjoy it and see how this engine works.
 */
public class BasicCharacter extends MBControls
{
	private Animation[] walk;
	private Animation[] jump;
	private Animation[] stand;
	private boolean left;

	/**
	 * Basic Character.
	 * This character simply has animation for walking, standing and jumping.
	 * Make sure you load them before you use this class, or you will crash the program.
	 */
	public BasicCharacter()
	{
		this.walk = new Animation[2];
		this.jump = new Animation[2];
		this.stand = new Animation[2];
		this.setLeftPressed(true);
	}

	/**
	 * Sets the walk animation frame size and frame refresh rate.
	 *
	 * @param size        Number of frames for the walking animation.
	 * @param refreshRate The refresh rate for the animation.
	 */
	public void setWalk(int size, int refreshRate)
	{
		this.walk[Constants.LEFT] = new Animation(size, refreshRate);
		this.walk[Constants.RIGHT] = new Animation(size, refreshRate);
	}

	/**
	 * Sets the jumping animation frame size and frame refresh rate.
	 *
	 * @param size        Number of frames for the jumping animation.
	 * @param refreshRate The refresh rate for the animation.
	 */
	public void setJump(int size, int refreshRate)
	{
		this.jump[Constants.LEFT] = new Animation(size, refreshRate);
		this.jump[Constants.RIGHT] = new Animation(size, refreshRate);
	}

	/**
	 * Sets the standing animation frame size and frame refresh rate.
	 *
	 * @param size        Number of frames for the standing animation.
	 * @param refreshRate The refresh rate for the animation.
	 */
	public void setStand(int size, int refreshRate)
	{
		this.stand[Constants.LEFT] = new Animation(size, refreshRate);
		this.stand[Constants.RIGHT] = new Animation(size, refreshRate);
	}

	/**
	 * Adds an image to the walking animation.
	 *
	 * @param image image that you would like added to the walking animation.
	 * @return True if the image was added, false otherwise.
	 */
	public boolean addWalk(BufferedImage image)
	{
		this.walk[Constants.LEFT].addImage(image);
		return this.walk[Constants.RIGHT].addImage(ImageProcessor.flipVertically(image));
	}

	/**
	 * Adds an image to the jumping animation.
	 *
	 * @param image image that you would like added to the jumping animation.
	 */
	public void addJump(BufferedImage image)
	{
		this.jump[Constants.LEFT].addImage(image);
		this.jump[Constants.RIGHT].addImage(ImageProcessor.flipVertically(image));
	}

	/**
	 * Adds an image to the standing animation.
	 *
	 * @param image image that you would like added to the standing animation.
	 */
	public void addStand(BufferedImage image)
	{
		this.stand[Constants.LEFT].addImage(image);
		this.stand[Constants.RIGHT].addImage(ImageProcessor.flipVertically(image));
	}

	/**
	 * Let's you notify this character if the left button was last button pressed or not.
	 * I chose left just because.
	 *
	 * @param left true if the left key was pressed, false otherwise (right key was pressed).
	 */
	public void setLeftPressed(boolean left)
	{
		this.left = left;
	}

	/**
	 * Tells you if the left button was last pressed or not.
	 * This is not always correct value, but this is what this character believes is the case.
	 *
	 * @return True if this character thinks left button was last button pressed, false otherwise.
	 */
	public boolean getLeftPressed()
	{
		return this.left;
	}

	/**
	 * Returns image in accordance with what the character is currently doing.
	 *
	 * @return Image that represents character's current state.
	 */
	public BufferedImage getImage()
	{
		if (this.getLeftPressed())
		{
			if (super.isStanding())
			{
				if (super.getXVector() != 0)
				{
					return this.walk[Constants.LEFT].getImage();
				}
				else
				{
					return this.stand[Constants.LEFT].getImage();
				}
			}
			else
			{
				return this.jump[Constants.LEFT].getImage();
			}
		}
		else
		{
			if (super.isStanding())
			{
				if (super.getXVector() != 0)
				{
					return this.walk[Constants.RIGHT].getImage();
				}
				else
				{
					return this.stand[Constants.RIGHT].getImage();
				}
			}
			else
			{
				return this.jump[Constants.RIGHT].getImage();
			}
		}
	}
}
