package snakegame;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

public class Snakey extends Application {

    int nextGrow = 1;
    int growCounterPlayer = 0;
    int growCounterAI = 0;
    
    @Override
    public void start(Stage primaryStage) {

        //Handles the speed and position of the window in relation to the game grid
        Background bg = new Background();
        //the root of the Window
        Group root = new Group();
        //The speed of the player Character Snake
        int Speed = 500;
        
        int WindowWidth = 640;
        int WindowHeight = 640;
        int GameGridWidth = 4096;
        int GameGridHeight = 4096;

        //The window view
        Scene theScene = new Scene(root, WindowWidth, WindowHeight);
        primaryStage.setTitle("Snakey!");
        primaryStage.setScene(theScene);
        //Drawable area inside of the scene
        Canvas canvas = new Canvas(WindowWidth, WindowHeight);

        //In x, y information from the mouse input is going to be stored here.
        ArrayList<Double> mouseInput = new ArrayList();

        //The image for the borders of the game
        Image cracked = new Image("stars5.jpg");
        //sets the thickness of the boarders
        theScene.setFill(new ImagePattern(cracked,
                ((theScene.getWidth() / 2) - 32),
                ((theScene.getHeight() / 2) - 32), .6, .6, true));
        root.getChildren().add(canvas);

        //it takes the information from the mouse and stores it into the ArrayList mouseInput
        //with the information from x first.
        theScene.setOnMouseMoved(
                new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                Double x = e.getX();
                Double y = e.getY();
                mouseInput.add(0, x);
                mouseInput.add(1, y);
            }
        });

        GraphicsContext gc = canvas.getGraphicsContext2D();

        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        // Primary Snake
        Snake theSnake = new Snake();
        Sprite snakeHead = new Sprite();
        snakeHead.setImage("snake_head_red.png");
        snakeHead.setPosition((theScene.getWidth() / 2) - 32, (theScene.getHeight() / 2) - 32);
        theSnake.setHead(snakeHead);

        // AI Snake
        //TODO: Make this a snake object and make the appropriate modifications to the code
        Snake theSnake2 = new Snake();
        snakeHead = new Sprite();
        snakeHead.setImage("snake_head_red.png");
        snakeHead.setPosition(200, 200);
        theSnake2.setHead(snakeHead);
        SnakeAI SAI = new SnakeAI(theSnake2, true);
        SAI.setHead(snakeHead);

        //Set the initial velocity of the background.
        bg.setBGVelX(0);
        bg.setBGVelY(Speed);
        
        //Places the apples at the start of the game on the gameGrid
        //TODO: Make the apples respawn after they are eaten
        ArrayList<Sprite> appleList = new ArrayList<Sprite>();
        for (int i = 0; i < 70; i++) {
            Sprite apple = new Sprite();
            apple.setImage("apple.png");
            double px = -1000 * Math.random() + 50;
            double py = 1000 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = 1000 * Math.random() + 50;
            py = -1000 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = -1000 * Math.random() + 50;
            py = -1000 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = 1000 * Math.random() + 50;
            py = 1000 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);
        }

        // wall sprite
        ArrayList<Sprite> wallList = new ArrayList<Sprite>();
        for (int i = 0; i <= GameGridWidth + 400; i += 144) {
            // places the wall at the top
            Sprite wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(i - (GameGridWidth / 2), ((WindowHeight / 2) - (GameGridWidth / 2)));
            wallList.add(wall);
            // places the wall at the bottom
            wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(i - (GameGridWidth / 2), ((WindowHeight / 2) + (GameGridHeight / 2)));
            wallList.add(wall);
            // places the wall to the left
            wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(((WindowHeight / 2) - (GameGridWidth / 2)), i - (GameGridWidth / 2));
            wallList.add(wall);
            // places the wall to the right
            wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(((WindowWidth / 2) + (GameGridWidth / 2)), i - (GameGridWidth / 2));
            wallList.add(wall);

        }

        //The time of the last update
        LongValue lastNanoTime = new LongValue(System.nanoTime());
        //The score of the player
        IntValue score = new IntValue(0);
        //The score of the AI
        IntValue aiScore = new IntValue(0);
        
        new AnimationTimer() {
            @Override
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                double newAngle;
                // game logic
                if (!mouseInput.isEmpty()) {
                    double x;
                    double y;
                    double totX = 0.0;
                    double totY = 0.0;
                    // buffer up to 20 samples from the mouse
                    // so we can normalize input by averaging
                    // otherwise there is lots of jitter
                    if (mouseInput.size() > 20) {
                        for (int i = 0; i < 19; i += 2) {
                            totX += mouseInput.get(i);
                            totY += mouseInput.get(i + 1);
                        }
                        x = totX / 10.0;
                        y = totY / 10.0;

                        // Calculate angle and update
                        x = (x - theScene.getWidth() / 2.0);
                        y = (y - theScene.getHeight() / 2.0);

                        newAngle = (Math.toDegrees(Math.atan2(y, x)) + 90.0);
                        theSnake.getHead().setAngle(newAngle);
                        if (newAngle % 90 != 0.0) {
                            // adjust velocities based on angle
                            double acute = ((theSnake.getHead().getAngle() + 90.0) % 90);
                            
                            double xSpeed = Speed * acute / 90.0;
                            double ySpeed = Speed - xSpeed;

                            if (newAngle > -90.0 && newAngle < 0.0) // upper left quarter
                            {
                                bg.setBGVelX(ySpeed);
                                bg.setBGVelY(xSpeed);
                            } else if (newAngle > 0.0 && newAngle <= 90.0) // upp right corner
                            {
                                bg.setBGVelX(-xSpeed);
                                bg.setBGVelY(ySpeed);
                            } else if (newAngle > 90.0 && newAngle <= 180.0) // lower right corner
                            {
                                bg.setBGVelX(-ySpeed);
                                bg.setBGVelY(-xSpeed);
                            } else if (newAngle > 180.0 && newAngle <= 270.0) // lower left corner
                            {
                                bg.setBGVelX(xSpeed);
                                bg.setBGVelY(-ySpeed);
                            }
                            //Set the velocity and angle of the head
                            //This doesn't ever get updated but is need to calculate the changes in the body pieces
                            theSnake.getHead().setVelocity(-bg.getBGVelX(), -bg.getBGVelY());
                            theSnake.getHead().setAngle(newAngle);
                        }
                    }
                }

                // collision detection
                // TODO: Make collision detection for snake against other snakes, and the death logic
                // and what happens when a player/AI snake dies.
                Iterator<Sprite> appleIter = appleList.iterator();
                while (appleIter.hasNext()) {
                    Sprite apple = appleIter.next();
                    if (theSnake.getHead().intersects(apple)) {
                        appleIter.remove();
                        score.value++;
                        growCounterPlayer++;
                        continue;
                    }
                    
                    if(theSnake2.getHead().intersects(apple)){
                        appleIter.remove();
                        growCounterAI++;
                        aiScore.value++;
                        SAI.mem = false;
                        continue;
                    }
                    
                    //If the snake should grow a new piece, grow a new piece.
                    if (growCounterPlayer >= nextGrow) {
                        Sprite bodySnake = new Sprite();
                        bodySnake.setImage("snake_body_red.png");
                        bodySnake.setPosition(theSnake);
                        bodySnake.setVelocity(theSnake);
                        theSnake.addBody(bodySnake);
                        growCounterPlayer = 0;
                    }
                    
                    if(growCounterAI >= nextGrow){
                        Sprite bodySnake = new Sprite();
                        bodySnake.setImage("snake_body_red.png");
                        bodySnake.setPosition(theSnake);
                        bodySnake.setVelocity(theSnake);
                        theSnake2.addBody(bodySnake);
                        growCounterAI = 0;
                    }

                    //The AI snake picks the next closest apple to it if it eats an apple
                    if (SAI.mem == false) {
                        Sprite nextApple = apple;
                        if (SAI.picksClosest) {
                            nextApple = SAI.shortestApple(appleList);
                        }
                        SAI.memAngle = SAI.calAngle(nextApple);
                        SAI.mem = true;
                    }
                }

                //Checks to see if a snake has hit a wall.
                Iterator<Sprite> wallIter = wallList.iterator();
                while (wallIter.hasNext()) {
                    Sprite wall = wallIter.next();
                    if (theSnake.getHead().intersects(wall)) {
                        //TODO: return to menu on death.
                        System.out.println("dead");
                        score.value = 0;
                        bg.setBGVelX(0);
                        bg.setBGVelY(0);
                        //reset back to 0
                    }
                }

                // update the Snake2's position relative to the change
                // background velocity
                theSnake2.getHead().setAngle(SAI.memAngle);
                theSnake2.getHead().setVelocity(Math.cos(Math.toRadians((SAI.memAngle - 90.0))) * Speed / 2, Math.sin(Math.toRadians((SAI.memAngle - 90.0))) * Speed / 2);
                theSnake2.getHead().setPosition(theSnake2.getHead().getPosX() + bg.getBGVelX() * elapsedTime, theSnake2.getHead().getPosY() + bg.getBGVelY() * elapsedTime);
                theSnake2.getHead().update(elapsedTime);

                // render whole board
                gc.clearRect(0, 0, WindowWidth, WindowHeight);

                // update background velocity
                bg.setBGX(bg.getBGX() + (bg.getBGVelX() * elapsedTime));
                bg.setBGY(bg.getBGY() + (bg.getBGVelY() * elapsedTime));

                // draw the walls
                theScene.setFill(new ImagePattern(cracked, bg.getBGX(), bg.getBGY(), cracked.getWidth(), cracked.getHeight(), false));

                //Takes each body piece of the snake, sets it's position to the position in front of it
                //then adjusts for the background velocity
                //Then renders the snake
                for (int i = theSnake.getSize() - 1; i > 0; i--) {
                    theSnake.getSegment(i).setPosition(
                        theSnake.getSegment(i - 1).getPosX() + (bg.bgVelX * elapsedTime),
                        theSnake.getSegment(i - 1).getPosY() + (bg.bgVelY * elapsedTime));
                    theSnake.getSegment(i).setAngle(theSnake.getSegment(i - 1).getAngle());
                    theSnake.getSegment(i).render(gc);
                }
                
                //Renders the head of the snake
                theSnake.getHead().render(gc);
                //Renders the AI snake
                for (int i = theSnake2.getSize() - 1; i > 0; i--) {
                    theSnake2.getSegment(i).setPosition(
                        theSnake2.getSegment(i - 1).getPosX() + (bg.bgVelX * elapsedTime),
                        theSnake2.getSegment(i - 1).getPosY() + (bg.bgVelY * elapsedTime));
                    theSnake2.getSegment(i).setAngle(theSnake2.getSegment(i - 1).getAngle());
                    theSnake2.getSegment(i).render(gc);
                }
                theSnake2.getHead().render(gc);

                //places the apples on the gameGrid and adjusts for backgroud speed
                for (Sprite apple : appleList) {
                    apple.setPosition((apple.getPosX() + bg.getBGVelX() * elapsedTime),
                            (apple.getPosY() + bg.getBGVelY() * elapsedTime));
                    apple.render(gc);
                }

                //places the walls on the gameGrid and adjusts for background speed
                for (Sprite wall : wallList) {
                    wall.setPosition((wall.getPosX() + bg.getBGVelX() * elapsedTime), (wall.getPosY() + bg.getBGVelY() * elapsedTime));
                    wall.render(gc);
                }

                String pointsText = "Score: " + (100 * score.value) + " , angle: " + (SAI.memAngle - 90.0);
                gc.fillText(pointsText, 360, 24);
                gc.strokeText(pointsText, 360, 24);
            }

        }.start();

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
/**
 * Turns a long into a class with a public data field
 * @author kamuela94
 */
