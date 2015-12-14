package sharif.bordingvistatestapp.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import sharif.bordingvistatestapp.R;
import sharif.bordingvistatestapp.database.table.Department;
import sharif.bordingvistatestapp.utils.Constants;
import sharif.bordingvistatestapp.utils.HttpHelper;
import sharif.bordingvistatestapp.utils.Utils;

public class NetworkActivity extends AppCompatActivity {

    private HttpHelper mHttpHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        setupToolbar();

        mHttpHelper = HttpHelper.getInstance(this);

        setupGetNetworkDataButton();
    }


    /**
     * Set toolbar into app toolbar.
     */
    public void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }



    /**
     * Setup all things for network button. When this button is clicked, we will show pulled data to the user.
     * */
    private void setupGetNetworkDataButton() {
        Button buttonNetworkData = (Button) findViewById(R.id.button_get_network_data);

        buttonNetworkData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (!Utils.isNetworkAvailable(getBaseContext())){
                    Toast.makeText(getBaseContext(), getString(R.string.no_internet_connection_text),Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.e("TAG", "Button is clicked");

              //  mHttpHelper.pullDataFromServer(Constants.STORE_URL);


                JsonObjectRequest jsObjRequest = new JsonObjectRequest
                        (Request.Method.GET, Constants.STORE_URL, null, new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                               // mTxtDisplay.setText("Response: " + response.toString());
                                Log.e("Server response ", ""+ response.toString());
                            }
                        }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // TODO Auto-generated method stub
                                Log.e("Server response ", "error");
                            }
                        });



                mHttpHelper.addToRequestQueue(jsObjRequest);



                JsonArrayRequest topicRequest;
                topicRequest = new JsonArrayRequest(Constants.STORE_URL,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                Log.e("insertion successful", " successful");

                            }
                        }, new Response.ErrorListener() {


                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("insertion", " error");
                    }
                }
                );
                mHttpHelper.addToRequestQueue(topicRequest);


            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_network, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
