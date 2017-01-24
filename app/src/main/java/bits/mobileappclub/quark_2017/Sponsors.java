package bits.mobileappclub.quark_2017;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

public class Sponsors extends AppCompatActivity {
    SponsorRVAdapter adapter;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Sponsor");
    private Vector<SponsorItemFormat> sponsorItems = new Vector<>();
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsors);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.content_sponsors_rv);
        progressBar = (ProgressBar) findViewById(R.id.content_sponsors_progress);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
            getWindow().setStatusBarColor(colorPrimary);
            getWindow().setNavigationBarColor(colorPrimary);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        //setup recyclerview
        recyclerView.setHasFixedSize(true);
        adapter = new SponsorRVAdapter(this, sponsorItems);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 0) {
                    return 2;
                } else return 1;
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        //Keep gallery database synced with firebase
        databaseReference.keepSynced(true);

    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(VISIBLE);
                sponsorItems.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    SponsorItemFormat item = new SponsorItemFormat();
                    try {
                        item.setImageurl(shot.child("imageurl").getValue(String.class));
                    } catch (Exception e) {
                        Log.e("TAG", "sponsor url error");
                        item.setImageurl("https://www.thersa.org/globalassets/signposts/enso-discover_600by280px.jpg");
                    }
                    try {
                        item.setTitle(shot.child("title").getValue(String.class));
                    } catch (Exception e) {
                        Log.e("TAG", "sponsor title error");
                        item.setTitle("Sponsor");
                    }
                    try {
                        item.setUrl(shot.child("url").getValue(String.class));
                    } catch (Exception e) {
                        Log.e("TAG", "sponsor link error");
                        item.setUrl("bits-quark.org");
                    }
                    sponsorItems.add(item);
                }
                //Need to notify adapter of data set change
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(INVISIBLE);
            }
        });

    }
}
