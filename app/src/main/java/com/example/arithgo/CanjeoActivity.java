package com.example.arithgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arithgo.model.data.CRUDPoints;

public class CanjeoActivity extends AppCompatActivity {

    private TextView myPointsTv;
    private ListView productList;
    private ProductAdapter productAdapter;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canjeo_activity);
        myPointsTv = findViewById(R.id.my_points_tv);
        Integer points = CRUDPoints.getPoints();
        myPointsTv.setText(""+points);
        productAdapter = new ProductAdapter(this);
        productList = findViewById(R.id.product_list);
        productList.setAdapter(productAdapter);
        exit = findViewById(R.id.exit_canjeo_btn);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CanjeoActivity.super.onBackPressed();
            }
        });
    }

    public void refreshInformation() {
        Integer points = CRUDPoints.getPoints();
        myPointsTv.setText(""+points);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
