package com.example.desk.a2_2_4_caso2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class AgregarMedicamento extends AppCompatActivity{
    EditText nom,pade,inst,fcon,fini,ffin;
    CheckBox vig;
    Button btn;
    TextView titulo;

    DBAdapter db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_medicamento);

        nom = (EditText) findViewById(R.id.nombre);
        pade = (EditText) findViewById(R.id.padeci);
        inst = (EditText) findViewById(R.id.instruc);
        fcon = (EditText) findViewById(R.id.fcon);
        fini = (EditText) findViewById(R.id.fini);
        ffin = (EditText) findViewById(R.id.ffin);
        vig = (CheckBox) findViewById(R.id.checkBox);
        btn = (Button) findViewById(R.id.btnGuardar);
        titulo = (TextView) findViewById(R.id.titulop);

        db=new DBAdapter(getApplicationContext());
        final int idp=this.getIntent().getExtras().getInt("idp");
        final int idm=this.getIntent().getExtras().getInt("idm");
        final int op=this.getIntent().getExtras().getInt("op");

        vig.setChecked(false);
        if(op==1){
            titulo.setText("Actualizar medicamento");
            db.open();
            Cursor resultado=db.getMedicamento(idm);
            nom.setText(resultado.getString(0));
            pade.setText(resultado.getString(1));
            inst.setText(resultado.getString(2));
            fcon.setText(resultado.getString(3));
            fini.setText(resultado.getString(4));
            ffin.setText(resultado.getString(5));
            int v=resultado.getInt(6);
            if(v==1){
                vig.setChecked(true);
            }
            resultado.close();
            db.close();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int v=1;
                if(!vig.isChecked()){
                    v=0;
                }
                if(op==1){
                    db.open();
                    db.updateMedicamento(idm,idp,nom.getText().toString(),
                            pade.getText().toString(),
                            inst.getText().toString(),
                            fcon.getText().toString(),
                            fini.getText().toString(),
                            ffin.getText().toString(),
                            v);
                    db.close();
                }else{
                    db.open();
                    db.insertMedicamento(idp,nom.getText().toString(),
                            pade.getText().toString(),
                            inst.getText().toString(),
                            fcon.getText().toString(),
                            fini.getText().toString(),
                            ffin.getText().toString(),
                            v);
                    db.close();
                }
                Intent intent=new Intent(AgregarMedicamento.this,Medicamento.class);
                intent.putExtra("idp",idp);
                startActivity(intent);
            }
        });
    }
}
