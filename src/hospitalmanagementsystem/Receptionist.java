package hospitalmanagementsystem;
import hospitalmanagementsystem.Room.RoomType;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Receptionist extends User {
    private List<Appointment> appointments;
    private List<Room> availableRooms;
    private AuthenticationManager authManager;

//    public Receptionist() {
//        this(user.getID(), user.getPassword(), user.getType());
//    }
//public Receptionist(AuthenticationManager authManager) {
//    this.authManager = authManager;
//}
    public Receptionist(String id, String password, String type, AuthenticationManager authManager) {
        super();
        appointments = new ArrayList<>();
        availableRooms = new ArrayList<>();
        this.authManager = authManager;
    }

    public Receptionist(String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
        appointments = new ArrayList<>();
        availableRooms = new ArrayList<>();
    }
public void viewAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            System.out.println("Appointments:");
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                Patient patient = appointment.getPatient();
                Room room = availableRooms.get(i);

                System.out.println("Appointment ID: " + i);
                System.out.println("Patient ID: " + patient.getID());
                System.out.println("Patient Name: " + patient.getFirstName() + " " + patient.getLastName());
                System.out.println("Age: " + patient.getAge());
                System.out.println("Phone Number: " + patient.getPhoneNumber());
                System.out.println("Room Number: " + room.getRoomNo());
                System.out.println("Room Type: " + room.getType());
                System.out.println("--------------------");
            }
        }
    }




public void scheduleAppointment(Patient patient) {
    Scanner scanner = new Scanner(System.in);
    GregorianCalendar calendar = new GregorianCalendar();

    boolean validDate = false;
    int year = 0, month = 0, day = 0;

    while (!validDate) {
        System.out.println("Enter the appointment date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();

        try {
            String[] dateParts = dateString.split("-");
            year = Integer.parseInt(dateParts[0]);
            month = Integer.parseInt(dateParts[1]) - 1; // Month is 0-based in Calendar
            day = Integer.parseInt(dateParts[2]);

            // Validate the entered date
            calendar.setLenient(false);
            calendar.set(year, month, day);
            Calendar currentDate = Calendar.getInstance(); // Get the current date
            if (calendar.before(currentDate)) {
                System.out.println("Invalid date. Appointment date should be in the future.");
            } else {
                validDate = true;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format or value. Please enter a valid date in the format yyyy-MM-dd.");
        }
    }

    List<Room> availableRooms = checkAvailableRooms();
    System.out.println("Available Rooms:");
    for (int i = 0; i < availableRooms.size(); i++) {
        Room room = availableRooms.get(i);
        System.out.println((i + 1) + ". Room Number: " + room.getRoomNo() + ", Type: " + room.getType());
    }

    System.out.println("Enter the room number for the appointment: ");
    int roomChoice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    if (roomChoice < 1 || roomChoice > availableRooms.size()) {
        System.out.println("Invalid room selection. Appointment scheduling failed.");
        return;
    }

    Room room = availableRooms.get(roomChoice - 1);
    Procedure procedure = getProcedureFromUser(roomChoice);

    if (procedure == null) {
        System.out.println("Invalid procedure information. Appointment scheduling failed.");
        return;
    }

Calendar appointmentDate = new GregorianCalendar(year, month, day);
    Appointment appointment = new Appointment(appointmentDate.getTime(), patient, null, room, procedure);
    appointments.add(appointment);

    System.out.println("Appointment scheduled successfully.");
}


public List<Room> checkAvailableRooms() {
    availableRooms = Room.getAvailableRooms();
    return availableRooms;
}
private Procedure getProcedureFromUser(int roomChoice) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter the procedure code: ");
    String code = scanner.nextLine();

    System.out.println("Enter the procedure name: ");
    String name = scanner.nextLine();

    System.out.println("Enter the procedure duration: ");
    long duration = scanner.nextLong();
    scanner.nextLine(); // Consume the newline character

    // Prompt the user for the specific procedure type
    System.out.println("Select the procedure type:");
    System.out.println("1. CheckUp");
    System.out.println("2. DiagnosticTest");
    int typeChoice = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    Procedure procedure = null;

    if (typeChoice == 1) {
        // Prompt the user for additional CheckUp information
        System.out.println("Enter the checkup location: ");
        String location = scanner.nextLine();

        authManager.showDoctors();
        System.out.println("Enter the checkup doctor ID: ");
        String doctorID = scanner.next();

        Room room = new Room(roomChoice, RoomType.SINGLE, false);

        procedure = new CheckUp(code, name, duration, null, room, new Doctor(doctorID));
    } else if (typeChoice == 2) {
        // Prompt the user for additional DiagnosticTest information
        System.out.println("Enter the test result: ");
        String result = scanner.nextLine();

        System.out.println("Enter the test status (true/false): ");
        boolean testStatus = scanner.nextBoolean();
        scanner.nextLine(); // Consume the newline character

        procedure = new DiagnosticTest(code, name, duration, result, testStatus);
    } else {
        System.out.println("Invalid procedure type selection.");
    }

    return procedure;
}
}