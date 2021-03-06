package test3_multiplayer;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstractSwing.Level;
import basicObjects.Dimensions;
import basicObjects.Point;
import basicObjects.camera.MultiplayerCamera;
import basicObjects.shapes.BBox;
import basicObjects.shapes.MBox;
import constants.Constants;
import logic.CollisionEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelOne extends Level
{
	private List<BBox> walls;
	private MBox       character1;
	private MBox       character2;

	private MultiplayerCamera camera;

	private MultiplayerCamera.Mode mode;

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
		character2 = new MBox();

		camera = new MultiplayerCamera(character1, character2);

		mode = MultiplayerCamera.Mode.Horizontal;
		camera.setMode(mode);

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

		character2.setCoordinates(new Point(250, 250));
		character2.setSize(new Dimensions(45, 45));
		character2.setOffsets(new Dimensions(7, 7));
		character2.setVector(new Point(0, 0));

		camera.setSize(new Dimensions(getWidth(), getHeight()),
					   new Dimensions(getWidth() / 2 - 70,
									  getHeight() / 2 - 70));

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
		camera.draw(character2);

		camera.drawCamera();
		if (black)
		{
			camera.drawClear(g);
		}
		else
		{
			camera.drawWhite(g);
		}

		g.drawString(String.valueOf(mode), 0, 70);

		if (black)
		{
			g.setColor(Color.WHITE);
		}
		g.setFont(g.getFont().deriveFont(Font.PLAIN, 12));
		g.drawString("Change Mode: M", 10, 210);
		g.drawString("Change color: C", 10, 220);
	}

	@Override
	public void update()
	{
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

			direction = CollisionEngine.horizontalCollision(character2, box);
			if (direction.equals(Constants.Direction.Right) && character2
					.getXVector() > 0)
			{
				character2.setXVector(0);
			}
			else if (direction.equals(Constants.Direction.Left) && character2
					.getXVector() < 0)
			{
				character2.setXVector(0);
			}
			direction = CollisionEngine.verticalCollision(character2, box);
			if (direction.equals(Constants.Direction.Down) && character2
					.getYVector() > 0)
			{
				character2.setYVector(0);
			}
			else if (direction.equals(Constants.Direction.Up) && character2
					.getYVector() < 0)
			{
				character2.setYVector(0);
			}
		}

		character1.move();
		character2.move();

		camera.updateCamera();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			character1.setYVector(-3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			character1.setYVector(3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			character1.setXVector(-3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			character1.setXVector(3);
		}

		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			character2.setYVector(-3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			character2.setYVector(3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			character2.setXVector(-3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			character2.setXVector(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			character1.setYVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			character1.setYVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			character1.setXVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			character1.setXVector(0);
		}

		if (e.getKeyCode() == KeyEvent.VK_W)
		{
			character2.setYVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			character2.setYVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			character2.setXVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			character2.setXVector(0);
		}

		if (e.getKeyCode() == KeyEvent.VK_M)
		{
			if (mode.equals(MultiplayerCamera.Mode.Horizontal))
			{
				mode = MultiplayerCamera.Mode.Vertical;
			}
			else if (mode.equals(MultiplayerCamera.Mode.Vertical))
			{
				mode = MultiplayerCamera.Mode.One;
			}
			else if (mode.equals(MultiplayerCamera.Mode.One))
			{
				mode = MultiplayerCamera.Mode.Smart;
			}
			else
			{
				mode = MultiplayerCamera.Mode.Horizontal;
			}
			camera.setMode(mode);
		}

		if (e.getKeyCode() == KeyEvent.VK_C)
		{
			black = !black;
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
