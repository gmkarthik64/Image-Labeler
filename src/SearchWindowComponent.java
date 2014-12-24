import java.awt.*; //import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

/**
 * The Component image for a Search Window.
 * 
 */
public class SearchWindowComponent extends Component {
	private ArrayList<String> result;
	int x, y;

	/**
	 * Constructor for the component.
	 * 
	 * @param x
	 *            The xValue
	 * @param y
	 *            The yValue
	 */
	public SearchWindowComponent(int x, int y) {
		result = new ArrayList<String>();
		this.x = x;
		this.y = y;
	}

	/**
	 * Paints this component.
	 * 
	 * @param g
	 *            The graphics to paint.
	 */
	public void paint(Graphics g) {
		ArrayList<String> results = result;
		for (int j = 0; j < y; j++) {
			for (int i = 0; i < x; i++) {
				if (results.size() != 0) {
					BufferedImage image = new LabelledImage(results.remove(0))
							.getImage();
					g.drawImage(image, 300 * i + 150 - image.getWidth() / 2,
							350 * j + 175 - image.getHeight() / 2, null);
				}
			}
		}
		results = result;
	}

	/**
	 * When a new search is done, this method is called to put the new returned
	 * images in the component.
	 * 
	 * @param newList
	 *            The newList of images to put in this component.
	 */
	public void replace(ArrayList<String> newList) {
		result = newList;
		repaint();
	}
}
