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
        //DBHandler dbHandler = new DBHandler();
        //dbHandler.deletePlayerFromDB(8);
        System.out.println("Boom");


    }

    @FXML
    private void loginButton(ActionEvent ae) {

        if (choiceBox.getValue().equals("Coach")) {
            try {
                throwExceptions();
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("CoachScene.fxml"));
                Parent root = null;
                root = (Parent) loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);
            } catch (IOException var7) {
                var7.printStackTrace();
            } catch (NullPointerException e) {
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setHeaderText("Error:");
                dialog.setContentText("Both fields must be filled in!");
                dialog.showAndWait();

            }





        } else if (choiceBox.getValue().equals("Player")) {
            try {
                throwExceptions();
                Node node = (Node) ae.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PlayerScene.fxml"));
                Parent root = null;
                root = (Parent) loader.load();
                Scene scene = new Scene(root);
                stage.setScene(scene);


            } catch (IOException var7) {
                var7.printStackTrace();
            } catch (NullPointerException e) {
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setHeaderText("Error:");
                dialog.setContentText("Both fields must be filled in!");
                dialog.showAndWait();
            } catch (Exception e) {
               e.printStackTrace();
            }








        }  if (choiceBox.getValue().equals("Guest")) {
            Node node = (Node) ae.getSource();
            Stage stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("GuestScene.fxml"));

            Parent root = null;

            try {
                root = (Parent) loader.load();
            } catch (IOException var7) {
                var7.printStackTrace();
            }catch (NullPointerException e) {
                System.out.println("hi");
            }

            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }

    public void handleMenuItemEdit() {
        if (!textUsername.getText().isEmpty() || !textPassword.getText().isEmpty() || !choiceBox.getValue().equals(null)) {
            textUsername.clear();
            textPassword.clear();
            choiceBox.setValue(null);

        }


    }


    public void viewPlayers() {
    }

    public void viewTeam() {
    }

    public void viewStatistics() {
    }

    public void viewMatches() {
    }

    @FXML
    public void throwExceptions() {
        if (textPassword.getText().isEmpty() && textUsername.getText().isEmpty()) {
            throw new NullPointerException("Not possible");

        }


    }


    }





