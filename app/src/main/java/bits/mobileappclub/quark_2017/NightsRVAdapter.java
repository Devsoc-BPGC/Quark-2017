package bits.mobileappclub.quark_2017;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Vector;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * Created by shubhamk on 22/1/17.
 */

public class NightsRVAdapter extends RecyclerView.Adapter<NightsRVAdapter.ViewHolder> {
    Context context;
    Vector<NightsItemFormat> nightsItemFormats;

    //Constructor

    public NightsRVAdapter(Context context, Vector<NightsItemFormat> nightsItemFormats) {
        this.context = context;
        this.nightsItemFormats = nightsItemFormats;
    }

    @Override
    public NightsRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.nights_item_format, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(NightsRVAdapter.ViewHolder holder, int position) {
        holder.title.setText(nightsItemFormats.get(position).getTitle());
        holder.location.setText(nightsItemFormats.get(position).getLocation());

        holder.imageSrc.setImageURI(Uri.parse(nightsItemFormats.get(position).getImageUrl()));

        holder.time.setText(nightsItemFormats.get(position).getTime());
        holder.details.setText(nightsItemFormats.get(position).getDetails());
        try {
            int x = Color.parseColor(nightsItemFormats.get(position).getColor());
            holder.card.setCardBackgroundColor(x);
        } catch (Exception e) {
            holder.card.setCardBackgroundColor(context.getResources().getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return nightsItemFormats.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public TextView location;
        public SimpleDraweeView imageSrc;
        public TextView time;
        public TextView details;
        public CardView card;
        public ViewHolder(final View itemView) {
            super(itemView);

            //References

            title = (TextView) itemView.findViewById(R.id.event_title);
            location = (TextView) itemView.findViewById(R.id.event_location_name);
            imageSrc = (SimpleDraweeView) itemView.findViewById(R.id.image_src);
            time = (TextView)itemView.findViewById(R.id.event_time);
            details = (TextView) itemView.findViewById(R.id.event_details_pro);
            card = (CardView) itemView.findViewById(R.id.card);
            imageSrc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleAlertDialog alert = new SimpleAlertDialog();
                    alert.showDialog(context, "Alert", "Do you want to download the poster?", "YES", "NO", "HI", true, true, false);
                    alert.setClickListener(new SimpleAlertDialog.ClickListener() {
                        @Override
                        public void onPosButtonClick() {
                            Uri link = Uri.parse(nightsItemFormats.get(getAdapterPosition()).getImageUrl());
                            DownloadManager downloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
                            DownloadManager.Request request = new DownloadManager.Request(link)
                                    .setDescription("Downloading")
                                    .setMimeType("image/*")
                                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                    .setVisibleInDownloadsUi(true)
                                    .setTitle(nightsItemFormats.get(getAdapterPosition()).getTitle() + " Poster Image");
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


        }
    }
}
