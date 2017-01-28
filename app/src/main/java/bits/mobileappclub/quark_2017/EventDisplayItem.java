package bits.mobileappclub.quark_2017;


/**
 * Created by shubhamk on 28/1/17.
 */

public class EventDisplayItem {
    private String imageurl;
    private String title;
    private String desc;
    private String pdflink;

    public EventDisplayItem(String imageurl, String title, String desc, String pdflink) {
        this.imageurl = imageurl;
        this.title = title;
        this.desc = desc;
        this.pdflink = pdflink;
    }

    public EventDisplayItem() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPdflink() {
        return pdflink;
    }

    public void setPdflink(String pdflink) {
        this.pdflink = pdflink;
    }


    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}
