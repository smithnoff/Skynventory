package com.example.manuelfleivar.skynventory;



import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
    LinearLayout contenedorCodigo;
    EditText codigo, nombre, fadquisicion, fvencimiento, marca, colsab, modelo, referencia, ubicacion;
    CheckBox pcodigo;
    RadioGroup rgEstado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);

        codigo=(EditText)findViewById(R.id.eda_codigo);
       rgEstado=(RadioGroup)findViewById(R.id.rga_radiogroup);
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
        codigo.setText("N/A");
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
                            "\nColor / Sabor:"+ colsab.getText().toString()+
                            "\nModelo:"+modelo.getText().toString()+
                            "\nReferencia:"+referencia.getText().toString()+
                            "\nFecha de Adquisicion:"+fadquisicion.getText().toString()+
                            "\nFecha de Vencimiento:"+fvencimiento.getText().toString()+
                            "\nUbicacion:"+ubicacion.getText().toString());

                    alerta.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            int idEstado=0;
                            switch (rgEstado.getCheckedRadioButtonId())
                            {
                                case R.id.rba_nuevo:
                                    idEstado=1;
                                    break;
                                case R.id.rba_bueno:
                                    idEstado=2;
                                    break;
                                case R.id.rba_danado:
                                    idEstado=3;
                                    break;
                                case R.id.rba_reparado:
                                    idEstado=4;
                                    break;
                            }
                            mn.insertarArticulo(codigo.getText().toString(),
                                    nombre.getText().toString(),marca.getText().toString(),
                                    referencia.getText().toString(),modelo.getText().toString(),
                                    ubicacion.getText().toString(),fadquisicion.getText().toString(),
                                    fvencimiento.getText().toString(),colsab.getText().toString(),idEstado);
                            Toast.makeText(Agregar.this, "Articulo agregado con Exito!!!", Toast.LENGTH_SHORT).show();
                            //para eliminar al regresar al registro
                            codigo.setText("");
                            nombre.setText("");
                            marca.setText("");
                            referencia.setText("");
                            modelo.setText("");
                            ubicacion.setText("");
                            fadquisicion.setText("");
                            colsab.setText("");
                            fvencimiento.setText("");

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
        contenedorCodigo=(LinearLayout)findViewById(R.id.contenedorCodigo);
        pcodigo=(CheckBox)findViewById(R.id.chba_poseecod);
        pcodigo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    contenedorCodigo.setVisibility(View.VISIBLE);
                }else{
                    contenedorCodigo.setVisibility(View.GONE);
                    codigo.setText("N/A");
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_agregar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_agregar) {
            Cursor ultimo=mn.ObtenerArticulosc();
            ultimo.moveToLast();
            nombre.setText(ultimo.getString(3));
            marca.setText(ultimo.getString(1));
            codigo.setText(ultimo.getString(2));
            colsab.setText(ultimo.getString(4));
            modelo.setText(ultimo.getString(6));
            referencia.setText(ultimo.getString(5));
            ubicacion.setText(ultimo.getString(7));



            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
