package sharif.bordingvistatestapp.database.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import sharif.bordingvistatestapp.database.MyContentProvider;
import sharif.bordingvistatestapp.database.table.ProductTable;
import sharif.bordingvistatestapp.database.table.PromotionTable;

/**
 * Created by androidcodehunter on 12/16/2015.
 * Email: sharif.iit.du@gmail.com
 */
public class Product {

    public static final String BASE_PATH = ProductTable.TABLE_NAME;

    public static final Uri CONTENT_URI = Uri
            .parse(MyContentProvider.SCHEME
                    + MyContentProvider.AUTHORITY + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/" + BASE_PATH;

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/" + BASE_PATH;


    private int mProductId;
    private String mProductName;
    private int mPromotionId;


    public Product(){

    }

    public Product(ContentValues values){
        if (values != null){
            setProductId(values.getAsInteger(ProductTable.PRODUCT_ID));
            setProductName(values.getAsString(ProductTable.PRODUCT_TEXT));
            setPromotionId(values.getAsInteger(ProductTable.PROMOTION_ID));
        }
    }


    public int getProductId() {
        return mProductId;
    }

    public String getProductName() {
        return mProductName;
    }

    public int getPromotionId() {
        return mPromotionId;
    }

    public void setProductId(int productId) {
        this.mProductId = productId;
    }

    public void setProductName(String productName) {
        this.mProductName = productName;
    }

    public void setPromotionId(int promotionId) {
        this.mPromotionId = promotionId;
    }


    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(ProductTable.PRODUCT_TEXT, getProductName());
        values.put(ProductTable.PROMOTION_ID, getPromotionId());
        return values;
    }
}
