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
public abstract class GameDoubleThreaded extends JFrame implements Game
{
	/**
	 * Initialise all of your Objects in here
	 * In our case we have our game object where we will do this
	 */
	public abstract void setup();

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
		private GameDoubleThreaded game;

		public KeyInputHandler(GameDoubleThreaded game)
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
	private class MouseControl extends MouseAdapter
	{
		private GameDoubleThreaded game;

		public MouseControl(GameDoubleThreaded game)
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
	private class MouseMotion extends MouseMotionAdapter
	{
		private GameDoubleThreaded game;

		public MouseMotion(GameDoubleThreaded game)
		{
			this.game = game;
		}

		public void mouseMoved(MouseEvent e)
		{
			this.game.mouseMoved(e);
		}
	}

	public void logic()
	{
		// INITIALIZING VALUES FOR TIME CALCULATIONS
		long beginLoopTime;
		long endLoopTime;
		long deltaLoop;

		// SETUP ANYTHING WE NEED BEFORE WE START RUNNING THE GAME
		setup();

		// START THE GAME THIS IS OUR GAME LOOP. RUNNING WILL NEVER BE FALSE I HAD THIS HERE BEFORE WHEN I HAD LEVELS
		// BUT LONG SINCE MOVED IT TO THE GAME OBJECT
		while (true)
		{
			// WE TAKE THE TIME
			beginLoopTime = System.nanoTime();

			// THIS CALCULATION IS NOT CRUCIAL REALLY AT THE SCALE WE ARE DEALING WITH BUT THIS IS FOR FUTURE
			update();

			// SEE HOW LONG IT TOOK US TO DO UPDATE AND DRAWING
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			// WE UPDATED AND DREW THINGS QUICK ENOUGH SO THEREFORE WE WILL WAIT IN ORDER TO MAKE THE GAME SMOOTH
			if (deltaLoop <= this.desiredDeltaLoop)
			{
				try
				{
					Thread.sleep((this.desiredDeltaLoop - deltaLoop) / (1000 * 1000));
				}
				catch (InterruptedException e)
				{
					//Do nothing, is it even possible for me to fail at putting thread to sleep?
				}
			}
		}
	}

