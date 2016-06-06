package com.example.manuelfleivar.skynventory;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Exportar extends AppCompatActivity {
    public com.github.clans.fab.FloatingActionButton btExport;
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

        List<String> articulosLista;
        articulosLista=mn.ObtenerArticulos();
        currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        String Fnamexls = "Inventario__" +currentDate+ ".xls";
        sdCard = Environment.getExternalStorageDirectory();
        directory = new File(sdCard.getAbsolutePath() + "/Download");
        directory.mkdirs();
        file = new File(directory, Fnamexls);
        file2 = new File(directory, "Carga_de_invetario_" +currentDate+ ".xls");
        file3 = new File(directory, "Impuesto_Colmena_" +currentDate+ ".xls");
        wbSettings = new WorkbookSettings();

        wbSettings.setLocale(new Locale("es", "ES"));
        try {

            workbook = Workbook.createWorkbook(file, wbSettings);
            workbook2 = Workbook.createWorkbook(file2, wbSettings);


            sheet = workbook.createSheet("SAPROD", 0);

        } catch (Exception i) {

        }

        btExport=(com.github.clans.fab.FloatingActionButton)findViewById(R.id.menu_i1);

    }
}
