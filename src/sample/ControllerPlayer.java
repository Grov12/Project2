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
import java.util.ResourceBundle;

/**
 * Created by robin on 2017-04-09.
 */
public class ControllerPlayer extends ControllerGuest implements Initializable {
    @FXML
    private TextArea playerTextArea;


    @FXML
    private ChoiceBox choiceBoxView; //Choicebox for View functions in Player Scene

    @FXML
    private TextField textNameTraining; //Textfield for Firstname in Report Absence Scene
    @FXML
    private TextField textTrainingID;  //Textfield for Training ID in Report Absence Scene
    @FXML
    private TextField textNameAttend; //Textfield for Firstname in Attend Training Scene
    @FXML
    private TextField textMatchID; //Textfield for MatchID in Match Training Scene


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //choiceBoxView.getItems().add("View Matches");
        //choiceBoxView.getItems().add("View Team");
        //choiceBoxView.getItems().add("View Training");
        //choiceBoxView.getItems().add("View Statistics");

        //Choicebox Doesn't work with many FXML-files with the same Controller.


    }

    @FXML
    private void playerLogout(ActionEvent ae) {
        Node node = (Node) ae.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScene.fxml"));
        Parent root = null;

        try {
            root = (Parent) loader.load();
        } catch (IOException var7) {
            var7.printStackTrace();
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);

    }

        @FXML
        private void trainingButton (ActionEvent ae){
            //Add new scene?
        }

        @FXML
        private void viewBoxPlayer (ActionEvent ae){
            if (choiceBoxView.getValue().equals("View Matches")) {
                playerTextArea.setText("Upcoming matches");

            }
            if (choiceBoxView.getValue().equals("View Training")) {
                playerTextArea.setText("Upcoming training");
            }
            if (choiceBoxView.getValue().equals("View Team")) {
                playerTextArea.setText("Players in team");
            }
            if (choiceBoxView.getValue().equals("View Statistics")) {
                playerTextArea.setText("Vie Statistics");
            }

        }


        @FXML
        private void attendTraining (ActionEvent ae){
            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlayerSceneAttend.fxml"));
            Parent root = null;
            try {
                root = (Parent) loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root);
            stage.setScene(scene);

        }


        @FXML
        public void absence (ActionEvent ae){

            try {

                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PlayerSceneAbsence.fxml"));
                Parent root;
                root = loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException em) {
                em.printStackTrace();

            }


        }

        @FXML
        private void saveAbsenceData () {
            //Method to Report Abscene
            //TODO


        }


        @FXML
        private void saveAttendTraining () {
            //Method to Attend Training
            //TODO


        }

    }



















