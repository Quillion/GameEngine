import BasicObjects.*;
import BasicObjects.Shapes.*;
import constants.Constants;
import junit.framework.Assert;
import logic.CollisionEngine;
import org.junit.Test;

/**
 * User: Edgar
 * Date: 12/16/13
 * Time: 10:28 AM
 */
public class CollisionEngineTest
{
	@Test
	public void testBoxPoint()
	{
		Box box = new Box();
		box.setCoordinates(new Point(1, 3));
		box.setSize(new Dimensions(7, 5));

		Point point = new Point(7, 7);

		Assert.assertTrue(CollisionEngine.collision(box, point));

		box.incrementWidth(-1);
		box.incrementHeight(-1);
		Assert.assertTrue(CollisionEngine.collision(box, point));

		box.incrementX(-1);
		Assert.assertFalse(CollisionEngine.collision(box, point));
	}

	@Test
	public void testBBoxPoint()
	{
		BBox box = new BBox();
		box.setCoordinates(new Point(1, 3));
		box.setSize(new Dimensions(7, 5));
		box.setOffsets(new Dimensions(1, 1));

		Point point = new Point(7, 7);

		Assert.assertTrue(CollisionEngine.collision(box, point));

		box.incrementWidth(-1);
		box.incrementHeight(-1);
		Assert.assertFalse(CollisionEngine.collision(box, point));

		box.incrementWidth(1);
		box.incrementHeight(1);
		box.setHorizontalOffset(2);
		Assert.assertFalse(CollisionEngine.collision(box, point));
	}

	@Test
	public void testBallPoint()
	{
		Ball ball = new Ball();
		ball.setCoordinates(new Point(1, 3));
		ball.setRadius(6);

		Point point = new Point(4, 7);

		Assert.assertTrue(CollisionEngine.collision(ball, point));

		ball.incrementRadius(-1);
		Assert.assertTrue(CollisionEngine.collision(ball, point));

		ball.move(new Point(-1, -1));
		ball.incrementRadius(1);
		Assert.assertFalse(CollisionEngine.collision(ball, point));
	}

	@Test
	public void testBBallPoint()
	{
		BBall ball = new BBall();
		ball.setCoordinates(new Point(1, 3));
		ball.setRadius(6);
		ball.setOffset(1);

		Point point = new Point(4, 7);

		Assert.assertTrue(CollisionEngine.collision(ball, point));

		ball.move(new Point(-1, 0));
		ball.incrementRadius(1);
		Assert.assertTrue(CollisionEngine.collision(ball, point));

		ball.setOffset(2);
		ball.move(new Point(-1, -1));
		ball.incrementRadius(1);
		Assert.assertFalse(CollisionEngine.collision(ball, point));
	}

	@Test
	public void testBBoxBox()
	{
		BBox wall1 = new BBox();
		Box wall2 = new Box();

		wall1.setCoordinates(new Point(1, 3));
		wall1.setSize(new Dimensions(7, 5));
		wall1.setOffsets(new Dimensions(3, 1));

		wall2.setCoordinates(new Point(4, 5));
		wall2.setSize(new Dimensions(3, 3));

		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall2.move(new Point(1, 1));
		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall2.move(new Point(1, 1));
		Assert.assertFalse(CollisionEngine.collision(wall1, wall2));

		wall1.move(new Point(4, 4));
		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall1.move(new Point(1, 2));
		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall1.move(new Point(1, 1));
		Assert.assertFalse(CollisionEngine.collision(wall1, wall2));
	}

	@Test
	public void testBBoxBBox()
	{
		BBox wall1 = new BBox();
		BBox wall2 = new BBox();

		wall1.setCoordinates(new Point(1, 3));
		wall1.setSize(new Dimensions(7, 5));
		wall1.setOffsets(new Dimensions(3, 1));

		wall2.setCoordinates(new Point(3, 4));
		wall2.setSize(new Dimensions(3, 3));
		wall2.setOffsets(new Dimensions(1, 1));

		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall2.move(new Point(1, 1));
		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall2.move(new Point(1, 1));
		Assert.assertFalse(CollisionEngine.collision(wall1, wall2));

		wall1.move(new Point(2, 2));
		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall1.move(new Point(1, 2));
		Assert.assertTrue(CollisionEngine.collision(wall1, wall2));

		wall1.move(new Point(1, 1));
		Assert.assertFalse(CollisionEngine.collision(wall1, wall2));

		Assert.assertTrue(CollisionEngine.collision(wall1, wall2.getBox()));
	}

	@Test
	public void testBBoxBall()
	{
		BBox box = new BBox();
		Ball ball = new Ball();

		box.setCoordinates(new Point(1, 3));
		box.setSize(new Dimensions(17, 15));
		box.setOffsets(new Dimensions(3, 1));

		ball.setCoordinates(new Point(-2, -1));
		ball.setRadius(5);

		Assert.assertFalse(CollisionEngine.collision(box, ball));

		ball.move(new Point(3, 1));
		Assert.assertTrue(CollisionEngine.collision(box, ball));

		ball.move(new Point(8, 10));
		Assert.assertTrue(CollisionEngine.collision(box, ball));

		ball.move(new Point(9, 1));
		Assert.assertTrue(CollisionEngine.collision(box, ball));
	}

