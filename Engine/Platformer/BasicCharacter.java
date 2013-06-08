package Platformer;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import BasicShapes.Animation;
import Constants.QConstants;
import ExtendedShapes.QBMControls;
import Logic.QImageProcessor;

import java.awt.image.BufferedImage;

public class BasicCharacter extends QBMControls
{
	private Animation [] walk_;
	private Animation [] jump_;
	private Animation [] stand_;
	private boolean left_;

	public BasicCharacter()
	{
		walk_ = new Animation[2];
		jump_ = new Animation[2];
		stand_ = new Animation[2];
		this.setLeftPressed(true);
	}

	public void setWalk(int size, int refreshRate)
	{
		walk_[QConstants.LEFT] = new Animation(size, refreshRate);
		walk_[QConstants.RIGHT] = new Animation(size, refreshRate);
	}

	public void setJump(int size, int refreshRate)
	{
		jump_[QConstants.LEFT] = new Animation(size, refreshRate);
		jump_[QConstants.RIGHT] = new Animation(size, refreshRate);
	}

	public void setStand(int size, int refreshRate)
	{
		stand_[QConstants.LEFT] = new Animation(size, refreshRate);
		stand_[QConstants.RIGHT] = new Animation(size, refreshRate);
	}

	public void addWalk(BufferedImage image)
	{
		walk_[QConstants.LEFT].addImage(image);
		walk_[QConstants.RIGHT].addImage(QImageProcessor.flipVertically(image));
	}

	public void addJump(BufferedImage image)
	{
		jump_[QConstants.LEFT].addImage(image);
		jump_[QConstants.RIGHT].addImage(QImageProcessor.flipVertically(image));
	}

	public void addStand(BufferedImage image)
	{
		stand_[QConstants.LEFT].addImage(image);
		stand_[QConstants.RIGHT].addImage(QImageProcessor.flipVertically(image));
	}

	public void setLeftPressed(boolean left)
	{
		this.left_ = left;
	}

	public boolean getLeftPressed()
	{
		return this.left_;
	}

	public BufferedImage getImage()
	{
		if(this.getLeftPressed())
		{
			if(super.isStanding())
			{
				if(super.getXVector() != 0)
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
			if(super.isStanding())
			{
				if(super.getXVector() != 0)
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
