package com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.javierhuinocana.grupo03_cibertec.R;
import com.example.javierhuinocana.grupo03_cibertec.dao.StockDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

import java.util.ArrayList;

/**
 * Created by JMartinez on 16/09/2015.
 */
public class RVStockUsuarioAdapter extends RecyclerView.Adapter<RVStockUsuarioAdapter.RVStockUsuarioAdapterViewHolder> {
    private ArrayList<StockMaterial> mLstStockMaterial,mLstStockMaterialFilter;
    private RVStockUsuarioAdapterCallBack mRVStockUsuarioAdapterCallBack;

    public interface RVStockUsuarioAdapterCallBack {
        void onStockClick(StockMaterial stockMaterial, int position);
    }

    public RVStockUsuarioAdapter(RVStockUsuarioAdapterCallBack mRVStockUsuarioAdapterCallBack) {
        this.mRVStockUsuarioAdapterCallBack = mRVStockUsuarioAdapterCallBack;
        mLstStockMaterialFilter = new ArrayList<>();
        mLstStockMaterial = new ArrayList<>();
        mLstStockMaterial.addAll(new StockDAO().lstStockMaterial());
        mLstStockMaterialFilter.addAll(mLstStockMaterial);
    }

    @Override
    public RVStockUsuarioAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RVStockUsuarioAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_stock,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(RVStockUsuarioAdapterViewHolder rvStockUsuarioAdapterViewHolder, int i) {
        StockMaterial stockMaterial = mLstStockMaterialFilter.get(i);
        rvStockUsuarioAdapterViewHolder.itemView.setTag(i);
        rvStockUsuarioAdapterViewHolder.itemView.setOnClickListener(itemViewOnClickListener);
        rvStockUsuarioAdapterViewHolder.tvDescripcion.setText(stockMaterial.getDescripcion());
        rvStockUsuarioAdapterViewHolder.tvStock.setText(String.valueOf(stockMaterial.getStock()));
    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mRVStockUsuarioAdapterCallBack != null){
                mRVStockUsuarioAdapterCallBack.onStockClick(mLstStockMaterialFilter.get((int) view.getTag()),(int) view.getTag());
            }
        }
    };

    @Override
    public int getItemCount() {
        return mLstStockMaterialFilter.size();
    }

    static class RVStockUsuarioAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView tvDescripcion, tvStock;
        public RVStockUsuarioAdapterViewHolder(View itemView) {
            super(itemView);
            tvDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion);
            tvStock = (TextView) itemView.findViewById(R.id.txtStock);
        }
    }
}
