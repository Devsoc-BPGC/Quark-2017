package bits.mobileappclub.quark_2017;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
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

public class Workshops extends AppCompatActivity {
    WorkshopRVAdapter adapter;
    private DatabaseReference workshop = FirebaseDatabase.getInstance().getReference().child("Workshops");
    private Vector<WorkshopListItem> workshopListItems = new Vector<>();
    private RecyclerView workshopRecycleView;
    private Vector<WorkshopItemformat> workshopItemformats=new Vector<>();
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshops);
        workshopRecycleView = (RecyclerView) findViewById(R.id.content_workshop_rv);
        progressBar = (ProgressBar) findViewById(R.id.content_workshop_progress);
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
        workshopRecycleView.setHasFixedSize(true);
        workshopRecycleView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WorkshopRVAdapter(this,workshopListItems);
        workshopRecycleView.setAdapter(adapter);
        workshop.keepSynced(true);
    }
    @Override
    protected void onStart() {
        super.onStart();

        workshop.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("TAG","omg pro boy");
                progressBar.setVisibility(VISIBLE);
                workshopItemformats.clear();
                workshopListItems.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    workshopItemformats.add(shot.getValue(WorkshopItemformat.class));
                }
                for(int i=0;i<workshopItemformats.size();i++)
                {
                    workshopListItems.add(new WorkshopListItem(workshopItemformats.get(i).getImageurl(),workshopItemformats.get(i)));
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressBar.setVisibility(INVISIBLE);
                Log.e("TAG",databaseError.getDetails());
            }
        });


    }
}
