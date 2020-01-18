package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class daftarhotelpadang extends AppCompatActivity {
    private Button btnpangeran, btngrand, btninna;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarhotelpadang);



        btnpangeran = findViewById(R.id.btnpangeran);

        btnpangeran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelpadang.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btngrand = findViewById(R.id.btngrand);

        btngrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelpadang.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btninna = findViewById(R.id.btnina);

        btninna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotelpadang.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed(){

        Intent i = new Intent(daftarhotelpadang.this, MainActivity.class);

        startActivity(i);

        finish();

        super.onBackPressed();

    }
}