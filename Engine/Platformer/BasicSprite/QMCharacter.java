package platformer.BasicSprite;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

import platformer.ExtendedShapes.QMControls;

public class QMCharacter extends QMControls
{
    private BufferedImage image_;
    private Color color_;

    /**
     * Constructor for the character, originally contains no image,
     * and color is set to black
     */
    public QMCharacter()
    {
        super();
        image_ = null;
        this.setColor(Color.BLACK);
    }

    /**
     * Sets the color of the character to whatever you want
     * @param color what color the new character will be
     */
    public void setColor(Color color)
    {
        this.color_ = color;
    }

    /**
     * Returns the color to which this character is set to
     * @return color of this character
     */
    public Color getColor()
    {
        return this.color_;
    }

    /**
     * Load the image from a given path,
     * @return true if image loaded successfully, false otherwise
     */
    public boolean loadImage(String path)
    {
        URL url = this.getClass().getResource(path);
        this.image_ = null;

        try
        {
            this.image_ =  ImageIO.read(url);
        }
        catch (Exception e)
        {
            return false;
        }

        super.setWidth(image_.getWidth());
        super.setHeight(image_.getHeight());

        return true;
    }

    /**
     * Returns this character's image
     * @return image of this character
     */
    public BufferedImage getImage()
    {
        return this.image_;
    }

    /**
     * Draws image for the character to the specified canvas
     */
    public void draw(Graphics2D g)
    {
        if(image_ == null)
        {
            g.setColor(this.getColor());
            g.fillRect(super.getX(), super.getY(), super.getWidth(), super.getHeight());
        }
        else
            g.drawImage(this.getImage(), null, super.getX(), super.getY());
    }
}