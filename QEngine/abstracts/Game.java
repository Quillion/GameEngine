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
 * A simple interface for the game.
 * Kind of stupid of me to include something like getWidth and getHeight, but oh well.
 */
public interface Game
{
	/**
	 * All the objects will be loaded here in the game.
	 */
	public void setup();

	/**
	 * All the drawing will be happening here.
	 */
	public void draw(Graphics2D g);

	/**
	 * The game logic will be occurring here.
	 */
	public void update();

	/**
	 * Returns the width of the screen for the current game.
	 *
	 * @return Integer that represents the screen's width.
	 */
	public int getWidth();

	/**
	 * Returns the height of the screen for the current game.
	 *
	 * @return Integer that represents the screen's height.
	 */
	public int getHeight();

	/**
	 * If user presses any key then you will know about it here.
	 *
	 * @param e event that tells which key was pressed.
	 */
	public void keyPressed(KeyEvent e);

	/**
	 * If user releases any key then you will know about it here.
	 *
	 * @param e event that tells which key was released.
	 */
	public void keyReleased(KeyEvent e);

	/**
	 * Notification if mouse has entered the game screen area.
	 *
	 * @param e event that will notify you about mouse entering the game screen area.
	 */
	public void mouseEntered(MouseEvent e);

	/**
	 * Will notify you what mouse key was pressed and at what location.
	 *
	 * @param e event telling you mouse button pressed and the location.
	 */
	public void mousePressed(MouseEvent e);

	/**
	 * Will notify you where the mouse has moved within the game screen.
	 *
	 * @param e event about mouse movements.
	 */
	public void mouseMoved(MouseEvent e);

	/**
	 * Will be called to start the game.
	 * Depending on the game different threads and such will be run.
	 */
	public void start();
}
