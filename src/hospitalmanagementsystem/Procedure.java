package hospitalmanagementsystem;

import java.util.Date;

public abstract class Procedure {
    private String code;
    private String name;
    private long duration;
    private Date scheduledDate;
    private Date scheduledTime;

    public Procedure() {
    }

    public Procedure(String code, String name, long duration) {
        this.code = code;
        this.name = name;
        this.duration = duration;
    }
    public Procedure(String code, String name) {
        this.code = code;
        this.name = name;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Date getScheduledDate() {
        return scheduledDate;
    }

    public void setScheduledDate(Date scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public Date getScheduledTime() {
        return scheduledTime;
    }

    public void setScheduledTime(Date scheduledTime) {
        this.scheduledTime = scheduledTime;
    }

    public abstract void scheduledTest(Date date, Date time);

    @Override
    public String toString() {
        return "Procedure Name: " + name + "\nProcedure code: " + code + "\nProcedure duration: " + duration;
    }
}