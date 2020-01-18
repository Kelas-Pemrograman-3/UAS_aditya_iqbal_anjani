package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.configURL;


public class Regisrasi extends AppCompatActivity {

    private RequestQueue mRequestQueue;

    private EditText edtuser,edtNama,edtalamat, edtemail,edtPassword;
    private Button btnregis;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regisrasi);

        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        edtuser = findViewById(R.id.edtusername);
        edtNama = findViewById(R.id.edtNama);
        edtalamat = findViewById(R.id.edtalamat);
        edtemail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

        btnregis = findViewById(R.id.btnregis);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String struser = edtuser.getText().toString();
                String strNama = edtNama.getText().toString();
                String stralamat = edtalamat.getText().toString();
                String stremail = edtemail.getText().toString();
                String strPassword = edtPassword.getText().toString();

                if (struser
                        .isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Npm Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strNama.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strPassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Prodi Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (stralamat.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Prodi Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (stremail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {
                    InputData(struser, strNama, stralamat, stremail, strPassword);

//                    Intent a = new Intent(RegistrasiActivity.this, LoginActivity.class);
//                    startActivity(a);
//                    finish();
                }
            }
        });
    }

    private void InputData(String user, String nama, String alamat, String email, String pass){

// Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("Username", user);
        params.put("Nama", nama);
        params.put("Password", pass);
        params.put("Alamat", alamat);
        params.put("Email", email);

        pDialog.setMessage("Mohon Tunggu");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(configURL.insert, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if(status == true){
                                msg = response.getString("pesan");
                            } else {
                                msg = response.getString("pesan");
                                Intent a = new Intent(Regisrasi.this, login.class);
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
        if (!pDialog.isShowing())
            pDialog.dismiss();
    }
}
