package sharif.bordingvistatestapp.database.table;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by androidcodehunter on 12/15/2015.
 * Email: sharif.iit.du@gmail.com
 */
public class PromotionTable {

    public static final String TABLE_NAME = "promotion";
    public static final String PROMOTION_ID = "_id";
    public static final String PROMOTION_TEXT = "message";

    private static final String CREATE_TABLE = "create table " + TABLE_NAME
            + "(" + PROMOTION_ID + " integer primary key autoincrement, "
            + PROMOTION_TEXT + " text not null );";

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion,
                                 int newVersion) {
        Log.w(PromotionTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



}
