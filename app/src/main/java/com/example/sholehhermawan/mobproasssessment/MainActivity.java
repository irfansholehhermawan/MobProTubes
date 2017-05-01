package com.example.sholehhermawan.mobproasssessment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //deklarasi variabel
    EditText nama, email, konfirmasi, password, alamat, noid, ttl, jk, nohp;
    Button signup, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //deklarasi memanggil id nya
        nama = (EditText) findViewById(R.id.registNama);
        email = (EditText) findViewById(R.id.registEmail);
        konfirmasi = (EditText) findViewById(R.id.registConfirm);
        password = (EditText) findViewById(R.id.registPassword);
        alamat = (EditText) findViewById(R.id.registAlamat);
        noid = (EditText) findViewById(R.id.registNoId);
        ttl = (EditText) findViewById(R.id.registTtl);
        jk = (EditText) findViewById(R.id.registJk);
        nohp = (EditText) findViewById(R.id.registNoHp);

        signup = (Button) findViewById(R.id.registSignUp);
        login = (Button) findViewById(R.id.login);

//aksi ketika tombol sign up di klik
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Check();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
    }

    // method untuk validasi
    private void Check() {
        nama.setError(null);
        email.setError(null);
        konfirmasi.setError(null);
        password.setError(null);
        alamat.setError(null);
        noid.setError(null);
        ttl.setError(null);
        jk.setError(null);
        nohp.setError(null);

        boolean cancel = false;
        View focusView = null;

        //cek setiap editText
        if (HeroHelper.isEmpty(nama)) {
            nama.setError("Nama tidak boleh kosong");
            cancel = true;
            focusView = nama;
        } else if (HeroHelper.isEmpty(email)) {
            email.setError("Email tidak boleh kosong");
            cancel = true;
            focusView = email;
        } else if (!HeroHelper.isEmailValid(email)) {
            email.setError("Email tidak valid");
            cancel = true;
            focusView = email;
        } else if (HeroHelper.isEmpty(password)) {
            password.setError("Password tidak boleh kosong");
            cancel = true;
            focusView = password;
        } else if (HeroHelper.isEmpty(konfirmasi)) {
            konfirmasi.setError("konfirmasi tidak boleh kosong");
            cancel = true;
            focusView = konfirmasi;
        } else if (HeroHelper.isCompare(password, konfirmasi)) {
            konfirmasi.setError("konfirmasi tidak sama");
            cancel = true;
            focusView = konfirmasi;
        } else if (HeroHelper.isEmpty(alamat)) {
            alamat.setError("Alamat tidak boleh kosong");
            cancel = true;
            focusView = alamat;
        } else if (HeroHelper.isEmpty(noid)) {
            noid.setError("No ID tidak boleh kosong");
            cancel = true;
            focusView = noid;
        } else if (HeroHelper.isEmpty(jk)) {
            jk.setError("Jenis Kelamin tidak boleh kosong");
            cancel = true;
            focusView = jk;
        } else if (HeroHelper.isEmpty(nohp)) {
            nohp.setError("No HP tidak boleh kosong");
            cancel = true;
            focusView = nohp;
        } else {
            Regristrasi();
        }
    }

    //method untuk parsing ke php
    private void Regristrasi() {
        String url = HeroHelper.BASE_URL + "register.php";
        Map<String, String> params = new HashMap<>();
        params.put(HeroHelper.NAMA_USER, nama.getText().toString());
        params.put(HeroHelper.EMAIL_USER, email.getText().toString());
        params.put(HeroHelper.PASSWORD_USER, password.getText().toString());
        params.put(HeroHelper.ALAMAT_USER, alamat.getText().toString());
        params.put(HeroHelper.NOID_USER, noid.getText().toString());
        params.put(HeroHelper.JK_USER, jk.getText().toString());
        params.put(HeroHelper.TTL_USER, ttl.getText().toString());
        params.put(HeroHelper.NOHP_USER, nohp.getText().toString());

        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading . . .");
        progressDialog.setCancelable(true);

        AQuery aq;
        aq = new AQuery(MainActivity.this);

        aq.progress(progressDialog).ajax(url, params, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String object, AjaxStatus status) {
                super.callback(url, object, status);
                if (object != null) {
                    HeroHelper.pre("Respon Register : "+ object);
                    try {
                        JSONObject jsonObject = new JSONObject(object);
                        String result = jsonObject.getString("success");
                        String pesan = jsonObject.getString("message");
                        if (result.equalsIgnoreCase("true")){
                            HeroHelper.pesan(getApplicationContext(), pesan);
                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                            finish();
                        }else {
                            HeroHelper.pesan(getApplicationContext(), pesan);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                } else {
                    HeroHelper.pesan(getApplicationContext(), "Url salah");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
