package test5;

import BasicShapes.Item;
import BasicSprite.BPlatform;
import Constants.Constants;
import abstracts.Game;
import logic.*;
import abstracts.Level;
import platformer.BasicCharacter;
import platformer.Background;
import TooGeneral.NormalPlatformGenerator;
import logic.platformer.BasicAIController;
import utils.ImageProcessor;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Edgar
 * Date: 11/11/13
 * Time: 6:31 PM
 */
public class LevelOne extends Level
{
	private List<BPlatform> grounds;
	private List<BPlatform> platforms;

	private List<BasicCharacter> characters;
	private List<BasicCharacter> ai;

	private Camera camera;

	private Background background;

	private List<BasicAIController> docileComputers;
	private List<BasicAIController> jumpingComputers;

	private Item flag;

	public LevelOne(Game game)
	{
		super(game);

		grounds = new ArrayList<BPlatform>();
		platforms = new ArrayList<BPlatform>();

		characters = new ArrayList<BasicCharacter>();
		ai = new ArrayList<BasicCharacter>();
		docileComputers = new ArrayList<BasicAIController>();
		jumpingComputers = new ArrayList<BasicAIController>();

		camera = new Camera(11, 111, game.getWidth()/2, game.getHeight()/2);

		background = new Background(89, 110, 2138, 279, game.getWidth(), game.getHeight());

		flag = new Item();
	}

	@Override
	public void setup()
	{
	}

	@Override
	public void load()
	{
		/****************************************************************************/
		/******************************** CHARACTERS ********************************/
		/****************************************************************************/
		BasicCharacter tempCharacter;

		ImageExtractor extractor = new ImageExtractor("Images/Platformer/Characters/lucida.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(350);
		tempCharacter.setY(150);
		tempCharacter.setWidth(43);
		tempCharacter.setHeight(55);
		tempCharacter.setHorizontalOffset(3);
		tempCharacter.setVerticalOffset(4);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(3.97);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.5);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.19);
		tempCharacter.setLeftKey(KeyEvent.VK_LEFT);
		tempCharacter.setRightKey(KeyEvent.VK_RIGHT);
		tempCharacter.setJumpKey(KeyEvent.VK_UP);
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
		extractor = new ImageExtractor("Images/Platformer/Characters/bluesnail.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(300);
		tempCharacter.setY(150);
		tempCharacter.setWidth(40);
		tempCharacter.setHeight(35);
		tempCharacter.setHorizontalOffset(3);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(1);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.2);
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
		BasicAIController computer = new BasicAIController(tempCharacter);
		computer.setActivity(90);
		docileComputers.add(computer);
		ai.add(tempCharacter);

		extractor = new ImageExtractor("Images/Platformer/Characters/bluesnail.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(150);
		tempCharacter.setY(150);
		tempCharacter.setWidth(40);
		tempCharacter.setHeight(35);
		tempCharacter.setHorizontalOffset(3);
		tempCharacter.setVerticalOffset(4);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(0.8);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.1);
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
		computer = new BasicAIController(tempCharacter);
		computer.setActivity(95);
		computer.setChoiceDuration(20, 70);
		computer.setRange(300, 200);
		docileComputers.add(computer);
		ai.add(tempCharacter);

		extractor = new ImageExtractor("Images/Platformer/Characters/redsnail.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(900);
		tempCharacter.setY(250);
		tempCharacter.setWidth(40);
		tempCharacter.setHeight(35);
		tempCharacter.setHorizontalOffset(3);
		tempCharacter.setVerticalOffset(4);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(1.3);
		tempCharacter.setJump(6);
		tempCharacter.setAcceleration(0.3);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.2);
		tempCharacter.setJump(1, 100);
		tempCharacter.addJump(extractor.getImage(3, 3, 40, 35));
		tempCharacter.setStand(1, 100);
		tempCharacter.addStand(extractor.getImage(3, 3, 40, 35));
		tempCharacter.setWalk(3, 20);
		tempCharacter.addWalk(extractor.getImage(3, 48, 40, 35));
		tempCharacter.addWalk(extractor.getImage(47, 48, 40, 35));
		tempCharacter.addWalk(extractor.getImage(99, 48, 40, 35));
		computer = new BasicAIController(tempCharacter);
		computer.setRange(595, 400);
		docileComputers.add(computer);
		ai.add(tempCharacter);

