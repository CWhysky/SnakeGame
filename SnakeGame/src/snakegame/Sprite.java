package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;


/**
 *
 * Sprite.java is a utility class to help manage the creation of elements in
 *   the Snake game. Apple.java, Snake.java, and Wall.java should all extend
 *   this class and leverage the functions in it. You may modify this class but
 *   please do not make any object-specific modifications; whatever you change
 *   in this class needs to work for all objects that use this class. 
 * 
 * @author ascott
 * 
 */
public class Sprite {

	private Image image;
	private double positionX;
	private double positionY;
	private double velocityX;
	private double velocityY;
	private double width;
	private double height;
	private double angle;

	public Sprite() {
		positionX = 0.0;
		positionY = 0.0;
		velocityX = 0.0;
		velocityY = 0.0;
		angle = 0.0;
	}

	public void setImage(Image i) {
		image = i;
		width = i.getWidth();
		height = i.getHeight();
	}

	public Image getImage() {
		return image;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double newAngle, GraphicsContext gc) {
		angle = newAngle;
	}

	public void setImage(String filename) {
		Image i = new Image(filename);

		setImage(i);
	}

	public void setPosition(double x, double y) {
		positionX = x;
		positionY = y;
	}

	public void setVelocity(double x, double y) {
		velocityX = x;
		velocityY = y;
	}

	public void addVelocity(double x, double y) {
		velocityX += x;
		velocityY += y;
	}

	public void update(double time, Scene scene) {
		positionX += velocityX * time;
		positionY += velocityY * time;
	}

	public void render(GraphicsContext gc) {
		// gc.drawImage(image, positionX, positionY);
		drawRotatedImage(gc, image, angle, positionX, positionY);
	}

	public Rectangle2D getBoundary() {
		return new Rectangle2D(positionX, positionY, width, height);
	}

	public boolean intersects(Sprite s) {
		return s.getBoundary().intersects(this.getBoundary());
	}

	public String toString() {
		return " Position: [" + positionX + "," + positionY + "]"
			+ " Velocity: [" + velocityX + "," + velocityY + "]";
	}

	// Following two functions borrowed from:
	// http://stackoverflow.com/questions/18260421/how-to-draw-image-rotated-on-javafx-canvas
	/**
	 * Sets the transform for the GraphicsContext to rotate around a pivot
	 * point.
	 *
	 * @param gc the graphics context the transform to applied to.
	 * @param angle the angle of rotation.
	 * @param px the x pivot co-ordinate for the rotation (in canvas
	 * co-ordinates).
	 * @param py the y pivot co-ordinate for the rotation (in canvas
	 * co-ordinates).
	 */
	private void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	/**
	 * Draws an image on a graphics context.
	 *
	 * The image is drawn at (tlpx, tlpy) rotated by angle pivoted around the
	 * point: (tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2)
	 *
	 * @param gc the graphics context the image is to be drawn on.
	 * @param angle the angle of rotation.
	 * @param tlpx the top left x co-ordinate where the image will be plotted
	 * (in canvas co-ordinates).
	 * @param tlpy the top left y co-ordinate where the image will be plotted
	 * (in canvas co-ordinates).
	 */
	private void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
		gc.save(); // saves the current state on stack, including the current transform
		rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
		gc.drawImage(image, tlpx, tlpy);
		gc.restore(); // back to original state (before rotation)
	}

}
