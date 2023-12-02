package hospitalmanagementsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends User {
    private List<MedicalRecord> medicalRecord;
    private List<Appointment> appointment;

    //constructor args
    public Patient(String firstName, String lastName, String ID, int age, char gender, String phoneNumber) {
        super(firstName, lastName, ID, age, gender, phoneNumber);
        medicalRecord = new ArrayList<>();
        appointment= new ArrayList<>();

    }

    //constructor args
    public Patient(String ID, String password, String type){
        super(ID, password, type);
        appointment= new ArrayList<>();
    }

    //constructor args
    public Patient (String firstName, String lastName, String ID, int age, String phoneNumber){
        super(firstName, lastName, ID,age, phoneNumber);
        medicalRecord = new ArrayList<>();
        appointment= new ArrayList<>();
    }



}