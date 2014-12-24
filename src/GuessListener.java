import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

/**
 * The class to listen for guesses (String) from partner. Uses a socket to
 * listen. Extends Thread to run simultaneously.
 * 
 * 
 */
public class GuessListener extends Thread {
	GameWindow window;

	Socket socket;
	// This is my own random port Number... make sure it matches your partner.
	public final static int portNumber = 1338;
	// The name of your partner's computer.
	public String hostName;

	/**
	 * 
	 * @param gameWindow
	 *            The GameWindow played in.
	 * @param host
	 *            The name of partner's computer.
	 */
	public GuessListener(GameWindow gameWindow, String host) {
		window = gameWindow;
		hostName = host;
	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The method to run this thread. Does all the work to listen for guesses.
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		while (socket == null || socket.isConnected() == false) {
			try {
				if (hostName != null) {
					socket = new Socket(hostName, portNumber);
				}
				// Take a break - other threads may want to do stuff.
				sleep(75);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				// Keep trying to connect until it works!
				System.out.println("CONNECTING...");
			}
		}
		while (socket.isConnected()) {
			try {
				ObjectInputStream in = new ObjectInputStream(socket
						.getInputStream());
				String message = (String) (in.readObject());
				window.receiveGuess(message);
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (IOException e) {
				break;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}

	}
}
