/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */
public class Receptionist extends User{
    public Receptionist(){
        super(); 
    }
    
    public Receptionist(String firstName, String lastName, String ID, int age, char gender, String phoneNumber, String password, String type){
        super(firstName, lastName, ID, age, gender, phoneNumber, password, type);
    }
    
    
    public List<Appointment> viewAppointments(){
        //sm code
    }
    
    public void scheduleAppointment(Patient patient, Date date, Procedure procedure, Room room){
        
    }
    
    public List<Room> checkAvailableRooms(){
        //sm code
    }
}
