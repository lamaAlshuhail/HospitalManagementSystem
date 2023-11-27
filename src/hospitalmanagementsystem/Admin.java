/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author lamashuhail
 */
public class Admin extends User{
    public Admin(){
        super();
    }
    public Admin (String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type){
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
//        super.setFirstName(firstName);
//        super.setLastName(lastName);
//        super.setID(ID);
//        super.setGender(gender);
//        super.setAge(age);
//        super.setPhoneNumber(phoneNumber);
//        super.setPassword(password);
//        super.setType(type);
   
    }
    public Admin (String ID, String pass, String type){
            super(ID, pass, type);
    }
    
    public void addUser(User newUser){
        //Smthn smthn 

    }
    public void deleteUser(String ID){
        AuthenticationManager authManager = new AuthenticationManager("/Users/lamashuhail/NetBeansProjects/HospitalManagementSystem/users.txt");
        Scanner scanner = new Scanner(System.in);
        
        boolean userExists = authManager.userLookUp(ID);
        if (userExists){
            try{
                Path filePath = Path.of("/Users/lamashuhail/NetBeansProjects/HospitalManagementSystem/users.txt");
                List<String> lines = Files.readAllLines(filePath);
                lines.removeIf(line -> {
                    String[] parts = line.split(",");
                    return parts.length >= 1 && parts[0].equals(ID);
                });
                
                Files.write(filePath, lines, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

                System.out.println("User with ID " + ID + " has been deleted.");            
            }catch (IOException e) {
                e.printStackTrace();
            }
        }else 
            System.out.println("User with ID "+ID+" doesn't exist.");
        
        scanner.close();
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
                updateUserField(user, fieldNumber, scanner);
            }
        } else {
            int choice = Integer.parseInt(choices);
            updateUserField(user, choice, scanner);
        }

        if (authManager.deleteUser(user.getID())) {
            authManager.saveUser(user);
            System.out.println("User updated successfully.");
        } else {
            System.out.println("Unable to update user.");
        }
    } else {
        System.out.println("No fields selected for update.");
    }
} 
    
    private void updateUserField(User user, int fieldNumber, Scanner scanner) {
    switch (fieldNumber) {
        case 1:
            System.out.print("Enter your first name: ");
            String newFirstName = scanner.nextLine();
            user.setFirstName(newFirstName);
            break;
        case 2:
            System.out.print("Enter your last name: ");
            String newLastName = scanner.nextLine();
            user.setLastName(newLastName);
            break;
        case 3:
            System.out.print("Enter your ID: ");
            String newID = scanner.nextLine();
            user.setID(newID);
            break;
        case 4:
            System.out.print("Enter your age: ");
            int newAge = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            user.setAge(newAge);
            break;
        case 5:
            System.out.print("Enter your phone number: ");
            String newPhoneNumber = scanner.nextLine();
            user.setPhoneNumber(newPhoneNumber);
            break;
        case 6:
            System.out.print("Enter your password: ");
            String newPassword = scanner.nextLine();
            user.setPassword(newPassword);
            break;
        default:
            System.out.println("Invalid input, please try again later");
    }
}
}

    
    



// switch (choice){
//                case 1: 
//                    System.out.print("Enter your first name: ");
//                    String firstName = scanner.next();
//                    user.setFirstName(firstName);
//                    break;
//                case 2:
//                    break;
//                case 3: 
//                    break;
//                case 4: 
//                    break;
//                case 5: 
//                    break;
//                default:
//                    System.out.println("Inavlid input, please try again later");
//            }