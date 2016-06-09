package com.example.manuelfleivar.skynventory;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Buscar extends AppCompatActivity {

    private EditText filterText;
    TextView emptyText,ultimoAdd;
public  String codigo="";
    private ArrayAdapter <String> listAdapter;
    DBManager mn;
    private String codei;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);
        mn=new DBManager(Buscar.this);
        filterText = (EditText)findViewById(R.id.editText);
        emptyText= (TextView)findViewById(R.id.noart);
        ultimoAdd= (TextView)findViewById(R.id.textView10);

        final ListView itemList = (ListView)findViewById(R.id.lvab_lista);

      final Cursor articulosLista;
        articulosLista=mn.ObtenerArticulos();
        List<String> listado= new ArrayList<>();
        if(articulosLista.moveToFirst()){
            do {
                listado.add(articulosLista.getString(2)+" "+articulosLista.getString(3));
            }while(articulosLista.moveToNext());

        }
        articulosLista.moveToFirst();
        if (articulosLista.getCount()>0) {
            articulosLista.moveToLast();

            ultimoAdd.setText("Total de Articulos Registrados: "+articulosLista.getCount()+"\n"+ultimoAdd.getText().toString() + "\nCodigo: " + articulosLista.getString(2) + "\nNombre: " + articulosLista.getString(3) + "\nFecha Registro: " + articulosLista.getString(12));
        }
        listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listado);
        itemList.setEmptyView(emptyText);

        itemList.setAdapter(listAdapter);


        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
// make Toast when click
                codei= itemList.getItemAtPosition(position).toString();



                AlertDialog.Builder alerta=new AlertDialog.Builder(Buscar.this);
                alerta.setTitle("Producto: "+codei).setIcon(R.drawable.skylogo).setMessage("seleccion:"+codei);
                alerta.setPositiveButton("Editar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i=new Intent(Buscar.this,Editar.class);
                        codei=codei.substring(0,codei.indexOf(" ")).trim();
                        articulosLista.moveToFirst();
                        do {
                            if(articulosLista.getString(2).equals(codei))
                            {
                                i.putExtra("codigo",articulosLista.getString(2));
                                i.putExtra("marca",articulosLista.getString(1));
                                i.putExtra("nombre",articulosLista.getString(3));
                                i.putExtra("colsab",articulosLista.getString(4));
                                i.putExtra("modelo",articulosLista.getString(6));
                                i.putExtra("referencia",articulosLista.getString(5));
                                i.putExtra("fven",articulosLista.getString(9));
                                i.putExtra("fadq",articulosLista.getString(8));
                                i.putExtra("ubicacion",articulosLista.getString(7));
                                i.putExtra("categoria",articulosLista.getString(11));
                                i.putExtra("estado",articulosLista.getInt(10));
                                break;
                            }
                        }while (articulosLista.moveToNext());


startActivity(i);


                    }
                }).setNegativeButton("eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        codei=codei.substring(0,codei.indexOf(" ")).trim();
                        mn.borrarArt(codei);
                        finish();
                        startActivity(getIntent());
                    }
                }).setNeutralButton("Cancelar",null).create().show();


            }
        });
        filterText.addTextChangedListener(new TextWatcher() {

            //un comentario
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Buscar.this.listAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }




}
