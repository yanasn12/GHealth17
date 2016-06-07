package Controllers;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import Entitys.patient;

public class Dispatcher {


public static boolean iDInfo(String input, patient tempinfo)
	{
	String [] parts;
	String quary;
	ArrayList<String> data = new ArrayList<String>();
	quary="pullbykey:people:person_id,"+input;
	data=jdbc.mysqlConnection.ActionMode(quary);
	if(data==null)
		{
			return false;
		}
	else
		{
		parts=data.toString().split(",");
		tempinfo.setPerson_id(parts[1]);
		tempinfo.setFirst_name(parts[2]);
		tempinfo.setLast_name(parts[3]);
		tempinfo.setAddress(parts[4]);
		tempinfo.setEmail(parts[5]);
		tempinfo.setPhone(parts[6]);
		tempinfo.setBirth_date(parts[7]);
		}
	quary="pullBykey:patients:Person_id,"+input;
	data=jdbc.mysqlConnection.ActionMode(quary);
	if(data==null)
		{
			JOptionPane.showMessageDialog(null,"No insurence info was found", "patient",JOptionPane.ERROR_MESSAGE);
			tempinfo.setInsurance_level("-1");
			tempinfo.setInsurance_validity("-1");
			return true;
		}
	else
		{
			parts=data.toString().split(",");
			tempinfo.setInsurance_level(parts[3]);
			tempinfo.setInsurance_validity(parts[4]);
		}
	return true;
	}

public static boolean newPatient(String input, patient tempinfo)
	{
		String [] data=input.split(",");
		String quary="push:people:";
		for(int i=0; i<data.length-4;i++)
		{
			quary=quary+data[i];
			if(i<data.length-5)
			{
				quary=quary+",";
			}
		}
			jdbc.mysqlConnection.ActionMode(quary);
		quary="push:patients:";
		quary=quary+"patient_num,"+patient.patientcount;
		quary=quary+",person_id,"+data[1];
		for(int i=(data.length-4);i<data.length;i++)
			{
				quary=quary+","+data[i];
			}
		quary=quary+",medical_file_num,-1,extensive_clinic_email,-1,leave_date,-1";
		jdbc.mysqlConnection.ActionMode(quary);
		if(iDInfo(data[1],tempinfo)==false)
			{
				return false;
			}
		return true;
	}
public static boolean updateInsurance(String input, patient tempinfo)
	{
		String quary="pullbykey:patients:person_id,";
		ArrayList<String> infoToUpadte = new ArrayList<String>();
		String [] parts=input.split(":");
		parts=parts[2].split(",");
		quary=quary+parts[2];
		
		
		infoToUpadte=jdbc.mysqlConnection.ActionMode(quary);
		parts=infoToUpadte.get(0).split(",");
		
		quary="update:patients:patient_num,"+parts[1]+",insurance_validity,"+parts[3];
		jdbc.mysqlConnection.ActionMode(quary);

		quary="update:patients:patient_num,"+parts[1]+",insurance_validity,"+parts[4];
		jdbc.mysqlConnection.ActionMode(quary);
		
		if(iDInfo(parts[1],tempinfo)==false)
		{
			return false;
		}
	return true;
	}
public static boolean findReff( String reff, patient tempinfo)
	{
		String quary="pullbykey:patients:person_id,"+tempinfo.getPerson_id().toString();
		ArrayList<String> data = new ArrayList<String>();
		String [] parts;
	
	
			data=jdbc.mysqlConnection.ActionMode(quary);
			parts=data.get(0).split(",");
			quary="pullbykey:medical_files:medical_file_num,"+parts[5];
			tempinfo.setMedical_file_num(parts[5]);
			data=jdbc.mysqlConnection.ActionMode(quary);

			parts=data.get(0).split(",");
			quary="pullbykey:references_files:references_file_num,"+parts[3];
			tempinfo.setReferences_file_num(parts[3]);
			data=jdbc.mysqlConnection.ActionMode(quary);
			
			for(int i=0; i<data.size();i++)
			{
				
				String[] temp =data.get(i).split(",");
				if(temp[2].equals(reff))
				{
					tempinfo.setReferral_num(temp[2]);
					return true;
				}	
			}
			
		return false;
	}

public static ArrayList<String> listOfExperts(String input,patient tempinfo)
{
	String quary ;
	ArrayList<String> Doctor =new ArrayList<String>();
	ArrayList<String> Paient_Doctor =new ArrayList<String>();
	ArrayList<String> ListOfDoctorsByDate = new ArrayList<>();
	ArrayList<String> List = new ArrayList<>();
	String [] parts;
	
	/**pulling the kind of doctor we need = expertise_code*/
	quary = "pullbykey:referrals:referral_num,"+ input;
	Doctor=jdbc.mysqlConnection.ActionMode(quary);
	parts=Doctor.get(0).split(",");	
	tempinfo.setExperieCodeInNeed(parts[4]);
	
	/**pulling all of the doctors how have that expertise_code*/
	quary = "pullbykey:experts:expertise_code,"+ tempinfo.getExperieCodeInNeed();
	Doctor=jdbc.mysqlConnection.ActionMode(quary);
	
	
	
	/**pulling the the medical_file_num of the patient to get the appointment_file_num*/
	quary = "pullbykey:medical_files:medical_file_num,"+ tempinfo.getMedical_file_num();
	Paient_Doctor=jdbc.mysqlConnection.ActionMode(quary);
	parts=Paient_Doctor.get(0).split(",");	
	
	/**pulling the appointment that have been with that patient, to get the appointment_num  */
	quary = "pullbykey:appointments_file:appointment_file_num,"+ parts[2];
	Paient_Doctor=jdbc.mysqlConnection.ActionMode(quary);
	for(int i=0; i<Paient_Doctor.size();i++)
		{
			String [] appointmentNumber =Paient_Doctor.get(i).split(",");
			List.add(appointmentNumber[2]);
		}
	
	/**the all appointment of the patient by time*/
	for(int i=0; i<List.size();i++)
		{
			String [] partsOfApointment;
			String toBeEnter;
			quary = "pullbykey:appointments:appointment_num,"+List.get(i);
			Paient_Doctor=jdbc.mysqlConnection.ActionMode(quary);
			partsOfApointment=Paient_Doctor.get(0).split(",");
			toBeEnter=partsOfApointment[4]+","+partsOfApointment[7];
			ListOfDoctorsByDate.add(toBeEnter);
		}
	
	/**exstracting all the doctors with that exsperty*/
	
	bubbleSort(ListOfDoctorsByDate);
	
	
	for(int j=0; j<ListOfDoctorsByDate.size();j++)
	{
		for(int i=0; i<Doctor.size();i++)
		{
		if(Doctor.get(i).split(",")[1].equals(ListOfDoctorsByDate.get(j).split(",")[0])==true)
				{
					Doctor.remove(i);
					Doctor.add(ListOfDoctorsByDate.get(j));
					
				}
		}
	}
//recheck the time.
//		bubbleSort();
//		ListOfDoctorsByDate.addAll();
	
	return Doctor;
}

private static void bubbleSort(ArrayList<String> intArray) 
	{
	    String temp;
	    for(int i=0; i < intArray.size(); i++){
	            for(int j=1; j < (intArray.size()-i); j++){
	                   
	                    if(intArray.get(j-1).equals(intArray.get(j))){
	                            temp = intArray.get(j);
	                            intArray.add(j,intArray.get(i));
	                            intArray.remove(j);
	                            intArray.add(j+1,temp);
	                    }       
	            }
	    }
	}

}
