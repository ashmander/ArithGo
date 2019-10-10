package com.example.arithgo;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.canjeo_activity);
        myPointsTv = findViewById(R.id.my_points_tv);
        int points = CRUDPoints.getPoints();
        myPointsTv.setText(points);
        productAdapter = new ProductAdapter();
        productList = findViewById(R.id.product_list);
        productList.setAdapter(productAdapter);

    }
}
