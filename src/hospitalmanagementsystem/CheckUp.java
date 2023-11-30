package hospitalmanagementsystem;

import java.util.Date;

public class CheckUp extends Procedure {
    private Room checkupLocation;
    private Doctor checkupDoctor;

    public CheckUp(String code, String name, long duration, Date scheduledDate, Room checkupLocation, Doctor checkupDoctor) {
        super(code, name, duration);
        setScheduledDate(scheduledDate);
        this.checkupLocation = checkupLocation;
        this.checkupDoctor = checkupDoctor;
    }

    public Room getCheckupLocation() {
        return checkupLocation;
    }

    public void setCheckupLocation(Room location) {
        this.checkupLocation = location;
    }

    public Doctor getCheckupDoctor() {
        return checkupDoctor;
    }

    public void setCheckupDoctor(Doctor doctor) {
        this.checkupDoctor = doctor;
    }

    @Override
    public void scheduledTest(Date date, Date time) {
        setScheduledDate(date);
        setScheduledTime(time);
        System.out.println("Scheduled Check-Up:\nDate: " + getScheduledDate() + "\nTime: " + getScheduledTime());
    }

    @Override
    public String toString() {
        return super.toString() + "\nLocation: " + checkupLocation + "\nDoctor: " + checkupDoctor;
    }
}