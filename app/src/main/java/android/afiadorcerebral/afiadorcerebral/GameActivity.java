package android.afiadorcerebral.afiadorcerebral;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {
    private TextView pergunta, resposta, textViewOp1, textViewOp2;

    //salva configurações selecionadas
    private SharedPreferences preferences; //nvvvvvvvvvvvvvvvvvvvvv
    private SharedPreferences.Editor editor; //nvvvvvvvvvvvvvvvvvvvvvv

    TextView tv, close2;

    String dados;
    String sinal;
    int resultadoV, resultadoF, numeroRandomico, numeroRandomico2,
            numeroRandomico3, numeroRandomico4, numeroRandomicoE,
            pontos=0;

    int soma2, sub2, div2, mult2, aviso3;

    String resultadoVV, resultadoFF, pontosR;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //salva configurações na memoria
        preferences = getSharedPreferences("variaveisGameActivity", MODE_PRIVATE);//1nvvvvvvvvvvvvvvvvvvvvvvvvvv
        editor = preferences.edit();//11nvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        pontos = preferences.getInt("pontoMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        soma2 = preferences.getInt("somaMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        sub2 = preferences.getInt("subMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        div2 = preferences.getInt("divMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
        mult2 = preferences.getInt("multMemoriaR", 0); //11nvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv

        tv=findViewById(R.id.tv);
        close2=findViewById(R.id.close2);

        Recebeinfo();

        pergunta = findViewById(R.id.textView_pergunta);
        resposta = findViewById(R.id.textView_resposta);
        textViewOp1 = findViewById(R.id.textViewOp1);
        textViewOp2 = findViewById(R.id.textViewOp2);

        logica();

        textViewOp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numeroRandomico4==1) {
                    pontos++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Boa!!!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    logica();
                    pontosR=String.valueOf(pontos);
                    resposta.setText(pontosR);
                } else {
                    pontos=0;
                    Toast toast = Toast.makeText(getApplicationContext(), "Errado!!!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    logica();
                    pontosR=String.valueOf(pontos);
                    resposta.setText(pontosR);
                }
            }
        });

        textViewOp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numeroRandomico4==0) {
                    pontos++;
                    Toast toast = Toast.makeText(getApplicationContext(), "Boa!!!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    logica();
                    pontosR=String.valueOf(pontos);
                    resposta.setText(pontosR);
                } else {
                    pontos=0;
                    Toast toast = Toast.makeText(getApplicationContext(), "Errado!!!!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    pontosR=String.valueOf(pontos);
                    resposta.setText(pontosR);
                }
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(GameActivity.this, SelecaoActivity.class);
                startActivity(myIntent);
            }
        });
    }
    private void Recebeinfo(){
            //busca e com o if verifica se foi enviado informação
            Intent myIntent = getIntent();
            aviso3 = myIntent.getIntExtra("avisoEnvio", 0);
            if (aviso3==1){
                soma2 = myIntent.getIntExtra("somaEnvio", 0);
                sub2 = myIntent.getIntExtra("subEnvio", 0);
                div2 = myIntent.getIntExtra("divEnvio", 0);
                mult2 = myIntent.getIntExtra("multEnvio", 0);
                aviso3=0;
            }
            //facilita leitura do que veio do intent
            String numberAsString11 = String.valueOf(soma2);
            String numberAsString22 = String.valueOf(sub2);
            String numberAsString33 = String.valueOf(div2);
            String numberAsString44 = String.valueOf(mult2);
            tv.setText(numberAsString11 + numberAsString22 + numberAsString33 + numberAsString44);

            //envia para salvar na memoria
            editor.putInt("somaMemoriaR", soma2); //nvvvvvvvvvvvvvvvvvvvvv
            editor.putInt("subMemoriaR", sub2); //nvvvvvvvvvvvvvvvvvvvvv
            editor.putInt("divMemoriaR", div2); //nvvvvvvvvvvvvvvvvvvvvv
            editor.putInt("multMemoriaR", mult2); //nvvvvvvvvvvvvvvvvvvvvv
            editor.commit(); //nvvvvvvvvvvvvvvvvvvvvvvvvvv
    }

    private void randomGerador() {
        Random randomico = new Random();
        numeroRandomico = randomico.nextInt(5);
        Random randomico2 = new Random();
        numeroRandomico2 = randomico2.nextInt(5-1)+1;
        Random randomicoE = new Random();
        numeroRandomicoE = randomicoE.nextInt(8-6)+6;
        //Random randomico3 = new Random();
        //numeroRandomico3 = randomico3.nextInt(3);
        Random randomico4 = new Random();
        numeroRandomico4 = randomico4.nextInt(2);
    }

    private void logica(){

        randomGerador();

        //teste incio
        if (soma2==0&sub2==0&mult2==0&div2==0){
            //Apresentar mensagem de erro
        }
        if (soma2==0&sub2==0&mult2==0&div2==1){

            Random randomico = new Random();
            numeroRandomico = randomico.nextInt(10-1)+1;
            Random randomico2 = new Random();
            numeroRandomico2 = randomico2.nextInt(50-1)+1;
            Random randomicoE = new Random();
            numeroRandomicoE = randomicoE.nextInt(80-6)+6;

            while (numeroRandomico2%numeroRandomico!=0){
                numeroRandomico = randomico.nextInt(10-1)+1;
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
            }
            if (numeroRandomico2%numeroRandomico==0){
                resultadoV = numeroRandomico2 / numeroRandomico;
                resultadoF = numeroRandomico2 / numeroRandomicoE;
                sinal=" / ";
            }



        }
        if (soma2==0&sub2==0&mult2==1&div2==0){
            resultadoV = numeroRandomico2 * numeroRandomico;
            resultadoF = numeroRandomico2 * numeroRandomicoE;
            sinal=" x ";
        }
        if (soma2==0&sub2==0&mult2==1&div2==1){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(2);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 * numeroRandomico;
                resultadoF = numeroRandomico2 * numeroRandomicoE;
                sinal=" x ";
            }
            if (numeroRandomico3 == 1) {
                Random randomico = new Random();
                numeroRandomico = randomico.nextInt(10-1)+1;
                Random randomico2 = new Random();
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
                Random randomicoE = new Random();
                numeroRandomicoE = randomicoE.nextInt(80-6)+6;

                while (numeroRandomico2%numeroRandomico!=0){
                    numeroRandomico = randomico.nextInt(10-1)+1;
                    numeroRandomico2 = randomico2.nextInt(50-1)+1;
                }
                if (numeroRandomico2%numeroRandomico==0){
                    resultadoV = numeroRandomico2 / numeroRandomico;
                    resultadoF = numeroRandomico2 / numeroRandomicoE;
                    sinal=" / ";
                }
            }
        }
        if (soma2==0&sub2==1&mult2==0&div2==0){
            resultadoV = numeroRandomico2 - numeroRandomico;
            resultadoF = numeroRandomico2 - numeroRandomicoE;
            sinal=" - ";
        }
        if (soma2==0&sub2==1&mult2==0&div2==1){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(2);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 - numeroRandomico;
                resultadoF = numeroRandomico2 - numeroRandomicoE;
                sinal=" - ";
            }
            if (numeroRandomico3 == 1) {
                Random randomico = new Random();
                numeroRandomico = randomico.nextInt(10-1)+1;
                Random randomico2 = new Random();
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
                Random randomicoE = new Random();
                numeroRandomicoE = randomicoE.nextInt(80-6)+6;

                while (numeroRandomico2%numeroRandomico!=0){
                    numeroRandomico = randomico.nextInt(10-1)+1;
                    numeroRandomico2 = randomico2.nextInt(50-1)+1;
                }
                if (numeroRandomico2%numeroRandomico==0){
                    resultadoV = numeroRandomico2 / numeroRandomico;
                    resultadoF = numeroRandomico2 / numeroRandomicoE;
                    sinal=" / ";
                }
            }
        }
        if (soma2==0&sub2==1&mult2==1&div2==0){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(2);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 - numeroRandomico;
                resultadoF = numeroRandomico2 - numeroRandomicoE;
                sinal=" - ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 * numeroRandomico;
                resultadoF = numeroRandomico2 * numeroRandomicoE;
                sinal=" x ";
            }

        }
        if (soma2==0&sub2==1&mult2==1&div2==1){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(3);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 - numeroRandomico;
                resultadoF = numeroRandomico2 - numeroRandomicoE;
                sinal=" - ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 * numeroRandomico;
                resultadoF = numeroRandomico2 * numeroRandomicoE;
                sinal=" x ";
            }
            if (numeroRandomico3 == 2) {
                Random randomico = new Random();
                numeroRandomico = randomico.nextInt(10-1)+1;
                Random randomico2 = new Random();
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
                Random randomicoE = new Random();
                numeroRandomicoE = randomicoE.nextInt(80-6)+6;

                while (numeroRandomico2%numeroRandomico!=0){
                    numeroRandomico = randomico.nextInt(10-1)+1;
                    numeroRandomico2 = randomico2.nextInt(50-1)+1;
                }
                if (numeroRandomico2%numeroRandomico==0){
                    resultadoV = numeroRandomico2 / numeroRandomico;
                    resultadoF = numeroRandomico2 / numeroRandomicoE;
                    sinal=" / ";
                }
            }
        }
        if (soma2==1&sub2==0&mult2==0&div2==0){
            resultadoV = numeroRandomico2 + numeroRandomico;
            resultadoF = numeroRandomico2 + numeroRandomicoE;
            sinal=" + ";
        }
        if (soma2==1&sub2==0&mult2==0&div2==1){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(2);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 + numeroRandomico;
                resultadoF = numeroRandomico2 + numeroRandomicoE;
                sinal=" + ";
            }
            if (numeroRandomico3 == 1) {
                Random randomico = new Random();
                numeroRandomico = randomico.nextInt(10-1)+1;
                Random randomico2 = new Random();
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
                Random randomicoE = new Random();
                numeroRandomicoE = randomicoE.nextInt(80-6)+6;

                while (numeroRandomico2%numeroRandomico!=0){
                    numeroRandomico = randomico.nextInt(10-1)+1;
                    numeroRandomico2 = randomico2.nextInt(50-1)+1;
                }
                if (numeroRandomico2%numeroRandomico==0){
                    resultadoV = numeroRandomico2 / numeroRandomico;
                    resultadoF = numeroRandomico2 / numeroRandomicoE;
                    sinal=" / ";
                }
            }
        }
        if (soma2==1&sub2==0&mult2==1&div2==0){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(2);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 + numeroRandomico;
                resultadoF = numeroRandomico2 + numeroRandomicoE;
                sinal=" + ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 * numeroRandomico;
                resultadoF = numeroRandomico2 * numeroRandomicoE;
                sinal=" x ";
            }
        }
        if (soma2==1&sub2==0&mult2==1&div2==1){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(3);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 + numeroRandomico;
                resultadoF = numeroRandomico2 + numeroRandomicoE;
                sinal=" + ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 * numeroRandomico;
                resultadoF = numeroRandomico2 * numeroRandomicoE;
                sinal=" x ";
            }
            if (numeroRandomico3 == 2) {
                Random randomico = new Random();
                numeroRandomico = randomico.nextInt(10-1)+1;
                Random randomico2 = new Random();
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
                Random randomicoE = new Random();
                numeroRandomicoE = randomicoE.nextInt(80-6)+6;

                while (numeroRandomico2%numeroRandomico!=0){
                    numeroRandomico = randomico.nextInt(10-1)+1;
                    numeroRandomico2 = randomico2.nextInt(50-1)+1;
                }
                if (numeroRandomico2%numeroRandomico==0){
                    resultadoV = numeroRandomico2 / numeroRandomico;
                    resultadoF = numeroRandomico2 / numeroRandomicoE;
                    sinal=" / ";
                }
            }
        }
        if (soma2==1&sub2==1&mult2==0&div2==0){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(2);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 + numeroRandomico;
                resultadoF = numeroRandomico2 + numeroRandomicoE;
                sinal=" + ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 - numeroRandomico;
                resultadoF = numeroRandomico2 - numeroRandomicoE;
                sinal=" - ";
            }
        }
        if (soma2==1&sub2==1&mult2==0&div2==1){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(3);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 + numeroRandomico;
                resultadoF = numeroRandomico2 + numeroRandomicoE;
                sinal=" + ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 - numeroRandomico;
                resultadoF = numeroRandomico2 - numeroRandomicoE;
                sinal=" - ";
            }
            if (numeroRandomico3 == 2) {
                Random randomico = new Random();
                numeroRandomico = randomico.nextInt(10-1)+1;
                Random randomico2 = new Random();
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
                Random randomicoE = new Random();
                numeroRandomicoE = randomicoE.nextInt(80-6)+6;

                while (numeroRandomico2%numeroRandomico!=0){
                    numeroRandomico = randomico.nextInt(10-1)+1;
                    numeroRandomico2 = randomico2.nextInt(50-1)+1;
                }
                if (numeroRandomico2%numeroRandomico==0){
                    resultadoV = numeroRandomico2 / numeroRandomico;
                    resultadoF = numeroRandomico2 / numeroRandomicoE;
                    sinal=" / ";
                }
            }
        }
        if (soma2==1&sub2==1&mult2==1&div2==0){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(3);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 + numeroRandomico;
                resultadoF = numeroRandomico2 + numeroRandomicoE;
                sinal=" + ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 - numeroRandomico;
                resultadoF = numeroRandomico2 - numeroRandomicoE;
                sinal=" - ";
            }
            if (numeroRandomico3 == 2) {
                resultadoV = numeroRandomico2 * numeroRandomico;
                resultadoF = numeroRandomico2 * numeroRandomicoE;
                sinal=" x ";
            }
        }
        if (soma2==1&sub2==1&mult2==1&div2==1){
            Random randomico3 = new Random();
            numeroRandomico3 = randomico3.nextInt(4);
            if (numeroRandomico3 == 0) {
                resultadoV = numeroRandomico2 + numeroRandomico;
                resultadoF = numeroRandomico2 + numeroRandomicoE;
                sinal=" + ";
            }
            if (numeroRandomico3 == 1) {
                resultadoV = numeroRandomico2 - numeroRandomico;
                resultadoF = numeroRandomico2 - numeroRandomicoE;
                sinal=" - ";
            }
            if (numeroRandomico3 == 2) {
                resultadoV = numeroRandomico2 * numeroRandomico;
                resultadoF = numeroRandomico2 * numeroRandomicoE;
                sinal=" x ";
            }
            if (numeroRandomico3 == 3) {
                Random randomico = new Random();
                numeroRandomico = randomico.nextInt(10-1)+1;
                Random randomico2 = new Random();
                numeroRandomico2 = randomico2.nextInt(50-1)+1;
                Random randomicoE = new Random();
                numeroRandomicoE = randomicoE.nextInt(80-6)+6;

                while (numeroRandomico2%numeroRandomico!=0){
                    numeroRandomico = randomico.nextInt(10-1)+1;
                    numeroRandomico2 = randomico2.nextInt(50-1)+1;
                }
                if (numeroRandomico2%numeroRandomico==0){
                    resultadoV = numeroRandomico2 / numeroRandomico;
                    resultadoF = numeroRandomico2 / numeroRandomicoE;
                    sinal=" / ";
                }
            }
        }
        //teste fim

        if (soma2==0&sub2==0&mult2==0&div2==0){
            dados = "Selecione ao menos uma operação na aba configurações.";
            pergunta.setText(dados);
            resultadoVV="N/A";
            resultadoFF="N/A";
        } else {
            dados = numeroRandomico2 + sinal + numeroRandomico;
            pergunta.setText(dados);
            resultadoVV=String.valueOf(resultadoV);
            resultadoFF=String.valueOf(resultadoF);
        }



        if (numeroRandomico4==1) {
            textViewOp1.setText(resultadoVV);
            textViewOp2.setText(resultadoFF);
        }
        if (numeroRandomico4==0) {
            textViewOp1.setText(resultadoFF);
            textViewOp2.setText(resultadoVV);
        }
        pontosR=String.valueOf(pontos);
        resposta.setText(pontosR);
        editor.putInt("pontoMemoriaR", pontos); //nvvvvvvvvvvvvvvvvvvvvv
        editor.commit(); //nvvvvvvvvvvvvvvvvvvvvvvvvvv
    }


}