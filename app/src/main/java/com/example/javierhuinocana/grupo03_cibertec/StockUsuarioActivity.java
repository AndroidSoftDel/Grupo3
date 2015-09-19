package com.example.javierhuinocana.grupo03_cibertec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.RVStockUsuarioAdapter;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

/**
 * Created by JMartinez on 16/09/2015.
 */
public class StockUsuarioActivity extends AppCompatActivity implements RVStockUsuarioAdapter.RVStockUsuarioAdapterCallBack {

    private RecyclerView rvStock;
    private RVStockUsuarioAdapter rvStockUsuarioAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_usuario);

        rvStock = (RecyclerView) findViewById(R.id.rvStockUsuario);
        rvStock.setHasFixedSize(true);
        rvStock.setLayoutManager(new LinearLayoutManager(StockUsuarioActivity.this));

        rvStockUsuarioAdapter = new RVStockUsuarioAdapter(StockUsuarioActivity.this);
        rvStock.setAdapter(rvStockUsuarioAdapter);
    }


    @Override
       public void onStockClick(StockMaterial stockMaterial, int position) {

    }
}
