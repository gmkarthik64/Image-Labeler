/**
 * @author 5081465
 * 
 *         The Score class holds the scorer’s name and the score at the end of a
 *         game.
 */
public class Score implements Comparable {
	private String name;

	private int count;

	/**
	 * Default constructor. Creates a Score with name "" and point count 0.
	 */
	public Score() {
		name = "";
		count = 0;
	}

	/**
	 * Constructor, to create a Score with a name and point count.
	 * 
	 * @param user
	 *            The user who attained the score
	 * @param c
	 *            The points in this score
	 */
	public Score(String user, int c) {
		name = user;
		count = c;
	}

	/**
	 * Getter for the name.
	 * 
	 * @return the name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the count.
	 * 
	 * @return the count.
	 */
	public int getCount() {
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object other) {
		if (other instanceof Score) {
			return count - ((Score) other).getCount();
		} else
			throw new IllegalArgumentException("Not comparing to a score");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (other instanceof Score) {
			return this.toString().equals(other.toString())
					&& this.compareTo(other) == 0;
		} else
			throw new IllegalArgumentException("Not comparing to a Score");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name + " " + count;
	}
}