	@Test
	public void testBBoxBBall()
	{
		BBox box = new BBox();
		BBall ball = new BBall();

		box.setCoordinates(new Point(1, 3));
		box.setSize(new Dimensions(17, 15));
		box.setOffsets(new Dimensions(3, 1));

		ball.setCoordinates(new Point(-3, -2));
		ball.setRadius(7);
		ball.setOffset(2);

		Assert.assertFalse(CollisionEngine.collision(box, ball));
		Assert.assertTrue(CollisionEngine.collision(box, ball.getBall()));

		ball.move(new Point(3, 1));
		Assert.assertTrue(CollisionEngine.collision(box, ball));

		ball.move(new Point(8, 10));
		Assert.assertTrue(CollisionEngine.collision(box, ball));

		ball.move(new Point(9, 1));
		Assert.assertTrue(CollisionEngine.collision(box, ball));
	}

	@Test
	public void testBallBall()
	{
		Ball ball1 = new Ball();
		Ball ball2 = new Ball();

		ball1.setCoordinates(new Point(1, 3));
		ball1.setRadius(5);

		ball2.setCoordinates(new Point(9, 11));
		ball2.setRadius(5);

		Assert.assertFalse(CollisionEngine.collision(ball1, ball2));

		ball1.move(new Point(1, 1));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(1, -1));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(2, -6));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(2, -1));
		Assert.assertFalse(CollisionEngine.collision(ball1, ball2));
	}

	@Test
	public void testBBallBall()
	{
		BBall ball1 = new BBall();
		Ball ball2 = new Ball();

		ball1.setCoordinates(new Point(1, 3));
		ball1.setRadius(6);
		ball1.setOffset(1);

		ball2.setCoordinates(new Point(9, 11));
		ball2.setRadius(5);

		Assert.assertFalse(CollisionEngine.collision(ball1, ball2));
		Assert.assertFalse(CollisionEngine.collision(ball1.getBall(), ball2));

		ball1.move(new Point(1, 1));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(1, -1));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(2, -6));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(2, -1));
		Assert.assertFalse(CollisionEngine.collision(ball1, ball2));
	}

	@Test
	public void testBBallBBall()
	{
		BBall ball1 = new BBall();
		BBall ball2 = new BBall();

		ball1.setCoordinates(new Point(1, 3));
		ball1.setRadius(6);
		ball1.setOffset(1);

		ball2.setCoordinates(new Point(9, 11));
		ball2.setRadius(6);
		ball2.setOffset(1);

		Assert.assertFalse(CollisionEngine.collision(ball1, ball2));
		Assert.assertTrue(CollisionEngine.collision(ball1.getBall(), ball2));
		Assert.assertFalse(CollisionEngine.collision(ball1, ball2.getBall()));
		Assert.assertTrue(CollisionEngine.collision(ball1.getBall(), ball2.getBall()));

		ball1.move(new Point(1, 1));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(1, -1));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(2, -6));
		Assert.assertTrue(CollisionEngine.collision(ball1, ball2));

		ball2.move(new Point(2, -1));
		Assert.assertFalse(CollisionEngine.collision(ball1, ball2));
	}

	@Test
	public void testHorizontalCollisionMBoxBox()
	{
		MBox character = new MBox();
		character.setCoordinates(new Point(1, 3));
		character.setSize(new Dimensions(3, 3));
		character.setOffsets(new Dimensions(1, 1));
		character.setVector(new Point(3, 0));

		Box wall = new Box();
		wall.setCoordinates(new Point(7, 1));
		wall.setSize(new Dimensions(5, 5));

		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character, wall));

		character.move();
		Assert.assertEquals(Constants.Direction.Right, CollisionEngine.horizontalCollision(character, wall));

		character.incrementXVector(5);
		character.move();
		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character, wall));

		character.setXVector(-3);
		Assert.assertEquals(Constants.Direction.Left, CollisionEngine.horizontalCollision(character, wall));

		character.move();
		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character, wall));
	}

	@Test
	public void testHorizontalCollisionMBoxBBox()
	{
		MBox character = new MBox();
		character.setCoordinates(new Point(1, 3));
		character.setSize(new Dimensions(5, 5));
		character.setOffsets(new Dimensions(1, 1));
		character.setVector(new Point(3, 0));

		BBox wall = new BBox();
		wall.setCoordinates(new Point(7, 1));
		wall.setSize(new Dimensions(7, 7));
		wall.setOffsets(new Dimensions(3, 3));

		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character, wall));

		character.move();
		Assert.assertEquals(Constants.Direction.Right, CollisionEngine.horizontalCollision(character, wall));

		character.incrementXVector(5);
		character.move();
		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character, wall));

		character.setXVector(-3);
		Assert.assertEquals(Constants.Direction.Left, CollisionEngine.horizontalCollision(character, wall));

		character.move();
		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character, wall));
	}

	@Test
	public void testHorizontalCollisionMBoxMBox()
	{
		MBox character1 = new MBox();
		character1.setCoordinates(new Point(1, 3));
		character1.setSize(new Dimensions(5, 5));
		character1.setOffsets(new Dimensions(1, 1));
		character1.setVector(new Point(3, 0));

		MBox character2 = new MBox();
		character2.setCoordinates(new Point(9, 1));
		character2.setSize(new Dimensions(7, 7));
		character2.setOffsets(new Dimensions(3, 3));
		character2.setVector(new Point(-3, 0));

		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character1, character2));

		character1.move();
		Assert.assertEquals(Constants.Direction.Right, CollisionEngine.horizontalCollision(character1, character2));

		character1.incrementXVector(5);
		character1.move();
		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character1, character2));

		character1.setXVector(-3);
		Assert.assertEquals(Constants.Direction.Left, CollisionEngine.horizontalCollision(character1, character2));

		character1.move();
		character2.move();
		Assert.assertEquals(Constants.Direction.Left, CollisionEngine.horizontalCollision(character1, character2));

		character1.move();
		Assert.assertEquals(Constants.Direction.None, CollisionEngine.horizontalCollision(character1, character2));
	}
}
