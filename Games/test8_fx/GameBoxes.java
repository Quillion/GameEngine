package test8_fx;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Random;

public class GameBoxes extends Application
{
	public static int WIDTH  = 800;
	public static int HEIGHT = 600;

	public static Rectangle rectangle  = new Rectangle();
	public static Rectangle rectangle1 = RectangleBuilder.create()
														 .x(50)
														 .y(50)
														 .width(100)
														 .height(100)
														 .fill(Color.RED)
														 .build();
	public static Rectangle rectangle2 = RectangleBuilder.create()
														 .x(100)
														 .y(100)
														 .width(100)
														 .height(100)
														 .fill(Color.BLUE)
														 .build();

	private Group gameground;
	private final static Random RANDOM = new Random();
	private Animation animation;

	@Override
	public void start(Stage stage)
	{
		Group background = new Group();
		gameground = new Group();
		Group foreground = new Group();

		TimelineBuilder.create()
					   .cycleCount(Animation.INDEFINITE)
					   .keyFrames(
							   new KeyFrame(Duration.millis(150),
											new EventHandler<ActionEvent>()
											{
												@Override
												public void handle(
														ActionEvent actionEvent)
												{
													gameground.getChildren()
															  .setAll(rectangle1);
												}
											}
							   ),
							   new KeyFrame(Duration.millis(300),
											new EventHandler<ActionEvent>()
											{
												@Override
												public void handle(
														ActionEvent actionEvent)
												{
													gameground.getChildren()
															  .setAll(rectangle2);
												}
											}
							   )
					   )
					   .build().play();

		final Group root = new Group(background, gameground, foreground);

		Scene scene = new Scene(root, WIDTH, HEIGHT);

		stage.setTitle("Game");
		stage.setScene(scene);
		stage.show();

		startAnimation();
	}

	public static void main(String[] args)
	{
		launch(args);
	}

	private void startAnimation()
	{
		if(animation != null)
		{
			animation.stop();
		}

		int y0 = RANDOM.nextInt(HEIGHT/2) + HEIGHT/4;
		int y1 = RANDOM.nextInt(HEIGHT/2) + HEIGHT/4;

		animation = TranslateTransitionBuilder.create().node(gameground)
				.fromX(-100).toX(WIDTH).fromY(y0).toY(y1)
				.duration(Duration.seconds(2))
				.onFinished(new EventHandler<ActionEvent>()
				{
					@Override
					public void handle(ActionEvent actionEvent)
					{
						startAnimation();
					}
				}).build();
		animation.play();
	}
}
