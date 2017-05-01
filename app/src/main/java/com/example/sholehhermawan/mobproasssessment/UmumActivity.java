package com.example.sholehhermawan.mobproasssessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Sholeh Hermawan on 3/13/2017.
 */

public class UmumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ArrayList<Dokter> word = new ArrayList<>();

        word.add(new Dokter("Dr. Bagus","Senin (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Jaka","Senin (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Hari","Senin (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Syaiful","Senin (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Imam","Selasa (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Bagus","Selasa (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Intan","Rabu (09.00 - 15.00)",R.drawable.doctor_wanita));
        word.add(new Dokter("Dr. Hari","Rabu (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Giar","Kamis (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Imam","Kamis (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Lintang","Jum'at (09.00 - 15.00)",R.drawable.doctor_wanita));
        word.add(new Dokter("Dr. Syaiful","Jum'at (09.00 - 15.00)",R.drawable.doctor_pria));

        ListView listView = (ListView)findViewById(R.id.list_view);

        DokterAdapter adapter = new DokterAdapter(this,word,R.color.category_umum);
        listView.setAdapter(adapter);
    }
}
