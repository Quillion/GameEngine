package BasicSprite;

/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;

import BasicShapes.QBBox;

/**
 * Platform with bounding box
 */
public class QBPlatform extends QBBox
{
    private BufferedImage image_;

    /**
     * Constructor for the platform, originally contains no image
     */
    public QBPlatform()
    {
        super();
        image_ = null;
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
     * Returns this platform's image
     * @return image of this platform
     */
    public BufferedImage getImage()
    {
        return this.image_;
    }

    /**
     * Draws image for the platform to the specified canvas
     */
    public void draw(Graphics2D g)
    {
        g.drawImage(this.getImage(), null, super.getX(), super.getY());
    }
}
