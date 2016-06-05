package Entitys;



public class ClinicManeger {
	private int WeekNum;
	private float weekly_patient_amount_avg;
	private float weekly_patient_amount_max;
	private float weekly_patient_amount_min;
	private float weekly_patient_amount_deviation;// סטיית תקן
	private float weekly_waiting_time_avg;
	private float weekly_waiting_time_max;
	private float weekly_waiting_time_min;
	private float weekly_waiting_time_deviation;
	private int clinic_num;
	private int whole_partial; //מלא- 0         חלקית- 1

	public ClinicManeger() {  /// בנאי
	}
	
	
	public float getAVGWaitingTimeForService() {
		return weekly_patient_amount_avg;
	}
	
	public float getMaxWaitingTimeForService() {
		return weekly_patient_amount_max;
	}
	
	public float getMinWaitingTimeForService() {
		return weekly_patient_amount_min;
	}
	
	public float getStandardDiviationWaitingTimeForService() {
		return weekly_patient_amount_deviation;
	}
	
	public float getAVGWaitingTimeForAppointment() {
		return weekly_waiting_time_avg;
	}
	
	public float getMaxWaitingTimeForAppointment() {
		return  weekly_waiting_time_max;
	}
	
	public float getMinWaitingTimeForAppointment() {
		return weekly_waiting_time_min;
		}
	
	public float getStandardDiviationWaitingTimeForAppointment() {
		return weekly_waiting_time_deviation;
		
	}	
	
	
}
