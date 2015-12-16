package sharif.bordingvistatestapp.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

import sharif.bordingvistatestapp.database.dao.Product;
import sharif.bordingvistatestapp.database.dao.Promotion;
import sharif.bordingvistatestapp.database.table.ProductTable;
import sharif.bordingvistatestapp.database.table.PromotionTable;

/**
 * Created by androidcodehunter on 12/16/2015.
 * Email: sharif.iit.du@gmail.com
 */
public class MyContentProvider extends ContentProvider {

    public static final String SCHEME = "content://";
    public static final String AUTHORITY = "com.androidtest.mycontentprovider";

    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    private static final int PROMOTIONS = 1;

    private static final int PROMOTION_ID = 2;

    private static final int PRODUCTS = 3;

    private static final int PRODUCT_ID = 4;

    static {
        uriMatcher.addURI(AUTHORITY, Promotion.BASE_PATH, PROMOTIONS);
        uriMatcher.addURI(AUTHORITY, Promotion.BASE_PATH + "/#", PROMOTION_ID);
        uriMatcher.addURI(AUTHORITY, Product.BASE_PATH, PRODUCTS);
        uriMatcher.addURI(AUTHORITY, Product.BASE_PATH + "/#", PRODUCT_ID);
    }


    private DBHelper mDbHelper;
    private SQLiteDatabase mSqLiteDatabase;

    @Override
    public boolean onCreate() {
        mDbHelper = new DBHelper(getContext());
        mSqLiteDatabase = mDbHelper.getWritableDatabase();
        return true;
    }


    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
            case PROMOTIONS:
                return Promotion.CONTENT_TYPE;
            case PROMOTION_ID:
                return Promotion.CONTENT_ITEM_TYPE;
            case PRODUCTS:
                return Product.CONTENT_TYPE;
            case PRODUCT_ID:
                return Product.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }
    }



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case PROMOTIONS:
                count = mSqLiteDatabase.delete(PromotionTable.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case PROMOTION_ID:
                count = mSqLiteDatabase.delete(PromotionTable.TABLE_NAME, PromotionTable.PROMOTION_ID
                        + " = ?", new String[]{uri.getLastPathSegment()});
                break;

            case PRODUCTS:
                count = mSqLiteDatabase.delete(ProductTable.TABLE_NAME, selection,
                        selectionArgs);
                break;
            case PRODUCT_ID:
                count = mSqLiteDatabase.delete(ProductTable.TABLE_NAME, ProductTable.PRODUCT_ID
                        + " = ?", new String[]{uri.getLastPathSegment()});
                break;

            default:
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long rowId = 0;

        switch (uriMatcher.match(uri)) {
            case PROMOTIONS:
                rowId = mSqLiteDatabase.insert(PromotionTable.TABLE_NAME, null, values);
                break;
            case PRODUCTS:
                rowId = mSqLiteDatabase.insert(ProductTable.TABLE_NAME, null, values);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        if (rowId > 0) {
            uri = ContentUris.withAppendedId(uri, rowId);
            getContext().getContentResolver().notifyChange(uri, null);
            return uri;
        }

        throw new SQLiteException("Insert failed.");
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        switch (uriMatcher.match(uri)) {
            case PRODUCT_ID:
                queryBuilder.appendWhere(ProductTable.PRODUCT_ID + "="
                        + uri.getLastPathSegment());
            case PRODUCTS:
                queryBuilder.setTables(ProductTable.TABLE_NAME);
                break;

            case PROMOTION_ID:
                queryBuilder.appendWhere(PromotionTable.PROMOTION_ID + "="
                        + uri.getLastPathSegment());
            case PROMOTIONS:
                queryBuilder.setTables(PromotionTable.TABLE_NAME);
                break;

            default:
                throw new IllegalArgumentException("Unknown URI" + uri);
        }

        Cursor cursor = queryBuilder.query(mSqLiteDatabase, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;

        switch (uriMatcher.match(uri)) {
            case PROMOTIONS:
                count = mSqLiteDatabase.update(PromotionTable.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case PROMOTION_ID:
                count = mSqLiteDatabase.update(PromotionTable.TABLE_NAME, values,
                        PromotionTable.PROMOTION_ID + " = ?",
                        new String[]{uri.getLastPathSegment()});
                break;

            case PRODUCTS:
                count = mSqLiteDatabase.update(ProductTable.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            case PRODUCT_ID:
                count = mSqLiteDatabase.update(ProductTable.TABLE_NAME, values,
                        ProductTable.PRODUCT_ID + " = ?",
                        new String[]{uri.getLastPathSegment()});
                break;

            default:
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
