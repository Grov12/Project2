package sample;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    @FXML private TextArea playersInTheTeamTextArea;
    @FXML private TextField removePlayerIDTextField;
    @FXML private TextField firstNameTextField;
    @FXML private TextField surNameTextField;
    @FXML private TextField positionTextField;
    @FXML private TextField userNameTextField;
    @FXML private TextField passwordTextField;
    @FXML private Button addPlayerButton;
    @FXML private Button removePlayerButton;
    @FXML private Button backButton;


    @Override
    public void initialize(URL location, ResourceBundle resources)  {

        ControllerGuest viewPlayer = new ControllerGuest();
        viewPlayer.viewPlayers(playersInTheTeamTextArea);




        // Set the text to all the players in the team.
    }

    @FXML
    private void buttonPressed(ActionEvent ae){

        Button source = (Button) ae.getSource();

        if (source == backButton){
            try {
                changeScene(ae , "CoachScene.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        if (source == addPlayerButton){
            addPlayer();
        }

        if (source == removePlayerButton){
            try {
                DBHandler dbHandler = new DBHandler();
                int index = Integer.parseInt(removePlayerIDTextField.getText());
                dbHandler.deletePlayerFromDB(index);

            } catch (InputMismatchException ex){
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("Error");
                dialog.setHeaderText("Error:");
                dialog.setContentText("Your input was invalid.");
                dialog.showAndWait();
            }
        }


    }

    public void addPlayer(){

        try {
            String firstname = firstNameTextField.getText();
            String surname = surNameTextField.getText();
            String position = String.valueOf(Player.Position.valueOf(positionTextField.getText()));
            String username = userNameTextField.getText();
            String password = passwordTextField.getText();


            DBHandler dbHandler = new DBHandler();
            dbHandler.addPlayerToDB(firstname,surname,position,username,password,"MalmöFF");

            DataStorage.getInstance().UpdatePlayerStorage();

            ControllerGuest viewPlayer = new ControllerGuest();
            viewPlayer.viewPlayers(playersInTheTeamTextArea);


        } catch (NullPointerException ex){
            Alert dialog = new Alert(Alert.AlertType.ERROR);
            dialog.setTitle("Error");
            dialog.setHeaderText("Error:");
            dialog.setContentText("You did not enter all the required information.");
            dialog.showAndWait();
        }



    }



}
