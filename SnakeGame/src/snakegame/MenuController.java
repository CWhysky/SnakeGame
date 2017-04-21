package snakegame;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

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
    void onPlayGameclick(ActionEvent event) {
       //Pane snakeyMenu = (Pane) FXMLLoader.load(getClass().getResource("SnakeOptionsLayout.fxml"));
       //snakeMenu.getChildren().setAll(snakeyMenu);
       
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
