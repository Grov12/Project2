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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by robin on 2017-04-09.
 */
public class ControllerGuest extends ControllerMain  {


    @FXML
    private TextArea guestTextArea;

    @FXML private ChoiceBox choiceBox;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().add("View Team");
        choiceBox.getItems().add("View Matches");
        choiceBox.getItems().add("View Players");
        choiceBox.getItems().add("View Player Statistics");
        choiceBox.getItems().add("View Match Statistics");
        choiceBox.getItems().add("View Training");


    }

    @FXML
    private void guestLogout(ActionEvent ae) {

        try {
            changeScene(ae, "MainScene.fxml");
        } catch (IOException var7) {
            var7.printStackTrace();
        }


    }

    public void menuOptions() {

        if (choiceBox.getValue().equals("View Team")) {
            viewTeam();
        }

        if (choiceBox.getValue().equals("View Matches")){
            viewMatches(guestTextArea);
        }

        if (choiceBox.getValue().equals("View Players")){
            viewPlayers(guestTextArea);

        }

        if (choiceBox.getValue().equals("View Player Statistics")){
            viewPlayerStatistics(guestTextArea);
        }

        if (choiceBox.getValue().equals("View Match Statistics")){
            viewMatchStatistics(guestTextArea);
        }

        if (choiceBox.getValue().equals("View Training")){
            viewTraining(guestTextArea);

        }

    }


    public static void viewTeam() {
        DBHandler dbView = new DBHandler();

        dbView.viewTrainingDB();


        dbView.viewTeamDB();

    }

    public void viewMatches(TextArea text) {
        try{
            DBHandler dbView = new DBHandler();
            text.setText(String.valueOf(dbView.viewMatchDB()).replace("[", "").replace("]", "").replace(",", ""));
        }catch (Exception e){

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Something went wrong when loading Matches");
            dialog.showAndWait();

        }
    }

    public void viewPlayers(TextArea tx) {
        try {
            DBHandler dbView = new DBHandler();
            tx.setText(String.valueOf(dbView.viewPlayersDB()).replace("[", "").replace("]", "").replace(",", ""));
        }catch (Exception e) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Something went wrong when loading Players");
            dialog.showAndWait();
        }



    }

    public void viewPlayerStatistics(TextArea text) {
        try {
            DBHandler dbView = new DBHandler();
            text.setText(String.valueOf(dbView.viewPlayerStatisticsDB()).replace("[", "").replace("]", "").replace(",", ""));
        } catch (Exception e) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Something went wrong when loading Player Statistics");
            dialog.showAndWait();

        }
    }



    public void viewMatchStatistics(TextArea teext){
        try {
            DBHandler dbView = new DBHandler();
            teext.setText(String.valueOf(dbView.viewMatchStatisticsDB()).replace("[", "").replace("]", "").replace(",", ""));
        } catch(Exception e) {

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Something went wrong when loading Match Statistics");
            dialog.showAndWait();

        }

    }

    public void viewTraining(TextArea text){
        try {
            DBHandler dbView = new DBHandler();
            text.setText(String.valueOf(dbView.viewTrainingDB()).replace("[", "").replace("]", "").replace(",", ""));

        } catch (Exception e){

            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Something went wrong when loading Training");
            dialog.showAndWait();

        }

    }



}
