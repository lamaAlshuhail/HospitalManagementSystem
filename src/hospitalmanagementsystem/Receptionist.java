package hospitalmanagementsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Receptionist extends User {
    private List<Appointment> appointments;
    private List<Room> availableRooms;

    public Receptionist() {
        super();
        appointments = new ArrayList<>();
        availableRooms = new ArrayList<>();
    }

    public Receptionist(String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
        appointments = new ArrayList<>();
        availableRooms = new ArrayList<>();
    }

    public List<Appointment> viewAppointments() {
        return appointments;
    }

    public void scheduleAppointment(Patient patient, Date date, Procedure procedure, Room room) {
        Appointment appointment = new Appointment(date, patient, this, room, procedure);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    public List<Room> checkAvailableRooms() {
        return availableRooms;
    }
}