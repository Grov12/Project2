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
public class ControllerAddMatch extends ControllerMain  {

    @FXML private TextField opponentTextField;
    @FXML private TextField dateTextField;
    @FXML private TextField resultTextField;
    @FXML private Button backButton;
    @FXML private Button addMatchButton;

    @Override
    public void initialize(URL location, ResourceBundle resources){

    }

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
                DBHandler db = new DBHandler();
                String opponent = opponentTextField.getText();
                String date = dateTextField.getText();


                if (db.doesMatchEntryExist(opponent,date)) {
                     //**
                    // this statement ask user if he/she wants to add duplicate information into database
                    //**
                    if       (createConformationDialog("Duplicate entry", "The match against "+opponent+" " +
                            "("+date+ ")" + " already exist in the database do you still " +
                            "want to add it?").getResult()==ButtonType.NEXT.YES){
                        db.addMatchToDB(date, opponent);
                        //if a result is entered add it to the database
                        if (!resultTextField.getText().isEmpty()) {
                            String result = resultTextField.getText();
                            db.setMatchResultToDB(date,opponent,result);
                        }
                    }
                }else {
                    db.addMatchToDB(date, opponent);
                    //if a result is entered add it to the database
                    if (!resultTextField.getText().isEmpty()) {
                        String result = resultTextField.getText();
                        db.setMatchResultToDB(date,opponent,result);
                    }
                }

            } catch (NullPointerException ex){
                createInformationDialog("Error","Error","You did not enter all the required information");
            }
        }

    }

    private boolean isDateFormatRight(){
        return false;
    }



}
