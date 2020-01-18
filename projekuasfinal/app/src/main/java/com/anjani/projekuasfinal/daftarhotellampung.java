package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class daftarhotellampung extends AppCompatActivity {
    private Button btnemersia, btnnovotel, btnbatiqa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftarhotellampung);



        btnemersia = findViewById(R.id.btnemersia);

        btnemersia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotellampung.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btnnovotel = findViewById(R.id.btnnovotel);

        btnnovotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotellampung.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

        btnbatiqa = findViewById(R.id.btnbatiqa);

        btnbatiqa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(daftarhotellampung.this,inputdata.class);
                startActivity(i);
                finish();
            }
        });

   }
    @Override
    public void onBackPressed(){

        Intent i = new Intent(daftarhotellampung.this, MainActivity.class);

        startActivity(i);

        finish();

        super.onBackPressed();

    }
}