		extractor = new ImageExtractor("Images/Platformer/Characters/zombiemushroom.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(400);
		tempCharacter.setY(0);
		tempCharacter.setWidth(65);
		tempCharacter.setHeight(70);
		tempCharacter.setHorizontalOffset(4);
		tempCharacter.setVerticalOffset(3);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(3.2);
		tempCharacter.setJump(7);
		tempCharacter.setAcceleration(1);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.2);
		tempCharacter.setJump(1, 12);
		tempCharacter.addJump(extractor.getImage(5, 154, 65, 70));
		tempCharacter.setStand(2, 12);
		tempCharacter.addStand(extractor.getImage(5, 5, 65, 70));
		tempCharacter.addStand(extractor.getImage(78, 5, 65, 70));
		tempCharacter.setWalk(4, 12);
		tempCharacter.addWalk(extractor.getImage(5, 81, 65, 70));
		tempCharacter.addWalk(extractor.getImage(78, 81, 65, 70));
		tempCharacter.addWalk(extractor.getImage(149, 81, 65, 70));
		tempCharacter.addWalk(extractor.getImage(221, 81, 65, 70));
		computer = new BasicAIController(tempCharacter);
		computer.setRange(350, 400);
		computer.setActivity(90);
		computer.setChoiceDuration(40, 190);
		jumpingComputers.add(computer);
		ai.add(tempCharacter);

		extractor = new ImageExtractor("Images/Platformer/Characters/zombiemushroom.png");

		tempCharacter = new BasicCharacter();
		tempCharacter.setX(1400);
		tempCharacter.setY(0);
		tempCharacter.setWidth(65);
		tempCharacter.setHeight(70);
		tempCharacter.setHorizontalOffset(4);
		tempCharacter.setVerticalOffset(3);
		tempCharacter.setGravity(0.2);
		tempCharacter.setMaxSpeed(3.2);
		tempCharacter.setJump(7);
		tempCharacter.setAcceleration(1);
		tempCharacter.setGroundFriction(0.2);
		tempCharacter.setAirFriction(0.2);
		tempCharacter.setJump(1, 12);
		tempCharacter.addJump(extractor.getImage(5, 154, 65, 70));
		tempCharacter.setStand(2, 12);
		tempCharacter.addStand(extractor.getImage(5, 5, 65, 70));
		tempCharacter.addStand(extractor.getImage(78, 5, 65, 70));
		tempCharacter.setWalk(4, 12);
		tempCharacter.addWalk(extractor.getImage(5, 81, 65, 70));
		tempCharacter.addWalk(extractor.getImage(78, 81, 65, 70));
		tempCharacter.addWalk(extractor.getImage(149, 81, 65, 70));
		tempCharacter.addWalk(extractor.getImage(221, 81, 65, 70));
		computer = new BasicAIController(tempCharacter);
		computer.setRange(1350, 400);
		computer.setActivity(80);
		computer.setChoiceDuration(40, 190);
		computer.setJumpFParameters(45, 3);
		jumpingComputers.add(computer);
		ai.add(tempCharacter);

