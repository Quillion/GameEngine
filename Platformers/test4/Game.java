package test4;

import BasicShapes.Animation;
import BasicSprite.QBPlatform;
import BasicSprite.QMCharacter;
import Constants.QConstants;
import Logic.*;
import Platformer.BasicCharacter;
import TooGeneral.NormalPlatformGenerator;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class Game
{
    private int WIDTH, HEIGHT;

	private List<QBPlatform> grounds;
	private List<QBPlatform> platforms;

	private List<BasicCharacter> characters;
	private List<BasicCharacter> ai;

	private QCamera camera;

	private List<BasicAIController> computers;

	QImageExtractor extractor;
	Animation animation;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

		grounds = new ArrayList<QBPlatform>();
		platforms = new ArrayList<QBPlatform>();

		characters = new ArrayList<BasicCharacter>();
		ai = new ArrayList<BasicCharacter>();
		computers = new ArrayList<BasicAIController>();

		/****************************************************************************/
		/******************************** CHARACTERS ********************************/
		/****************************************************************************/
		extractor = new QImageExtractor("Images/Characters/cat.png");

		BasicCharacter tempCharacter = new BasicCharacter();
		tempCharacter.setX(150);
		tempCharacter.setY(150);
		tempCharacter.setWidth(43);
		tempCharacter.setHeight(43);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(3.9);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.5);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.2);
		tempCharacter.setLeftKey(KeyEvent.VK_LEFT);
		tempCharacter.setRightKey(KeyEvent.VK_RIGHT);
		tempCharacter.setJumpKey(KeyEvent.VK_UP);
		tempCharacter.setJump(1, 100);
		tempCharacter.addJump(extractor.getImage(3, 3, 43, 43));
		tempCharacter.setStand(4, 21);
		tempCharacter.addStand(extractor.getImage(3, 59, 43, 43));
		tempCharacter.addStand(extractor.getImage(3, 59, 43, 43));
		tempCharacter.addStand(extractor.getImage(54, 58, 43, 43));
		tempCharacter.addStand(extractor.getImage(109, 58, 43, 43));
		tempCharacter.setWalk(3, 12);
		tempCharacter.addWalk(extractor.getImage(3, 3, 43, 43));
		tempCharacter.addWalk(extractor.getImage(53, 3, 43, 43));
		tempCharacter.addWalk(extractor.getImage(111, 3, 43, 43));
		characters.add(tempCharacter);

		extractor = new QImageExtractor("Images/Characters/lucida.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(350);
		tempCharacter.setY(150);
		tempCharacter.setWidth(43);
		tempCharacter.setHeight(55);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(3.9);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.5);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.2);
		tempCharacter.setLeftKey(KeyEvent.VK_A);
		tempCharacter.setRightKey(KeyEvent.VK_D);
		tempCharacter.setJumpKey(KeyEvent.VK_W);
		tempCharacter.setJump(1, 100);
		tempCharacter.addJump(extractor.getImage(6, 190, 43, 55));
		tempCharacter.setStand(5, 20);
		tempCharacter.addStand(extractor.getImage(3, 3, 43, 55));
		tempCharacter.addStand(extractor.getImage(3, 3, 43, 55));
		tempCharacter.addStand(extractor.getImage(3, 3, 43, 55));
		tempCharacter.addStand(extractor.getImage(59, 3, 43, 55));
		tempCharacter.addStand(extractor.getImage(114, 3, 43, 55));
		tempCharacter.setWalk(3, 15);
		tempCharacter.addWalk(extractor.getImage(3, 68, 43, 55));
		tempCharacter.addWalk(extractor.getImage(59, 68, 43, 55));
		tempCharacter.addWalk(extractor.getImage(114, 68, 43, 55));
		characters.add(tempCharacter);

		/****************************************************************************/
		/************************************ AI ************************************/
		/****************************************************************************/
		extractor = new QImageExtractor("Images/Characters/bluesnail.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(300);
		tempCharacter.setY(150);
		tempCharacter.setWidth(40);
		tempCharacter.setHeight(35);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(3.9);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.5);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.2);
		tempCharacter.setJump(1, 100);
		tempCharacter.addJump(extractor.getImage(3, 3, 40, 35));
		tempCharacter.setStand(1, 100);
		tempCharacter.addStand(extractor.getImage(3, 3, 40, 35));
		tempCharacter.setWalk(3, 20);
		tempCharacter.addWalk(extractor.getImage(3, 48, 40, 35));
		tempCharacter.addWalk(extractor.getImage(53, 48, 40, 35));
		tempCharacter.addWalk(extractor.getImage(104, 48, 40, 35));
		ai.add(tempCharacter);

		extractor = new QImageExtractor("Images/Characters/redsnail.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(400);
		tempCharacter.setY(150);
		tempCharacter.setWidth(40);
		tempCharacter.setHeight(35);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(3.9);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.5);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.2);
		tempCharacter.setJump(1, 100);
		tempCharacter.addJump(extractor.getImage(3, 3, 40, 35));
		tempCharacter.setStand(1, 100);
		tempCharacter.addStand(extractor.getImage(3, 3, 40, 35));
		tempCharacter.setWalk(3, 20);
		tempCharacter.addWalk(extractor.getImage(3, 48, 40, 35));
		tempCharacter.addWalk(extractor.getImage(53, 48, 40, 35));
		tempCharacter.addWalk(extractor.getImage(104, 48, 40, 35));
		ai.add(tempCharacter);

		extractor = new QImageExtractor("Images/Characters/bluesnail.png");
		animation = new Animation(3, 20);
		animation.addImage(extractor.getImage(3, 48, 40, 35));
		animation.addImage(extractor.getImage(53, 48, 40, 35));
		animation.addImage(extractor.getImage(104, 48, 40, 35));


		NormalPlatformGenerator generator = new NormalPlatformGenerator();
		/************ LEFTMOST WALL **************/
		QBPlatform tempPlatform = new QBPlatform();
		tempPlatform.setX(0);
		tempPlatform.setY(0);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructVertical(generator.getWallTop(), generator.getWall()));
		for(int i = 0; i < 7; i++)
			tempPlatform.setImage(QImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		tempPlatform.setImage(QImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWallBottom()));
		grounds.add(tempPlatform);

		/************ THE GAP **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(1100);
		tempPlatform.setY(330);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 2; i++)
			tempPlatform.setImage(QImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		tempPlatform.setImage(QImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWallBottom()));
		grounds.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(1130);
		tempPlatform.setY(390);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		tempPlatform.setImage(QImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		grounds.add(tempPlatform);

		/************ RIGHTMOST WALL **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(2250);
		tempPlatform.setY(0);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 3; i++)
			tempPlatform.setImage(QImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		tempPlatform.setImage(QImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWallBottom()));
		grounds.add(tempPlatform);

		/************ GROUND **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(0);
		tempPlatform.setY(518);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		for(int i = 0; i < 17; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround()));
		grounds.add(tempPlatform);

		/************ HIGHER GROUND **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(1710);
		tempPlatform.setY(228);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructHorizontal(generator.getGround(5), generator.getGround(5)));
		for(int i = 0; i < 5; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(5)));
		grounds.add(tempPlatform);

		/************ 4 JUMPING PLATFORMS **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(430);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(290);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(200);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(110);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		/************ 3 BIG PLATFORMS **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(415);
		tempPlatform.setY(234);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getGround(3));
		for(int i = 0; i < 5; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(3)));
		platforms.add(tempPlatform);

		/************ SMALL LEDGE **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(505);
		tempPlatform.setY(292);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(235);
		tempPlatform.setY(350);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getGround(3));
		for(int i = 0; i < 5; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(3)));
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(595);
		tempPlatform.setY(408);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getGround(2));
		for(int i = 0; i < 4; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(2)));
		platforms.add(tempPlatform);

		/************ SMALL LEDGE **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(325);
		tempPlatform.setY(466);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatformTop());
		platforms.add(tempPlatform);

		/************ TOP ACCESS **************/
		tempPlatform = new QBPlatform();
		tempPlatform.setX(350);
		tempPlatform.setY(130);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		for(int i = 0; i < 4; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getPlatform()));
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(930);
		tempPlatform.setY(110);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		for(int i = 0; i < 1; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getPlatform()));
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(1350);
		tempPlatform.setY(120);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		for(int i = 0; i < 4; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getPlatform()));
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(1440);
		tempPlatform.setY(344);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructHorizontal(generator.getGround(3), generator.getGround(3)));
		for(int i = 0; i < 2; i++)
			tempPlatform.setImage(QImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(3)));
		platforms.add(tempPlatform);

		tempPlatform = new QBPlatform();
		tempPlatform.setX(1300);
		tempPlatform.setY(450);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(QImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		tempPlatform.setImage(QImageProcessor.extractImage(tempPlatform.getImage(), 0, 0, 50, 100));
		platforms.add(tempPlatform);

		camera = new QCamera(11, 111, WIDTH/2, HEIGHT/2);
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

		for(QBPlatform platform : platforms)
			camera.draw(g, platform);

		for (BasicCharacter character : characters)
			camera.draw(g, character);

		for (BasicCharacter character : ai)
			camera.draw(g, character);

		for(QBPlatform ground : grounds)
			camera.draw(g, ground);
/*
		QImageExtractor extractor = new QImageExtractor("Images/Characters/cat.png");
		g.drawImage(extractor.getImage(3, 111, 40, 35), null, 10, 10);
		g.drawImage(extractor.getImage(), null, 10, 130);
		g.setColor(Color.WHITE);
		g.drawRect(10, 10, 43, 55);*/
    }

    public void update()
    {
		int x = 0;
		int y = 0;
		for(BasicCharacter character : characters)
		{
			QEngine.preUpdate(character);
			character.setStanding(false);
			for(BasicCharacter comp : ai)
			{
				int vert = QEngine.verticalCollision(character, comp);
				int hort = QEngine.horizontalCollision(character, comp);

				if(hort == QConstants.RIGHT)
				{
					character.setXVector(-character.getJump());
					character.setYVector(-character.getJump() / 2);
				}
				else if(hort == QConstants.LEFT)
				{
					character.setXVector(character.getJump());
					character.setYVector(-character.getJump() / 2);
				}

				if(vert == QConstants.UP)
				{
					character.setYVector(character.getJump());
				}
				else if(vert == QConstants.DOWN)
				{
					character.setYVector(-character.getJump());
				}
			}
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

		for(BasicCharacter character : ai)
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
		}

		camera.updateCamera(x/characters.size(), y/characters.size());
    }

    public void keyPressed(KeyEvent e)
    {
		for(BasicCharacter character : characters)
			QEngine.keyPressed(e.getKeyCode(), character);
    }

    public void keyReleased(KeyEvent e)
    {
		for(BasicCharacter character : characters)
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
