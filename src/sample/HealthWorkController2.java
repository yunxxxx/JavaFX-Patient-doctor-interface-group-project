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
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class HealthWorkController2 {

    @FXML
    TextArea doctorPTR;

    @FXML
    TextArea doctorMN;

    @FXML
    TextArea doctorNote;

    @FXML
    TextField Weight;

    @FXML
    TextField Height;

    @FXML
    TextField BodyTemp;

    @FXML
    TextField BloodPressure;

    @FXML
    TextField Allergies;

    @FXML
    TextArea HealthConcerns;

    @FXML
    TextArea NurseNote;

    @FXML
    TextArea PreviousHealthIssues;

    @FXML
    TextArea PreviousPrescribedMed;

    @FXML
    TextArea ImmunicationHistory;

    @FXML
    Label NameDisplay;

    @FXML
    Label PatientNameDisplay;

    @FXML
    Label infoName;

    @FXML
    TextField Birthday;

    @FXML
    TextField Address;

    @FXML
    TextField Phone;

    @FXML
    TextField Email;

    @FXML
    TextArea Massage;


    private Stage stage;
    private Scene scene;
    private Parent root;

    private String First;
    private String Last;
    private String BirthdayNum;

    private String nurseFirst;
    private String nurseLast;
    private String weight;
    private String height;
    private String bodyTemperature;
    private String bloodPressure;
    private String allergies;
    private String healthConcerns;
    private String note;
    private String previousHealthIssues;
    private String previousPrescribedMed;
    private String immunicationHistory;
    private String address;
    private String phone;
    private String email;
    private String messageTitle;
    private String message;

    private int patientIndex = 1;

    public void displayInfo(String First, String Last, String BirthdayNum) throws IOException {
        this.First = First;
        this.Last = Last;
        this.BirthdayNum = BirthdayNum;

        patientIndex = 1;
        searchPatient(First, Last, BirthdayNum);
        setUpInfo();

        infoName.setText(First + " " + Last + "'s personal information");
        Birthday.setText(BirthdayNum);
        Address.setText(address);
        Phone.setText(phone);
        Email.setText(email);
        Massage.setText(messageTitle + "\n\n" + message);

        if (loadPatientInfo()) {
            String nameText = "Patient Name " + First + " " + Last + " Recorded by Nurse " + nurseFirst + " " + nurseLast;
            NameDisplay.setText(nameText);
            PatientNameDisplay.setText("Patient Name " + First + " " + Last);
            Weight.setText(weight);
            Height.setText(height);
            BodyTemp.setText(bodyTemperature);
            BloodPressure.setText(bloodPressure);
            Allergies.setText(allergies);
            HealthConcerns.setText(healthConcerns);
            NurseNote.setText(note);
            PreviousHealthIssues.setText(previousHealthIssues);
            PreviousPrescribedMed.setText(previousPrescribedMed);
            ImmunicationHistory.setText(immunicationHistory);
        }
        else {
            String nameText = First + " " + Last + "'s information haven't been recorded by any nurse";
            NameDisplay.setText(nameText);
            PatientNameDisplay.setText(nameText);
            NameDisplay.setTextFill(Color.RED);
            PatientNameDisplay.setTextFill(Color.RED);
        }
        searchPatient(First, Last, BirthdayNum);

    }

    public void logout(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void doctorRecordInformation(ActionEvent event) throws IOException {
        String ptr = doctorPTR.getText();
        String mn = doctorMN.getText();
        String dnote = doctorNote.getText();

        FileWriter doctorInfo = new FileWriter("doctorinfo.txt", true);
        doctorInfo.write(ptr + "\n");
        doctorInfo.write(mn + "\n");
        doctorInfo.write(dnote + "\n");
        doctorInfo.close();
    }


    public boolean loadPatientInfo() throws FileNotFoundException {
        File nurseinfo = new File("nurseinfo.txt");
        Scanner scan = new Scanner(nurseinfo);

        if (scan.hasNext()) {
            nurseFirst = scan.nextLine();
            nurseLast = scan.nextLine();
            String firstName = scan.nextLine();
            String lastName = scan.nextLine();
            String birthDay = scan.nextLine();
            if (firstName.equals(this.First) && lastName.equals(this.Last) && birthDay.equals(this.BirthdayNum)) {
                this.weight = scan.next();
                this.height = scan.next();
                this.bodyTemperature = scan.next();
                this.bloodPressure = scan.next();
                scan.nextLine();

                this.previousHealthIssues = scan.nextLine();
                this.previousPrescribedMed = scan.nextLine();
                this.immunicationHistory = scan.nextLine();

                this.allergies = scan.nextLine();
                this.healthConcerns = scan.nextLine();
                this.note = scan.nextLine();
                return true;
            }
        }

        return false;
    }

    public void setUpInfo() throws FileNotFoundException {
        Scanner fileReader = new Scanner(new File("PatientData.txt")).useDelimiter("\t");
        for (int i = 0; i < patientIndex; i++)
        {
            if(fileReader.hasNextLine())
            {
                fileReader.nextLine();
            }
        }
        fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        address = fileReader.next("[\\S ]+");
        phone = fileReader.next("[\\S ]+");
        email = fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        fileReader.next("[\\S ]+");
        messageTitle = fileReader.next("[\\S ]+");
        message = fileReader.next("[\\S ]+");
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
