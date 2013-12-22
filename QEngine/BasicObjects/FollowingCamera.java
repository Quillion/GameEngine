package BasicObjects;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import abstracts.Shape;

/**
 * This Camera will require to be given a Shape that it will be following around.
 */
public class FollowingCamera extends Camera
{
	private Shape character;

	/**
	 * We have to give following camera bounds and object to follow.
	 *
	 * @param leftRightBoundary Vertical offset of camera.
	 * @param topBottomBoundary Horizontal offset of the camera.
	 * @param character         The Shape to follow.
	 */
	public FollowingCamera(int leftRightBoundary, int topBottomBoundary, Shape character)
	{
		super(leftRightBoundary, topBottomBoundary);
		this.character = character;
	}

	/**
	 * Update camera position based on the character's coordinates which it should be looking at.
	 */
	public void updateCamera()
	{
		super.updateCamera(character.getCenterX(), character.getCenterY());
	}
}
