package test5;

import Objects.Level;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Game
{
	private int WIDTH, HEIGHT;

	private ArrayList<Level> levels;

	public Game(int WIDTH, int HEIGHT)
	{
		this.WIDTH = WIDTH;
		this.HEIGHT = HEIGHT;

		levels = new ArrayList<Level>();
		levels.add(new LevelOne());
		levels.add(new LevelTwo());

		levels.get(0).setActive(true);
	}

	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		for (Level level : levels)
			if (level.isActive())
				if (level.isLoaded())
					level.draw(g);
				else
					g.drawString("Loading", 100, 100);
	}

	public void update()
	{
		for (int i = 0; i < levels.size(); i++)
		{
			if (levels.get(i).isActive())
			{
				if (levels.get(i).isLoaded())
					levels.get(i).update();
				else
					levels.get(i).load();
				break;
			}
			else if (i < levels.size() - 1)
			{
				levels.get(i + 1).setActive(true);
				levels.remove(i);
			}
			else
			{
				System.exit(0);
			}
		}
	}

	public void keyPressed(KeyEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.keyPressed(e);
	}

	public void keyReleased(KeyEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.keyReleased(e);
	}

	public void mouseEntered(MouseEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.mouseEntered(e);
	}

	public void mousePressed(MouseEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.mousePressed(e);
	}

	public void mouseMoved(MouseEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.mouseMoved(e);
	}

	public int getWIDTH()
	{
		return WIDTH;
	}

	public int getHEIGHT()
	{
		return HEIGHT;
	}
}
