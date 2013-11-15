package BasicShapes;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * User: Edgar
 * Date: 11/11/13
 * Time: 6:26 PM
 */
public abstract class Level
{
	private boolean active;
	private boolean loaded;

	public Level()
	{
		active = false;
		loaded = false;
	}

	public boolean isActive()
	{
		return active;
	}

	public void setActive(boolean active)
	{
		this.active = active;
	}

	public boolean isLoaded()
	{
		return loaded;
	}

	public void setLoaded(boolean loaded)
	{
		this.loaded = loaded;
	}

	public abstract void load();

	public abstract void draw(Graphics2D g);

	public abstract void update();

	public abstract void keyPressed(KeyEvent e);

	public abstract void keyReleased(KeyEvent e);

	public abstract void mouseEntered(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e);
}
