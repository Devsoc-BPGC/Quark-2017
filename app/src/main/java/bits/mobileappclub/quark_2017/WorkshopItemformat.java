package bits.mobileappclub.quark_2017;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by shubhamk on 18/1/17.
 */

public class WorkshopItemformat implements Parcelable {
    public static final Creator<WorkshopItemformat> CREATOR = new Creator<WorkshopItemformat>() {
        @Override
        public WorkshopItemformat createFromParcel(Parcel source) {
            return new WorkshopItemformat(source);
        }

        @Override
        public WorkshopItemformat[] newArray(int size) {
            return new WorkshopItemformat[size];
        }
    };
    private String imageurl;
    private String title;
    private String desc;
    private String fees;
    private String pdflink;

    public WorkshopItemformat(String imageurl, String title, String desc, String fees, String pdflink) {
        this.imageurl = imageurl;
        this.title = title;
        this.desc = desc;
        this.fees = fees;
        this.pdflink = pdflink;
    }
    public WorkshopItemformat(){
    }
    protected WorkshopItemformat(Parcel in){
        this.title = in.readString();
        this.imageurl = in.readString();
        this.desc = in.readString();
        this.fees = in.readString();
        this.pdflink = in.readString();
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

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.desc);
        parcel.writeString(this.fees);
        parcel.writeString(this.imageurl);
        parcel.writeString(this.pdflink);
    }
}
