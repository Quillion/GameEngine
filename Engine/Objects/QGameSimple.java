package Objects;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Edgar
 * Date: 11/14/13
 * Time: 9:55 PM
 */
public abstract class QGameSimple extends QGame
{
	private List<Level> levels;

	public QGameSimple()
	{
		super();
		levels = new ArrayList<Level>();
	}

	@Override
	protected void setup()
	{
		for(Level level : levels)
			level.setup();

		levels.get(0).setActive(true);
	}

	@Override
	protected void render(Graphics2D g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		for (Level level : levels)
		{
			if (level.isActive())
			{
				if (level.isLoaded())
				{
					level.draw(g);
				}
				else
				{
					g.drawString("Loading", 100, 100);
				}
			}
		}
	}

	@Override
	protected void update(int deltaTime)
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

	@Override
	public void keyPressed(KeyEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.keyReleased(e);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.mouseEntered(e);
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.mousePressed(e);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		for (Level level : levels)
			if (level.isActive() && level.isLoaded())
				level.mouseMoved(e);
	}

	public void addLevel(Level level)
	{
		levels.add(level);
	}
}
