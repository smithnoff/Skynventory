package com.example.manuelfleivar.skynventory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Exportar extends AppCompatActivity {
    public com.github.clans.fab.FloatingActionButton btExport, btVersionS, btExportarBD;
    public File sdCard, directory, file,file2,file3;
    public WorkbookSettings wbSettings;
    public WritableWorkbook workbook,workbook2,workbook3;
    public WritableSheet sheet, sheet2,sheet3,sheet4,sheet5;
    public DBManager mn;
    private Cursor c;
    public SQLiteDatabase db;
    String currentDate="";
    public DBHelper helper;
   public Label label, label1, label2, label3, label4, label5, label6, label7, label8, label9,
            label10, label11, label12, label13, label14, label15, label16, label17, label18,
            label19, label20, label21, label22, label23, label24, label25, label26, label27,
            label28, label29, label30, label31, label32, label33, label34, label35, label36,
            label37, label38, label39, label40, label41, label42, label43, label44, label45,
            label46, label47, label48, label49, label50, label51, label52, label53,label54;
    jxl.write.Number num1,num2,num3,num4,num5,num6,num7,num8,num9,num10,num11,num12,num13,num14,num15,num16,num17,num18,
            num19,num20,num21,num22,num23,num24,num25,num26,num27,num28,num29,num30,num31,num32,num33,num34,num35;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exportar);
        helper = new DBHelper(this);
        db = helper.getWritableDatabase();
        mn = new DBManager(this);

        final Cursor articulosLista;
        articulosLista=mn.ObtenerArticulosc();


        btExport=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_i1);
        btExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Fnamexls="Inventario"  + ".xls";
                File sdCard = Environment.getExternalStorageDirectory();
                File directory = new File (sdCard.getAbsolutePath() + "/Download");
                directory.mkdirs();
                File file = new File(directory, Fnamexls);

                WorkbookSettings wbSettings = new WorkbookSettings();

                wbSettings.setLocale(new Locale("en", "EN"));

                WritableWorkbook workbook;
                try {
                    int fila = 1;
                    workbook = Workbook.createWorkbook(file, wbSettings);
                    //workbook.createSheet("Report", 0);
                    WritableSheet sheet = workbook.createSheet("Hoja 1", 0);
                    Label label = new Label(0,0,  "Codigo");
                    Label label1 = new Label(1,0,"Nombre");
                    Label label2 = new Label(2,0,"Marca");
                    Label label3 = new Label(3,0,"Modelo");
                    Label label4 = new Label(4,0,"Referencia");
                    Label label5= new Label(5,0,"Ubicacion");
                    Label label6 = new Label(6,0,"Fecha Ven.");
                    try {
                        sheet.addCell(label);
                        sheet.addCell(label1);
                        sheet.addCell(label2);
                        sheet.addCell(label3);
                        sheet.addCell(label4);
                        sheet.addCell(label5);
                        sheet.addCell(label6);
                    } catch (RowsExceededException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (WriteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                 if (articulosLista.getCount()>0)
                 {
                     articulosLista.moveToFirst();
                     do {

                         Label label8 = new Label(0,fila, articulosLista.getString(1));
                         Label label9 = new Label(1,fila,articulosLista.getString(2));
                         Label label10 = new Label(2,fila,articulosLista.getString(3));
                         Label label11= new Label(3,fila,articulosLista.getString(4));
                         Label label12 = new Label(4,fila,articulosLista.getString(5));
                         Label label13= new Label(5,fila,articulosLista.getString(6));
                         Label label14 = new Label(6,fila,articulosLista.getString(7));
                         try {
                             sheet.addCell(label14);
                             sheet.addCell(label8);
                             sheet.addCell(label9);
                             sheet.addCell(label10);
                             sheet.addCell(label11);
                             sheet.addCell(label12);
                             sheet.addCell(label13);
                         } catch (RowsExceededException e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                         } catch (WriteException e) {
                             // TODO Auto-generated catch block
                             e.printStackTrace();
                         }
                         fila++;

                     }while (articulosLista.moveToNext());

                 }


                    workbook.write();
                    try {
                        workbook.close();
                    } catch (WriteException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    //createExcel(excelSheet);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Snackbar.make(v,"Archivo creado satisfactoriamente: vaya a la carpeta Download para ver archivo",Snackbar.LENGTH_LONG).show();
            }

        });
        btVersionS=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_i2);
        btVersionS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Adquiera la version PRO para utilizar esta opcion.",Snackbar.LENGTH_SHORT).show();

            }
        });
        btExportarBD=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_i3);
        btExportarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"Adquiera la version PRO para utilizar esta opcion.",Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
