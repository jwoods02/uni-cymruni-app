import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.text.MessageFormat;

/**
 * Created by c1629177 on 10/03/2017.
 */
public class ItemsDBHelper  /* public class ItemsDBHelper extends SQLiteOpenHelper */{
    public static final String TABLE_NAME = "ListItems";
    public static final String COL_ID = "Item_ID";
    public static final String COL_Item = "Item_Txt";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Items.db";

    public ItemsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        String create = MessageFormat.format("CREATE TABLE IF NOT EXISTS {0}({1} INTEGER PRIMARY KEY AUTOINCREMENT, {2} VARCHAR);", TABLE_NAME, COL_ID, COL_Item);
        db.execSQL(create);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
