package com.example.manuelfleivar.skynventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuelfleivar on 29/5/16.
 */
public class DBManager {
    public static final String tabla="categorias";
    public static final String tabla2="articulos";
    public static final String catId="_id";
    public static final String nombre="nombre";
    public static final String marca="marca";
    public static final String codigo="codigo";
    public static final String modelo="modelo";
    public static final String referencia="referencia";
    public static final String codlsab="colorsabor";
    public static final String fechaAD="fecha_adquirido";
    public static final String fechaVn="fecha_vencimiento";
    public static final String ubicacion="ubicacion";
    public static final String descripcion="descripcion";
    public static final String crearCategorias_t=" create table "+tabla+" " +
            "("+catId+" integer primary key autoincrement,"+nombre+" text not null,"+descripcion+" text);";

    public static final String crearArticulos_t="CREATE TABLE articulos(_id INTEGER PRIMARY KEY AUTOINCREMENT, marca TEXT(20), " +
            "codigo TEXT(15) NOT NULL, nombre TEXT NOT NULL, " +
            "colorsabor TEXT, referencia TEXT,modelo TEXT, ubicacion TEXT, fechaadq TEXT, fechaven TEXT,estado INTEGER, categoria TEXT,registro TEXT);";
    DBHelper helper;
    SQLiteDatabase db;


    public DBManager(Context context) {
        helper = new DBHelper(context);
        db = helper.getWritableDatabase();
    }

    //Metodos para agregar, y obtener categorias
    public ContentValues ContenedorValores(String n, String d)
    {
        ContentValues contenedor=new ContentValues();
        contenedor.put("nombre",n);
        contenedor.put("descripcion",d);
        return contenedor;

    }
    public void insertarCategoria(String n,String d)
    {
        db.insert(tabla,null,ContenedorValores(n,d));
    }
    public List<String> ObtenerCategorias() {

        List<String> categorias= new ArrayList<>();
        Cursor c=db.rawQuery("select * from "+tabla,null);
        if(c.moveToFirst()){
            do {if(c.getString(2).equals("categoria"))
                categorias.add(c.getString(1));
            }while(c.moveToNext());

        }
        c.close();
        return categorias;
    }

    public void modificar(String cod,String nom,String mar,String ref,String mod,String ubi,String fad,String fvn,String cols,int stt,String cat){


        db.update(tabla2, contenedorEditar(nom,mar,ref,mod,ubi,fad,fvn,cols,stt,cat), "codigo="+cod, null);

    }
    public ContentValues contenedorEditar(String nom,String mar,String ref,String mod,String ubi,String fad,String fvn,String cols,int stt,String cat)
    {
        ContentValues contenedor=new ContentValues();
        contenedor.put("nombre",nom);

        contenedor.put("colorsabor",cols);
        contenedor.put("marca",mar);
        contenedor.put("referencia",ref);
        contenedor.put("modelo",mod);
        contenedor.put("ubicacion",ubi);
        contenedor.put("fechaadq",fad);
        contenedor.put("fechaven",fvn);
        contenedor.put("estado",stt);
        contenedor.put("categoria",cat);

        return contenedor;
    }


    public List<String> ObtenerUbicacion() {

        List<String> categorias= new ArrayList<>();
        Cursor c=db.rawQuery("select * from "+tabla,null);
        if(c.moveToFirst()){
            do {
                if(c.getString(2).equals("zona"))
                categorias.add(c.getString(1));
            }while(c.moveToNext());

        }
        c.close();
        return categorias;
    }

    //Metodos para agregar y obtener articulos
    public void insertarArticulo(String cod,String nom,String mar,String ref,String mod,String ubi,String fad,String fvn,String cols,int stt, String cat,String reg)
    {
        db.insert(tabla2,null,ContenedorArticulos(cod,nom,mar,ref,mod,ubi,fad,fvn,cols,stt,cat,reg));
    }
    public ContentValues ContenedorArticulos(String cod,String nom,String mar,String ref,String mod,String ubi,String fad,String fvn,String cols,int stt,String cat,String reg)
    {
        ContentValues contenedor=new ContentValues();
        contenedor.put("nombre",nom);
        contenedor.put("codigo",cod);
        contenedor.put("colorsabor",cols);
        contenedor.put("marca",mar);
        contenedor.put("referencia",ref);
        contenedor.put("modelo",mod);
        contenedor.put("ubicacion",ubi);
        contenedor.put("fechaadq",fad);
        contenedor.put("fechaven",fvn);
        contenedor.put("estado",stt);
        contenedor.put("categoria",cat);
        contenedor.put("registro",reg);
        return contenedor;

    }

    public Cursor ObtenerArticulos() {


        Cursor c=db.rawQuery("select * from "+tabla2,null);


        return c;
    }
    public Cursor ObtenerArticulosc() {

        List<String> articulos= new ArrayList<>();
        Cursor c=db.rawQuery("select * from "+tabla2,null);


        return c;
    }
    public Cursor ObtenerArticulosc(int x) {

        List<String> articulos= new ArrayList<>();
        Cursor c=db.rawQuery("select * from "+tabla2+" where estado = "+x,null);


        return c;
    }
    public void borrarDB(int op)
    {

        if(op==1)
            db.delete(tabla2,"",null);
        else
        if(op==2)
            db.delete(tabla,"descripcion = "+"'categoria'",null);
        else
            db.delete(tabla, "descripcion='zona'", null);
    }
    public void borrarArt(String code)
    {
        db.delete(tabla2,"codigo='"+code+"'",null);
    }

}