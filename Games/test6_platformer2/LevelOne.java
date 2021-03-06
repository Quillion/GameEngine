package test6_platformer2;

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
import logic.platformer.CollisionEngine;
import logic.platformer.Engine;
import platformer.MBox;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class LevelOne extends Level
{
	private List<BBox> walls;
	private List<BBox> platforms;
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
		platforms = new ArrayList<BBox>();

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
		box.setCoordinates(new Point(30, 500));
		box.setSize(new Dimensions(150, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(420, 500));
		box.setSize(new Dimensions(150, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(30, 100));
		box.setSize(new Dimensions(150, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(420, 100));
		box.setSize(new Dimensions(150, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(200, 400));
		box.setSize(new Dimensions(200, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(200, 200));
		box.setSize(new Dimensions(200, 30));
		box.setOffsets(new Dimensions(0, 5));
		walls.add(box);

		box = new BBox();
		box.setCoordinates(new Point(30, 300));
		box.setSize(new Dimensions(540, 30));
		box.setOffsets(new Dimensions(0, 5));
		platforms.add(box);

		character1.setCoordinates(new Point(100, 360));
		character1.setSize(new Dimensions(45, 45));
		character1.setOffsets(new Dimensions(7, 7));
		character1.setVector(new Point(0, 0));
		character1.setKeys(
				new int[]{KeyEvent.VK_LEFT, KeyEvent.VK_UP, KeyEvent.VK_RIGHT,
						  KeyEvent.VK_DOWN, KeyEvent.VK_UP}
		);
		character1.setAcceleration(0.15);
		character1.setMaxSpeed(3);
		character1.setFriction(0.3);
		character1.setJump(9);
		character1.setGravity(0.3);

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

		for (BBox box : platforms)
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
		g.drawString("Jump: " + character1.getJump(), 10, 40);
		g.drawString("Gravity: " + character1.getGravity(), 10, 50);
		g.drawString("----------------" + character1.getGravity(), 10, 60);
		g.drawString("Controls: ", 10, 70);
		g.drawString("--------", 10, 80);
		g.drawString("q, a, w, s, e, d", 10, 90);
		g.drawString("r, f, t, g, c, v, b", 10, 100);
	}

	@Override
	public void update()
	{
		Engine.setControlSpeeds(character1);
		for (BBox box : walls)
		{
			CollisionEngine.wallCollision(character1, box);
		}

		for (BBox box : platforms)
		{
			CollisionEngine.platformCollision(character1, box);
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
		else if (e.getKeyCode() == KeyEvent.VK_R)
		{
			character1.incrementJump(0.5);
		}
		else if (e.getKeyCode() == KeyEvent.VK_F)
		{
			character1.incrementJump(-0.5);
		}
		else if (e.getKeyCode() == KeyEvent.VK_V)
		{
			character1.setJump(-character1.getJump());
		}
		else if (e.getKeyCode() == KeyEvent.VK_T)
		{
			character1.incrementGravity(0.05);
		}
		else if (e.getKeyCode() == KeyEvent.VK_G)
		{
			character1.incrementGravity(-0.05);
		}
		else if (e.getKeyCode() == KeyEvent.VK_B)
		{
			character1.setGravity(-character1.getGravity());
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
