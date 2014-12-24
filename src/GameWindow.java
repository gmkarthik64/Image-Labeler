import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
import java.net.*;

/**
 * The main class. Provides the framework for playing the game as well as the
 * Window to play it in.
 * 
 */
public class GameWindow extends JFrame implements ActionListener, KeyListener {

	private BufferedImage img;

	private JTextField field;

	private JTextField message;

	private JPanel textArea;

	private JPanel messageArea;

	private JPanel pic;

	private ArrayList<String> guesses;

	private ArrayList<String> theirGuesses;

	private ArrayList<LabelledImage> imageList;

	private ArrayList<LabelledImage> currentList;

	private Button back;

	ServerSocket server;

	Socket sendSocket;

	GuessListener guessListener;

	private int currentScore;

	private HighScore highScores;

	private String partnerName;

	private boolean justWon;

	private GameImageComponent imgComp;

	private LabelledImage current;

	/**
	 * The constructor. Sets up all variables and starts the other thread.
	 * Connects sockets.
	 * 
	 * @param partnerName
	 *            The name of partner's computer.
	 */
	public GameWindow(String partnerName) {
		super("Image Labeller Game");
		justWon = false;
		this.partnerName = partnerName;
		// Read in database.
		try {
			BufferedReader f = new BufferedReader(
					new FileReader("database.txt"));
			imageList = new ArrayList<LabelledImage>();
			currentList = new ArrayList<LabelledImage>();
			while (true)// Runs for each line in database
			{
				String s = "";
				s = f.readLine();
				if (s == null)
					break;
				String[] data = s.split(" ");
				LabelledImage temp = new LabelledImage(data[0]);
				temp.setCount(Integer.parseInt(data[1]));
				for (int i = 2; i < data.length; i += 2) {
					temp.add(new Label(data[i], Integer.parseInt(data[i + 1])));
				}
				imageList.add(temp);
				currentList.add(temp);
			}
		} catch (IOException e) {
		}
		// Start the guess listening thread running.
		guessListener = new GuessListener(this, partnerName);
		guessListener.start();
		try {
			// Accept the socket connection.
			// Blocks until complete.
			server = new ServerSocket(GuessListener.portNumber);
			sendSocket = server.accept();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			// Start with an image based on you and your partner.
			current = currentList.remove(Math.abs(partnerName.toLowerCase()
					.hashCode()
					+ InetAddress.getLocalHost().getHostName().toLowerCase()
							.hashCode())
					% currentList.size());
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		// Set up stuff for the Window.
		field = new JTextField(17);
		textArea = new JPanel();
		textArea.setSize(300, 50);
		messageArea = new JPanel();
		messageArea.setSize(300, 50);
		message = new JTextField(25);
		message.setEditable(false);
		messageArea.add(message);
		pic = new JPanel();
		pic.setLayout(new BorderLayout());
		textArea.add(field);
		field.setVisible(true);
		Button button = new Button("Enter");
		back = new Button("Back");
		back.addActionListener(this);
		back.setActionCommand("back");
		textArea.add(button);
		textArea.add(back);
		field.addKeyListener(this);
		button.addActionListener(this);
		button.setActionCommand("enter");
		textArea.setBackground(Color.CYAN);
		getContentPane().add(textArea, BorderLayout.NORTH);
		imgComp = new GameImageComponent(current.getImage());
		pic.add(imgComp, BorderLayout.CENTER);
		pic.setBackground(Color.CYAN);
		add(messageArea, BorderLayout.CENTER);
		add(pic, BorderLayout.SOUTH);
		// Initialize their guesses and yours to be empty.
		guesses = new ArrayList<String>();
		theirGuesses = new ArrayList<String>();
		// Read high scores.
		BufferedReader f = null;
		try {
			f = new BufferedReader(new FileReader("HighScoreDB.txt"));
		} catch (FileNotFoundException e) {
			// Auto-generated catch block
			e.printStackTrace();
		}
		String[] readHS = new String[5];
		for (int i = 0; i < 5; i++) {
			try {
				readHS[i] = f.readLine();
			} catch (IOException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
		}
		highScores = new HighScore(readHS);
	}

	/**
	 * When an action is performed, namely, enter is pressed, it sends the
	 * guess.
	 */
	public void actionPerformed(ActionEvent evt) {

		if (evt.getActionCommand().equals("enter")) {
			String guess = field.getText().toLowerCase();
			String temp = "";
			for (int i = 0; i < guess.length(); i++) {
				if (Character.isLetter(guess.charAt(i))) {
					temp += guess.charAt(i);
				}
			}
			guess = temp;
			if (guess.length() == 0) {
				// Can't send nothing.
				message.setText("Invalid Guess");
				message.setVisible(true);
				System.out.println("Invalid Guess");
			} else if (guesses.contains(guess)) {
				// Can't repeat guesses.
				message.setText("Already Guessed");
				message.setVisible(true);
				System.out.println("Already Guessed");
			} else {
				// Go ahead and send the guess.
				message.setVisible(false);
				guesses.add(guess);

				try {
					ObjectOutputStream out = new ObjectOutputStream(sendSocket
							.getOutputStream());
					out.writeObject(guess);
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (theirGuesses.contains(guess)) {
					win(guess);
				}

			}
			field.setText("");
		}

	}

	/**
	 * Adds a new image to replace current image.
	 * 
	 * @param n
	 *            The numerical index of the new image.
	 */
	public void newImage(int n) {

		justWon = false;
		current = currentList.remove(n);
		imgComp.replace(current.getImage());
		// If none available, start over from beginning.
		if (currentList.size() == 0) {
			for (LabelledImage img : imageList) {
				currentList.add(img);
			}
		}
	}

	/**
	 * Called by GuessListener. Receives a word String as a guess. Checks to see
	 * if they match.
	 * 
	 * @param s
	 *            The guess to receive.
	 */
	public void receiveGuess(String s) {
		theirGuesses.add(s);
		if (guesses.contains(s)) {
			win(s);
		}
	}

	/**
	 * 
	 * YAY, you matched!
	 * 
	 * @param s
	 *            The word you matched on.
	 */
	public void win(String s) {
		current.incrementLabel(s);
		justWon = true;
		System.out.println("Matched on: " + s);

		currentScore += points(s);
		System.out.println("Current score: " + currentScore);
		message.setText("Matched on: " + s + "   Current score: "
				+ currentScore);
		message.setVisible(true);
		// Bases the new index on the hashcode of the word you match on - will
		// be the same for both computers.
		int newIndex = (Math.abs(s.hashCode()) % currentList.size());
		// Replace image.
		newImage(newIndex);

		// Reset guesses.
		theirGuesses = new ArrayList<String>();
		guesses = new ArrayList<String>();

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * If enter is pressed, do the same thing as clicking enter.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			ActionEvent fakeEvent = new ActionEvent(this, 42, "enter");
			actionPerformed(fakeEvent);
		}

	}

	/**
	 * Method to return the guesslistener associated with this game window.
	 * 
	 * @return The GuessListener associated with this game window.
	 */
	public GuessListener getGuessListener() {
		return guessListener;
	}

	/**
	 * Calculates the number of points a guess is worth.
	 * 
	 * @param match
	 *            The word they matched on.
	 * @return The number of points the guess is worth.
	 */
	private static int points(String match) {
		int total = 50;
		int hits = 0;
		BufferedReader f = null;
		// Read through file.
		try {
			f = new BufferedReader(new FileReader("database.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		while (true)// Runs for each line in database
		{
			String s = "";
			try {
				s = f.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (s == null)
				break;
			String[] labels = s.split(" ");
			for (int i = 2; i < labels.length; i += 2) {
				if (labels[i].equals(match)) {
					hits++;
				}
			}
		}
		if (hits == 0)
			hits = 1;
		total += 80 / hits;
		if (match.length() >= 5)
			total += 10;
		if (match.length() >= 9)
			total += 10;
		return total;
	}

	/**
	 * When the game ends, write the guesses to the database.
	 * 
	 */
	public void write() {
		PrintWriter writer = null;
		// Try to write it to the file.
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(
					"database.txt")));
		} catch (IOException e) {
			System.out.println("exception");
		}
		for (LabelledImage img : imageList) {
			writer.println(img);
		}
		// Clean up the game - close all sockets and writers.
		writer.close();
		guessListener.close();
		try {
			server.close();
			sendSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Close this window.
		dispose();
	}

	/**
	 * At the end of the game, creates a HighScore window to display high
	 * scores. This also gives the chance to play again.
	 */
	public void displayHS() {
		Score yourScore = new Score();
		try {
			yourScore = new Score(InetAddress.getLocalHost().getHostName()
					+ " & " + partnerName, currentScore);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		// If you got a high score, add it!
		highScores.add(yourScore);
		HighScoreWindow window = new HighScoreWindow(highScores, yourScore);
		// Set up the window.
		window.setLocation(300, 100);
		window.setVisible(true);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setSize(800, 400);
		window.setResizable(false);
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedWriter(new FileWriter(
					"HighScoreDB.txt")));

		} catch (IOException e) {
			System.out.println("exception");
		}
		// Write the new highscores to the file.
		writer.println(highScores.toString());
		writer.close();
	}
}
