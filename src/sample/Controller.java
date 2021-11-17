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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    @FXML
    TextField employeesNum;

    @FXML
    Label errorMessage;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int id;
    private String First;
    private String Last;
    private char N_or_D;

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


        try {
            this.id = Integer.parseInt(userID);
        } catch (NumberFormatException e) {
            errorMessage.setText("Please Enter a Number");
            return;
        }

        //wait for the classes
        if (LoadEmployeeInfo()) {
            errorMessage.setText("Couldn't find your ID");
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene2.fxml"));
        root = loader.load();
        HealthWorkController healthWorkController = loader.getController();
        healthWorkController.displayName(id, N_or_D, First, Last);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    public boolean LoadEmployeeInfo() throws FileNotFoundException {
        File healthWorkerInfo = new File("healthworkerinfo.txt");
        Scanner scan = new Scanner(healthWorkerInfo);
        ArrayList<Integer> empID = new ArrayList<Integer>();
        ArrayList<Character> nurseOrDoctor = new ArrayList<Character>();
        ArrayList<String> empFirstName = new ArrayList<String>();
        ArrayList<String> empLastName = new ArrayList<String>();
        int count = 0;
        while (scan.hasNextLine()) {
            String fileEntry = scan.nextLine();
            String[] fileInfo = fileEntry.split(" ");
            empID.add(Integer.parseInt(fileInfo[0]));
            nurseOrDoctor.add(fileInfo[1].charAt(0));
            empFirstName.add(fileInfo[2]);
            empLastName.add(fileInfo[3]);
            count++;
        }

        for (int i = 0; i < count; i++) {
            if (empID.get(i) == id) {
                N_or_D = nurseOrDoctor.get(i);
                First = empFirstName.get(i);
                Last = empLastName.get(i);
                return false;
            }
        }
        return true;

    }
}
