package com.example.manuelfleivar.skynventory;



import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import google.zxing.integration.android.IntentIntegrator;
import google.zxing.integration.android.IntentResult;

public class Agregar extends AppCompatActivity {
    Button escanear, guardar;
    Spinner spCategoria;
    DBManager mn;
    EditText codigo, nombre, fadquisicion, fvencimiento, marca, colsab, modelo, referencia, ubicacion;
    CheckBox pcodigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        codigo=(EditText)findViewById(R.id.eda_codigo);

        escanear=(Button)findViewById(R.id.bta_escanear);
        escanear.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                if (v.getId() == R.id.bta_escanear) {
                    //Se instancia un objeto de la clase IntentIntegrator
                    IntentIntegrator scanIntegrator = new IntentIntegrator(Agregar.this);
                    //Se procede con el proceso de scaneo
                    scanIntegrator.initiateScan();
                }
            }  });
        mn=new DBManager(Agregar.this);
        nombre=(EditText)findViewById(R.id.eda_nombre);
        final Calendar c = Calendar.getInstance();
        final int año = c.get(Calendar.YEAR);
        final int mes = c.get(Calendar.MONTH);
        final int dia = c.get(Calendar.DAY_OF_MONTH);

        fadquisicion=(EditText)findViewById(R.id.eda_fadquisicion);
        fvencimiento=(EditText)findViewById(R.id.eda_fvencimiento);
        marca=(EditText)findViewById(R.id.eda_marca);
        colsab=(EditText)findViewById(R.id.eda_colsab);
        modelo=(EditText)findViewById(R.id.eda_modelo);
        spCategoria=(Spinner)findViewById(R.id.spa_categoria) ;
        referencia=(EditText)findViewById(R.id.eda_referencia);
        ubicacion=(EditText)findViewById(R.id.eda_ubicacion);
        guardar=(Button)findViewById(R.id.bta_guardar);
        List<String> lista=mn.ObtenerCategorias();

        ArrayAdapter<String> data=new ArrayAdapter<String>(Agregar.this,android.R.layout.simple_spinner_item,lista);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCategoria.setAdapter(data);


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(codigo.getText().toString().isEmpty() || nombre.getText().toString().isEmpty()){
                    Snackbar.make(v,"El nombre y el codigo no puede ir vacios",Snackbar.LENGTH_SHORT).show();

                }else{
                    AlertDialog.Builder alerta=new AlertDialog.Builder(Agregar.this);
                    alerta.setTitle("¿Estas seguro que desea guardar estos datos?");
                    //spCategoria.getSelectedItem().toString();
                    alerta.setMessage("Codigo:"+ codigo.getText().toString()+
                            "\nMarca:"+marca.getText().toString()+
                            "\nNombre:"+nombre.getText().toString()+
                            "\nCodigo / Sabor:"+ colsab.getText().toString()+
                            "\nModelo:"+modelo.getText().toString()+
                            "\nReferencia:"+referencia.getText().toString()+
                            "\nFecha de Adquisicion:"+fadquisicion.getText().toString()+
                            "\nFecha de Vencimiento:"+fvencimiento.getText().toString()+
                            "\nUbicacion:"+ubicacion.getText().toString());

                    alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mn.insertarArticulo(codigo.getText().toString(),
                                    nombre.getText().toString(),marca.getText().toString(),
                                    referencia.getText().toString(),modelo.getText().toString(),
                                    ubicacion.getText().toString(),fadquisicion.getText().toString(),
                                    fvencimiento.getText().toString());
                            Toast.makeText(Agregar.this, "Articulo agregado con Exito!!!", Toast.LENGTH_SHORT).show();

                        }
                    });
                    alerta.setNegativeButton("Cancelar", null);
                    alerta.create().show();




                }
            }




        });
fadquisicion.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus){
            DatePickerDialog dpdadquisicion = new DatePickerDialog(Agregar.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    fadquisicion.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                }
            }, año, mes, dia);
            dpdadquisicion.setTitle("Seleccione Fecha");
            dpdadquisicion.show();

        }
    }


        });
        fvencimiento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    DatePickerDialog dpdadquisicion = new DatePickerDialog(Agregar.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                            fvencimiento.setText(year+"-"+(monthOfYear+1)+"-"+dayOfMonth);
                        }
                    }, año, mes, dia);
                    dpdadquisicion.setTitle("Seleccione Fecha");
                    dpdadquisicion.show();
                    marca.requestFocus();
                }

            }
        });
        final LinearLayout contenedorCodigo=(LinearLayout)findViewById(R.id.contenedorCodigo);
        pcodigo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    contenedorCodigo.setVisibility(View.VISIBLE);
                }else{
                    contenedorCodigo.setVisibility(View.GONE);
                }
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //Se obtiene el resultado del proceso de scaneo y se parsea
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //Quiere decir que se obtuvo resultado pro lo tanto:
            //Desplegamos en pantalla el contenido del código de barra scaneado
            String scanContent = scanningResult.getContents();
            codigo.setText(scanContent);
            //Desplegamos en pantalla el nombre del formato del código de barra scaneado
            Toast toast = Toast.makeText(this,
                    "codigo:"+scanContent, Toast.LENGTH_SHORT);
            toast.show();
        }else{
            //Quiere decir que NO se obtuvo resultado
            Toast toast = Toast.makeText(this,
                    "No se ha recibido datos del Escaneo!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



}
