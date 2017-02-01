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

public class AboutQuark extends AppCompatActivity {
    AboutRVAdapter adapter;
    private Vector<AboutItemFormat> aboutItemFormats = new Vector<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_quark);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.content_about_rv);
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
        recyclerView.setHasFixedSize(true);
        adapter = new AboutRVAdapter(this, aboutItemFormats);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        prepareAboutUsData();

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void prepareAboutUsData() {

        AboutItemFormat item = new AboutItemFormat("Siddhartha Yadav ", "+91-9552312005 ", "siddhartha@bits-quark.org", R.drawable.siddhartha, "Convener");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Yash Singhal ", "+91-7722047558 ", "singhal@bits-quark.org", R.drawable.yash, "Chief Coordinator");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Harsh Patel ", "+91-7046776276 ", "harsh@bits-quark.org", R.drawable.harsh, "Head, Publicity and Public Relations");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Manas Jha", " +91-9637888670 ", "manas@bits-quark.org", R.drawable.manas, "Head, Publicity and Public Relations");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Kinjal Motwani ", "+91-9657791242 ", "kinjal@bits-quark.org", R.drawable.kinjal, "Head, Sponsorship and Marketing");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Amith Plankhze ", "+91-7722048336", "amith@bits-quark.org", R.drawable.amith, "Head, Sponsorship and Marketing");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Wahid Patel ", "+91-8460640271 ", "wahid@bits-quark.org", R.drawable.wahid, "Head, Lectures and Exhibits");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Harikrishnan R. ", "+91-7774054512 ", " hari@bits-quark.org", R.drawable.hari, "Head, Aurora and Research");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Akash Deep ", "+91-7038707486 ", "akashdeep@bits-quark.org", R.drawable.akash, "Head, Workshops and CSR");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Apeksha Jain ", "+91-9158031113 ", "apeksha@bits-quark.org", R.drawable.apeksha, "Head, Media");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Samariddhi ", "+91-7720982150  ", "samariddhi@bits-quark.org", R.drawable.sam, "Head, Media");
        aboutItemFormats.add(item);
        item = new AboutItemFormat("Swati Shikha  ", "", "swati@bits-quark.org", R.drawable.swati, "Head, Design");
        aboutItemFormats.add(item);
        adapter.notifyDataSetChanged();

    }
}
