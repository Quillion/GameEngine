package test6;

import Objects.QGameSimple;
import test5.LevelOne;

/**
 * User: Edgar
 * Date: 11/14/13
 * Time: 9:27 PM
 */
public class Main extends QGameSimple
{
	public static void main(String [] args)
	{
		Main game = new Main();
		LevelOne lvl = new LevelOne();
		game.addLevel(lvl);
		new Thread(game).start();
	}
}
