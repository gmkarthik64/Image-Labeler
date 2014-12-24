/**
 * The Label class holds the Label’s name and the number of hits.
 * 
 * 
 */
public class Label {
	private String label;

	private int count;

	/**
	 * Creates a Label with name l and count 1.
	 * 
	 * @param l
	 *            the name of the Label
	 */
	public Label(String l) {
		label = l;
		count = 1;
	}

	/**
	 * Creates a Label with name l and count c.
	 * 
	 * @param l
	 *            the name of the Label
	 * @param c
	 *            the count of the hits
	 */
	public Label(String l, int c) {
		l = reformat(l);
		label = l;
		count = c;
	}

	/**
	 * Getter for the name.
	 * 
	 * @return the name of the label
	 */
	public String getName() {
		return label;
	}

	/**
	 * Increments the count by 1.
	 */
	public void increment() {
		count++;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		return " " + label + " " + count;
	}

	/**
	 * Changes an input string into a string properly formatted for databases.
	 * 
	 * @param initial
	 *            the initial string to reformat
	 * @return the reformatted string
	 */
	private String reformat(String initial) {
		StringBuffer result = new StringBuffer();
		initial = initial.toLowerCase();
		for (int i = 0; i < initial.length(); i++) {
			if (Character.isLetterOrDigit(initial.charAt(i))) {
				result.append(initial.charAt(i));
			} else if (initial.charAt(i) == ' ') {
				result.append('?');
			}
		}
		return result.toString();
	}
}
