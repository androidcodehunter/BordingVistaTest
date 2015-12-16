package sharif.bordingvistatestapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import sharif.bordingvistatestapp.R;
/**
 * Created by androidcodehunter on 12/14/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDatabaseButton, mNetworkButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initViews();

        setupToolbar();

        initListeners();
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
     * The listeners for this activity.
     */
    private void initListeners() {
        mDatabaseButton.setOnClickListener(this);
        mNetworkButton.setOnClickListener(this);
    }

    /**
     * Initialize all views related to this activity.
     */
    private void initViews() {
        mDatabaseButton = (Button) findViewById(R.id.database_button);
        mNetworkButton = (Button) findViewById(R.id.button_network);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        final int id = view.getId();

        if (id == R.id.database_button) {
            launchClass(PromotionActivity.class);
        }

        if (id == R.id.button_network) {
            launchClass(NetworkActivity.class);
        }

    }


    /**
     * A generic method to Launch a class by name.
     *
     * @param clasz
     */
    private void launchClass(Class<?> clasz) {
        final Intent intent = new Intent(this, clasz);
        startActivity(intent);
    }
}
