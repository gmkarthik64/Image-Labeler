import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

/**
 * The main class for our game. Creates a new window that allows the user to
 * choose game or database.
 * 
 */
public class ImageLabeller extends JFrame implements ActionListener {
	private Button gameButton;

	private Button searchButton;

	private Container buttonContainer;

	/**
	 * Default constructor for the ImageLabeller
	 */
	public ImageLabeller() {
		super("Choose one:");
		setLayout(new FlowLayout());
		buttonContainer = new Container();
		gameButton = new Button("Play the Image Labelling Game");
		add(gameButton);
		searchButton = new Button("Use the search engine");
		add(searchButton);
		gameButton.addActionListener(this);
		gameButton.setActionCommand("game");
		searchButton.addActionListener(this);
		searchButton.setActionCommand("search");
	}

	/**
	 * Action performed. Occurs when the user clicks a button. Opens the
	 * corresponding window.
	 */
	public void actionPerformed(ActionEvent a) {
		if (a.getActionCommand().equals("game")) {

			NetworkInfoWindow infoWindow = new NetworkInfoWindow();
			infoWindow.setLocation(100, 100);
			infoWindow.setVisible(true);
			infoWindow.setDefaultCloseOperation(EXIT_ON_CLOSE);
			infoWindow.setSize(300, 200);
			infoWindow.setResizable(false);
			dispose();
		} else {
			SearchWindow window = new SearchWindow();
			window.setVisible(true);
			window.setDefaultCloseOperation(EXIT_ON_CLOSE);
			window.setSize(900, 700);
			window.setResizable(false);
			dispose();
		}
	}

	/**
	 * Opens an Image Labeller.
	 * 
	 * @param args
	 *            Default String array for running program from command line.
	 */
	public static void main(String args[]) {
		ImageLabeller window = new ImageLabeller();
		window.setLocation(100, 100);
		window.setVisible(true);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setSize(200, 100);
		window.setResizable(false);
	}
}
