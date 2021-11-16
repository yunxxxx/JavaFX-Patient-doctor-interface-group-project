package sample;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;


public class PatientInfoController2 {
    @FXML
    Label patientName;


    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void saveChange(ActionEvent event) throws IOException {
    }
    
    public void sendMessage(ActionEvent event) throws IOException {
    }

}