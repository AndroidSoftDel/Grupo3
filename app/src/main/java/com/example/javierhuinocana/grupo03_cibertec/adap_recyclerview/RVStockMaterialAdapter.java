package com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javierhuinocana.grupo03_cibertec.AddMaterialLiquidarActivity;
import com.example.javierhuinocana.grupo03_cibertec.R;
import com.example.javierhuinocana.grupo03_cibertec.dao.StockDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

import java.util.ArrayList;

/**
 * Created by JMartinez on 12/09/2015.
 */
public class RVStockMaterialAdapter extends RecyclerView.Adapter<RVStockMaterialAdapter.RVStockMaterialAdapterViewHolder> {
    private ArrayList<StockMaterial> mLstStockMaterial;//, mLstStockMaterialFilter;
    //private ArrayList<StockMaterial> mLstStockMaterialadd;
    //private RVStockMaterialAdapterCallBack mRVStockMaterialAdapterCallBack;

//    public interface RVStockMaterialAdapterCallBack {
//        void onStockClick(StockMaterial stockMaterial, int position);
//    }

    public RVStockMaterialAdapter() {
        //this.mRVStockMaterialAdapterCallBack = mRVStockMaterialAdapterCallBack;
        //mLstStockMaterialFilter = new ArrayList<>();
        //mLstStockMaterialadd = new ArrayList<>();
        mLstStockMaterial = new ArrayList<>();
        mLstStockMaterial.addAll(new StockDAO().lstStockMaterial());
        //mLstStockMaterialFilter.addAll(mLstStockMaterial);
    }

    @Override
    public RVStockMaterialAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RVStockMaterialAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_material_add, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final RVStockMaterialAdapterViewHolder rvStockMaterialAdapterViewHolder, int i) {
        final StockMaterial stockMaterial = mLstStockMaterial.get(i);
        rvStockMaterialAdapterViewHolder.itemView.setTag(i);
        //rvStockMaterialAdapterViewHolder.itemView.setOnClickListener(itemViewOnClickListener);
        rvStockMaterialAdapterViewHolder.tvDescripcion.setText(stockMaterial.getDescripcion());
        rvStockMaterialAdapterViewHolder.tvStock.setText(String.valueOf(stockMaterial.getStock()));
        rvStockMaterialAdapterViewHolder.tvCantidadAdd.setText(String.valueOf(stockMaterial.getCantidad()));

        rvStockMaterialAdapterViewHolder.tvCantidadAdd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isNum(s.toString())) {
                    /*ES NUMERO*/
                    if (Integer.parseInt(s.toString()) > 0) {
                        if (stockMaterial.getStock() < Integer.parseInt(s.toString())) {
                            /*GRABAMOS EN EL CAMPO DE LA ENTIDAD LA CANTIDAD INGRESADA*/
                            stockMaterial.setCantidad(stockMaterial.getStock());
                            rvStockMaterialAdapterViewHolder.tvCantidadAdd.setText(String.valueOf(stockMaterial.getStock()));
                            /*ENVIAMOS EL CURSOR AL FINAL*/
                            ((EditText) rvStockMaterialAdapterViewHolder.tvCantidadAdd).setSelection(String.valueOf(stockMaterial.getStock()).length());
                        }else {
                            /*GRABAMOS EN EL CAMPO DE LA ENTIDAD LA CANTIDAD INGRESADA*/
                            stockMaterial.setCantidad(Integer.parseInt(s.toString()));
                        }
                    } else {
                        if (!s.toString().equals("0")) {
                            rvStockMaterialAdapterViewHolder.tvCantidadAdd.setText("0");
                        }
                        stockMaterial.setCantidad(Integer.parseInt("0"));
                    }
                } else {
                    stockMaterial.setCantidad(Integer.parseInt("0"));
                }
            }
        });
    }

    /*FUNCION QUE DETERMINA SI UN TEXTO ES NUMERO ENTERO*/
    private static boolean isNum(String strNum) {
        boolean ret = true;
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException e) {
            ret = false;
        }
        return ret;
    }

    /*
    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mRVStockMaterialAdapterCallBack != null) {
                mRVStockMaterialAdapterCallBack.onStockClick(mLstStockMaterial.get((int) view.getTag()), (int) view.getTag());
            }
        }
    };
    */

    public StockMaterial getMaterial(int position) {
        return mLstStockMaterial.get(position);
    }

 //   private ArrayList<StockMaterial> mLstStockMaterial;//, mLstStockMaterialFilter;
    public ArrayList<StockMaterial> getListaActual(){
        return mLstStockMaterial;
    }


    @Override
    public int getItemCount() {
        return mLstStockMaterial.size();
    }

    static class RVStockMaterialAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvDescripcion, tvStock;
        EditText tvCantidadAdd;

        public RVStockMaterialAdapterViewHolder(View itemView) {
            super(itemView);

            tvDescripcion = (TextView) itemView.findViewById(R.id.txtDescripcion_AddMat);
            tvStock = (TextView) itemView.findViewById(R.id.txtStock_AddMat);
            tvCantidadAdd = (EditText) itemView.findViewById(R.id.txtCantidad_AddMat);
        }
    }
}
