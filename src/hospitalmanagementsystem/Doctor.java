package hospitalmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Doctor extends User {
//    private String speciality;
private List<Medicine> prescribedMedicine;
private List<MedicalRecord> medicalRecord;

    //    public Doctor(String speciality, String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
//        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
//        this.speciality = speciality;
//    }
    
    public Doctor(String ID, String password, String type){
        super(ID, password, type);
        prescribedMedicine = new ArrayList<>();
        medicalRecord= new ArrayList<>();


    }
    public Doctor(String ID){
        super(ID);
        prescribedMedicine = new ArrayList<>();
                medicalRecord= new ArrayList<>();


    }
    
//    public String getSpeciality() {
//        return speciality;
//    }
//    
//    public void setSpeciality(String speciality) {
//        this.speciality = speciality;
//    }
//    
public void prescribeMedicine(Patient patient) {
    Scanner scanner = new Scanner(System.in);

    // Prompt the doctor to enter the medicine details
    System.out.println("Prescribe Medicine");
    System.out.print("Enter medicine code: ");
    String medicineCode = scanner.nextLine();

    System.out.print("Enter medicine name: ");
    String medicineName = scanner.nextLine();

    System.out.print("Enter dose: ");
    double dose = scanner.nextDouble();

    System.out.print("Enter quantity: ");
    int quantity = scanner.nextInt();

    // Create a new Medicine object with the entered details
    Medicine medicine = new Medicine(medicineCode, medicineName, dose, quantity);

    // Add the prescribed medicine to the patient's list of prescribed medicines
    prescribedMedicine.add(medicine);

    System.out.println("Medicine prescribed successfully.");
}

public void showPrescribedMedicine(String patientId, AuthenticationManager authManager) {
    // Find the patient with the given ID
    User patient = authManager.getUserByID(patientId);

    // Check if the patient exists
    if (patient == null) {
        System.out.println("Patient not found.");
        return;
    }


    // Check if there is any prescribed medicine
    if (prescribedMedicine.isEmpty()) {
        System.out.println("No prescribed medicine for this patient.");
    } else {
        System.out.println("Prescribed medicine for patient with ID " + patientId + ":");
        for (Medicine medicine : prescribedMedicine) {
            System.out.println("- Code: " + medicine.getMedicineCode());
            System.out.println("  Name: " + medicine.getMedicineName());
            System.out.println("  Dose: " + medicine.getDose());
            System.out.println("  Quantity: " + medicine.getQuantity());
        }
    }
}

    
public void viewMedicalRecords() {
        for (MedicalRecord record : medicalRecord) {
            System.out.println("Document ID: " + record.getDocumentId());
            System.out.println("Procedure Name: " + record.getProcedure().getName());
            System.out.println("Procedure Code: " + record.getProcedure().getCode());
            System.out.println("Medical Notes: " + record.getMedicalNotes());
            System.out.println("-----------------------------------");
        }
    }
    public void addMedicalRecord(MedicalRecord record) {
        
        medicalRecord.add(record);
    }

    
}

