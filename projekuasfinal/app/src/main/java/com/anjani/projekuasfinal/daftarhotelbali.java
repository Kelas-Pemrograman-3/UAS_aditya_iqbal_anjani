package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class daftarhotelbali extends AppCompatActivity {
    private Button btnhorison, btnheri, btnkuta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarhotelbali);



        btnhorison = findViewById(R.id.btnhorison);

        btnhorison.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelbali.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btnheri = findViewById(R.id.btnheri);

        btnheri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelbali.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btnkuta = findViewById(R.id.btnkuta);

        btnkuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelbali.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){

        Intent i = new Intent(daftarhotelbali.this, MainActivity.class);

        startActivity(i);

        finish();

        super.onBackPressed();

    }
}
