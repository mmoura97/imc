package br.com.aula.text;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


    public void calculaImc(View view) {
        TextInputEditText campoNome = findViewById(R.id.textinputNome);
        TextInputEditText campoSexo = findViewById(R.id.textInputSexo);
        TextInputEditText campoPeso = findViewById(R.id.textInputPeso);
        TextInputEditText campoAltura = findViewById(R.id.textinputAltura);

        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);

        // Cria as variáveis e converte para string
        String nome = campoNome.getText().toString().trim();
        String sexo = campoSexo.getText().toString().trim();
        String pesoStr = campoPeso.getText().toString().trim();
        String alturaStr = campoAltura.getText().toString().trim();

        // Verifica se todos os campos foram preenchidos
        if (nome.isEmpty() || sexo.isEmpty() || pesoStr.isEmpty() || alturaStr.isEmpty()) {
            resultado1.setText("ERRO");
            resultado2.setText("ENTRADA INVALIDA");
            return;
        }

        try {
            // Converte os dados de peso e altura
            double peso = Double.parseDouble(pesoStr);
            double altura = Double.parseDouble(alturaStr);
            double imc = peso / (altura * altura);

            // Formata o IMC para duas casas decimais
            DecimalFormat df = new DecimalFormat("##.##");
            String imcFormatado = df.format(imc);
            resultado1.setText(imcFormatado);

            // Classificação do IMC
            String classificacao;
            if (sexo.equalsIgnoreCase("Masculino") || sexo.equalsIgnoreCase("Feminino")) {
                if (imc < 18.5) {
                    classificacao = "Baixo peso";
                } else if (imc >= 18.5 && imc < 24.9) {
                    classificacao = "Peso normal";
                } else if (imc >= 25 && imc < 29.9) {
                    classificacao = "Sobrepeso";
                } else if (imc >= 30 && imc < 34.9) {
                    classificacao = "Obesidade grau I";
                } else if (imc >= 35 && imc < 39.9) {
                    classificacao = "Obesidade grau II";
                } else {
                    classificacao = "Obesidade extrema (grau III)";
                }
            } else {
                classificacao = "Sexo inválido";
            }

            resultado2.setText(classificacao);
        } catch (NumberFormatException e) {
            // Caso a conversão falhe, exibe mensagens de erro
            resultado1.setText("ERRO");
            resultado2.setText("ENTRADA INVALIDA");
        }
    }





    public void limpaDados(View view){
        TextInputEditText campoNome = findViewById(R.id.textinputNome);
        TextInputEditText campoPeso = findViewById(R.id.textInputPeso);
        TextInputEditText campoSexo = findViewById(R.id.textInputSexo);
        TextInputEditText campoAltura = findViewById(R.id.textinputAltura);
        TextView resultado1 = findViewById(R.id.textResultado1);
        TextView resultado2 = findViewById(R.id.textResultado2);


        campoNome.setText("");
        campoPeso.setText("");
        campoSexo.setText("");
        campoAltura.setText("");
        resultado1.setText("");
        resultado2.setText("");
        }

    }

