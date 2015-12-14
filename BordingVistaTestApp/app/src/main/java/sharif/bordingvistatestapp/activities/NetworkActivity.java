package sharif.bordingvistatestapp.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

import sharif.bordingvistatestapp.R;
import sharif.bordingvistatestapp.adapters.DepartmentListAdapter;
import sharif.bordingvistatestapp.database.table.Department;
import sharif.bordingvistatestapp.utils.Constants;
import sharif.bordingvistatestapp.utils.HttpHelper;
import sharif.bordingvistatestapp.utils.Utils;
import sharif.bordingvistatestapp.view.SimpleItemDecoration;

/**
 * Created by androidcodehunter on 12/14/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class NetworkActivity extends AppCompatActivity {

    private HttpHelper mHttpHelper;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private DepartmentListAdapter mDepartmentListAdapter;

    private List<Department> mDepartmentList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        setupToolbar();

        mHttpHelper = HttpHelper.getInstance(this);

        setupGetNetworkDataButton();

        initDepartmentAdapter();
    }

    /**
     * Initialize the adapter for department.
     *
     * */
    private void initDepartmentAdapter() {

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mDepartmentList = Collections.EMPTY_LIST;

        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration =
                new SimpleItemDecoration(getResources().getDrawable(android.R.drawable.divider_horizontal_bright));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mDepartmentListAdapter = new DepartmentListAdapter();
        mRecyclerView.setAdapter(mDepartmentListAdapter);
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


                if (!Utils.isNetworkAvailable(getBaseContext())) {
                    Toast.makeText(getBaseContext(), getString(R.string.no_internet_connection_text), Toast.LENGTH_SHORT).show();
                    return;
                }

                runJsonDataParser();

            }
        });
    }



    /**
     * Run the parser process to parsee the data which is comming from server.
     * */
    public void runJsonDataParser(){

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, Constants.STORE_URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mDepartmentList.clear();
                            mDepartmentList = Department.getDepartmentsInfoFromJson(response);
                            refreshDepartmentList();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Server response ", "error");
                    }
                });

        mHttpHelper.addToRequestQueue(jsObjRequest);

    }

    /**
     * Refresh department list item.
     *
     *
     */
    public void refreshDepartmentList() {
        mDepartmentListAdapter.setDepartments(mDepartmentList);
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
