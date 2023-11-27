/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MedicalRecord extends Document {
  private List<String> medicalNotes;
  private Procedure procedure;
  
  
//   public MedicalRecord() {
//        super();
//        medicalNotes = new ArrayList<>();
//    }
   
  public MedicalRecord(String documentId,Procedure procedure,List<String> medicalNotes, Date creationDate){
  super(documentId,creationDate);
  this.procedure=procedure;
  this.medicalNotes=new ArrayList<>();
  }
  
  public List<String> getMedicalNotes(){
  return this.medicalNotes;
  }
   
 public void setMedicalNotes(List<String>  medicalNotes){
 this.medicalNotes=medicalNotes;
 }
 
 public Procedure getProcedure(){
 return this.procedure;
 }
 
  public void setProcedure(){
 this.procedure=procedure;
 }
  
  public void printMedicalRecord(Doctor doctor, Medicine medicine){
  //code to print medicalrecored
  }
  
}

