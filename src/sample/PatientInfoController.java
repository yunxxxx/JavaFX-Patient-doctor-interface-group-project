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

import java.io.*;
import java.util.*;


public class PatientInfoController {
    @FXML
    TextField patientFirst;
    
    @FXML
    TextField patientLast;
    
    @FXML
    TextField patientBirth;
    
    @FXML
    Label errorMessage;


    private Stage stage;
    private Scene scene;
    private Parent root;

    public void enter(ActionEvent event) throws IOException {
    	String First = patientFirst.getText();
    	String Last = patientLast.getText();
    	String BirthdayNum = patientBirth.getText();
    	Integer birthday;
    	try {
            birthday = Integer.parseInt(BirthdayNum);
        } catch (NumberFormatException e) {
            errorMessage.setText("Please Enter a Number at the birthday (XXXXXX)");
            return;
        }
    	
    	boolean found;
    	found = searchPatient(First, Last, birthday);
    	
    	if(found) {
    		root = FXMLLoader.load(getClass().getResource("PatientScene3.fxml"));
    		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        	scene = new Scene(root);
        	stage.setScene(scene);
        	stage.show();
    	}
    	
    	else {
    		return;
    	}
    }


    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    
    public void signUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PatientScene2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void finishSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("PatientScene1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public boolean searchPatient(String First, String Last, Integer Birthday) throws IOException{
    	if(First.equals("Yuan") && Last.equals("Bo") && Birthday == 0000000)
    		return true;
    	else 
    		errorMessage.setText("Patient does not exist");
    		return false;
    }

}



