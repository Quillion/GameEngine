package test5;

import abstracts.GameSimple;

/**
 * User: Edgar
 * Date: 11/14/13
 * Time: 9:27 PM
 */
public class Main extends GameSimple
{
	public static void main(String [] args)
	{
		Main game = new Main();
		game.setScreen(900, 500, false);
		game.init();
//		LevelOne lvl1 = new LevelOne(game);
//		LevelTwo lvl2 = new LevelTwo(game);
//		game.addLevel(lvl1);
//		game.addLevel(lvl2);
		new Thread(game).start();
	}
}
