package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 1/2/17.
 */

public class ExhibitionsItemFormat {
    String imageurl;
    String title;
    String desc;

    public ExhibitionsItemFormat(String imageurl, String title, String desc) {
        this.imageurl = imageurl;
        this.title = title;
        this.desc = desc;
    }

    public ExhibitionsItemFormat() {
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {

        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {

        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
