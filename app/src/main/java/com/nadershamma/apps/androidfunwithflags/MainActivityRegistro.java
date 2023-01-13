package com.nadershamma.apps.androidfunwithflags;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityRegistro extends AppCompatActivity {

    EditText edinombre,edicontraseña,ediCorreo,ediapellido,edicodigo;
    Button  btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_registro);
        edinombre= findViewById(R.id.editNombre);
        edicontraseña= findViewById(R.id.editTextContraseña);
        ediapellido= findViewById(R.id.editApellido);
        ediCorreo= findViewById(R.id.editCorreo);
        btnRegistrar= findViewById(R.id.buttonRegistrar);
        edicodigo = findViewById(R.id.editTextcodigo);
    }
    public void insertar(View view){

        this.insertarUsuario();

        Intent intent = new Intent(this,MainActivityLogin.class);
        startActivity(intent);
    }

    public void regresar(View view){


        Intent intent = new Intent(this,MainActivityLogin.class);
        startActivity(intent);
    }
    public void buscarCod(View view){

        this.buscar();

    }
    public void eliminarv(View view){

        this.eliminar();

    }
    public void modificarv(View view){

        this.modificar();

    }

    private  void  insertarUsuario()
    {
        //crea la base de datos
        UsuariosHelper usuariosHelper = new UsuariosHelper(this
                ,"usuariosDB",null,1);

        //abrir la base de datos
        //QLiteDatabase sql = clientesHelper.getReadableDatabase();//solo modo lectura
        SQLiteDatabase sql = usuariosHelper.getWritableDatabase();//solo modo escritura

        String nombre = edinombre.getText().toString();
        String apellido = ediapellido.getText().toString();
        String correo = ediCorreo.getText().toString();
        String clave = edicontraseña.getText().toString();

        if (!nombre.equals("") && !apellido.equals("") && !correo.equals("")){



            //crear una  collecion  de valores
            ContentValues lstvalores = new ContentValues();
            lstvalores.put("Nombre", nombre);
            lstvalores.put("Apellido", apellido);
            lstvalores.put("Clave", clave);
            lstvalores.put("Correo", correo);


            //enviar a la base de datos
            //cantidad regresa la cantidad de registros afectados en la base de datos
            long cantidad = sql.insert("Usuarios",null,lstvalores );


            //cerrar la base de datos
            sql.close();

            Toast.makeText(this,"Se inserto un Usuario",Toast.LENGTH_LONG).show();



        }else{
            Toast.makeText(this, "Los campo son obligatorios", Toast.LENGTH_SHORT).show();
        }

    }

    public void buscar(){
        UsuariosHelper usuariosHelper = new UsuariosHelper(this
                ,"usuariosDB",null,1);
        SQLiteDatabase sql = usuariosHelper.getReadableDatabase();
        String codigo =  edicodigo.getText().toString();

        String consulta = "SELECT Nombre,Apellido,Correo,Clave FROM Usuarios  WHERE Codigo=" + codigo;


        //cursor apunta a primer registro de la consulta

        Cursor cursor = sql.rawQuery(consulta,null);
        //ciclo where si hay datos para recuperar.
        //while(cursor.moveToFirst())

        if ( cursor.moveToFirst()){

            //el numero de poscion es lap posicion de la consulta
            edinombre.setText(cursor.getString(0));
            ediapellido.setText(cursor.getString(1));
            ediCorreo.setText(cursor.getString(2));
            edicontraseña.setText(cursor.getString(3));


        }else{
            Toast.makeText(this, "No se  encotraron registrso en la tabla ",
                    Toast.LENGTH_SHORT).show();
        }

        sql.close();
    }

    public void eliminar (){
        UsuariosHelper clientesHelper = new UsuariosHelper(this
                ,"usuariosDB",null,1);

        SQLiteDatabase sql = clientesHelper.getWritableDatabase();//solo modo escritura
        String codigo = edicodigo.getText().toString();

        int cantidad  = sql.delete("Usuarios", "Codigo=" + codigo,null);
        sql.close();

        if( cantidad > 0){
            Toast.makeText(this, "Registro eliminado",
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, " El registro no se encontro para eliminar ",
                    Toast.LENGTH_SHORT).show();
        }

        limpiar();
    }
    public void limpiar(){
        edicontraseña.setText("");
        ediapellido.setText("");
        edinombre.setText("");
        ediCorreo.setText("");
        edicodigo.setText("");
    }
    public void modificar(){
         UsuariosHelper usuariosHelper = new UsuariosHelper(this
                ,"usuariosDB",null,1);

        SQLiteDatabase sql = usuariosHelper.getWritableDatabase();//solo modo escritura
        String codigo = edicodigo.getText().toString();
        String nombre = edinombre.getText().toString();
        String apellido = ediapellido.getText().toString();
        String correo = ediCorreo.getText().toString();
        String clave = edicontraseña.getText().toString();

        ContentValues listValores = new ContentValues();
        listValores.put("Nombre", nombre);
        listValores.put("Apellido", apellido);
        listValores.put("Correo", correo);
        listValores.put("Clave", clave);
        //listValores.put("Codigo", codigo);
        int cantidad = sql.update("Usuarios",listValores,"Codigo="+ codigo,null);
        sql.close();

        if( cantidad > 0){
            Toast.makeText(this, "Registro modificado",
                    Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, " El registro no se encontro para modificar ",
                    Toast.LENGTH_SHORT).show();
        }
    }




}