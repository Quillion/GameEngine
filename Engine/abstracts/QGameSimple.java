package abstracts;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Simplified abstract class.
 * Just add levels and it will handle them properly without your supervision.
 */
public abstract class QGameSimple extends QGame
{
	private List<Level> levels;

	/**
	 * Starts the class. Nothing important here.
	 */
	public QGameSimple()
	{
		super();
		levels = new ArrayList<Level>();
	}

	/**
	 * All it does is call setup() for all levels and then sets level 1 to active.
	 */
	@Override
	protected void setup()
	{
		for (Level level : levels)
			level.setup();

		if (levels.size() > 0)
			levels.get(0).setActive(true);
	}

	/**
	 * Calls render for the currently loaded and active level.
	 *
	 * @param g Graphics to which to draw images to.
	 */
	@Override
	protected void render(Graphics2D g)
	{
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
					g.drawString("Loading level.", 100, 100);
				}
			}
		}
	}

	/**
	 * Calls update function for currently active and loaded level.
	 * As soon as level becomes unactive it will be deleted from this game object and
	 * the next level will become active, and then will become loaded.
	 *
	 * @param deltaTime how much time has passed since last time update was called.
	 */
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
				levels.get(i).delete();
				levels.remove(i);
			}
			else
			{
				System.exit(0);
			}
		}
	}

	/**
	 * All the keyboard presses will be registered here, and passed onto currently active level.
	 *
	 * @param e What key the user has pressed.
	 */
	@Override
	public void keyPressed(KeyEvent e)
	{
		for (Level level : levels)
		{
			if (level.isActive() && level.isLoaded())
			{
				level.keyPressed(e);
				break;
			}
		}
	}

	/**
	 * All the keyboard releases will be registered here,
	 * and passed onto currently active level.
	 *
	 * @param e Which key the user has released.
	 */
	@Override
	public void keyReleased(KeyEvent e)
	{
		for (Level level : levels)
		{
			if (level.isActive() && level.isLoaded())
			{
				level.keyReleased(e);
				break;
			}
		}
	}

	/**
	 * If mouse has entered into the window. Quite useless but I am giving this lesson nonetheless.
	 * All mouse actions are passed to the currently active level only.
	 *
	 * @param e Event which shows where the mouse has entered.
	 */
	@Override
	public void mouseEntered(MouseEvent e)
	{
		for (Level level : levels)
		{
			if (level.isActive() && level.isLoaded())
			{
				level.mouseEntered(e);
				break;
			}
		}
	}

	/**
	 * If user presses the mouse button.
	 * All mouse presses are passed to the currently active level only.
	 *
	 * @param e All the info about which mouse button was pressed and where.
	 */
	@Override
	public void mousePressed(MouseEvent e)
	{
		for (Level level : levels)
		{
			if (level.isActive() && level.isLoaded())
			{
				level.mousePressed(e);
				break;
			}
		}
	}

	/**
	 * Mouse movement actions will go here.
	 * All mouse motions are passed to the currently active level only.
	 *
	 * @param e Where the mouse is currently located.
	 */
	@Override
	public void mouseMoved(MouseEvent e)
	{
		for (Level level : levels)
		{
			if (level.isActive() && level.isLoaded())
			{
				level.mouseMoved(e);
				break;
			}
		}
	}

	/**
	 * Adds a level to the current game.
	 * All levels are stored into linked list.
	 * They will be called into order of how they are added.
	 *
	 * @param level The level you would like to add.
	 */
	public void addLevel(Level level)
	{
		levels.add(level);
	}
}
