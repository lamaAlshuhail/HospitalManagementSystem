package hospitalmanagementsystem;

import java.util.Date;

public class DiagnosticTest extends Procedure {
    private String result;
    private boolean testStatus;

    public DiagnosticTest(String code, String name, long duration, String result, boolean testStatus) {
        super(code, name, duration);
        this.result = result;
        this.testStatus = testStatus;
    }

    DiagnosticTest(String procedureCode, String procedureName) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean getTestStatus() {
        return testStatus;
    }

    public void setTestStatus(boolean testStatus) {
        this.testStatus = testStatus;
    }

    public String cancelTest() {
        // Add your implementation here
        return null;
    }

    @Override
    public void scheduledTest(Date date, Date time) {
        setScheduledDate(date);
        setScheduledTime(time);
        System.out.println("Scheduled Diagnostic Test:\nDate: " + getScheduledDate() + "\nTime: " + getScheduledTime());
    }

    @Override
    public String toString() {
        return super.toString() + "\nProcedure result: " + result + "\nProcedure status: " + testStatus;
    }
    
    
}