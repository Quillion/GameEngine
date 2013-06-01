package Logic;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Color;
import java.awt.Graphics2D;

import BasicShapes.QBBox;
import BasicShapes.QBox;
import BasicSprite.QBPlatform;
import BasicSprite.QMCharacter;
import BasicSprite.QPlatform;
import ExtendedShapes.QBMControls;
import ExtendedShapes.QMControls;
import MovingShapes.QBMBox;
import MovingShapes.QMBox;

public class QCamera
{
    private int x_, y_;
    private int horizontalBoundary_, verticalBoundary_;
	private int centerX_, centerY_;

    public QCamera(int leftRightBoundary, int topBottomBoundary, int centerX, int centerY)
    {
        this.setBoundaries(leftRightBoundary, topBottomBoundary);
		this.setCenter(centerX, centerY);
        this.setX(0);
        this.setY(0);
    }

    public void setBoundaries(int leftRightBoundary, int topBottomBoundary)
    {
        this.horizontalBoundary_ = leftRightBoundary;
        this.verticalBoundary_ = topBottomBoundary;
    }

	public void setCenter(int x, int y)
	{
		this.centerX_ = x;
		this.centerY_ = y;
	}

    public void updateCamera(int x, int y)
    {
        int horizontalDifference = this.getX() - x;
        int verticalDifference = this.getY() - y;

		// right
        if(horizontalDifference < -this.getHorizontalBoundary())
        {
            this.incrementX(x - this.getRightSide());
        }
		// left
        else if(horizontalDifference > this.getHorizontalBoundary())
        {
			this.incrementX(x - this.getLeftSide());
        }

		// down
        if(verticalDifference < -this.getVerticalBoundary())
        {
            this.incrementY(y - this.getBottomSide());
        }
		// up
        else if(verticalDifference > this.getVerticalBoundary())
        {
            this.incrementY(y - this.getTopSide());
        }
    }

    public int getX()
    {
        return this.x_;
    }

    public int getY()
    {
        return this.y_;
    }

    public void setX(int x)
    {
        this.x_ = x;
    }

    public void setY(int y)
    {
        this.y_ = y;
    }

    public void incrementX(int amount)
    {
        this.x_ += amount;
    }

    public void incrementY(int amount)
    {
        this.y_ += amount;
    }

    public int getHorizontalBoundary()
    {
        return this.horizontalBoundary_;
    }

    public int getVerticalBoundary()
    {
        return this.verticalBoundary_;
    }

	public int getCenterX()
	{
		return this.centerX_;
	}

	public int getCenterY()
	{
		return this.centerY_;
	}

	public int getLeftSide()
	{
		return this.getX() - this.getHorizontalBoundary();
	}

	public int getRightSide()
	{
		return this.getX() + this.getHorizontalBoundary();
	}

	public int getTopSide()
	{
		return this.getY() - this.getVerticalBoundary();
	}

	public int getBottomSide()
	{
		return this.getY() + this.getVerticalBoundary();
	}

    public void draw(Graphics2D g, QBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

    public void draw(Graphics2D g, QBBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

    public void draw(Graphics2D g, QMBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

    public void draw(Graphics2D g, QBMBox box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

    public void draw(Graphics2D g, QPlatform box)
    {
        if(box.getImage() == null)
        {
            g.setColor(box.getColor());
            g.fillRect(	box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY(),
						box.getWidth(),
						box.getHeight());
        }
        else
            g.drawImage(box.getImage(),
						null,
						box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY());
    }

    public void draw(Graphics2D g, QBPlatform box)
    {
		g.drawImage(box.getImage(),
					null,
					box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY());
    }

    public void draw(Graphics2D g, QMCharacter box)
    {
        if(box.getImage() == null)
        {
            g.setColor(box.getColor());
			g.fillRect(	box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY(),
						box.getWidth(),
						box.getHeight());
        }
        else
			g.drawImage(box.getImage(),
						null,
						box.getX()-this.getX()+this.getCenterX(),
						box.getY()-this.getY()+this.getCenterY());
    }

    public void draw(Graphics2D g, QBMControls box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }

    public void draw(Graphics2D g, QMControls box)
    {
        g.setColor(Color.BLACK);
		g.drawRect(	box.getX()-this.getX()+this.getCenterX(),
					box.getY()-this.getY()+this.getCenterY(),
					box.getWidth(),
					box.getHeight());
    }
}
