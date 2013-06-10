package Logic;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import Constants.QConstants;
import Platformer.BasicCharacter;

import java.util.Random;

public class BasicAIController
{
	private Random fate_;
	private int activity_;
	private int minChoiceDuration_, choiceRange_, duration_;
	private int choice_;
	private boolean rangeSet_;
	private int minRange_, maxRange_;
	private int count_;
	private int jumpFrequency_, jumpChance_;
	private BasicCharacter character_;

	public BasicAIController(BasicCharacter character)
	{
		fate_ = new Random();
		this.character_ = character;
		this.count_ = 0;
		this.duration_ = 0;
		this.setJumpFParameters(30, 3);
		this.setActivity(30);
		this.setChoiceDuration(100, 300);
		this.rangeSet_ = false;
	}

	public void randomNoJump()
	{
		if(this.count_++ < this.duration_)
		{
			if(this.rangeSet_)
			{
				if(character_.getLeftX() < this.minRange_+10)
				{
					choice_ = character_.getRightKey();
				}
				else if (character_.getRightX() > this.maxRange_-10)
				{
					choice_ = character_.getLeftKey();
				}
			}
			QEngine.keyPressed(choice_, character_);
		}
		else
		{
			this.count_ = 0;
			this.duration_ = fate_.nextInt(this.choiceRange_) + this.minChoiceDuration_;
			this.choice_ = QConstants.NONE;

			// DO SOMETHING
			if(this.activity_ > fate_.nextInt(100))
			{
				if(this.rangeSet_)
				{
					if(character_.getLeftX() < this.minRange_+10)
					{
						this.choice_ = character_.getRightKey();
					}
					else if (character_.getRightX() > this.maxRange_-10)
					{
						this.choice_ = character_.getLeftKey();
					}
					else
					{
						this.choice_ = fate_.nextInt(2);
					}
				}
				else
				{
					this.choice_ = fate_.nextInt(2);
				}
			}
			// DO NOTHING
			else
			{
				character_.setLeft(false);
				character_.setRight(false);
			}
		}
	}

	public void random()
	{
		if(this.count_++ < this.duration_)
		{
			if(this.rangeSet_)
			{
				if(character_.getLeftX() < this.minRange_+10)
				{
					choice_ = character_.getRightKey();
				}
				else if (character_.getRightX() > this.maxRange_-10)
				{
					choice_ = character_.getLeftKey();
				}
			}
			QEngine.keyPressed(choice_, character_);
			if (this.count_%this.jumpFrequency_ == 0 && fate_.nextInt(this.jumpChance_) == 1)
			{
				QEngine.keyPressed(character_.getJumpKey(), character_);
			}
		}
		else
		{
			this.count_ = 0;
			this.duration_ = fate_.nextInt(this.choiceRange_) + this.minChoiceDuration_;
			this.choice_ = QConstants.NONE;

			// DO SOMETHING
			if(this.activity_ > fate_.nextInt(100))
			{
				if(this.rangeSet_)
				{
					if(character_.getLeftX() < this.minRange_+10)
					{
						this.choice_ = character_.getRightKey();
					}
					else if (character_.getRightX() > this.maxRange_-10)
					{
						this.choice_ = character_.getLeftKey();
					}
					else
					{
						this.choice_ = fate_.nextInt(2);
					}
				}
				else
				{
					this.choice_ = fate_.nextInt(2);
				}
			}
			// DO NOTHING
			else
			{
				character_.setLeft(false);
				character_.setRight(false);
			}
		}
	}

	public void setActivity(int activity)
	{
		this.activity_ = activity;
	}

	public void setChoiceDuration(int minChoiceDuration, int maxChoiceDuration)
	{
		this.minChoiceDuration_ = minChoiceDuration;
		this.choiceRange_ = maxChoiceDuration - minChoiceDuration;
	}

	public void setRange(int minRange, int range)
	{
		this.rangeSet_ = true;
		this.minRange_ = minRange;
		this.maxRange_ = minRange + range;
	}

	public void setJumpFParameters(int jumpFrequency, int jumpChance)
	{
		this.jumpFrequency_ = jumpFrequency;
		this.jumpChance_ = jumpChance;
	}
}
