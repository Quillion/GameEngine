package platformer.ExtendedShapes;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import constants.Constants;
import platformer.MovingShapes.MBox;

/**
 * Controls for the box with vector qualities.
 */
public class MControls extends MBox
{
	private boolean left, right, standing;
	private int LEFT, RIGHT, JUMP;

	/**
	 * Creates the MControls.
	 * By default left, right and standing are false.
	 * And arrow keys are set to defaults stated in Constants.
	 */
	public MControls()
	{
		super();
		this.setLeft(false);
		this.setRight(false);
		this.setStanding(false);
		this.setLeftKey(Constants.LEFT);
		this.setRightKey(Constants.RIGHT);
		this.setJumpKey(Constants.JUMP);
	}

	/**
	 * Sets the boolean value signifying if the player is moving left.
	 *
	 * @param value boolean representing movement to the left.
	 */
	public void setLeft(boolean value)
	{
		this.left = value;
	}

	/**
	 * Tells if the character is moving left.
	 *
	 * @return boolean value stating if the character is moving left.
	 */
	public boolean getLeft()
	{
		return this.left;
	}

	/**
	 * Sets the boolean value signifying if character is moving right.
	 *
	 * @param value boolean representing movement to the right.
	 */
	public void setRight(boolean value)
	{
		this.right = value;
	}

	/**
	 * Tells if the character is moving right.
	 *
	 * @return boolean value stating if the character is moving right.
	 */
	public boolean getRight()
	{
		return this.right;
	}

	/**
	 * Used to specify if character is jumping or standing.
	 *
	 * @param value boolean representation of whether character is jumping or standing.
	 */
	public void setStanding(boolean value)
	{
		this.standing = value;
	}

	/**
	 * Returns value which states whether the character is standing or not.
	 *
	 * @return boolean value which will tell if character is standing.
	 */
	public boolean isStanding()
	{
		return this.standing;
	}

	/**
	 * Sets the key that would cause character to go left.
	 *
	 * @param key value to which to set left key to.
	 */
	public void setLeftKey(int key)
	{
		this.LEFT = key;
	}

	/**
	 * Tells which key is movement to left is assigned to.
	 *
	 * @return key value which left key is assigned to.
	 */
	public int getLeftKey()
	{
		return this.LEFT;
	}

	/**
	 * Sets the key that would cause character to go right.
	 *
	 * @param key value to which to set right key to.
	 */
	public void setRightKey(int key)
	{
		this.RIGHT = key;
	}

	/**
	 * Tells which key is movement to right is assigned to.
	 *
	 * @return key value which right key is assigned to.
	 */
	public int getRightKey()
	{
		return this.RIGHT;
	}

	/**
	 * Sets the key that would cause character to jump.
	 *
	 * @param key value to which to set jump key to.
	 */
	public void setJumpKey(int key)
	{
		this.JUMP = key;
	}

	/**
	 * Tells which key jump is assigned to.
	 *
	 * @return key value which jump key is assigned to.
	 */
	public int getJumpKey()
	{
		return this.JUMP;
	}

	/**
	 * Sets all the keys at once.
	 *
	 * @param left  what to set left key to.
	 * @param right what to set right key to.
	 * @param jump  what to set jump key to.
	 */
	public void setKeys(int left, int right, int jump)
	{
		this.setLeftKey(left);
		this.setRightKey(right);
		this.setJumpKey(jump);
	}
}
