package abstractFx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public abstract class GameAbstract extends Application implements Game
{
	// FULL SCREEN OR NOT
	protected boolean FULL_SCREEN;
	protected boolean EXIT_ON_ESC;

	// SCREEN MEASUREMENTS AND REFRESH
	protected int WIDTH;
	protected int HEIGHT;
	protected long desiredFPS = 60;

	protected final Duration oneFrameAmt = Duration
			.millis(1000 / (float) desiredFPS);

	protected Group background;
	protected Group gameground;
	protected Group foreground;

	private Timeline loop;

	public GameAbstract()
	{
		this.background = new Group();
		this.gameground = new Group();
		this.foreground = new Group();

		final KeyFrame oneFrame = new KeyFrame(this.oneFrameAmt,
											   new EventHandler<ActionEvent>()
											   {
												   @Override
												   public void handle(
														   ActionEvent event)
												   {
													   // update actors
													   draw();
													   // check for collision
													   update();
												   }
											   }
		); // oneFrame

		this.loop = TimelineBuilder.create()
								   .cycleCount(Animation.INDEFINITE)
								   .keyFrames(oneFrame)
								   .build();

		setup();
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		final Group root = new Group(this.background,
									 this.gameground,
									 this.foreground);

		Scene scene = new Scene(root, this.getWidth(), this.getHeight());

		scene.setOnKeyPressed(new KeyPressHandler(this));
		scene.setOnKeyReleased(new KeyReleaseHandler(this));
		scene.setOnMousePressed(new MousePressedHandler(this));
		scene.setOnMouseReleased(new MouseReleasedHandler(this));
		scene.setOnMouseClicked(new MouseClickedHandler(this));
		scene.setOnMouseDragged(new MouseDraggedHandler(this));

		stage.setTitle("Game");
		stage.setScene(scene);
		stage.show();

		loop.play();
	}

	/**
	 * Sets screen to specified values.
	 *
	 * @param width
	 * 		the width of the screen/window.
	 * @param height
	 * 		the height of the screen/window.
	 * @param fullScreen
	 * 		if you do set it to true, please do make sure that width and height are
	 * 		correct parameters.
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
	 * Specifies whether or not to set up ESC button to automatically close the
	 * program.
	 *
	 * @param exitOnEsc
	 * 		True if you want game to close eon ESC, false otherwise.
	 */
	public void exitOnEsc(boolean exitOnEsc)
	{
		this.EXIT_ON_ESC = exitOnEsc;
	}

	protected class KeyPressHandler implements EventHandler<KeyEvent>
	{
		private Game game;

		public KeyPressHandler(Game game)
		{
			this.game = game;
		}

		@Override
		public void handle(KeyEvent keyEvent)
		{
			game.keyPressed(keyEvent);
		}
	}

	protected class KeyReleaseHandler implements EventHandler<KeyEvent>
	{
		private Game game;

		public KeyReleaseHandler(Game game)
		{
			this.game = game;
		}

		@Override
		public void handle(KeyEvent keyEvent)
		{
			game.keyReleased(keyEvent);
		}
	}

	protected class MousePressedHandler implements EventHandler<MouseEvent>
	{
		private Game game;

		public MousePressedHandler(Game game)
		{
			this.game = game;
		}

		@Override
		public void handle(MouseEvent mouseEvent)
		{
			game.mousePressed(mouseEvent);
		}
	}

	protected class MouseReleasedHandler implements EventHandler<MouseEvent>
	{
		private Game game;

		public MouseReleasedHandler(Game game)
		{
			this.game = game;
		}

		@Override
		public void handle(MouseEvent mouseEvent)
		{
			game.mouseReleased(mouseEvent);
		}
	}

	protected class MouseClickedHandler implements EventHandler<MouseEvent>
	{
		private Game game;

		public MouseClickedHandler(Game game)
		{
			this.game = game;
		}

		@Override
		public void handle(MouseEvent mouseEvent)
		{
			game.mouseClicked(mouseEvent);
		}
	}

	protected class MouseDraggedHandler implements EventHandler<MouseEvent>
	{
		private Game game;

		public MouseDraggedHandler(Game game)
		{
			this.game = game;
		}

		@Override
		public void handle(MouseEvent mouseEvent)
		{
			game.mouseDragged(mouseEvent);
		}
	}
}
