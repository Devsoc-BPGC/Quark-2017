package bits.mobileappclub.quark_2017;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Vector;

import static android.R.id.list;

/**
 * Created by shubhamk on 18/1/17.
 */

public class WorkshopRVAdapter extends RecyclerView.Adapter<WorkshopRVAdapter.ViewHolder> {
    Context context;
    Vector<WorkshopListItem> workshopListItems;

    public WorkshopRVAdapter(Context context, Vector<WorkshopListItem> workshopListItems) {
        this.context = context;
        this.workshopListItems = workshopListItems;
    }

    @Override
    public WorkshopRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.workshop_item_format, parent, false);
        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(WorkshopRVAdapter.ViewHolder holder, int position) {
        holder.workshopImg.setImageURI(Uri.parse(workshopListItems.get(position).getImageurl()));
    }

    @Override
    public int getItemCount() {
        return workshopListItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView workshopImg;

        public ViewHolder(View itemView) {
            super(itemView);
            workshopImg = (SimpleDraweeView) itemView.findViewById(R.id.item_format_workshop_image);
            CircleImageDrawable cid = new CircleImageDrawable();
            cid.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
            workshopImg.getHierarchy().setProgressBarImage(cid);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,WorkshopDetails.class);

                WorkshopItemformat workshopItemformat1;
                workshopItemformat1 = workshopListItems.get(getAdapterPosition()).getWorkshopItemformat();

                //intent.putExtra("Workshop", workshopItemformat1);
                intent.putExtra("desc",workshopItemformat1.getDesc());
                intent.putExtra("name",workshopItemformat1.getTitle());
                intent.putExtra("fees",workshopItemformat1.getFees());
                intent.putExtra("image",workshopItemformat1.getImageurl());
                intent.putExtra("pdf",workshopItemformat1.getPdflink());
                context.startActivity(intent);
                if (context instanceof WorkshopDetails) {
                    ((WorkshopDetails) context).finish();
                }
            }
        });

        }
    }
}