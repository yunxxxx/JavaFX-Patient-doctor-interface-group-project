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


public class PatientInfoController2 {
    @FXML
    Label welcome; 
    @FXML
    TextField savedFirst;
    @FXML
    TextField savedLast;
    @FXML
    TextField savedBirth;
    @FXML
    TextField savedAddr;
    @FXML
    TextField savedNum;
    @FXML
    TextField savedEmail;
    @FXML
    TextField savedPCN;
    @FXML
    TextField savedRelation;
    @FXML
    TextField savedCN;
    @FXML
    TextField savedInsurance;
    @FXML
    TextField savedMemId;
    @FXML
    TextField savedGN;
    @FXML
    TextField subject;
    @FXML
    TextArea message;
    
    private int updatePatientNum = 1;
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void welcomeName(String fullName)
    {
    	welcome.setText("Welcome " + fullName);
    }
    

    public void setUpInputs(String f, String l, String b, String addr, String num, String e, String pcn, String r, String cn, String i, String m, String gn)
    {
    	savedFirst.setText(f);
    	savedLast.setText(l);
    	savedBirth.setText(b);
    	savedAddr.setText(addr);
    	savedNum.setText(num);
    	savedEmail.setText(e);
    	savedPCN.setText(pcn);
    	savedRelation.setText(r);
	  	savedCN.setText(cn);
	  	savedInsurance.setText(i);
	  	savedMemId.setText(m);
	  	savedGN.setText(gn);
    }
    
    public void patientNum(int num)
    {
    	updatePatientNum = num;
    }
    
    public void logOut(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("MainScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void saveChange(ActionEvent event) throws IOException 
    {
    	updatePatientNum = 1;
    	//to find patient in text file
    	if (searchPatient(savedFirst.getText().toString(), savedLast.getText().toString(), savedBirth.getText().toString()))
    	{
    		int index =  updatePatientNum;  
    		Scanner fileReader = new Scanner(new File("PatientData.txt")); 
        	for (int i = 0; i < updatePatientNum-1; i++)
        	{
        		if(fileReader.hasNextLine())
        		{
        			fileReader.nextLine();
        		}
        	}
        	fileReader.close();
    	}
   	
        int index = updatePatientNum;	
    	String newInfo = savedFirst.getText().toString() + "\t" +
    						savedLast.getText().toString() + "\t" +
    						savedBirth.getText().toString() + "\t" +
    						savedAddr.getText().toString() + "\t" +
    						savedNum.getText().toString() + "\t" +
    						savedEmail.getText().toString() + "\t" +
    						savedPCN.getText().toString() + "\t" +
    						savedRelation.getText().toString() + "\t" +
    						savedCN.getText().toString() + "\t" +
    						savedInsurance.getText().toString() + "\t" +
    						savedMemId.getText().toString() + "\t" +
    						savedGN.getText().toString() + "\t";
    	
    	//remove patient info
    	delete(index+1);

    	//add in new info to text file
    	try(BufferedWriter bf = new BufferedWriter(new FileWriter("PatientData.txt", true)))
    	{
    		bf.write(newInfo);
    	}
    	catch (IOException ex)
    	{
    		
    	}
    	
    
    	
    	
    }
    
    //to delete a patient 
    void delete(int startline)
	{
		try
		{
			BufferedReader br=new BufferedReader(new FileReader("PatientData.txt"));
 
			//String buffer to store contents of the file
			StringBuffer sb=new StringBuffer("");
 
			//Keep track of the line number
			int linenumber=1;
			String line;
 
			while((line=br.readLine())!=null)
			{
				//Store each valid line in the string buffer
				if(linenumber<startline||linenumber>=startline+1)
					sb.append(line+"\n");
				linenumber++;
			}
			br.close();
 
			FileWriter fw=new FileWriter(new File("PatientData.txt"));
			
			//Write entire string buffer into the file
			fw.write(sb.toString());
			fw.close();
		}
		catch (Exception e)
		{
			System.out.println("something went wrong");
		}
	}
    
    public void sendMessage(ActionEvent event) throws IOException {
    	
    	updatePatientNum = 1;
    	//to find patient in text file
    	if (searchPatient(savedFirst.getText().toString(), savedLast.getText().toString(), savedBirth.getText().toString()))
    	{
    		int index =  updatePatientNum;  
    		@SuppressWarnings("resource")
			Scanner fileReader = new Scanner(new File("PatientData.txt")).useDelimiter("\t"); 
        	for (int i = 0; i < updatePatientNum-1; i++)
        	{
        		if(fileReader.hasNextLine())
        		{
        			fileReader.nextLine();
        		}
        	}
        	fileReader.nextLine();
        	
        	int numInputs = 12;
        	int i = 0; 
        	String rewrite = "";
        	while(i < numInputs)
        	{
        		System.out.println(i);
        		String text = fileReader.next("[\\S ]+");
        		rewrite = rewrite + text + "\t";
        		i++;
        	}
        	
        	rewrite = rewrite + "Subject: " + subject.getText().toString() + "\tMessage: " + message.getText().toString() + "\t";
        	
        	
//        	String newInfo = fileReader.nextLine() + 
//        						"Subject: " + subject.getText().toString() + "\tMessage: " + message.getText().toString() + "\t";
        	
        	//remove patient info
//        	if(index == 1)
//        	{
//        		index++;
//        	}
        	delete(index+1);

        	//add in new info to text file
        	try(BufferedWriter bf = new BufferedWriter(new FileWriter("PatientData.txt", true)))
        	{
        		bf.write(rewrite);
        	}
        	catch (IOException ex)
        	{
        		
        	}
        	
        	fileReader.close();
    	}
    	
    	

    	//reset text boxes
    	subject.setText("");
    	message.setText("");
    }
    
    
    public boolean searchPatient(String First, String Last, String Birthday) throws IOException{
    	boolean flag = false;
    	
    	Scanner fileReader = new Scanner(new File("PatientData.txt")); 
    	
    	while(fileReader.hasNextLine())
    	{
    		//grab first name string
    		String compF = fileReader.next("[\\S ]+");
    		if(compF.equals(First))
    		{
    			//grab last name string
    			String compL = fileReader.next("[\\S ]+");
    			if (compL.equals(Last)) 
    			{
    				// grab birthday string
    				String compB = fileReader.next("[\\S ]+");
        			if (compB.equals(Birthday.toString()))
        			{
        				// if everything equals, flag is true
        				flag = true;
            			break;
        			}
        			else //go to next patient
        			{
        				fileReader.nextLine();
        				updatePatientNum++;
        			} 				
    			}
    			else //go to next patient
    			{
    				fileReader.nextLine();
    				updatePatientNum++;
    			}
    		}
    		else //go to next patient
    		{
    			fileReader.nextLine();
    			updatePatientNum++;
    		}
    		//System.out.println("flag is " + flag);
    	}
    	
    	fileReader.close();
    	
    	
    	return flag;
    }


}