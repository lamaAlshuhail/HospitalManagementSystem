package hospitalmanagementsystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Patient extends User {
    private List<MedicalRecord> medicalRecord;
    private List<Appointment> appointment;

    //constructor no args
    public Patient() {}

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
    public Patient(List<MedicalRecord> medicalRecord, String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
        this.medicalRecord = medicalRecord;
        appointment= new ArrayList<>();
    }

    //constructor args
    public Patient (String firstName, String lastName, String ID, int age, String phoneNumber){
        super(firstName, lastName, ID,age, phoneNumber);
        medicalRecord = new ArrayList<>();
        appointment= new ArrayList<>();
    }

    //viewMedicalRecord method
    public List<MedicalRecord> viewMedicalRecord() {
        return medicalRecord;}



    //cancelAppointment method
    public boolean cancelAppointment(Appointment appointmentId) {
        for (int i = 0; i < appointment.size(); i++) {
            if (appointmentId.equals(appointment.get(i)))
            {
                appointment.remove(i);

            }} return true;}


    //viewMyAppointments method
    public void viewMyAppointments(){
        if (appointment.isEmpty()) {
            System.out.println("there is No appointments scheduled .");
        } else {
            System.out.println("My Appointments:");
            for (Appointment appointment : appointment) {
                System.out.println(appointment);
            }}}

    //findAppointmentById method
    public static Appointment findAppointmentById(String appointmentId) {
        // search through the patient's appointments and return the one with the given ID
        return new Appointment();}


    private Date parseDate(String dateString) { // convert a string into a Date object
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString); } catch (ParseException e) {
            e.printStackTrace();
            return null; }
    }

}