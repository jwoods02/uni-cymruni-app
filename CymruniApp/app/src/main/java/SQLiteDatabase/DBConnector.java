package SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by encima on 16/12/2016.
 */

public class DBConnector {

    ItemsDBHelper sd;
    String TABLE_NAME = "ListItems";
    String COL_ID = "Item_ID";
    String COL_Item = "Item_Txt";

    public DBConnector(Context ctx) {
        sd = new ItemsDBHelper(ctx);
    }

    public long addItem(String item, SQLiteDatabase writableDatabase) {
        SQLiteDatabase db = sd.getWritableDatabase(writableDatabase);
        ContentValues cv = new ContentValues();
        cv.put("Item_Txt", item);
        long rowId = db.insert(ItemsDBHelper.TABLE_NAME, null, cv);
        db.close();
        return rowId;
    }


    public int removeItem(String id, SQLiteDatabase writableDatabase) {
        SQLiteDatabase db = sd.getWritableDatabase(writableDatabase);
        String where = ItemsDBHelper.COL_ID + " = ?";
        String[] args = {id};
        int deleted = db.delete(ItemsDBHelper.TABLE_NAME, where, args);
        db.close();
        return deleted;
    }

    public Map<Long, String> getItems(String searchTerm, SQLiteDatabase writableDatabase) {
        SQLiteDatabase db = sd.getWritableDatabase(writableDatabase);
        String[] cols = {ItemsDBHelper.COL_ID, ItemsDBHelper.COL_Item};
        String where = null;
        String[] whereArgs = null;
        if (searchTerm != null) {
            where = ItemsDBHelper.COL_Item + " = ?";
            whereArgs = new String[]{searchTerm};
        }
        Cursor c = db.query(ItemsDBHelper.TABLE_NAME, cols, where, whereArgs, null, null, null);
        c.moveToFirst();

        Map<Long, String> items = new HashMap<>();
        if(c.getCount() != 0) {
            do {
                long id = c.getLong(0);
                String item = c.getString(1);
                items.put(id, item);
            } while (c.moveToNext());
        }

        return items;

    }
}
