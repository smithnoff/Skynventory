package com.example.manuelfleivar.skynventory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Configuracion extends Activity {
    EditText elemento;
    Spinner listaElemento;
    Button agregar, eliminar;
    DBManager mn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);
        agregar=(Button)findViewById(R.id.btAddCat);
        eliminar=(Button)findViewById(R.id.btc_eliminar);
        elemento=(EditText)findViewById(R.id.etElemento);
        listaElemento=(Spinner)findViewById(R.id.spAddCategory);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(Configuracion.this,R.array.elemento,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        listaElemento.setAdapter(adapter);
        mn=new DBManager(Configuracion.this);
        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                if(elemento.getText().toString().isEmpty()|| listaElemento.getSelectedItemPosition()==0){
                    Snackbar.make(v,"Error: verifique que el campo de elemento no esta vacio y que selecciono un item",Snackbar.LENGTH_SHORT).show();

                }else{

                    AlertDialog.Builder alerta=new AlertDialog.Builder(Configuracion.this);
                    alerta.setTitle("Esta seguro que desea agregar este elemento?")
                            .setMessage("Esta a punto de guardar "+elemento.getText().toString()+" como "+listaElemento.getSelectedItem().toString())
                            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    mn.insertarCategoria(elemento.getText().toString(),listaElemento.getSelectedItem().toString());
                                    Snackbar.make(v,"agregado correctamente",Snackbar.LENGTH_SHORT).show();
                                    elemento.setText("");
                                    listaElemento.setSelection(0);

                                }
                            }).setNegativeButton("cancelar",null).create().show();
                }



            }

        });
        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta=new AlertDialog.Builder(Configuracion.this);
                alerta.setTitle("¿Esta seguro que desea eliminar la Base de datos?")
                        .setMessage("Esta a punto de eliminar toda la informacion en la Base de datos.")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                mn.borrarDB(2);

                            }
                        }).setNegativeButton("Cancelar",null).create().show();



            }
        });
    }






}
