package com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.javierhuinocana.grupo03_cibertec.R;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

import java.util.ArrayList;

/**
 * Created by Javier Huiñocana on 18/09/2015.
 */
public class RVMaterialesLiquidarAdpater extends RecyclerView.Adapter<RVMaterialesLiquidarAdpater.RVMaterialesViewHolder> {
    private ArrayList<StockMaterial> mLstStockMaterial;

    public RVMaterialesLiquidarAdpater(ArrayList<StockMaterial> stockMaterials) {
        //this.mLstStockMaterial = new ArrayList<StockMaterial>();
        this.mLstStockMaterial = stockMaterials;
    }

    @Override
    public RVMaterialesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RVMaterialesViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_material_liquidar, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(RVMaterialesViewHolder holder, int position) {
        //final StockMaterial stockMaterial = mLstStockMaterial.get(position);
        StockMaterial stockMaterial = mLstStockMaterial.get(position);
        holder.itemView.setTag(position);
        //rvStockMaterialAdapterViewHolder.itemView.setOnClickListener(itemViewOnClickListener);
        holder.lblDescripcion.setText(String.valueOf(stockMaterial.getDescripcion()));
        holder.lblCantidad.setText(String.valueOf(stockMaterial.getCantidad()));
    }

    @Override
    public int getItemCount() {
        return mLstStockMaterial.size();
    }

    public void addItem(StockMaterial sm) {
        mLstStockMaterial.add(sm);
        //this.notifyDataSetChanged();
    }


    static class RVMaterialesViewHolder extends RecyclerView.ViewHolder {
        TextView lblDescripcion, lblCantidad;

        public RVMaterialesViewHolder(View itemView) {
            super(itemView);
            lblDescripcion = (TextView) itemView.findViewById(R.id.lblDescripcion_Item_Material_Liquidar);
            lblCantidad = (TextView) itemView.findViewById(R.id.lblCantidad_Item_Material_Liquidar);
        }
    }
}
