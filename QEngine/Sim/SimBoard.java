package sim;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import basicObjects.Dimensions;
import basicObjects.shapes.Field;
import constants.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that creates the board and handles anything board related.
 */
public class SimBoard
{
	private int         sides;
	private List<Field> board;
	private int         idCounter;
	private Dimensions  size;

	/**
	 * If we are making a board then we have to specify the width and height of the board, we also have to specify if it
	 * is square or hexagonal board. If wrong sides is given, then you will get an exception.
	 *
	 * @param width
	 * 		How many fields across the board will have.
	 * @param height
	 * 		How many fields downward the board will have.
	 * @param sides
	 * 		Use Constants to specify hexagonal or square board.
	 */
	public SimBoard(int width, int height, int sides)
	{
		if (!this.setSides(sides))
		{
			throw new IllegalArgumentException("Wrong number for sides given");
		}
		this.setWidth(width);
		this.setHeight(height);
		this.generateBoard();
	}

	/**
	 * Returns what type of board we have.
	 *
	 * @return Type of the board corresponding to the Constants.
	 */
	public int getSides()
	{
		return this.sides;
	}

	/**
	 * We will keep this as private, because you should not be able to change the type of board once you set it.
	 *
	 * @param sides
	 * 		the type of board, hexagonal or square.
	 *
	 * @return True if correct board type was specified.
	 */
	private boolean setSides(int sides)
	{
		if (sides != Constants.FOUR_SIDED && sides != Constants.SIX_SIDED)
		{
			return false;
		}
		this.sides = sides;
		return true;
	}

	/**
	 * Sets the width of the board.
	 *
	 * @param width
	 * 		how many fields across the board should have.
	 */
	private void setWidth(int width)
	{
		this.size.setWidth(width);
	}

	/**
	 * Tell you how many fields across the board has.
	 *
	 * @return How many fields across the board has.
	 */
	public int getWidth()
	{
		return this.size.getWidth();
	}

	/**
	 * Sets the height of the board.
	 *
	 * @param height
	 * 		how many fields downward the board should have.
	 */
	private void setHeight(int height)
	{
		this.size.setHeight(height);
	}

	/**
	 * Tell you how many fields downwards the board has.
	 *
	 * @return The height of the board.
	 */
	public int getHeight()
	{
		return this.size.getHeight();
	}

	/**
	 * Generates the board based on width and height, and the type. Very simple method.
	 */
	private void generateBoard()
	{
		this.board = new ArrayList<Field>(this.getWidth() * this.getHeight() - 1);
		for (int y = 0; y < this.getHeight(); y++)
		{
			for (int x = 0; x < this.getWidth(); x++)
			{
				Field field = new Field(this.idCounter++);
				if (this.getSides() == Constants.FOUR_SIDED)
				{
					// WE ARE NOT LEFTMOST FIELD,
					// SO WE ADD FIELD TO THE LEFT OF US AS OUR NEIGHBOR
					if (x != 0)
					{
						field.addField(this.getField(x - 1, y));
					}
					// WE ARE NOT THE TOP FIELD
					// SO WE ADD EVERYTHING ABOVE US TO THE FIELD
					if (y != 0)
					{
						field.addField(this.getField(x, y - 1));
					}
				}
				else if (this.getSides() == Constants.SIX_SIDED)
				{
					// WE ARE NOT LEFTMOST FIELD,
					// SO WE ADD FIELD TO THE LEFT OF US AS OUR NEIGHBOR
					if (x != 0)
					{
						field.addField(this.getField(x - 1, y));
					}
					// WE ARE NOT THE TOP FIELD
					// SO WE ADD EVERYTHING ABOVE US TO THE FIELD
					if (y != 0)
					{
						// ATTACH TO THE FIELD DIRECTLY ABOVE
						field.addField(this.getField(x, y - 1));
						if (x % 2 == 0)
						{
							// ATTACH TO THE FIELD TO THE LEFT TOP OF YOU
							if (x != 0)
							{
								field.addField(this.getField(x - 1, y - 1));
							}
							// ATTACH TO THE FIELD TO THE RIGHT TOP OF YOU
							if (x != this.getWidth() - 1)
							{
								field.addField(this.getField(x + 1, y - 1));
							}
						}

					}
				}
				this.board.add(field);
			}
		}
	}

	/**
	 * Returns the field at given positions.
	 *
	 * @param x
	 * 		The x position of the field.
	 * @param y
	 * 		The y position of the field you want.
	 *
	 * @return The field at specified coordinates (hexagonal is also like square really if you think about it).
	 */
	public Field getField(int x, int y)
	{
		return this.getBoard().get(this.getWidth() * y + x);
	}

	/**
	 * Returns the whole game board, no idea why you would even want this.
	 *
	 * @return Fields on the whole game board.
	 */
	public List<Field> getBoard()
	{
		return this.board;
	}
}
