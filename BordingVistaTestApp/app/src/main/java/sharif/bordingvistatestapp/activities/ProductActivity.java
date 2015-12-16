package sharif.bordingvistatestapp.activities;

import android.content.ContentUris;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import sharif.bordingvistatestapp.R;
import sharif.bordingvistatestapp.database.DBUtils;
import sharif.bordingvistatestapp.database.dao.Product;
import sharif.bordingvistatestapp.database.table.PromotionTable;

/**
 * Created by androidcodehunter on 12/16/2015.
 * Email: sharif.iit.du@gmail.com
 */
public class ProductActivity extends AppCompatActivity {

    private EditText mPromotionEditText, mProductEditText;

    private int mPromotionId;
    private Button mProductSaveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        initViews();

        setupToolbar();

        getBundleMessage();

        setUpPromotionText();

        setupSaveButton();
    }

    private void setupSaveButton() {
        mProductSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Product product = new Product();
                Uri uri = DBUtils.insertProduct(getBaseContext(), product);
                int id = (int) ContentUris.parseId(uri);
                product.setProductId(id);


            }
        });
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


    private void setUpPromotionText() {
        String promotionText = DBUtils.getPromotion(this, mPromotionId).getPromotionText();
        mPromotionEditText.setText(promotionText);
    }

    private void initViews() {
        mProductEditText = (EditText) findViewById(R.id.product_edit_text);
        mPromotionEditText = (EditText) findViewById(R.id.promotion_edit_text);
        mProductSaveButton = (Button) findViewById(R.id.product_save_button);
    }

    private void getBundleMessage() {
        Bundle bundle = getIntent().getExtras();
        mPromotionId = bundle.getInt(PromotionTable.PROMOTION_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_product, menu);
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
}
