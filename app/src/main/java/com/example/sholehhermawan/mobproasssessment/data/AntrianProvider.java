package com.example.sholehhermawan.mobproasssessment.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import com.example.sholehhermawan.mobproasssessment.data.AntrianContract.Entry;

public class AntrianProvider extends ContentProvider {

    public static final String LOG_TAG = AntrianProvider.class.getSimpleName();

    private static final int ANTRIAN = 100;

    private static final int ANTRIAN_ID = 101;

    private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sUriMatcher.addURI(AntrianContract.CONTENT_AUTHORITY, AntrianContract.PATH_ANTRIAN, ANTRIAN);

        sUriMatcher.addURI(AntrianContract.CONTENT_AUTHORITY, AntrianContract.PATH_ANTRIAN + "/#", ANTRIAN_ID);
    }

    private AntrianDbHelper mDbHelper;

    @Override
    public boolean onCreate() {
        mDbHelper = new AntrianDbHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {
        SQLiteDatabase database = mDbHelper.getReadableDatabase();

        Cursor cursor;

        int match = sUriMatcher.match(uri);
        switch (match) {
            case ANTRIAN:
                cursor = database.query(Entry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            case ANTRIAN_ID:
                selection = Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };

                cursor = database.query(Entry.TABLE_NAME, projection, selection, selectionArgs,
                        null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Cannot query unknown URI " + uri);
        }

        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ANTRIAN:
                return insertAntrian(uri, contentValues);
            default:
                throw new IllegalArgumentException("Insertion is not supported for " + uri);
        }
    }

    private Uri insertAntrian(Uri uri, ContentValues values) {
        String name = values.getAsString(Entry.COLUMN_NAMA_USER);
        if (name == null) {
            throw new IllegalArgumentException("Antrian requires a name user");
        }

        String keluhan = values.getAsString(Entry.COLUMN_KELUHAN);
        if (keluhan == null) {
            throw new IllegalArgumentException("Antrian requires a keluhan");
        }

        Integer kategori = values.getAsInteger(Entry.COLUMN_KATEGORI);
        if (kategori == null || !Entry.isValidKategori(kategori)) {
            throw new IllegalArgumentException("Antrian requires valid kategori");
        }

        Integer dokter = values.getAsInteger(Entry.COLUMN_NAMA_DOKTER);
        if (dokter == null || !Entry.isValidDokter(dokter)) {
            throw new IllegalArgumentException("Antrian requires a name dokter");
        }

        Integer ruangan = values.getAsInteger(Entry.COLUMN_RUANGAN);
        if (ruangan == null || !Entry.isValidRuangan(ruangan)) {
            throw new IllegalArgumentException("Antrian requires valid room");
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        long id = database.insert(Entry.TABLE_NAME, null, values);
        if (id == -1) {
            Log.e(LOG_TAG, "Failed to insert row for " + uri);
            return null;
        }

        getContext().getContentResolver().notifyChange(uri, null);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection,
                      String[] selectionArgs) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ANTRIAN:
                return updateAntrian(uri, contentValues, selection, selectionArgs);
            case ANTRIAN_ID:
                selection = Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                return updateAntrian(uri, contentValues, selection, selectionArgs);
            default:
                throw new IllegalArgumentException("Update is not supported for " + uri);
        }
    }

    private int updateAntrian(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (values.containsKey(Entry.COLUMN_NAMA_USER)) {
            String name = values.getAsString(Entry.COLUMN_NAMA_USER);
            if (name == null) {
                throw new IllegalArgumentException("Antrian requires a nama user");
            }
        }

        if (values.containsKey(Entry.COLUMN_KATEGORI)) {
            Integer kategori = values.getAsInteger(Entry.COLUMN_KATEGORI);
            if (kategori == null || !Entry.isValidKategori(kategori)) {
                throw new IllegalArgumentException("Antrian requires valid kategori");
            }
        }

        if (values.containsKey(Entry.COLUMN_NAMA_DOKTER)) {
            Integer dokter = values.getAsInteger(Entry.COLUMN_NAMA_DOKTER);
            if (dokter == null || !Entry.isValidKategori(dokter)) {
                throw new IllegalArgumentException("Antrian requires valid nama dokter");
            }
        }

        if (values.containsKey(Entry.COLUMN_RUANGAN)) {
            Integer ruangan = values.getAsInteger(Entry.COLUMN_RUANGAN);
            if (ruangan == null || !Entry.isValidKategori(ruangan)) {
                throw new IllegalArgumentException("Antrian requires valid ruangan");
            }
        }

        if (values.size() == 0) {
            return 0;
        }

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsUpdated = database.update(Entry.TABLE_NAME, values, selection, selectionArgs);

        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsUpdated;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int rowsDeleted;

        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ANTRIAN:
                rowsDeleted = database.delete(Entry.TABLE_NAME, selection, selectionArgs);
                break;
            case ANTRIAN_ID:
                selection = Entry._ID + "=?";
                selectionArgs = new String[] { String.valueOf(ContentUris.parseId(uri)) };
                rowsDeleted = database.delete(Entry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Deletion is not supported for " + uri);
        }

        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case ANTRIAN:
                return Entry.CONTENT_LIST_TYPE;
            case ANTRIAN_ID:
                return Entry.CONTENT_ITEM_TYPE;
            default:
                throw new IllegalStateException("Unknown URI !!! " + uri + " with match " + match);
        }
    }
}
