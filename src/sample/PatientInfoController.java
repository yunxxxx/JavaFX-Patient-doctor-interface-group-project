package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientInfoController {
    @FXML
    Label patientName;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private int Birthday;

    public void displayPatient(String First, String Last, Integer Birthday) {
        this.Birthday = Birthday;
        patientName.setText("Enter" + First + " " + Last + "'s basic information");
    }

    public void BasicInfo(ActionEvent event) throws IOException {
//        String weight = FirstName.getText();
//        String height = LastName.getText();
//        String bodyTemp = Birthday.getText();
//        String bodyPres = Birthday.getText();
//
//        Integer weight;
//        try {
//            weight = Integer.parseInt(BirthdayNum);
//        } catch (NumberFormatException e) {
//            errorMessage.setText("Please Enter a Number at the birthday");
//            return;
//        }
//
//        //just use Yuan Bo 000000 as an example patient for now
//        if (!BirthdayID.equals(000000) && !First.equals("Yuan") && !Last.equals("Bo")) {
//            errorMessage.setText("Couldn't find the patient");
//            return;
//        }

//        FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene3.fxml"));
//        root = loader.load();
//        PatientInfoController patientInfoController = loader.getController();
//        patientInfoController.displayPatient(First, Last, BirthdayID);

        root = FXMLLoader.load(getClass().getResource("HealthScene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void enter(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HealthScene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
