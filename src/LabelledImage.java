import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.*;

/**
 * 
 * The LabelledImage Class stores a reference to an Image as well as its labels.
 */
public class LabelledImage {
	private BufferedImage image;

	private ArrayList<Label> labels;

	private int count;

	private String name;

	/**
	 * Constructor for this class. Takes in the name of an image file to assign
	 * to image.
	 * 
	 * @param name
	 *            the name of the image file.
	 */
	public LabelledImage(String name) {
		labels = new ArrayList<Label>();
		this.name = name;
		count = 0;
		try {
			image = ImageIO.read(new File("ImageDB\\" + name));
		} catch (IOException e) {
		}
		resize();
	}

	public void add(Label label) {
		labels.add(label);
	}

	/**
	 * Adds the label or increments it if the label is already in the ArrayList.
	 * 
	 * @param label
	 *            The label to add or increment.
	 */
	public void incrementLabel(String label) {
		boolean changed = false;
		for (Label l : labels) {
			if (l.getName().equals(label)) {
				l.increment();
				changed = true;
				break;
			}
		}
		if (!changed) {
			labels.add(new Label(label));
		}
		count++;
	}

	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Getter for the image.
	 * 
	 * @return the image.
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Resizes the Image to fit.
	 */
	public void resize() {
		int width = image.getWidth();
		int height = image.getHeight();
		if (width > height) {
			height = height * 200 / width;
			width = 200;
		} else {
			width = width * 200 / height;
			height = 200;
		}
		BufferedImage scaled = new BufferedImage(width, height, image.getType());
		Graphics2D g = scaled.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(image, 0, 0, width, height, 0, 0, image.getWidth(), image
				.getHeight(), null);
		g.dispose();
		image = scaled;
	}

	/**
	 * Converts this LabelledImage to a String.
	 */
	public String toString() {
		String s = name + " " + count;
		for (Label label : labels) {
			s += label;
		}
		return s;
	}
}
