package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
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

public class Ubah_detail extends AppCompatActivity {
    EditText kode, nama,ktp, psn, keluar, jumlah, harga;
    Button btnKirim;

    private RequestQueue mRequestQueue;

    private ProgressDialog pDialog;

    Intent intent;
    String detailorupdate, _id, kodemk, strNama, strktp, strpsn, strkeluar, strjumlah, strharga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_detail);
        mRequestQueue = Volley.newRequestQueue(this);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        kode = (EditText) findViewById(R.id.kode);
        nama = (EditText) findViewById(R.id.nama);
        ktp = (EditText) findViewById(R.id.ktp);
        psn = (EditText) findViewById(R.id.psn);
        keluar = (EditText) findViewById(R.id.keluar);
        jumlah = (EditText) findViewById(R.id.jumlah);
        harga = (EditText) findViewById(R.id.harga);
        btnKirim = (Button) findViewById(R.id.btnKirim);

        intent = getIntent();
        detailorupdate = intent.getStringExtra("detailorupdate");
        _id         = intent.getStringExtra("_id");
        kodemk     = intent.getStringExtra("kode_boking");
        strNama   = intent.getStringExtra("nama");
        strktp     = intent.getStringExtra("no_ktp");
        strpsn     = intent.getStringExtra("tanggal_pemesanan");
        strkeluar  = intent.getStringExtra("tanggal_keluar");
        strjumlah  = intent.getStringExtra("jumlah_kamar");
        strharga  = intent.getStringExtra("harga");


        kode.setText(kodemk);
        nama.setText(strNama);
        ktp.setText(strktp);
        psn.setText(strpsn);
        keluar.setText(strkeluar);
        jumlah.setText(strjumlah);
        harga.setText(strharga);


        if(detailorupdate.equals("detail")){
            kode.setEnabled(false);
            nama.setEnabled(false);
            ktp.setEnabled(false);
            psn.setEnabled(false);
            keluar.setEnabled(false);
            jumlah.setEnabled(false);
            harga.setEnabled(false);
            btnKirim.setVisibility(View.GONE);
        }
        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strKode = kode.getText().toString();
                String strNama = nama.getText().toString();
                String strktp = ktp.getText().toString();
                String strpsn = psn.getText().toString();
                String strkeluar = keluar.getText().toString();
                String strjumlah = jumlah.getText().toString();
                String strharga = harga.getText().toString();
                if (strKode.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Kode Matakuliah Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strNama.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama Matakuliah Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strktp.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Jam Matakuliah Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strpsn.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Hari Matakuliah Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strkeluar.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ruang Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strjumlah.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ruang Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }else if(strharga.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ruang Tidak Boleh Kosong", Toast.LENGTH_LONG).show();
                }
                ubahhotel(strKode, strNama, strktp, strpsn, strkeluar, strjumlah, strharga);
            }
        });
    }
    private void ubahhotel(String kode, String nama, String ktp, String psn, String keluar, String jumlah, String harga){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("kode_boking", kode);
        params.put("nama", nama);
        params.put("no_ktp", ktp);
        params.put("tanggal_pemesanan", psn);
        params.put("tanggal_keluar", keluar);
        params.put("jumlah_kamar", jumlah);
        params.put("harga", harga);


        pDialog.setMessage("Mohon tunggu");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, configURL.updatemkandroid + _id, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if(status == true){
                                msg = response.getString("pesan");
                            }else {
                                msg = response.getString("pesan");
                                Intent i = new Intent(Ubah_detail.this,
                                        listhotel.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg,
                                    Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(Ubah_detail.this, listhotel.class);
        startActivity(i);
        finish();
    }
}
