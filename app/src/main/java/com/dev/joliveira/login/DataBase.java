package com.dev.joliveira.login;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ADM on 04/02/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    //builder database
    public DataBase(Context context)
    {
        super(context, "EXEMPLE", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS USERS( " + SqlScript.getCreateTable());//create the table
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS USERS" + SqlScript.getCreateTable()); // if table had an upgrade
        onCreate(db);

    }
}
