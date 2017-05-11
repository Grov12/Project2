package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ControllerReportAbsence extends ControllerMain implements Initializable {

    @FXML
    private TextField textNameTraining;
    @FXML
    private TextField textTrainingID;
    @FXML
    private TextArea textAreaTraining;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ControllerGuest viewTraining = new ControllerGuest();
        viewTraining.viewTraining(textAreaTraining);
    }
    @FXML
    private void playerLogout(ActionEvent ae) {

        try {
          changeScene(ae,"PlayerScene.fxml");
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    @FXML
    private void saveAbsenceData() {




    }


}
