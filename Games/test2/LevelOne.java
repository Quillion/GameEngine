package test2;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.*;
import BasicObjects.Point;
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
	private List<Box> boxes;
	private List<BBox> bBoxes;
	private MBox character;

	private FollowingCamera camera;

	public LevelOne(Game game)
	{
		super(game);
	}

	@Override
	public void setup()
	{
		boxes = new ArrayList<Box>();
		bBoxes = new ArrayList<BBox>();

		character = new MBox();

		camera = new FollowingCamera(character);
	}

	@Override
	public void load()
	{
		Box box = new Box();
		box.setCoordinates(new BasicObjects.Point(0, getHeight() - 30));
		box.setSize(new Dimensions(getWidth(), 30));
		boxes.add(box);

		box = new Box();
		box.setCoordinates(new BasicObjects.Point(0, 0));
		box.setSize(new Dimensions(getWidth(), 30));
		boxes.add(box);

		BBox bBox = new BBox();
		bBox.setCoordinates(new Point(0, 30));
		bBox.setSize(new Dimensions(30, getHeight() - 60));
		bBox.setOffsets(new Dimensions(5, 0));
		bBoxes.add(bBox);

		bBox = new BBox();
		bBox.setCoordinates(new Point(getWidth() - 30, 30));
		bBox.setSize(new Dimensions(30, getHeight() - 60));
		bBox.setOffsets(new Dimensions(5, 0));
		bBoxes.add(bBox);

		bBox = new BBox();
		bBox.setCoordinates(new Point(getWidth() / 2 - 50, getHeight() / 2 - 50));
		bBox.setSize(new Dimensions(100, 100));
		bBox.setOffsets(new Dimensions(9, 9));
		bBoxes.add(bBox);

		character.setCoordinates(new Point(getWidth() / 3, getHeight() / 3));
		character.setSize(new Dimensions(45, 45));
		character.setOffsets(new Dimensions(7, 7));
		character.setVector(new Point(0, 0));

		camera.setSize(new Dimensions(getWidth(), getHeight()));
		camera.setOffsets(new Dimensions(200, 200));

		setLoaded(true);
	}

	@Override
	public void draw(Graphics2D g)
	{
		for (Box box : boxes)
			camera.draw(box);

		for (BBox box : bBoxes)
			camera.draw(box);

		camera.draw(character);

		camera.drawWhite(g);
		camera.drawCamera(g);
	}

	@Override
	public void update()
	{
		for (Box box : boxes)
		{
			Constants.Direction direction = CollisionEngine.horizontalCollision(character, box);
			if (direction.equals(Constants.Direction.Right) && character.getXVector() > 0)
				character.setXVector(0);
			else if (direction.equals(Constants.Direction.Left) && character.getXVector() < 0)
				character.setXVector(0);
			direction = CollisionEngine.verticalCollision(character, box);
			if (direction.equals(Constants.Direction.Down) && character.getYVector() > 0)
				character.setYVector(0);
			else if (direction.equals(Constants.Direction.Up) && character.getYVector() < 0)
				character.setYVector(0);
		}

		for (BBox box : bBoxes)
		{
			Constants.Direction direction = CollisionEngine.horizontalCollision(character, box);
			if (direction.equals(Constants.Direction.Right) && character.getXVector() > 0)
				character.setXVector(0);
			else if (direction.equals(Constants.Direction.Left) && character.getXVector() < 0)
				character.setXVector(0);
			direction = CollisionEngine.verticalCollision(character, box);
			if (direction.equals(Constants.Direction.Down) && character.getYVector() > 0)
				character.setYVector(0);
			else if (direction.equals(Constants.Direction.Up) && character.getYVector() < 0)
				character.setYVector(0);
		}

		character.move();

		camera.updateCamera();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			character.setYVector(-3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			character.setYVector(3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			character.setXVector(-3);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			character.setXVector(3);
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			character.setYVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			character.setYVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			character.setXVector(0);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			character.setXVector(0);
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
