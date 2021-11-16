package sample;

import javafx.event.ActionEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
    TextField patientWeight;
    
    @FXML
    TextField patientHeight;
    
    @FXML
    TextField patientBodyTemp;
    
    @FXML
    TextField patientBloodPressure;
    
    @FXML
    Label errorMessage;

    @FXML
    Label patientName;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int Birthday;

    public void displayPatient(String First, String Last, Integer Birthday) {
        this.Birthday = Birthday;
        patientName.setText("Enter " + First + " " + Last + "'s basic information");
    }

    public void enterHealth4(ActionEvent event) throws IOException {
    	String weight = patientWeight.getText();
    	String height = patientHeight.getText();
    	String bt = patientBodyTemp.getText();
    	String bp = patientBloodPressure.getText();
    	
    	FileWriter nurseInfo = new FileWriter("C:\\Users\\bloxx\\OneDrive\\Documents\\health\\nurseinfo.txt", true);
    	nurseInfo.write(weight + " ");
    	nurseInfo.write(height + " ");
    	nurseInfo.write(bt + " ");
    	nurseInfo.write(bp + "\n");
    	nurseInfo.close();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene4.fxml"));
    	root = loader.load();
   //   root = FXMLLoader.load(getClass().getResource("HealthScene4.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
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



