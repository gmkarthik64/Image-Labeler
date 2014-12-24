import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
import javax.swing.*;
import java.util.*;

/**
 * Only concerned with searchString at this point.
 * 
 * Format for database.txt: PicName #totalHits tag1 #hits1 tag2 #hits2...
 * 
 * 
 */
public class SearchWindow extends JFrame implements ActionListener, KeyListener {
	private static BufferedReader f;

	private JPanel textArea;

	private JPanel imageArea;

	private JTextField field;

	private Button searchButton;

	private int x, y;

	private String word;

	private ArrayList<String> results;

	private SearchWindowComponent pics;

	/**
	 * Default constructor.
	 */
	public SearchWindow() {
		this(3, 2);
	}

	/**
	 * Constructor for the Search Window.
	 * 
	 * @param x
	 *            xValue of the Search Window
	 * @param y
	 *            yValue of the Search Window
	 */
	public SearchWindow(int x, int y) {
		super("Image Searcher");
		word = "";
		try {
			results = searchString(word);
		} catch (IOException e) {
		}
		textArea = new JPanel();
		textArea.setSize(900, 50);
		textArea.setBackground(Color.CYAN);
		imageArea = new JPanel();
		imageArea.setLayout(new BorderLayout());
		pics = new SearchWindowComponent(x, y);
		imageArea.setBackground(Color.CYAN);
		imageArea.add(pics, BorderLayout.CENTER);
		field = new JTextField(20);
		searchButton = new Button("Search");
		textArea.add(field);
		textArea.add(searchButton);
		searchButton.addActionListener(this);
		searchButton.setActionCommand("search");
		field.addKeyListener(this);
		this.x = x;
		this.y = y;
		add(textArea);
		add(imageArea);
	}

	/**
	 * Searches for the given String.
	 * 
	 * @param keyword
	 *            The word to search for.
	 * @return Returns an ArrayList of String image Names that match.
	 * @throws IOException
	 *             Throws Exception.
	 */
	public ArrayList<String> searchString(String keyword) throws IOException {
		f = new BufferedReader(new FileReader("database.txt"));
		ArrayList<String> result = new ArrayList<String>();
		LinkedList<SearchRev> tagged = new LinkedList<SearchRev>();
		keyword = keyword.toLowerCase();
		while (true)// Runs for each line in database
		{
			String s = "";

			s = f.readLine();

			if (s == null)
				break;
			String[] labels = s.split(" ");
			for (int i = 2; i < labels.length; i += 2) {
				labels[i] = labels[i].replaceAll("[?]", " ");
				if (labels[i].equals(keyword)) {
					tagged.add(new SearchRev(labels[0], Integer
							.parseInt(labels[i + 1]), Integer
							.parseInt(labels[1])));
				}
			}
		}
		Collections.sort(tagged);
		for (int i = tagged.size() - 1; i >= 0; i--) {
			SearchRev sr = tagged.get(i);
			// Replace with this to check ratios:
			// String picName = sr.toString();
			String picName = sr.getName();
			picName = picName.replaceAll("[?]", " ");
			result.add(picName);
		}
		return result;
	}

	/**
	 * Main method to test the SearchWindow.
	 * 
	 * @param args
	 *            Default String array for running from command line.
	 * @throws IOException
	 *             Throws exception.
	 */
	public static void main(String args[]) throws IOException {
		SearchWindow window = new SearchWindow();
		window.setVisible(true);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setSize(900, 700);
		window.setResizable(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
		try {
			results = searchString(field.getText());
		} catch (IOException e) {
		}
	}

	/**
	 * When the button is pressed, do the search!
	 */
	public void actionPerformed(ActionEvent arg0) {

		word = field.getText();
		try {
			results = searchString(word);
			pics.replace(results);
			// pics.replace( results );
			field.repaint();
		} catch (IOException e) {
		}
		field.setText(word);

	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * If enter is pressed, do the same as if the button is pressed!
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			ActionEvent fakeEvent = new ActionEvent(this, 42, "search");
			actionPerformed(fakeEvent);
		}

	}
}