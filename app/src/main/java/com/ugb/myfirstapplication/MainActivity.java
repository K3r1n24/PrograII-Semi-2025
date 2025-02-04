package com.ugb.myfirstapplication;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btn;
    TextView tempVal;
    RadioGroup rgb;
    RadioButton opt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnCalcular);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempVal = findViewById(R.id.txtNum1);
                double num1 = Double.parseDouble(tempVal.getText().toString());

                tempVal = findViewById(R.id.txtNum2);
                double num2 = Double.parseDouble(tempVal.getText().toString());

                double respuesta = 0.0;

                opt = findViewById(R.id.optSuma);
                if (opt.isChecked()) {
                    respuesta = num1 + num2;
                }
                opt = findViewById(R.id.optResta);
                if (opt.isChecked()) {
                    respuesta = num1 - num2;
                }
                opt = findViewById(R.id.optMultiplicacion);
                if (opt.isChecked()) {
                    respuesta = num1 * num2;
                }
                opt = findViewById(R.id.optDivision);
                if (opt.isChecked()) {
                    respuesta = num1 / num2;
                }
                opt = findViewById(R.id.optExponente);
                if (opt.isChecked()) {
                    respuesta = Math.pow(num1, num2);

                }
                opt = findViewById(R.id.optPorcentaje);
                if (opt.isChecked()) {
                    respuesta = (num1 * num2) / 100;
                }
                opt = findViewById(R.id.optRaiz);
                if (opt.isChecked()) {
                    respuesta = num1 >= 0 ? Math.sqrt(num1) : Double.NaN;
                }
                opt = findViewById(R.id.optFactorial);
                if (opt.isChecked()) {
                    if (num1 < 0 || num1 != (int) num1) {
                        tempVal = findViewById(R.id.lblRespuesta);
                        tempVal.setText("Error: Factorial solo para enteros positivos.");
                        return;
                    }
                    respuesta = calcularFactorial((int) num1);
                }

                tempVal = findViewById(R.id.lblRespuesta);
                tempVal.setText("Respuesta: " + respuesta);
            }
        });
    }

    private long calcularFactorial(int num) {
        long factorial = 1;
        for (int i = 1; i <= num; i++) {
            factorial *= i;
        }
        return factorial;
    }
}



