package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-04-19.
 */
public class ControllerAddMatch extends ControllerMain {

    @FXML
    private TextField opponentTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextField resultTextField;
    @FXML
    private Button backButton;
    @FXML
    private Button addMatchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void buttonPressed(ActionEvent ae) {

        Button source = (Button) ae.getSource();

        if (source == backButton) {
            try {
                changeScene(ae, "CoachScene.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (source == addMatchButton) {

            try {
                DBHandler db = new DBHandler();
                String opponent = opponentTextField.getText();
                String date = dateTextField.getText();


                if (db.doesMatchEntryExist(date,opponent)) {
                    duplicatesDoExistInDB(date,opponent);
                }else {
                    //if a result is entered add it to the database
                    if (!resultTextField.getText().isEmpty()) {
                        isResultFormatRight(resultTextField.getText());
                        db.addMatchToDB(date, opponent);
                        String result = resultTextField.getText();
                        db.setMatchResultToDB(date, opponent, result);
                    }else {
                    db.addMatchToDB(date, opponent);
                    }
                }


            } catch(Exception ex)  {
                createInformationDialog("Error", "Error", "You did not enter all the required information in the right format");
            }
        }

    }

    private void isDateFormatRight() throws Exception {
        //balbalab

        throw new Exception("fel format");


    }

    //Method to see if the result is written in correct format.
    public void isResultFormatRight(String result)throws Exception{
        String pattern1 = "\\d\\d"+"-"+"\\d\\d";
        String pattern2 = "\\d"+"-"+"\\d";
        String pattern3 = "\\d"+"-"+"\\d\\d";
        String pattern4 = "\\d\\d"+"-"+"\\d";

        if (result.matches(pattern1)||result.matches(pattern2)||result.matches(pattern3)||result.matches(pattern4)){

        }else {
            throw new IndexOutOfBoundsException();
        }

    }

    // This Method ask user if he/she wants to add duplicate information into database
    private void duplicatesDoExistInDB(String date, String opponent) {

        try {
            DBHandler db = new DBHandler();

            if (createConformationDialog("Duplicate entry", "The match against " + opponent + " " +
                    "(" + date + ")" + " already exist in the database do you still " +
                    "want to add it?").getResult() == ButtonType.NEXT.YES) {

                db.addMatchToDB(date, opponent);
                //if a result is entered add it to the database
                if (!resultTextField.getText().isEmpty()) {
                    isResultFormatRight(resultTextField.getText());
                    String result = resultTextField.getText();
                    db.setMatchResultToDB(date, opponent, result);
                }

            }

        } catch (Exception ae) {
            createInformationDialog("Error", "Error", "Something went wrong");
        }


    }
}