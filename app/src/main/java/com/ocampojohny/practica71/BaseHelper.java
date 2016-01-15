package com.ocampojohny.practica71;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by johny on 14/01/2016.
 */
public class BaseHelper extends SQLiteOpenHelper{


    String tabla="CREATE TABLE personas(id INTEGER PRIMARY KEY AUTOINCREMENT, Nombre Text,Password Text,Usuario Text)";

    public BaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
