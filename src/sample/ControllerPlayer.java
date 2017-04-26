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



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBoxView.getItems().add("View Team");
        choiceBoxView.getItems().add("View Matches");
        choiceBoxView.getItems().add("View Players");
        choiceBoxView.getItems().add("View Player Statistics");
        choiceBoxView.getItems().add("View Match Statistics");
        choiceBoxView.getItems().add("View Training");




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
            if(choiceBoxView.getValue().equals("View Team")) {
                viewTeam();

            }

            if (choiceBoxView.getValue().equals("View Matches")){
                viewMatches();
            }

            if (choiceBoxView.getValue().equals("View Players")){
                DBHandler dbHandler = new DBHandler();
                playerTextArea.setText(dbHandler.viewPlayersDB());
            }

            if (choiceBoxView.getValue().equals("View Player Statistics")){
                viewPlayerStatistics();
            }

            if (choiceBoxView.getValue().equals("View Match Statistics")){
                viewMatchStatistics();
            }

            if (choiceBoxView.getValue().equals("View Training")){
                viewTraining();


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




    }



















