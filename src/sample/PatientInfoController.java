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
	TextField patientAddr;

	@FXML
	TextField patientNum;

	@FXML
	TextField patientEmail;

	@FXML
	TextField patientPCN;

	@FXML
	TextField patientRelation;

	@FXML
	TextField patientCN;

	@FXML
	TextField patientInsurance;

	@FXML
	TextField patientMemId;

	@FXML
	TextField patientGN;

	@FXML
	Label errorMessage;

	@FXML
	Label patientName;

	@FXML
	TextField patientWeight;

	@FXML
	TextField patientHeight;

	@FXML
	TextField patientBodyTemp;

	@FXML
	TextField patientBloodPressure;

	private Stage stage;
	private Scene scene;
	private Parent root;

	private int Birthday;

	private int patientIndex = 1;

	public void getAddr(String first, String last, String Birth) throws IOException
	{
		patientIndex = 1;
		Scanner fileReader = new Scanner(new File("PatientData.txt")).useDelimiter("\t");
		if (searchPatient(first, last, Birth))
		{
			for(int i = 0; i < 4; i++)
			{
				if(fileReader.hasNextLine())
				{
					fileReader.nextLine();
				}
			}

			System.out.println(fileReader.next("[\\S ]+"));
		}
		fileReader.close();

	}


	public void newPatient() throws IOException {

	}


	public void displayPatient(String First, String Last, Integer Birthday) {
		this.Birthday = Birthday;
		patientName.setText("Enter" + First + " " + Last + "'s basic information");
	}

	public void enterHealth4(ActionEvent event) throws IOException {
		String weight = patientWeight.getText();
		String height = patientHeight.getText();
		String bt = patientBodyTemp.getText();
		String bp = patientBloodPressure.getText();

		FileWriter nurseInfo = new FileWriter("nurseinfo.txt", true);
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
		patientIndex = 1;
		String First = patientFirst.getText();
		String Last = patientLast.getText();
		String BirthdayNum = patientBirth.getText();
		Integer birthday;
		try {
			birthday = Integer.parseInt(BirthdayNum);
		} catch (NumberFormatException e) {
			errorMessage.setText("Please Enter Number As: (XXXXXX)");
			return;
		}

		boolean found;
		found = searchPatient(First, Last, BirthdayNum);

		if(found) {
			// set up next page
			String fullName = patientFirst.getText() + " " + patientLast.getText();

			FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientScene3.fxml"));
			root = loader.load();
			PatientInfoController2 info = loader.getController();

			info.welcomeName(fullName);

			//go through text file and get info
			@SuppressWarnings("resource")
			Scanner fileReader = new Scanner(new File("PatientData.txt")).useDelimiter("\t");
			for (int i = 0; i < patientIndex; i++)
			{
				if(fileReader.hasNextLine())
				{
					fileReader.nextLine();
				}
			}

			String tempF = fileReader.next("[\\S ]+");
			String tempL = fileReader.next("[\\S ]+");
			String tempB = fileReader.next("[\\S ]+");
			String tempAddr = fileReader.next("[\\S ]+");
			String tempNum = fileReader.next("[\\S ]+");
			String tempE = fileReader.next("[\\S ]+");
			String tempPcn = fileReader.next("[\\S ]+");
			String tempR = fileReader.next("[\\S ]+");
			String tempCn = fileReader.next("[\\S ]+");
			String tempI = fileReader.next("[\\S ]+");
			String tempM = fileReader.next("[\\S ]+");
			String tempGn = fileReader.next("[\\S ]+");

			fileReader.close();

			// update fields
			info.setUpInputs(tempF, tempL, tempB, tempAddr, tempNum, tempE, tempPcn, tempR, tempCn, tempI, tempM, tempGn);

			//pass in patient index
			info.patientNum(patientIndex);

			//root = FXMLLoader.load(getClass().getResource("PatientScene3.fxml"));		DO NOT USE
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();

		}
		else {
			errorMessage.setText("Invalid Patient Login");
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

		try(BufferedWriter bf = new BufferedWriter(new FileWriter("PatientData.txt", true)))
		{
			bf.newLine();
			bf.write(patientFirst.getText().toString() + "\t");
			bf.write(patientLast.getText().toString() + "\t");
			bf.write(patientBirth.getText().toString() + "\t");
			bf.write(patientAddr.getText().toString() + "\t");
			bf.write(patientNum.getText().toString() + "\t");
			bf.write(patientEmail.getText().toString() + "\t");
			bf.write(patientPCN.getText().toString() + "\t");
			bf.write(patientRelation.getText().toString() + "\t");
			bf.write(patientCN.getText().toString() + "\t");
			bf.write(patientInsurance.getText().toString() + "\t");
			bf.write(patientMemId.getText().toString() + "\t");
			bf.write(patientGN.getText().toString() + "\t");
			bf.write("No Subject Line \t");
			bf.write("No Message \t");


		}
		catch (IOException ex)
		{

		}


		root = FXMLLoader.load(getClass().getResource("PatientScene1.fxml"));
		stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
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


