package bits.mobileappclub.quark_2017;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class EventList extends AppCompatActivity {
    EventlistRVAdapter adapter;
    private DatabaseReference databaseReference;
    private Vector<EventlistItem> eventlistItems = new Vector<>();
    private RecyclerView recyclerView;
    private Vector<EventDisplayItem> eventDisplayItems = new Vector<>();
    private ProgressBar progressBar;
    private SimpleDraweeView background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        recyclerView = (RecyclerView) findViewById(R.id.content_event_list_rv);
        progressBar = (ProgressBar) findViewById(R.id.content_event_list_progress);
        background = (SimpleDraweeView) findViewById(R.id.content_event_list_background);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
            getWindow().setStatusBarColor(colorPrimary);
            getWindow().setNavigationBarColor(colorPrimary);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        String category = getIntent().getStringExtra("Category");
        if (category != null) {
            getSupportActionBar().setTitle(category);
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Events").child(category);
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventlistRVAdapter(this, eventlistItems);
        recyclerView.setAdapter(adapter);
        databaseReference.keepSynced(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(VISIBLE);
                eventDisplayItems.clear();
                eventlistItems.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    eventDisplayItems.add(shot.getValue(EventDisplayItem.class));
                }
                for (int i = 0; i < eventDisplayItems.size(); i++) {
                    eventlistItems.add(new EventlistItem(eventDisplayItems.get(i).getTitle(), eventDisplayItems.get(i).getImageurl(), eventDisplayItems.get(i)));
                }
                background.setImageURI(Uri.parse(eventDisplayItems.get(0).getImageurl()));
                //background.setImageURI(Uri.parse("http://more-sky.com/data/out/6/IMG_85570.png"));
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(INVISIBLE);
                Log.e("TAG", databaseError.getDetails());
            }
        });


    }
}
