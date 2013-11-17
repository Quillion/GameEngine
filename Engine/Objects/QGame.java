package Objects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

/**
 * User: Edgar
 * Date: 11/14/13
 * Time: 9:19 PM
 */
public abstract class QGame implements Runnable
{
	/*
	 * Initialise all of your Objects in here
	 * In our case we have our game object where we will do this
	 */
	protected abstract void setup();

	/*
	 * Method where you paint to the screen
	 */
	protected abstract void render(Graphics2D g);

	/*
	 * Method that handles all the movements that are supposed to happen
	 * So initialise all the x and y changes here
	 * deltaTime tells you how many nanoseconds have passed by since this function
	 * was last called
	 */
	protected abstract void update(int deltaTime);

	public abstract void keyPressed(KeyEvent e);

	public abstract void keyReleased(KeyEvent e);

	public abstract void mouseEntered(MouseEvent e);

	public abstract void mousePressed(MouseEvent e);

	public abstract void mouseMoved(MouseEvent e);

	/*
	 * Keyboard Action listeners
	 * Key esc closes the program by default
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

	/*
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

	/*
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

	/*
	 * Method to initialise graphics that are going to be painted to the screen
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

	/*
	 * Main thread
	 */
	public QGame()
	{
		WIDTH = 640;
		HEIGHT = 480;
		FULL_SCREEN = false;
		title = "QGame";
	}

	/*
	 * Let's see which display mode is suitable for our computer
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

	public void setScreen(int width, int height, boolean fullScreen)
	{
		WIDTH =  width;
		HEIGHT = height;
		FULL_SCREEN = fullScreen;
	}

	public int getWidth()
	{
		return WIDTH;
	}

	public int getHeight()
	{
		return HEIGHT;
	}

	public void setTitle(String title)
	{
		frame.setTitle(title);
	}

	public void init()
	{
		// REMOVE ThIS If YOU DO NOT WANT TO ASK FOR SCREEN MODES
		/* No full screen asking anymore, this annoys me
		int answer = JOptionPane.showConfirmDialog(null, "Do you want to play in full screen", "Full screen?", 0);
        if(answer == 0)
        {
            FULL_SCREEN = true;
        }
        else if(answer == 1)
        {
            FULL_SCREEN = false;
        }
        else
        {
            System.exit(0);
        }
        */

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
