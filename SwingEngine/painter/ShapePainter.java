package painter;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.shapes.*;

import java.awt.*;

public class ShapePainter
{
	/**
	 * Draws the ball into to the graphics passed, box outline will be black
	 * color.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param ball
	 * 		The ball which you want drawn to the graphics
	 */
	private static void drawBall(Graphics2D g, Ball ball)
	{
		g.setColor(Color.BLACK);
		g.drawOval(ball.getX() - ball.getRadius(),
				   ball.getY() - ball.getRadius(), ball.getDiameter(),
				   ball.getDiameter());
		g.drawLine(ball.getCenterX() - 1, ball.getCenterY() - 1,
				   ball.getCenterX() + 1, ball.getCenterY() + 1);
	}

	/**
	 * Draws the ball into to the graphics passed, box outline will be black
	 * color.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param ball
	 * 		The ball which you want drawn to the graphics. Only bounding box will
	 * 		be drawn.
	 */
	private static void drawBBall(Graphics2D g, BBall ball)
	{
		g.setColor(Color.GRAY);
		g.drawOval(ball.getLeftX(), ball.getTopY(),
				   ball.getDiameter() - ball.getOffset() * 2,
				   ball.getDiameter() - ball.getOffset() * 2);
	}

	/**
	 * Draws the ball into to the graphics passed, box outline will be black
	 * color.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param ball
	 * 		The ball which you want drawn to the graphics. Only vector will be
	 * 		drawn.
	 */
	private static void drawMBall(Graphics2D g, MBall ball)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(ball.getCenterX(),
				   ball.getCenterY(),
				   (int) (ball.getCenterX() + ball.getXVector() * 5),
				   (int) (ball.getCenterY() + ball.getYVector() * 5));
	}

	/**
	 * Draws the box into to the graphics passed, box outline will be black
	 * color.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param box
	 * 		The ball you want to draw to graphics.
	 */
	private static void drawBox(Graphics2D g, Box box)
	{
		g.setColor(Color.BLACK);
		g.drawRect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
	}

	/**
	 * Draws the box into to the graphics passed, box outline will be black
	 * color.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param box
	 * 		The ball you want to draw to graphics. Only draws bounding box.
	 */
	private static void drawBBox(Graphics2D g, BBox box)
	{
		g.setColor(Color.GRAY);
		g.drawRect(box.getLeftX(), box.getTopY(),
				   box.getRightX() - box.getLeftX(),
				   box.getBottomY() - box.getTopY());
	}

	/**
	 * Draws the box into to the graphics passed, box outline will be black
	 * color.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param box
	 * 		The ball you want to draw to graphics. Only draws vector of the box.
	 */
	private static void drawMBox(Graphics2D g, MBox box)
	{
		g.setColor(Color.LIGHT_GRAY);
		g.drawLine(box.getCenterX(),
				   box.getCenterY(),
				   (int) (box.getCenterX() + box.getXVector() * 5),
				   (int) (box.getCenterY() + box.getYVector() * 5));
	}

	/**
	 * Same as drawBall.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param ball
	 * 		The ball which you want drawn to the graphics
	 */
	public static void draw(Graphics2D g, Ball ball)
	{
		drawBall(g, ball);
	}

	/**
	 * Same as drawBall.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param ball
	 * 		The ball which you want drawn to the graphics
	 */
	public static void draw(Graphics2D g, BBall ball)
	{
		drawBall(g, ball);
		drawBBall(g, ball);
	}

	/**
	 * Same as drawBall.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param ball
	 * 		The ball which you want drawn to the graphics
	 */
	public static void draw(Graphics2D g, MBall ball)
	{
		drawBall(g, ball);
		drawBBall(g, ball);
		drawMBall(g, ball);
	}

	/**
	 * Draws box to the given graphics.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param box
	 * 		The box you want to draw to graphics.
	 */
	public static void draw(Graphics2D g, Box box)
	{
		drawBox(g, box);
	}

	/**
	 * Draws bounding box to the given graphics.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param box
	 * 		The box you want to draw to graphics.
	 */
	public static void draw(Graphics2D g, BBox box)
	{
		drawBox(g, box);
		drawBBox(g, box);
	}

	/**
	 * Draws moving box to the given graphics.
	 *
	 * @param g
	 * 		graphics where the box will be drawn into.
	 * @param box
	 * 		The box you want to draw to graphics.
	 */
	public static void draw(Graphics2D g, MBox box)
	{
		drawBox(g, box);
		drawBBox(g, box);
		drawMBox(g, box);
	}
}
