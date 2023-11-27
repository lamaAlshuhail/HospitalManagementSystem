/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */

public class Medicine {
    
private String medicineCode;
private String medicineName;
private double dose;
private int quantity;

public Medicine(){}
public Medicine(String medicineCode, String medicineName, double dose, int quantity){
 this.medicineCode=medicineCode;
 this.medicineName=medicineName;
 this.dose=dose;
 this.quantity=quantity;
}

public String getMedicineCode(){
return medicineCode;
}

public String getMedicineName(){
return medicineName;
}

public double getDose(){
return dose;
}

public int getQuantity(){
return quantity;
}

public void setMedicineCode(String medicineCode){
this.medicineCode=medicineCode;
}

public void setMedicineName(String medicineName){
this.medicineName=medicineName;
}

public void setDose(double dose){
this.dose=dose;
}

public void setQuantity(int quantity){
this.quantity=quantity;
}

}