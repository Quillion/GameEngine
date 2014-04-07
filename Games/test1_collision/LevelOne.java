package test1_collision;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.*;
import basicObjects.Point;
import basicObjects.shapes.*;
import painter.ShapePainter;
import abstracts.Level;
import constants.Constants;
import logic.CollisionEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelOne extends Level
{
	private List<Box>  boxes;
	private List<BBox> bBoxes;
	private List<MBox> mBoxes;

	private List<Ball>  balls;
	private List<BBall> bBalls;
	private List<MBall> mBalls;

	public LevelOne(Game game)
	{
		super(game);
	}

	@Override
	public void setup()
	{
		boxes = new ArrayList<Box>();
		bBoxes = new ArrayList<BBox>();
		mBoxes = new ArrayList<MBox>();

		balls = new ArrayList<Ball>();
		bBalls = new ArrayList<BBall>();
		mBalls = new ArrayList<MBall>();
	}

	@Override
	public void load()
	{
		Box box = new Box();
		box.setCoordinates(new basicObjects.Point(0, getHeight() - 30));
		box.setSize(new Dimensions(getWidth() / 2, 30));
		boxes.add(box);

		box = new Box();
		box.setCoordinates(new basicObjects.Point(0, 0));
		box.setSize(new Dimensions(getWidth() / 2, 30));
		boxes.add(box);

		BBox bBox = new BBox();
		bBox.setCoordinates(new Point(0, 30));
		bBox.setSize(new Dimensions(30, getHeight() - 60));
		bBox.setOffsets(new Dimensions(5, 0));
		bBoxes.add(bBox);

		bBox = new BBox();
		bBox.setCoordinates(new Point(getWidth() / 2 - 30, 30));
		bBox.setSize(new Dimensions(30, getHeight() - 60));
		bBox.setOffsets(new Dimensions(5, 0));
		bBoxes.add(bBox);

		bBox = new BBox();
		bBox.setCoordinates(new Point(getWidth() / 4 - 50, getHeight() / 2 - 50));
		bBox.setSize(new Dimensions(100, 100));
		bBox.setOffsets(new Dimensions(9, 9));
		bBoxes.add(bBox);

		MBox mBox = new MBox();
		mBox.setCoordinates(new Point(getWidth() / 3, getHeight() / 3));
		mBox.setSize(new Dimensions(45, 45));
		mBox.setOffsets(new Dimensions(7, 7));
		mBox.setVector(new Point(0, 0));
		mBoxes.add(mBox);

		Ball ball;
		for (int i = 0; i < 5; i++)
		{
			ball = new Ball();
			ball.setCoordinates(new Point(getWidth() / 2 + 125 + (i * 50), 25));
			ball.setRadius(25);
			balls.add(ball);

			ball = new Ball();
			ball.setCoordinates(new Point(getWidth() / 2 + 125 + (i * 50), getHeight() - 25));
			ball.setRadius(25);
			balls.add(ball);
		}

		for (int i = 0; i < 3; i++)
		{
			ball = new Ball();
			ball.setCoordinates(new Point(getWidth() / 2 + 40, 140 + (i * 110)));
			ball.setRadius(40);
			balls.add(ball);

			ball = new Ball();
			ball.setCoordinates(new Point(getWidth() - 40, 140 + (i * 110)));
			ball.setRadius(40);
			balls.add(ball);
		}

		BBall bBall = new BBall();
		bBall.setCoordinates(new Point(getWidth() / 2 + 50, 50));
		bBall.setRadius(50);
		bBall.setOffset(7);
		bBalls.add(bBall);

		bBall = new BBall();
		bBall.setCoordinates(new Point(getWidth() - 50, 50));
		bBall.setRadius(50);
		bBall.setOffset(7);
		bBalls.add(bBall);

		bBall = new BBall();
		bBall.setCoordinates(new Point(getWidth() / 2 + 50, getHeight() - 50));
		bBall.setRadius(50);
		bBall.setOffset(7);
		bBalls.add(bBall);

		bBall = new BBall();
		bBall.setCoordinates(new Point(getWidth() - 50, getHeight() - 50));
		bBall.setRadius(50);
		bBall.setOffset(7);
		bBalls.add(bBall);

		bBall = new BBall();
		bBall.setCoordinates(new Point(getWidth() * 0.75, getHeight() / 2));
		bBall.setRadius(50);
		bBall.setOffset(9);
		bBalls.add(bBall);

		MBall mBall = new MBall();
		mBall.setCoordinates(new Point(getWidth() * 0.67, getHeight() / 3));
		mBall.setRadius(23);
		mBall.setOffset(7);
		mBalls.add(mBall);

		setLoaded(true);
	}

	@Override
	public void draw(Graphics2D g)
	{
		for (Box box : boxes)
		{
			ShapePainter.draw(g, box);
		}

		for (BBox box : bBoxes)
		{
			ShapePainter.draw(g, box);
		}

		for (MBox box : mBoxes)
		{
			ShapePainter.draw(g, box);
		}

		for (Ball ball : balls)
		{
			ShapePainter.draw(g, ball);
		}

		for (BBall ball : bBalls)
		{
			ShapePainter.draw(g, ball);
		}

		for (MBall ball : mBalls)
		{
			ShapePainter.draw(g, ball);
		}
	}

	@Override
	public void update()
	{
		for (MBox mbox : mBoxes)
		{
			for (Box box : boxes)
			{
				Constants.Direction direction = CollisionEngine.horizontalCollision(mbox, box);
				if (direction.equals(Constants.Direction.Right) && mbox.getXVector() > 0)
				{
					mbox.setXVector(0);
				}
				else if (direction.equals(Constants.Direction.Left) && mbox.getXVector() < 0)
				{
					mbox.setXVector(0);
				}
				direction = CollisionEngine.verticalCollision(mbox, box);
				if (direction.equals(Constants.Direction.Down) && mbox.getYVector() > 0)
				{
					mbox.setYVector(0);
				}
				else if (direction.equals(Constants.Direction.Up) && mbox.getYVector() < 0)
				{
					mbox.setYVector(0);
				}
			}

			for (BBox box : bBoxes)
			{
				Constants.Direction direction = CollisionEngine.horizontalCollision(mbox, box);
				if (direction.equals(Constants.Direction.Right) && mbox.getXVector() > 0)
				{
					mbox.setXVector(0);
				}
				else if (direction.equals(Constants.Direction.Left) && mbox.getXVector() < 0)
				{
					mbox.setXVector(0);
				}
				direction = CollisionEngine.verticalCollision(mbox, box);
				if (direction.equals(Constants.Direction.Down) && mbox.getYVector() > 0)
				{
					mbox.setYVector(0);
				}
				else if (direction.equals(Constants.Direction.Up) && mbox.getYVector() < 0)
				{
					mbox.setYVector(0);
				}
			}

			mbox.move();
		}

		for (MBall mball : mBalls)
		{
			for (Ball ball : balls)
			{
				boolean collision = CollisionEngine.collision(mball.copy().move(), ball);
				Constants.Direction direction = CollisionEngine.horizontalOrientation(mball, ball);
				if (direction.equals(Constants.Direction.Right) &&
						mball.getXVector() > 0 &&
						collision)
				{
					mball.setXVector(0);
				}
				else if (direction.equals(Constants.Direction.Left) &&
						mball.getXVector() < 0 &&
						collision)
				{
					mball.setXVector(0);
				}
				direction = CollisionEngine.verticalOrientation(mball, ball);
				if (direction.equals(Constants.Direction.Down) &&
						mball.getYVector() > 0 &&
						collision)
				{
					mball.setYVector(0);
				}
				else if (direction.equals(Constants.Direction.Up) &&
						mball.getYVector() < 0 &&
						collision)
				{
					mball.setYVector(0);
				}
			}

			for (BBall ball : bBalls)
			{
				boolean collision = CollisionEngine.collision(mball.copy().move(), ball);
				Constants.Direction direction = CollisionEngine.horizontalOrientation(mball, ball);
				if (direction.equals(Constants.Direction.Right) &&
						mball.getXVector() > 0 &&
						collision)
				{
					mball.setXVector(0);
				}
				else if (direction.equals(Constants.Direction.Left) &&
						mball.getXVector() < 0 &&
						collision)
				{
					mball.setXVector(0);
				}
				direction = CollisionEngine.verticalOrientation(mball, ball);
				if (direction.equals(Constants.Direction.Down) &&
						mball.getYVector() > 0 &&
						collision)
				{
					mball.setYVector(0);
				}
				else if (direction.equals(Constants.Direction.Up) &&
						mball.getYVector() < 0 &&
						collision)
				{
					mball.setYVector(0);
				}
			}

			mball.move();
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		for (MBox box : mBoxes)
		{
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				box.setYVector(-3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				box.setYVector(3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				box.setXVector(-3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				box.setXVector(3);
			}
		}

		for (MBall ball : mBalls)
		{
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				ball.setYVector(-3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				ball.setYVector(3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				ball.setXVector(-3);
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				ball.setXVector(3);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		for (MBox box : mBoxes)
		{
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				box.setYVector(0);
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				box.setYVector(0);
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				box.setXVector(0);
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				box.setXVector(0);
			}
		}

		for (MBall ball : mBalls)
		{
			if (e.getKeyCode() == KeyEvent.VK_UP)
			{
				ball.setYVector(0);
			}
			else if (e.getKeyCode() == KeyEvent.VK_DOWN)
			{
				ball.setYVector(0);
			}
			else if (e.getKeyCode() == KeyEvent.VK_LEFT)
			{
				ball.setXVector(0);
			}
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			{
				ball.setXVector(0);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	@Override
	public void delete()
	{
	}
}
