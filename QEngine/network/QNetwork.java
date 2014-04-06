package network;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

/**
 * This is the only object that is going to be passed between the Server and the Client.
 */
public abstract class QNetwork
{
	/**
	 * This enum is going to be constantly growing depending
	 * on the types of messages we want to send.
	 */
	public enum Type
	{
		None, Connect, NewPlayer, Box, BBox
	}

	/**
	 * Port that both Server and Client will be connecting to.
	 */
	static public int PORT = 57931;

	/**
	 * Instead of doing register on the client and server end,
	 * we will call this since it is simpler.
	 *
	 * @param endPoint Server or Client object to which we want to register this object.
	 */
	public static void register(EndPoint endPoint)
	{
		Kryo kryo = endPoint.getKryo();
		kryo.register(QNetwork.class);
	}

	private Type type;
	private Object object;

	/**
	 * Basic constructor. Object will be set to null, and type to None.
	 */
	public QNetwork()
	{
		setType(Type.None);
		setObject(null);
	}

	/**
	 * A slightly costly method. Based on the object type will be set accordingly.
	 *
	 * @param object The object we want to send.
	 */
	public QNetwork(Object object)
	{
		setType(Type.None);
		setObject(object);
	}

	/**
	 * Simple constructor. Give it type and the object and send it away.
	 *
	 * @param type   The type of the message you want to be sending.
	 * @param object The Object that you want to send.
	 */
	public QNetwork(Type type, Object object)
	{
		setType(type);
		setObject(object);
	}

	/**
	 * Tells you the type of the current object received.
	 *
	 * @return Type of the object.
	 */
	public Type getType()
	{
		return this.type;
	}

	/**
	 * Sets the type of the object that you are about to send to whatever you want.
	 *
	 * @param type New type for the object.
	 */
	public void setType(Type type)
	{
		this.type = type;
	}

	/**
	 * Gives you the object that was sent. The object sent depends on the type.
	 *
	 * @return Object that was sent, you will not know what it is unless you look at the type.
	 */
	public Object getObject()
	{
		return this.object;
	}

	/**
	 * Sets the object that you want to send to whatever value you wish.
	 * Make sure that the type reflects the object accordingly.
	 *
	 * @param object The object we want to send.
	 */
	public void setObject(Object object)
	{
		this.object = object;
	}
}
