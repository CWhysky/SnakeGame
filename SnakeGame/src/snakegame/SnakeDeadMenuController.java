package snakegame;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * SnakeDeadMenuController -- Controller class for the Game Over menu
 *
 * @author anthonyma
 */
public class SnakeDeadMenuController {

    @FXML
    private Button mainMenu;

    @FXML
    private AnchorPane anchorsAway;

    /**
     * onMainMenuClick - Sends the user back to the Main Menu.
     *
     * @param event
     * @throws IOException
     */
    @FXML   //return to main menu whene click on main menu button of snake dead menu
    void onMainMenuClick(ActionEvent event) throws IOException {
        System.out.println("Dead");
        Pane optionPane = (Pane) FXMLLoader.load(getClass().getResource("SnakeMenuLayout.fxml"));
        anchorsAway.getChildren().setAll(optionPane);

    }

}
