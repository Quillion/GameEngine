package test8_fx;

import abstractFx.GameAbstract;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;

public class TestGame extends GameAbstract
{
	public static Rectangle rectangle;
	public        int       x;
	public        int       y;

	public TestGame()
	{
		super();
	}

	@Override
	public void setup()
	{
		x = 100;
		y = 100;
		rectangle = RectangleBuilder.create()
									.x(x)
									.y(y)
									.width(100)
									.height(100)
									.fill(Color.RED)
									.build();
	}

	@Override
	public void update()
	{
	}

	@Override
	public void draw()
	{
		this.gameground.getChildren().setAll(rectangle);
	}

	@Override
	public void keyPressed(KeyEvent keyEvent)
	{
		if (keyEvent.getCode() == KeyCode.LEFT)
		{
			x -= 10;
			rectangle.setX(x);
		}
		else if (keyEvent.getCode() == KeyCode.RIGHT)
		{
			x += 10;
			rectangle.setX(x);
		}
		else if (keyEvent.getCode() == KeyCode.UP)
		{
			y -= 10;
			rectangle.setY(y);
		}
		else if (keyEvent.getCode() == KeyCode.DOWN)
		{
			y += 10;
			rectangle.setY(y);
		}
	}

	@Override
	public void keyReleased(KeyEvent keyEvent)
	{
	}

	@Override
	public void mousePressed(MouseEvent mouseEvent)
	{
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent)
	{
	}

	@Override
	public void mouseClicked(MouseEvent mouseEvent)
	{
	}

	@Override
	public void mouseDragged(MouseEvent mouseEvent)
	{
	}

	public static void main(String[] args)
	{
		TestGame game = new TestGame();
		game.setScreen(800, 600, true);
		launch(args);
	}
}
