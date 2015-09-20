package com.example.javierhuinocana.grupo03_cibertec;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.RVListadoAdapter;
import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.RVStockMaterialAdapter;
import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

import java.util.ArrayList;

/**
 * Created by Javier Huiñocana on 11/09/2015.
 */
public class AddMaterialLiquidarActivity extends AppCompatActivity {
    private RecyclerView rvMaterial;
    private RVStockMaterialAdapter rvStockMaterialAdapter;
    Button btnAgregar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_material_liquidar);

        btnAgregar = (Button) findViewById(R.id.btnAgregarMaterial_AddMaterialLiquidar);

        btnAgregar.setOnClickListener(btnAgregarOnClickListener);

        rvMaterial = (RecyclerView) findViewById(R.id.rvAddMaterial);
        rvMaterial.setHasFixedSize(true);
        rvMaterial.setLayoutManager(new LinearLayoutManager(AddMaterialLiquidarActivity.this));

        rvStockMaterialAdapter = new RVStockMaterialAdapter();
        rvMaterial.setAdapter(rvStockMaterialAdapter);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(LiquidarOrdenActivity.KEY_MAT)) {
            ArrayList<StockMaterial> listaStock = getIntent().getParcelableArrayListExtra(LiquidarOrdenActivity.KEY_MAT);
            for (int i = 0; i < listaStock.size(); i++) {
                ArrayList<StockMaterial> Temp = rvStockMaterialAdapter.getListaActual();
                int pos = -1;
                for (int j = 0; j < Temp.size(); j++) {
                    if (listaStock.get(i).getIdMaterial() == Temp.get(j).getIdMaterial()) {
                        pos = j;
                        break;
                    }
                }
                if (pos != -1) {
                    Temp.remove(pos);
                }
            }

        }
    }

    View.OnClickListener btnAgregarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ArrayList<StockMaterial> lista = new ArrayList<StockMaterial>();
            for (int i = 0; i < rvMaterial.getAdapter().getItemCount(); i++) {
                StockMaterial material = rvStockMaterialAdapter.getMaterial(i);
                if (material.getCantidad() > 0) {
                    lista.add(material);
                }
            }
            //lista = rvStockMaterialAdapter.listaMaterialadd();
            Intent intent = new Intent();
            intent.putExtra(LiquidarOrdenActivity.KEY_ARG, lista);
            setResult(RESULT_OK, intent);
            finish();
        }
    };

}
