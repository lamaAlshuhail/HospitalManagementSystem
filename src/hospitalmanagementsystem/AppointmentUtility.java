///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package hospitalmanagementsystem;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AppointmentUtility {
//    
//    public static List<Appointment> loadAppointmentsFromFile(String filename) {
//        List<Appointment> appointments = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                try {
//                    Appointment appointment = parseAppointment(line);
//                    appointments.add(appointment);
//                } catch (Exception e) {
//                    System.out.println("Error: " + e.getMessage());
//                }
//            }
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//        return appointments;
//    }
//
//
//}