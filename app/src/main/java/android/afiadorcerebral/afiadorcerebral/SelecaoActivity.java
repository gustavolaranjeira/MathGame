package android.afiadorcerebral.afiadorcerebral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SelecaoActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3;
    TextView tvx, tvx2;

    //salva configurações selecionadas
    private SharedPreferences preferences; //nvvvvvvvvvvvvvvvvvvvvv
    private SharedPreferences.Editor editor; //nvvvvvvvvvvvvvvvvvvvvvv
    int soma2, sub2, div2, mult2, aviso2;
    int aviso3=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecao);

        tv1 = findViewById(R.id.textView1);
        tv2 = findViewById(R.id.textView2);
        tv3 = findViewById(R.id.textView3);

        tvx=findViewById(R.id.textViewTV);
        tvx2=findViewById(R.id.textViewTV2);

        //salva configurações na memoria
        preferences = getSharedPreferences("variaveisSelecaoActivity", MODE_PRIVATE);//1nvvvvvvvvvvvvvvvvvvvvvvvvvv
        editor = preferences.edit();//11nvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        soma2 = preferences.getInt("somaMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        sub2 = preferences.getInt("subMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        div2 = preferences.getInt("divMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        mult2 = preferences.getInt("multMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        //recebe as infos da configuraçõesActivity
        recebeVariaveisConfiguracoesActivity();

        //clique para ir para game
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(SelecaoActivity.this, GameActivity.class);
                myIntent.putExtra("somaEnvio", soma2);
                myIntent.putExtra("subEnvio", sub2);
                myIntent.putExtra("divEnvio", div2);
                myIntent.putExtra("multEnvio", mult2);
                myIntent.putExtra("avisoEnvio", aviso3);
                startActivity(myIntent);
            }
        });

        //clique para ir para congifurações
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SelecaoActivity.this, ConfiguracoesActivity.class);
                startActivity(it);
            }
        });

        //clique para ir para historico
        tv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SelecaoActivity.this, HistoricoActivity.class);
                startActivity(it);
            }
        });
        
    }


    //recebe oque foi enviado de outra activity
    private void recebeVariaveisConfiguracoesActivity(){
        //busca e com o if verifica se foi enviado informação
        Intent myIntent = getIntent();
        aviso2 = myIntent.getIntExtra("avisoEnvio", 0);
        if (aviso2==1){
            soma2 = myIntent.getIntExtra("somaEnvio", 0);
            sub2 = myIntent.getIntExtra("subEnvio", 0);
            div2 = myIntent.getIntExtra("divEnvio", 0);
            mult2 = myIntent.getIntExtra("multEnvio", 0);
            aviso2=0;
        }

        //facilita leitura do que veio do intent
        String numberAsString11 = String.valueOf(soma2);
        String numberAsString22 = String.valueOf(sub2);
        String numberAsString33 = String.valueOf(div2);
        String numberAsString44 = String.valueOf(mult2);
        tvx2.setText(numberAsString11 + numberAsString22 + numberAsString33 + numberAsString44);

        //envia para salvar na memoria
        editor.putInt("somaMemoriaR", soma2); //nvvvvvvvvvvvvvvvvvvvvv
        editor.putInt("subMemoriaR", sub2); //nvvvvvvvvvvvvvvvvvvvvv
        editor.putInt("divMemoriaR", div2); //nvvvvvvvvvvvvvvvvvvvvv
        editor.putInt("multMemoriaR", mult2); //nvvvvvvvvvvvvvvvvvvvvv
        editor.commit(); //nvvvvvvvvvvvvvvvvvvvvvvvvvv
    }

}