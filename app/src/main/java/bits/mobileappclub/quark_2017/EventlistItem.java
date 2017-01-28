package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 28/1/17.
 */

public class EventlistItem {
    private String title;
    private String imageurl;
    private EventDisplayItem eventDisplayItem;

    public EventlistItem(String title, String imageurl, EventDisplayItem eventDisplayItem) {
        this.title = title;
        this.imageurl = imageurl;
        this.eventDisplayItem = eventDisplayItem;
    }

    public EventlistItem() {

    }

    public String getImageurl() {
        if (eventDisplayItem != null) return eventDisplayItem.getImageurl();
        else return "";
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        if (eventDisplayItem != null) return eventDisplayItem.getTitle();
        else return "";
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public EventDisplayItem getEventDisplayItem() {
        return eventDisplayItem;
    }

    public void setEventDisplayItem(EventDisplayItem eventDisplayItem) {
        this.eventDisplayItem = eventDisplayItem;
    }
}
