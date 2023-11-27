package hospitalmanagementsystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Admin extends User {
    private List<User> userList;

    public Admin() {
        super();
        userList = new ArrayList<>();
    }
        public Admin(String ID, String password, String type) {
        super(ID, password, type);
        userList = new ArrayList<>();
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
  

    public void deleteUser(String ID) {
        Iterator<User> iterator = userList.iterator();
        boolean found = false;

        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getID().equals(ID)) {
                iterator.remove();
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("User with ID " + ID + " has been deleted.");
        } else {
            System.out.println("User with ID " + ID + " doesn't exist.");
        }
    }

    public void updateUser(User user, Scanner scanner) {
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
                    updateUserField(user, fieldNumber, scanner);
                }
            } else {
                int choice = Integer.parseInt(choices);
                updateUserField(user, choice, scanner);
            }
            
            System.out.println("User updated successfully.");
        } else {
            System.out.println("No fields selected for update.");
        }
    }

    private void updateUserField(User user, int fieldNumber, Scanner scanner) {
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
                user.setID(newID);
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
                user.setPassword(newPassword);
                break;
            default:
                System.out.println("Invalid input, please try again later");
        }
    }
}