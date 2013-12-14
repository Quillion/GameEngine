package test2;
/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import BasicSprite.Platform;
import constants.Constants;
import logic.Camera;
import logic.CollisionEngine;
import logic.Engine;
import platformer.BasicSprite.MCharacter;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class Game
{
//    private int WIDTH, HEIGHT;
//
//	private List<Platform> platforms;
//	private Platform temp_platform;
//
//	private MCharacter character;
//
//	private Camera camera;
//
//    public Game(int WIDTH, int HEIGHT)
//    {
//        this.WIDTH = WIDTH;
//        this.HEIGHT = HEIGHT;
//
//		platforms = new ArrayList<Platform>();
//
//		temp_platform = new Platform();
//		temp_platform.setX(0);
//		temp_platform.setY(430);
//		temp_platform.setWidth(640);
//		temp_platform.setHeight(50);
//		temp_platform.setColor(Color.GREEN);
//		platforms.add(temp_platform);
//
//		temp_platform = new Platform();
//		temp_platform.setX(0);
//		temp_platform.setY(0);
//		temp_platform.setWidth(10);
//		temp_platform.setHeight(430);
//		temp_platform.setColor(Color.GREEN);
//		platforms.add(temp_platform);
//
//		temp_platform = new Platform();
//		temp_platform.setX(630);
//		temp_platform.setY(0);
//		temp_platform.setWidth(10);
//		temp_platform.setHeight(430);
//		temp_platform.setColor(Color.GREEN);
//		platforms.add(temp_platform);
//
//		temp_platform = new Platform();
//		temp_platform.setX(10);
//		temp_platform.setY(0);
//		temp_platform.setWidth(620);
//		temp_platform.setHeight(30);
//		temp_platform.setColor(Color.GREEN);
//		platforms.add(temp_platform);
//
//		temp_platform = new Platform();
//		temp_platform.setX(10);
//		temp_platform.setY(370);
//		temp_platform.setWidth(70);
//		temp_platform.setHeight(30);
//		temp_platform.setColor(Color.CYAN);
//		platforms.add(temp_platform);
//
//		temp_platform = new Platform();
//		temp_platform.setX(140);
//		temp_platform.setY(300);
//		temp_platform.setWidth(70);
//		temp_platform.setHeight(30);
//		temp_platform.setColor(Color.BLUE);
//		platforms.add(temp_platform);
//
//		temp_platform = new Platform();
//		temp_platform.setX(10);
//		temp_platform.setY(230);
//		temp_platform.setWidth(70);
//		temp_platform.setHeight(30);
//		temp_platform.setColor(Color.RED);
//		platforms.add(temp_platform);
//
//		character = new MCharacter();
//		character.setX(WIDTH/2);
//		character.setY(HEIGHT/2);
//		character.setWidth(30);
//		character.setHeight(30);
//		character.setGravity(0.2);
//		character.setMaxSpeed(3.9);
//		character.setJump(5.5);
//		character.setAcceleration(0.5);
//		character.setGroundFriction(0.2);
//		character.setAirFriction(0.2);
//		character.setLeftKey(KeyEvent.VK_LEFT);
//		character.setRightKey(KeyEvent.VK_RIGHT);
//		character.setJumpKey(KeyEvent.VK_UP);
//		character.setColor(Color.ORANGE);
//
//		camera = new Camera(111, 111, WIDTH/2, HEIGHT/2);
//    }
//
//    public void draw(Graphics2D g)
//    {
//        g.setColor(Color.BLACK);
//        g.fillRect(0, 0, WIDTH, HEIGHT);
//
//		camera.draw(g, character);
//
//		for(Platform platform : platforms)
//		{
//			camera.draw(g, platform);
//		}
//
//		g.setColor(Color.WHITE);
//		g.drawString("| Camera | x: "+camera.getX()+" | y: "+camera.getY()+" |", 240, 120);
//		g.drawRect(camera.getCenterX() - camera.getHorizontalBoundary(),
//				camera.getCenterY() - camera.getVerticalBoundary(),
//				camera.getHorizontalBoundary()*2,
//				camera.getVerticalBoundary()*2);
//    }
//
//    public void update()
//    {
//		Engine.preUpdate(character);
//		character.setStanding(false);
//		for(Platform platform : platforms)
//		{
//			Constants.Direction vert = CollisionEngine.verticalCollision(character, platform);
//			Constants.Direction hort = CollisionEngine.horizontalCollision(character, platform);
//
//			if(hort == Constants.Direction.Right)
//			{
//				character.setRight(false);
//				character.setX(platform.getLeftX() - character.getWidth());
//				character.setXVector(0);
//			}
//			else if(hort == Constants.Direction.Left)
//			{
//				character.setLeft(false);
//				character.setX(platform.getRightX());
//				character.setXVector(0);
//			}
//
//			if(vert == Constants.Direction.Up)
//			{
//				if(character.getGravity() < 0)
//					character.setStanding(true);
//				character.setY(platform.getBottomY());
//				character.setYVector(0);
//			}
//			else if(vert == Constants.Direction.Down)
//			{
//				if(character.getGravity() > 0)
//					character.setStanding(true);
//				character.setY(platform.getTopY()-character.getHeight());
//				character.setYVector(0);
//			}
//		}
//		Engine.postUpdate(character);
//
//		camera.updateCamera(character.getCenterX(), character.getCenterY());
//    }
//
//    public void keyPressed(KeyEvent e)
//    {
//		Engine.keyPressed(e.getKeyCode(), character);
//    }
//
//    public void keyReleased(KeyEvent e)
//    {
//		Engine.keyReleased(e.getKeyCode(), character);
//    }
//
//    public void mouseEntered(MouseEvent e)
//    {
//    }
//
//    public void mousePressed(MouseEvent e)
//    {
//    }
//
//    public void mouseMoved(MouseEvent e)
//    {
//    }
//
//    public int getWIDTH ()
//    {
//        return WIDTH;
//    }
//
//    public int getHEIGHT ()
//    {
//        return HEIGHT;
//    }
}