	public void draw()
	{
		// INITIALIZING VALUES FOR TIME CALCULATIONS
		long beginLoopTime;
		long endLoopTime;
		long deltaLoop;

		// START THE GAME THIS IS OUR GAME LOOP. RUNNING WILL NEVER BE FALSE I HAD THIS HERE BEFORE WHEN I HAD LEVELS
		// BUT LONG SINCE MOVED IT TO THE GAME OBJECT
		while (true)
		{
			// WE TAKE THE TIME
			beginLoopTime = System.nanoTime();

			// THIS CALCULATION IS NOT CRUCIAL REALLY AT THE SCALE WE ARE DEALING WITH BUT THIS IS FOR FUTURE
			render();

			// SEE HOW LONG IT TOOK US TO DO UPDATE AND DRAWING
			endLoopTime = System.nanoTime();
			deltaLoop = endLoopTime - beginLoopTime;

			// WE UPDATED AND DREW THINGS QUICK ENOUGH SO THEREFORE WE WILL WAIT IN ORDER TO MAKE THE GAME SMOOTH
			if (deltaLoop <= this.desiredDeltaLoop)
			{
				try
				{
					Thread.sleep((this.desiredDeltaLoop - deltaLoop) / (1000 * 1000));
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
	private boolean EXIT_ON_ESC;

	// SCREEN MEASUREMENTS AND REFRESH
	private int WIDTH;
	private int HEIGHT;
	private long desiredFPS = 60;

	// INITIALIZING SOME FRAME RATE THINGS
	private long desiredDeltaLoop = (1000 * 1000 * 1000) / desiredFPS;

	// INITIALIZING SOME JAVA THINGS
	private BufferStrategy bufferStrategy;

	private GraphicsDevice graphicsDevice;
	private DisplayMode originalDisplayMode;
	private Font graphicsFont;

	private Runnable logic;
	private Runnable draw;

	// WILL HAVE THREE DISPLAY MODES, 32 bit, 16bit and 8 bit
	private DisplayMode[] MODES = new DisplayMode[]
			{
					new DisplayMode(this.WIDTH, this.HEIGHT, 32, 0), new DisplayMode(this.WIDTH, this.HEIGHT, 16, 0),
					new DisplayMode(this.WIDTH, this.HEIGHT, 8, 0)
			};

	/**
	 * Method to initialise graphics that are going to be painted to the screen.
	 * Don't mess with this method too much, it does things well.
	 */
	public void render()
	{
		// CREATE THE GRAPHICS
		Graphics2D g = (Graphics2D) this.bufferStrategy.getDrawGraphics();
		// MAKE THE PICTURE WHERE WE WILL PAINT EVERYTHING AT ONCE
		g.clearRect(0, 0, this.WIDTH, this.HEIGHT);
		g.setFont(this.graphicsFont);
		// PAINT ANYTHING WE NEED HERE
		render(g);
		g.dispose();
		// SHOW THE WHOLE IMAGE RATHER THAN PAINTING ONE OBJECT AT A TIME
		this.bufferStrategy.show();
	}

	/**
	 * Main thread initialization.
	 * Default width and height is 640 x 480 and full screen is false.
	 */
	public GameDoubleThreaded()
	{
		this.WIDTH = 640;
		this.HEIGHT = 480;
		this.FULL_SCREEN = false;
		this.EXIT_ON_ESC = true;
		this.setTitle("Game");
		this.graphicsFont = new Font("TimesRoman", Font.BOLD, 90);
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
	 * @param exitOnEsc True if you want game to close eon ESC, false otherwise.
	 */
	public void exitOnEsc(boolean exitOnEsc)
	{
		this.EXIT_ON_ESC = exitOnEsc;
	}

	/**
	 * Initializes everything by the specified values.
	 * You can call it right away, or call setScreen() method first.
	 */
	public void init()
	{
		// SET UP THE WINDOW AND GRAPHICS
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		this.graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		this.originalDisplayMode = this.graphicsDevice.getDisplayMode();

		if (FULL_SCREEN)
		{
			this.setUndecorated(true);

			this.graphicsDevice.setFullScreenWindow(this);
			if (this.graphicsDevice.isDisplayChangeSupported())
			{
				this.graphicsDevice.setDisplayMode(getBestDisplayMode(this.graphicsDevice));
			}
		}
		else
		{
			this.setSize(this.WIDTH, this.HEIGHT);
		}

		// CREATE NEW JPanel WITH SPECIFIED WIDTH AND HEIGHT
		JPanel panel = (JPanel) this.getContentPane();
		// I had to add the -9 to readjust window size
		panel.setPreferredSize(new Dimension(this.WIDTH - 9, this.HEIGHT - 9));
		panel.setLayout(null);
		// CREATE A Canvas INSIDE JPanel WITH SPECIFIED WIDTH AND HEIGHT
		Canvas canvas = new Canvas();
		// once again I add 1 because java is stupid that's why
		canvas.setBounds(0, 0, this.WIDTH + 1, this.HEIGHT + 1);
		canvas.setIgnoreRepaint(true);

		panel.add(canvas);
		// ADD LISTENERS
		canvas.addKeyListener(new KeyInputHandler(this));
		canvas.addMouseListener(new MouseControl(this));
		canvas.addMouseMotionListener(new MouseMotion(this));
		// INITIALIZE THE WINDOW
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);

		// SET GRAPHICS DEVICE
		canvas.createBufferStrategy(2);
		this.bufferStrategy = canvas.getBufferStrategy();
		// FOCUS ON THE WINDOW
		canvas.requestFocus();

		this.logic = new Runnable()
		{
			@Override
			public void run()
			{
				logic();
			}
		};

		this.draw = new Runnable()
		{
			@Override
			public void run()
			{
				draw();
			}
		};
	}

	public void start()
	{
		Thread logic = new Thread(this.logic);
		Thread draw = new Thread(this.draw);

		logic.setPriority(Thread.MAX_PRIORITY);
		draw.setPriority(Thread.NORM_PRIORITY);

		logic.start();
		draw.start();
	}
}
