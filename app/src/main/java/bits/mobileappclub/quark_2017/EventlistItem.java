package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 28/1/17.
 */

public class EventlistItem {
    private String title;
    private EventDisplayItem eventDisplayItem;

    public EventlistItem(String title, EventDisplayItem eventDisplayItem) {
        this.title = title;
        this.eventDisplayItem = eventDisplayItem;
    }

    public EventlistItem() {

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
