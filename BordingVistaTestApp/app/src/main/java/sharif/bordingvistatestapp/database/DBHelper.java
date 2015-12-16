package sharif.bordingvistatestapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import sharif.bordingvistatestapp.database.table.ProductTable;
import sharif.bordingvistatestapp.database.table.PromotionTable;

/**
 * Created by androidcodehunter on 12/14/2015.
 * Email: sharif.iit.du@gmail.com
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "bordingvista.db";

    private static final int DB_VERSION = 1;

    private Context mContext;

    public DBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ProductTable.onCreate(db);
        PromotionTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("TAG DB", "onUpgrade is called()");
        ProductTable.onUpgrade(db, oldVersion, newVersion);
        PromotionTable.onUpgrade(db, oldVersion, newVersion);
    }

}

