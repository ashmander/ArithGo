package com.example.arithgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arithgo.app.ArithGoApp;
import com.example.arithgo.model.data.CRUDPoints;

public class Challenge extends AppCompatActivity {

    private TextView operandoATv;
    private TextView operandoBTv;
    private TextView operadorTv;
    private EditText resultEt;
    private Button verifyBtn, exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.operation);
        operadorTv = findViewById(R.id.operador_tv);
        String op = generateOperator();
        operadorTv.setText(op);
        operandoATv = findViewById(R.id.operando_a_tv);
        int a = (int)(Math.random()*100)+1;
        operandoATv.setText(a+"");
        operandoBTv = findViewById(R.id.operando_b_tv);
        int b = (int)(Math.random()*100)+1;
        operandoBTv.setText(b+"");
        final int result = getResult(op,a,b);
        resultEt = findViewById(R.id.result_et);
        verifyBtn = findViewById(R.id.verify_btn);
        verifyBtn.setEnabled(true);
        verifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resultEt.getText().toString().equals("")) {
                    Toast answare = Toast.makeText(ArithGoApp.getContext(),"Responde la pregunta",Toast.LENGTH_SHORT);
                    answare.show();
                } else if(resultEt.getText().toString().equals(""+result)){
                    CRUDPoints.updatePoints();
                    Toast answare = Toast.makeText(ArithGoApp.getContext(),"Ganaste 1 punto",Toast.LENGTH_LONG);
                    answare.show();
                    Intent intent = new Intent(getApplicationContext(),Mapa.class);
                    startActivity(intent);
                } else {
                    CRUDPoints.substractPoint();
                    Toast answare = Toast.makeText(ArithGoApp.getContext(),"Perdiste 1 punto",Toast.LENGTH_LONG);
                    answare.show();
                    verifyBtn.setText("v");
                    Intent intent = new Intent(getApplicationContext(),Mapa.class);
                    startActivity(intent);
                }
            }
        });
        exit = findViewById(R.id.exit_challenge_btn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Challenge.super.onBackPressed();
            }
        });
    }

    public String generateOperator() {
        String operator = "";
        int op = (int)(Math.random()*4)+1;
        switch (op) {
            case 1:
                operator="-";
                break;
            case 2:
                operator="+";
                break;
            case 3:
                operator="*";
                break;
            case 4:
                operator="/";
                break;
                default:
                    generateOperator();
        }
        return operator;
    }

    public int getResult(String op, int a, int b) {
        int result = 0;
        switch (op) {
            case "-":
                result = a - b;
                break;
            case "+":
                result = a + b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return result;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
