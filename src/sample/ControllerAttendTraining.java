package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jimmyjonsson on 2017-04-26.
 */
public class ControllerAttendTraining extends ControllerMain implements Initializable {

    @FXML
    private TextField playerIDAttend;
    @FXML
    private TextField textTrainingID;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
    @FXML
    private void playerLogout(ActionEvent ae) {
        try {
            changeScene(ae , "PlayerScene.fxml");
        } catch (IOException var7) {
            var7.printStackTrace();
        }
    }
   // @FXML
   // private void

    //@FXML
    //public void saveAttendTraining(){
        //try{
            //int playerID = Integer.parseInt(playerIDAttend.getText());
            //int trainingID = Integer.parseInt(textTrainingID.getText());
            //DBHandler db = new DBHandler();
            //db.attendTrainingDB(playerID, trainingID);


        //}finally {

        //}




    }


