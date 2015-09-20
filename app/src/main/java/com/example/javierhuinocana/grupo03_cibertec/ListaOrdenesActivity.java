package com.example.javierhuinocana.grupo03_cibertec;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import android.support.v7.widget.RecyclerView;

import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.DrawerItemCustomAdapter;
import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.RVListadoAdapter;
import com.example.javierhuinocana.grupo03_cibertec.adap_spiner.SpinerAdapter;
import com.example.javierhuinocana.grupo03_cibertec.dao.DataBaseHelper;
import com.example.javierhuinocana.grupo03_cibertec.dao.ListadoDAO;
import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.example.javierhuinocana.grupo03_cibertec.entities.ObjectDrawerItem;

import java.util.ArrayList;

/**
 * Created by Javier Huiñocana on 07/09/2015.
 */
public class ListaOrdenesActivity extends AppCompatActivity implements RVListadoAdapter.RVListadoAdapterCallBack {
    public static final int estadoOrdenPendiente = 0;
    public static final int estadoOrdenLiquidada = 1;
    public static final int estadoOrdenRechazada = 10;
    public final int Code_Respuesta = 2;

    Spinner cboFiltrar;
    private SpinerAdapter SpinerAdaptador;
    private RecyclerView rvPrincipal;
    private RVListadoAdapter rvListadoAdapter;

    private DrawerLayout dlmenu;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    MenuItem menuVerMapa;

    private String[] mNavigationDrawerItemTitles;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    public final static String ARG_ORDEN = "orden", ARG_POSITION = "position";
    private final static int REQUEST_CODE_EDITAR = 2;
    private ArrayList<ListaOrdenes> ListaArray_Pendientes, ListaArray_Liquidadas, ListaArray_Rechazadas;

