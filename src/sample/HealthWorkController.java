package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HealthWorkController {

    @FXML
    Label welcomeTex;

    @FXML
    TextField FirstName;

    @FXML
    TextField LastName;

    @FXML
    TextField Birthday;

    @FXML
    Label errorMessage;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int workNum;

    public void displayName(Integer employeeID) {
        this.workNum = employeeID;
        if (employeeID.equals(112345)) {
            welcomeTex.setText("Welcome Nurse Vi-Anh Hoang");
        }
        if (employeeID.equals(212345)) {
            welcomeTex.setText("Welcome Doctor Afsana Salahudeen");
        }
    }

    public void lookingForPatient(ActionEvent event) throws IOException {
        String First = FirstName.getText();
        String Last = LastName.getText();
        String BirthdayNum = Birthday.getText();

        Integer BirthdayID;
        try {
            BirthdayID = Integer.parseInt(BirthdayNum);
        } catch (NumberFormatException e) {
            errorMessage.setText("Please Enter a Number at the birthday");
            return;
        }

        //just use Yuan Bo 000000 as an example patient for now
        if (!BirthdayID.equals(000000) && !First.equals("Yuan") && !Last.equals("Bo")) {
            errorMessage.setText("Couldn't find the patient");
            return;
        }

        if (workNum == 112345) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene3.fxml"));
            root = loader.load();
            PatientInfoController patientInfoController = loader.getController();
            patientInfoController.displayPatient(First, Last, BirthdayID);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (workNum == 212345) {
            root = FXMLLoader.load(getClass().getResource("HealthScene5.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void recordInformation(ActionEvent event) throws IOException {

    }

}
