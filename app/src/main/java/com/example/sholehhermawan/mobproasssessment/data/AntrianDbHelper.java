package com.example.sholehhermawan.mobproasssessment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sholehhermawan.mobproasssessment.data.AntrianContract.Entry;

public class AntrianDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = AntrianDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "listAntrian.db";

    private static final int DATABASE_VERSION = 1;

    public AntrianDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ANTRIAN_TABLE =  "CREATE TABLE " + Entry.TABLE_NAME + " ("
                + Entry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Entry.COLUMN_NAMA_USER + " TEXT NOT NULL, "
                + Entry.COLUMN_KELUHAN + " TEXT, "
                + Entry.COLUMN_KATEGORI + " INTEGER NOT NULL, "
                + Entry.COLUMN_NAMA_DOKTER + " INTEGER NOT NULL, "
                + Entry.COLUMN_RUANGAN + " INTEGER NOT NULL DEFAULT 1);";

        db.execSQL(SQL_CREATE_ANTRIAN_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}