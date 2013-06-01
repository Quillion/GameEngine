package test3;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;

import BasicSprite.QBPlatform;
import Logic.QCamera;
import Logic.QImageProcessor;
import TooGeneral.NormalPlatformGenerator;

public class Game
{
    private int WIDTH, HEIGHT;

	private NormalPlatformGenerator generator;

	private java.util.List<QBPlatform> platforms;
	private QBPlatform temp_platform;

	private QCamera camera;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

		generator = new NormalPlatformGenerator();

		platforms = new ArrayList<QBPlatform>();

		temp_platform = new QBPlatform();
		temp_platform.setX(0);
		temp_platform.setY(430);
		temp_platform.setImage(QImageProcessor.constructHorizontal(generator.getPlatform(), generator.getPlatform()));
		for(int i = 0; i < 9; i++)
		{
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getPlatform()));
		}
		platforms.add(temp_platform);

		camera = new QCamera(111, 111, WIDTH/2, HEIGHT/2);
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.WHITE);
		g.drawImage(generator.getPlatform(), null, 10, 10);
		g.drawImage(generator.getWall(), null, 120, 10);
		g.drawImage(QImageProcessor.constructVertical(
					QImageProcessor.constructVertical(generator.getWallTop(), generator.getWall()),
					generator.getWallBottom()), null, 200, 50);

		for(QBPlatform platform : platforms)
		{
			//camera.draw(g, platform);
			platform.draw(g);
		}
    }

    public void update()
    {
    }

    public void keyPressed(KeyEvent e)
    {
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void mouseEntered(MouseEvent e) 
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public int getWIDTH ()
    {
        return WIDTH;
    }

    public int getHEIGHT ()
    {
        return HEIGHT;
    }
}
