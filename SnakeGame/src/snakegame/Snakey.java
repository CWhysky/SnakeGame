/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.animation.AnimationTimer;
import javafx.scene.SnapshotParameters;

/**
 *
 * @author ascott
 */
public class Snakey extends Application {

    private int growCounter;
    private final int speed = 250;

    @Override
    public void start(Stage primaryStage) {

        Snake theSnake = new Snake();
        Sprite headSnake = new Sprite();
        headSnake.setImage("snake_head_red.png");
        headSnake.setPosition(200, 0, -1);

        theSnake.setHead(headSnake);

        // StackPane root = new StackPane();
        growCounter = 1;
        Group root = new Group();
        Scene theScene = new Scene(root, 512, 512);
        primaryStage.setTitle("Snakey!");
        primaryStage.setScene(theScene);

        // Canvas canvas = new Canvas(4096, 4096);
        Canvas canvas = new Canvas(512, 512);
        root.getChildren().add(canvas);

        ArrayList<String> input = new ArrayList<String>();

        theScene.setOnKeyPressed(
                new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                String code = e.getCode().toString();
                if (!input.contains(code)) {
                    input.add(code);
                    if (input.contains("LEFT") && theSnake.getHead().getAngle() != 90) {
                        theSnake.getHead().setVelocity(-speed, 0, 270);
                        theSnake.setSecondChgs();
                    }

                    if (input.contains("RIGHT") && theSnake.getHead().getAngle() != 270) {
                        theSnake.getHead().setVelocity(speed, 0, 90);
                        theSnake.setSecondChgs();
                    }

                    if (input.contains("UP") && theSnake.getHead().getAngle() != 180) {
                        theSnake.getHead().setVelocity(0, -speed, 0);
                        theSnake.setSecondChgs();
                    }

                    if (input.contains("DOWN") && theSnake.getHead().getAngle() != 0) {
                        theSnake.getHead().setVelocity(0, speed, 180);
                        theSnake.setSecondChgs();
                    }
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

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Image space = new Image("space.png");

        Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
        gc.setFont(theFont);
        gc.setFill(Color.YELLOW);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        ArrayList<Sprite> appleList = new ArrayList<Sprite>();

        for (int i = 0; i < 15; i++) {
            Sprite apple = new Sprite();
            apple.setImage("apple.png");
            double px = 350 * Math.random() + 50;
            double py = 350 * Math.random() + 50;
            apple.setPosition(px, py, 90);
            appleList.add(apple);
        }

        LongValue lastNanoTime = new LongValue(System.nanoTime());

        IntValue score = new IntValue(0);

        new AnimationTimer() {
            public void handle(long currentNanoTime) {
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;

                // game logic
                // theSnake.setVelocity(0,0);
                theSnake.velocityChanges();

                for (int i = 0; i < theSnake.getSize(); i++) {
                    theSnake.getSegement(i).update(elapsedTime);
                }

                // collision detection
                Iterator<Sprite> appleIter = appleList.iterator();
                while (appleIter.hasNext()) {
                    Sprite apple = appleIter.next();
                    if (theSnake.getHead().intersects(apple)) {
                        appleIter.remove();
                        score.value++;
                        if (score.value >= growCounter) {
                            Sprite bodySnake = new Sprite();
                            bodySnake.setImage("snake_body_red.png");
                            bodySnake.setPosition(theSnake);
                            bodySnake.setVelocity(theSnake);
                            theSnake.addBody(bodySnake);
                            growCounter += 1;

                        }
                    }
                }

                // render
                gc.clearRect(0, 0, 512, 512);
                gc.drawImage(space, 0, 0);

                for (int i = 0; i < theSnake.getSize(); i++) {
                    theSnake.getSegement(i).render(gc);
                }

                //theSnake.getHead().render( gc );
                for (Sprite apple : appleList) {
                    apple.render(gc);
                }

                String pointsText = "Score: " + (100 * score.value);
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
