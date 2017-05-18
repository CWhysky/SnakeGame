package snakegame;

import java.io.IOException;
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
import java.util.LinkedList;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXMLLoader;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;

// the main application class of snake game
public class Snakey extends Application {

    int nextGrow = 1;
    ArrayList<Snake> snakes = new ArrayList<Snake>();
    int aiSnakeCount = 10; // Need to fetch this from menu
    String[] snakeHeads =  {"head_yellow.png", "snake_head_red.png", "snake_head_green.png"};
    String[] snakeBodies = {"snake_body_yellow.png", "snake_body_red.png", "snake_body_green.png"};
    int colorCount = 0;
    
    @Override   //the function to start the stage when main function called launch(args) funtion
    public void start(Stage primaryStage) throws Exception {
        //Menu Scene: Complete
        //Menu Layout: Complete
        Pane snakeMenu = (Pane) FXMLLoader.load(getClass().getResource("SnakeMenuLayout.fxml"));
        primaryStage.setScene(new Scene(snakeMenu));
        primaryStage.show(); 
    }

    /**
     * the game loop function for snake game
     * @param primaryStage javaFX stage 
     * @throws IOException 
     */
    public void beginGame(Stage primaryStage) throws IOException {

        //Handles the speed and position of the window in relation to the game grid
        Background bg = new Background();
        //the root of the Window
        Group root = new Group();
        //The speed of the player Character Snake
        int Speed = 500;
        
        int WindowWidth = 640;
        int WindowHeight = 640;
        int GameGridWidth = 8192;
        int GameGridHeight = 8192;

        //The window view
        Scene theScene = new Scene(root, WindowWidth, WindowHeight);
        Scene randomScene = new Scene(FXMLLoader.load(getClass().getResource("SnakeDeadMenu.fxml")), WindowWidth, WindowHeight);
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

        // Player Snake
        Snake playerSnake = new Snake();
        Sprite snakeHead = new Sprite();
        snakeHead.setImage("snake_head_purple.png");
        playerSnake.setBodyColor("snake_body_purple.png");
        snakeHead.setPosition((theScene.getWidth() / 2) - 32, (theScene.getHeight() / 2) - 32);
        playerSnake.setHead(snakeHead);
        snakes.add(playerSnake);

        //creating AI sankes
        for(int i=0; i<aiSnakeCount; i++) {
            // Create spriteobject and set color
            snakeHead = new Sprite();
            snakeHead.setImage(snakeHeads[colorCount]);
            
            SnakeAI SAI = new SnakeAI(snakeHead);
            
            // Set body color and "increment" color so next snake is of different color
            SAI.setBodyColor(snakeBodies[colorCount]);
            colorCount = (colorCount + 1) % snakeHeads.length;
            
            snakeHead.setPosition(200, 200);
            SAI.setHead(snakeHead);
            snakes.add(SAI);
        }
        
        //Set the initial velocity of the background.
        bg.setBGVelX(0);
        bg.setBGVelY(Speed);
        
        Apple apples = new Apple(); //apple factory
        
        //Places the apples at the start of the game on the gameGrid
        ArrayList<Sprite> appleList = new ArrayList<Sprite>();
        for (int i = 0; i < 50; i++) 
        {
            Sprite apple = new Sprite();
            apple.setImage("apple.png");
            double px = -GameGridWidth/2 * Math.random() + 50;
            double py = GameGridWidth/2 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = GameGridWidth/2 * Math.random() + 50;
            py = -GameGridWidth/2 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = -GameGridWidth/2 * Math.random() + 50;
            py = -GameGridWidth/2 * Math.random() + 50;
            apple.setPosition(px, py);
            appleList.add(apple);

            apple = new Sprite();
            apple.setImage("apple.png");
            px = GameGridWidth/2 * Math.random() + 50;
            py = GameGridWidth/2 * Math.random() + 50;
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
                        playerSnake.getHead().setAngle(newAngle);
                        if (newAngle % 90 != 0.0) {
                            // adjust velocities based on angle
                            double acute = ((playerSnake.getHead().getAngle() + 90.0) % 90);
                            
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
                            playerSnake.getHead().setVelocity(-bg.getBGVelX(), -bg.getBGVelY());
                            playerSnake.getHead().setAngle(newAngle);
                        }
                    }
                }

                // Detecting collisions with player snake
                Iterator<Snake> snakesIter = snakes.iterator();
                Snake player = snakesIter.next(); // Player is first snake in `snakes` list
                
