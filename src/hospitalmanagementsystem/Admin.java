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
    
    public void addUser(User newUser){
        //Smthn smthn 

    }
    public void deleteUser(String ID){
        AuthenticationManager authManager = new AuthenticationManager("(SOMEPATH).txt");
        Scanner scanner = new Scanner(System.in);
        
        boolean userExists = authManager.userLookUp(ID);
        if (userExists){
            try{
                Path filePath = Path.of("(SOMEPATH).text");
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
    
    
    public void updateUser(User user){
        
    }
    
    
}