		/****************************************************************************/
		/********************************** GROUND **********************************/
		/****************************************************************************/
		NormalPlatformGenerator generator = new NormalPlatformGenerator();
		/************ LEFTMOST WALL **************/
		BPlatform tempPlatform = new BPlatform();
		tempPlatform.setX(0);
		tempPlatform.setY(0);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructVertical(generator.getWallTop(), generator.getWall()));
		for(int i = 0; i < 7; i++)
			tempPlatform.setImage(ImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		tempPlatform.setImage(ImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWallBottom()));
		grounds.add(tempPlatform);

		/************ THE GAP **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(1100);
		tempPlatform.setY(330);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 2; i++)
			tempPlatform.setImage(ImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		tempPlatform.setImage(ImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWallBottom()));
		grounds.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(1130);
		tempPlatform.setY(390);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		tempPlatform.setImage(ImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		grounds.add(tempPlatform);

		/************ RIGHTMOST WALL **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(2250);
		tempPlatform.setY(0);
		tempPlatform.setHorizontalOffset(9);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructVertical(generator.getWall(), generator.getWall()));
		for(int i = 0; i < 3; i++)
			tempPlatform.setImage(ImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWall()));
		tempPlatform.setImage(ImageProcessor.constructVertical(tempPlatform.getImage(), generator.getWallBottom()));
		grounds.add(tempPlatform);

		/************ GROUND **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(0);
		tempPlatform.setY(518);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		for(int i = 0; i < 17; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround()));
		grounds.add(tempPlatform);

		/************ HIGHER GROUND **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(1710);
		tempPlatform.setY(228);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructHorizontal(generator.getGround(5), generator.getGround(5)));
		for(int i = 0; i < 5; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(5)));
		grounds.add(tempPlatform);

		/****************************************************************************/
		/******************************** PLATFORMS *********************************/
		/****************************************************************************/
		/************ 4 JUMPING PLATFORMS **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(430);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(290);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(200);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(50);
		tempPlatform.setY(110);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		/************ 3 BIG PLATFORMS **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(415);
		tempPlatform.setY(234);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getGround(3));
		for(int i = 0; i < 5; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(3)));
		platforms.add(tempPlatform);

		/************ SMALL LEDGE **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(505);
		tempPlatform.setY(292);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(235);
		tempPlatform.setY(350);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getGround(3));
		for(int i = 0; i < 5; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(3)));
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(595);
		tempPlatform.setY(408);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getGround(2));
		for(int i = 0; i < 4; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(2)));
		platforms.add(tempPlatform);

		/************ SMALL LEDGE **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(325);
		tempPlatform.setY(466);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatformTop());
		platforms.add(tempPlatform);

		/************ TOP ACCESS **************/
		tempPlatform = new BPlatform();
		tempPlatform.setX(350);
		tempPlatform.setY(130);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		for(int i = 0; i < 4; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getPlatform()));
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(930);
		tempPlatform.setY(110);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		for(int i = 0; i < 1; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getPlatform()));
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(1350);
		tempPlatform.setY(120);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(generator.getPlatform());
		for(int i = 0; i < 4; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getPlatform()));
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(1440);
		tempPlatform.setY(344);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructHorizontal(generator.getGround(3), generator.getGround(3)));
		for(int i = 0; i < 2; i++)
			tempPlatform.setImage(ImageProcessor.constructHorizontal(tempPlatform.getImage(), generator.getGround(3)));
		platforms.add(tempPlatform);

		tempPlatform = new BPlatform();
		tempPlatform.setX(1300);
		tempPlatform.setY(450);
		tempPlatform.setVerticalOffset(5);
		tempPlatform.setImage(ImageProcessor.constructHorizontal(generator.getGround(), generator.getGround()));
		tempPlatform.setImage(ImageProcessor.extractImage(tempPlatform.getImage(), 0, 0, 50, 100));
		platforms.add(tempPlatform);

		/****************************************************************************/
		/********************************** FLAG ************************************/
		/****************************************************************************/
		extractor = new ImageExtractor("Images/Platformer/Objects/flags.png");
		flag.setX(1640);
		flag.setY(310);
		flag.setAnimation(6, 10);
		flag.addImage(extractor.getImage(218, 200, 30, 25));
		flag.addImage(extractor.getImage(251, 200, 30, 25));
		flag.addImage(extractor.getImage(283, 200, 30, 25));
		flag.addImage(extractor.getImage(315, 200, 30, 25));
		flag.addImage(extractor.getImage(347, 200, 30, 25));
		flag.addImage(extractor.getImage(380, 200, 30, 25));

		extractor = new ImageExtractor("Images/Platformer/Backgrounds/japan.png");
		background.setBackground(extractor.getImage(30, 10, 930, 350));
		background.setMidGround(extractor.getImage(25, 995, 360, 250));
		background.addForegroundObject(extractor.getImage(425, 860, 350, 485));
		background.generateBackground(15);

		setLoaded(true);
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, getGame().getWidth(), getGame().getHeight());

		background.draw(g, camera.getX(), camera.getY());

		for(BPlatform platform : platforms)
			camera.draw(g, platform);

		for (BasicCharacter character : characters)
			camera.draw(g, character);

		for (BasicCharacter character : ai)
			camera.draw(g, character);

		for(BPlatform ground : grounds)
			camera.draw(g, ground);

		camera.draw(g, flag);
	}

	@Override
	public void update()
	{
		int x = 0;
		int y = 0;
		// CHARACTERS
		for(BasicCharacter character : characters)
		{
			Engine.preUpdate(character);
			character.setStanding(false);
			// VS AI
			for(BasicCharacter comp : ai)
			{
				int vert = Engine.verticalCollision(character, comp);
				int hort = Engine.horizontalCollision(character, comp);

				if(hort == Constants.RIGHT)
				{
					character.setXVector(-character.getJump());
					character.setYVector(-character.getJump() / 2);
				}
				else if(hort == Constants.LEFT)
				{
					character.setXVector(character.getJump());
					character.setYVector(-character.getJump() / 2);
				}

				if(vert == Constants.UP)
				{
					character.setYVector(character.getJump());
				}
				else if(vert == Constants.DOWN)
				{
					character.setYVector(-character.getJump());
				}
			}
			// VS GROUND
			for(BPlatform ground : grounds)
			{
				int vert = Engine.verticalCollision(character, ground);
				int hort = Engine.horizontalCollision(character, ground);

				if(hort == Constants.RIGHT)
				{
					character.setRight(false);
					character.setX(ground.getLeftX() - character.getWidth() + character.getHorizontalOffset());
					character.setXVector(0);
				}
				else if(hort == Constants.LEFT)
				{
					character.setLeft(false);
					character.setX(ground.getRightX() - character.getHorizontalOffset());
					character.setXVector(0);
				}

				if(vert == Constants.UP)
				{
					if(character.getGravity() < 0)
						character.setStanding(true);
					character.setY(ground.getBottomY() - character.getVerticalOffset());
					character.setYVector(0);
				}
				else if(vert == Constants.DOWN)
				{
					if(character.getGravity() > 0)
						character.setStanding(true);
					character.setY(ground.getTopY() - character.getHeight() + character.getVerticalOffset());
					character.setYVector(0);
				}
			}
			// VS PLATFORMS
			for(BPlatform platform : platforms)
			{
				int vert = Engine.verticalCollision(character, platform);

				if(vert == Constants.DOWN)
				{
					if(character.getGravity() > 0)
						character.setStanding(true);
					character.setY(platform.getTopY() - character.getHeight() + character.getVerticalOffset());
					character.setYVector(0);
				}
			}

			setActive(!Engine.collision(character, flag));

			Engine.postUpdate(character);

			x += character.getCenterX();
			y += character.getCenterY();
		}

		for(BasicAIController computer : docileComputers)
		{
			computer.randomNoJump();
		}

		for(BasicAIController computer : jumpingComputers)
		{
			computer.random();
		}

		// COMPUTERS
		for(BasicCharacter character : ai)
		{
			Engine.preUpdate(character);
			character.setStanding(false);
			// VS GROUND
			for(BPlatform ground : grounds)
			{
				int vert = Engine.verticalCollision(character, ground);
				int hort = Engine.horizontalCollision(character, ground);

				if(hort == Constants.RIGHT)
				{
					character.setRight(false);
					character.setX(ground.getLeftX() - character.getWidth() + character.getHorizontalOffset());
					character.setXVector(0);
				}
				else if(hort == Constants.LEFT)
				{
					character.setLeft(false);
					character.setX(ground.getRightX() - character.getHorizontalOffset());
					character.setXVector(0);
				}

				if(vert == Constants.UP)
				{
					if(character.getGravity() < 0)
						character.setStanding(true);
					character.setY(ground.getBottomY() - character.getVerticalOffset());
					character.setYVector(0);
				}
				else if(vert == Constants.DOWN)
				{
					if(character.getGravity() > 0)
						character.setStanding(true);
					character.setY(ground.getTopY() - character.getHeight() + character.getVerticalOffset());
					character.setYVector(0);
				}
			}
			// VS PLATFORMS
			for(BPlatform platform : platforms)
			{
				int vert = Engine.verticalCollision(character, platform);

				if(vert == Constants.DOWN)
				{
					if(character.getGravity() > 0)
						character.setStanding(true);
					character.setY(platform.getTopY() - character.getHeight() + character.getVerticalOffset());
					character.setYVector(0);
				}
			}

			Engine.postUpdate(character);
		}

		camera.updateCamera(x/characters.size(), y/characters.size());
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		for(BasicCharacter character : characters)
			Engine.keyPressed(e.getKeyCode(), character);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		for(BasicCharacter character : characters)
			Engine.keyReleased(e.getKeyCode(), character);
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	@Override
	public void delete()
	{
	}
}
