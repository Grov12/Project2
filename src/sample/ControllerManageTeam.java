package sample;

import javafx.beans.binding.Bindings;
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
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.ResourceBundle;

/**
 * Created by Daniel on 2017-04-19.
 */
public class ControllerManageTeam extends ControllerMain implements Initializable {

    @FXML
    private TextArea playersInTheTeamTextArea;
    @FXML
    private TextField removePlayerIDTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField surNameTextField;
    @FXML
    private TextField positionTextField;
    @FXML
    private TextField userNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Button addPlayerButton;
    @FXML
    private Button removePlayerButton;
    @FXML
    private Button backButton;
    @FXML private RadioButton radioButton;
    @FXML private RadioButton radioButton2;
    @FXML private RadioButton radioButton3;
    @FXML private RadioButton radioButton4;
    @FXML private ToggleGroup toggleGroup = new ToggleGroup();


    @Override

    public void initialize(URL location, ResourceBundle resources) {
            ControllerGuest viewPlayer = new ControllerGuest();
            viewPlayer.viewPlayers(playersInTheTeamTextArea);
            radioButton.setToggleGroup(toggleGroup);
            radioButton2.setToggleGroup(toggleGroup);
            radioButton3.setToggleGroup(toggleGroup);
            radioButton.setUserData("Goalkeeper");
            radioButton2.setUserData("Defender");
            radioButton3.setUserData("Midfielder");
            radioButton4.setUserData("Forward");








            // Set the text to all the players in the team.
        }

        @FXML
        private void buttonPressed (ActionEvent ae){

            Button source = (Button) ae.getSource();

            if (source == backButton) {
                try {
                    changeScene(ae, "CoachScene.fxml");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

            if (source == addPlayerButton) {
                addPlayer();
            }

            if (source == removePlayerButton) {
                deletePlayerFromDB();
            }


        }


    // Adding player information to database.
    public void addPlayer() {

        try {
            String enumName = String.valueOf(toggleGroup.getSelectedToggle().getUserData());
            String firstname = firstNameTextField.getText();
            String surname = surNameTextField.getText();

            String username = userNameTextField.getText();
            String password = passwordTextField.getText();


            if (!firstname.isEmpty() && !surname.isEmpty() && !username.isEmpty() && !password.isEmpty()) {
                DBHandler dbHandler = new DBHandler();
                dbHandler.addPlayerToDB(firstname, surname, enumName, username, password, "MalmöFF");
                ControllerGuest viewPlayer = new ControllerGuest();
                viewPlayer.viewPlayers(playersInTheTeamTextArea);

                DataStorage.getInstance().UpdatePlayerStorage();
            }
            else {
                throw new NullPointerException("Everything was not filled in");
            }
        } catch (Exception e) {
            createErrorDialog("Please fill in everything", "Error", "Fill in everything!");
        }
    }
    // Deleting player information from database.
    public void deletePlayerFromDB() {
        try {
            DBHandler dbHandler = new DBHandler();
            int index = Integer.parseInt(removePlayerIDTextField.getText());
            dbHandler.deletePlayerFromDB(index);
            ControllerGuest viewPlayer = new ControllerGuest();
            viewPlayer.viewPlayers(playersInTheTeamTextArea);




        } catch (InputMismatchException ex) {
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("Your input was invalid.");
            dialog.showAndWait();
        }
    }


}
