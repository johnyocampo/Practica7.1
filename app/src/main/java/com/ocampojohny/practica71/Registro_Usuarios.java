package com.ocampojohny.practica71;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by johny on 15/01/2016.
 */
public class Registro_Usuarios extends AppCompatActivity {


    EditText ETnombre,ETpassword,ETusuario;
    Button bcargar,beliminar,bmodificar;
    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);

        BaseHelper baseHelper= new BaseHelper(this,"Demodb",null,1);
        db= baseHelper.getWritableDatabase();

        ETnombre = (EditText) findViewById(R.id.ETnombre);
        ETpassword = (EditText) findViewById(R.id.Etpaswword);
        ETusuario = (EditText) findViewById(R.id.ETusuario);
        bcargar = (Button) findViewById(R.id.BTregistrarse);}















    public void Guardar_datos(View view){

        String nombre=ETnombre.getText().toString();
        String Password=ETpassword.getText().toString();
        String Usuario=ETpassword.getText().toString();
        BaseHelper baseHelper= new BaseHelper(this,"Demodb",null,1);
        SQLiteDatabase db= baseHelper.getWritableDatabase();
        if(db!=null){

            ContentValues registro_nuevo=new ContentValues();
            registro_nuevo.put("Nombre", nombre);
            registro_nuevo.put("Password", Password);
            registro_nuevo.put("Usuario", Usuario);
            long i=db.insert("personas",null,registro_nuevo);
            if (i>0){
                Toast.makeText(this,"Registro insertado", Toast.LENGTH_SHORT).show();
            }
        }


    }


}


