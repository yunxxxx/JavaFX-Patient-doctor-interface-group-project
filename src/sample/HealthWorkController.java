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

import java.io.File;
import java.io.FileWriter;
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
    TextArea patientAllergies;
    
    @FXML
    TextArea patientHealthConcerns;
    
    @FXML
    TextArea patientNote;
    
    @FXML
    TextArea doctorPTR;
    
    @FXML
    TextArea doctorMN;
    
    @FXML
    TextArea doctorNote;

    @FXML
    Label errorMessage;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int workNum;
    private String First;
    private String Last;
    private char N_or_D;
    public void displayName(Integer employeeID, char N_or_D, String First, String Last) throws IOException {
        this.workNum = employeeID;
        this.N_or_D = N_or_D;
        this.First = First;
        this.Last = Last;
        if(N_or_D == 'N') {
        	welcomeTex.setText("Welcome Nurse " + First + " " + Last);
        	FileWriter nurseInfo = new FileWriter("C:\\Users\\bloxx\\OneDrive\\Documents\\health\\nurseinfo.txt");
        	nurseInfo.write(First + "\n");
        	nurseInfo.write(Last + "\n");
        	nurseInfo.close();
        }else {
        	welcomeTex.setText("Welcome Doctor " + First + " " + Last);
        	FileWriter doctorInfo = new FileWriter("C:\\Users\\bloxx\\OneDrive\\Documents\\health\\doctorinfo.txt");
        	doctorInfo.write(First + "\n");
        	doctorInfo.write(Last + "\n");
        	doctorInfo.close();
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

        if (N_or_D == 'N') {
        	FileWriter nurseInfo = new FileWriter("C:\\Users\\bloxx\\OneDrive\\Documents\\health\\nurseinfo.txt", true);
        	nurseInfo.write(First + "\n");
        	nurseInfo.write(Last + "\n");
        	nurseInfo.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene3.fxml"));
            root = loader.load();
            PatientInfoController patientInfoController = loader.getController();
            patientInfoController.displayPatient(First, Last, BirthdayID);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (N_or_D == 'D') {
        	FileWriter doctorInfo = new FileWriter("C:\\Users\\bloxx\\OneDrive\\Documents\\health\\doctorinfo.txt", true);
        	doctorInfo.write(First + "\n");
        	doctorInfo.write(Last + "\n");
        	doctorInfo.close();
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
    	String allergies = patientAllergies.getText();
    	String healthConcerns = patientHealthConcerns.getText();
    	String note = patientNote.getText();
    	
    	FileWriter nurseInfo = new FileWriter("C:\\Users\\bloxx\\OneDrive\\Documents\\health\\nurseinfo.txt", true);
    	nurseInfo.write(allergies + "\n");
    	nurseInfo.write(healthConcerns + "\n");
    	nurseInfo.write(note + "\n");
    	nurseInfo.close();
    }
    
    public void doctorRecordInformation(ActionEvent event) throws IOException {
    	String ptr = doctorPTR.getText();
    	String mn = doctorMN.getText();
    	String dnote = doctorNote.getText();
    	
    	FileWriter doctorInfo = new FileWriter("C:\\Users\\bloxx\\OneDrive\\Documents\\health\\doctorinfo.txt", true);
    	doctorInfo.write(ptr + "\n");
    	doctorInfo.write(mn + "\n");
    	doctorInfo.write(dnote + "\n");
    	doctorInfo.close();
    }

}
