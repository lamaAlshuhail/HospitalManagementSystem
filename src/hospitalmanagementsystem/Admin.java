package hospitalmanagementsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    private List<User> userList;
    private AuthenticationManager authManager;
    public Admin() {
        super();
        userList = new ArrayList<>();
    }
    
    
        public Admin(String ID, String password, String type) {
        super(ID, password, type);
        userList = new ArrayList<>();
    }
        
        public Admin(AuthenticationManager authManager) {
        this.authManager = authManager;
    }
        
  public void addUser(User newUser) {
    boolean isDuplicate = userList.stream().anyMatch(user -> user.getID().equals(newUser.getID()));

    if (isDuplicate) {
        System.out.println("User with the same ID already exists. User not added.");
    } else {
        userList.add(newUser);
        System.out.println("User added successfully.");
    }
}
  

public void deleteUser(String ID, ArrayList<User> sysUsers) {
    boolean found = false;
    int index = -1;

    // Find the index of the user with the specified ID
    for (int i = 0; i < sysUsers.size(); i++) {
        User user = sysUsers.get(i);
        if (user.getID().equals(ID)) {
            found = true;
            index = i;
            break;
        }
    }

    if (found) {
        sysUsers.remove(index);
        System.out.println("User with ID " + ID + " has been deleted.");
    } else {
        System.out.println("User with ID " + ID + " doesn't exist.");
    }
}

    public void updateUser(User user, Scanner scanner, AuthenticationManager authManager) {
    System.out.println("Which field would you like to update: ");
    System.out.println("\t1. First Name\n\t2. Last Name\n\t3. ID\n\t4. Age\n\t5. Phone Number\n\t6. Password\n");
    System.out.print("Field Numbers (You can choose more than one, separate by spaces): ");
    scanner.nextLine();
    String choices = scanner.nextLine();

    if (!choices.isEmpty()) {
        if (choices.length() > 1) {
            String[] choiceArr = choices.split(" ");

            for (String choice : choiceArr) {
                int fieldNumber = Integer.parseInt(choice);
                updateUserField(user, fieldNumber, scanner, authManager);
            }
        } else {
            int choice = Integer.parseInt(choices);
            updateUserField(user, choice, scanner, authManager);
        }

        System.out.println("User updated successfully.");
    } else {
        System.out.println("No fields selected for update.");
    }
}


    private void updateUserField(User user, int fieldNumber, Scanner scanner, AuthenticationManager authManager) {
        switch (fieldNumber) {
            case 1:
                System.out.print("Enter the new first name: ");
                String newFirstName = scanner.nextLine();
                user.setFirstName(newFirstName);
                break;
            case 2:
                System.out.print("Enter the new last name: ");
                String newLastName = scanner.nextLine();
                user.setLastName(newLastName);
                break;
            case 3:
                System.out.print("Enter the new ID: ");
                String newID = scanner.nextLine();
                if (authManager.userLookUp(newID))
                user.setID(newID); //make sure id doesnt repeat
                break;
            case 4:
                System.out.print("Enter the new age: ");
                int newAge = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                user.setAge(newAge);
                break;
            case 5:
                System.out.print("Enter the new phone number: ");
                String newPhoneNumber = scanner.nextLine();
                user.setPhoneNumber(newPhoneNumber);
                break;
            case 6:
                System.out.print("Enter the new password: ");
                String newPassword = scanner.nextLine();
                user.setPassword(newPassword); //Verify Pass later
                break;
            default:
                System.out.println("Invalid input, please try again later");
        }
    }
}