/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalmanagementsystem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author lamashuhail
 */
public class HospitalManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        String usersFile ="/Users/lamashuhail/NetBeansProjects/HospitalManagementSystem/users.txt";
        AuthenticationManager authManager = new AuthenticationManager("/Users/lamashuhail/NetBeansProjects/HospitalManagementSystem/users.txt");
        signupMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        ArrayList<User> sysUsers = new ArrayList<>(authManager.loadUsersFromFile());
        String appointmentsFile="appointments.txt";
        User user= null;
        if (choice == 1) {
            user = getUserDetails(scanner);
            boolean signUpResult = authManager.authenticate(user.getID(), user.getPassword());
            if (authManager.userLookUp(user.getID())) {
                System.out.println("User with the same ID already exists. Please try again.");
            } else {
                authManager.addUser(user);
                System.out.println("Sign Up Successful.");
            }
        } else if (choice == 2) {
            System.out.print("Enter your ID: ");
            String ID = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
            if (authManager.authenticate(ID, password)) {
                System.out.println("Log in successful.");
                user = authManager.getUserByID(ID);
//        if (userDetails.getID() != null) {
//            String[] userInfoParts = userInfo.split(",");
//            String userID = userInfoParts[0];
//            String userPass = userInfoParts[1];
//            String userType = userInfoParts[2];
                switch(user.getType()){
                    case "Admin":
                        user = new Admin(user.getID(), user.getPassword(),user.getType());
                        adminMenu(user, authManager, sysUsers);
                        break;
                    case "Patient":
                        user = new Patient(user.getID(), user.getPassword(),user.getType());

                        patientMenu(user,authManager);
                        break;
                    case "Doctor":
                        user = new Doctor(user.getID(), user.getPassword(),user.getType());
                        doctorMenu();
                        break;
                    case "Receptionist":
                        user = new Receptionist(user.getID(), user.getPassword(), user.getType(), authManager);
                        receptionistMenu(user, authManager);
                        break;
                }

            } else {
                System.out.println("Log in failed.");
            }
        } else {
            System.out.println("Invalid Input.");
        }
    }

    public static void signupMenu() {
        displayHeader("S I G N  I N  P A G E");
        System.out.println("\t1. Sign up");
        System.out.println("\t2. Log in");
        System.out.print("Enter your choice: ");
    }
    public static void userRoles(){
        System.out.print("Enter your role \n\t1. Admin\n\t2. Receptionist\n\t3. Doctor\n\t4. Patient\n");
        System.out.print("Role: ");

    }
    private static User getUserDetails(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter ID: ");
        String ID = scanner.nextLine();

        while (!isValidID(ID)) {
            displayErrorMessage("Invalid ID. ID must be a digit and 10 characters long.");
            System.out.print("Enter ID: ");
            ID = scanner.nextLine();
        }
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        
        char gender = ' '; // Initialize with a default value

        while (gender != 'M' && gender != 'F') {
            System.out.print("Enter gender (M/F): ");
            String genderInput = scanner.nextLine();

            if (!genderInput.isEmpty()) {
                gender = Character.toUpperCase(genderInput.charAt(0));
            } else {
                System.out.println("Gender cannot be empty. Please try again.");
            }

            if (gender != 'M' && gender != 'F') {
                System.out.println("Invalid gender. Please enter 'M' for male or 'F' for female.");
            }
        }
        
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        while (!isValidPhoneNumber(phoneNumber)) {
            displayErrorMessage("Invalid phone number. Phone number must be a digit and start with '05'.");
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();
        }

        String password;
        User temp = new User();

        do {
            temp.displayPasswordRequirements();
            System.out.print("Enter password: ");
            password = scanner.nextLine();

            if (!temp.verifyPassword(password)) {
                displayErrorMessage("Invalid password.");
            }
        } while (!temp.verifyPassword(password));
        userRoles();
        int role = scanner.nextInt();
        String type=convertSignUpRoleToType(role);

        return new User(firstName, lastName, ID, age, gender, phoneNumber, password,type);
    }

    public static String convertSignUpRoleToType(int signUpRole){
        String type="Patient";
        switch (signUpRole) {
            case 1:
                type="Admin";
                break;
            case 2:
                type="Receptionist";
                break;
            case 3:
                type="Doctor";
                break;
            default:
                return "Patient";
        }
        return type;
    }
//    public static void adminStartPage(){
//        System.out.println("Which operation would you like to do: ");
//        System.out.println("\t1. Add User\n\t2. Update User\n\t3. Delete User\n\t4. Exit");
//    }


    //    private static void receptionistStartPage() {
