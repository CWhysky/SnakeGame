package snakegame;

import java.util.LinkedList;
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
    
    /**
     * Gets the position of the sprite on the x plane
     * @return the position of the sprite on the x plane
     */    
    public double getPosX(){
        return positionX;
    }
    
    /**
     * Gets the position of the sprite on the y plane
     * @return the position of the sprite on the y plane
     */
    public double getPosY(){
        return positionY;
    }
    
    /**
     * sets the image i and sets the height and width of the sprite based on the height and width of the image
     * @param i the image
     */
    public void setImage(Image i) {
        image = i;
        width = i.getWidth();
        height = i.getHeight();
    }

    /**
     * Gets the image stored in the sprite
     * @return the image stored in the sprite
     */
    public Image getImage() {
        return image;
    }

    /**
     * Gets the angle the sprite is currently traveling in
     * @return the angle the sprite is currently traveling in
     */
    public double getAngle() {
        return angle;
    }

    /**
     * sets the angle of the sprite
     * @param newAngle the new angle for the sprite
     */
    public void setAngle(double newAngle) {
        angle = newAngle;
    }

    /**
     * Creates a new Image based on a file path then calls setImage
     * @param filename the file path
     */
    public void setImage(String filename) {
        Image i = new Image(filename);
        setImage(i);
    }
    
    /**
     * sets the position of x and y on the gameGrid
     * @param x the position on the x plane
     * @param y the position on the y plane
     */
    public void setPosition(double x, double y) {
	positionX = x;
	positionY = y;
    }

    /**
     * sets the velocity in the x and y direction on the gameGrid
     * @param x the velocity in the x direction
     * @param y the velocity in the y direction
     */
    public void setVelocity(double x, double y) {
	velocityX = x;
	velocityY = y;
    }

    /**
     * adds velocity to the current velocity
     * @param x addition velocity in the x direction
     * @param y addition velocity in the y direction
     */
    public void addVelocity(double x, double y) {
	velocityX += x;
        velocityY += y;
    }

    /**
     * changes the position of the sprite based on the velocity and time
     * @param time the time since the last update.
     */
    public void update(double time) {
	positionX += velocityX * time;
	positionY += velocityY * time;
    }

    /**
     * draws the image on the game map.
     * @param gc the graphical context
     */
    public void render(GraphicsContext gc) {
	drawRotatedImage(gc, image, angle, positionX, positionY);
    }

    /**
     * gets the edge of the sprite
     * @return a hit box of the sprite
     */
    public Rectangle2D getBoundary() {
	return new Rectangle2D(positionX, positionY, width, height);
    }

    /**
     * checks to see if the this sprite has hit another sprite
     * @param s the sprite that may or may not intersect this sprite
     * @return true if they intersect, false if it doesn't
     */
    public boolean intersects(Sprite s) {
	return s.getBoundary().intersects(this.getBoundary());
    }

    /**
     * Debug method
     * @return returns a string representation of the current position and velocity
     */
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
    
    /**
     * sets the position of this sprite behind the last snake body part in the snake.
     * @param snake the snake to add this sprite behind
     */
    public void setPosition(Snake snake){
        //The position of this segement has to be behind the current last segement
        //Meaning that if the segment is moving up left, then the segement should 
        //appear at the lower right.
        double rootTheta = (snake.getLast().getAngle()) * (Math.PI/180);
        double radius = snake.getLast().getWidth();
        
        double nX = Math.sin(rootTheta) * radius;
        nX = nX/2;
        
        double nY = Math.cos(rootTheta) * radius;
        nY = nY/2;
        
        positionX = (nX + snake.getLast().getPosX());
        positionY = (nY + snake.getLast().getPosY());
        
    }
    
    /**
     * returns the width of the sprite
     * @return the width of the sprite
     */
    public double getWidth(){
        return width;
    }
    
    /**
     * sets the velocity and angle of the sprite based on the velocity and angle of the last body piece in the snake
     * @param snake the snake to set the velocity off of
     */
    public void setVelocity(Snake snake){
        velocityX = snake.getLast().getVelocityX();
        velocityY = snake.getLast().getVelocityY();
        angle = snake.getLast().getAngle();
    }
    
    /**
     * Gets the velocity of the sprite in the x direction
     * @return the velocity of the sprite in the x direction
     */
    public double getVelocityX(){
        return velocityX;
    }
    
    /**
     * gets the velocity of the sprite in the y direction
     * @return the velocity of the sprite in the y direction
     */
    public double getVelocityY(){
        return velocityY;
    }
    
    
}
