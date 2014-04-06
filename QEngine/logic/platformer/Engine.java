package logic.platformer;

import Constants.Constants;
import platformer.MBox;

/**
 * User: Edgar
 * Date: 2/2/14
 * Time: 8:08 PM
 */
public class Engine
{
	public static void keyPressed(MBox box, int key)
	{
		if (box.getKey(Constants.Direction.Left) == key)
		{
			box.press(Constants.Direction.Left);
			box.release(Constants.Direction.Right);
			return;
		}
		if (box.getKey(Constants.Direction.Right) == key)
		{
			box.press(Constants.Direction.Right);
			box.release(Constants.Direction.Left);
			return;
		}
		if (box.getKey(Constants.Direction.Jump) == key && box.isStanding())
		{
			box.press(Constants.Direction.Jump);
			return;
		}
	}

	public static void keyReleased(MBox box, int key)
	{
		if (box.getKey(Constants.Direction.Left) == key)
		{
			box.release(Constants.Direction.Left);
			return;
		}
		if (box.getKey(Constants.Direction.Right) == key)
		{
			box.release(Constants.Direction.Right);
			return;
		}
		if (box.getKey(Constants.Direction.Jump) == key)
		{
			box.release(Constants.Direction.Jump);
			return;
		}
	}

	public static void setControlSpeeds(MBox box)
	{
		box.setStanding(false);
		if (box.isPressed(Constants.Direction.Left))
		{
			box.incrementXVector(-box.getAcceleration());
			if (box.getXVector() < -box.getMaxSpeed())
			{
				box.setXVector(-box.getMaxSpeed());
			}
		}
		else if (box.isPressed(Constants.Direction.Right))
		{
			box.incrementXVector(box.getAcceleration());
			if (box.getXVector() > box.getMaxSpeed())
			{
				box.setXVector(box.getMaxSpeed());
			}
		}
		else if (box.getXVector() != 0)
		{
			if (box.getXVector() < 0)
			{
				if (box.getXVector() > box.getFriction())
				{
					box.setXVector(0);
				}
				else
				{
					box.incrementXVector(box.getFriction());
				}
			}
			else
			{
				if (box.getXVector() < box.getFriction())
				{
					box.setXVector(0);
				}
				else
				{
					box.incrementXVector(-box.getFriction());
				}
			}
		}

		if (box.isPressed(Constants.Direction.Jump))
		{
			box.setYVector(-box.getJump());
			box.release(Constants.Direction.Jump);
		}
		else if (!box.isStanding())
		{
			box.incrementYVector(box.getGravity());
		}
	}
}
