package test1;

import abstracts.GameSimple;

/**
 * User: Edgar
 * Date: 12/19/13
 * Time: 7:43 PM
 */
public class Game extends GameSimple
{
	public static void main(String [] args)
	{
		Game game = new Game();
		game.setScreen(900, 500, false);
		game.init();
		LevelOne lvl1 = new LevelOne(game);
		game.addLevel(lvl1);
		new Thread(game).start();
	}
}
