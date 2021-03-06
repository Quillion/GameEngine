package test4_dungeon1;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstractSwing.Level;
import basicObjects.Dimensions;
import basicObjects.Point;
import basicObjects.camera.FollowingCamera;
import basicObjects.shapes.BBox;
import constants.Constants;
import dungeon.MBox;
import logic.CollisionEngine;
import logic.dungeon.Engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelOne extends Level
{
	private List<BBox> walls;
	private MBox       character1;

	private FollowingCamera camera;

	private boolean black;

	public LevelOne(Game game)
	{
		super(game);
	}

	@Override
	public void setup()
	{
		walls = new ArrayList<BBox>();

		character1 = new MBox();

		camera = new FollowingCamera(character1);

		black = true;
	}

	@Override
	public void load()
	{
		BBox box = new BBox();
		box.setCoordinates(new Point(0, 0));
		box.setSize(new Dimensions(600, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(0, 600));
		box.setSize(new Dimensions(600, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(0, 30));
		box.setSize(new Dimensions(30, 570));
		box.setOffsets(new Dimensions(5, 0));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(570, 30));
		box.setSize(new Dimensions(30, 570));
		box.setOffsets(new Dimensions(5, 0));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(200, 200));
		box.setSize(new Dimensions(370, 30));
		box.setOffsets(new Dimensions(5, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(200, 230));
		box.setSize(new Dimensions(30, 170));
		box.setOffsets(new Dimensions(5, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(200, 400));
		box.setSize(new Dimensions(200, 30));
		box.setOffsets(new Dimensions(5, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(370, 300));
		box.setSize(new Dimensions(30, 100));
		box.setOffsets(new Dimensions(5, 5));
		walls.add(box);

		character1.setCoordinates(new Point(100, 100));
		character1.setSize(new Dimensions(45, 45));
		character1.setOffsets(new Dimensions(7, 7));
		character1.setVector(new Point(0, 0));
		character1.setKeys(
				new int[]{KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_RIGHT,
						  KeyEvent.VK_DOWN});
		character1.setAcceleration(0.15);
		character1.setMaxSpeed(3);
		character1.setFriction(0.3);

		camera.setSize(new Dimensions(getWidth(), getHeight()));
		camera.setOffsets(new Dimensions(400, 200));

		setLoaded(true);
	}

	@Override
	public void draw(Graphics2D g)
	{
		for (BBox box : walls)
		{
			camera.draw(box);
		}

		camera.draw(character1);

		camera.drawCamera();
		if (black)
		{
			camera.drawClear(g);
			g.setColor(Color.WHITE);
		}
		else
		{
			camera.drawWhite(g);
		}
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 12));
		g.drawString("Max speed: " + character1.getMaxSpeed(), 10, 10);
		g.drawString("Acceleration: " + character1.getAcceleration(), 10, 20);
		g.drawString("Friction: " + character1.getFriction(), 10, 30);
	}

	@Override
	public void update()
	{
		Engine.setControlSpeeds(character1);
		for (BBox box : walls)
		{
			Constants.Direction direction = CollisionEngine
					.horizontalCollision(character1, box);
			if (direction.equals(Constants.Direction.Right) && character1
					.getXVector() > 0)
			{
				character1.setXVector(0);
			}
			else if (direction.equals(Constants.Direction.Left) && character1
					.getXVector() < 0)
			{
				character1.setXVector(0);
			}
			direction = CollisionEngine.verticalCollision(character1, box);
			if (direction.equals(Constants.Direction.Down) && character1
					.getYVector() > 0)
			{
				character1.setYVector(0);
			}
			else if (direction.equals(Constants.Direction.Up) && character1
					.getYVector() < 0)
			{
				character1.setYVector(0);
			}
		}

		character1.move();

		camera.updateCamera();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		Engine.keyPressed(character1, e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		Engine.keyReleased(character1, e.getKeyCode());

		if (e.getKeyCode() == KeyEvent.VK_C)
		{
			black = !black;
		}
		else if (e.getKeyCode() == KeyEvent.VK_Q)
		{
			character1.incrementMaxSpeed(0.1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			character1.incrementMaxSpeed(-0.1);
		}
		else if (e.getKeyCode() == KeyEvent.VK_W)
		{
			character1.incrementAcceleration(0.05);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			character1.incrementAcceleration(-0.05);
		}
		else if (e.getKeyCode() == KeyEvent.VK_E)
		{
			character1.incrementFriction(0.05);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			character1.incrementFriction(-0.05);
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
