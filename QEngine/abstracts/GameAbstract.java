package abstracts;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

/**
 * Simply extends JFrame and initializes some basic things.
 */
public abstract class GameAbstract extends JFrame implements Game
{
	// FULL SCREEN OR NOT
	protected boolean FULL_SCREEN;
	protected boolean EXIT_ON_ESC;

	// SCREEN MEASUREMENTS AND REFRESH
	protected int WIDTH;
	protected int HEIGHT;
	protected long desiredFPS = 60;

	// INITIALIZING SOME FRAME RATE THINGS
	protected long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;

	// INITIALIZING SOME JAVA THINGS
	protected BufferStrategy bufferStrategy;

	protected GraphicsDevice graphicsDevice;
	protected DisplayMode    originalDisplayMode;
	protected Font           graphicsFont;

	/**
	 * Sets screen to specified values.
	 *
	 * @param width
	 * 		the width of the screen/window.
	 * @param height
	 * 		the height of the screen/window.
	 * @param fullScreen
	 * 		if you do set it to true, please do make sure that width and height are correct parameters.
	 */
	public void setScreen(int width, int height, boolean fullScreen)
	{
		this.WIDTH = width;
		this.HEIGHT = height;
		this.FULL_SCREEN = fullScreen;
	}

	/**
	 * Returns the width of the game window.
	 *
	 * @return int which represents the width of this game window.
	 */
	public int getWidth()
	{
		return this.WIDTH;
	}

	/**
	 * Returns the height of this game window.
	 *
	 * @return int which will represent the height of this game window.
	 */
	public int getHeight()
	{
		return HEIGHT;
	}

	/**
	 * Specifies whether or not to set up ESC button to automatically close the program.
	 *
	 * @param exitOnEsc
	 * 		True if you want game to close eon ESC, false otherwise.
	 */
	public void exitOnEsc(boolean exitOnEsc)
	{
		this.EXIT_ON_ESC = exitOnEsc;
	}

	// WILL HAVE THREE DISPLAY MODES, 32 bit, 16bit and 8 bit
	private DisplayMode[] MODES = new DisplayMode[]
			{
					new DisplayMode(this.WIDTH, this.HEIGHT, 32, 0), new DisplayMode(this.WIDTH, this.HEIGHT, 16, 0),
					new DisplayMode(this.WIDTH, this.HEIGHT, 8, 0)
			};

	/**
	 * Let's see which display mode is suitable for our computer. Returns you whichever display mode is suitable. It
	 * will first try 32 bit colors, and if fails it will try 16 and then 8 bit.
	 *
	 * @param device
	 * 		which graphics device we should test color depth on.
	 */
	protected DisplayMode getBestDisplayMode(GraphicsDevice device)
	{
		// LOOP THROUGH 32 THEN 16 THEN 8 BIT DISPLAY MODES AND SEE WHICH IS THE FIRST ONE WE CAN HANDLE
		for (DisplayMode MODE : MODES)
		{
			DisplayMode[] modes = device.getDisplayModes();
			for (DisplayMode mode : modes)
			{
				if (mode.getWidth() == MODE.getWidth() && mode.getHeight() == MODE.getHeight() && mode
						.getBitDepth() == MODE.getBitDepth())
				{
					return MODE;
				}
			}
		}
		return null;
	}

	/**
	 * Keyboard Action listeners. Key esc closes the program by default, no way you can override that.
	 */
	protected class KeyInputHandler extends KeyAdapter
	{
		private Game game;

		public KeyInputHandler(Game game)
		{
			this.game = game;
		}

		public void keyPressed(KeyEvent e)
		{
			// USER PRESSED ESC AND WE WANT TO CLOSE ON ESC
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE && EXIT_ON_ESC)
			{
				// IF WE ARE FULL SCREEN IT IS BETTER FOR US TO CLEAR THINGS UP
				if (FULL_SCREEN)
				{
					graphicsDevice.setDisplayMode(originalDisplayMode);
					graphicsDevice.setFullScreenWindow(null);
				}
				System.exit(0);
			}

			this.game.keyPressed(e);
		}

		public void keyReleased(KeyEvent e)
		{
			this.game.keyReleased(e);
		}
	}

	/**
	 * Mouse Action Listeners
	 */
	protected class MouseControl extends MouseAdapter
	{
		private Game game;

		public MouseControl(Game game)
		{
			this.game = game;
		}

		public void mouseEntered(MouseEvent e)
		{
			this.game.mouseEntered(e);
		}

		public void mousePressed(MouseEvent e)
		{
			this.game.mousePressed(e);
		}
	}

	/**
	 * Mouse Motion Listeners
	 */
	protected class MouseMotion extends MouseMotionAdapter
	{
		private Game game;

		public MouseMotion(Game game)
		{
			this.game = game;
		}

		public void mouseMoved(MouseEvent e)
		{
			this.game.mouseMoved(e);
		}
	}
}
