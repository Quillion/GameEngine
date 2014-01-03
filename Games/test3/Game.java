package test3;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstracts.GameSimple;

public class Game extends GameSimple
{
	public static void main(String[] args)
	{
		Game game = new Game();
		game.setScreen(900, 500, false);
		game.init();
		LevelOne lvl1 = new LevelOne(game);
		game.addLevel(lvl1);
		new Thread(game).start();
	}
}
