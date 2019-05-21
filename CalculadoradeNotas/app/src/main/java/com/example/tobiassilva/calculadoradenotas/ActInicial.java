package com.example.tobiassilva.calculadoradenotas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class ActInicial extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_inicial);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnActPrincial = findViewById(R.id.btnCalc);
        Button btnFormaCalculo = findViewById(R.id.btnFormulas);
        btnActPrincial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActInicial.this,ActPrincipal.class));
            }
        });

        btnFormaCalculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActInicial.this,ActForma.class));
            }
        });




    }

}
