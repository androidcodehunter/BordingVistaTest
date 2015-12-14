package sharif.bordingvistatestapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Acer on 12/14/2015.
 */
public class Utils {


    /**
     * Check if network is available or not.
     *
     * @param context The context to be passed.
     * */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectionManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectionManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }


}
