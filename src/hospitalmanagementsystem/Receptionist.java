package hospitalmanagementsystem;
import hospitalmanagementsystem.Room.RoomType;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        checkAvailableRooms();
        loadAppointmentsFromFile("appointments.txt");
    }

    public Receptionist(String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type) {
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
        appointments = new ArrayList<>();
        availableRooms = new ArrayList<>();
        checkAvailableRooms();
        loadAppointmentsFromFile("appointments.txt");
    }
    public void viewAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("No appointments scheduled.");
        } else {
            System.out.println("Appointments:");
            for (int i = 0; i < appointments.size(); i++) {
                Appointment appointment = appointments.get(i);
                Patient patient = appointment.getPatient();
                Room room = availableRooms.get(appointment.getRoom().getRoomNo());

                System.out.println("Appointment ID: " + i);
                System.out.println("Patient ID: " + patient.getID());
                System.out.println("Patient Name: " + patient.getFirstName() + " " + patient.getLastName());
                System.out.println("Age: " + patient.getAge());
                System.out.println("Phone Number: " + patient.getPhoneNumber());
                System.out.println("Room Number: " + room.getRoomNo());
                System.out.println("Room Type: " + room.getType());

                Procedure procedure = appointment.getProcedure();
                System.out.println("Procedure Type: " + procedure.getClass().getSimpleName());
                System.out.println("Procedure Code: " + procedure.getCode());
                System.out.println("Procedure Name: " + procedure.getName());

                if (procedure instanceof CheckUp) {
                    CheckUp checkUp = (CheckUp) procedure;
                    System.out.println("Doctor ID: " + checkUp.getCheckupDoctor().getID());
                } else if (procedure instanceof DiagnosticTest) {
                    DiagnosticTest diagnosticTest = (DiagnosticTest) procedure;
                    System.out.println("Test Result: " + diagnosticTest.getResult());
                    System.out.println("Test Status: " + diagnosticTest.getTestStatus());
                }

                System.out.println("--------------------");
            }
        }
    }




    @Override
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
        Appointment appointment = new Appointment(appointmentDate.getTime(), patient, room, procedure);
        appointments.add(appointment);
        System.out.println("Appointment scheduled successfully.");
    }

    public void viewAppointments(String patientID) {
        boolean foundAppointments = false;
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getID().equals(patientID)) {
                foundAppointments = true;
                Patient patient = appointment.getPatient();
                Room room = availableRooms.get(appointment.getRoom().getRoomNo());

                System.out.println("Appointment ID: " + appointment.getAppointmentId());
                System.out.println("Patient ID: " + patient.getID());
                System.out.println("Patient Name: " + patient.getFirstName() + " " + patient.getLastName());
                System.out.println("Age: " + patient.getAge());
                System.out.println("Phone Number: " + patient.getPhoneNumber());
                System.out.println("Room Number: " + room.getRoomNo());
                System.out.println("Room Type: " + room.getType());

                Procedure procedure = appointment.getProcedure();
                System.out.println("Procedure Type: " + procedure.getClass().getSimpleName());
                System.out.println("Procedure Code: " + procedure.getCode());
                System.out.println("Procedure Name: " + procedure.getName());

                if (procedure instanceof CheckUp) {
                    CheckUp checkUp = (CheckUp) procedure;
                    System.out.println("Doctor ID: " + checkUp.getCheckupDoctor().getID());
                } else if (procedure instanceof DiagnosticTest) {
                    DiagnosticTest diagnosticTest = (DiagnosticTest) procedure;
                    System.out.println("Test Result: " + diagnosticTest.getResult());
                    System.out.println("Test Status: " + diagnosticTest.getTestStatus());
                }

                System.out.println("--------------------");
            }
        }

        if (!foundAppointments) {
            System.out.println("No appointments found for the provided patient ID.");
        }
    }


    public List<Room> checkAvailableRooms() {
        availableRooms.clear(); // Clear the previous list of available rooms
        availableRooms.addAll(Room.getAvailableRooms()); // Update with the current list of available rooms
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
//        System.out.println("Enter the checkup location: ");
//        String location = scanner.nextLine();

            authManager.showDoctors();
            System.out.println("Enter the checkup doctor ID: ");
            String doctorID = scanner.next();

            Room room = new Room(roomChoice, RoomType.SINGLE, true);

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
    public Room selectRoom(int roomIndex) {
        List<Room> availableRooms = Room.getAvailableRooms();

        if (roomIndex >= 0 && roomIndex < availableRooms.size()) {
            Room selectedRoom = availableRooms.get(roomIndex);
            return selectedRoom;
        } else {
            return null;
        }
    }public void saveAppointmentsToFile(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            for (Appointment appointment : appointments) {
                String formattedDate = dateFormat.format(appointment.getDate());

                // Format the appointment details as CSV and write them to the file
                String csvLine = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s",
                        formattedDate,
                        appointment.getPatient().getID(),
                        appointment.getPatient().getFirstName(),
                        appointment.getPatient().getLastName(),
                        appointment.getPatient().getAge(),
                        appointment.getPatient().getPhoneNumber(),
                        appointment.getRoom().getRoomNo(),
                        appointment.getRoom().getType(),
                        appointment.getProcedure().getClass().getSimpleName(),
                        appointment.getProcedure().getCode(),
                        appointment.getProcedure().getName(),
                        getAdditionalInfo(appointment.getProcedure())
                );
                writer.write(csvLine);
                writer.newLine();
            }

            writer.close(); // Flush the buffer to ensure data is written to the file
            System.out.println("Appointments saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while saving appointments to file: " + e.getMessage());
        }
    }

    private String getAdditionalInfo(Procedure procedure) {
        if (procedure instanceof CheckUp) {
            CheckUp checkUp = (CheckUp) procedure;
            return checkUp.getCheckupDoctor().getID();
        } else if (procedure instanceof DiagnosticTest) {
            DiagnosticTest diagnosticTest = (DiagnosticTest) procedure;
            return diagnosticTest.getResult() + "," + diagnosticTest.getTestStatus();
        } else {
            return "";
        }
    }
    @Override
    public String toString() {
        return "Receptionist{" + "appointments=" + appointments + '}';
    }
    public void loadAppointmentsFromFile(String fileName) {
        appointments.clear(); // Clear existing appointments

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
//            System.out.println("Parsing line: " + line);
                Appointment appointment = parseAppointment(line);
                if (appointment != null) {
                    appointments.add(appointment);
                }
            }
