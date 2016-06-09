package com.example.manuelfleivar.skynventory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manuelfleivar on 29/5/16.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String name="Skynventory2";
    public static final int version=1;
    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL( DBManager.crearCategorias_t);
        db.execSQL( DBManager.crearArticulos_t);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
