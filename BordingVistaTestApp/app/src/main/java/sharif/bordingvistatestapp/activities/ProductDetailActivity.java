package sharif.bordingvistatestapp.activities;

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
import sharif.bordingvistatestapp.database.dao.Promotion;
import sharif.bordingvistatestapp.database.table.ProductTable;
import sharif.bordingvistatestapp.database.table.PromotionTable;

public class ProductDetailActivity extends AppCompatActivity {

    private EditText mProductName, mProductPromotionText;
    private Button mUpdateButton, mDeleteButton;

    private Product mProduct;
    private int mProductId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        initViews();

        setupToolbar();
        getBundleMessage();

        setEditableText();

        setupUpdateButton();

        setupDeleteButton();

    }

    private void setEditableText() {
        mProductName.setText(mProduct.getProductName());
        mProductPromotionText.setText(DBUtils.getPromotion(this, mProduct.getPromotionId()).getPromotionText());
    }

    private void setupDeleteButton() {
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBUtils.deleteProduct(getBaseContext(), mProductId);
                finish();
            }
        });
    }

    private void setupUpdateButton() {
        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Product product = new Product();
                product.setProductName(mProductName.getText().toString());
                product.setProductId(mProduct.getProductId());
                product.setPromotionId(mProduct.getPromotionId());

                DBUtils.updateProduct(getBaseContext(), product);

                Promotion promotion = new Promotion();
                promotion.setPromotionId(mProduct.getPromotionId());
                promotion.setPromotionText(mProductPromotionText.getText().toString());

                DBUtils.updatePromotion(getBaseContext(), promotion);

                finish();
            }
        });
    }

    private void getBundleMessage() {
        Bundle bundle = getIntent().getExtras();
        mProductId = bundle.getInt(ProductTable.PRODUCT_ID);

        mProduct = DBUtils.getProduct(this, mProductId);
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


    private void initViews() {
        mDeleteButton = (Button) findViewById(R.id.button_delete);
        mUpdateButton = (Button) findViewById(R.id.button_update);
        mProductName = (EditText) findViewById(R.id.product_edit_text);
        mProductPromotionText = (EditText) findViewById(R.id.promotion_edit_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_product_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
