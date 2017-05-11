package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.ResourceBundle;


public class ControllerReportAbsence extends ControllerMain implements Initializable {

    @FXML
    private TextField playerIDAbsence;
    @FXML
    private TextField trainingIDAbsence;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void playerLogout(ActionEvent ae) {

        try {
          changeScene(ae,"PlayerScene.fxml");
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    @FXML
    public void saveAbsenceData() {

        try {
            int playerID = Integer.parseInt(playerIDAbsence.getText());
            int trainingID = Integer.parseInt(trainingIDAbsence.getText());
            DBHandler dbHandler = new DBHandler();
            dbHandler.playerReportAbsenceDB(playerID, trainingID);

        }
        catch (InputMismatchException ex) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Your input was invalid.");
            dialog.showAndWait();
        }







    }


}
