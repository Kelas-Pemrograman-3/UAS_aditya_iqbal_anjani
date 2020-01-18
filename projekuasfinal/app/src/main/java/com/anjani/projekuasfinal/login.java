package com.anjani.projekuasfinal;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.configURL;
import session.sessionManager;

public class login extends AppCompatActivity {
    private RequestQueue mRequestQueue;

    private EditText edtPassword, edtuser;
    private Button btnLinkRegister, btnlogin ;

    private ProgressDialog pDialog;

    private sessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        session = new sessionManager(this);
        if (session.isLoggedIn()){
            Intent i = new Intent(login.this,MainActivity.class);
            startActivity(i);
        }

        edtuser = findViewById(R.id.edtusername);
        edtPassword = findViewById(R.id.edtPassword);

        btnLinkRegister = findViewById(R.id.btnLinkRegister);

        btnLinkRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(login.this,Regisrasi.class);
                startActivity(i);
                finish();
            }
        });

        btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struser = edtuser.getText().toString();
                String strpass = edtPassword.getText().toString();

                if (struser.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"npm tidak boleh kosong", Toast.LENGTH_LONG).show();
                } else if (strpass.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "password tidak boleh kosong", Toast.LENGTH_LONG).show();
                } else {
                    login(struser, strpass);
                }
            }
        });

    }

    private void login(String user, String pass){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Username", user);
        params.put("Password", pass);

        pDialog.setMessage("Mohon Tunggu");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(configURL.masuk, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status == true) {
                                msg = response.getString("pesan");
                            } else {
                                session.setLogin(true);
                                msg = response.getString("pesan");
                                Intent a = new Intent(login.this, MainActivity.class);
                                startActivity(a);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
//                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
// ApplicationController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}