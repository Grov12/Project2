package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by robin on 2017-05-11.
 */
public class ControllerEditPlayer extends ControllerMain implements Initializable {

    @FXML
    private TextField textPlayerID;
    @FXML
    private TextField textYellowCard;
    @FXML
    private TextField textRedCard;
    @FXML
    private TextField textGoal;
    @FXML
    private Button returnButton;
    @FXML
    private TextArea playerView;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerGuest viewPlayer = new ControllerGuest();
        viewPlayer.viewPlayers(playerView);

    }

    @FXML
    private void goBack(ActionEvent ae) {
        Button source = (Button) ae.getSource();
        if (source == returnButton) {
            try {
                changeScene(ae, "ManageTeam.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void saveToDatabase() {
        try {
        int playerID = Integer.parseInt(textPlayerID.getText());
        int yellowCard = Integer.parseInt(textYellowCard.getText());
        int redCard = Integer.parseInt(textRedCard.getText());
        int goalsScored = Integer.parseInt(textGoal.getText());


            DBHandler db = new DBHandler();
            db.editPlayer(playerID, yellowCard, redCard, goalsScored);
        } catch (Exception e) {
            createErrorDialog("Error", "Error", "Fill in everything.");
        }
    }
}
