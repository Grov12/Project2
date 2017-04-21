package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
public class ControllerCoach extends ControllerPlayer implements Initializable {

    @FXML private ChoiceBox choiceBox;
    @FXML private TextArea coachTextArea;
    @FXML private Button scheduleTrainingButton;
    @FXML private Button addMatchButton;
    @FXML private Button manageTeamButton;

    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().add("View Team");
        choiceBox.getItems().add("View Matches");
        choiceBox.getItems().add("View Players");
        choiceBox.getItems().add("View Player Statistics");
        choiceBox.getItems().add("View Match Statistics");
        choiceBox.getItems().add("View Training");

    }

    @FXML
    private void coachLogout(ActionEvent ae) {
        Node node = (Node)ae.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
        Parent root = null;

        try {
            root = (Parent)loader.load();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void coachButtonPressed(ActionEvent ae){

        Button source = (Button) ae.getSource();

        if (source == scheduleTrainingButton){
            try {
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ScheduleTraining.fxml"));
                Parent root = null;
                root = (Parent) loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }

        if (source == addMatchButton){
            try {
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("AddMatch.fxml"));
                Parent root = null;
                root = (Parent) loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }

        if (source == manageTeamButton){
            try {
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("ManageTeam.fxml"));
                Parent root = null;
                root = (Parent) loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }


    public void menuOptions() {

        if (choiceBox.getValue().equals("View Team")) {
            viewTeam();
        }

        if (choiceBox.getValue().equals("View Matches")){
            viewMatches();
        }

        if (choiceBox.getValue().equals("View Players")){
            viewPlayers();
        }

        if (choiceBox.getValue().equals("View Player Statistics")){
            viewPlayerStatistics();
        }

        if (choiceBox.getValue().equals("View Match Statistics")){
            viewMatchStatistics();
        }

        if (choiceBox.getValue().equals("View Training")){

        }

    }



}
