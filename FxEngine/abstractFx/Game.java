package abstractFx;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public interface Game
{
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

	public void setup();

	public void update();

	public void draw();

	public void keyPressed(KeyEvent keyEvent);

	public void keyReleased(KeyEvent keyEvent);

	public void mousePressed(MouseEvent mouseEvent);

	public void mouseReleased(MouseEvent mouseEvent);

	public void mouseClicked(MouseEvent mouseEvent);

	public void mouseDragged(MouseEvent mouseEvent);
}
