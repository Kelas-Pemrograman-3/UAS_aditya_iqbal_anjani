package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.anjani.projekuasfinal.R;
import com.anjani.projekuasfinal.Ubah_detail;
import com.anjani.projekuasfinal.listhotel;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.anjani.projekuasfinal.listhotel;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import model.hotel_model;
import server.configURL;

public class adapterhotel extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<hotel_model> item;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapterhotel(Activity activity, List<hotel_model> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
        this.activity = activity;
        this.item = item;
        this.mRequestQueue = mRequestQueue;
        this.pDialog = pDialog;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.conten_hotel, null);


        TextView kode_boking = (TextView) convertView.findViewById(R.id.txtKode);
        TextView nama = (TextView) convertView.findViewById(R.id.txtNama);
        TextView no_ktp = (TextView) convertView.findViewById(R.id.txtktp);
        Button btnDetail = (Button) convertView.findViewById(R.id.btnDetail);
        Button btnUbah = (Button) convertView.findViewById(R.id.btnUbah);
        Button btnHapus = (Button) convertView.findViewById(R.id.btnHapus);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(Html.fromHtml("<font color='#000000'><b>Yakin ingin menghapus data ini ?</b></font>"))
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                hapusData(item.get(position).get_id());
                            }
                        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                }).show();
            }
        });
//
        btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, Ubah_detail.class);
                i.putExtra("_id", item.get(position).get_id());
                i.putExtra("kode_boking", item.get(position).getKode_boking());
                i.putExtra("nama", item.get(position).getNama());
                i.putExtra("no_ktp", item.get(position).getNo_ktp());
                i.putExtra("tanggal_pemesanan", item.get(position).getTanggal_pemesanan());
                i.putExtra("tanggal_keluar", item.get(position).getTanggal_keluar());
                i.putExtra("jumlah_kamar", item.get(position).getJumlah_kamar());
                i.putExtra("harga", item.get(position).getHarga());
                i.putExtra("detailorupdate", "detail");
                activity.startActivity(i);
                activity.finish();
            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, Ubah_detail.class);
                i.putExtra("_id", item.get(position).get_id());
                i.putExtra("kode_boking", item.get(position).getKode_boking());
                i.putExtra("nama", item.get(position).getNama());
                i.putExtra("no_ktp", item.get(position).getNo_ktp());
                i.putExtra("tanggal_pemesanan", item.get(position).getTanggal_pemesanan());
                i.putExtra("tanggal_keluar", item.get(position).getTanggal_keluar());
                i.putExtra("jumlah_kamar", item.get(position).getJumlah_kamar());
                i.putExtra("harga", item.get(position).getHarga());
                i.putExtra("detailorupdate", "update");
                activity.startActivity(i);
                activity.finish();
            }
        });

        kode_boking.setText(item.get(position).getKode_boking());
        nama.setText(item.get(position).getNama());
        no_ktp.setText(item.get(position).getNo_ktp());

        return convertView;
    }

    private void hapusData(String _id) {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.DELETE, configURL.delete + _id, null,
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
                                msg = response.getString("pesan");
                                Intent i = new Intent(activity, listhotel.class);
                                activity.startActivity(i);
                                activity.finish();
                            }
                            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
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

}

