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
 * Simple thread. Creates everything necessary to run the game, and makes you implement things that are only needed.
 */
public abstract class QGame implements Runnable
{
	/**
	 * Initialise all of your Objects in here
	 * In our case we have our game object where we will do this
	 */
	protected abstract void setup();

	/**
	 * Method where you paint to the screen.
	 *
	 * @param g Graphics to which to draw images to.
	 */
	protected abstract void render(Graphics2D g);

	/**
	 * Method that handles all the movements that are supposed to happen.
	 * So initialise all the x and y changes here.
	 * deltaTime tells you how many nanoseconds have passed by since this function
	 * was last called.
	 *
	 * @param deltaTime how much time has passed since last time update was called.
	 */
	protected abstract void update(int deltaTime);

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
	 * If mouse has entered into the window. Quote useless but I am giving this lesson nonetheless.
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
	 * Keyboard Action listeners.
	 * Key esc closes the program by default, no way you can override that.
	 */
	private class KeyInputHandler extends KeyAdapter
	{
		private QGame game;

		public KeyInputHandler(QGame game)
		{
			this.game = game;
		}

		public void keyPressed(KeyEvent e)
		{
			// USER PRESSED ESC
			if (e.getKeyCode() == 27)
			{
				// IF WE ARE FULL SCREEN IT IS BETTER FOR US TO CLEAR THINGS UP
				if (FULL_SCREEN)
				{
					graphicsDevice.setDisplayMode(originalDisplayMode);
					graphicsDevice.setFullScreenWindow(null);
				}
				System.exit(0);
			}

			game.keyPressed(e);
		}

		public void keyReleased(KeyEvent e)
		{
			game.keyReleased(e);
		}
	}

	/**
	 * Mouse Action Listeners
	 */
	private class MouseControl extends MouseAdapter
	{
		private QGame game;

		public MouseControl(QGame game)
		{
			this.game = game;
		}

		public void mouseEntered(MouseEvent e)
		{
			game.mouseEntered(e);
		}

		public void mousePressed(MouseEvent e)
		{
			game.mousePressed(e);
		}
	}

	/**
	 * Mouse Motion Listeners
	 */
	private class MouseMotion extends MouseMotionAdapter
	{
		private QGame game;

		public MouseMotion(QGame game)
		{
			this.game = game;
		}

		public void mouseMoved(MouseEvent e)
		{
			game.mouseMoved(e);
		}
	}

	/**
	 * All the game logic is here. This function calls setup() first, and once the loop starts it
	 * will keep on calling draw and update alternatively.
	 */
	@Override
	public void run()
	{
		// INITIALIZING VALUES FOR TIME CALCULATIONS
		long beginLoopTime;
		long endLoopTime;
		long currentUpdateTime = System.nanoTime();
		long lastUpdateTime;
		long deltaLoop;

		// SETUP ANYTHING WE NEED BEFORE WE START RUNNING THE GAME
		setup();

		// START THE GAME THIS IS OUR GAME LOOP. RUNNING WILL NEVER BE FALSE I HAD THIS HERE BEFORE WHEN I HAD LEVELS
		// BUt LONG SINCE MOVED IT TO THE GAME OBJECT
		while (running)
		{
			// WE TAKE THE TIME
			beginLoopTime = System.nanoTime();

			// DRAW WHAtEVER WE HAVE TO
			render();

			// THIS CALCULATION IS NOT CRUCIAL REALLY AT THE SCALE WE ARE DEALING WITH BUT THIS IS FOR FUTURE
			lastUpdateTime = currentUpdateTime;
			currentUpdateTime = System.nanoTime();
			update((int) ((currentUpdateTime - lastUpdateTime) / (1000 * 1000)));

			// SEE HOW LONG IT TOOK US TO DO UPDATE AND DRAWING
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			// IF WE ENTER THIS IF STATEMENT THEN WE ARE KIND OF SCREWED OUR GAME LAGS
			if (deltaLoop > desiredDeltaLoop)
			{
				//Do nothing. We are already late. I honestly don't even know why I have this if here :)
			}
			// WE UPDATED AND DREW THINGS QUICK ENOUGH SO THEREFORE WE WILL WAIT IN ORDER TO MAKE THE GAME SMOOTH
			else
			{
				try
				{
					Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
				}
				catch (InterruptedException e)
				{
					//Do nothing, is it even possible for me to fail at putting thread to sleep?
				}
			}
		}
	}

	// FULL SCREEN OR NOT
	private boolean FULL_SCREEN;

	// SCREEN MEASUREMENTS AND REFRESH
	private int WIDTH;
	private int HEIGHT;
	private long desiredFPS = 60;

	// NAME OF WINDOW
	private String title;

	// INITIALIZING SOME FRAME RATE THINGS
	private long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;
	private boolean running = true;

	// INITIALIZING SOME JAVA THINGS
	private JFrame frame;
	private Canvas canvas;
	private BufferStrategy bufferStrategy;

