package bits.mobileappclub.quark_2017;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by shubhamk on 18/1/17.
 */

public class Quark extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //Mutlidex is used when the number of methods or method calls including android's extends
        // to more than 65536. It is often required in large projects
        //You don't need to bother about the usage, it handles everything on its own
        MultiDex.install(this);

    }

    @Override
    public void onCreate() {
        super.onCreate();
        //initialise Fresco when app starts
        //this will speed download process and is required by this library
        //more info http://frescolib.org/
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Fresco.initialize(this);
    }
}
