package com.example.desk.a2_2_4_caso2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class AgregarPaciente extends AppCompatActivity{
    EditText nom,dir,mail;
    Button btn;
    DBAdapter db;
    TextView titulo;

    int op,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_paciente);

        nom=(EditText) findViewById(R.id.nombrep);
        dir=(EditText) findViewById(R.id.direccionp);
        mail=(EditText) findViewById(R.id.mailp);
        btn=(Button) findViewById(R.id.buttongp);
        titulo=(TextView) findViewById(R.id.titulop);

        String fant="";

        Bundle parametros = this.getIntent().getExtras();
        op = parametros.getInt("op");
        id = parametros.getInt("idp");

        db=new DBAdapter(getApplicationContext());
        if(op==1){
            titulo.setText("Actualizar paciente #"+id);

            db.open();
            Cursor paciente=db.getAllPaciente(id);
            nom.setText(paciente.getString(1));
            dir.setText(paciente.getString(2));
            mail.setText(paciente.getString(3));
            fant=paciente.getString(4);
            paciente.close();
            db.close();
        }

        final String finalFant = fant;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(op==1){
                    db.open();
                    db.updatePaciente(id,nom.getText().toString(),
                            dir.getText().toString(),
                            mail.getText().toString(),
                            finalFant);
                    db.close();
                }else{
                    db.open();
                    if(db.insertPaciente(
                            nom.getText().toString(),
                            dir.getText().toString(),
                            mail.getText().toString()
                    )){
                        Toast.makeText(getApplicationContext(),"Guardado",Toast.LENGTH_SHORT).show();
                    }
                    db.close();
                }
                Intent i=new Intent(AgregarPaciente.this,Pacientes.class);
                startActivity(i);
            }
        });
    }
}
