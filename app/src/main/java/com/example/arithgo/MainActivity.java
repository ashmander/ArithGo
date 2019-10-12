package com.example.arithgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.arithgo.model.data.CRUDPoints;

public class MainActivity extends AppCompatActivity {

    private Button iniciar, info, exit;
    private CheckBox lapicero, cuaderno, libreta, camiseta, saco;
    private ProgressBar pointsProgress;
    private TextView mainPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CRUDPoints.insertPoint();
        iniciar = findViewById(R.id.inicar_btn);
        iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Mapa.class);
                startActivity(i);
            }
        });
        info = findViewById(R.id.info_btn);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),Info.class);
                startActivity(i);
            }
        });
        exit = findViewById(R.id.exit_app);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        lapicero = findViewById(R.id.lapicero_cb);
        cuaderno = findViewById(R.id.cuaderno_cb);
        libreta = findViewById(R.id.libreta_cb);
        camiseta = findViewById(R.id.camiseta_cb);
        saco = findViewById(R.id.saco_cb);
        pointsProgress = findViewById(R.id.points_progress);
        mainPoints = findViewById(R.id.points_main_tv);
        int points = CRUDPoints.getPoints();
        pointsProgress.setProgress(points);
        mainPoints.setText(""+points+" puntos");
        if(points>=100) {
            lapicero.setChecked(true);
            cuaderno.setChecked(true);
            libreta.setChecked(true);
            camiseta.setChecked(true);
            saco.setChecked(true);
        } else if(points>=80) {
            lapicero.setChecked(true);
            cuaderno.setChecked(true);
            libreta.setChecked(true);
            camiseta.setChecked(true);
        } else if(points>=40) {
            lapicero.setChecked(true);
            cuaderno.setChecked(true);
            libreta.setChecked(true);
        } else if(points>=30) {
            lapicero.setChecked(true);
            cuaderno.setChecked(true);
        } else if(points>=20) {
            lapicero.setChecked(true);
        }
    }

    @Override
    public void onBackPressed() {

    }


}
