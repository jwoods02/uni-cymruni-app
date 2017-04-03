package com.example.c1629177.cymruniapp;

/**
 * Created by c1652778 on 20/03/2017.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */

    public List<String> getAll() {
        List<String> list = new ArrayList<>();
        Cursor cursor = openHelper.getReadableDatabase().rawQuery("SELECT * FROM CymruNi", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            list.add(cursor.getString(1));
            list.add(cursor.getString(2));
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getNames() {
        List<String> list = new ArrayList<>();
        Cursor cursor = openHelper.getReadableDatabase().rawQuery("SELECT * FROM CymruNi", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(2));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getBeacons() {
        List<String> list = new ArrayList<>();
        Cursor cursor = openHelper.getReadableDatabase().rawQuery("SELECT * FROM CymruNi", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }



    public boolean loginTest(String username, String password) {
        List<String> list = new ArrayList<>();
        Cursor cursor = openHelper.getReadableDatabase().rawQuery("SELECT * FROM Accounts WHERE username=\"" + username + "\" AND password=\"" + password + "\"", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        if (!list.isEmpty()) {
            return true;
        }
        return false;
    }

    public String getDescription(String shopSelected) {
        String desc = null;
        String[] parameters = new String[1];
        parameters[0] = shopSelected;
        Cursor cursor = database.rawQuery("SELECT shopDes-en FROM CymruNi WHERE shopName-en = ?", parameters);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.i("DESC", cursor.getString(0));
            desc = (cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return desc;
    }

}




