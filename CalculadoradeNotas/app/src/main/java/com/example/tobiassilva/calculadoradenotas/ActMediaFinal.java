package com.example.tobiassilva.calculadoradenotas;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ActMediaFinal extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_media_final);

        Bundle b = getIntent().getExtras();
        TextView media1 = findViewById(R.id.media1b);
        TextView media2 = findViewById(R.id.media2b);
        TextView mediaFinal = findViewById(R.id.mediaFinal);

        DecimalFormat decimalFormat = new DecimalFormat("##.#");

        assert b != null;
        String s = decimalFormat.format(b.getDouble("media1"));
        media1.setText(s);
        s = decimalFormat.format(b.getDouble("media2"));
        media2.setText(s);
        s =  decimalFormat.format(b.getDouble("mediaFinal"));
        mediaFinal.setText(s);

        TextView textView = findViewById(R.id.txtSituação);

        if(b.getDouble("mediaFinal") > 7){
            textView.setText("Aprovado");
            textView.setTextColor(Color.GREEN);
        }else if (b.getDouble("mediaFinal") < 4){
            textView.setText("Reprovado");
            textView.setTextColor(Color.RED);
        }else if(b.getDouble("mediaFinal") < 7 && b.getDouble("mediaFinal") > 4){
            textView.setText("Ex. Final");
            textView.setTextColor(Color.YELLOW);
        }
    }
}
