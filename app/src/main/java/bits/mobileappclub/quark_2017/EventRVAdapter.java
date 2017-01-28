package bits.mobileappclub.quark_2017;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Vector;

/**
 * Created by shubhamk on 26/1/17.
 */

public class EventRVAdapter extends RecyclerView.Adapter<EventRVAdapter.ViewHolder> {
    Context context;
    Vector<EventItemFormat> eventItemFormats;

    //Constructor

    public EventRVAdapter(Context context, Vector<EventItemFormat> eventItemFormats) {
        this.context = context;
        this.eventItemFormats = eventItemFormats;
    }

    @Override
    public EventRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.event_item_format, parent, false);

        // Return a new holder instance
        return new EventRVAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(EventRVAdapter.ViewHolder holder, int position) {
        holder.eventname.setText(eventItemFormats.get(position).getTitle());
        int Imageid = eventItemFormats.get(position).getImageid();
        holder.eventimage.setImageURI("res:///" + Imageid);
    }

    @Override
    public int getItemCount() {
        return eventItemFormats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView eventimage;
        TextView eventname;

        public ViewHolder(View itemView) {
            super(itemView);
            eventimage = (SimpleDraweeView) itemView.findViewById(R.id.event_item_format_image);
            eventname = (TextView) itemView.findViewById(R.id.event_item_format_text);
        }
    }
}