//        System.out.println("Appointments loaded from file: " + fileName);
        } catch (IOException e) {
            System.out.println("An error occurred while loading appointments from file: " + e.getMessage());
        }
    }
    public Appointment parseAppointment(String line) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String[] parts = line.split(",");
        if (parts.length != 12) {
            return null; // Invalid line format
        }

        try {
            Date appointmentDate = dateFormat.parse(parts[0]);
            String patientID = parts[1];
            String firstName = parts[2];
            String lastName = parts[3];
            int age = Integer.parseInt(parts[4]);
            String phoneNumber = parts[5];
            int roomNo = Integer.parseInt(parts[6]);
            RoomType roomType = parseRoomType(parts[7]);
            String procedureType = parts[8];
            String procedureCode = parts[9];
            String procedureName = parts[10];
            String additionalInfo = parts[11];

            Patient patient = new Patient(firstName, lastName, patientID, age, phoneNumber);
            Room room = new Room(roomNo, roomType);

            Procedure procedure;
            if (procedureType.equalsIgnoreCase("CheckUp")) {
                procedure = new CheckUp(appointmentDate, procedureCode, procedureName);
            } else if (procedureType.equalsIgnoreCase("DiagnosticTest")) {
                procedure = new DiagnosticTest(procedureCode, procedureName);
            } else {
                throw new IllegalArgumentException("Invalid procedure type: " + procedureType);
            }

            if (procedure instanceof CheckUp) {
                CheckUp checkUp = (CheckUp) procedure;
                checkUp.setCheckupDoctor(new Doctor(additionalInfo));
            } else if (procedure instanceof DiagnosticTest) {
                DiagnosticTest diagnosticTest = (DiagnosticTest) procedure;
                String[] additionalInfoParts = additionalInfo.split(",");
                if (additionalInfoParts.length != 2) {
                    throw new IllegalArgumentException("Invalid additionalInfo format: " + additionalInfo);
                }
                diagnosticTest.setResult(additionalInfoParts[0]);
                diagnosticTest.setTestStatus(Boolean.parseBoolean(additionalInfoParts[1]));
            }

            return new Appointment(appointmentDate, patient, room, procedure);
        } catch (ParseException | NumberFormatException e) {
            // Handle parsing errors
            System.out.println("Error parsing appointment: " + line);
            return null;
        }
    }

    private RoomType parseRoomType(String roomType) {
        switch (roomType.toUpperCase()) {
            case "SINGLE":
                return RoomType.SINGLE;
            case "SHARED":
                return RoomType.SHARED;
            case "SUITE":
                return RoomType.SUITE;
            default:
                throw new IllegalArgumentException("Invalid room type: " + roomType);
        }
    }


}