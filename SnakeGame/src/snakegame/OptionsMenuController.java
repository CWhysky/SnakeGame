package snakegame;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

//controller class for the option menu
public class OptionsMenuController {

    @FXML
    private TextField numberOfPlayers;
    
    @FXML
    private CheckBox aiSnakesCheckbox;

    @FXML
    private TextField networkHostname;

    @FXML
    private Button optionsBackButton;
     
   @FXML
    private Pane optionsPane;

    @FXML   //back to main menu when click on back button on option menu
    void onOptionsBackButtonClick(ActionEvent event) throws IOException {
         Pane optionPane = (Pane) FXMLLoader.load(getClass().getResource("SnakeMenuLayout.fxml"));
         optionsPane.getChildren().setAll(optionPane);
    }

    @FXML   //check mark button for AI snake on option menu
    void onAiSnakesChecked(ActionEvent event) {
           
    }

    @FXML   //number of player type in text field on option menu
    void onNumberOfPlayerstyped(ActionEvent event) {
        
    }
    
        @FXML   //network host name type in text field on option menu
    void onNetworkHostnametyped(ActionEvent event) {

    }

}
