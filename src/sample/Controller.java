package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

import java.io.IOException;

public class Controller {
    @FXML
    TextField employeesNum;
    TextField patientFirst;
    TextField patientLast;
    TextField patientBirth;
    
    @FXML
    Label errorMessage;

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToMainScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToHealthScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("HealthScene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToPatientScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PatientScene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToPatientScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PatientScene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void switchToPatientScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PatientScene3.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void employeeLogin(ActionEvent event) throws IOException {

        String userID = employeesNum.getText();

        Integer ID;
        try {
            ID = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            errorMessage.setText("Please Enter a Number");
            return;
        }

        //wait for the classes
        if (!ID.equals(112345) && !ID.equals(212345)) {
            errorMessage.setText("Couldn't find your ID");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene2.fxml"));
        root = loader.load();
        HealthWorkController healthWorkController = loader.getController();
        healthWorkController.displayName(ID);

        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

}
