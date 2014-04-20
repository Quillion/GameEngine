package abstractSwing;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import javax.swing.*;
import java.awt.*;

/**
 * Simple thread. Creates everything necessary to run the game, and makes you
 * implement things that are only needed.
 */
public abstract class GameDoubleThreaded extends GameAbstract implements Game
{
	/**
	 * This one is the logic loop. It first does setup and then proceeds to
	 * doing update infinitely.
	 */
	private void logic()
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
					Thread.sleep(
							(this.desiredDeltaLoop - deltaLoop) / (1000 * 1000));
				}
				catch (InterruptedException e)
				{
					//Do nothing, is it even possible for me to fail at putting thread to sleep?
				}
			}
		}
	}

	/**
	 * This is the draw loop. Set up does not happen here, only drawing does.
	 */
	private void draw()
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
					Thread.sleep(
							(this.desiredDeltaLoop - deltaLoop) / (1000 * 1000));
				}
				catch (InterruptedException e)
				{
					//Do nothing, is it even possible for me to fail at putting thread to sleep?
				}
			}
		}
	}

	private Runnable logic;
	private Runnable draw;

	/**
	 * Method to initialise graphics that are going to be painted to the screen.
	 * Don't mess with this method too much, it does things well.
	 */
	private void render()
	{
		// CREATE THE GRAPHICS
		Graphics2D g = (Graphics2D) this.bufferStrategy.getDrawGraphics();
		// MAKE THE PICTURE WHERE WE WILL PAINT EVERYTHING AT ONCE
		g.clearRect(0, 0, this.WIDTH, this.HEIGHT);
		g.setFont(this.graphicsFont);
		// PAINT ANYTHING WE NEED HERE
		draw(g);
		g.dispose();
		// SHOW THE WHOLE IMAGE RATHER THAN PAINTING ONE OBJECT AT A TIME
		this.bufferStrategy.show();
	}

	/**
	 * Main thread initialization. Default width and height is 640 x 480 and
	 * full screen is false.
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
	 * Initializes everything by the specified values. You can call it right
	 * away, or call setScreen() method first.
	 */
	public void init()
	{
		// SET UP THE WINDOW AND GRAPHICS
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		this.graphicsDevice = graphicsEnvironment.getDefaultScreenDevice();
		this.originalDisplayMode = this.graphicsDevice.getDisplayMode();

		if (FULL_SCREEN)
		{
			this.setUndecorated(true);

			this.graphicsDevice.setFullScreenWindow(this);
			if (this.graphicsDevice.isDisplayChangeSupported())
			{
				this.graphicsDevice.setDisplayMode(
						getBestDisplayMode(this.graphicsDevice));
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

	/**
	 * Start running the game loop.
	 */
	public void start()
	{
		Thread draw = new Thread(this.draw);
		Thread logic = new Thread(this.logic);

		draw.setPriority(Thread.NORM_PRIORITY);
		logic.setPriority(Thread.MAX_PRIORITY);

		draw.start();
		logic.start();
	}
}
