package sample;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
import java.util.ResourceBundle;


/**
 * Created by Daniel on 2017-04-19.
 */
public class ControllerScheduleTraining extends ControllerMain {

    @FXML
    private TextField dateTextField;
    @FXML
    private TextField timeTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button addButton;



    @Override
    public void initialize(URL location, ResourceBundle resources) {



    }

    @FXML
    private void buttonPressed(ActionEvent ae) {

        Button source = (Button) ae.getSource();

        if (source == backButton) {
            try {
              changeScene(ae,"CoachScene.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (source == addButton) {

            try {
                DBHandler db = new DBHandler();
                String date = dateTextField.getText();
                String pattern = "([1-9]|[0-2][0-9]|[0-3][0-1])/([1-9]|[0-1][0-2])";
                if (!date.matches(pattern)) {
                    throw new InputMismatchException();
                }

                String time = timeTextField.getText();
                if (!time.matches("\\d{2}:\\d{2}")){
                    throw new InputMismatchException();
                }

                if (!db.duplicateTraining(date, time)){
                    db.scheduleTraining(date, time);
                } else {
                    if (createConformationDialog("Duplicate entry", "The training at " + date + ", " + time + "o'clock already exists, " +
                                                "would you like to add it anyway?").getResult()== ButtonType.NEXT.YES){
                        db.scheduleTraining(date, time);
                    }
                }


            } catch (NullPointerException ex) {
                createErrorDialog("Error", "An error occured.", "You did not enter all the required information.");
            } catch (InputMismatchException ex){
                createErrorDialog("Error", "An error occured.", "You did not enter the information in the correct format.");
            }
        }
    }
}
