package com.example.arithgo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arithgo.app.ArithGoApp;
import com.example.arithgo.model.data.CRUDPoints;
import com.example.arithgo.model.driver.DBDriver;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private ArrayList<Product> products;
    private CanjeoActivity canjeoActivity;

    public ProductAdapter(CanjeoActivity cj) {
        products = new ArrayList<>();
        addProducts();
        canjeoActivity = cj;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row_product, null);
        TextView productTv = view.findViewById(R.id.row_product_tv);
        productTv.setText(products.get(position).getName());
        TextView pointsProductTv = view.findViewById(R.id.points_tv);
        pointsProductTv.setText(products.get(position).getValue()+" puntos");
        Button canjeoBtn = view.findViewById(R.id.canjeo_btn);

        canjeoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int points = CRUDPoints.getPoints();
                if(points>=products.get(position).getValue()) {
                    int p = points - products.get(position).getValue();
                    CRUDPoints.updatePoints(products.get(position).getValue());
                    canjeoActivity.refreshInformation();
                } else {
                    Toast morePoints = Toast.makeText(ArithGoApp.getContext(), "Debes tener mas puntos para poder canjear", Toast.LENGTH_LONG);
                    morePoints.show();
                }
            }
        });
        return view;
    }



    public void addProducts(){
        Product p1 = new Product("Lapicero Icesi",20);
        Product p2 = new Product("Cuaderno",30);
        Product p3 = new Product("Libreta Icesi",40);
        Product p4 = new Product("Camiseta Icesi",80);
        Product p5 = new Product("Saco Icesi",100);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
    }

}
