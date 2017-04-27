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

    //growCounter to see when to add a body piece
    private int growCounter;
    private int NEXT_GROW = 1;

    // Background Velocity, Accessors, and Settings
    double bgVelX = 0.0;
    double bgVelY = 0.0;

    double getBGVelX() {
        return bgVelX;
    }

    double getBGVelY() {
        return bgVelY;
    }

    void setBGVelX(double num) {
        bgVelX = num;
    }

    void setBGVelY(double num) {
        bgVelY = num;
    }

    // Background Position, Accessors, and Setters
    double bgX = 0.0;
    double bgY = 0.0;

    double getBGX() {
        return bgX;
    }

    double getBGY() {
        return bgY;
    }

    void setBGX(double num) {
        bgX = num;
    }

    void setBGY(double num) {
        bgY = num;
    }

    @Override
    public void start(Stage primaryStage) {
        
        int Speed = 500;
        int WindowWidth = 640;
        int WindowHeight = 640;
        int GameGridWidth = 4096;
        int GameGridHeight = 4096;
        Snakey background = this;
        // StackPane root = new StackPane();
        Group root = new Group();
        Scene theScene = new Scene(root, WindowWidth, WindowHeight);
        Snake theSnake = new Snake();
        Sprite headSnake = new Sprite();
        headSnake.setImage("snake_head_red.png");
        headSnake.setPosition((theScene.getWidth() / 2) - 32, (theScene.getHeight() / 2) - 32);
        headSnake.setVelocity(0, -Speed);
        theSnake.setHead(headSnake);
        growCounter = 0;

        
        
        primaryStage.setTitle("Snakey!");
        primaryStage.setScene(theScene);
        Canvas canvas = new Canvas(WindowWidth, WindowHeight);

        ArrayList<String> input = new ArrayList<String>();
        ArrayList<Double> mouseInput = new ArrayList<Double>();

        Image cracked = new Image("stars5.jpg");
        theScene.setFill(new ImagePattern(cracked,
                ((theScene.getWidth() / 2) - 32),
                ((theScene.getHeight() / 2) - 32), .6, .6, true));
        root.getChildren().add(canvas);

        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!input.contains(code)) {
                    input.add(code);
                }
            }
        });

        theScene.setOnKeyReleased(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                input.remove(code);
            }
        });

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

        theSnake.getHead().setAngle(0, gc);
        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        // theSnake.setAngle(180, gc);

        Sprite theSnake2 = new Sprite();
        theSnake2.setImage("snake_head_red.png");
        theSnake2.setPosition(200, 200);
        SnakeAI SAI = new SnakeAI(theSnake2, true);

        setBGVelX(0);
        setBGVelY(Speed);
        ArrayList<Sprite> appleList = new ArrayList<Sprite>();

        for (int i = 0; i < 70; i++) {
            Sprite apple = new Sprite();
            apple.setImage("apple.png");
            double px = -1500 * Math.random() + 50;
            double py = 1500 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = 1500 * Math.random() + 50;
            py = -1500 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = -1500 * Math.random() + 50;
            py = -1500 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = 1500 * Math.random() + 50;
            py = 1500 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);
        }

        // wall sprite
        ArrayList<Sprite> wallList = new ArrayList<Sprite>();

        for (int i = 0; i <= GameGridWidth + 400; i += 144) {
            // top
            Sprite wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(i - (GameGridWidth / 2), ((WindowHeight / 2) - (GameGridWidth / 2)));
            wallList.add(wall);
            // bottom
            wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(i - (GameGridWidth / 2), ((WindowHeight / 2) + (GameGridHeight / 2)));
            wallList.add(wall);
            // left
            wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(((WindowHeight / 2) - (GameGridWidth / 2)), i - (GameGridWidth / 2));
            wallList.add(wall);
            // right
            wall = new Sprite();
            wall.setImage("cracked.png");
            wall.setPosition(((WindowWidth / 2) + (GameGridWidth / 2)), i - (GameGridWidth / 2));
            wallList.add(wall);

        }

        LongValue lastNanoTime = new LongValue(System.nanoTime());
        IntValue score = new IntValue(0);

        new AnimationTimer() {

            @Override
            public void handle(long currentNanoTime) {

                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                double newAngle = 0.0;

                // game logic
                if (!mouseInput.isEmpty()) {
                    double x = mouseInput.get(0);
                    double y = mouseInput.get(1);
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
                        // newAngle = (Math.toDegrees(Math.atan2(y, x)) + 90);
                        // newAngle = (Math.toDegrees(Math.atan2(y, x)) + 90);

                        newAngle = (Math.toDegrees(Math.atan2(y, x)) + 90.0);
                        theSnake.getHead().setAngle(newAngle, gc);
                        if (newAngle % 90 != 0.0) {
                            // adjust velocities based on angle
                            double acute = ((theSnake.getHead().getAngle() + 90.0) % 90);
                            // double acute = (newAngle%90);
                            double xSpeed = Speed * acute / 90.0;
                            double ySpeed = Speed - xSpeed;

                            // System.out.println("DEBUG" + x + " " + y +  "     " + newAngle + "    " + xSpeed + " " + ySpeed);
                            if (newAngle > -90.0 && newAngle < 0.0) // upper left quarter
                            {
                                setBGVelX(ySpeed);
                                setBGVelY(xSpeed);
                            } else if (newAngle > 0.0 && newAngle <= 90.0) // upp right corner
                            {
                                setBGVelX(-xSpeed);
                                setBGVelY(ySpeed);
                            } else if (newAngle > 90.0 && newAngle <= 180.0) // lower right corner
                            {
                                setBGVelX(-ySpeed);
                                setBGVelY(-xSpeed);
                            } else if (newAngle > 180.0 && newAngle <= 270.0) // lower left corner
                            {
                                setBGVelX(xSpeed);
                                setBGVelY(-ySpeed);
                            }
                            
                        }
                        theSnake.getHead().setVelocity(-getBGVelX(), -getBGVelY());
                        theSnake.getHead().setAngle(-newAngle, gc);
                        theSnake.setSecondChgs();
                    }
                    
                }                
                theSnake.velocityChanges();
                // collision detection
                Iterator<Sprite> appleIter = appleList.iterator();
                while (appleIter.hasNext()) {
                    Sprite apple = appleIter.next();
                    if (theSnake.getHead().intersects(apple)) {  //testing
                        appleIter.remove();
                        score.value++;
                        growCounter++;
                        SAI.mem = false; //testing
                        continue;
                    }
                    
                    if(growCounter >= NEXT_GROW){
                        Sprite bodySnake = new Sprite();
                            bodySnake.setImage("snake_body_red.png");
                            bodySnake.setPosition(theSnake);                           
                            bodySnake.setVelocity(theSnake);
                            theSnake.addBody(bodySnake);
                            NEXT_GROW = NEXT_GROW + 1;
                    }
                    
                    if (SAI.mem == false) {
                        Sprite nextApple = apple;
                        if (SAI.picksClosest) {
                            nextApple = SAI.shortestApple(appleList);
                        }
                        SAI.memAngle = SAI.calAngle(nextApple);
                        SAI.mem = true;
                    }
                }

                Iterator<Sprite> wallIter = wallList.iterator();
                while (wallIter.hasNext()) {
                    Sprite wall = wallIter.next();
                    if (theSnake.getHead().intersects(wall)) {
                        System.out.println("dead");
                        score.value = 0;
                        setBGVelX(0);
                        setBGVelY(0);
                        //reset back to 0
                    }
                }
                
                for (int i = 1; i < theSnake.getSize(); i++) {
                  //  theSnake.getSegement(i).setPosition(theSnake.getSegement(i).getPosX() + bgVelX * elapsedTime, 
                  //                                      theSnake.getSegement(i).getPosY() + bgVelY * elapsedTime);
                  //  theSnake.getSegement(i).update(elapsedTime, theScene);
                  theSnake.getSegement(i).update(theSnake, i);
                }
                
                

                theSnake2.setAngle(SAI.memAngle, gc);
                // theSnake2.setVelocity(Math.cos(Math.toRadians((SAI.memAngle - 90.0))) * 100, Math.sin(Math.toRadians((SAI.memAngle - 90.0))) * 100);
                theSnake2.setVelocity(Math.cos(Math.toRadians((SAI.memAngle - 90.0))) * Speed/2, Math.sin(Math.toRadians((SAI.memAngle - 90.0))) * Speed/2);

				// update the Snake2's position relative to the change
				// background velocity
				theSnake2.setPosition(theSnake2.getPosX() + getBGVelX() * elapsedTime,
					theSnake2.getPosY() + getBGVelY() * elapsedTime);

                //theSnake2.setVelocity(100, 0);
                theSnake2.update(elapsedTime, theScene);

                // render
                gc.clearRect(0, 0, WindowWidth, WindowHeight);

                setBGX(getBGX() + (getBGVelX() * elapsedTime));
                setBGY(getBGY() + (getBGVelY() * elapsedTime));

                // (getBGX() + getBGVelX()), (getBGY() + getBGVelY()),
                theScene.setFill(new ImagePattern(cracked,
                        getBGX(), getBGY(),
                        cracked.getWidth(), cracked.getHeight(), false));

                theSnake.getHead().render(gc);

                theSnake2.render(gc);
                
                for (int i = 0; i < theSnake.getSize(); i++) {
                    theSnake.getSegement(i).render(gc);
                }

                for (Sprite apple : appleList) {
                    apple.setPosition((apple.getSpriteX() + getBGVelX() * elapsedTime),
                            (apple.getSpriteY() + getBGVelY() * elapsedTime));
                    apple.render(gc);
                }

                for (Sprite wall : wallList) {
                    wall.setPosition((wall.getSpriteX() + getBGVelX() * elapsedTime), (wall.getSpriteY() + getBGVelY() * elapsedTime));
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

class LongValue {

    public long value;

    public LongValue(long i) {
        value = i;
    }
}

class IntValue {

    public long value;

    public IntValue(long i) {
        value = i;
    }
}
