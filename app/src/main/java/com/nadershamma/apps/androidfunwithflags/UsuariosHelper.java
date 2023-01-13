package com.nadershamma.apps.androidfunwithflags;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;


public class UsuariosHelper extends SQLiteOpenHelper {
    //Crear las tablas
    private  String createTable_Usuarios = "CREATE TABLE Usuarios" +
            "(Codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
            "Nombre TEXT," +
            "Apellido TEXT," +
            "Clave TEXT," +
            "Correo TEXT)";
    public UsuariosHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTable_Usuarios);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL(createTable_Usuarios);

    }
}
