package com.example.tobiassilva.calculadoradenotas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ActMedias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_medias);

        Bundle b = getIntent().getExtras();
        TextView media1 = findViewById(R.id.media1);
        DecimalFormat decimalFormat = new DecimalFormat("##.#");
        assert b != null;
        String s = decimalFormat.format(b.getDouble("media"));
        media1.setText(s);
    }
}
