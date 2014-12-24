/**
 * A class to store the relevance of a result. Helper class for SearchWindow
 * 
 * 
 */
public class SearchRev implements Comparable {
	private double ratio;

	private String name;

	/**
	 * Creates a result for relevance.
	 * 
	 * @param n
	 *            the search term
	 * @param hits
	 *            the number of tags for this term
	 * @param total
	 *            the total number of tags
	 */
	public SearchRev(String n, int hits, int total) {
		name = n;
		ratio = ((double) hits) / (total);
	}

	/**
	 * Getter for the name.
	 * 
	 * @return the name of the search term
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter for the ratio.
	 * 
	 * @return the ratio of hits to total tags
	 */
	public double getRatio() {
		return ratio;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object other) {
		if (other instanceof SearchRev) {
			return (int) (1000 * (ratio - ((SearchRev) other).getRatio()));
		} else
			throw new IllegalArgumentException("Not comparing to a SearchRev");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return name + " " + ratio;
	}

}