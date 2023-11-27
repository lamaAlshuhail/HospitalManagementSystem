package hospitalmanagementsystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class AuthenticationManager {
    private String filePath;
    private ArrayList<User> userList;

    public AuthenticationManager(String filePath) {
        this.filePath = filePath;
        this.userList = new ArrayList<>();
        loadUsersFromFile();
    }

    public ArrayList<User> loadUsersFromFile() {
    userList.clear(); // Clear the existing user entries

    try {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] userData = line.split(",");

            if (userData.length == 3) {
                String userID = userData[0];
                String password = userData[1];
                String userType = userData[2];

                User user = new User(userID, password, userType);
                userList.add(user);
            }
        }

        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return userList;
}


    public boolean userLookUp(String ID) {
        for (User user : userList) {
            if (user.getID().equals(ID)) {
                return true; // User found
            }
        }
        return false; // User not found
    }

    public boolean login(String ID, String password) {
        boolean isAuthenticated = authenticate(ID, password);
        if (isAuthenticated)
            System.out.println("Login successful");
        else
            System.out.println("Login failed");
        return isAuthenticated;
    }

    public boolean authenticate(String ID, String password) {
        for (User user : userList) {
            if (user.getID().equals(ID) && user.getPassword().equals(password)) {
                return true; // Authentication success
            }
        }
        return false; // Authentication failed
    }

   public void saveUsers() {
          try {
            // Clear the file by creating a new PrintWriter with an empty file
            PrintWriter clearWriter = new PrintWriter(filePath);
            clearWriter.close();

            FileWriter writer = new FileWriter(filePath, true); // Set append parameter to true
            
            for (User user : userList) {
                String line = user.getID() + "," + user.getPassword() + "," + user.getType();
                writer.write(line + System.lineSeparator());
            }
            
            writer.close();
            System.out.println("User data saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    

    public User getUserByID(String ID) {
        for (User user : userList) {
            if (user.getID().equals(ID)) {
                return user;
            }
        }
        return null; // User not found
    }

    public String getUserDataByID(String ID) {
        for (User user : userList) {
            if (user.getID().equals(ID)) {
                return user.getID() + "," + user.getPassword() + "," + user.getType();
            }
        }
        return null; // User not found
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
        for (User user : userList) {
            if (user.getType().equals(userType)) {
                System.out.println(userType + ": " + user.getID());
            }
        }
    }
    public void addUser(User newUser) {
    userList.add(newUser);
    System.out.println("User added successfully.");
    saveUsers();
}
}