package sharif.bordingvistatestapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import sharif.bordingvistatestapp.R;
import sharif.bordingvistatestapp.database.DBUtils;
import sharif.bordingvistatestapp.database.dao.Product;

/**
 * Created by androidcodehunter on 12/16/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<Product> mProducts;

    private ProductListItemClickListener mProductListItemClickListener;
    private Context mContext;


    public ProductListAdapter(Context context) {
        mContext = context;
    }


    public void setProducts(List<Product> products) {
        this.mProducts = products;
        notifyDataSetChanged();
    }

    /**
     * Set reminder item click listener.
     *
     * @param productListItemClickListener
     */

    public void setProductListItemClickListener(ProductListItemClickListener productListItemClickListener) {
        this.mProductListItemClickListener = productListItemClickListener;
    }


    public List<Product> getProducts() {
        return mProducts;
    }

    /**
     * ViewHolder pattern
     */
    private class ReminderViewHolder extends RecyclerView.ViewHolder {
        public TextView productName, promotionText;

        public ReminderViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.product_name);
            promotionText = (TextView) view.findViewById(R.id.promotion_text);
            // set listener for row and cross icon
            view.setOnClickListener(ProductListAdapter.this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        return new ReminderViewHolder(view);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final Product product = mProducts.get(position);
        ReminderViewHolder holder = (ReminderViewHolder) viewHolder;

        final String promotionText = DBUtils.getPromotion(mContext, product.getPromotionId()).getPromotionText();

        holder.productName.setText(product.getProductName());
        holder.promotionText.setText(promotionText);
        holder.itemView.setTag(product.getProductId());
    }

    /**
     * {@inheritDoc}
     *
     * @return reminder item count
     */
    @Override
    public int getItemCount() {
        return mProducts != null ? mProducts.size() : 0;
    }

    /**
     * Onclick listener for list item and cross icon
     * This will register interface and fire RecyclerView onCrossClicked and onItemClicked listener.
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.list_container) {
            if (mProductListItemClickListener != null) {
                mProductListItemClickListener.onItemClickListener((Integer) v.getTag());
            }
        }

    }

    /**
     * The interface class responsible to response on click listener for this adapter.
     *
     */
    public static interface ProductListItemClickListener {
        /**
         * This method invoke whenever user click on each item of this adapter.
         * */
        void onItemClickListener(int productId);
    }


}
