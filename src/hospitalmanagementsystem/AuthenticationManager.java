/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
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
public boolean authenticate(String ID, String password) {
    boolean userExists = userLookUp(ID);
    if (userExists) {
        return false; // User with the same ID already exists
    }
    
    // Continue with the authentication logic to check if the user exists with the provided ID and password
    File file = new File(filePath);
    Scanner scanner;
    try {
        scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split(",");
            if (parts.length == 2 && parts[0].equals(ID) && parts[1].equals(password)) {
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
    
}
