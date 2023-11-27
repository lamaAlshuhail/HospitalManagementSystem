/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;


import java.util.Date;
public abstract class  Procedure {
    private String code; 
    private String name;
    private long duration;
    private Date scheduledDate;
    private Date scheduledTime;
    
    
    public Procedure(){
    }
    
    
    public Procedure(String code, String name, long duration){
        this.code = code;
        this.name = name;
        this.duration = duration;
    }
    
    
    
    public String getCode(){
        return this.code;
    }
    
    public void setCode(String code){
        this.code = code;    
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String name){
        this.name = name;    
    }
    
    public long getDuration(){
        return this.duration;
    }

    
    public void setDuration(long duration){
        this.duration = duration;    
    }
    
  
    public Date getScheduledDate(){
        return this.scheduledDate;
    }
    
    
    public void setscheduledDate(Date scheduledDate){
        this.scheduledDate = scheduledDate;    
    }
    
    
     public Date getScheduledTime(){
        return this.scheduledTime;
    }
    
    
    public void setscheduledTime(Date scheduledTime){
        this.scheduledTime = scheduledTime;    
    }
    
    public void scheduledTest(Date date, Date time){
    System.out.print("The scheduled Test:\t Date:" +getScheduledDate() + "  Time: "+ getScheduledTime());
    }
    
    @Override
    public String toString() {
        return "Procedure Name: " + name + "\nProcedure code: " + code + "\nProcedure duration: " + duration;
    }    

}