	private GraphicsDevice graphicsDevice;
	private DisplayMode originalDisplayMode;

	// WILL HAVE THREE DISPLAY MODES, 32 bit, 16bit and 8 bit
	private DisplayMode[] MODES = new DisplayMode[]
			{
					new DisplayMode(WIDTH, HEIGHT, 32, 0), new DisplayMode(WIDTH, HEIGHT, 16, 0),
					new DisplayMode(WIDTH, HEIGHT, 8, 0)
			};

	/**
	 * Method to initialise graphics that are going to be painted to the screen.
	 * Don't mess with this method too much, it does things well.
	 */
	private void render()
	{
		// CREATE THE GRAPHICS
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		// MAKE THE PICTURE WHERE WE WILL PAINT EVERYTHING AT ONCE
		g.clearRect(0, 0, WIDTH, HEIGHT);
		// PAINT ANYTHING WE NEED HERE
		render(g);
		g.dispose();
		// SHOW THE WHOLE IMAGE RATHER THAN PAINTING ONE OBJECT AT A TIME
		bufferStrategy.show();
	}

	/**
	 * Main thread initialization.
	 * Default width and height is 640 x 480 and full screen is false.
	 */
	public QGame()
	{
		WIDTH = 640;
		HEIGHT = 480;
		FULL_SCREEN = false;
		title = "QGame";
	}

	/**
	 * Let's see which display mode is suitable for our computer.
	 * Returns you whichever display mode is suitable.
	 * It will first try 32 bit colors, and if fails it will try 16 and then 8 bit.
	 *
	 * @param device which graphics device we should test color depth on.
	 */
	private DisplayMode getBestDisplayMode(GraphicsDevice device)
	{
		// LOOP THROUGH 32 THEN 16 THEN 8 BIT DISPLAY MODES AND SEE WHICH IS THE FIRST ONE WE CAN HANDLE
		for (DisplayMode MODE : MODES)
		{
			DisplayMode[] modes = device.getDisplayModes();
			for (DisplayMode mode : modes)
			{
				if (mode.getWidth() == MODE.getWidth() && mode.getHeight() == MODE.getHeight() && mode.getBitDepth() == MODE.getBitDepth())
				{
					return MODE;
				}
			}
		}
		return null;
	}

	/**
	 * Sets screen to specified values.
	 *
	 * @param width      the width of the screen/window.
	 * @param height     the height of the screen/window.
	 * @param fullScreen if you do set it to true, please do make sure that width and height are correct parameters.
	 */
	public void setScreen(int width, int height, boolean fullScreen)
	{
		WIDTH = width;
		HEIGHT = height;
		FULL_SCREEN = fullScreen;
	}

	/**
	 * Returns the width of the game window.
	 *
	 * @return int which represents the width of this game window.
	 */
	public int getWidth()
	{
		return WIDTH;
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
	 * Sets the title of this game window to what you specify,
	 *
	 * @param title Title of this game window/program.
	 */
	public void setTitle(String title)
	{
		frame.setTitle(title);
	}

	/**
	 * Initializes everything by the specified values.
	 * You can call it right away, or call setScreen() method first.
	 */
	public void init()
	{
		// SET UP THE WINDOW AND GRAPHICS
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		originalDisplayMode = graphicsDevice.getDisplayMode();

		// CREATE NEW Frame
		frame = new JFrame(title);

		if (FULL_SCREEN)
		{
			frame.setUndecorated(true);

			graphicsDevice.setFullScreenWindow(frame);
			if (graphicsDevice.isDisplayChangeSupported())
			{
				graphicsDevice.setDisplayMode(getBestDisplayMode(graphicsDevice));
			}
		}
		else
		{
			frame.setSize(WIDTH, HEIGHT);
		}

		Rectangle bounds = frame.getBounds();

		// CREATE NEW JPanel WITH SPECIFIED WIDTH AND HEIGHT
		JPanel panel = (JPanel) frame.getContentPane();
		// I had to add the -9 to readjust window size
		panel.setPreferredSize(new Dimension(WIDTH - 9, HEIGHT - 9));
		panel.setLayout(null);
		// CREATE A Canvas INSIDE JPanel WITH SPECIFIED WIDTH AND HEIGHT
		canvas = new Canvas();
		// once again I add 1 because java is stupid that's why
		canvas.setBounds(0, 0, WIDTH + 1, HEIGHT + 1);
		//canvas.setBounds(bounds);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);
		// ADD LISTENERS
		canvas.addKeyListener(new KeyInputHandler(this));
		canvas.addMouseListener(new MouseControl(this));
		canvas.addMouseMotionListener(new MouseMotion(this));
		// INITIALIZE THE WINDOW
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);

		// SET GRAPHICS DEVICE
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();
		// FOCUS ON THE WINDOW
		canvas.requestFocus();
	}
}