package abstractFx;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Contains all the basics that a level should contain.
 */
public abstract class Level
{
	private boolean active;
	private boolean loaded;
	private Game    game;
	private int     WIDTH;
	private int     HEIGHT;

	/**
	 * Game must be given to the level so that the level knows parameters such
	 * as screen size and all.
	 *
	 * @param game
	 * 		the game in which this level will be present.
	 */
	public Level(Game game)
	{
		this.active = false;
		this.loaded = false;
		this.game = game;
		this.WIDTH = game.getWidth();
		this.HEIGHT = game.getHeight();
	}

	/**
	 * If the current level is active and is being used.
	 *
	 * @return Status of the current level.
	 */
	public boolean isActive()
	{
		return this.active;
	}

	/**
	 * Sets the status of the current level.
	 *
	 * @param active
	 * 		status of the current level. True means active and false means
	 * 		inactive.
	 */
	public void setActive(boolean active)
	{
		this.active = active;
	}

	/**
	 * Tells you if the current level resources are loaded. If not then I would
	 * suggest using load before setting loaded to true.
	 *
	 * @return True if resources are loaded, false otherwise.
	 */
	public boolean isLoaded()
	{
		return this.loaded;
	}

	/**
	 * Sets the loaded status of the game to what you give it. Should be private
	 * and should be used at the end of the load method, however I am leaving
	 * this public for experimentation.
	 *
	 * @param loaded
	 * 		status of the level's resources. Set to true if resources are loaded,
	 * 		false otherwise.
	 */
	public void setLoaded(boolean loaded)
	{
		this.loaded = loaded;
	}

	/**
	 * Returns the game that this level is attached to. This will be used in the
	 * child classes that extend this class.
	 *
	 * @return Game that this level is attached to.
	 */
	public Game getGame()
	{
		return this.game;
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
	 */
	public abstract void draw();

	/**
	 * This will be called in parallel with draw. Do all your collision logic
	 * and such in here.
	 */
	public abstract void update();

	/**
	 * All the keyboard presses will be registered here.
	 *
	 * @param keyEvent
	 * 		What key the user has pressed.
	 */
	public abstract void keyPressed(KeyEvent keyEvent);

	/**
	 * All the keyboard releases will be registered here.
	 *
	 * @param keyEvent
	 * 		Which key the user has released.
	 */
	public abstract void keyReleased(KeyEvent keyEvent);

	/**
	 * If user presses the mouse button.
	 *
	 * @param mouseEvent
	 * 		All the info about which mouse button was pressed and where.
	 */
	public abstract void mousePressed(MouseEvent mouseEvent);

	/**
	 * If user releases the mouse button.
	 *
	 * @param mouseEvent
	 * 		All the info about which mouse button was pressed and where.
	 */
	public abstract void mouseReleased(MouseEvent mouseEvent);

	/**
	 * If user clicks the mouse button.
	 *
	 * @param mouseEvent
	 * 		All the info about which mouse button was pressed and where.
	 */
	public abstract void mouseClicked(MouseEvent mouseEvent);

	/**
	 * If user drags the mouse while pressed.
	 *
	 * @param mouseEvent
	 * 		All the info about which mouse button was pressed and where.
	 */
	public abstract void mouseDragged(MouseEvent mouseEvent);

	/**
	 * Remove all the variables here. And make sure to set everything to null so
	 * that level can be deleted and done with.
	 */
	public abstract void delete();

	/**
	 * Returns the width that this level is registered to. Width is relative to
	 * game's width, so keep that in mind.
	 *
	 * @return Width of the screen for this level.
	 */
	public int getWidth()
	{
		return WIDTH;
	}

	/**
	 * Returns the height that this level is registered to. Height is relative
	 * to game's height, so keep that in mind.
	 *
	 * @return Height of the screen for this level.
	 */
	public int getHeight()
	{
		return HEIGHT;
	}
}
