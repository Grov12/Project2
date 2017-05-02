package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Daniel on 2017-04-19.
 */
public class ControllerAddMatch extends ControllerMain {

    @FXML private TextField opponentTextField;
    @FXML private TextField dateTextField;
    @FXML private TextField resultTextField;
    @FXML private Button backButton;
    @FXML private Button addMatchButton;

    @FXML
    private void buttonPressed(ActionEvent ae){

        Button source = (Button) ae.getSource();

        if (source == backButton) {
            try {
               changeScene(ae , "CoachScene.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (source == addMatchButton){

            try {
                String opponent = opponentTextField.getText();
                String date = dateTextField.getText();
                String result = resultTextField.getText();
                int matchID = DataStorage.getInstance().getMatchList().size() + 1;

                //addMatchToDB(matchID, opponent, date, result);

            } catch (NullPointerException ex){
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setHeaderText("Error:");
                dialog.setContentText("You did not enter all the required information.");
                dialog.showAndWait();
            }
        }

    }

}
