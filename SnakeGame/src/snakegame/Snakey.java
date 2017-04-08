/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakegame;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import javafx.scene.input.KeyEvent;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import java.util.Iterator;
import javafx.animation.AnimationTimer;
// import javafx.event.ActionEvent;
// import javafx.scene.control.Button;
// import javafx.scene.layout.StackPane;
// import javafx.scene.image.ImageView;
// import javafx.scene.SnapshotParameters;

import javafx.scene.paint.ImagePattern;



/**
 *
 * @author ascott
 */
public class Snakey extends Application {
	int velX = 0;
	int velY = 0;

	int getVelX() {
		return velX;
	}

	int getVelY() {
		return velY;
	}

	void setVelX(int num) {
		velX = num;
	}

	void setVelY(int num) {
		velY = num;
	}

	int X = 0;
	int Y = 0;

	int getX() {
		return X;
	}

	int getY() {
		return Y;
	}

	void setX(int num) {
		X = num;
	}

	void setY(int num) {
		Y = num;
	}

	
	@Override
	public void start(Stage primaryStage) {
		
		// StackPane root = new StackPane();
		Group root = new Group();
		int WindowWidth = 640;
		int WindowHeight = 640;
		int GameGridWidth = 4096;
		int GameGridHeight = 4096;
		Scene theScene = new Scene(root, WindowWidth, WindowHeight);
		primaryStage.setTitle("Snakey!");
		primaryStage.setScene(theScene);

		// Canvas canvas = new Canvas(4096, 4096);
		Canvas canvas = new Canvas(WindowWidth, WindowHeight);

        	ArrayList<String> input = new ArrayList<String>();

		// Image space = new Image("space.png");
		Image cracked = new Image("stars5.jpg");
		theScene.setFill(new ImagePattern(cracked, 
			((theScene.getWidth()/2)-32), 
			((theScene.getHeight()/2)-32), .6, .6, true));

		root.getChildren().add(canvas);

		theScene.setOnKeyPressed(
		    new EventHandler<KeyEvent>()
		    {
			public void handle(KeyEvent e)
			{
			    String code = e.getCode().toString();
			    if ( !input.contains(code) )
				input.add( code );
			}
		    });

		theScene.setOnKeyReleased(
		    new EventHandler<KeyEvent>()
		    {
			public void handle(KeyEvent e)
			{
			    String code = e.getCode().toString();
			    input.remove( code );
			}
		    });

	        GraphicsContext gc = canvas.getGraphicsContext2D();

		Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 24 );
		gc.setFont( theFont );
		gc.setFill( Color.YELLOW);
		gc.setStroke( Color.BLACK );
		gc.setLineWidth(1);

		Sprite theSnake = new Sprite();
		theSnake.setImage("snake_head_red.png");
		theSnake.setPosition((theScene.getWidth()/2)-32, (theScene.getHeight()/2)-32, 180);

		// Sprite theBG = new Sprite();
		// theBG.setImage("cracked.png");
		// theBG.setPosition((theScene.getWidth()/2)-32, (theScene.getHeight()/2)-32, 180);

		ArrayList<Sprite> appleList = new ArrayList<Sprite>();

		for (int i = 0; i < 15; i++)
		{
		    Sprite apple = new Sprite();
		    apple.setImage("apple.png");
		    double px = 350 * Math.random() + 50;
		    double py = 350 * Math.random() + 50;          
		    apple.setPosition(px, py, 90);
		    appleList.add( apple );
		}

		LongValue lastNanoTime = new LongValue( System.nanoTime() );

		IntValue score = new IntValue(0);

		new AnimationTimer()
		{
		    public void handle(long currentNanoTime)
		    {
			// calculate time since last update.
			double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
			lastNanoTime.value = currentNanoTime;

			// game logic

			// theSnake.setVelocity(0,0);

			if (input.contains("LEFT"))
			{
			    // theSnake.setPosition(-250,0, 270);
			    // theSnake.setVelocity(-250,0, 270, theScene);
			    theSnake.setVelocity(0,0, 270, theScene, -250, 0);
			    // theBG.setVelocity(-200, 0, 270, theScene);
			    setVelX(1);
			    setX(getX()+10);
			}

			if (input.contains("RIGHT"))
			{
			    // theSnake.setPosition(250,0, 90);
			    // theSnake.setVelocity(250,0, 90, theScene);
			    theSnake.setVelocity(0, 0, 90, theScene, 250, 0);
			    setVelX(-1);
			    setX(getX()-10);
			}

			if (input.contains("UP"))
			{
			    // theSnake.setPosition(0,-250, 0);
			    // theSnake.setVelocity(0,-250, 0, theScene);
			    theSnake.setVelocity(0, 0, 0, theScene, 0, -250);
			    setVelY(1);
			    setY(getY()+10);
			}

			if (input.contains("DOWN"))
			{
			    // theSnake.setPosition(0,250, 180);
			    // theSnake.setVelocity(0,250, 180, theScene);
			    theSnake.setVelocity(0, 0, 180, theScene, 0, 250);
			    setVelY(-1);
			    setY(getY()-10);
			}

			// collision detection

			Iterator<Sprite> appleIter = appleList.iterator();
			while ( appleIter.hasNext() )
			{
			    Sprite apple = appleIter.next();
			    if ( theSnake.intersects(apple) )
			    {
				appleIter.remove();
				score.value++;
			    }
			}

			// render

			gc.clearRect(0, 0, WindowWidth, WindowHeight);
			// gc.drawImage(space, 0, 0);
			// gc.drawImage(cracked, 0, 0);
			// gc.setFill(new ImagePattern(cracked, 0.2, 0.2, .6, .6, true));


			theScene.setFill(new ImagePattern(cracked, getX() + getVelX(), getY() + getVelY(), cracked.getWidth(), cracked.getHeight(), false));

			theSnake.render( gc );

			for (Sprite apple : appleList )
			    apple.render( gc );

			String pointsText = "Score: " + (100 * score.value);
			gc.fillText( pointsText, 360, 24);
			gc.strokeText( pointsText, 360, 24);
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
