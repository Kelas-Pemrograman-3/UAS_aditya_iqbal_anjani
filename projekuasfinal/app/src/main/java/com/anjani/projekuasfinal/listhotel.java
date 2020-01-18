package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

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

public class listhotel extends AppCompatActivity {
    ProgressDialog pDialog;

    adapterhotel adapter;
    ListView list;

    ArrayList<hotel_model> newsList = new ArrayList<hotel_model>();

    private RequestQueue mRequestQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listhotel);

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();
        adapter = new adapterhotel(listhotel.this, newsList, mRequestQueue, pDialog);
        list.setAdapter(adapter);
        getAllData();
    }
    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, configURL.getallhotel, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                   hotel_model hotel = new hotel_model();
                                   hotel.set_id(jsonObject.getString("_id"));
                                    hotel.setKode_boking(jsonObject.getString("kode_boking"));
                                   hotel.setNama(jsonObject.getString("nama"));
                                    hotel.setNo_ktp(jsonObject.getString("no_ktp"));
                                    hotel.setTanggal_pemesanan(jsonObject.getString("tanggal_pemesanan"));
                                   hotel.setTanggal_keluar(jsonObject.getString("tanggal_keluar"));
                                    hotel.setJumlah_kamar(jsonObject.getString("jumlah_kamar"));
                                    hotel.setHarga(jsonObject.getString("harga"));
                                    newsList.add(hotel);
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
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
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
    public void onBackPressed(){
        Intent i = new Intent(listhotel.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
