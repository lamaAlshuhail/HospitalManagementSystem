/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalmanagementsystem;

/**
 *
 * @author lamashuhail
 */
public class DiagnosticTest extends Procedure{
    private String result;
    private boolean testStatus;
    
    public DiagnosticTest(){
        super();
    }
    
    public DiagnosticTest(String code, String name, long duration, String result, boolean state){
        super();
        this.result = result;
        this.testStatus = testStatus;
        
    }
    
    public String getResult() {
        return this.result;
    }

    
    public void setResult(String result){
        this.result= result;    
    }
    
     public boolean getTestStatus() {
        return this.testStatus;
    }

    
    public void setTestStatus(boolean testStatus){
        this.testStatus= testStatus;    
    }
 
    public String cancelTest(){
            return null;    
    }
    
    @Override
    public String toString() {
        return super.toString() +"\nProcedure result: " + result + "\nProcedure Status:" + testStatus;   }    

}
