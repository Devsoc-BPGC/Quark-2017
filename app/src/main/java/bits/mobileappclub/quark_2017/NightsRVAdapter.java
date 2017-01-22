package bits.mobileappclub.quark_2017;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Vector;

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

        public ViewHolder(final View itemView) {
            super(itemView);

            //References

            title = (TextView) itemView.findViewById(R.id.event_title);
            location = (TextView) itemView.findViewById(R.id.event_location_name);
            imageSrc = (SimpleDraweeView) itemView.findViewById(R.id.image_src);
            time = (TextView)itemView.findViewById(R.id.event_time);
            details = (TextView) itemView.findViewById(R.id.event_details_pro);


        }
    }
}
