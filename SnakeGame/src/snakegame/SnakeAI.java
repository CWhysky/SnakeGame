package snakegame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * SnakeAI -- Class that extends the Snake class for managing computer player.
 * This class contains the properties of AI snake players.
 *
 * @author Darren ZL Zhen, Tim Beiko
 */
public class SnakeAI extends Snake {

    Sprite snakeSprite;
    public double memAngle = 0.0;
    public boolean mem = false;

    /**
     * Constructor for the class, runs the super's constructor and initializes
     * the snakeSprite which is the head of the AI Snake.
     *
     * @param snakeSprite
     */
    public SnakeAI(Sprite snakeSprite) {
        super();
        this.snakeSprite = snakeSprite;
    }

    /**
     * closestApple -- Calculates and returns the shortest distance among the
     * distances between the Snake and all the apples in appleList.
     *
     * @param appleList list of apples
     * @return the closest apple of the snake
     */
    public Sprite closestApple(ArrayList<Sprite> appleList) {
        double minDist = 999999999;
        Sprite closestApple = null;

        Iterator<Sprite> appleIter = appleList.iterator();
        while (appleIter.hasNext()) {
            Sprite apple = appleIter.next();
            double ax = apple.getPosX();
            double ay = apple.getPosY();

            double sx = this.snakeSprite.getPosX();
            double sy = this.snakeSprite.getPosY();

            double dist = java.lang.Math.sqrt(java.lang.Math.pow((ax * ax - sx * sx), 2) + java.lang.Math.pow((ay * ay - sy * sy), 2));

            if (dist < minDist) {
                minDist = dist;
                closestApple = apple;
            }
        }

        return closestApple;
    }

    /**
     * calAngle -- Calculates and returns the angle between the Snake and Apple.
     *
     * @param apple the target apple
     * @return the angle from the snake to the target apple
     */
    public double calAngle(Sprite apple) {
        double x = apple.getPosX() - snakeSprite.getPosX();
        double y = apple.getPosY() - snakeSprite.getPosY();
        double newAngle = (Math.toDegrees(Math.atan2(y, x)) + 90.0);
        return newAngle;
    }
}
