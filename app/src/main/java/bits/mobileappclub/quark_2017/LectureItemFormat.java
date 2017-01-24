package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 24/1/17.
 */

public class LectureItemFormat {
    private String title;
    private String location;
    private String time;
    private String remindertime;
    private String imageUrl;
    private String details;
    private String color;
    private int day;

    public LectureItemFormat() {
        this.title = "Event Title";
        this.location = "B-Dome Stage";
        this.time = "21:00";
        this.remindertime = "2100";
        this.details = "Details";
        this.imageUrl = "";
        this.day = 1;
        this.color = "#000000";
    }

    public LectureItemFormat(String title, String time, String location, String imageUrl, String details, String color, int day, String remindertime) {
        this.title = title;
        this.time = time;
        this.location = location;
        this.imageUrl = imageUrl;
        this.details = details;
        this.color = color;
        this.day = day;
        this.remindertime = remindertime;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRemindertime() {
        return remindertime;
    }

    public void setRemindertime(String remindertime) {
        this.remindertime = remindertime;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