                while(snakesIter.hasNext()) {
                    Snake ai = snakesIter.next();
                    LinkedList<Sprite> aiBody = ai.getBody();
                    Iterator<Sprite> aiBodyIter = aiBody.iterator();
                   
                    // Player head collides with AI body
                    while(aiBodyIter.hasNext()) {
                        Sprite bodyPart = aiBodyIter.next();
                        if (player.getHead().intersects(bodyPart)){
                            player.setScore(0);
                            bg.setBGVelX(0);
                            bg.setBGVelY(0);
                            primaryStage.setScene(randomScene);
                        }
                    }
                    
                    // AI head collides with other snake body
                    for (Snake s : snakes) {
                        if (s == ai)
                            continue;
                        
                        LinkedList<Sprite> snakeBody = s.getBody();
                        Iterator<Sprite> snakeBodyIter = snakeBody.iterator();
                        
                        while(snakeBodyIter.hasNext()) {
                            Sprite bodyPart = snakeBodyIter.next();
                            if (ai.getHead().intersects(bodyPart)){
                                // AI player dies and respawns
                                ai.getHead().setPosition(GameGridWidth/2 * Math.random() + 50, GameGridHeight/2 * Math.random() + 50);
                            }    
                        }
                    }
                }   
                
                //Checks to see if a snake has hit a wall.
                Iterator<Sprite> wallIter = wallList.iterator();
                while (wallIter.hasNext()) {
                    Sprite wall = wallIter.next();
                    if (playerSnake.getHead().intersects(wall)) {
                        player.setScore(0);
                        bg.setBGVelX(0);
                        bg.setBGVelY(0);
                        primaryStage.setScene(randomScene);
                    }
                    for (Snake snake : snakes) {
                        if (snake == playerSnake)
                            continue;
                        if (snake.getHead().intersects(wall)){
                            snake.getHead().setPosition(GameGridWidth/2 * Math.random() + 50, GameGridHeight/2 * Math.random() + 50);
                            snake.dropTail();
                        }
                    }
                }
                
                Iterator<Sprite> appleIter = appleList.iterator();
                while (appleIter.hasNext()) {
                    Sprite apple = appleIter.next();
                    SnakeAI ai;
                    
                    //snakes interaction with apple
                    for (Snake s : snakes) {
                        if(s.getHead().intersects(apple)){
                            apples.SpanwAppleInSameQ(apple.getPosX(), apple.getPosY());
                            apple.setPosition(apples.getXposition(), apples.getYpostion());
                            s.setScore(s.getScore() + 1);
                            s.setGrowCount(playerSnake.getGrowCount() + 1);
                            if(s != playerSnake){
                                ai = (SnakeAI) s;
                                ai.mem = false;
                            }
                            continue;
                        }
                    }
                   
                    for (Snake s : snakes) {
                        if (s.getGrowCount() >= nextGrow) {
                            Sprite bodySnake = new Sprite();
                            bodySnake.setImage(s.getBodyColor());
                            bodySnake.setPosition(playerSnake);
                            bodySnake.setVelocity(playerSnake);
                            s.addBody(bodySnake);
                            s.setGrowCount(0);
                        }
                    }
                    
                    for (Snake s : snakes) {
                        if (s == playerSnake)
                            continue;
                        else
                            ai = (SnakeAI) s;
                        if (ai.mem == false) {
                            Sprite nextApple = ai.closestApple(appleList);
                            ai.memAngle = ai.calAngle(nextApple);
                            ai.mem = true;
                        }
                    }
                }
                
                Iterator<Snake> snakeIter = snakes.iterator();
                Snake discardPlayerSnake = snakeIter.next(); // Skip player snake

                while(snakeIter.hasNext()) {
                    Snake s = snakeIter.next();
                    SnakeAI ai = (SnakeAI) s;
                    
                    ai.getHead().setAngle(ai.memAngle);
                    ai.getHead().setVelocity(Math.cos(Math.toRadians((ai.memAngle - 90.0))) * Speed / 2, Math.sin(Math.toRadians((ai.memAngle - 90.0))) * Speed / 2);
                    ai.getHead().setPosition(ai.getHead().getPosX() + bg.getBGVelX() * elapsedTime, ai.getHead().getPosY() + bg.getBGVelY() * elapsedTime);
                    ai.getHead().update(elapsedTime);
                }

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
                for (int i = playerSnake.getSize() - 1; i > 0; i--) {
                    playerSnake.getSegment(i).setPosition(playerSnake.getSegment(i - 1).getPosX() + (bg.bgVelX * elapsedTime),
                    playerSnake.getSegment(i - 1).getPosY() + (bg.bgVelY * elapsedTime));
                    playerSnake.getSegment(i).setAngle(playerSnake.getSegment(i - 1).getAngle());
                    playerSnake.getSegment(i).render(gc);
                }
                
                for (Snake s : snakes) {
                    if (s == playerSnake) {
                        playerSnake.getHead().render(gc);
                    } else {
                        //Renders the AI snake
                        for (int i = s.getSize() - 1; i > 0; i--) {
                            s.getSegment(i).setPosition(
                                s.getSegment(i - 1).getPosX() + (bg.bgVelX * elapsedTime),
                                s.getSegment(i - 1).getPosY() + (bg.bgVelY * elapsedTime));
                            s.getSegment(i).setAngle(s.getSegment(i - 1).getAngle());
                            s.getSegment(i).render(gc);
                        }
                        s.getHead().render(gc);
                    }
                }

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

                //String pointsText = "Score: " + (100 * player.getScore()) + " , body: " + (playerSnake.getBody().size());
                String pointsText = "Score: " + (100 * player.getScore());
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


