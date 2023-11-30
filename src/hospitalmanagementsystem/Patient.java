package hospitalmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends User {
    private List<MedicalRecord> medicalRecord;

//    public Patient() {
//        this(firstName, lastName, ID, age, gender, phoneNumber);
//    }

    public Patient(String firstName, String lastName, String ID, int age, char gender, String phoneNumber) {
        super();
        medicalRecord = new ArrayList<>();
    }
  
    public Patient(List<MedicalRecord> medicalRecord, String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
        this.medicalRecord = medicalRecord;
    }
    
    public List<MedicalRecord> viewMedicalRecord() {
        return medicalRecord;
    }
    
    public boolean cancelAppointment(Appointment appointment) {
        // Code to cancel an appointment
        return true; // Return true if the appointment was successfully canceled, false otherwise
    }
    
    public List<Appointment> viewMyAppointments() {
        // Code to retrieve and return the list of appointments for the patient
        return new ArrayList<Appointment>();
    }
    
    public void modifyMyAppointments(Appointment appointment, Date newDate) {
        // Code to modify the date of an appointment
    }
}