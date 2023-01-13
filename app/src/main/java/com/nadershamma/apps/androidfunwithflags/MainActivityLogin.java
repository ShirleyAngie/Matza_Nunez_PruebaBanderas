package com.nadershamma.apps.androidfunwithflags;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;

public class MainActivityLogin extends AppCompatActivity {
EditText edinombre,edicontraseña;
Button btnIngresar, btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        edinombre=  findViewById(R.id.editTextTextNmobre);
        edicontraseña=  findViewById(R.id.editTextTextContraseña);
        btnIngresar= findViewById(R.id.btnIngresar);
        btnRegistrar= findViewById(R.id.buttonregistrar);


    }

     public void  onregistrar(View view){
         Intent intent= new Intent(this,MainActivityRegistro.class);
         startActivity(intent);


     }

    public void  login(View view){
        this.buscarUsuarios();
    }


    public void buscarUsuarios(){
        UsuariosHelper usuariosHelper = new UsuariosHelper(this
                ,"usuariosDB",null,1);
        SQLiteDatabase sql = usuariosHelper.getReadableDatabase();
        //String consulta = "SELECT * FROM Usuarios";
        //String consulta = "SELECT Codigo,Nombre,Apellido,Correo FROM Clientes ORDER BY Codigo";
        String consulta = "SELECT Correo, Clave FROM Usuarios";


        //cursor apunta a primer registro de la consulta

        Cursor cursor = sql.rawQuery(consulta,null);
        //ciclo where si hay datos para recuperar.
        //while(cursor.moveToFirst())

        if ( cursor.moveToFirst()){

            //el numero de poscion es lap posicion de la consulta
           // editTextNombre.setText(cursor.getString(1));
            String usuario = edinombre.getText().toString();
            String contraseña= edicontraseña.getText().toString();

            do{
                if(usuario.equals(cursor.getString(0))&& contraseña.equals(cursor.getString(1))){
                    Intent intent= new Intent(this,MainActivity.class);
                    startActivity(intent);

                }

            }while(cursor.moveToNext());





        }else{
            Toast.makeText(this, "No se  encotraron registrso en la tabla ",
                    Toast.LENGTH_SHORT).show();
        }

        sql.close();
    }
}