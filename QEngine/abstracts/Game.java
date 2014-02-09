package abstracts;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * User: Edgar
 * Date: 2/9/14
 * Time: 10:48 AM
 */
public interface Game
{
	public void setup();

	public void render();

	public void update();

	public int getWidth();

	public int getHeight();

	public void keyPressed(KeyEvent e);

	public void keyReleased(KeyEvent e);

	public void mouseEntered(MouseEvent e);

	public void mousePressed(MouseEvent e);

	public void mouseMoved(MouseEvent e);

	public void start();
}
