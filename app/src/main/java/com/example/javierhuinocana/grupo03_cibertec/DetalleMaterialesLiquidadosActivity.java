package com.example.javierhuinocana.grupo03_cibertec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.RVMaterialesLiquidarAdpater;
import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.RVStockMaterialAdapter;
import com.example.javierhuinocana.grupo03_cibertec.entities.OrdenMaterial;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

import java.util.ArrayList;

/**
 * Created by Javier Huiñocana on 21/09/2015.
 */
public class DetalleMaterialesLiquidadosActivity extends AppCompatActivity implements RVMaterialesLiquidarAdpater.RVListadoAdapterCallBack {

    private RVMaterialesLiquidarAdpater RVAdaptador;
    RecyclerView RVListaMaterialesAgregados;
    ArrayList<StockMaterial> listaStock;
    ArrayList<OrdenMaterial> ordenMaterials;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalle_materiales_liquidados);
        RVListaMaterialesAgregados = (RecyclerView) findViewById(R.id.rvDetalleMaterialesLiquidados);
        RVListaMaterialesAgregados.setLayoutManager(new LinearLayoutManager(DetalleMaterialesLiquidadosActivity.this));

        listaStock = new ArrayList<StockMaterial>();


        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(DetalleOrdenesActivity.KEY_ARG)) {
            ordenMaterials = getIntent().getParcelableArrayListExtra(DetalleOrdenesActivity.KEY_ARG);
            for (int i = 0; i < ordenMaterials.size(); i++) {
                StockMaterial stockMaterial = new StockMaterial();
                stockMaterial.setIdMaterial(ordenMaterials.get(i).getIdMaterial());
                stockMaterial.setDescripcion(ordenMaterials.get(i).getDescripcion());
                stockMaterial.setCantidad(ordenMaterials.get(i).getCantidad());
                stockMaterial.setStock(0);
                listaStock.add(stockMaterial);
            }
            RVAdaptador = new RVMaterialesLiquidarAdpater(DetalleMaterialesLiquidadosActivity.this, listaStock);
            RVListaMaterialesAgregados.setAdapter(RVAdaptador);
        }
    }

    @Override
    public void onListadoClick(StockMaterial SM, int position) {

    }
}
