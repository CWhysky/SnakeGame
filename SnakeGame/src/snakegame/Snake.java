package snakegame;

import java.util.LinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.canvas.GraphicsContext;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.paint.Color;

/**
 * Snake - Class that defines the properties of Snake character in the game.
 *
 * @author kamuela94
 */
public class Snake {

    private LinkedList<Sprite> snake;
    private int growCount;
    private String bodyColor;
    private int score;

    public Snake() {
        snake = new LinkedList();
        growCount = 0;
        score = 0;
    }

    /**
     * setHead -- Sets the head of the snake to a given sprite.
     *
     * @param s where the sprite is the new head of the snake
     */
    public void setHead(Sprite s) {
        snake.addFirst(s);
    }

    /**
     * getHead -- Returns the current head of the snake.
     *
     * @return the head of the snake
     */
    public Sprite getHead() {
        return snake.getFirst();
    }

    /**
     * adds the sprite to the body at the end of the snake
     *
     * @param s the sprite to be added
     */
    public void addBody(Sprite s) {
        snake.add(s);
    }

    /**
     *
     * @return body of snake
     */
    public LinkedList<Sprite> getBody() {
        return snake;
    }

    /**
     * set the body color of the snake from url string
     *
     * @param bc url string of bodycolor image
     */
    public void setBodyColor(String bc) {
        bodyColor = bc;
    }

    /**
     *
     * @return the bodycolor url string
     */
    public String getBodyColor() {
        return bodyColor;
    }

    /**
     * remove/drop tail when snake have a collision
     */
    public void dropTail() {
        Sprite h = snake.getFirst();
        snake = new LinkedList();
        snake.addFirst(h);
    }

    /**
     * returns the size of the snake
     *
     * @return the size of the snake
     */
    public int getSize() {
        return snake.size();
    }

    /**
     * get the grow number of the snake which the snake should grow
     *
     * @return grow number count
     */
    public int getGrowCount() {
        return growCount;
    }

    /**
     * set the grow number of the sanke which the snake should grow
     *
     * @param gc int of grow number count
     */
    public void setGrowCount(int gc) {
        growCount = gc;
    }

    /**
     * gets the segment in the snake at position i
     *
     * @param i the index of the segment to return
     * @return the segment at position i
     */
    public Sprite getSegment(int i) {
        return snake.get(i);
    }

    /**
     * gets the last segment in the snake
     *
     * @return the last segment of the snake
     */
    public Sprite getLast() {
        return snake.getLast();
    }

    /**
     * set the score of the snake
     *
     * @param s the score
     */
    public void setScore(int s) {
        score = s;
    }

    /**
     * get the score of the snake
     *
     * @return the score of the snake
     */
    public int getScore() {
        return score;
    }

}
