package platformer;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import BasicObjects.Dimensions;
import logic.Camera;
import utils.ImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Background class that will hopefully be useful,
 * I spent too much brainpower on it.
 */
public class Background
{
//	private BufferedImage background;
//	private BufferedImage midground;
//	private BufferedImage foreground;
//	private Point coordinates;
//	private List<BufferedImage> foregroundObjects;
//
//	private Dimensions size;
//	private int backgroundShift, midgroundShift;
//
//	/**
//	 * Background class. It is probably riddled with bunch of bugs, so please be careful.
//	 *
//	 * @param x            The lowest x coordinate possible as stated by map.
//	 * @param y            The lowest y coordinate possible as stated by map.
//	 * @param worldWidth   The Width of the whole world as stated by map.
//	 * @param worldHeight  The Height of the whole world as stated by map.
//	 * @param screenWidth  The screen width in the game.
//	 * @param screenHeight The screen height in the game.
//	 */
//	public Background(int x, int y, int worldWidth, int worldHeight, int screenWidth, int screenHeight)
//	{
//		// Basic init
//		this.foregroundObjects = new ArrayList<BufferedImage>();
//		this.setBackgroundShift(3);
//		this.setMidgroundShift(2);
//
//		this.coordinates = new Point(x, y);
//
//		// We multiply screen width by two because the camera spans the width of two screens.
//		// However we could care less that the height spans two screens
//		this.size = new Dimensions(worldWidth + screenWidth * 2, worldHeight + screenHeight);
//	}
//
//	/**
//	 * Set the background image for this world.
//	 * The background will be changed in size in
//	 * accordance with the parameters passed previously.
//	 *
//	 * @param background The image that will be our background.
//	 */
//	public void setBackground(BufferedImage background)
//	{
//		this.background = background;
//	}
//
//	/**
//	 * Sets the midground image for this world.
//	 * It will be duplicated and tiled horizontally in
//	 * accordance with the width of the world.
//	 *
//	 * @param midGround The image that will be ou midground.
//	 */
//	public void setMidGround(BufferedImage midGround)
//	{
//		this.midground = midGround;
//	}
//
//	/**
//	 * Adds an image to the list of the images that can be in the foreground.
//	 *
//	 * @param object an object that can be a foreground image.
//	 */
//	public void addForegroundObject(BufferedImage object)
//	{
//		this.foregroundObjects.add(object);
//	}
//
//	/**
//	 * Sets how slowly the background will move in respect to the camera.
//	 * The higher the number the slower the movement.
//	 *
//	 * @param backgroundShift The shift for the background.
//	 */
//	public void setBackgroundShift(int backgroundShift)
//	{
//		this.backgroundShift = backgroundShift;
//	}
//
//	/**
//	 * Sets how slowly the midground will move in respect to the camera.
//	 * The higher the number the slower the movement.
//	 * Please use common sense and ensure this number is smaller than background shift.
//	 *
//	 * @param midgroundShift The shift for the midground.
//	 */
//	public void setMidgroundShift(int midgroundShift)
//	{
//		this.midgroundShift = midgroundShift;
//	}
//
//	/**
//	 * Generates the background.
//	 * Call this method otherwise you will not have a background.
//	 * This method stretches image for the background,
//	 * horizontally duplicates images for the midground,
//	 * and randomly generates foreground based on the items that you passed.
//	 *
//	 * @param density The density of the items for the foreground.
//	 */
//	public void generateBackground(int density)
//	{
//		// Stretch the background
//		this.background = ImageProcessor.resize(this.background,
//				this.size.getWidth() / this.backgroundShift,
//				this.size.getHeight() / this.backgroundShift);
//
//		// Temp manipulation
//		this.foreground = this.midground;
//		// Duplicate the midground
//		while (this.foreground.getWidth() < this.size.getWidth() / this.midgroundShift)
//			this.foreground = ImageProcessor.constructHorizontal(this.foreground, this.midground);
//		this.midground = this.foreground;
//
//		// Initialize foreground
//		Random fate = new Random();
//		int type = this.foreground.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : this.foreground.getType();
//		this.foreground = new BufferedImage(this.size.getWidth(), this.size.getHeight(), type);
//		Graphics g = this.foreground.getGraphics();
//
//		// Randomly generate the foreground
//		for (int i = 0; i < density; i++)
//		{
//			int image = fate.nextInt(this.foregroundObjects.size());
//			int x = fate.nextInt(this.size.getWidth());
//			g.drawImage(this.foregroundObjects.get(image),
//					(int) this.coordinates.getX() + x,
//					(int) this.coordinates.getY() + this.size.getHeight() - this.foregroundObjects.get(image).getHeight(),
//					null);
//		}
//	}
//
//	/**
//	 * Draws the background,
//	 * followed by midground and
//	 * followed by foreground.
//	 * This method will just call the other draw method,
//	 * so please call other draw method using
//	 * draw(g, camera.getX(), camera.getY());
//	 *
//	 * @param g      The graphics onto which to draw.
//	 * @param camera The camera whose coordinates we want for drawing images accordingly
//	 */
//	public void draw(Graphics2D g, Camera camera)
//	{
//		this.draw(g, camera.getX(), camera.getY());
//	}
//
//	/**
//	 * Draws the background,
//	 * followed by midground and
//	 * followed by foreground.
//	 *
//	 * @param g The graphics onto which we will draw images.
//	 * @param x The x coordinate of the camera.
//	 * @param y The y coordinate of the camera.
//	 */
//	public void draw(Graphics2D g, int x, int y)
//	{
//		x = (int) this.coordinates.getX() - x;
//		int newY = (int) this.coordinates.getY() - y + this.size.getHeight();
//		g.drawImage(this.background, x / this.backgroundShift, newY / this.backgroundShift, null);
//		g.drawImage(this.midground, x / this.midgroundShift, newY / this.midgroundShift, null);
//		g.drawImage(this.foreground, x, 0 - y, null);
//	}
}
