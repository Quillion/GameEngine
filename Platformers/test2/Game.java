package test2;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import BasicSprite.QMCharacter;
import BasicSprite.QPlatform;
import Constants.QConstants;
import Logic.QCamera;
import Logic.QEngine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Game
{
    private int WIDTH, HEIGHT;

	private List<QPlatform> platforms;
	private QPlatform temp_platform;

	private QMCharacter character;

	private QCamera camera;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

		platforms = new ArrayList<QPlatform>();

		temp_platform = new QPlatform();
		temp_platform.setX(0);
		temp_platform.setY(430);
		temp_platform.setWidth(640);
		temp_platform.setHeight(50);
		temp_platform.setColor(Color.GREEN);
		platforms.add(temp_platform);

		temp_platform = new QPlatform();
		temp_platform.setX(0);
		temp_platform.setY(0);
		temp_platform.setWidth(10);
		temp_platform.setHeight(430);
		temp_platform.setColor(Color.GREEN);
		platforms.add(temp_platform);

		temp_platform = new QPlatform();
		temp_platform.setX(630);
		temp_platform.setY(0);
		temp_platform.setWidth(10);
		temp_platform.setHeight(430);
		temp_platform.setColor(Color.GREEN);
		platforms.add(temp_platform);

		temp_platform = new QPlatform();
		temp_platform.setX(10);
		temp_platform.setY(0);
		temp_platform.setWidth(620);
		temp_platform.setHeight(30);
		temp_platform.setColor(Color.GREEN);
		platforms.add(temp_platform);

		temp_platform = new QPlatform();
		temp_platform.setX(10);
		temp_platform.setY(370);
		temp_platform.setWidth(70);
		temp_platform.setHeight(30);
		temp_platform.setColor(Color.CYAN);
		platforms.add(temp_platform);

		temp_platform = new QPlatform();
		temp_platform.setX(140);
		temp_platform.setY(300);
		temp_platform.setWidth(70);
		temp_platform.setHeight(30);
		temp_platform.setColor(Color.BLUE);
		platforms.add(temp_platform);

		temp_platform = new QPlatform();
		temp_platform.setX(10);
		temp_platform.setY(230);
		temp_platform.setWidth(70);
		temp_platform.setHeight(30);
		temp_platform.setColor(Color.RED);
		platforms.add(temp_platform);

		character = new QMCharacter();
		character.setX(WIDTH/2);
		character.setY(HEIGHT/2);
		character.setWidth(30);
		character.setHeight(30);
		character.setGravity(0.2);
		character.setMaxSpeed(3.9);
		character.setJump(5.5);
		character.setAcceleration(0.5);
		character.setGroundFriction(0.2);
		character.setAirFriction(0.2);
		character.setLeftKey(KeyEvent.VK_LEFT);
		character.setRightKey(KeyEvent.VK_RIGHT);
		character.setJumpKey(KeyEvent.VK_UP);
		character.setColor(Color.ORANGE);

		camera = new QCamera(111, 111, WIDTH/2, HEIGHT/2);
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		camera.draw(g, character);

		for(QPlatform platform : platforms)
		{
			camera.draw(g, platform);
		}

		g.setColor(Color.WHITE);
		g.drawString("| Camera | x: "+camera.getX()+" | y: "+camera.getY()+" |", 240, 120);
		g.drawRect(camera.getCenterX() - camera.getHorizontalBoundary(),
				camera.getCenterY() - camera.getVerticalBoundary(),
				camera.getHorizontalBoundary()*2,
				camera.getVerticalBoundary()*2);
    }

    public void update()
    {
		QEngine.preUpdate(character);
		character.setStanding(false);
		for(QPlatform platform : platforms)
		{
			int vert = QEngine.verticalCollision(character, platform);
			int hort = QEngine.horizontalCollision(character, platform);

			if(hort == QConstants.RIGHT)
			{
				character.setRight(false);
				character.setX(platform.getLeftX() - character.getWidth());
				character.setXVector(0);
			}
			else if(hort == QConstants.LEFT)
			{
				character.setLeft(false);
				character.setX(platform.getRightX());
				character.setXVector(0);
			}

			if(vert == QConstants.UP)
			{
				if(character.getGravity() < 0)
					character.setStanding(true);
				character.setY(platform.getBottomY());
				character.setYVector(0);
			}
			else if(vert == QConstants.DOWN)
			{
				if(character.getGravity() > 0)
					character.setStanding(true);
				character.setY(platform.getTopY()-character.getHeight());
				character.setYVector(0);
			}
		}
		QEngine.postUpdate(character);

		camera.updateCamera(character.getCenterX(), character.getCenterY());
    }

    public void keyPressed(KeyEvent e)
    {
		QEngine.keyPressed(e.getKeyCode(), character);
    }

    public void keyReleased(KeyEvent e)
    {
		QEngine.keyReleased(e.getKeyCode(), character);
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
