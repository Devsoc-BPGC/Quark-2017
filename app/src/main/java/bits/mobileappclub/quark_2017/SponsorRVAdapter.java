package bits.mobileappclub.quark_2017;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Vector;

/**
 * Created by shubhamk on 24/1/17.
 */

public class SponsorRVAdapter extends RecyclerView.Adapter<SponsorRVAdapter.ViewHolder> {
    Context context;
    Vector<SponsorItemFormat> sponsorItems;

    public SponsorRVAdapter(Context context, Vector<SponsorItemFormat> sponsorItems) {

        this.context = context;
        this.sponsorItems = sponsorItems;

    }

    @Override
    public SponsorRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.sponsor_item_format, parent, false);
        final ViewHolder viewHolder = new ViewHolder(contactView);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent viewIntent =
                        new Intent(Intent.ACTION_VIEW,
                                Uri.parse(sponsorItems.get(viewHolder.getAdapterPosition()).getUrl()));
                context.startActivity(Intent.createChooser(viewIntent, "Choose browser"));
            }
        });
        // Return a new holder instance
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SponsorRVAdapter.ViewHolder holder, int position) {
        holder.simpleDraweeView.setImageURI(Uri.parse(sponsorItems.get(position).getImageurl()));
        holder.textView.setText(sponsorItems.get(position).getTitle());
    }


    @Override
    public int getItemCount() {

        return sponsorItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView simpleDraweeView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sponsor_item_format_image);
            textView = (TextView) itemView.findViewById(R.id.sponsor_item_format_text);
            CircleImageDrawable cid = new CircleImageDrawable();
            cid.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
            simpleDraweeView.getHierarchy().setProgressBarImage(cid);
        }
    }
}
