package bits.mobileappclub.quark_2017;

/**
 * Created by shubhamk on 24/1/17.
 */

public class SponsorItemFormat {
    String title;
    String url;
    String imageurl;

    public SponsorItemFormat(String title, String url, String imageurl) {
        this.title = title;
        this.url = url;
        this.imageurl = imageurl;
    }

    public SponsorItemFormat() {
        this.title = "";
        this.url = "";
        this.imageurl = "";

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

}
