package bits.mobileappclub.quark_2017;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Vector;

/**
 * Created by shubhamk on 28/1/17.
 */

public class EventlistRVAdapter extends RecyclerView.Adapter<EventlistRVAdapter.ViewHolder> {
    Context context;
    private Vector<EventlistItem> eventlistItems;

    //Constructor

    EventlistRVAdapter(Context context, Vector<EventlistItem> eventlistItems) {
        this.context = context;
        this.eventlistItems = eventlistItems;
    }

    @Override
    public EventlistRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.event_list_item_format, parent, false);

        // Return a new holder instance
        return new EventlistRVAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(EventlistRVAdapter.ViewHolder holder, int position) {
        holder.eventtitle.setText(eventlistItems.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return eventlistItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView eventtitle;
        ViewHolder(View itemView) {
            super(itemView);
            eventtitle = (TextView) itemView.findViewById(R.id.event_list_item_format_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, EventDisplay.class);
                    EventDisplayItem eventDisplayItem;
                    eventDisplayItem = eventlistItems.get(getAdapterPosition()).getEventDisplayItem();
                    intent.putExtra("desc", eventDisplayItem.getDesc());
                    intent.putExtra("name", eventDisplayItem.getTitle());
                    intent.putExtra("image", eventDisplayItem.getImageurl());
                    intent.putExtra("pdf", eventDisplayItem.getPdflink());
                    context.startActivity(intent);
                    if (context instanceof EventDisplay) {
                        ((WorkshopDetails) context).finish();
                    }
                }
            });
        }
    }
}
