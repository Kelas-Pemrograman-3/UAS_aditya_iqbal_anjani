package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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


import java.util.ArrayList;

import adapter.adapterhotel;
import model.hotel_model;
import server.configURL;
public class pesan extends AppCompatActivity {
    private RequestQueue mRequestQueue;
    private TextView vdatamk;

    ProgressDialog pDialog;

    adapterhotel adapter;
    ListView list;

    ArrayList<hotel_model> newsList = new ArrayList<hotel_model>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);
        getSupportActionBar().hide();

        mRequestQueue = Volley.newRequestQueue(this);
        vdatamk = findViewById(R.id.txtlihat);


        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new adapterhotel(pesan.this, newsList, mRequestQueue, pDialog);
        list.setAdapter(adapter);

        getAllData();

    }

    @Override
    public void onBackPressed(){

        Intent i = new Intent(pesan.this, MainActivity.class);

        startActivity(i);

        finish();

        super.onBackPressed();

    }
    private void getAllData() {
        // Pass second argument as "null" for GET requests

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, configURL.getallhotel, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    hotel_model matkul = new hotel_model();
                                    matkul.set_id(jsonObject.getString("_id"));
                                    matkul.setKode_boking(jsonObject.getString("kode_boking"));
                                    matkul.setNama(jsonObject.getString("nama"));
                                    matkul.setTanggal_pemesanan(jsonObject.getString("tanggal_pemesanan"));
                                    matkul.setTanggal_keluar(jsonObject.getString("tanggal_keluar"));
                                    matkul.setJumlah_kamar(jsonObject.getString("jumlah_kamar"));
                                    matkul.setHarga(jsonObject.getString("harga"));
                                    matkul.setNo_ktp(jsonObject.getString("no_ktp"));
                                    newsList.add(matkul);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());

            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }
}
