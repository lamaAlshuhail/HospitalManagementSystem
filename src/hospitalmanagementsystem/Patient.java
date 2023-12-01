package hospitalmanagementsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends User {
    private List<MedicalRecord> medicalRecord;

//    public Patient() {
//        this(firstName, lastName, ID, age, gender, phoneNumber);
//    }

    public Patient(String firstName, String lastName, String ID, int age, char gender, String phoneNumber) {
        super(firstName, lastName, ID,age,gender, phoneNumber);
        medicalRecord = new ArrayList<>();
    }
    
    public Patient (String firstName, String lastName, String ID, int age, String phoneNumber){
             super(firstName, lastName, ID,age, phoneNumber);
            medicalRecord = new ArrayList<>();
    }
  
    public Patient(List<MedicalRecord> medicalRecord, String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
        this.medicalRecord = medicalRecord;
    }

//    public Patient(String firstName, String lastName, String patientID, int age, String phoneNumber) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    
    public List<MedicalRecord> viewMedicalRecord() {
        return medicalRecord;
    }
    
//    public boolean cancelAppointment(Appointment appointment) {
//        // Code to cancel an appointment
//        return true; // Return true if the appointment was successfully canceled, false otherwise
//    }
    
    public List<Appointment> viewMyAppointments() {
        // Code to retrieve and return the list of appointments for the patient
        return new ArrayList<Appointment>();
    }
    private Appointment findAppointmentById(int appointmentId) {
// search through the patient's appointments and return the one with the given ID return new Appointment();}
return new Appointment();}
    
    
    private Date parseDate(String dateString) { // convert a string into a Date object
try {
return new SimpleDateFormat("yyyy-MM-dd").parse(dateString); } catch (ParseException e) {
e.printStackTrace();
return null; }
}

}