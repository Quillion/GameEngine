package test4;

import BasicShapes.Animation;
import BasicSprite.QBPlatform;
import BasicSprite.QMCharacter;
import Constants.QConstants;
import Logic.QCamera;
import Logic.QEngine;
import Logic.QImageExtractor;
import Logic.QImageProcessor;
import TooGeneral.NormalPlatformGenerator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class Game
{
    private int WIDTH, HEIGHT;

	private NormalPlatformGenerator generator;

	private List<QBPlatform> grounds;
	private List<QBPlatform> platforms;
	private QBPlatform temp_platform;

	private List<QMCharacter> characters;
	private List<QMCharacter> ai;
	private QMCharacter temp_character;

	private QCamera camera;

	QImageExtractor extractor;
	Animation animation;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

		generator = new NormalPlatformGenerator();

		grounds = new ArrayList<QBPlatform>();
		platforms = new ArrayList<QBPlatform>();

		characters = new ArrayList<QMCharacter>();
		ai = new ArrayList<QMCharacter>();

		temp_character = new QMCharacter();
		temp_character.setX(150);
		temp_character.setY(150);
		temp_character.setWidth(30);
		temp_character.setHeight(30);
		temp_character.setGravity(0.2);
		temp_character.setMaxSpeed(3.9);
		temp_character.setJump(6);
		temp_character.setAcceleration(0.5);
		temp_character.setGroundFriction(0.2);
		temp_character.setAirFriction(0.2);
		temp_character.setLeftKey(KeyEvent.VK_LEFT);
		temp_character.setRightKey(KeyEvent.VK_RIGHT);
		temp_character.setJumpKey(KeyEvent.VK_UP);
		temp_character.setColor(Color.ORANGE);
		characters.add(temp_character);

		temp_character = new QMCharacter();
		temp_character.setX(350);
		temp_character.setY(150);
		temp_character.setWidth(30);
		temp_character.setHeight(30);
		temp_character.setGravity(0.2);
		temp_character.setMaxSpeed(3.9);
		temp_character.setJump(6);
		temp_character.setAcceleration(0.5);
		temp_character.setGroundFriction(0.2);
		temp_character.setAirFriction(0.2);
		temp_character.setLeftKey(KeyEvent.VK_A);
		temp_character.setRightKey(KeyEvent.VK_D);
		temp_character.setJumpKey(KeyEvent.VK_W);
		temp_character.setColor(Color.LIGHT_GRAY);
		characters.add(temp_character);

		/************ LEFTMOST WALL **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(0);
		temp_platform.setY(0);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructVertical(generator.getWallTop(), generator.getWall()));
		for(int i = 0; i < 7; i++)
			temp_platform.setImage(QImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(QImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		grounds.add(temp_platform);

		/************ THE GAP **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(1100);
		temp_platform.setY(330);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 2; i++)
			temp_platform.setImage(QImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(QImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		grounds.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(1130);
		temp_platform.setY(390);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		temp_platform.setImage(QImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		grounds.add(temp_platform);

		/************ RIGHTMOST WALL **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(2250);
		temp_platform.setY(0);
		temp_platform.setHorizontalOffset(9);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 3; i++)
			temp_platform.setImage(QImageProcessor.constructVertical(temp_platform.getImage(), generator.getWall()));
		temp_platform.setImage(QImageProcessor.constructVertical(temp_platform.getImage(), generator.getWallBottom()));
		grounds.add(temp_platform);

		/************ GROUND **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(0);
		temp_platform.setY(518);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		for(int i = 0; i < 17; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround()));
		grounds.add(temp_platform);

		/************ HIGHER GROUND **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(1710);
		temp_platform.setY(228);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructHorizontal(generator.getGround(5), generator.getGround(5)));
		for(int i = 0; i < 5; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround(5)));
		grounds.add(temp_platform);

		/************ 4 JUMPING PLATFORMS **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(50);
		temp_platform.setY(430);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(50);
		temp_platform.setY(290);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(50);
		temp_platform.setY(200);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(50);
		temp_platform.setY(110);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		/************ 3 BIG PLATFORMS **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(415);
		temp_platform.setY(234);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getGround(3));
		for(int i = 0; i < 5; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround(3)));
		platforms.add(temp_platform);

		/************ SMALL LEDGE **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(505);
		temp_platform.setY(292);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(235);
		temp_platform.setY(350);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getGround(3));
		for(int i = 0; i < 5; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround(3)));
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(595);
		temp_platform.setY(408);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getGround(2));
		for(int i = 0; i < 4; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround(2)));
		platforms.add(temp_platform);

		/************ SMALL LEDGE **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(325);
		temp_platform.setY(466);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatformTop());
		platforms.add(temp_platform);

		/************ TOP ACCESS **************/
		temp_platform = new QBPlatform();
		temp_platform.setX(350);
		temp_platform.setY(130);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		for(int i = 0; i < 4; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getPlatform()));
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(930);
		temp_platform.setY(110);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		for(int i = 0; i < 1; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getPlatform()));
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(1350);
		temp_platform.setY(120);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(generator.getPlatform());
		for(int i = 0; i < 4; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getPlatform()));
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(1440);
		temp_platform.setY(344);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructHorizontal(generator.getGround(3), generator.getGround(3)));
		for(int i = 0; i < 2; i++)
			temp_platform.setImage(QImageProcessor.constructHorizontal(temp_platform.getImage(), generator.getGround(3)));
		platforms.add(temp_platform);

		temp_platform = new QBPlatform();
		temp_platform.setX(1300);
		temp_platform.setY(450);
		temp_platform.setVerticalOffset(5);
		temp_platform.setImage(QImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		temp_platform.setImage(QImageProcessor.extractImage(temp_platform.getImage(), 0, 0, 50, 100));
		platforms.add(temp_platform);

		camera = new QCamera(11, 111, WIDTH/2, HEIGHT/2);

		extractor = new QImageExtractor("Images/Characters/bluesnail.png");
		animation = new Animation(3, 20);
		animation.addImage(extractor.getImage(3, 48, 40, 35));
		animation.addImage(extractor.getImage(53, 48, 40, 35));
		animation.addImage(extractor.getImage(104, 48, 40, 35));
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		for(QBPlatform platform : platforms)
			camera.draw(g, platform);

		for (QMCharacter character : characters)
			camera.draw(g, character);

		for (QMCharacter character : ai)
			camera.draw(g, character);

		for(QBPlatform ground : grounds)
			camera.draw(g, ground);

		QImageExtractor extractor = new QImageExtractor("Images/Characters/bluesnail.png");
		g.drawImage(extractor.getImage(3, 3, 40, 35), null, 10, 10);
		g.drawImage(extractor.getImage(3, 48, 40, 35), null, 10, 50);
		g.drawImage(extractor.getImage(53, 48, 40, 35), null, 50, 50);
		g.drawImage(extractor.getImage(104, 48, 40, 35), null, 90, 50);
		g.setColor(Color.WHITE);
		g.drawRect(10, 10, 40, 35);
		g.drawRect(10, 50, 40, 35);
		g.drawRect(50, 50, 40, 35);
		g.drawRect(90, 50, 40, 35);
		g.drawImage(animation.getImage(), null, 10, 90);
    }

    public void update()
    {
		int x = 0;
		int y = 0;
		for(QMCharacter character : characters)
		{
			QEngine.preUpdate(character);
			character.setStanding(false);
			for(QBPlatform ground : grounds)
			{
				int vert = QEngine.verticalCollision(character, ground);
				int hort = QEngine.horizontalCollision(character, ground);

				if(hort == QConstants.RIGHT)
				{
					character.setRight(false);
					character.setX(ground.getLeftX() - character.getWidth());
					character.setXVector(0);
				}
				else if(hort == QConstants.LEFT)
				{
					character.setLeft(false);
					character.setX(ground.getRightX());
					character.setXVector(0);
				}

				if(vert == QConstants.UP)
				{
					if(character.getGravity() < 0)
						character.setStanding(true);
					character.setY(ground.getBottomY());
					character.setYVector(0);
				}
				else if(vert == QConstants.DOWN)
				{
					if(character.getGravity() > 0)
						character.setStanding(true);
					character.setY(ground.getTopY()-character.getHeight());
					character.setYVector(0);
				}
			}

			for(QBPlatform platform : platforms)
			{
				int vert = QEngine.verticalCollision(character, platform);

				if(vert == QConstants.DOWN)
				{
					if(character.getGravity() > 0)
						character.setStanding(true);
					character.setY(platform.getTopY()-character.getHeight());
					character.setYVector(0);
				}
			}

			QEngine.postUpdate(character);

			x += character.getCenterX();
			y += character.getCenterY();
		}

		camera.updateCamera(x/characters.size(), y/characters.size());
    }

    public void keyPressed(KeyEvent e)
    {
		for(QMCharacter character : characters)
			QEngine.keyPressed(e.getKeyCode(), character);
    }

    public void keyReleased(KeyEvent e)
    {
		for(QMCharacter character : characters)
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
