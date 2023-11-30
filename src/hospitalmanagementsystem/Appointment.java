/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */
import java.util.Date;

public class Appointment {
    private int appointmentId;
    private Date date;
    private Patient patient;
    private Doctor doctor;
    private Room room;
    private Procedure procedure;

    public Appointment() {
//        this(date, patient, date, room, procedure);
    }

    public Appointment(Date date1, Patient patient1, Receptionist aThis, Room room1, Procedure procedure1) {
    }

    public Appointment(int appointmentId, Date date, Patient patient, Doctor doctor, Room room, Procedure procedure) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.patient = patient;
        this.doctor = doctor;
        this.room = room;
        this.procedure = procedure;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
//
//    // Method to schedule a new appointment
//    public void scheduleAppointment(Patient patient, Date date, Procedure procedure, Room room) {
//        
//    }
//
//    // Method to cancel an appointment
//    public void cancelAppointment(int appointmentId) {
//        // code :)
//    }
//
//    // Method to reschedule an appointment
//    public void rescheduleAppointment(int appointmentId, Date newDate, Room newRoom) {
//        // code :)
//    }

    // Override toString method to provide a meaningful string representation of the appointment
    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId +
                ", Date: " + date +
                ", Patient: " + patient +
                ", Doctor: " + doctor +
                ", Room: " + room +
                ", Procedure: " + procedure;
    }
}