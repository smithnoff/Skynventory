package com.example.manuelfleivar.skynventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {
    com.github.clans.fab.FloatingActionButton agregar,buscar,estado,exportar,acerca,config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-6657569473526754~1360210227");
       AdView mAdView = (AdView) findViewById(R.id.adView);
     AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        agregar=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_item);
        buscar=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_item2);
        estado=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_item3);
        exportar=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_item4);
        acerca=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_item5);
        config=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_item6);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Agregar.class);
                startActivity(i);
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Buscar.class);
                startActivity(i);
            }
        });
        estado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Estado.class);
                startActivity(i);
            }
        });
        exportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Exportar.class);
                startActivity(i);
            }
        });
        acerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Acerca.class);
                startActivity(i);
            }
        });
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,Configuracion.class);
                startActivity(i);
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {


            Intent i=new Intent(MainActivity.this,Ayuda.class);
            startActivity(i);

            return true;
        }
        //noinspection SimplifiableIfStatement







        return super.onOptionsItemSelected(item);
    }


}
