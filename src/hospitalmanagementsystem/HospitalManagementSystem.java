/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalmanagementsystem;
import java.util.ArrayList;
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
        AuthenticationManager authManager = new AuthenticationManager("/Users/lamashuhail/NetBeansProjects/HospitalManagementSystem/users.txt");
        signupMenu();
        int choice = scanner.nextInt();
        scanner.nextLine();
        ArrayList<User> sysUsers = new ArrayList<>(authManager.loadUsersFromFile());

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
    System.out.println("Enter your ID: ");
    String ID = scanner.nextLine();
    System.out.println("Enter your password: ");
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
                    adminStartPage();
                    int choice1 = scanner.nextInt();
                    switch (choice1){
                        case 1:
                            break;
                        case 2:
                            System.out.print("User's ID: ");
                            String idToBeUpdated = scanner.next();
                            User userToUpdate = authManager.getUserByID(idToBeUpdated);
                            if(userToUpdate !=null){
                                 if (user instanceof Admin){
                                 ((Admin)user).updateUser(userToUpdate, scanner, authManager);
//                                 authManager.updateUserList(sysUsers);

                                 authManager.saveUsers();
                                 }
                                 else 
                                     System.out.println("Cannot update");
                            }
                            break;
                        case 3: 
                            System.out.print("User's ID: ");
                            String idToBeDeleted = scanner.next();
                            User userToDelete= authManager.getUserByID(idToBeDeleted);

                                if(idToBeDeleted !=null){
                                 if (user instanceof Admin){
                                 ((Admin)user).deleteUser(idToBeDeleted, sysUsers);
                                 authManager.updateUserList(sysUsers);
                                 authManager.saveUsers();
                                 }
                                 else 
                                     System.out.println("Cannot delete");
                            }
                            break;
                    }
                    break;
                case "Patient":
                    break;
                case "Doctor":
                    break;
                case "Receptionist":
                    break;   
            }
//            if (userType.equals("Admin")) {
//                user = new Admin(userID, userPass, "Admin");
//                System.out.println(showWelcomeMessage(user));
//                adminStartPage();
//            } else if (userType.equals("Receptionist")) {
//                receptionistStartPage();
//            }
//        } else {
//            System.out.println("User not found.");
//        }
    } else {
        System.out.println("Log in failed.");
    }
} else {
    System.out.println("Invalid Input.");
}
    }
    
    public static void signupMenu(){
        System.out.println("------------S I G N  I N  P A G E ------------");
        System.out.println("\t1. Sign up\n\t2. Log in\nEnter your choice: ");                 
    }
    public static void userRoles(){
             System.out.print("Enter your role \n\t1. Admin\n\t2. Receptionist\n\t3. Doctor\n\t4. Patient\n");
             System.out.print("Role: ");
            
    }
    private static User getUserDetails(Scanner scanner) {
    System.out.println("Enter your first name: ");
    String firstName = scanner.nextLine();

    System.out.println("Enter your last name: ");
    String lastName = scanner.nextLine();

    System.out.println("Enter your ID: ");
    String ID = scanner.nextLine();

    System.out.println("Enter your age: ");
    int age = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    System.out.println("Enter your gender (M/F): ");
    char gender = scanner.nextLine().charAt(0);

    System.out.println("Enter your phone number: ");
    String phoneNumber = scanner.nextLine();

    System.out.println("Enter your password: ");
    String password = scanner.nextLine();
    
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
    public static void adminStartPage(){
        System.out.println("Which operation would you like to do: ");
        System.out.println("\t1. Add User\n\t2. Update User\n\t3. Delete User");
    }
public static String showWelcomeMessage(User user) {
    String userType = user.getType();
    String welcomeMessage = "";
    
    if (userType.equals("Admin")) {
        welcomeMessage = "Welcome Admin, " + user.getFirstName() + " " + user.getLastName();
    } else if (userType.equals("Receptionist")) {
        welcomeMessage = "Welcome Receptionist, " + user.getFirstName() + " " + user.getLastName();
    } else if (userType.equals("Doctor")) {
        welcomeMessage = "Welcome Doctor, " + user.getFirstName() + " " + user.getLastName();
    } else if (userType.equals("Patient")) {
        welcomeMessage = "Welcome Patient, " + user.getFirstName() + " " + user.getLastName();
    } else {
        welcomeMessage = "Welcome, " + user.getFirstName() + " " + user.getLastName();
    }
    
    return welcomeMessage;
}

    private static void receptionistStartPage() {
        System.out.println("\n\t1. View Appointment\n\t2. Schedule Appointments\n\t3. Check Available Rooms");
    }
}



    
