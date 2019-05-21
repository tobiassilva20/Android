package com.example.tobiassilva.calculadoradenotas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ActPrincipal extends AppCompatActivity  {
       private RadioGroup rg;
       private double parcial1, oficial1, parcial2, oficial2, media1Bim, media2Bim, mediaFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_principal);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Desativa a ediçao de notas
        //findViewById(R.id.notaParcial1).setEnabled(false);
        findViewById(R.id.notaParcial1).setVisibility(View.INVISIBLE);
        findViewById(R.id.notaOficial1).setVisibility(View.INVISIBLE);
        findViewById(R.id.notaParcial2).setVisibility(View.INVISIBLE);
        findViewById(R.id.notaOficial2).setVisibility(View.INVISIBLE);
        final Button btnCalc = findViewById(R.id.btnCalcular);
        btnCalc.setEnabled(false);

        //Botao radioGrupo
        rg =  findViewById(R.id.radioGroup);

        //Verifica a opçao selecionada
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radioBtnB1){
                    findViewById(R.id.notaParcial1).setVisibility(View.VISIBLE);
                    findViewById(R.id.notaOficial1).setVisibility(View.VISIBLE);
                    findViewById(R.id.notaParcial2).setVisibility(View.INVISIBLE);
                    findViewById(R.id.notaOficial2).setVisibility(View.INVISIBLE);
                    btnCalc.setEnabled(true);

                }else if(checkedId == R.id.radioBtnB2){
                    findViewById(R.id.notaParcial1).setVisibility(View.VISIBLE);
                    findViewById(R.id.notaOficial1).setVisibility(View.VISIBLE);
                    findViewById(R.id.notaParcial2).setVisibility(View.VISIBLE);
                    findViewById(R.id.notaOficial2).setVisibility(View.VISIBLE);
                    btnCalc.setEnabled(true);
                }
            }
        });

        //Tratamento de eventos do botao CalcularMedia
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int RadioSelecionado = rg.getCheckedRadioButtonId();
                if(RadioSelecionado == R.id.radioBtnB1){
                    EditText edt1 =  findViewById(R.id.notaParcial1);
                    EditText edt2 =  findViewById(R.id.notaOficial1);

                    //Verifica se os campos de notas foram preenchidos.
                    try {
                        new NotaExceptions().notaException(edt1.getText().toString(),edt2.getText().toString());
                            parcial1 = Double.parseDouble(edt1.getText().toString());
                            oficial1 = Double.parseDouble(edt2.getText().toString());

                       //Verifica se as notas digitadas sao validas
                        try {
                            new NotaExceptions().notaException(parcial1,oficial1);

                                media1Bim =  calculaMedia(parcial1,oficial1);

                                //Elemento transportador para outra actvt
                                Bundle bndl = new Bundle();
                                bndl.putDouble("media",media1Bim);
                                Intent medias = new Intent(getApplicationContext(),ActMedias.class);
                                medias.putExtras(bndl);
                                startActivity(medias);
                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Nota(s) Inválida(s)",Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Nota(s) Inválida(s)",Toast.LENGTH_LONG).show();
                    }

                }else{
                        //recebe os dados fornecidos pelo usuario
                        EditText edt1 =  findViewById(R.id.notaParcial1);
                        EditText edt2 =  findViewById(R.id.notaOficial1);
                        EditText edt3 =  findViewById(R.id.notaParcial2);
                        EditText edt4 =  findViewById(R.id.notaOficial2);
                    try {
                        new NotaExceptions().notaException(edt1.getText().toString(),edt2.getText().toString());
                        new NotaExceptions().notaException(edt3.getText().toString(),edt4.getText().toString());
                        parcial1 = Double.parseDouble(edt1.getText().toString());
                        oficial1 = Double.parseDouble(edt2.getText().toString());
                        parcial2 = Double.parseDouble(edt3.getText().toString());
                        oficial2 = Double.parseDouble(edt4.getText().toString());
                        //Verifica se as notas digitadas sao validas
                        try {
                            new NotaExceptions().notaException(parcial1,oficial1);
                            new NotaExceptions().notaException(parcial2,oficial2);
                            media1Bim =  calculaMedia(parcial1,oficial1);
                            media2Bim =  calculaMedia(parcial2,oficial2);
                            //Calcula a media final do semestre;
                            mediaFinal = calculaMediaFinal(media1Bim,media2Bim);

                            Bundle bndl = new Bundle();
                            bndl.putDouble("media1",media1Bim);
                            bndl.putDouble("media2",media2Bim);
                            bndl.putDouble("mediaFinal",mediaFinal);

                            Intent medias = new Intent(getApplicationContext(),ActMediaFinal.class);
                            medias.putExtras(bndl);
                            startActivity(medias);

                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(),"Nota(s) Inválida(s)",Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(),"Nota(s) Inválida(s)",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });//fim do botao

    }//Fim do metodo Oncreate

    //Metodo que calcula a media do bimestre
    public double calculaMedia (double nota1 ,double nota2){
        return (nota1 * 0.3) + (nota2 * 0.7);
    }
    //Metodo que calcula a media final
    public double calculaMediaFinal(double media1, double media2){
         return (media1 * 0.4) + (media2 * 0.6);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_principal, menu);
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}//Fim da Classe