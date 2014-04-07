package platformer.basicSprite;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import platformer.extendedShapes.MControls;
import utils.ImageProcessor;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This object actually has image that can be display, and or a color. The choice is completely yours. It does not have
 * Bounding Box, only a box.
 */
public class MCharacter extends MControls
{
	private BufferedImage image;
	private Color         color;

	/**
	 * Constructor for the character, originally contains no image, and color is set to black.
	 */
	public MCharacter()
	{
		super();
		this.image = null;
		this.setColor(Color.BLACK);
	}

	/**
	 * Sets the color of the character to whatever you want.
	 *
	 * @param color
	 * 		what color the new character will be.
	 */
	public void setColor(Color color)
	{
		this.color = color;
	}

	/**
	 * Returns the color to which this character is set to.
	 *
	 * @return color of this character.
	 */
	public Color getColor()
	{
		return this.color;
	}

	/**
	 * Load the image from a given path.
	 *
	 * @return true if image loaded successfully, false otherwise.
	 */
	public boolean loadImage(String path)
	{
		this.image = ImageProcessor.loadImage(path);

		if (this.image == null)
		{
			return false;
		}

		super.setWidth(this.image.getWidth());
		super.setHeight(this.image.getHeight());

		return true;
	}

	/**
	 * Returns this character's image.
	 *
	 * @return image of this character.
	 */
	public BufferedImage getImage()
	{
		return this.image;
	}

	/**
	 * Draws image for the character to the specified canvas.
	 */
	public void draw(Graphics2D g)
	{
		if (this.image == null)
		{
			g.setColor(this.getColor());
			g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
		}
		else
		{
			g.drawImage(this.getImage(), null, super.getX(), super.getY());
		}
	}
}
