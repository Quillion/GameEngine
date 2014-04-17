package logic.dungeon;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import constants.Constants;
import dungeon.MBox;

/**
 * Engine for the dungeon crawler.
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
		if (box.getKey(Constants.Direction.Up) == key)
		{
			box.press(Constants.Direction.Up);
			box.release(Constants.Direction.Down);
			return;
		}
		if (box.getKey(Constants.Direction.Down) == key)
		{
			box.press(Constants.Direction.Down);
			box.release(Constants.Direction.Up);
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
		if (box.getKey(Constants.Direction.Up) == key)
		{
			box.release(Constants.Direction.Up);
			return;
		}
		if (box.getKey(Constants.Direction.Down) == key)
		{
			box.release(Constants.Direction.Down);
			return;
		}
	}

	public static void setControlSpeeds(MBox box)
	{
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

		if (box.isPressed(Constants.Direction.Up))
		{
			box.incrementYVector(-box.getAcceleration());
			if (box.getYVector() < -box.getMaxSpeed())
			{
				box.setYVector(-box.getMaxSpeed());
			}
		}
		else if (box.isPressed(Constants.Direction.Down))
		{
			box.incrementYVector(box.getAcceleration());
			if (box.getYVector() > box.getMaxSpeed())
			{
				box.setYVector(box.getMaxSpeed());
			}
		}
		else
		{
			if (box.getYVector() < 0)
			{
				if (box.getYVector() > box.getFriction())
				{
					box.setYVector(0);
				}
				else
				{
					box.incrementYVector(box.getFriction());
				}
			}
			else
			{
				if (box.getYVector() < box.getFriction())
				{
					box.setYVector(0);
				}
				else
				{
					box.incrementYVector(-box.getFriction());
				}
			}
		}
	}
}
