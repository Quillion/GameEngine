package platformer;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import logic.QCamera;
import logic.QImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Background class that will hopefully be useful,
 * I spent too much brainpower on it.
 */
public class QBackground
{
	private BufferedImage background_;
	private BufferedImage midground_;
	private BufferedImage foreground_;
	private int x_, y_;
	private List<BufferedImage> foregroundObjects_;

	private int worldHeight_, worldWidth_;
	private int backgroundShift_, midgroundShift_;

	/**
	 * Background class. It is probably riddled with bunch of bugs, so please be careful.
	 * @param x The lowest x coordinate possible as stated by map.
	 * @param y The lowest y coordinate possible as stated by map.
	 * @param worldWidth The Width of the whole world as stated by map.
	 * @param worldHeight The Height of the whole world as stated by map.
	 * @param screenWidth The screen width in the game.
	 * @param screenHeight The screen height in the game.
	 */
	public QBackground(int x, int y, int worldWidth, int worldHeight, int screenWidth, int screenHeight)
	{
		// Basic init
		this.foregroundObjects_ = new ArrayList<BufferedImage>();
		this.setBackgroundShift(3);
		this.setMidgroundShift(2);

		this.x_ = x;
		this.y_ = y;

		// We multiply screen width by two because the camera spans the width of two screens.
		// However we could care less that the height spans two screens
		this.worldWidth_ = worldWidth + screenWidth*2;
		this.worldHeight_ = worldHeight + screenHeight;
	}

	/**
	 * Set the background image for this world.
	 * The background will be changed in size in
	 * accordance with the parameters passed previously.
	 * @param background The image that will be our background.
	 */
	public void setBackground(BufferedImage background)
	{
		this.background_ = background;
	}

	/**
	 * Sets the midground image for this world.
	 * It will be duplicated and tiled horizontally in
	 * accordance with the width of the world.
	 * @param midGround The image that will be ou midground.
	 */
	public void setMidGround(BufferedImage midGround)
	{
		this.midground_ = midGround;
	}

	/**
	 * Adds an image to the list of the images that can be in the foreground.
	 * @param object an object that can be a foreground image.
	 */
	public void addForegroundObject(BufferedImage object)
	{
		this.foregroundObjects_.add(object);
	}

	/**
	 * Sets how slowly the background will move in respect to the camera.
	 * The higher the number the slower the movement.
	 * @param backgroundShift The shift for the background.
	 */
	public void setBackgroundShift(int backgroundShift)
	{
		this.backgroundShift_ = backgroundShift;
	}

	/**
	 * Sets how slowly the midground will move in respect to the camera.
	 * The higher the number the slower the movement.
	 * Please use common sense and ensure this number is smaller than background shift.
	 * @param midgroundShift The shift for the midground.
	 */
	public void setMidgroundShift(int midgroundShift)
	{
		this.midgroundShift_ = midgroundShift;
	}

	/**
	 * Generates the background.
	 * Call this method otherwise you will not have a background.
	 * This method stretches image for the background,
	 * horizontally duplicates images for the midground,
	 * and randomly generates foreground based on the items that you passed.
	 * @param density The density of the items for the foreground.
	 */
	public void generateBackground(int density)
	{
		// Stretch the background
		this.background_ = QImageProcessor.resize(this.background_,
													this.worldWidth_/this.backgroundShift_,
													this.worldHeight_/this.backgroundShift_);

		// Temp manipulation
		this.foreground_ = this.midground_;
		// Duplicate the midground
		while(this.foreground_.getWidth() < this.worldWidth_/this.midgroundShift_)
			this.foreground_ = QImageProcessor.constructHorizontal(this.foreground_, this.midground_);
		this.midground_ = this.foreground_;

		// Initialize foreground
		Random fate = new Random();
		int type = this.foreground_.getType() == 0? BufferedImage.TYPE_INT_ARGB : this.foreground_.getType();
		this.foreground_ = new BufferedImage(this.worldWidth_, this.worldHeight_, type);
		Graphics g = this.foreground_.getGraphics();

		// Randomly generate the foreground
		for (int i = 0; i < density; i++)
		{
			int image = fate.nextInt(this.foregroundObjects_.size());
			int x = fate.nextInt(this.worldWidth_);
			g.drawImage(this.foregroundObjects_.get(image),
						this.x_ + x,
						this.y_ + this.worldHeight_ - this.foregroundObjects_.get(image).getHeight(),
						null);
		}
	}

	/**
	 * Draws the background,
	 * followed by midground and
	 * followed by foreground.
	 * This method will just call the other draw method,
	 * so please call other draw method using
	 * draw(g, camera.getX(), camera.getY());
	 * @param g The graphics onto which to draw.
	 * @param camera The camera whose coordinates we want for drawing images accordingly
	 */
	public void draw(Graphics2D g, QCamera camera)
	{
		this.draw(g, camera.getX(), camera.getY());
	}

	/**
	 * Draws the background,
	 * followed by midground and
	 * followed by foreground.
	 * @param g The graphics onto which we will draw images.
	 * @param x The x coordinate of the camera.
	 * @param y The y coordinate of the camera.
	 */
	public void draw(Graphics2D g, int x, int y)
	{
		x = this.x_ - x;
		int newY = this.y_ - y + this.worldHeight_;
		g.drawImage(this.background_, x/this.backgroundShift_, newY/this.backgroundShift_, null);
		g.drawImage(this.midground_, x/this.midgroundShift_, newY/this.midgroundShift_, null);
		g.drawImage(this.foreground_, x, 0 - y, null);
	}
}
