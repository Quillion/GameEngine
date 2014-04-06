package network;
/**
 * @author Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version Version 1
 * @since 1.6
 */

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

/**
 * Client for the game. Has basics for sending and receiving objects.
 */
public abstract class QClient extends Listener
{
	private Client client;

	private boolean connecting;

	/**
	 * Constructor class. Just give a host and it will attempt to connect
	 * to the specified server.
	 *
	 * @param host The host that the client will attempt to connect to.
	 */
	public QClient(String host)
	{
		connecting = false;
		try
		{
			client = new Client();
			client.start();

			QNetwork.register(client);

			client.addListener(this);

			client.connect(5000, host, QNetwork.PORT);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
		connecting = true;
	}

	/**
	 * Once connection is successful you will be ontified of it here.
	 *
	 * @param connection The successful connection to the server.
	 */
	public abstract void connected(Connection connection);

	/**
	 * Any messages received from the server will end up coming here.
	 *
	 * @param connection The connection (server) that sent the message.
	 * @param object     The object that was sent from the server.
	 */
	public abstract void received(Connection connection, Object object);

	/**
	 * Whenever a disconnect happens then you will know about it here.
	 *
	 * @param connection The server that you were disconnected from.
	 */
	public abstract void disconnected(Connection connection);

	/**
	 * This is set to false by default. Once connection is established the value is true.
	 *
	 * @return True if the connection is established, false if still connecting.
	 */
	public boolean isConnecting()
	{
		return connecting;
	}
}
