package sharif.bordingvistatestapp.activities;

import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sharif.bordingvistatestapp.R;
import sharif.bordingvistatestapp.database.DBUtils;
import sharif.bordingvistatestapp.database.dao.Promotion;
import sharif.bordingvistatestapp.database.table.PromotionTable;


/**
 * Created by androidcodehunter on 12/14/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class PromotionActivity extends AppCompatActivity {

    private Button mEnterButton;
    private EditText mPromotionEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        initViews();

        setupToolbar();

        setEnterButton();
    }

    private void setEnterButton() {
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Promotion promotion = new Promotion();
                promotion.setPromotionText(mPromotionEditText.getText().toString());
                Uri uri = DBUtils.insertPromotion(getBaseContext(), promotion);
                int id = (int) ContentUris.parseId(uri);
                promotion.setPromotionId(id);

                startProductActivity(id);
            }
        });
    }

    /**
     * Start product activity.
     * */
    private void startProductActivity(int id) {
        Intent intent = new Intent(this, ProductActivity.class);
        intent.putExtra(PromotionTable.PROMOTION_ID, id);
        startActivity(intent);
    }

    private void initViews() {
        mPromotionEditText = (EditText) findViewById(R.id.promotion_edit_text);
        mEnterButton = (Button) findViewById(R.id.promotion_enter_button);
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_database, menu);
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
