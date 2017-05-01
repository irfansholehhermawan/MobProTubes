package com.example.sholehhermawan.mobproasssessment;

/**
 * Created by Sholeh Hermawan on 4/19/2017.
 */
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.sholehhermawan.mobproasssessment.data.AntrianContract.Entry;

public class AntrianCursorAdapter extends CursorAdapter {

    public AntrianCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_item1, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView nameTextView = (TextView) view.findViewById(R.id.name);
        TextView summaryTextView = (TextView) view.findViewById(R.id.summary);

        int nameColumnIndex = cursor.getColumnIndex(Entry.COLUMN_NAMA_USER);
        int keluhanColumnIndex = cursor.getColumnIndex(Entry.COLUMN_KELUHAN);

        String namauser = cursor.getString(nameColumnIndex);
        String keluhan = cursor.getString(keluhanColumnIndex);

        if (TextUtils.isEmpty(keluhan)) {
            keluhan = context.getString(R.string.keluhan_unknown);
        }

        nameTextView.setText(namauser);
        summaryTextView.setText(keluhan);
    }
}
