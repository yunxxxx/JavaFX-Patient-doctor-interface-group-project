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
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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
    Label errorMessage;

    @FXML
    TextArea PreviousHealthIssues;

    @FXML
    TextArea PreviousPrescribedMed;

    @FXML
    TextArea ImmunicationHistory;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int workNum;
    private String First;
    private String Last;
    private String BirthdayNum;
    private Integer birthNum;
    private char N_or_D;

    private int patientIndex = 1;

    public void displayName(Integer employeeID, char N_or_D, String First, String Last) throws IOException {
        this.workNum = employeeID;
        this.N_or_D = N_or_D;
        this.First = First;
        this.Last = Last;
        if (N_or_D == 'N') {
            welcomeTex.setText("Welcome Nurse " + First + " " + Last);
            FileWriter nurseInfo = new FileWriter("nurseinfo.txt");
            nurseInfo.write(First + "\n");
            nurseInfo.write(Last + "\n");
            nurseInfo.close();
        } else {
            welcomeTex.setText("Welcome Doctor " + First + " " + Last);
            FileWriter doctorInfo = new FileWriter("doctorinfo.txt");
            doctorInfo.write(First + "\n");
            doctorInfo.write(Last + "\n");
            doctorInfo.close();
        }
    }

    public void lookingForPatient(ActionEvent event) throws IOException {
        this.First = FirstName.getText();
        this.Last = LastName.getText();
        this.BirthdayNum = Birthday.getText();

        try {
            birthNum = Integer.parseInt(BirthdayNum);
        } catch (NumberFormatException e) {
            errorMessage.setText("Please Enter a Number at the birthday");
            return;
        }

        //just use Yuan Bo 000000 as an example patient for now
        if (!searchPatient(First, Last, BirthdayNum)) {
            errorMessage.setText("Couldn't find the patient");
            return;
        }

        if (N_or_D == 'N') {
            FileWriter nurseInfo = new FileWriter("nurseinfo.txt", true);
            nurseInfo.write(First + "\n");
            nurseInfo.write(Last + "\n");
            nurseInfo.write(BirthdayNum + "\n");
            nurseInfo.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene3.fxml"));
            root = loader.load();
            PatientInfoController patientInfoController = loader.getController();
            patientInfoController.displayPatient(First, Last, birthNum);

            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else if (N_or_D == 'D') {
            FileWriter doctorInfo = new FileWriter("doctorinfo.txt", true);
            doctorInfo.write(First + "\n");
            doctorInfo.write(Last + "\n");
            doctorInfo.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("HealthScene5.fxml"));
            root = loader.load();
            HealthWorkController2 healthWorkController2 = loader.getController();
            healthWorkController2.displayInfo(First, Last, BirthdayNum);

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

        String previousHealthIssues = PreviousHealthIssues.getText();
        String previousPrescribedMed = PreviousPrescribedMed.getText();
        String immunicationHistory = ImmunicationHistory.getText();

        FileWriter nurseInfo = new FileWriter("nurseinfo.txt", true);
        nurseInfo.write(previousHealthIssues + "\n");
        nurseInfo.write(previousPrescribedMed + "\n");
        nurseInfo.write(immunicationHistory + "\n");
        nurseInfo.write(allergies + "\n");
        nurseInfo.write(healthConcerns + "\n");
        nurseInfo.write(note + "\n");
        nurseInfo.close();
    }


    public boolean searchPatient(String First, String Last, String Birthday) throws IOException{
        boolean flag = false;

        Scanner fileReader = new Scanner(new File("PatientData.txt"));

        while(fileReader.hasNextLine())
        {
            //grab first name string
            String compF = fileReader.next("[\\S ]+");
            //System.out.println("sc is: " + compF);
            if(compF.equals(First))// && sc.next().equals(Last) && sc.next().equals(Birthday.toString()))
            {
                //grab last name string
                String compL = fileReader.next("[\\S ]+");
                //System.out.println("sc is: " + compL);
                if (compL.equals(Last))
                {
                    // grab birthday string
                    String compB = fileReader.next("[\\S ]+");
                    //System.out.println("sc is: " + compB);
                    if (compB.equals(Birthday.toString()))
                    {
                        // if everything equals, flag is true
                        flag = true;
                        break;
                    }
                    else //go to next patient
                    {
                        fileReader.nextLine();
                        patientIndex++;
                    }
                }
                else //go to next patient
                {
                    fileReader.nextLine();
                    patientIndex++;
                }
            }
            else //go to next patient
            {
                fileReader.nextLine();
                patientIndex++;
            }
            //System.out.println("flag is " + flag);
        }

        fileReader.close();


        return flag;
    }

}