    private DataBaseHelper dataBaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_ordenes);

        cboFiltrar = (Spinner) findViewById(R.id.cboFiltrar);

        /*CREAMOS LOS ITEM PARA EL SPINER*/
        ArrayList<String> ArrayFiltro = new ArrayList<>();

        ArrayFiltro.add("Pendientes");
        ArrayFiltro.add("Liquidadas");
        ArrayFiltro.add("Rechazadas");

        /*ASOCIAMOS EL ADAPTADOR AL SPINER*/
        SpinerAdaptador = new SpinerAdapter(ListaOrdenesActivity.this, ArrayFiltro);
        cboFiltrar.setAdapter(SpinerAdaptador);


        rvPrincipal = (RecyclerView) findViewById(R.id.rvPrincipal);
        rvPrincipal.setHasFixedSize(true);
        rvPrincipal.setLayoutManager(new LinearLayoutManager(ListaOrdenesActivity.this));

        cboFiltrar.setOnItemSelectedListener(cboFiltrarOnItemSelectedListener);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[4];

        SharedPreferences preferences = getSharedPreferences("Usuario", Context.MODE_PRIVATE);


        drawerItem[0] = new ObjectDrawerItem(R.drawable.ic_action_ai_back, preferences.getString("nombreUsuario", "").toString());
        drawerItem[1] = new ObjectDrawerItem(R.drawable.ic_action_ai_settings, "Cambiar Contraseña");
        drawerItem[2] = new ObjectDrawerItem(R.drawable.ic_action_ai_storage, "Ver Stock");
        drawerItem[3] = new ObjectDrawerItem(R.drawable.ic_action_ai_back, "Cerrar Sesion");


        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(ListaOrdenesActivity.this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(DrawerItemClickListener);

        /*PARA CONTROLAR EL MENU DESPLEGABLE*/
        mTitle = mDrawerTitle = getTitle();
        dlmenu = (DrawerLayout) findViewById(R.id.MenuDesplegable_ListaOrdenes);
        actionBarDrawerToggle = new ActionBarDrawerToggle(ListaOrdenesActivity.this, dlmenu, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                rvPrincipal.setEnabled(true);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                rvPrincipal.setEnabled(false);
            }
        };

        dlmenu.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        SelectOrdenesActual();
    }

    public void SelectOrdenesActual() {
        ArrayList<ListaOrdenes> arrayListTemporal;
        arrayListTemporal = new ArrayList<>();
        arrayListTemporal.addAll(new ListadoDAO().listOrdenes());

        ListaArray_Pendientes = new ArrayList<ListaOrdenes>();
        ListaArray_Liquidadas = new ArrayList<ListaOrdenes>();
        ListaArray_Rechazadas = new ArrayList<ListaOrdenes>();

        for (int i = 0; i < arrayListTemporal.size(); i++) {
            switch (arrayListTemporal.get(i).getEstado()) {
                case 0:
                    /*PENDIENTES*/
                    ListaArray_Pendientes.add(arrayListTemporal.get(i)); //, ListaArray_Liquidadas, ListaArray_Rechazadas;
                    break;
                case 1:
                    ListaArray_Liquidadas.add(arrayListTemporal.get(i)); //, , ListaArray_Rechazadas;

                    break;
                case 10:
                    ListaArray_Rechazadas.add(arrayListTemporal.get(i));
                    break;
            }
        }

        rvListadoAdapter = new RVListadoAdapter(ListaOrdenesActivity.this, ListaArray_Pendientes);
        rvPrincipal.setAdapter(rvListadoAdapter);
    }

    ListView.OnItemClickListener DrawerItemClickListener = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
            switch (position) {
                //nombre de usuario
                case 0:
                    break;
                //cambiar contraseña
                case 1:
                    intent = new Intent(ListaOrdenesActivity.this, CambioContrasenaActivity.class);
                    startActivity(intent);
                    break;
                //ver stock
                case 2:
                    intent = new Intent(ListaOrdenesActivity.this, StockUsuarioActivity.class);
                    startActivity(intent);
                    break;
                //cerrar sesion
                case 3:
                    new AlertDialog.Builder(ListaOrdenesActivity.this).setTitle("Cerrar Sesion").setMessage("¿Desea cerrar sesion?")
                            .setNegativeButton("Cancelar", alertCancelOnClickListener).setPositiveButton("Aceptar", alertAcceptOnClickListener)
                            .setCancelable(false).show();
                    break;
                default:
                    break;
            }
        }
    };

    /*VARIABLE PARA IR SUMANDO O DISMINUYENDO CUANTOAS ORDENES TIENEN CHECK*/
    private int ContadorCheck = 0;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menu.getItem(0).setVisible(false);
        menuVerMapa = menu.getItem(1);

        /*BLOQUEAMOS EL ACTIONBAR: VER MAPA*/
        menuVerMapa.setEnabled(false);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_VerMapa_Lista) {
            View controlTem;
            ArrayList<ListaOrdenes> lista = new ArrayList<ListaOrdenes>();

            for (int i = 0; i < rvPrincipal.getAdapter().getItemCount(); i++) {
                controlTem = rvPrincipal.getChildAt(i);
                if (((CheckBox) controlTem.findViewById(R.id.chkChequeado)).isChecked()) {
                    ListaOrdenes Orden = rvListadoAdapter.getOrdenes(i);
                    lista.add(Orden);
                }
            }
            //lista = rvListadoAdapter.listaChequeada();
            Intent intent = new Intent(ListaOrdenesActivity.this, Mapa_Ordenes.class);

            intent.putExtra(ARG_ORDEN, lista);
            startActivity(intent);
            return true;
        } else if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    AdapterView.OnItemSelectedListener cboFiltrarOnItemSelectedListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    /*PENDIENTES*/
                    rvListadoAdapter = new RVListadoAdapter(ListaOrdenesActivity.this, ListaArray_Pendientes);
                    rvPrincipal.setAdapter(rvListadoAdapter);

                    break;
                case 1:
                    /*LIQUIDADAS*/
                    rvListadoAdapter = new RVListadoAdapter(ListaOrdenesActivity.this, ListaArray_Liquidadas);
                    rvPrincipal.setAdapter(rvListadoAdapter);
                    break;
                case 2:
                    /*RECHAZADAS*/
                    rvListadoAdapter = new RVListadoAdapter(ListaOrdenesActivity.this, ListaArray_Rechazadas);
                    rvPrincipal.setAdapter(rvListadoAdapter);

                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    @Override
    public void onListadoClick(ListaOrdenes listaOrdenes, int position) {
        Intent intent = new Intent(ListaOrdenesActivity.this, DetalleOrdenesActivity.class);
        intent.putExtra(ARG_ORDEN, listaOrdenes);
        intent.putExtra(ARG_POSITION, position);
        startActivityForResult(intent,Code_Respuesta);
        //startActivityForResult(intent, REQUEST_CODE_EDITAR);
        //Toast.makeText(ListaOrdenesActivity.this, listaOrdenes.getOrden(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Code_Respuesta && resultCode == RESULT_OK) {
            SelectOrdenesActual();
        }

    }

    @Override
    public void onCheckChange(boolean isChecked) {
        if (isChecked) {
            ContadorCheck++;
            menuVerMapa.setIcon(R.drawable.ver_mapa);
            menuVerMapa.setEnabled(true);
        } else {
            ContadorCheck--;

            if (ContadorCheck <= 0)
                menuVerMapa.setIcon(R.drawable.ver_mapa_sin_color);
            menuVerMapa.setEnabled(false);
        }
    }

    DialogInterface.OnClickListener alertAcceptOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            SharedPreferences settings = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            Intent intent = new Intent(ListaOrdenesActivity.this, MainActivity.class);
                finish();

            startActivity(intent);
            dialogInterface.dismiss();
        }
    };

    DialogInterface.OnClickListener alertCancelOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            dialogInterface.dismiss();
        }
    };

}
