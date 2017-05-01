package com.example.sholehhermawan.mobproasssessment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    //deklarasi variabel
    EditText email, password;
    Button login;
    TextView register;
    AQuery aq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//deklarasi untuk memanggil ID
        email = (EditText) findViewById(R.id.loginEmail);
        password = (EditText) findViewById(R.id.loginPassword);
        register = (TextView) findViewById(R.id.loginRegister);
        login = (Button) findViewById(R.id.loginLogin);

//ketika button login di klik
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CHECK();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

    private void CHECK() {
        email.setError(null);
        password.setError(null);
        boolean cancel = false;
        View focusView = null;
        if (HeroHelper.isEmpty(email)) {
            email.setError("Email tidak boleh kosong");
            focusView = email;
            cancel = true;
        } else if (!HeroHelper.isEmailValid(email)) {
            email.setError("Email tidak valid");
            focusView = email;
            cancel = true;
        } else if (HeroHelper.isEmpty(password)) {
            password.setError("Password tidak boleh kosong");
            focusView = password;
            cancel = true;
        } else {
            LOGIN();
        }
    }

    private void LOGIN() {
        String url = HeroHelper.BASE_URL + "login.php";

        Map<String, String> param = new HashMap<>();

        param.put(HeroHelper.EMAIL_USER, email.getText().toString());
        param.put(HeroHelper.PASSWORD_USER, password.getText().toString());

        ProgressDialog pdialog = new ProgressDialog(LoginActivity.this);
        pdialog.setCancelable(true);
        pdialog.setMessage("Loading . . .");

        aq = new AQuery(LoginActivity.this);

        aq.progress(pdialog).ajax(url, param, String.class, new AjaxCallback<String>() {
            @Override
            public void callback(String url, String object, AjaxStatus status) {
                super.callback(url, object, status);
                if (object != null){
                    HeroHelper.pre("Respon API : " + object);
                    try {
       //deklarasi JSON
                        JSONObject jsonObject = new JSONObject(object);
                        //memanggil JSON Object
                        String result = jsonObject.getString("success");
                        String pesan = jsonObject.getString("message");
                        if (result.equalsIgnoreCase("true")){
                            HeroHelper.pesan(getApplicationContext(), pesan);
                            startActivity(new Intent(getApplicationContext(), Main2Activity.class));
                            finish();
                        }else {
                            HeroHelper.pesan(getApplicationContext(), pesan);
                        }
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }else {
                    HeroHelper.pesan(getApplicationContext(), "Url salah");
                }
            }
        });
    }
}