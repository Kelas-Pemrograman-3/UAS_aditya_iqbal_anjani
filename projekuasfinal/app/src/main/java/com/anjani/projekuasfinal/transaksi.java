package com.anjani.projekuasfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TabHost;

public class transaksi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi);

        TabHost tabHost= (TabHost) findViewById (R.id.tabHost);
        tabHost.setup();

        //tab1
        TabHost.TabSpec spec = tabHost.newTabSpec("Tab one");
        spec.setContent(R.id.tab1);
        spec.setIndicator("Tab one");
        tabHost.addTab(spec);
        //tab2
        spec = tabHost.newTabSpec("Tab two");
        spec.setContent(R.id.tab2);
        spec.setIndicator("Tab two");
        tabHost.addTab(spec);
        //tab
        spec = tabHost.newTabSpec("Tab three");
        spec.setContent(R.id.tab3);
        spec.setIndicator("Tab three");
        tabHost.addTab(spec);

    }

}
