package test5_platformer1;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstractSwing.GameSimpleS;

public class Game extends GameSimpleS
{
	public static void main(String[] args)
	{
		Game game = new Game();
		game.setScreen(900, 500, false);
		game.setTitle("Test 5");
		game.init();
		LevelOne lvl1 = new LevelOne(game);
		game.addLevel(lvl1);
		new Thread(game).start();
	}
}
