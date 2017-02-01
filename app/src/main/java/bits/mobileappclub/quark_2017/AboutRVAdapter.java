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
 * Created by shubhamk on 1/2/17.
 */

public class AboutRVAdapter extends RecyclerView.Adapter<AboutRVAdapter.ViewHolder> {

    Context context;
    Vector<AboutItemFormat> aboutItemFormats;

    //Constructor

    public AboutRVAdapter(Context context, Vector<AboutItemFormat> aboutItemFormats) {
        this.context = context;
        this.aboutItemFormats = aboutItemFormats;
    }

    @Override
    public AboutRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.about_item_format, parent, false);

        // Return a new holder instance
        return new AboutRVAdapter.ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(AboutRVAdapter.ViewHolder holder, int position) {
        holder.name.setText(aboutItemFormats.get(position).getName());
        holder.number.setText(aboutItemFormats.get(position).getPhone());
        holder.email.setText(aboutItemFormats.get(position).getEmail());
        holder.post.setText(aboutItemFormats.get(position).getPost());
        int Imageid = aboutItemFormats.get(position).getId();
        holder.image.setImageURI("res:///" + Imageid);
    }

    @Override
    public int getItemCount() {
        return aboutItemFormats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView image;
        TextView name, email, number, post;


        public ViewHolder(View itemView) {
            super(itemView);
            image = (SimpleDraweeView) itemView.findViewById(R.id.item_format_about_image);
            name = (TextView) itemView.findViewById(R.id.item_format_about_name);
            number = (TextView) itemView.findViewById(R.id.item_format_about_phone);
            email = (TextView) itemView.findViewById(R.id.item_format_about_email);
            post = (TextView) itemView.findViewById(R.id.item_format_about_designation);


        }
    }
}
