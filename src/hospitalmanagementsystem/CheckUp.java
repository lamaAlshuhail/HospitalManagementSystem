package hospitalmanagementsystem;

import java.util.Date;

public class CheckUp extends Procedure {
    private Room checkupLocation;
    private Doctor checkupDoctor;

    public CheckUp(String code, String name, long duration, Date scheduledDate, Room checkupLocation, Doctor checkupDoctor) {
        super(code, name, duration);
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
}