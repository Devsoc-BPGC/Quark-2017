package bits.mobileappclub.quark_2017;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    CardView card, card1, card2, card3;
    SimpleDraweeView main_nights, main_event, main_workshop, main_lecture;
    DatabaseReference pdfReference = FirebaseDatabase.getInstance().getReference().child("Schedule");
    String pdfLink = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        card = (CardView) findViewById(R.id.card2);
        card1 = (CardView) findViewById(R.id.card3);
        card2 = (CardView) findViewById(R.id.card4);
        card3 = (CardView) findViewById(R.id.card5);
        card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Event.class);
                startActivity(intent);
            }
        });
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Workshops.class);
                startActivity(intent);
            }
        });
        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LectureSeries.class);
                startActivity(intent);
            }
        });
        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Nights.class);
                startActivity(intent);
            }
        });
        main_nights = (SimpleDraweeView) findViewById(R.id.main_nights);
        main_event = (SimpleDraweeView) findViewById(R.id.main_event);
        main_workshop = (SimpleDraweeView) findViewById(R.id.main_workshop);
        main_lecture = (SimpleDraweeView) findViewById(R.id.main_lecture);
        main_nights.setImageURI(Uri.parse("res:///" + R.drawable.empty_event1));
        main_event.setImageURI(Uri.parse("res:///" + R.drawable.event_category));
        main_workshop.setImageURI(Uri.parse("res:///" + R.drawable.wrkshp));
        main_lecture.setImageURI(Uri.parse("res:///" + R.drawable.lectures));
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_events) {
            Intent intent = new Intent(MainActivity.this, Event.class);
            startActivity(intent);
        } else if (id == R.id.nav_maps) {
            Intent intent = new Intent(MainActivity.this, BPGCMapsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_workshop) {
            Intent intent = new Intent(MainActivity.this, Workshops.class);
            startActivity(intent);
        } else if (id == R.id.nav_nights) {
            Intent intent = new Intent(MainActivity.this, Nights.class);
            startActivity(intent);
        } else if (id == R.id.nav_sponsor) {
            Intent intent = new Intent(MainActivity.this, Sponsors.class);
            startActivity(intent);
        } else if (id == R.id.nav_lecture) {
            Intent intent = new Intent(MainActivity.this, LectureSeries.class);
            startActivity(intent);
        } else if (id == R.id.nav_ex) {
            Intent intent = new Intent(MainActivity.this, Exhibitions.class);
            startActivity(intent);
        } else if (id == R.id.nav_aboutq) {
            Intent intent = new Intent(MainActivity.this, AboutQuark.class);
            startActivity(intent);
        } else if (id == R.id.nav_mac) {
            Intent intent = new Intent(MainActivity.this, AboutMAC.class);
            startActivity(intent);
        } else if (id == R.id.nav_sch) {
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            try {
                downloadManager.enqueue(new DownloadManager.Request(Uri.parse(pdfLink))
                        .setTitle("Complete schedule")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDescription("Quark Schedule"));

            } catch (Exception e) {
                Log.e("TAG", "link wrong");
                Toast.makeText(this, "Please try again later", Toast.LENGTH_SHORT).show();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        pdfReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    pdfLink = dataSnapshot.getValue(String.class);
                    Log.e("TAG", "pdfLink " + pdfLink);
                } catch (Exception e) {
                    Log.e("TAG", "pdf rulebook " + dataSnapshot.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
