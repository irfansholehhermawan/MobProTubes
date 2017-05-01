package com.example.sholehhermawan.mobproasssessment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.sholehhermawan.mobproasssessment.EditorActivity.noAntrian;
import static com.example.sholehhermawan.mobproasssessment.EditorActivity.noKonfirmasi;

/**
 * Created by Sholeh Hermawan on 3/17/2017.
 */

public class RegistrasiFragment extends Fragment {
    private String textAntrian, textSekarang;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_registrasi, container, false);

        textAntrian = Integer.toString(noAntrian);
        TextView noAntrian = (TextView) rootView.findViewById(R.id.text_view_antrian);
        noAntrian.setText(textAntrian);

        textSekarang = Integer.toString(noKonfirmasi);
        TextView noSekarang = (TextView) rootView.findViewById(R.id.text_view_sekarang);
        noSekarang.setText(textSekarang);

        return rootView;
    }
}
