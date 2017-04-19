package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
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



    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private void playerLogout(ActionEvent ae) {
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

    public void playerview() {
                try {
                    ControllerMain main = new ControllerMain();
                    ControllerGuest.viewPlayers();

                }catch (Exception e) {

                }
    }
    @FXML
    private void teamButton(ActionEvent ae){
        //Add new code later
        playerTextArea.setText("Players in team");
    }
    @FXML
    private void matcheButton(ActionEvent ae){
        //Add new code later
        playerTextArea.setText("Upcoming matches");
    }
    @FXML
    private void statisticButton(ActionEvent ae){
        //Add new code later
        playerTextArea.setText("Player and match statistics");
    }
    @FXML
    private void trainingButton(ActionEvent ae){
        //Add new scene?
    }



        }




