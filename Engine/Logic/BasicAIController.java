package Logic;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import Platformer.BasicCharacter;

import java.util.Random;

public class BasicAIController
{
	private Random fate_;
	private int activity_;
	private int minChoiceDuration, maximumChoiceDuration;
	private int choice_;
	private BasicCharacter character_;

	public BasicAIController(BasicCharacter character)
	{
		fate_ = new Random();
		this.setActivity(30);
	}

	public void random()
	{
		// DO SOMETHING
		if(this.getActivity() > fate_.nextInt(100))
		{
			QEngine.keyPressed(fate_.nextInt(2), character_);
		}
		// DO NOTHING
		else
		{
			character_.setLeft(false);
			character_.setRight(false);
		}
	}

	public int getActivity()
	{
		return this.activity_;
	}

	public void setActivity(int activity)
	{
		this.activity_ = activity;
	}
}
