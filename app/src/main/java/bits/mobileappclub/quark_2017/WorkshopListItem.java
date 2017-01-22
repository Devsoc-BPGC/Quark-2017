package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 20/1/17.
 */

public class WorkshopListItem {
    String imageurl;
    WorkshopItemformat workshopItemformat;

    public WorkshopListItem(String imageurl, WorkshopItemformat workshopItemformat) {
        this.imageurl = imageurl;
        this.workshopItemformat = workshopItemformat;
    }

    public String getImageurl() {
        if (workshopItemformat != null) return workshopItemformat.getImageurl();
        else return "";
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public WorkshopItemformat getWorkshopItemformat() {
        return workshopItemformat;
    }

    public void setWorkshopItemformat(WorkshopItemformat workshopItemformat) {
        this.workshopItemformat = workshopItemformat;
    }
}
