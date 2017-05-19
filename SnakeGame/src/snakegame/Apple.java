package snakegame;

/**
 * Apple -- Factory class used to generate apples in the game. Has a location,
 * and manages a Sprite that represents this particular Apple in the game.
 *
 * @author ascott
 */
public class Apple {

    private double x;
    private double y;
    private Sprite SpawnApple;
    private int GameGridWidth;

    public Apple() {
        x = 0.0;
        y = 0.0;
        SpawnApple = new Sprite();
        GameGridWidth = 8192;

    }

    /**
     * GenerateApples - Generates an apple sprite with position.
     *
     * @return apple sprite
     */
    public Sprite GenerateApples() {

        Sprite apple = new Sprite();
        apple.setImage("apple.png");
        double px = 350 * Math.random() + 50;
        double py = 350 * Math.random() + 50;
        apple.setPosition(px, py);
        apple.setVelocity(px + 40, py + 40);

        return apple;
    }

    /**
     * SpownAppleInSameQ - Spawn (instantiate) an apple in an area.
     *
     * @param Xquadrant x position of the eaten apple
     * @param Yquadrant y position of the eaten apple
     * @return apple sprite
     */
    public Sprite SpanwAppleInSameQ(double Xquadrant, double Yquadrant) {
        x = Xquadrant;
        y = Yquadrant;
        SpawnApple.setImage("apple.png");

        if (x < 0 && y > 0) {
            x = -GameGridWidth / 2 * Math.random() + 50;
            y = GameGridWidth / 2 * Math.random() + 50;
            SpawnApple.setPosition(x, y);
        } else if (x > 0 && y < 0) {
            x = GameGridWidth / 2 * Math.random() + 50;
            y = -GameGridWidth / 2 * Math.random() + 50;
            SpawnApple.setPosition(x, y);
        } else if (x < 0 && y < 0) {
            x = -GameGridWidth / 2 * Math.random() + 50;
            y = -GameGridWidth / 2 * Math.random() + 50;
            SpawnApple.setPosition(x, y);
        } else {
            x = GameGridWidth / 2 * Math.random() + 50;
            y = GameGridWidth / 2 * Math.random() + 50;
            SpawnApple.setPosition(x, y);
        }

        return SpawnApple;
    }

    /**
     * getXposition - Returns the X position of the Apple.
     *
     * @return spawned apple's x position
     */
    public double getXposition() {
        return this.SpawnApple.getPosX();
    }

    /**
     * getXposition - Returns the Y position of the Apple.
     *
     * @return spawned apple's y position
     */
    public double getYpostion() {
        return this.SpawnApple.getPosY();
    }

}
