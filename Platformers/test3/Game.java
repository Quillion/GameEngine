package test3;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import BasicSprite.BPlatform;
import logic.Camera;
import logic.Engine;
import platformer.BasicSprite.MCharacter;
import Constants.Constants;
import utils.ImageProcessor;
import TooGeneral.NormalPlatformGenerator;

public class Game
{
    private int WIDTH, HEIGHT;

	private NormalPlatformGenerator generator;

	private List<BPlatform> platforms;
	private BPlatform temp_platform;

	private MCharacter character;

	private Camera camera;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

		generator = new NormalPlatformGenerator();

		platforms = new ArrayList<BPlatform>();

		/************ 3 JUMPING PLATFORMS **************/
		temp_platform = new BPlatform();
		temp_platform.setX(50);
		temp_platform.setY(430);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(235);
		temp_platform.setY(350);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(50);
		temp_platform.setY(270);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		/************ LONG PLATFORM **************/
		temp_platform = new BPlatform();
		temp_platform.setX(220);
		temp_platform.setY(190);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		for(int i = 0; i < 5; i++)
			temp_platform.setImage(ImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getPlatform()));
		platforms.add(temp_platform);

		/************ UPPER PLATFORM **************/
		temp_platform = new BPlatform();
		temp_platform.setX(820);
		temp_platform.setY(110);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		/************ 2 LOWER PLATFORMS **************/
		temp_platform = new BPlatform();
		temp_platform.setX(780);
		temp_platform.setY(370);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(910);
		temp_platform.setY(410);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		/************ DEAD END **************/
		temp_platform = new BPlatform();
		temp_platform.setX(1600);
		temp_platform.setY(0);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructVertical(generator.getWallTop(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(1300);
		temp_platform.setY(100);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		for(int i = 0; i < 3; i++)
			temp_platform.setImage(ImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getPlatform()));
		platforms.add(temp_platform);

		/************ TWO PLATFORMS **************/
		temp_platform = new BPlatform();
		temp_platform.setX(1250);
		temp_platform.setY(430);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(1400);
		temp_platform.setY(340);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		/************ LEFTMOST WALL **************/
		temp_platform = new BPlatform();
		temp_platform.setX(0);
		temp_platform.setY(0);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructVertical(generator.getWallTop(), generator.getWall()));
		for(int i = 0; i < 7; i++)
			temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		platforms.add(temp_platform);

		/************ THE GAP **************/
		temp_platform = new BPlatform();
		temp_platform.setX(1100);
		temp_platform.setY(330);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 2; i++)
			temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(1130);
		temp_platform.setY(390);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(1100);
		temp_platform.setY(90);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructVertical(generator.getWallTop(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		platforms.add(temp_platform);

		/************ MID WALL **************/
		temp_platform = new BPlatform();
		temp_platform.setX(1660);
		temp_platform.setY(330);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		platforms.add(temp_platform);

		/************ RIGHTMOST WALL **************/
		temp_platform = new BPlatform();
		temp_platform.setX(2250);
		temp_platform.setY(0);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 3; i++)
			temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(ImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		platforms.add(temp_platform);

		/************ GROUND **************/
		temp_platform = new BPlatform();
		temp_platform.setX(0);
		temp_platform.setY(518);
		temp_platform.setHorizontalOffset(5);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		for(int i = 0; i < 17; i++)
			temp_platform.setImage(ImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround()));
		platforms.add(temp_platform);

		temp_platform = new BPlatform();
		temp_platform.setX(1680);
		temp_platform.setY(250);
		temp_platform.setHorizontalOffset(5);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(ImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		for(int i = 0; i < 5; i++)
			temp_platform.setImage(ImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround()));
		platforms.add(temp_platform);

		character = new MCharacter();
		character.setX(400);
		character.setY(300);
		character.setWidth(30);
		character.setHeight(30);
		character.setGravity(0.2);
		character.setMaxSpeed(3.9);
		character.setJump(6);
		character.setAcceleration(0.5);
		character.setGroundFriction(0.2);
		character.setAirFriction(0.2);
		character.setLeftKey(KeyEvent.VK_LEFT);
		character.setRightKey(KeyEvent.VK_RIGHT);
		character.setJumpKey(KeyEvent.VK_UP);
		character.setColor(Color.ORANGE);

		camera = new Camera(111, 111, WIDTH/2, HEIGHT/2);
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		camera.draw(g, character);

		for(BPlatform platform : platforms)
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
		Engine.preUpdate(character);
		character.setStanding(false);
		for(BPlatform platform : platforms)
		{
			int vert = Engine.verticalCollision(character, platform);
			int hort = Engine.horizontalCollision(character, platform);

			if(hort == Constants.RIGHT)
			{
				character.setRight(false);
				character.setX(platform.getLeftX() - character.getWidth());
				character.setXVector(0);
			}
			else if(hort == Constants.LEFT)
			{
				character.setLeft(false);
				character.setX(platform.getRightX());
				character.setXVector(0);
			}

			if(vert == Constants.UP)
			{
				if(character.getGravity() < 0)
					character.setStanding(true);
				character.setY(platform.getBottomY());
				character.setYVector(0);
			}
			else if(vert == Constants.DOWN)
			{
				if(character.getGravity() > 0)
					character.setStanding(true);
				character.setY(platform.getTopY()-character.getHeight());
				character.setYVector(0);
			}
		}
		Engine.postUpdate(character);

		camera.updateCamera(character.getCenterX(), character.getCenterY());
    }

    public void keyPressed(KeyEvent e)
    {
		Engine.keyPressed(e.getKeyCode(), character);
    }

    public void keyReleased(KeyEvent e)
    {
		Engine.keyReleased(e.getKeyCode(), character);
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
