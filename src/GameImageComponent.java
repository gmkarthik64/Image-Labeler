import java.awt.*; //import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

/**
 * The component for a game image.
 */
public class GameImageComponent extends Component {
	BufferedImage img;

	/**
	 * The constructor given a specific image.
	 * 
	 * @param img
	 *            The image for the component.
	 */
	public GameImageComponent(BufferedImage img) {
		this.img = img;
	}

	/**
	 * Returns the expected size of the image.
	 */
	public Dimension getPreferredSize() {
		return new Dimension(300, 350);
	}

	/**
	 * Paints the image.
	 */
	public void paint(Graphics g) {
		g.drawImage(img, 150 - img.getWidth() / 2, 175 - img.getHeight() / 2,
				null);
	}

	/**
	 * Replaces the image with a new image.
	 * 
	 * @param image
	 *            The new image.
	 */
	public void replace(BufferedImage image) {
		img = image;
		repaint();
	}

}
