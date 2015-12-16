package sharif.bordingvistatestapp.utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by androidcodehunter on 12/14/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class HttpHelper {

    private RequestQueue mRequestQueue;
    private static Context mContext;
    private static HttpHelper mInstance;
    public static final String TAG = HttpHelper.class.getSimpleName();

    @SuppressWarnings("static-access")
    private HttpHelper(Context context) {
        this.mContext = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized HttpHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new HttpHelper(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext
                    .getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
