import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * The Window to ask for partner's computer name to connect the Sockets.
 * 
 */
public class NetworkInfoWindow extends JFrame implements ActionListener,
		KeyListener {
	private TextField hostInput;

	private JLabel infoLabel, inputLabel;

	private Button searchButton;

	private Container buttonContainer;

	/**
	 * Default constructor for the prompt window.
	 */
	public NetworkInfoWindow() {
		super("Network information");
		setLayout(new FlowLayout());
		try {
			infoLabel = new JLabel("Your computer name is: "
					+ InetAddress.getLocalHost().getHostName());
			add(infoLabel);
			inputLabel = new JLabel("Please enter partner's computer name:");
			add(inputLabel);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		hostInput = new TextField("", 20);
		add(hostInput);
		hostInput.addKeyListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

	}

	/**
	 * If enter is pressed, create a new GameWindow using that partner name.
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {
			GameWindow window = new GameWindow(hostInput.getText());
			window.setLocation(100, 100);
			window.setVisible(true);
			window.setDefaultCloseOperation(EXIT_ON_CLOSE);
			window.setSize(300, 450);
			window.setResizable(false);
			TimerWindow window2 = new TimerWindow(window);
			window2.setLocation(420, 100);
			window2.setVisible(true);
			window2.setDefaultCloseOperation(EXIT_ON_CLOSE);
			window2.setSize(200, 100);
			window2.setResizable(false);
			dispose();
		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
