package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.InputMismatchException;
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
    @FXML
    private Button removeMatchButton;
    @FXML
    private TextArea matchView;
    @FXML
    private TextField textDeleteMatch;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerGuest viewMatches = new ControllerGuest();
        viewMatches.viewMatches(matchView);

    }

    @FXML
    private void buttonPressed(ActionEvent ae) {

        Button source = (Button) ae.getSource();

        if (source == backButton) { //Go back to coach scene.
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
                isDateFormatRight(date);
                if (!resultTextField.getText().isEmpty()){
                    isResultFormatRight(resultTextField.getText());
                }
                ControllerGuest viewMatches = new ControllerGuest();
                viewMatches.viewMatches(matchView);

                if (db.doesMatchEntryExist(date,opponent)) {
                    duplicatesDoExistInDB(date,opponent);
                }else {
                    //if a result is entered add it to the database
                    if (!resultTextField.getText().isEmpty()) {
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

        if (source == removeMatchButton) {
            deleteMatchFromDB();
        }

    }



    private void isDateFormatRight(String date) throws Exception {
        String pattern ="([1-9]|[0-2][0-9]|[0-3][0-1])/([1-9]|[0-1][0-2])";

        if(!date.matches(pattern)){
            throw new IndexOutOfBoundsException();
        }





    }

    //Method to see if the result is written in correct format.
    public void isResultFormatRight(String result)throws Exception{

        String pattern = "([0-9]|[1-9][0-9])-([0-9]|[1-9][0-9])";

        if (!result.matches(pattern)){
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
    public void deleteMatchFromDB(){
        try {
            DBHandler dbHandler = new DBHandler();
            int id = Integer.parseInt(textDeleteMatch.getText());
            dbHandler.removeMatchFromDB(id);
            ControllerGuest viewMatches = new ControllerGuest();
            viewMatches.viewMatches(matchView);




        } catch (InputMismatchException ex) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Your input was invalid.");
            dialog.showAndWait();
        }

    }



    }
