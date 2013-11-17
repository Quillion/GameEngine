package abstracts;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Contains all the basics that a level should contain.
 */
public abstract class Level
{
	private boolean active;
	private boolean loaded;
	private QGame game;

	/**
	 * Game must be given to the level so that the level knows parameters such as screen size and all.
	 *
	 * @param game the game in which this level will be present.
	 */
	public Level(QGame game)
	{
		active = false;
		loaded = false;
		this.game = game;
	}

	/**
	 * If the current level is active and is being used.
	 *
	 * @return Status of the current level.
	 */
	public boolean isActive()
	{
		return active;
	}

	/**
	 * Sets the status of the current level.
	 *
	 * @param active status of the current level. True means active and false means inactive.
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * Tells you if the current level resources are loaded.
	 * If not then I would suggest using load before setting loaded to true.
	 *
	 * @return True if resources are loaded, false otherwise.
	 */
	public boolean isLoaded()
	{
		return loaded;
	}

	/**
	 * Sets the loaded status of the game to what you give it.
	 * Should be private and should be used at the end of the load method, however I am leaving this public for experimentation.
	 *
	 * @param loaded status of the level's resources. Set to true if resources are loaded, false otherwise.
	 */
	public void setLoaded(boolean loaded)
	{
		this.loaded = loaded;
	}

	/**
	 * Returns the game that this level is attached to. This will be used in the child classes that extend this class.
	 *
	 * @return QGame that this level is attached to.
	 */
	public QGame getGame()
	{
		return game;
	}

	/**
	 * Setup all of your variables here, however do not load them here.
	 */
	public abstract void setup();

	/**
	 * Load all of your images and sounds and such here.
	 */
	public abstract void load();

	/**
	 * Draw everything needed here.
	 *
	 * @param g Graphics to which to draw images to.
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * This will be called in parallel with draw. Do all your collision logic and such in here.
	 */
	public abstract void update();

	/**
	 * All the keyboard presses will be registered here.
	 *
	 * @param e What key the user has pressed.
	 */
	public abstract void keyPressed(KeyEvent e);

	/**
	 * All the keyboard releases will be registered here.
	 *
	 * @param e Which key the user has released.
	 */
	public abstract void keyReleased(KeyEvent e);

	/**
	 * If mouse has entered into the window. Quite useless but I am giving this lesson nonetheless.
	 *
	 * @param e Event which shows where the mouse has entered.
	 */
	public abstract void mouseEntered(MouseEvent e);

	/**
	 * If user presses the mouse button.
	 *
	 * @param e All the info about which mouse button was pressed and where.
	 */
	public abstract void mousePressed(MouseEvent e);

	/**
	 * Mouse movement actions will go here.
	 *
	 * @param e Where the mouse is currently located.
	 */
	public abstract void mouseMoved(MouseEvent e);

	/**
	 * Remove all the variables here. And make sure to set everything to null so that level can be deleted and done with.
	 */
	public abstract void delete();
}
