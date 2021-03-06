package logic.platformer;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import constants.Constants;
import platformer.MBox;

import java.util.Random;

/**
 * This will controls the behaviour of the specified character the way you want
 * to. It will perform whatever actions you specify at a specified frequency.
 */
public class BasicAIController
{
	private Random fate;
	private int    activity;
	private int    minChoiceDuration, choiceRange, duration;
	private int     choice;
	private boolean rangeSet;
	private int     minRange, maxRange;
	private int count;
	private int jumpFrequency, jumpChance;
	private MBox character;

	/**
	 * Well we can't have AI without specifying which character it is for after
	 * all.
	 *
	 * @param character
	 * 		Character who will be controlled by AI.
	 */
	public BasicAIController(MBox character)
	{
		this.fate = new Random();
		this.character = character;
		this.count = 0;
		this.duration = 0;
		this.setJumpFParameters(30, 3);
		this.setActivity(30);
		this.setChoiceDuration(100, 300);
		this.rangeSet = false;
	}

	/**
	 * Basic AI movement without any jumping involved.
	 */
	public void randomNoJump()
	{
		// STILL DOING THE ACTION FROM LAST TIME
		if (this.count++ < this.duration)
		{
			if (this.rangeSet)
			{
				if (this.character.getLeftX() < this.minRange + 10)
				{
					this.character.press(Constants.Direction.Right);
				}
				else if (this.character.getRightX() > this.maxRange - 10)
				{
					this.character.press(Constants.Direction.Left);
				}
			}
		}
		// WE WANT NEW ACTION
		else
		{
			this.count = 0;
			this.duration = this.fate
					.nextInt(this.choiceRange) + this.minChoiceDuration;
			this.choice = Constants.NONE;

			// DO SOMETHING CHOSEN
			if (this.activity > this.fate.nextInt(100))
			{
				if (this.rangeSet)
				{
					// IF WE EXCEEDED OUR CURRENT RANGE MOVE TO RANGE
					if (this.character.getLeftX() < this.minRange + 10)
					{
						this.character.press(Constants.Direction.Right);
						this.character.release(Constants.Direction.Left);
					}
					else if (this.character.getRightX() > this.maxRange - 10)
					{
						this.character.release(Constants.Direction.Right);
						this.character.press(Constants.Direction.Left);
					}
					// WE ARE WITHIN OUR RANGE, DECIDE ON NEW ACTION
					else
					{
						if (this.fate.nextInt(2) == 1)
						{
							this.character.press(Constants.Direction.Right);
							this.character.release(Constants.Direction.Left);
						}
						else
						{
							this.character.release(Constants.Direction.Right);
							this.character.press(Constants.Direction.Left);
						}
					}
				}
				// WE DO NOT HAVE RANGE CONSTRAINTS GO CRAZY
				else
				{
					if (this.fate.nextInt(2) == 1)
					{
						this.character.press(Constants.Direction.Right);
						this.character.release(Constants.Direction.Left);
					}
					else
					{
						this.character.release(Constants.Direction.Right);
						this.character.press(Constants.Direction.Left);
					}
				}
			}
			// DO NOTHING
			else
			{
				this.character.release(Constants.Direction.Left);
				this.character.release(Constants.Direction.Right);
			}
		}
	}

