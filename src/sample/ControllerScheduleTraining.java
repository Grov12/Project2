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
public class ControllerScheduleTraining {

    @FXML
    private TextField dateTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button addButton;


    @FXML
    private void buttonPressed(ActionEvent ae) {

        Button source = (Button) ae.getSource();

        if (source == backButton) {
            try {
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("CoachScene.fxml"));
                Parent root = null;
                root = (Parent) loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (source == addButton) {

            try {

                String date = dateTextField.getText();
                String time = timeTextField.getText();

                //continue..

            } catch (NullPointerException ex) {
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setHeaderText("Error:");
                dialog.setContentText("You did not enter all the required information.");
                dialog.showAndWait();
            }
        }
    }
}
