package network;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import java.awt.event.KeyEvent;
import java.io.Serializable;

/**
 * This object will be used to broadcast all the keyboard actions to all the
 * users.
 */
public class Keyboard implements Serializable
{
	/**
	 * Currently we only have key presses and key releases.
	 */
	public enum Action
	{
		keyPressed, keyReleased
	}

	private int keyCode;
	private Action action;
	private int id;

	/**
	 * Whenever we send anything to the server or clients
	 * we have to specify unique id that the client has.
	 *
	 * @param id Unique id of the client who created this action.
	 */
	public Keyboard(int id)
	{
		this.setId(id);
		this.setAction(null);
		this.setKeyCode(0);
	}

	/**
	 * Builds a full keyboard action, along with the unique id.
	 *
	 * @param id     Unique client id of the given action.
	 * @param e      What key was pressed.
	 * @param action What action it was.
	 */
	public Keyboard(int id, KeyEvent e, Action action)
	{
		this.setId(id);
		this.setKeyCode(e.getKeyCode());
		this.setAction(action);
	}

	/**
	 * Returns you the key code that represents which key was pressed.
	 *
	 * @return Keycode of which key was pressed.
	 */
	public int getKeyCode()
	{
		return this.keyCode;
	}

	/**
	 * Sets the keycode of the specified Keyboard to what you want.
	 *
	 * @param keyCode What key was pressed.
	 */
	public void setKeyCode(int keyCode)
	{
		this.keyCode = keyCode;
	}

	/**
	 * Sets the keycode from given KeyEvent.
	 *
	 * @param e The event, which will contain which key was pressed.
	 */
	public void setKeyCode(KeyEvent e)
	{
		this.keyCode = e.getKeyCode();
	}

	/**
	 * Tells you whether the given keyboard action is press or release.
	 *
	 * @return Press or release depending on what the action was.
	 */
	public Action getAction()
	{
		return this.action;
	}

	/**
	 * Sets the given action to what you want.
	 * I have no idea why you would want to use it, but here you go.
	 *
	 * @param action Press or release, depending on what you pressed.
	 */
	public void setAction(Action action)
	{
		this.action = action;
	}

	/**
	 * Returns the id that is given to this Keyboard action.
	 * Id is unique for each client.
	 *
	 * @return Unique id which represents client.
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Sets the id to what you want. This is a private method, so yeah...
	 *
	 * @param id New id of this keyboard.
	 */
	private void setId(int id)
	{
		this.id = id;
	}
}
