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
        DBHandler dbHandler = new DBHandler();

        DataStorage.getInstance().UpdatePlayerStorage();

        choiceBox.getItems().add("Guest");
        choiceBox.getItems().add("Coach");
        choiceBox.getItems().add("Player");




    }

    @FXML
    private void loginButton(ActionEvent ae) {

        if (choiceBox.getValue().equals("Coach")) {
            try {
                 //throwExceptionsCoach();
            changeScene(ae,"CoachScene.fxml");
            } catch (IOException var7) {
                var7.printStackTrace();
            } catch (NullPointerException e) {
                Alert dialog = new Alert(Alert.AlertType.INFORMATION);
                dialog.setTitle("Error");
                dialog.setHeaderText("Error:");
                dialog.setContentText("Sorry, incorrest password or username");
                dialog.showAndWait();
            }

        } else if (choiceBox.getValue().equals("Player")) {
            try {
                changeScene(ae,"PlayerScene.fxml");

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

            try {
                changeScene(ae,"GuestScene.fxml");
            } catch (IOException var7) {
                var7.printStackTrace();
            }catch (NullPointerException e) {
                System.out.println("hi");
            }


        }
    }
    @FXML
    private void handleMenuItemEdit() {
        if (!textUsername.getText().isEmpty() || !textPassword.getText().isEmpty() || !choiceBox.getValue().equals(null)) {
            textUsername.clear();
            textPassword.clear();
            choiceBox.setValue(null);

        }


    }

    //Method for changing scene
    public void changeScene (ActionEvent event, String fxml) throws IOException{
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    public Alert createConformationDialog(String titleText,String headerText){
        Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setTitle(titleText);
        dialog.setHeaderText(headerText);
        dialog.getButtonTypes().set(0,ButtonType.YES);
        dialog.getButtonTypes().set(1,ButtonType.NO);
        dialog.showAndWait();
        return dialog;
    }
    public void createInformationDialog(String titleText,String headerText,String contextText){
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle(titleText);
        dialog.setHeaderText(headerText);
        dialog.setContentText(contextText);
        dialog.showAndWait();
    }



   // @FXML
   // public void throwExceptionsCoach() {
     //   DBHandler dbHandler = new DBHandler();
     //   boolean tempo = dbHandler.handleLoginCoach(textUsername.getText(),textPassword.getText());
      //  if(tempo == false) {

          // throw new NullPointerException();
      //  }
      //  else {
        //    System.out.println("hi");
        }

   // } //









