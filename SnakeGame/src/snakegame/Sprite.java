package snakegame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class Sprite {

	private Image image;
	private ImageView theImageView;
	private double positionX;
	private double positionY;
	private double bgPositionX;
	private double bgPositionY;
	private double velocityX;
	private double velocityY;
	private double bgVelocityX;
	private double bgVelocityY;
	private double width;
	private double height;
	private double angle;
	private final ImageView head;

	public Sprite() {
		positionX = 0;
		positionY = 0;
		velocityX = 0;
		velocityY = 0;
		angle = 0;
		head = new ImageView(new Image("snake_head_red.png"));
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

	public void setAngle(double newAngle) {
		angle = newAngle;

		// Rotate the head to the new angle
		head.setRotate(newAngle);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		Image rotatedImage = head.snapshot(params, null);
		setImage(rotatedImage);
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
		gc.drawImage(image, positionX, positionY);
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
}
