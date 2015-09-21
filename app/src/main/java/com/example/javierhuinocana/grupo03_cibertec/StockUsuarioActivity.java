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
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.DrawerItemCustomAdapter;
import com.example.javierhuinocana.grupo03_cibertec.adap_recyclerview.RVStockUsuarioAdapter;
import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.example.javierhuinocana.grupo03_cibertec.entities.ObjectDrawerItem;
import com.example.javierhuinocana.grupo03_cibertec.entities.StockMaterial;

import java.util.ArrayList;

/**
 * Created by JMartinez on 16/09/2015.
 */
public class StockUsuarioActivity extends AppCompatActivity implements RVStockUsuarioAdapter.RVStockUsuarioAdapterCallBack {

    private RecyclerView rvStock;
    private RVStockUsuarioAdapter rvStockUsuarioAdapter;
    private DrawerLayout dlmenustock;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private String[] mNavigationDrawerItemTitles;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_usuario);

        rvStock = (RecyclerView) findViewById(R.id.rvStockUsuario);
        rvStock.setHasFixedSize(true);
        rvStock.setLayoutManager(new LinearLayoutManager(StockUsuarioActivity.this));

        rvStockUsuarioAdapter = new RVStockUsuarioAdapter(StockUsuarioActivity.this);
        rvStock.setAdapter(rvStockUsuarioAdapter);

        ObjectDrawerItem[] drawerItem = new ObjectDrawerItem[2];

        drawerItem[0] = new ObjectDrawerItem(R.drawable.change_password_white, "Cambiar Contraseña");
        drawerItem[1] = new ObjectDrawerItem(R.drawable.close_white, "Cerrar Sesion");

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(StockUsuarioActivity.this, R.layout.listview_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(DrawerItemClickListener);

        dlmenustock = (DrawerLayout) findViewById(R.id.MenuDesplegable_StockUsuario);
        actionBarDrawerToggle = new ActionBarDrawerToggle(StockUsuarioActivity.this, dlmenustock, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                Toast.makeText(StockUsuarioActivity.this, "Closed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                Toast.makeText(StockUsuarioActivity.this, "Opened", Toast.LENGTH_SHORT).show();
            }
        };

        dlmenustock.setDrawerListener(actionBarDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    ListView.OnItemClickListener DrawerItemClickListener = new ListView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent intent;
            switch (position) {
                case 0:
                    intent = new Intent(StockUsuarioActivity.this, CambioContrasenaActivity.class);
                    startActivity(intent);
                    dlmenustock.closeDrawers();
                    break;
                case 1:
                    //Intent intent = new Intent(Intent.ACTION_MAIN);
                    //intent.addCategory(Intent.CATEGORY_HOME);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    new AlertDialog.Builder(StockUsuarioActivity.this).setTitle("Cerrar Sesion").setMessage("¿Desea cerrar sesion?")
                            .setNegativeButton("Cancelar", alertCancelOnClickListener).setPositiveButton("Aceptar", alertAcceptOnClickListener)
                            .setCancelable(false).show();

                    dlmenustock.closeDrawers();
                    break;
                case 2:
                    dlmenustock.closeDrawers();
                    break;
                default:
                    break;
            }
        }
    };


    @Override
    public void onStockClick(StockMaterial stockMaterial, int position) {

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stock, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_opciones) {
            return true;
        } else if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    DialogInterface.OnClickListener alertAcceptOnClickListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            SharedPreferences settings = getSharedPreferences("Usuario", Context.MODE_PRIVATE);
            settings.edit().clear().commit();
            Intent intent = new Intent(StockUsuarioActivity.this, MainActivity.class);
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
