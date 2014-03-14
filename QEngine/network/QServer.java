package network;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import java.io.IOException;

/**
 * Server for the game. I would prefer you use TCP instead of UDP while using this.
 */
public abstract class QServer extends Listener
{
	private Server server;

	private boolean initializing;

	/**
	 * Constructor class. Creates a server running at whatever PORT
	 * is specified in the QNetowork port class.
	 */
	public QServer()
	{
		initializing = false;
		try
		{
			server = new Server();

			QNetwork.register(server);

			server.addListener(this);

			server.bind(QNetwork.PORT);
			server.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		initializing = true;
	}

	/**
	 * Which connection just connected to the server.
	 *
	 * @param connection The connection that just established a handshake.
	 */
	public abstract void connected(Connection connection);

	/**
	 * You will know from which connection you received which object.
	 *
	 * @param c      Connection from which you received the object.
	 * @param object The object that the connection gave you.
	 */
	public abstract void received(Connection c, Object object);

	/**
	 * Which connection just disconnected from the server.
	 *
	 * @param c The connection that just left.
	 */
	public abstract void disconnected(Connection c);

	/**
	 * Tells you the status of Server. Once initialization is true,
	 * then the Server is capable of receiving connections and receiving objects.
	 *
	 * @return True if the server is ready to receive objects and connections, false otherwise.
	 */
	public boolean isInitializing()
	{
		return initializing;
	}
}
