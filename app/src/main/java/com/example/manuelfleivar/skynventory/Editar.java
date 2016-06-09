package com.example.manuelfleivar.skynventory;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Editar extends AppCompatActivity {
    Button escanear, guardar;
    Spinner spCategoria,spUbicacion;
    DBManager mn;
    LinearLayout contenedorCodigo;
    EditText codigo, nombre, fadquisicion, fvencimiento, marca, color, modelo, referencia, ubicacion;
    CheckBox pcodigo;
    RadioGroup rgEstado;
    private String currentDate;
    private int idEstado;
    private Bundle datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        datos=getIntent().getExtras();
        rgEstado=(RadioGroup)findViewById(R.id.rga_radiogroup);

         codigo=(EditText)findViewById(R.id.eda_codigo);
         nombre=(EditText)findViewById(R.id.eda_nombre);
        fadquisicion=(EditText)findViewById(R.id.eda_fadquisicion);
        fvencimiento=(EditText)findViewById(R.id.eda_fvencimiento);
        marca=(EditText)findViewById(R.id.eda_marca);
        color=(EditText)findViewById(R.id.eda_color);
        modelo=(EditText)findViewById(R.id.eda_modelo);
        spCategoria=(Spinner)findViewById(R.id.spa_categoria) ;
        spUbicacion=(Spinner)findViewById(R.id.spa_cateubicacion) ;
        referencia=(EditText)findViewById(R.id.eda_referencia);
        ubicacion=(EditText)findViewById(R.id.eda_ubicacion);
        guardar=(Button)findViewById(R.id.bta_guardar);

        // agregando los valores
  mn=new DBManager(Editar.this);
        codigo.setText(datos.getString("codigo"));
        nombre.setText(datos.getString("nombre"));
        marca.setText(datos.getString("marca"));
        modelo.setText(datos.getString("modelo"));
        color.setText(datos.getString("colsab"));
        referencia.setText(datos.getString("referencia"));

        fvencimiento.setText(datos.getString("fven"));
        fadquisicion.setText(datos.getString("fadq"));

        List<String> lista3=mn.ObtenerCategorias();
        List<String> lista4=mn.ObtenerUbicacion();
        ArrayAdapter<String> data=new ArrayAdapter<String>(Editar.this,android.R.layout.simple_spinner_item,lista3);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> data2=new ArrayAdapter<String>(Editar.this,android.R.layout.simple_spinner_item,lista4);
        data2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(data);
        spUbicacion.setAdapter(data2);
       // Toast.makeText(Editar.this," es: "+datos.getString("categoria"),Toast.LENGTH_LONG).show();
        for (int i = 0; i < spCategoria.getCount(); i++) {
            if (spCategoria.getItemAtPosition(i).toString().equals(datos.getString("categoria")))
            {
                spCategoria.setSelection(i);
                break;
            }

        }
        for (int i = 0; i < spUbicacion.getCount(); i++) {
            if (spUbicacion.getItemAtPosition(i).toString().equals(datos.getString("ubicacion")))
            {
                spUbicacion.setSelection(i);
                break;
            }

        }



        spUbicacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                ubicacion.setText(""+spUbicacion.getItemAtPosition(position).toString());


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


switch (datos.getInt("estado")){
    case 1:
        ((RadioButton)findViewById(R.id.rba_nuevo)).setChecked(true);
        break;
      case 2:
        ((RadioButton)findViewById(R.id.rba_bueno)).setChecked(true);
        break;
        case 3:
        ((RadioButton)findViewById(R.id.rba_danado)).setChecked(true);
        break;

        case 4:
        ((RadioButton)findViewById(R.id.rba_reparado)).setChecked(true);
        break;
}

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (codigo.getText().toString().isEmpty() || nombre.getText().toString().isEmpty()) {
                    Snackbar.make(v, "El nombre o el codigo no puede ir vacios", Snackbar.LENGTH_SHORT).show();

                } else {
                    idEstado = 0;
                    String state = "";
                    switch (rgEstado.getCheckedRadioButtonId()) {
                        case R.id.rba_nuevo:
                            idEstado = 1;
                            state = "Nuevo";
                            break;
                        case R.id.rba_bueno:
                            idEstado = 2;
                            state = "Bueno";
                            break;
                        case R.id.rba_danado:
                            idEstado = 3;
                            state = "Danado";
                            break;
                        case R.id.rba_reparado:
                            idEstado = 4;
                            state = "Reparacion";
                            break;
                    }
                    AlertDialog.Builder alerta = new AlertDialog.Builder(Editar.this);
                    alerta.setTitle("¿Estas seguro que desea guardar estos Cambios?");
                    //spCategoria.getSelectedItem().toString();
                    alerta.setMessage("Categoria:" + spCategoria.getSelectedItem().toString() + "\nCodigo:" + codigo.getText().toString() +
                            "\nMarca:" + marca.getText().toString() +
                            "\nNombre:" + nombre.getText().toString() +
                            "\nColor / Sabor:" + color.getText().toString() +
                            "\nModelo:" + modelo.getText().toString() +
                            "\nReferencia:" + referencia.getText().toString() +
                            "\nFecha de Adquisicion:" + fadquisicion.getText().toString() +
                            "\nFecha de Vencimiento:" + fvencimiento.getText().toString() +
                            "\nUbicacion:" + ubicacion.getText().toString() +

                            "\nEstado:" + state);

                    alerta.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());

                            mn.modificar(codigo.getText().toString(),
                                    nombre.getText().toString(), marca.getText().toString(),
                                    referencia.getText().toString(), modelo.getText().toString(),
                                    ubicacion.getText().toString(), fadquisicion.getText().toString(),
                                    fvencimiento.getText().toString(), color.getText().toString(), idEstado, spCategoria.getSelectedItem().toString());
                            Snackbar.make(v, "Articulo agregado con Exito!!!", Snackbar.LENGTH_SHORT).show();
                            //para eliminar al regresar al registro
                            Intent i=new Intent(Editar.this,Buscar.class);
                            startActivity(i);

                        }
                    });
                    alerta.setNegativeButton("Cancelar", null).setIcon(R.drawable.skylogo);
                    alerta.create().show();

                }
            }
        });
    }
}