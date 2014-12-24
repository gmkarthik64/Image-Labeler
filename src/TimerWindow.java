import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * The class to run the Timer. Creates a window and displays the time as well.
 * 
 */
public class TimerWindow extends JFrame implements ActionListener {
	private JTextField timerText;

	private int timeLeft;

	private Timer timer;

	private GameWindow window;
	// The amount of time per round. Currently set to 75 seconds.
	private final int MAX_TIME = 75;

	/**
	 * Constructor to create the Timer and start it.
	 * 
	 * @param window
	 *            The GameWindow itis associated with.
	 */
	public TimerWindow(GameWindow window) {
		super("Timer");
		this.window = window;
		timeLeft = MAX_TIME;
		timerText = new JTextField(20);
		timerText.setFont(new Font("Verdana", Font.PLAIN, 30));
		timerText.setForeground(Color.BLACK);
		timerText.setEditable(false);
		// Start the timer going!
		add(timerText);
		timer = new Timer(1000, this);
		timer.start();
		timer.setActionCommand("time");
	}

	/**
	 * Should be called every second for the clock. Decreases time left. If no
	 * time remains, ends the game.
	 */
	public void actionPerformed(ActionEvent arg0) {
		timeLeft--;
		timerText.setText(timeLeft + " seconds");
		timerText.repaint();
		if (timeLeft == 0) {
			window.displayHS();
			window.write();
			dispose();
		}
	}

}
