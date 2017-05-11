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
public class ControllerCoach extends ControllerPlayer  {

    @FXML private ChoiceBox choiceBox;
    @FXML private TextArea coachTextArea;
    @FXML private Button scheduleTrainingButton;
    @FXML private Button addMatchButton;
    @FXML private Button manageTeamButton;

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
    private void coachLogout(ActionEvent ae) { // Go back to log in scene.

        try {
            changeScene(ae,"MainScene.fxml");
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    @FXML
    private void coachButtonPressed(ActionEvent ae){

        Button source = (Button) ae.getSource();

        if (source == scheduleTrainingButton){
            try {
              changeScene(ae,"ScheduleTraining.fxml");
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }

        if (source == addMatchButton){
            try {
            changeScene(ae,"AddMatch.fxml");
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }

        if (source == manageTeamButton){
            try {
            changeScene(ae,"ManageTeam.fxml");
            } catch (IOException ex){
                ex.printStackTrace();
            }
        }

    }


    public void menuOptions() {

        if(choiceBox.getValue().equals("View Team")) {
            viewTeam();

        }

        if (choiceBox.getValue().equals("View Matches")){
            viewMatches(coachTextArea);
        }

        if (choiceBox.getValue().equals("View Players")){
            viewPlayers(coachTextArea);
        }

        if (choiceBox.getValue().equals("View Player Statistics")){
            viewPlayerStatistics(coachTextArea);
        }

        if (choiceBox.getValue().equals("View Match Statistics")){
            viewMatchStatistics(coachTextArea);
        }

        if (choiceBox.getValue().equals("View Training")){
            viewTraining(coachTextArea);


        }

    }



}