	/**
	 * Basic random AI movement, with jumping involved.
	 */
	public void random()
	{
		// STILL DOING THE ACTION FROM LAST TIME
		if (this.count++ < this.duration)
		{
			// MAKE SURE WE DID NOT EXCEED OUR RANGE
			if (this.rangeSet)
			{
				if (this.character.getLeftX() < this.minRange + 10)
				{
					this.character.press(Constants.Direction.Right);
					this.character.release(Constants.Direction.Left);
				}
				else if (this.character.getRightX() > this.maxRange - 10)
				{
					this.character.release(Constants.Direction.Right);
					this.character.press(Constants.Direction.Left);
				}
			}
			// SEE IF WE SHOULD TRY JUMPING
			if (this.count % this.jumpFrequency == 0 && this.fate
					.nextInt(this.jumpChance) == 1)
			{
				this.character.press(Constants.Direction.Jump);
			}
		}
		else
		{
			this.count = 0;
			this.duration = this.fate
					.nextInt(this.choiceRange) + this.minChoiceDuration;
			this.choice = Constants.NONE;

			// DO SOMETHING
			if (this.activity > this.fate.nextInt(100))
			{
				// IF WE EXCEEDED OUR CURRENT RANGE MOVE TO RANGE
				if (this.rangeSet)
				{
					if (this.character.getLeftX() < this.minRange + 10)
					{
						this.character.press(Constants.Direction.Right);
						this.character.release(Constants.Direction.Left);
					}
					else if (this.character.getRightX() > this.maxRange - 10)
					{
						this.character.release(Constants.Direction.Right);
						this.character.press(Constants.Direction.Left);
					}
					// WE ARE WITHIN OUR RANGE, DECIDE ON NEW ACTION
					else
					{
						if (this.fate.nextInt(2) == 1)
						{
							this.character.press(Constants.Direction.Right);
							this.character.release(Constants.Direction.Left);
						}
						else
						{
							this.character.release(Constants.Direction.Right);
							this.character.press(Constants.Direction.Left);
						}
					}
				}
				// WE DO NOT HAVE RANGE CONSTRAINTS GO CRAZY
				else
				{
					if (this.fate.nextInt(2) == 1)
					{
						this.character.press(Constants.Direction.Right);
						this.character.release(Constants.Direction.Left);
					}
					else
					{
						this.character.release(Constants.Direction.Right);
						this.character.press(Constants.Direction.Left);
					}
				}
			}
			// DO NOTHING
			else
			{
				this.character.release(Constants.Direction.Left);
				this.character.release(Constants.Direction.Right);
			}
		}
	}

	/**
	 * Must be in range from 1 to 100, the higher the range, the more active the
	 * character.
	 *
	 * @param activity
	 * 		The rate of activity of the given character.
	 */
	public void setActivity(int activity)
	{
		if (activity > 0 && activity < 101)
		{
			this.activity = activity;
		}
	}

	/**
	 * The duration of actions that character will take. Smaller range will
	 * result in more rapidly changing and seizurelike character. Make sure that
	 * minimum choice duration is smaller than max choice duration.
	 *
	 * @param minChoiceDuration
	 * 		Minimum duration to hold the action.
	 * @param maxChoiceDuration
	 * 		Maximum duration to hold the action.
	 */
	public void setChoiceDuration(int minChoiceDuration, int maxChoiceDuration)
	{
		if (minChoiceDuration > maxChoiceDuration)
		{
			return;
		}
		this.minChoiceDuration = minChoiceDuration;
		this.choiceRange = maxChoiceDuration - minChoiceDuration;
	}

	/**
	 * The range within which the character can move. Very useful to keep
	 * character on the platform.
	 *
	 * @param minRange
	 * 		The left side range for the character. The left x.
	 * @param range
	 * 		The range from the left side, the width.
	 */
	public void setRange(int minRange, int range)
	{
		if (range <= 0)
		{
			return;
		}
		this.rangeSet = true;
		this.minRange = minRange;
		this.maxRange = minRange + range;
	}

	/**
	 * Sets up jumping behaviour. For truly random jumping I would recommend
	 * setting frequency to low and chance to high.
	 *
	 * @param jumpFrequency
	 * 		How frequently the character will jump.
	 * @param jumpChance
	 * 		The chance of jumping. The higher the number the less likely the
	 * 		character is to jump.
	 */
	public void setJumpFParameters(int jumpFrequency, int jumpChance)
	{
		if (jumpFrequency <= 0 || jumpChance <= 0)
		{
			return;
		}
		this.jumpFrequency = jumpFrequency;
		this.jumpChance = jumpChance;
	}
}
