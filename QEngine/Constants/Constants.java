package Constants;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

/**
 * Constant values to be used for the game.
 */
public class Constants
{
	public enum Direction
	{
		None(-1), Left(0), Up(1), Right(2), Down(3), Jump(4), Total(5);

		private int value;

		private Direction(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return this.value;
		}
	}

	public enum FieldShape
	{
		NONE(-1), FOUR_SIDED(4), SIX_SIDED(6);

		private int value;

		private FieldShape(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return this.value;
		}
	}

	public enum Action
	{
		None, MoveLeft, MoveRight, MoveUp, MoveDown, Jump
	}

	// PLATFORMER
	public static int NONE = -1;
	public static int LEFT = 0;
	public static int RIGHT = 1;
	public static int UP = 2;
	public static int DOWN = 3;
	public static int JUMP = 4;

	// SIM
	public static int FOUR_SIDED = 4;
	public static int SIX_SIDED = 4;
}
