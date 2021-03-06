package test7_dungeon2;

/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstractSwing.GameSimpleD;

public class Game extends GameSimpleD
{
	public static void main(String[] args)
	{
		Game game = new Game();
		game.setScreen(900, 500, false);
		game.setTitle("Test 7");
		game.init();
		LevelOne lvl1 = new LevelOne(game);
		game.addLevel(lvl1);
		game.start();
	}
}
