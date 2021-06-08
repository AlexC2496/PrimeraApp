package com.example.primeraapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String NOMBRE_BD="prueba.bd";
    private static final int VERSION_BD=1;
    private static final String TABLA_EJERICIOS="CREATE TABLE EJERCICIOS(color TEXT, ejercicio TEXT, descripcion TEXT)";
    public AdminSQLiteOpenHelper(@Nullable Context context) {
        super(context, NOMBRE_BD, null, VERSION_BD);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
      sqLiteDatabase.execSQL(TABLA_EJERICIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ejercicios");
        sqLiteDatabase.execSQL(TABLA_EJERICIOS);


    }

    public void agregarEjericios(String color, String ejercicio, String descripcion){
        SQLiteDatabase bd=getWritableDatabase();
        if(bd!=null){
            bd.execSQL("INSERT INTO EJERCICIOS (color, ejercicio, descripcion) VALUES ('','Flexiones', 'Realizar 4 series de 20 repeticiones')");
            bd.close();
        }
    }
    public List<listElement> mostarEjercicios(){
        SQLiteDatabase bd =getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM EJERCICIOS", null);
        List<listElement> list= new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                list.add(new listElement(cursor.getString(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
}
