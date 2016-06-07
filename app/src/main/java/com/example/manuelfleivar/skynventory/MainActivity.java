package com.example.manuelfleivar.skynventory;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    com.github.clans.fab.FloatingActionButton agregar,buscar,estado,exportar,acerca,config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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



}
