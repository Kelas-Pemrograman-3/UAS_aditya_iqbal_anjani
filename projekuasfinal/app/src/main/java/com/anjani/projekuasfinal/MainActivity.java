package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.circularreveal.CircularRevealFrameLayout;
import com.google.android.material.circularreveal.cardview.CircularRevealCardView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.configURL;
import session.sessionManager;

public class MainActivity extends AppCompatActivity {

    private sessionManager session;
    private CircularRevealCardView  lihatdata;
   private FrameLayout framebali,framelampung,framepadang,framepalembang;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new sessionManager(this);
        lihatdata = findViewById(R.id.card2);


        framebali = (FrameLayout) findViewById(R.id.framebali);
        framelampung = (FrameLayout) findViewById(R.id.framebdl);
        framepadang = (FrameLayout) findViewById(R.id.framepadang);
        framepalembang = (FrameLayout) findViewById(R.id.framepalembang);

        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, listhotel.class);
                startActivity(a);
                finish();
            }
        });

        framebali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSkip(false);
                session.setSessid(0);
                Intent i = new Intent(MainActivity.this, daftarhotelbali.class);
                startActivity(i);
                finish();
            }
        });
        framelampung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSkip(false);
                session.setSessid(0);
                Intent i = new Intent(MainActivity.this, daftarhotellampung.class);
                startActivity(i);
                finish();
            }
        });
        framepalembang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSkip(false);
                session.setSessid(0);
                Intent i = new Intent(MainActivity.this, daftarhotelpalembang.class);
                startActivity(i);
                finish();
            }
        });
        framepadang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSkip(false);
                session.setSessid(0);
                Intent i = new Intent(MainActivity.this, daftarhotelpadang.class);
                startActivity(i);
                finish();

    }
        });
}}