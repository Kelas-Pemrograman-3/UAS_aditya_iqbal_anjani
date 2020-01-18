package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class daftarhotelpalembang extends AppCompatActivity {
    private Button btnnovo, btnbatiqa, btnzuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarhotelpadang);



        btnnovo = findViewById(R.id.btnnovo);

        btnnovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelpalembang.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btnbatiqa = findViewById(R.id.btnbatiqa);

        btnbatiqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelpalembang.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btnzuri = findViewById(R.id.btnzuri);

        btnzuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelpalembang.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){

        Intent i = new Intent(daftarhotelpalembang.this, MainActivity.class);

        startActivity(i);

        finish();

        super.onBackPressed();

    }
}
