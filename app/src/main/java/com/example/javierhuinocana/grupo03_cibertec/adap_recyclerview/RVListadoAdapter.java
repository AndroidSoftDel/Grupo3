package com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javierhuinocana.grupo03_cibertec.ListaOrdenesActivity;
import com.example.javierhuinocana.grupo03_cibertec.R;
import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.example.javierhuinocana.grupo03_cibertec.dao.ListadoDAO;

import java.util.ArrayList;

/**
 * Created by JMartinez on 08/09/2015.
 */
public class RVListadoAdapter extends RecyclerView.Adapter<RVListadoAdapter.RVListadoAdapterViewHolder> implements Filterable {

    private ArrayList<ListaOrdenes> mLstListaOrdenesFilter;
    private RVListadoAdapterCallBack mRVListadoAdapterCallBack;
    private ArrayList<ListaOrdenes> mListaChequeada;

    public interface RVListadoAdapterCallBack {
        void onListadoClick(ListaOrdenes listaOrdenes, int position);

        void onCheckChange(boolean isChecked);
    }

    public RVListadoAdapter(RVListadoAdapterCallBack mRVListadoAdapterCallBack, ArrayList<ListaOrdenes> miLista) {
        this.mRVListadoAdapterCallBack = mRVListadoAdapterCallBack;

        mListaChequeada = new ArrayList<>();
        mLstListaOrdenesFilter = new ArrayList<>();
        //mLstListaOrdenes = new ArrayList<>();
        //mLstListaOrdenes.addAll(new ListadoDAO().listOrdenes());
        mLstListaOrdenesFilter.addAll(miLista);
    }

    @Override
    public RVListadoAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RVListadoAdapterViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_orden, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(final RVListadoAdapterViewHolder rvListadoAdapterViewHolder, int i) {
        final ListaOrdenes listaOrdenes = mLstListaOrdenesFilter.get(i);
        rvListadoAdapterViewHolder.itemView.setTag(i);
        //rvListadoAdapterViewHolder.chkChequeado.setTag(i);
        rvListadoAdapterViewHolder.itemView.setOnClickListener(itemViewOnClickListener);
        rvListadoAdapterViewHolder.tvOrden.setText(listaOrdenes.getOrden());
        rvListadoAdapterViewHolder.tvTelefono.setText(listaOrdenes.getTelefono());
        rvListadoAdapterViewHolder.tvNegocio.setText(listaOrdenes.getNegocio());
        rvListadoAdapterViewHolder.tvActividad.setText(listaOrdenes.getActividad());

        rvListadoAdapterViewHolder.chkChequeado.setChecked((listaOrdenes.getChequeado() == null) ? false : listaOrdenes.getChequeado());

        //listaOrdenes.setChequeado(false);

        rvListadoAdapterViewHolder.chkChequeado.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                listaOrdenes.setChequeado(isChecked);

                if (mRVListadoAdapterCallBack != null)
                    mRVListadoAdapterCallBack.onCheckChange(isChecked);
                //if(rvListadoAdapterViewHolder.chkChequeado.isChecked()){
                //    mListaChequeada.add(listaOrdenes);
                //}else {
                //    mListaChequeada.remove(listaOrdenes);
                //}
            }
        });

    }

    View.OnClickListener itemViewOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mRVListadoAdapterCallBack != null)
                mRVListadoAdapterCallBack.onListadoClick(mLstListaOrdenesFilter.get((int) view.getTag()), (int) view.getTag());
        }
    };

    public ListaOrdenes getOrdenes(int position) {
        return mLstListaOrdenesFilter.get(position);
    }

    public ArrayList<ListaOrdenes> listaChequeada() {
        return mListaChequeada;
    }

    public ArrayList<ListaOrdenes> getListaCompelta() {
        return mLstListaOrdenesFilter;
    }

    @Override
    public int getItemCount() {
        return mLstListaOrdenesFilter.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    static class RVListadoAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrden, tvTelefono, tvNegocio, tvActividad, tvIdOrden;
        CheckBox chkChequeado;

        public RVListadoAdapterViewHolder(View itemView) {
            super(itemView);

            tvOrden = (TextView) itemView.findViewById(R.id.tvOrden);
            tvTelefono = (TextView) itemView.findViewById(R.id.tvTelefono);
            tvNegocio = (TextView) itemView.findViewById(R.id.tvNegocio);
            tvActividad = (TextView) itemView.findViewById(R.id.tvActividad);
            chkChequeado = (CheckBox) itemView.findViewById(R.id.chkChequeado);
        }


    }
}
