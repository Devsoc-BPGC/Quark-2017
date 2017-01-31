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
 * Created by shubhamk on 1/2/17.
 */

public class ExhibitionsRVAdapter extends RecyclerView.Adapter<ExhibitionsRVAdapter.ViewHolder> {
    Context context;
    Vector<ExhibitionsItemFormat> exhibitionsItemFormats;

    //Constructor

    public ExhibitionsRVAdapter(Context context, Vector<ExhibitionsItemFormat> exhibitionsItemFormats) {
        this.context = context;
        this.exhibitionsItemFormats = exhibitionsItemFormats;
    }

    @Override
    public ExhibitionsRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.exhibitions_item_format, parent, false);

        // Return a new holder instance
        return new ExhibitionsRVAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(ExhibitionsRVAdapter.ViewHolder holder, int position) {
        holder.title.setText(exhibitionsItemFormats.get(position).getTitle());
        holder.image.setImageURI(Uri.parse(exhibitionsItemFormats.get(position).getImageurl()));
        holder.desc.setText(exhibitionsItemFormats.get(position).getDesc());
    }

    @Override
    public int getItemCount() {
        return exhibitionsItemFormats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        SimpleDraweeView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.ex_title);
            image = (SimpleDraweeView) itemView.findViewById(R.id.exhibition_image_src);
            desc = (TextView) itemView.findViewById(R.id.ex_details);
        }
    }
}
