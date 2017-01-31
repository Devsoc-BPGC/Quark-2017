package bits.mobileappclub.quark_2017;

import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.provider.CalendarContract;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by shubhamk on 24/1/17.
 */

public class LectureRVAdapter extends RecyclerView.Adapter<LectureRVAdapter.ViewHolder> {
    Context context;
    Vector<LectureItemFormat> lectureItemFormats;

    //Constructor

    public LectureRVAdapter(Context context, Vector<LectureItemFormat> lectureItemFormats) {
        this.context = context;
        this.lectureItemFormats = lectureItemFormats;
    }

    @Override
    public LectureRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.lecture_item_format, parent, false);

        // Return a new holder instance
        return new LectureRVAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(LectureRVAdapter.ViewHolder holder, int position) {
        holder.title.setText(lectureItemFormats.get(position).getTitle());
        holder.location.setText(lectureItemFormats.get(position).getLocation());

        holder.imageSrc.setImageURI(Uri.parse(lectureItemFormats.get(position).getImageUrl()));

        holder.time.setText(lectureItemFormats.get(position).getTime());
        holder.details.setText(lectureItemFormats.get(position).getDetails());
        try {
            int x = Color.parseColor(lectureItemFormats.get(position).getColor());
            holder.card.setCardBackgroundColor(x);
        } catch (Exception e) {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.black));
        }
    }

    @Override
    public int getItemCount() {
        return lectureItemFormats.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView location;
        public SimpleDraweeView imageSrc;
        public TextView time;
        public TextView details;
        public CardView card;
        public TextView reminder;

        public ViewHolder(final View itemView) {
            super(itemView);

            //References

            title = (TextView) itemView.findViewById(R.id.lecture_title);
            location = (TextView) itemView.findViewById(R.id.lecture_location_name);
            imageSrc = (SimpleDraweeView) itemView.findViewById(R.id.lecture_image_src);
            time = (TextView) itemView.findViewById(R.id.lecture_time);
            details = (TextView) itemView.findViewById(R.id.lecture_details_pro);
            card = (CardView) itemView.findViewById(R.id.lecture_card);
            reminder = (TextView) itemView.findViewById(R.id.lecture_reminder);
            imageSrc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleAlertDialog alert = new SimpleAlertDialog();
                    alert.showDialog(context, "Alert", "Do you want to download the image?", "YES", "NO", "HI", true, true, false);
                    alert.setClickListener(new SimpleAlertDialog.ClickListener() {
                        @Override
                        public void onPosButtonClick() {
                            Uri link = Uri.parse(lectureItemFormats.get(getAdapterPosition()).getImageUrl());
                            DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                            DownloadManager.Request request = new DownloadManager.Request(link)
                                    .setDescription("Downloading")
                                    .setMimeType("image/*")
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setVisibleInDownloadsUi(true)
                                    .setTitle(lectureItemFormats.get(getAdapterPosition()).getTitle() + " Poster Image");
                            long id = downloadManager.enqueue(request);
                        }

                        @Override
                        public void onNegButtonClick() {

                        }

                        @Override
                        public void onNeuButtonClick() {

                        }
                    });
                }
            });

            reminder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        long tempStartTime, miliSecsDate;
                        miliSecsDate = 0;
                        CharSequence charSequence = lectureItemFormats.get(getAdapterPosition()).getRemindertime();
                        int temp = Integer.parseInt(charSequence.toString());
                        int dayIndex = lectureItemFormats.get(getAdapterPosition()).getDay();
                        if (dayIndex == 0)
                            miliSecsDate = milliseconds("2017-02-02");
                        else if (dayIndex == 1)
                            miliSecsDate = milliseconds("2017-02-03");
                        else if (dayIndex == 2)
                            miliSecsDate = milliseconds("2017-02-04");
                        else if (dayIndex == 3)
                            miliSecsDate = milliseconds("2017-02-05");

                        tempStartTime = miliSecsDate + ((temp / 100 - 1) * 3600 * 1000) + (temp % 100 * 60 * 1000) + 3600000;

                        Intent intent = new Intent(Intent.ACTION_INSERT);
                        intent.setData(CalendarContract.Events.CONTENT_URI);
                        if (tempStartTime >= System.currentTimeMillis())
                            intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, tempStartTime);
                        intent.putExtra(CalendarContract.Events.TITLE, lectureItemFormats.get(getAdapterPosition()).getTitle());
                        intent.putExtra(CalendarContract.Events.DESCRIPTION, lectureItemFormats.get(getAdapterPosition()).getDetails());
                        intent.putExtra(CalendarContract.Events.EVENT_LOCATION, lectureItemFormats.get(getAdapterPosition()).getLocation());
                        context.startActivity(intent);

                    } catch (ActivityNotFoundException e) {
                        Snackbar.make(view, "No calendar found", Snackbar.LENGTH_LONG).show();
                        Log.e("TAG", "No calendar app exists!");
                    } catch (Exception e) {
                        Toast.makeText(context, "Sorry could not open calendar. ", Toast
                                .LENGTH_SHORT).show();
                        Log.e("TAG", "exception during addiing reminder in event");

                    }
                }
            });

        }

        public long milliseconds(String date) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
            try {
                Date mDate = sdf.parse(date);
                return mDate.getTime();
            } catch (ParseException e) {
                Log.e("TAG", "Parse exception in date");
            }
            return 0;
        }
    }
}
