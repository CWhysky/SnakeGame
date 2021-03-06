package snakegame;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * MenuController.java
 *
 * Controller class for managing the main menu and button actions.
 *
 * @author anthonyma
 */
public class MenuController {

    @FXML
    private Button exitButton;

    @FXML
    private Button playGameButton;

    @FXML
    private Button optionsButton;

    @FXML
    private Pane snakeMenu;

    /**
     * onPlayGameclick() - will instantiate a new stage and run beginGame in the
     * Snakey class
     *
     * @param event
     * @throws Exception
     */
    @FXML     //Play game when click on play button
    void onPlayGameclick(ActionEvent event) throws Exception {
        Stage primaryStage = new Stage();
        Snakey snakeGame = new Snakey();
        snakeGame.beginGame(primaryStage);

    }

    /**
     * onExitClick() - System Call for ending the java app.
     *
     * @param event
     */
    @FXML   //Exit the game when click on exit button
    void onExitClick(ActionEvent event) {
        System.exit(0);
    }

    /**
     * onOptionsClick() - Redirects the current scene into the the layout
     * SnakeOptionsLayout.fxml, thereby switching the the options menu
     *
     * @param event
     * @throws IOException
     */
    @FXML   //bring up option menu when click on option button
    void onOptionsClick(ActionEvent event) throws IOException {
        Pane snakeyMenu = (Pane) FXMLLoader.load(getClass().getResource("SnakeOptionsLayout.fxml"));
        snakeMenu.getChildren().setAll(snakeyMenu);
    }
}
