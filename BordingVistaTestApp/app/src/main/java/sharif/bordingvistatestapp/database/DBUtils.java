package sharif.bordingvistatestapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import sharif.bordingvistatestapp.database.dao.Product;
import sharif.bordingvistatestapp.database.dao.Promotion;
import sharif.bordingvistatestapp.database.table.ProductTable;
import sharif.bordingvistatestapp.database.table.PromotionTable;

/**
 * Created by androidcodehunter on 12/15/2015.
 * Email: sharif.iit.du@gmail.com
 */
public class DBUtils {

    private static final String TAG = "DBUtils";

    public static Uri insertPromotion(Context context, Promotion promotion) {
        return context.getContentResolver().insert(Promotion.CONTENT_URI, promotion.getContentValues());
    }


    public static Promotion getPromotion(Context context, int id) {
        Cursor cursor = null;
        Promotion promotion = null;

        try {
            cursor = context.getContentResolver().query(Promotion.CONTENT_URI, null,
                    PromotionTable.PROMOTION_ID + " = ?", new String[]{
                            String.valueOf(id)
                    }, null);

            while (cursor.moveToNext()) {
                promotion = cursorToWord(cursor);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        if (cursor != null)cursor.close();

        return promotion;
    }


    private static Promotion cursorToWord(Cursor cursor) {
        if (cursor.isBeforeFirst()) {
            cursor.moveToFirst();
        }

        ContentValues values = new ContentValues();

        DatabaseUtils.cursorIntToContentValues(cursor, PromotionTable.PROMOTION_ID, values,
                PromotionTable.PROMOTION_ID);
        DatabaseUtils.cursorStringToContentValues(cursor, PromotionTable.PROMOTION_TEXT, values,
                PromotionTable.PROMOTION_TEXT);

        return new Promotion(values);
    }


    public static Uri insertProduct(Context context, Product product) {
        return context.getContentResolver().insert(Promotion.CONTENT_URI, product.getContentValues());
    }



    public static Product getProduct(Context context, int id) {
        Cursor cursor = null;
        Product product = null;

        try {
            cursor = context.getContentResolver().query(Product.CONTENT_URI, null,
                    ProductTable.PRODUCT_ID + " = ?", new String[]{
                            String.valueOf(id)
                    }, null);

            while (cursor.moveToNext()) {
                product = cursorToProduct(cursor);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }

        if (cursor != null)cursor.close();

        return product;
    }


    public static List<Product> getProducts(Context context) {

        List<Product> products = new ArrayList<Product>();

        Cursor cursor = null;

        try {
            cursor = context.getContentResolver().query(Product.CONTENT_URI, null, null, null, null);
            while (cursor.moveToNext()) {
                products.add(cursorToProduct(cursor));
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        } finally {
            cursor.close();
        }

        return products;
    }


    private static Product cursorToProduct(Cursor cursor) {
        if (cursor.isBeforeFirst()) {
            cursor.moveToFirst();
        }

        ContentValues values = new ContentValues();

        DatabaseUtils.cursorIntToContentValues(cursor, ProductTable.PRODUCT_ID, values,
                ProductTable.PRODUCT_ID);
        DatabaseUtils.cursorStringToContentValues(cursor, ProductTable.PRODUCT_TEXT, values,
                ProductTable.PRODUCT_TEXT);
        DatabaseUtils.cursorIntToContentValues(cursor, ProductTable.PROMOTION_ID, values,
                ProductTable.PROMOTION_ID);

        return new Product(values);
    }


}