class LongValue {

    public long value;

    public LongValue(long i) {
        value = i;
    }
}

/**
 * Turns an int into a class with a public data field
 * @author kamuela94
 */
class IntValue {

    public int value;

    public IntValue(int i) {
        value = i;
    }
}

/**
 * Holds the information for the background velocities and positions
 * @author kamuela94
 */
class Background {

    // Background Position, Accessors, and Setters
    public double bgX = 0.0;
    public double bgY = 0.0;
    // Background Velocites, Accessors and Setters
    double bgVelX = 0.0;
    double bgVelY = 0.0;
    
    /**
    * 
    * @return The x value of the background velocity
    */
    double getBGVelX() {
        return bgVelX;
    }

    /**
     * 
     * @return The y value of the background velocity
     */
    double getBGVelY() {
        return bgVelY;
    }

    /**
     * Sets the x value of the background velocity
     * @param num the double to be set to background velocity X
     */
    void setBGVelX(double num) {
        bgVelX = num;
    }

    /**
     * Sets the y value of the background velocity
     * @param num the double to be set to background velocity Y
     */
    void setBGVelY(double num) {
        bgVelY = num;
    }

    /**
     * 
     * @return the position of the background on the x plane
     */
    double getBGX() {
        return bgX;
    }

    /**
     * 
     * @return the position of the background on the y plane
     */
    double getBGY() {
        return bgY;
    }
    
    /**
     * sets the position of the background to value num on the x plane
     * @param num 
     */
    void setBGX(double num) {
        bgX = num;
    }

    /**
     * sets the position of the background to value num on the y plane
     * @param num 
     */
    void setBGY(double num) {
        bgY = num;
    }
}
