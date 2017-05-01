package com.example.sholehhermawan.mobproasssessment;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Sholeh Hermawan on 4/17/2017.
 */

public class HeroHelper {
    private static final int DEBUG = 1;
    public static final String BASE_URL = "http://kamikazebioskop04.pe.hu/";

    public static final String EMAIL_USER = "email";
    public static final String PASSWORD_USER = "pass";
    public static final String NAMA_USER = "nama_user";
    public static final String ALAMAT_USER = "alamat";
    public static final String NOID_USER = "noid";
    public static final String JK_USER = "jk";
    public static final String TTL_USER = "ttl";
    public static final String NOHP_USER = "nohp";
    public static final String NAMA_DOKTER = "nama_dokter";
    public static final String KATEGORI_DOKTER = "kategori_dokter";
    public static final String KELUHAN = "keluhan";

    //ini method validasi EditText
    public static boolean isEmpty(EditText editText) {
        if (editText.getText().toString().trim().length() > 0) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean isCompare(EditText editText1, EditText editText2) {
        String a = editText1.getText().toString();
        String b = editText2.getText().toString();
        if (a.equals(b)) {
            return false;
        } else {
            return true;
        }
    }

    public static void pesan(Context c, String msg) {
        Toast.makeText(c, msg, Toast.LENGTH_SHORT).show();
    }

    public static void pre(String pesan) {
        try {
            if (DEBUG == 1) {
                System.out.println(pesan);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // validasi untuk inputan email
    public static boolean isEmailValid(EditText email) {
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email.getText().toString();

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }
}