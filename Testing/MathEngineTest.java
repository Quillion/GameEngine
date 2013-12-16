/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Point;
import junit.framework.Assert;
import logic.MathEngine;
import org.junit.Test;

public class MathEngineTest
{
	@Test
	public void testDistance()
	{
		Point point1 = new Point(3, 5);
		Point point2 = new Point(6, 9);
		Assert.assertEquals(5d, MathEngine.distance(point1, point2)); // pythogoras much?
		Point absolute = MathEngine.absDistance(point2, point1);
		Assert.assertEquals(3d, absolute.getX());
		Assert.assertEquals(4d, absolute.getY());
		Assert.assertEquals(3d, MathEngine.deltaX(point2, absolute));
		Assert.assertEquals(-1d, MathEngine.deltaY(absolute, point1));
		Assert.assertEquals(5d, MathEngine.hypotenuse(absolute));
	}

	@Test
	public void testAngle()
	{
		Point point1 = new Point(3, 5);

		Point point2 = new Point(5, 5); // same y plane to the right
		Assert.assertEquals(0d, MathEngine.angleDeg(point1, point2));

		point2.changeLocation(7, 9); // first quadrant
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) > 0d);
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) < 90d);

		point2.changeLocation(3, 7); // same x plane above
		Assert.assertEquals(90d, MathEngine.angleDeg(point1, point2));

		point2.changeLocation(1, 7); // second quadrant
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) > 90d);
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) < 180d);

		point2.changeLocation(1, 5); // same y plane to the left
		Assert.assertEquals(180d, MathEngine.angleDeg(point1, point2));

		point2.changeLocation(1, 3); // third quadrant
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) < -90d);
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) > -180d);

		point2.changeLocation(3, 3); // same x plane below
		Assert.assertEquals(-90d, MathEngine.angleDeg(point1, point2));

		point2.changeLocation(5, 3); // fourth quadrant
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) < 0d);
		Assert.assertTrue(MathEngine.angleDeg(point1, point2) > -90d);
	}
}
