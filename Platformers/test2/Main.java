package test2;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

public class Main implements Runnable
{
    // FULL SCREEN OR NOT
    public static boolean FULL_SCREEN = false;

    // SCREEN MEASUREMENTS AND REFRESH
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private long desiredFPS = 60;

    // NAME OF WINDOW
    private String title = "Base";

    // GAME OBJECT INIT
    private Game game;

    /*
     * Initialise all of your Objects in here
     * In our case we have our game object where we will do this
     */
    public void setup()
    {
        game = new Game(WIDTH, HEIGHT);
    }

    /*
     * Method that handles all the movements that are supposed to happen
     * So initialise all the x and y changes here
     * deltaTime tells you how many nanoseconds have passed by since this function
     * was last called
     */
    protected void update(int deltaTime)
    {
        game.update();
    }

    /*
     * Method where you paint to the screen
     */
    protected void render(Graphics2D g)
    {
        game.draw(g);
    }

    /*
     * Keyboard Action listeners
     * Key esc closes the program by default
     */
    private class KeyInputHandler extends KeyAdapter
    {
        public void keyPressed(KeyEvent e)
        {
            // TELL GAME OBJECT WHAT WE PRESSED
            game.keyPressed(e);

            // USER PRESSED ESC
            if(e.getKeyCode()==27)
            {
                // IF WE ARE FULL SCREEN IT IS BETTER FOR US TO CLEAR THINGS UP
                if(FULL_SCREEN)
                {
                    graphicsDevice.setDisplayMode(originalDisplayMode);
                    graphicsDevice.setFullScreenWindow(null);
                }
                System.exit(0);
            }
        }

        public void keyReleased(KeyEvent e)
        {
            // TELL GAME OBJECT WHAT KEY WAS RELEASED
            game.keyReleased(e);
        }
    }

    /*
     * Main game loop
     */
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
        while(running)
        {
            // WE TAKE THE TIME
            beginLoopTime = System.nanoTime();

            // DRAW WHAtEVER WE HAVE TO
            render();

            // THIS CALCULATION IS NOT CRUCIAL REALLY AT THE SCALE WE ARE DEALING WITH BUT THIS IS FOR FUTURE
            lastUpdateTime = currentUpdateTime;
            currentUpdateTime = System.nanoTime();
            update((int) ((currentUpdateTime - lastUpdateTime)/(1000*1000)));

            // SEE HOW LONG IT TOOK US TO DO UPDATE AND DRAWING
            endLoopTime = System.nanoTime();
            deltaLoop = endLoopTime - beginLoopTime;

            // IF WE ENTER THIS IF STATEMENT THEN WE ARE KIND OF SCREWED OUR GAME LAGS
            if(deltaLoop > desiredDeltaLoop)
            {
                //Do nothing. We are already late. I honestly don't even know why I have this if here :)
            }
            // WE UPDATED AND DREW THINGS QUICK ENOUGH SO THEREFORE WE WILL WAIT IN ORDER TO MAKE THE GAME SMOOTH
            else
            {
                try
                {
                    Thread.sleep((desiredDeltaLoop - deltaLoop)/(1000*1000));
                }
                catch(InterruptedException e)
                {
                    //Do nothing, is it even possible for me to fail at putting thread to sleep?
                }
            }
        }
    }

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
     * Mouse Action Listeners
     */
    private class MouseControl extends MouseAdapter
    {
        public void mouseEntered(MouseEvent e) 
        {

        }

        public void mousePressed(MouseEvent e)
        {

        }
    }

    /*
     * Mouse Motion Listeners
     */
    private class MouseMotion extends MouseMotionAdapter
    {
        public void mouseMoved(MouseEvent e)
        {

        }
    }

    /*
     * public static void main
     * I do not even have to explain this
     */
    public static void main(String [] args)
    {
        Main ex = new Main();
        new Thread(ex).start();
    }

    // WILL HAVE THREE DISPLAY MODES, 32 bit, 16bit and 8 bit
    private static DisplayMode MODES[] = new DisplayMode[]
    {
        new DisplayMode(WIDTH, HEIGHT, 32, 0), new DisplayMode(WIDTH, HEIGHT, 16, 0),
        new DisplayMode(WIDTH, HEIGHT, 8, 0)
    };

    /*
     * Let's see which display mode is suitable for our computer
     */
    private static DisplayMode getBestDisplayMode(GraphicsDevice device)
    {
        // LOOP THROUGH 32 THEN 16 THEN 8 BIT DISPLAY MODES AND SEE WHICH IS THE FIRST ONE WE CAN HANDLE
        for (int x = 0, xn = MODES.length; x < xn; x++)
        {
            DisplayMode[] modes = device.getDisplayModes();
            for (int i = 0, in = modes.length; i < in; i++)
            {
                if (modes[i].getWidth() == MODES[x].getWidth() && modes[i].getHeight() == MODES[x].getHeight() && modes[i].getBitDepth() == MODES[x].getBitDepth())
                {
                    return MODES[x];
                }
            }
        }
        return null;
    }

    // INITIALIZING SOME FRAME RATE THINGS
    private long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    private boolean running = true;

    // INITIALIZING SOME JAVA THINGS
    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;

    private GraphicsDevice graphicsDevice;
    private DisplayMode originalDisplayMode;

    /*
     * Main thread
     */
    public Main()
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

        if(FULL_SCREEN)
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
        panel.setPreferredSize(new Dimension(WIDTH-9, HEIGHT-9));
        panel.setLayout(null);
        // CREATE A Canvas INSIDE JPanel WITH SPECIFIED WIDTH AND HEIGHT
        canvas = new Canvas();
        // once again I add 1 because java is stupid that's why
        canvas.setBounds(0, 0, WIDTH+1, HEIGHT+1);
        //canvas.setBounds(bounds);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);
        // ADD LISTENERS
        canvas.addKeyListener(new KeyInputHandler());
        canvas.addMouseListener(new MouseControl());
        canvas.addMouseMotionListener(new MouseMotion());
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
