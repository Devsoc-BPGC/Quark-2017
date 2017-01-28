package bits.mobileappclub.quark_2017;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import java.util.Vector;

public class Event extends AppCompatActivity {
    private final String event_name[] = {
            "Design & Build", "Roboficial", "Initiatives", "Programmer's Inc", "Electrify", "Elixir", "Specials", "Corporate", "Matka",
            "Cubing", "BITSMUN"
    };
    private final int event_imageid[] = {
            R.drawable.designandbuild, R.drawable.roboficial, R.drawable.initiatives,
            R.drawable.programmerinc, R.drawable.electrify, R.drawable.elizer, R.drawable.spec,
            R.drawable.corporate, R.drawable.matka, R.drawable.cubing2, R.drawable.bitsmun
    };
    EventRVAdapter adapter;
    private Vector<EventItemFormat> eventItemFormats = new Vector<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        recyclerView = (RecyclerView) findViewById(R.id.content_event_rv);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        eventItemFormats = data();
        adapter = new EventRVAdapter(this, eventItemFormats);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    private Vector<EventItemFormat> data() {
        Vector<EventItemFormat> eventItemFormats1 = new Vector<>();
        for (int i = 0; i < 11; i++) {
            EventItemFormat eventItemFormat = new EventItemFormat();
            eventItemFormat.setImageid(event_imageid[i]);
            eventItemFormat.setTitle(event_name[i]);
            eventItemFormats1.add(eventItemFormat);

        }
        return eventItemFormats1;
    }
}
