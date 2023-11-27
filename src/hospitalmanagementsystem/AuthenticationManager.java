/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lamashuhail
 */
public class AuthenticationManager {
    private String filePath;
    
    public AuthenticationManager(String filePath) {
    this.filePath=filePath;
}
public  boolean userLookUp(String ID) {
    File file = new File(filePath);
    try {
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length >= 1 && parts[0].equals(ID)) {
                scanner.close();
                return true; // User found
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return false; // User not found
}

    public boolean login(String ID, String password){
        boolean isAuthenticated = authenticate(ID, password);
        if (isAuthenticated)
            System.out.println("Login successful");
        else 
            System.out.println("Login failed");
        return isAuthenticated;
    }
public boolean authenticate(String fullName, String password) {
    File file = new File(filePath);
    Scanner scanner;
    try {
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 3 && parts[0].equals(fullName) && parts[1].equals(password)) {
                scanner.close();
                return true; // Authentication success
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return false; // Authentication failed
}
public boolean saveUser(User user) {
    // Check if the user already exists
    if (userLookUp(user.getID())) {
        System.out.println("User with ID " + user.getID() + " already exists.");
        return false; // User already exists
    }

    try {
        FileWriter writer = new FileWriter(filePath, true);
        writer.write(user.getID() + "," + user.getPassword() + "," + user.getType() + "\n");
        writer.close();
        System.out.println("User with ID " + user.getID() + " saved successfully.");
        return true; // User saved successfully
    } catch (IOException e) {
        e.printStackTrace();
        return false; // Failed to save user
    }
}

public String getUserByID(String ID) {
    File file = new File(filePath);
    
    try {
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            
            if (parts.length == 3 && parts[0].equals(ID)) {
                String userID = parts[0];
                String password = parts[1];
                String userType = parts[2];
                
                scanner.close();
                
                return userID + "," + password + "," + userType;
            }
        }
        
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    
    return null; // User not found
}
public boolean deleteUser(String ID) {
    File inputFile = new File(filePath);
    File tempFile = new File("/Users/lamashuhail/NetBeansProjects/HospitalManagementSystem/temp.txt");

    try {
        Scanner scanner = new Scanner(inputFile);
        FileWriter writer = new FileWriter(tempFile);

        boolean userFound = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] userData = line.split(","); // Assuming user data is comma-separated

            String userID = userData[0]; // Assuming ID is stored in the first field

            if (userID.equals(ID)) {
                userFound = true;
                continue; // Skip writing this line to the temporary file
            }

            writer.write(line);
            writer.write(System.lineSeparator());
        }

        scanner.close();
        writer.close();

        if (userFound) {
            inputFile.delete();
            tempFile.renameTo(inputFile);
            return true; // User deleted successfully
        } else {
            tempFile.delete();
            return false; // User not found
        }
    } catch (IOException e) {
        e.printStackTrace();
        return false; // Failed to delete user
    }
}
public void showDoctors() {
    showUsersByType("Doctor");
}

public void showPatients() {
    showUsersByType("Patient");
}

public void showReceptionists() {
    showUsersByType("Receptionist");
}

public void showAdmins() {
    showUsersByType("Admin");
}

private void showUsersByType(String userType) {
    File file = new File(filePath);

    try {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            
            if (parts.length == 3 && parts[2].equals(userType)) {
                String userID = parts[0];
                System.out.println(userType + ": " + userID);
            }
        }
        
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}
}
