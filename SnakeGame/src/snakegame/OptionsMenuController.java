package snakegame;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;

public class OptionsMenuController {

    @FXML
    private CheckBox aiSnakesCheckbox;

    @FXML
    private CheckBox onlineGameCheckbox;

    @FXML
    private Button optionsBackButton;
    
    @FXML
    private Pane optionsPane;

    @FXML
    void onOptionsBackButtonClick(ActionEvent event) throws IOException {
         Pane optionPane = (Pane) FXMLLoader.load(getClass().getResource("SnakeMenuLayout.fxml"));
         optionsPane.getChildren().setAll(optionPane);
    }

    @FXML
    void onAiSnakesChecked(ActionEvent event) {

    }

    @FXML
    void onOnlineGameChecked(ActionEvent event) {

    }

}
