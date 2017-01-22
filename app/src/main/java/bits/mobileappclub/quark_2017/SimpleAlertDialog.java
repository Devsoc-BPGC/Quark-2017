package bits.mobileappclub.quark_2017;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;

/**
 * Created by shubhamk on 20/1/17.
 */

public class SimpleAlertDialog {
    Context context;
    String title;
    String message;
    private ClickListener listener;

    public SimpleAlertDialog() {
        listener = null;
    }

    // Assign the listener implementing events interface that will receive the events
    public void setClickListener(ClickListener listener) {
        this.listener = listener;
    }

    public void showDialog(
            Context context,
            String title,
            String message,
            String positiveButtonText,
            String negativeButtonText,
            String neutralButtonText,
            final boolean POSDisplay,
            final boolean NEGDisplay,
            final boolean NEUDisplay) {
        this.context = context;
        this.title = title;
        this.message = message;
        final AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle(title);
        ad.setMessage(message);

        if (POSDisplay) {

            ad.setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null)
                        listener.onPosButtonClick();
                }
            });
        }
        if (NEGDisplay)
            ad.setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null)
                        listener.onNegButtonClick();
                }
            });
        if (NEUDisplay)
            ad.setNeutralButton(neutralButtonText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (listener != null)
                        listener.onNeuButtonClick();
                }
            });
        final AlertDialog alertDialog = ad.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface arg0) {
                if (NEGDisplay)
                    alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(ContextCompat.getColor(ad.getContext(), R.color.colorPrimary));
                if (NEUDisplay)
                    alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL).setTextColor(ContextCompat.getColor(ad.getContext(), R.color.colorPrimaryDark));
                if (POSDisplay)
                    alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(ContextCompat.getColor(ad.getContext(), R.color.colorPrimary));
            }
        });
        alertDialog.show();


    }


    public interface ClickListener {
        // These methods are the different events and
        // need to pass relevant arguments related to the event triggered
        void onPosButtonClick();

        // or when data has been loaded
        void onNegButtonClick();

        // or when data has been loaded
        void onNeuButtonClick();

    }
}
