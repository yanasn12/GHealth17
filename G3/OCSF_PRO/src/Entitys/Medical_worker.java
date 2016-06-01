package Entitys;

public class Medical_worker {
	
private int worker_num;
private String password;
private int person_id;
private boolean is_connected;
private int clinic_num;
private String worker_type;

public Medical_worker()
{
}
/**Gets and sets*/
public int getWorker_num() {
	return worker_num;
}
public void setWorker_num(int worker_num) {
	this.worker_num = worker_num;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public int getPerson_id() {
	return person_id;
}
public void setPerson_id(int person_id) {
	this.person_id = person_id;
}
public boolean isIs_connected() {
	return is_connected;
}
public void setIs_connected(boolean is_connected) {
	this.is_connected = is_connected;
}
public int getClinic_num() {
	return clinic_num;
}
public void setClinic_num(int clinic_num) {
	this.clinic_num = clinic_num;
}
public String getWorker_type() {
	return worker_type;
}
public void setWorker_type(String worker_type) {
	this.worker_type = worker_type;
}

}
