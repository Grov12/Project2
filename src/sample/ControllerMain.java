package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerMain implements Initializable {
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField textUsername;
    @FXML
    private TextField textPassword;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().add("Guest");
        choiceBox.getItems().add("Coach");
        choiceBox.getItems().add("Player");



    }

    @FXML
    private void loginButton(ActionEvent ae) {
        if (choiceBox.getValue().equals("Coach")) {
            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("CoachScene.fxml"));

            Parent root = null;

            try {
                root = (Parent) loader.load();
            } catch (IOException var7) {
                var7.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
        else if (choiceBox.getValue().equals("Player")) {
            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PlayerScene.fxml"));

            Parent root = null;

            try {
                root = (Parent) loader.load();
            } catch (IOException var7) {
                var7.printStackTrace();
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }

      else if (choiceBox.getValue().equals("Guest")) {
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GuestScene.fxml"));

                Parent root = null;

                try {
                    root = (Parent) loader.load();
                } catch (IOException var7) {
                    var7.printStackTrace();
                }

                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
        }
        public void handleMenuItemEdit() {
            if(!textUsername.getText().isEmpty() || !textPassword.getText().isEmpty() || !choiceBox.getValue().equals(null)) {
                textUsername.clear(); textPassword.clear();
                choiceBox.setValue(null);

            }


        }



}



