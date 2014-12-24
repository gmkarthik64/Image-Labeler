import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;

/**
 * The class for a HighScoreWindow that displays the HighScores.
 * 
 */
public class HighScoreWindow extends JFrame implements ActionListener {
	private JTextArea HSText;

	/**
	 * The default constructor to make a new HighScoreWindow right after a
	 * round.
	 * 
	 * @param highScores
	 *            The HighScore object that keeps track of scores.
	 * @param yourScore
	 *            The score obtained on this round.
	 */
	public HighScoreWindow(HighScore highScores, Score yourScore) {
		super("High Scores");
		setLayout(new BorderLayout());
		HSText = new JTextArea();
		HSText.setFont(new Font("Verdana", Font.PLAIN, 30));
		HSText.setForeground(Color.BLACK);
		HSText.setEditable(false);
		// Display new score.
		String toShow = "Your score for this round is " + yourScore.getCount();
		toShow += "\n\nCurrent High Scores:\n"
				+ highScores.toString().substring(0,
						highScores.toString().length() - 1);
		HSText.setText(toShow);
		add(HSText, BorderLayout.NORTH);
		// Play again button
		Button button = new Button("Home");
		button.setFont(new Font("Times New Roman", Font.BOLD, 36));
		button.setSize(200, 200);
		button.addActionListener(this);
		add(button, BorderLayout.CENTER);
	}

	@Override
	/**
	 * If an action is performed (namely, they click home) restart the game!
	 * LET'S DO IT AGAIN!
	 */
	public void actionPerformed(ActionEvent arg0) {
		ImageLabeller window = new ImageLabeller();
		window.setLocation(100, 100);
		window.setVisible(true);
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);
		window.setSize(200, 100);
		window.setResizable(false);
		dispose();
	}
}