package android.afiadorcerebral.afiadorcerebral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class ConfiguracoesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Switch switchSoma, switchSub, switchDiv, switchMult;

    TextView tv, close, voltar;

    Spinner spinner;

    String numSpinner;

    int soma, sub, div, mult, aviso=1;


    //salva configurações selecionadas
    private SharedPreferences preferences; //nvvvvvvvvvvvvvvvvvvvvv
    private SharedPreferences.Editor editor; //nvvvvvvvvvvvvvvvvvvvvvv

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);

        switchSoma = findViewById(R.id.switchSoma);
        switchSub = findViewById(R.id.switchSub);
        switchDiv = findViewById(R.id.switchDiv);
        switchMult = findViewById(R.id.switchMult);

        tv = findViewById(R.id.textView);
        close = findViewById(R.id.close);
        voltar = findViewById(R.id.textViewVoltar);
        spinner = findViewById(R.id.spinner1);

        //salva configurações na memoria
        preferences = getSharedPreferences("variaveisConfiguracoesActivity", MODE_PRIVATE);//1nvvvvvvvvvvvvvvvvvvvvvvvvvv
        editor = preferences.edit();//11nvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        soma = preferences.getInt("somaMemoria)", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        sub = preferences.getInt("subMemoria)", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        div = preferences.getInt("divMemoria)", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        mult = preferences.getInt("multMemoria)", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        Atualizar();

        if (soma == 1) {
            switchSoma.setChecked(true);
        }
        if (sub == 1) {
            switchSub.setChecked(true);
        }
        if (div == 1) {
            switchDiv.setChecked(true);
        }
        if (mult == 1) {
            switchMult.setChecked(true);
        }

        switchSoma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchSoma.isChecked())
                    soma = 1;
                else
                    soma = 0;

                //salva a configuração
                SalvaConfiguraçao();
                Atualizar();
            }
        });

        switchSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchSub.isChecked())
                    sub = 1;
                else
                    sub = 0;

                //salva a configuração
                SalvaConfiguraçao();
                Atualizar();
            }
        });

        switchDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchDiv.isChecked())
                    div = 1;
                else
                    div = 0;

                //salva a configuração
                SalvaConfiguraçao();
                Atualizar();
            }
        });

        switchMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (switchMult.isChecked())
                    mult = 1;
                else
                    mult = 0;

                //salva a configuração
                SalvaConfiguraçao();
                Atualizar();
            }
        });

        //clique para SelecaoActivity
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envioParaSelecaoActivity();
            }
        });

        //clique para GameActivity
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                envioParaGameActivity();
            }
        });

        //text spinner (one, two, three, four, five)
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.numbers, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }


    //clique do jogar, envia as infos para gameActivity
    private void envioParaGameActivity() {
        Intent myIntent = new Intent(ConfiguracoesActivity.this, GameActivity.class);
        myIntent.putExtra("somaEnvio", soma);
        myIntent.putExtra("subEnvio", sub);
        myIntent.putExtra("divEnvio", div);
        myIntent.putExtra("multEnvio", mult);
        myIntent.putExtra("avisoEnvio", aviso);
        startActivity(myIntent);
    }


    //clique do X, envia as infos para a selecaoActivity
    private void envioParaSelecaoActivity() {
        Intent myIntent = new Intent(ConfiguracoesActivity.this, SelecaoActivity.class);
        myIntent.putExtra("somaEnvio", soma);
        myIntent.putExtra("subEnvio", sub);
        myIntent.putExtra("divEnvio", div);
        myIntent.putExtra("multEnvio", mult);
        myIntent.putExtra("avisoEnvio", aviso);
        startActivity(myIntent);
    }

    private void Atualizar(){
        tv.setText(soma + "<-soma  " + sub + "<-sub  " + div + "<-div  " + mult + "<-mult  "+numSpinner);
    }

    private void SalvaConfiguraçao(){
        editor.putInt("somaMemoria)", soma); //nvvvvvvvvvvvvvvvvvvvvv
        editor.putInt("subMemoria)", sub); //nvvvvvvvvvvvvvvvvvvvvv
        editor.putInt("divMemoria)", div); //nvvvvvvvvvvvvvvvvvvvvv
        editor.putInt("multMemoria)", mult); //nvvvvvvvvvvvvvvvvvvvvv
        editor.commit(); //nvvvvvvvvvvvvvvvvvvvvvvvvvv
    }

    //função do spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
        numSpinner=text;
        Atualizar();
    }
    //função do spinner
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}




