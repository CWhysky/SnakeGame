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
import javafx.scene.paint.ImagePattern;

public class Snakey extends Application {

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

		// StackPane root = new StackPane();
		Group root = new Group();
		int WindowWidth = 640;
		int WindowHeight = 640;
		int GameGridWidth = 4096;
		int GameGridHeight = 4096;
		Scene theScene = new Scene(root, WindowWidth, WindowHeight);
		primaryStage.setTitle("Snakey!");
		primaryStage.setScene(theScene);
		Canvas canvas = new Canvas(WindowWidth, WindowHeight);

		ArrayList<String> input = new ArrayList<String>();
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

		GraphicsContext gc = canvas.getGraphicsContext2D();

		Font theFont = Font.font("Helvetica", FontWeight.BOLD, 24);
		gc.setFont(theFont);
		gc.setFill(Color.YELLOW);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(1);

		Sprite theSnake = new Sprite();
		theSnake.setImage("snake_head_red.png");
		theSnake.setPosition((theScene.getWidth() / 2) - 32, (theScene.getHeight() / 2) - 32);
		theSnake.setAngle(180);

		ArrayList<Sprite> appleList = new ArrayList<Sprite>();

		for (int i = 0; i < 15; i++) {
			Sprite apple = new Sprite();
			apple.setImage("apple.png");
			double px = 350 * Math.random() + 50;
			double py = 350 * Math.random() + 50;
			apple.setPosition(px, py);
			appleList.add(apple);
		}

		LongValue lastNanoTime = new LongValue(System.nanoTime());
		IntValue score = new IntValue(0);

		new AnimationTimer() {

            @Override
			public void handle(long currentNanoTime) {

	            int bgVelX = 0;
	            int bgVelY = 0;

				// calculate time since last update.
				double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
				lastNanoTime.value = currentNanoTime;

				
				// game logic
				if (input.contains("LEFT")) {
					theSnake.setAngle(270);
					setBGVelX(500.0);
					setBGVelY(0.0);
					// setBGX(getBGX() + 1);
				}

				if (input.contains("RIGHT")) {
					theSnake.setAngle(90);
					setBGVelX(-500.0);
					setBGVelY(0.0);
					// setBGX(getBGX() - 1);
				}

				if (input.contains("UP")) {
					theSnake.setAngle(0);
					setBGVelY(500.0);
					setBGVelX(0.0);
					// setBGY(getBGY() + 1);
				}

				if (input.contains("DOWN")) {
					theSnake.setAngle(180);
					setBGVelY(-500.0);
					setBGVelX(0.0);
					// setBGY(getBGY() - 1);
				}

				// collision detection
				Iterator<Sprite> appleIter = appleList.iterator();
				while (appleIter.hasNext()) {
					Sprite apple = appleIter.next();
					if (theSnake.intersects(apple)) {
						appleIter.remove();
						score.value++;
					}
				}

				// render
				gc.clearRect(0, 0, WindowWidth, WindowHeight);

				setBGX(getBGX() + (getBGVelX()*elapsedTime));
				setBGY(getBGY() + (getBGVelY()*elapsedTime));

				// (getBGX() + getBGVelX()), (getBGY() + getBGVelY()),
				theScene.setFill(new ImagePattern(cracked, getBGX(), getBGY(), cracked.getWidth(), cracked.getHeight(), false));
			    theSnake.render(gc);

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
