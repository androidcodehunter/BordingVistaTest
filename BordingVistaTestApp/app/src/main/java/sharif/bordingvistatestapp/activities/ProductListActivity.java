package sharif.bordingvistatestapp.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.Collections;
import java.util.List;

import sharif.bordingvistatestapp.R;
import sharif.bordingvistatestapp.adapters.ProductListAdapter;
import sharif.bordingvistatestapp.adapters.ProductListAdapter.ProductListItemClickListener;
import sharif.bordingvistatestapp.database.DBUtils;
import sharif.bordingvistatestapp.database.dao.Product;
import sharif.bordingvistatestapp.database.table.ProductTable;
import sharif.bordingvistatestapp.view.SimpleItemDecoration;

public class ProductListActivity extends AppCompatActivity implements ProductListItemClickListener{

    private List<Product> mProducts;

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ProductListAdapter mProductListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        setupToolbar();

        mProducts = Collections.emptyList();
        initializeRecyclerAdapter();

    }


    @Override
    protected void onResume() {
        super.onResume();

        refreshWordList();

    }


    public void refreshWordList() {
        mProducts = DBUtils.getProducts(this);
        mProductListAdapter.setProducts(mProducts);
    }

    /**
     * Initialize list of reminders from db
     */
    private void initializeRecyclerAdapter() {


        mRecyclerView.setHasFixedSize(true);
        RecyclerView.ItemDecoration itemDecoration =
                new SimpleItemDecoration(getResources().getDrawable(android.R.drawable.divider_horizontal_bright));
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mProductListAdapter = new ProductListAdapter(this);
        mProductListAdapter.setProductListItemClickListener(this);
        mRecyclerView.setAdapter(mProductListAdapter);
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
        getMenuInflater().inflate(R.menu.menu_product_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
         return true;
        }


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClickListener(int productId) {

        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(ProductTable.PRODUCT_ID, productId);
        startActivity(intent);
    }

}
