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

	/**
	 * Well we can't have AI without specifying which character it is for after all.
	 * @param character Character who will be controlled by AI.
	 */
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

	/**
	 * Basic AI movement without any jumping involved.
	 */
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

	/**
	 * Basic random AI movement, with jumping involved.
	 */
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

	/**
	 * Must be in range from 1 to 100, the higher the range, the more active the character.
	 * @param activity The rate of activity of the given character.
	 */
	public void setActivity(int activity)
	{
		if(activity > 0 && activity < 101)
			this.activity_ = activity;
	}

	/**
	 * The duration of actions that character will take.
	 * Smaller range will result in more rapidly changing and seizurelike character.
	 * Make sure that minimum choice duration is smaller than max choice duration.
	 * @param minChoiceDuration Minimum duration to hold the action.
	 * @param maxChoiceDuration Maximum duration to hold the action.
	 */
	public void setChoiceDuration(int minChoiceDuration, int maxChoiceDuration)
	{
		if(minChoiceDuration > maxChoiceDuration)
			return;
		this.minChoiceDuration_ = minChoiceDuration;
		this.choiceRange_ = maxChoiceDuration - minChoiceDuration;
	}

	/**
	 * The range within which the character can move.
	 * Very useful to keep character on the platform.
	 * @param minRange The left side range for the character. The left x.
	 * @param range The range from the left side, the width.
	 */
	public void setRange(int minRange, int range)
	{
		if(range <= 0)
			return;
		this.rangeSet_ = true;
		this.minRange_ = minRange;
		this.maxRange_ = minRange + range;
	}

	/**
	 * Sets up jumping behaviour.
	 * For truly random jumping I would recommend setting frequency to low and chance to high.
	 * @param jumpFrequency How frequently the character will jump.
	 * @param jumpChance The chance of jumping. The higher the number the less likely the character is to jump.
	 */
	public void setJumpFParameters(int jumpFrequency, int jumpChance)
	{
		if (jumpFrequency <= 0 || jumpChance <= 0)
			return;
		this.jumpFrequency_ = jumpFrequency;
		this.jumpChance_ = jumpChance;
	}
}
