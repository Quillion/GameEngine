package platformer;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicShapes.Animation;
import Constants.QConstants;
import platformer.ExtendedShapes.QBMControls;
import utils.QImageProcessor;

import java.awt.image.BufferedImage;

/**
 * This is a very basic character.
 * It has very basic, walk, stand and jump images.
 * And that is all.
 * You can use it and enjoy it and see how this engine works.
 */
public class BasicCharacter extends QBMControls
{
	private Animation[] walk_;
	private Animation[] jump_;
	private Animation[] stand_;
	private boolean left_;

	/**
	 * Basic Character.
	 * This character simply has animation for walking, standing and jumping.
	 * Make sure you load them before you use this class, or you will crash the program.
	 */
	public BasicCharacter()
	{
		walk_ = new Animation[2];
		jump_ = new Animation[2];
		stand_ = new Animation[2];
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
		walk_[QConstants.LEFT] = new Animation(size, refreshRate);
		walk_[QConstants.RIGHT] = new Animation(size, refreshRate);
	}

	/**
	 * Sets the jumping animation frame size and frame refresh rate.
	 *
	 * @param size        Number of frames for the jumping animation.
	 * @param refreshRate The refresh rate for the animation.
	 */
	public void setJump(int size, int refreshRate)
	{
		jump_[QConstants.LEFT] = new Animation(size, refreshRate);
		jump_[QConstants.RIGHT] = new Animation(size, refreshRate);
	}

	/**
	 * Sets the standing animation frame size and frame refresh rate.
	 *
	 * @param size        Number of frames for the standing animation.
	 * @param refreshRate The refresh rate for the animation.
	 */
	public void setStand(int size, int refreshRate)
	{
		stand_[QConstants.LEFT] = new Animation(size, refreshRate);
		stand_[QConstants.RIGHT] = new Animation(size, refreshRate);
	}

	/**
	 * Adds an image to the walking animation.
	 *
	 * @param image image that you would like added to the walking animation.
	 * @return True if the image was added, false otherwise.
	 */
	public boolean addWalk(BufferedImage image)
	{
		walk_[QConstants.LEFT].addImage(image);
		return walk_[QConstants.RIGHT].addImage(QImageProcessor.flipVertically(image));
	}

	/**
	 * Adds an image to the jumping animation.
	 *
	 * @param image image that you would like added to the jumping animation.
	 */
	public void addJump(BufferedImage image)
	{
		jump_[QConstants.LEFT].addImage(image);
		jump_[QConstants.RIGHT].addImage(QImageProcessor.flipVertically(image));
	}

	/**
	 * Adds an image to the standing animation.
	 *
	 * @param image image that you would like added to the standing animation.
	 */
	public void addStand(BufferedImage image)
	{
		stand_[QConstants.LEFT].addImage(image);
		stand_[QConstants.RIGHT].addImage(QImageProcessor.flipVertically(image));
	}

	/**
	 * Let's you notify this character if the left button was last button pressed or not.
	 * I chose left just because.
	 *
	 * @param left true if the left key was pressed, false otherwise (right key was pressed).
	 */
	public void setLeftPressed(boolean left)
	{
		this.left_ = left;
	}

	/**
	 * Tells you if the left button was last pressed or not.
	 * This is not always correct value, but this is what this character believes is the case.
	 *
	 * @return True if this character thinks left button was last button pressed, false otherwise.
	 */
	public boolean getLeftPressed()
	{
		return this.left_;
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
					return walk_[QConstants.LEFT].getImage();
				}
				else
				{
					return stand_[QConstants.LEFT].getImage();
				}
			}
			else
			{
				return jump_[QConstants.LEFT].getImage();
			}
		}
		else
		{
			if (super.isStanding())
			{
				if (super.getXVector() != 0)
				{
					return walk_[QConstants.RIGHT].getImage();
				}
				else
				{
					return stand_[QConstants.RIGHT].getImage();
				}
			}
			else
			{
				return jump_[QConstants.RIGHT].getImage();
			}
		}
	}
}
