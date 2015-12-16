package sharif.bordingvistatestapp.database.dao;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;

import sharif.bordingvistatestapp.database.MyContentProvider;
import sharif.bordingvistatestapp.database.table.PromotionTable;

/**
 * Created by androidcodehunter on 12/16/2015.
 * Email: sharif.iit.du@gmail.com
 */

public class Promotion {

    public static final String BASE_PATH = PromotionTable.TABLE_NAME;

    public static final Uri CONTENT_URI = Uri
            .parse(MyContentProvider.SCHEME
                    + MyContentProvider.AUTHORITY + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/" + BASE_PATH;

    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/" + BASE_PATH;



    public Promotion(){

    }

    public Promotion(ContentValues values){
        if (values != null){
            setPromotionId(values.getAsInteger(PromotionTable.PROMOTION_ID));
            setPromotionText(values.getAsString(PromotionTable.PROMOTION_TEXT));
        }
    }


    private int mPromotionId;
    private String mPromotionText;

    public int getPromotionId() {
        return mPromotionId;
    }

    public String getPromotionText() {
        return mPromotionText;
    }

    public void setPromotionText(String promotionText) {
        this.mPromotionText = promotionText;
    }

    public void setPromotionId(int promotionId) {
        this.mPromotionId = promotionId;
    }


    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(PromotionTable.PROMOTION_TEXT, getPromotionText());
        return values;
    }


}
