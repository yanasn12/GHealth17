package Entitys;

public class patient {

/**full patient data*/
private String patient_num;
private String person_id;
private String insurance_level;
private String insurance_validity;
private String medical_file_num;
private String extensive_clinic_email;
private String first_name;
private String last_name;
private String address;
private String email;
private String phone;
private String birth_date;
private String references_file_num;
private String experieCodeInNeed;




public void newClient()
{
	patient_num=Integer.toString(patientcount);
	patientcount++;
}

/**gettring and setring*/

public String getExperieCodeInNeed() {
	return experieCodeInNeed;
}


public void setExperieCodeInNeed(String experieCodeInNeed) {
	this.experieCodeInNeed = experieCodeInNeed;
}


public String getReferences_file_num() {
	return references_file_num;
}


public void setReferences_file_num(String references_file_num) {
	this.references_file_num = references_file_num;
}

public String getReferral_num() {
	return referral_num;
}

public void setReferral_num(String referral_num) {
	this.referral_num = referral_num;
}
private String referral_num;

public static int patientcount=0;



public String getPatient_num() {
	return patient_num;
}
public void setPatient_num(String patient_num) {
	this.patient_num = patient_num;
}
public String getPerson_id() {
	return person_id;
}
public void setPerson_id(String person_id) {
	this.person_id = person_id;
}
public String getInsurance_level() {
	return insurance_level;
}
public void setInsurance_level(String insurance_level) {
	this.insurance_level = insurance_level;
}
public String getInsurance_validity() {
	return insurance_validity;
}
public void setInsurance_validity(String insurance_validity) {
	this.insurance_validity = insurance_validity;
}
public String getMedical_file_num() {
	return medical_file_num;
}
public void setMedical_file_num(String medical_file_num) {
	this.medical_file_num = medical_file_num;
}
public String getExtensive_clinic_email() {
	return extensive_clinic_email;
}
public void setExtensive_clinic_email(String extensive_clinic_email) {
	this.extensive_clinic_email = extensive_clinic_email;
}
public String getFirst_name() {
	return first_name;
}
public void setFirst_name(String first_name) {
	this.first_name = first_name;
}
public String getLast_name() {
	return last_name;
}
public void setLast_name(String last_name) {
	this.last_name = last_name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhone() {
	return phone;
}
public void setPhone(String phone) {
	this.phone = phone;
}
public String getBirth_date() {
	return birth_date;
}
public void setBirth_date(String birth_date) {
	this.birth_date = birth_date;
}


}
