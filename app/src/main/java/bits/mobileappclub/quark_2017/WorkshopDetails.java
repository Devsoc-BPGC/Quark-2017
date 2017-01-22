package bits.mobileappclub.quark_2017;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

public class WorkshopDetails extends AppCompatActivity {
    WorkshopItemformat workshopItemformat;
    private TextView workshopname;
    private TextView workshopdetails;
    private Button pdfbutton;
    private TextView fees;
    private SimpleDraweeView workshopimage;
    String name,pdf,image,wfees,desc ;
    private CollapsingToolbarLayout collapsingToolbarLayout;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context =getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshop_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.workshop_details_toolbar);
        setSupportActionBar(toolbar);
        workshopname = (TextView) findViewById(R.id.content_workshop_details_name);
        workshopdetails = (TextView) findViewById(R.id.content_workshop_details_description);
        fees = (TextView) findViewById(R.id.content_workshop_details_fee_text);
        pdfbutton = (Button) findViewById(R.id.content_workshop_details_pdf_button);
        workshopimage = (SimpleDraweeView) findViewById(R.id.activity_workshop_details_backdrop);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.workshop_details_collapsing_toolbar);

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
      // workshopItemformat= getIntent().getParcelableExtra("Workshop");

        name=getIntent().getStringExtra("name");
        desc=getIntent().getStringExtra("desc");
        wfees=getIntent().getStringExtra("fees");
        image=getIntent().getStringExtra("image");
        pdf=getIntent().getStringExtra("pdf");
if( name!=null &&desc!=null &&wfees!=null &&image!=null &&pdf!=null ){
    workshopname.setText(name);
            workshopdetails.setText(desc);
         fees.setText(wfees);
            String imageUrl = image;
            workshopimage.setImageURI((Uri.parse(imageUrl)));
            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
pdfbutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        SimpleAlertDialog alert = new SimpleAlertDialog();
        alert.showDialog(WorkshopDetails.this, "Alert", "Do you want to download the brochure?", "YES", "NO", "HI", true, true, false);
    alert.setClickListener(new SimpleAlertDialog.ClickListener() {
        @Override
        public void onPosButtonClick() {
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            try {
                downloadManager.enqueue(new DownloadManager.Request(Uri.parse(pdf))
                        .setTitle(name + " Brochure")
                        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                        .setDescription("Workshop Brochure"));

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

//        if (workshopItemformat == null)
//            Log.e("TAG", "null");
//        else {
//            workshopname.setText(workshopItemformat.getTitle());
//            workshopdetails.setText(workshopItemformat.getDesc());
//            fees.setText(workshopItemformat.getFees());
//            String imageUrl = workshopItemformat.getImageurl();
//            workshopimage.setImageURI((Uri.parse(imageUrl)));
//            collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
//
//        }

    }
}