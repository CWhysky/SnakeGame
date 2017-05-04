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

public class MenuController {

    @FXML
    private Button exitButton;

    @FXML
    private Button playGameButton;

    @FXML
    private Button optionsButton;

    @FXML
    private Pane snakeMenu;

    @FXML
    void onPlayGameclick(ActionEvent event) throws Exception {
         Stage primaryStage = new Stage();
         Snakey snakeGame = new Snakey();
         snakeGame.beginGame(primaryStage);
         
         
    }

    @FXML
    void onExitClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onOptionsClick(ActionEvent event) throws IOException {
       Pane snakeyMenu = (Pane) FXMLLoader.load(getClass().getResource("SnakeOptionsLayout.fxml"));
       snakeMenu.getChildren().setAll(snakeyMenu);
       }
}
