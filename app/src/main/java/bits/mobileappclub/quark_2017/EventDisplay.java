package bits.mobileappclub.quark_2017;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

public class EventDisplay extends AppCompatActivity {
    String name, pdf, image, desc;
    Context context;
    private TextView eventdetails;
    private Button pdfbutton;
    private SimpleDraweeView eventimage;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.event_details_toolbar);
        setSupportActionBar(toolbar);
        eventdetails = (TextView) findViewById(R.id.content_event_details_description);
        pdfbutton = (Button) findViewById(R.id.content_event_details_pdf_button);
        eventimage = (SimpleDraweeView) findViewById(R.id.activity_event_details_backdrop);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.event_details_collapsing_toolbar);
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
        name = getIntent().getStringExtra("name");
        desc = getIntent().getStringExtra("desc");
        image = getIntent().getStringExtra("image");
        pdf = getIntent().getStringExtra("pdf");
        if (name != null && desc != null && image != null && pdf != null) {
            getSupportActionBar().setTitle(name);

            eventdetails.setText(desc);
            String imageUrl = image;
            eventimage.setImageURI((Uri.parse(imageUrl)));
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
            pdfbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SimpleAlertDialog alert = new SimpleAlertDialog();
                    alert.showDialog(EventDisplay.this, "Alert", "Do you want to download the rulebook?", "YES", "NO", "HI", true, true, false);
                    alert.setClickListener(new SimpleAlertDialog.ClickListener() {
                        @Override
                        public void onPosButtonClick() {
                            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                            try {
                                downloadManager.enqueue(new DownloadManager.Request(Uri.parse(pdf))
                                        .setTitle(name + " Rulebook")
                                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                                        .setDescription("Event Rulebook"));

                            } catch (Exception e) {
                                Log.e("TAG", "link wrong");
                                Toast.makeText(getApplicationContext(), "Please try again later", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onNegButtonClick() {

                        }

                        @Override
                        public void onNeuButtonClick() {

                        }
                    });
                }
            });
        }

    }
}