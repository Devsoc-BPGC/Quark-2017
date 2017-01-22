package bits.mobileappclub.quark_2017;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;


public class DayTwoNights extends Fragment {
    Vector<NightsItemFormat> nightsItemFormats = new Vector<>();
    private NightsRVAdapter adapter;
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("Nights").child("Day2");
    private ProgressBar progressBar;
    public DayTwoNights() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_day_two_nights, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.content_daytwonight_rv);
        progressBar = (ProgressBar) view.findViewById(R.id.content_daytwonight_progress);
        databaseReference.keepSynced(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new NightsRVAdapter(getContext(), nightsItemFormats);
        recyclerView.setAdapter(adapter);
    return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressBar.setVisibility(VISIBLE);
                nightsItemFormats.clear();
                for (DataSnapshot shot : dataSnapshot.getChildren()) {
                    nightsItemFormats.add(shot.getValue(NightsItemFormat.class));
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
