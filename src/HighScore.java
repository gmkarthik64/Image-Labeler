import java.util.*;

/**
 * @author 5081465
 * 
 *         The HighScore Class stores the five highest Scores in a LinkedList.
 */
public class HighScore {
	// The linked list that we had to use!
	private List<Score> top5;

	/**
	 * Default constructor. Initializes the LinkedList and puts in 5 initial
	 * values.
	 */
	public HighScore() {
		//Yay, it's a linked list!
		top5 = new LinkedList<Score>();
		top5.add(new Score("Never", 69));
		top5.add(new Score("Gonna", 42));
		top5.add(new Score("Give", 36));
		top5.add(new Score("You", 21));
		top5.add(new Score("Up", 13));
		Collections.sort(top5);
		Collections.reverse(top5);
	}
	/**
	 * The constructor for a HighScore given some current scores.
	 * @param current The current scores.
	 */
	public HighScore(String[] current) {
		if (current.length != 5)
			throw new IllegalArgumentException("Wrong size");
		top5 = new LinkedList<Score>();
		for (int i = 0; i < 5; i++) {
			String[] scoreInfo = current[i].split(" ");
			String scoreNum = scoreInfo[scoreInfo.length - 1];
			Score parsed = new Score(current[i].substring(0, current[i]
					.length()
					- scoreNum.length() - 1), Integer.parseInt(scoreNum));
			// System.out.println(parsed);
			top5.add(parsed);
		}
		//Keep it sorted!
		Collections.sort(top5);
		Collections.reverse(top5);
	}

	/**
	 * Finds the position of the score in relation to the current top 5 and puts
	 * it there, then removes the latest. Will not add if it's a duplicate
	 * (.equals another score)
	 * 
	 * @param score
	 *            The score to potentially add.
	 */
	public void add(Score score) {
		/*
		 * int i = 5; while ( i > 0 && top5.get( i - 1 ).compareTo( score ) < 0
		 * ) { i--; }
		 */
		// top5.add( i, score );
		for (Score s : top5) {
			if (s.equals(score))
				return;
		}
		top5.add(score);
		Collections.sort(top5);
		Collections.reverse(top5);
		top5.remove(5);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = "";
		for (Score s : top5) {
			result = result + s.toString() + "\n";
		}
		return result;
	}
}