package test1_collision;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstracts.GameSimpleS;

public class Game extends GameSimpleS
{
	public static void main(String [] args)
	{
		Game game = new Game();
		game.setScreen(900, 500, false);
		game.setTitle("Test 1");
		game.exitOnEsc(true);
		game.deleteFinishedLevel(true);
		game.init();
		LevelOne lvl1 = new LevelOne(game);
		game.addLevel(lvl1);
		new Thread(game).start();
	}
}
