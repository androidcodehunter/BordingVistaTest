package sharif.bordingvistatestapp.utils;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import sharif.bordingvistatestapp.database.table.Department;

/**
 * Created by Acer on 12/14/2015.
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


    /**
     *
     * Pull all department data's from specific server url.
     *
     * @param url The url which is used to get server data.
     * */
    public void pullDataFromServer(String url) {
        JsonArrayRequest topicRequest;
        topicRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.e("insertion successful", " successful");

                        Log.e("Server response ", ""+ response.toString());

                        Department.getDepartmentsInfoFromJson(response);
                    }
                }, new Response.ErrorListener() {


            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }
        );
        addToRequestQueue(topicRequest);
    }
}
