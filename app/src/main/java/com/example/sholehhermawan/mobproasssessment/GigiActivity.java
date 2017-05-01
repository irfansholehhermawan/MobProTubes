package com.example.sholehhermawan.mobproasssessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Sholeh Hermawan on 3/16/2017.
 */

public class GigiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ArrayList<Dokter> word = new ArrayList<>();

        word.add(new Dokter("Dr. Danang","Senin (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Edi","Selasa (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Endah","Selasa (09.00 - 15.00)",R.drawable.doctor_wanita));
        word.add(new Dokter("Dr. Dadang","Rabu (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Hadiyan","Kamis (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Jannah","Jum'at (09.00 - 15.00)",R.drawable.doctor_wanita));
        word.add(new Dokter("Dr. Narno","Jum'at (09.00 - 15.00)",R.drawable.doctor_pria));

        ListView listView = (ListView)findViewById(R.id.list_view);

        DokterAdapter adapter = new DokterAdapter(this,word,R.color.category_gigi);
        listView.setAdapter(adapter);
    }
}
