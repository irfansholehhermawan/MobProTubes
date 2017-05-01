package com.example.sholehhermawan.mobproasssessment;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sholeh Hermawan on 3/13/2017.
 */

public class DokterAdapter extends ArrayAdapter<Dokter> {

    private int mBackgroundColours;

    public DokterAdapter(Context context, List<Dokter> objects, int backgroundColours) {
        super(context, 0, objects);
        mBackgroundColours = backgroundColours;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).
                    inflate(R.layout.list_item,parent,false);

        Dokter current = getItem(position);

        ImageView gambarItem = (ImageView) convertView.findViewById(R.id.gambar);
        if(current.isImageAvailable()){
            gambarItem.setImageResource(current.getmImageWord());
            gambarItem.setVisibility(View.VISIBLE);
        }else {
            gambarItem.setVisibility(View.GONE);
        }

        LinearLayout words = (LinearLayout) convertView.findViewById(R.id.word);
        words.setBackgroundColor(ContextCompat.getColor(getContext(),mBackgroundColours));

        TextView itemWord = (TextView)convertView.findViewById(R.id.item_word);
        itemWord.setText(current.getmItemWord());
        TextView subItemWord = (TextView)convertView.findViewById(R.id.sub_item_word);
        subItemWord.setText(current.getmSubItemWord());

        return convertView;
    }
}
