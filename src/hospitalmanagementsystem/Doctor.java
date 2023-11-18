package hospitalmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class Doctor extends User {
    private String speciality;

    public Doctor() {
        super();
    }
    
    public Doctor(String speciality, String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
        this.speciality = speciality;
    }
    
    public String getSpeciality() {
        return speciality;
    }
    
    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
    
    public void prescribeMedicine(Medicine medicine, Patient patient) {
        // Code to prescribe medicine to a patient
    }
    
    public List<Medicine> showPrescribedMedicine(Patient patient) {
        // Code to retrieve and return the list of prescribed medicines for a patient
        return new ArrayList<Medicine>();
    }
    
    public List<MedicalRecord> viewPatientRecord(Patient patient) {
        // Code to retrieve and return the list of medical records for a patient
        return new ArrayList<MedicalRecord>();
    }
    
    public void writeMedicalNotes(MedicalRecord medicalRecord) {
        // Code to write medical notes to a medical record
    }
    
    public void addMedicalRecord(MedicalRecord record, Patient patient) {
        // Code to add a medical record to a patient's records
    }
}