//        System.out.println("\n\t1. View Appointment\n\t2. Schedule Appointments\n\t3. Check Available Rooms");
//    }
//
    public static void adminMenu(User user, AuthenticationManager authManager, ArrayList<User> sysUsers) {
        displayHeader("Admin Menu");
        System.out.println("\t1. Add User");
        System.out.println("\t2. Update User");
        System.out.println("\t3. Delete User");
        System.out.println("\t4. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                User newUser = getUserDetails(scanner);
                boolean signUpResult = authManager.authenticate(user.getID(), user.getPassword());

                if (authManager.userLookUp(newUser.getID())) {
                    displayErrorMessage("User with the same ID already exists. Please try again.");
                } else {
                    authManager.addUser(newUser);
                    displaySuccessMessage("User added successfully.");
                }

                adminMenu(user, authManager, sysUsers);
                break;
            case 2:
                
                showUsers(sysUsers);
                System.out.print("Enter the ID of the user to update: ");
                String idToBeUpdated = scanner.next();
                scanner.nextLine();

                User userToUpdate = authManager.getUserByID(idToBeUpdated);

                if (userToUpdate != null) {
                    if (user instanceof Admin) {
                        ((Admin) user).updateUser(userToUpdate, scanner, authManager,sysUsers);
                        authManager.saveUsers();
//                    displaySuccessMessage("User updated successfully.");
                    } else {
                        displayErrorMessage("Cannot update user.");
                    }
                } else {
                    displayErrorMessage("User not found.");
                }

                adminMenu(user, authManager, sysUsers);
                break;
            case 3:
                showUsers(sysUsers);
                System.out.print("Enter the ID of the user to delete: ");
                String idToBeDeleted = scanner.next();
                scanner.nextLine();

                User userToDelete = authManager.getUserByID(idToBeDeleted);

                if (userToDelete != null) {
                    if (user instanceof Admin) {
                        ((Admin) user).deleteUser(idToBeDeleted, sysUsers);
                        authManager.updateUserList(sysUsers);
                        authManager.saveUsers();
                        displaySuccessMessage("User deleted successfully.");
                    } else {
                        displayErrorMessage("Cannot delete user.");
                    }
                } else {
                    displayErrorMessage("User not found.");
                }

                adminMenu(user, authManager, sysUsers);
                break;
            case 4:
                displaySuccessMessage("Logging out.");
                break;
            default:
                displayErrorMessage("Invalid input.");
                adminMenu(user, authManager, sysUsers);
                break;
        }
    }

    public static void showUsers(ArrayList<User> sysUsers){
        System.out.println("Users:");
        for (User temp : sysUsers) {
            String output = String.format("ID: %-10s Type: %s", temp.getID(), temp.getType());
            System.out.println(output);
        }
    }
    public static void displayHeader(String title) {
        System.out.println("===============================================");
        System.out.println("\t\t" + title);
        System.out.println("===============================================");
    }
    public static void displayErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public static void displaySuccessMessage(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    public static void receptionistMenu(User user, AuthenticationManager authManager) {

        displayHeader("Receptionist Menu");
        System.out.println("\t1. View Appointments");
        System.out.println("\t2. Schedule Appointment");
        System.out.println("\t3. Check Available Rooms");
        System.out.println("\t4. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                ((Receptionist)user).viewAppointments();
                receptionistMenu(user, authManager);
            case 2:
                authManager.showPatients();
                System.out.print("Enter the ID of the patient: ");
                String patientID = scanner.next();
                if(authManager.userLookUp(patientID)){
                    Patient patientApp = patientInfo(scanner, patientID);
                    ((Receptionist)user).scheduleAppointment(patientApp);
                    ((Receptionist)user).saveAppointmentsToFile("appointments.txt");
                }else {
                    displayErrorMessage("ID doesn't exist");
                }

                receptionistMenu(user, authManager);
            case 3:
                ((Receptionist)user).checkAvailableRooms();
                receptionistMenu(user, authManager);

            case 4:
                displaySuccessMessage("Logging out.");
                System.exit(0);
            default:
                displayErrorMessage("Invalid input.");

                break;
        }
    }
    public static void doctorMenu() {
        displayHeader("Doctor Menu");
        System.out.println("\t1. Prescribe Medicine");
        System.out.println("\t2. Add Medical Record");
        System.out.println("\t3. View Patient Record");
        System.out.println("\t4. Show Prescribed Medicine");

        System.out.println("\t5. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:
                break;
            case 5:
                displaySuccessMessage("Logging out.");
                break;
            default:
                displayErrorMessage("Invalid input.");

                break;
        }
    }
    public static void patientMenu(User user, AuthenticationManager authManager) {
        Receptionist tempRecep = new Receptionist("default","default","Receptionist",authManager);
        displayHeader("Patient Menu");
        System.out.println("\t1. View Medical Record");
        System.out.println("\t2. View my Appointments");
        System.out.println("\t3. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                ((Patient)user).viewMedicalRecord();
                patientMenu(user, authManager);

            case 2:
                tempRecep.viewAppointments(user.getID());
                patientMenu(user, authManager);
                break;

            case 3:
                displaySuccessMessage("Logging out.");
                break;
            default:
                displayErrorMessage("Invalid input.");

                break;
        }
    }
    public static void patientDetails(Scanner scanner, List<MedicalRecord> medicalRecord){

        System.out.print("Enter ID: ");
        String ID = scanner.nextLine();


        System.out.print("Enter Diagnosis: ");
        String notes = scanner.nextLine();
        //solve it later
    }
    public static Patient patientInfo(Scanner scanner, String ID) {
        scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        return new Patient(firstName, lastName, ID, age, gender, phoneNumber);
    }
    private static boolean isValidID(String ID) {
    return ID.matches("\\d{10}");
}
    
    private static boolean isValidPhoneNumber(String phoneNumber) {
    return phoneNumber.matches("05\\d{8}") && phoneNumber.length() == 10;
}
}



    
