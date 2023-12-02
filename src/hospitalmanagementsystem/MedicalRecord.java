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
   
  public MedicalRecord(String documentId,Procedure procedure,String medicalNotes){
  super(documentId);
  this.procedure=procedure;
  this.medicalNotes=new ArrayList<>();
  addMedicalNotes(medicalNotes);
  }
  
    public void addMedicalNotes(String notes) {
        medicalNotes.add(notes);
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
  
public void printMedicalRecord(Medicine medicine) {
    System.out.println("Medical Record:");
    System.out.println("Document ID: " + getDocumentId());
    System.out.println("Procedure: " + procedure.getName());
//    System.out.println("Doctor: " + doctor.getName());
//    System.out.println("Medicine: " + medicine.getName());
    System.out.println("Medical Notes:");
    for (String note : medicalNotes) {
        System.out.println("- " + note);
    }
}
  
}

