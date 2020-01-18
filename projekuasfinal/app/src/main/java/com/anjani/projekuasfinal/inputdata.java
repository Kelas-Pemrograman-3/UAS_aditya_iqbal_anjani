package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class inputdata extends AppCompatActivity {
    private RequestQueue mRequestQueue;

    private EditText edtkd_boking, edtnama, edtnoktp, edttglpsn, edttglkeluar, edtjmlh_kmr, edtharga;
    private Button btntambah;

    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inputdata);


        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        edtkd_boking = findViewById(R.id.edtboking);
        edtnama = findViewById(R.id.edtnama);
        edtnoktp = findViewById(R.id.edtktp);
        edttglpsn = findViewById(R.id.edttpsn);
        edttglkeluar = findViewById(R.id.edtklr);
        edtjmlh_kmr = findViewById(R.id.edtjumlah);
        edtharga = findViewById(R.id.edtharga);

        btntambah = findViewById(R.id.btnTambah);

        btntambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strkode = edtkd_boking.getText().toString();
                String strnama = edtnama.getText().toString();
                String strnoktp = edtnoktp.getText().toString();
                String strtglpsn = edttglpsn.getText().toString();
                String strtglklr = edttglkeluar.getText().toString();
                String strjumlah = edtjmlh_kmr.getText().toString();
                String strharga = edtharga.getText().toString();

                if (strkode.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Kode Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strnama.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Nama Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strnoktp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "KTP Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strtglpsn.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tanggal Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strtglklr.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "TanggalTidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strjumlah.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Jumlah Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else if (strharga.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "harga Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                } else {
                    InputData(strkode, strnama, strnoktp, strtglpsn, strtglklr, strjumlah, strharga);

//                    Intent a = new Intent(RegistrasiActivity.this, LoginActivity.class);
//                    startActivity(a);
//                    finish();
                }
            }
            });
        }

    @Override
    public void onBackPressed(){

        Intent i = new Intent(inputdata.this, daftarhotellampung.class);

        startActivity(i);

        finish();

        super.onBackPressed();

    }


    private void InputData(String kode, String nama, String ktp, String tglpsn, String tglklr, String jumlah, String harga){
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("kode_boking", kode);
        params.put("nama", nama);
        params.put("no_ktp", ktp);
        params.put("tanggal_pemesanan", tglpsn);
        params.put("tanggal_keluar", tglklr);
        params.put("jumlah_kamar", jumlah);
        params.put("harga", harga);

        pDialog.setMessage("Mohon Tunggu");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(configURL.boking, new JSONObject(params),
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
                                Intent a = new Intent(inputdata.this, inputdata.class);
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