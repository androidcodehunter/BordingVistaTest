package sharif.bordingvistatestapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.net.Uri;
import android.util.Log;

import sharif.bordingvistatestapp.database.dao.Promotion;
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

}
