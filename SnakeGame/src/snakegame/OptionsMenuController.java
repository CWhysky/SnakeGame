package snakegame;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * OptionsMenuController -- Controller class for the Options menu. Redirects
 * current scene into layout SnakeMenuLayout.fxml
 *
 * @author anthonyma
 */
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

    /**
     * onOptionsBackButtonClick returns the user to the Main Menu when Back
     * button is selected from the Options Menu.
     *
     * @param event
     * @throws IOException
     */
    @FXML   // back to main menu when click on back button on option menu
    void onOptionsBackButtonClick(ActionEvent event) throws IOException {
        Pane optionPane = (Pane) FXMLLoader.load(getClass().getResource("SnakeMenuLayout.fxml"));
        optionsPane.getChildren().setAll(optionPane);
    }

    /**
     * onAiSnakesChecked TODO: Will return a boolean for whether the checkbox to
     * enable AI snakes or not is enabled.
     *
     * @param event
     */
    @FXML   //check mark button for AI snake on option menu
    void onAiSnakesChecked(ActionEvent event) {

    }

    /**
     * onNumberOfPlayerstyped TODO: Will handle change in number of players
     *   update to configuration variables. 
     *
     * @param event
     */
    @FXML   //number of player type in text field on option menu
    void onNumberOfPlayerstyped(ActionEvent event) {

    }

    /**
     * onNetworkHostnametyped TODO: Will handle hostname configuration.
     *
     * @param event
     */
    @FXML   //network host name type in text field on option menu
    void onNetworkHostnametyped(ActionEvent event) {

    }

}
