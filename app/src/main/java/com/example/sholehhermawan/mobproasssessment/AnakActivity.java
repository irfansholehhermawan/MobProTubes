package com.example.sholehhermawan.mobproasssessment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Sholeh Hermawan on 3/13/2017.
 */

public class AnakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ArrayList<Dokter> word = new ArrayList<>();

        word.add(new Dokter("Dr. Yandi","Senin (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Anisa","Senin (09.00 - 15.00)",R.drawable.doctor_wanita));
        word.add(new Dokter("Dr. Retno","Senin (09.00 - 15.00)",R.drawable.doctor_wanita));
        word.add(new Dokter("Dr. Rahmat","Selasa (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Anisa","Rabu (09.00 - 15.00)",R.drawable.doctor_wanita));
        word.add(new Dokter("Dr. Habib","Kamis (09.00 - 15.00)",R.drawable.doctor_pria));
        word.add(new Dokter("Dr. Anisa","Jum'at (09.00 - 15.00)",R.drawable.doctor_wanita));

        ListView listView = (ListView)findViewById(R.id.list_view);

        DokterAdapter adapter = new DokterAdapter(this,word,R.color.category_anak);
        listView.setAdapter(adapter);
    }
}
