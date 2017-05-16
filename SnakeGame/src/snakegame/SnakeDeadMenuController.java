package snakegame;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SnakeDeadMenuController {

    @FXML
    private Button mainMenu;

    @FXML
    private AnchorPane anchorsAway;
    
    @FXML
    void onMainMenuClick(ActionEvent event) throws IOException {
         System.out.println("Dead");
         Pane optionPane = (Pane) FXMLLoader.load(getClass().getResource("SnakeMenuLayout.fxml"));
         anchorsAway.getChildren().setAll(optionPane);

    }

}


