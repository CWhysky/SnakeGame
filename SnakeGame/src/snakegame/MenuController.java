package snakegame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        
    }

    @FXML
    void onExitClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onOptionsClick(ActionEvent event) {

    }

}
