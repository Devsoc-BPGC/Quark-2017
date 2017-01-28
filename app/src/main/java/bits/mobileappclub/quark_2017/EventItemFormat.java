package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 26/1/17.
 */

public class EventItemFormat {
    int imageid;
    String title;

    public EventItemFormat(int imageid, String title) {
        this.imageid = imageid;
        this.title = title;

    }

    public EventItemFormat() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }
}
