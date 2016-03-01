package geogit.com.sharewatcher;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by sandeep on 25/2/16.
 *
 * This class hold app configuration and constance
 */
public class ConfigAndConst {

    public static final String BASE_URL = "http://finance.google.com/finance/info?client=ig&q=NSE:";
    public static final int REFRESH_TIME_IN_MILLISECONDS = 60 *1000;

    private static ConfigAndConst mInstance = null;

    public boolean flagRefreshing = false;
    public boolean flagNetwork = false;

    private ConfigAndConst(){
    }

    public static ConfigAndConst getInstance(){
        if(mInstance == null) {
            mInstance = new ConfigAndConst();
        }
        return mInstance;
    }

    public  boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

}
