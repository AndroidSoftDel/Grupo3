package com.example.javierhuinocana.grupo03_cibertec;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.javierhuinocana.grupo03_cibertec.entities.ListaOrdenes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;

/**
 * Created by javier on 13/09/2015.
 */
public class Mapa_Ordenes extends AppCompatActivity implements OnMapReadyCallback {

    private SupportMapFragment mSupportMapFragment;
    private GoogleMap mGoogleMap;
    ArrayList<ListaOrdenes> listaOrdenes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mapa_ordenes);

        mSupportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mSupportMapFragment.getMapAsync(Mapa_Ordenes.this);

        if (getIntent().getExtras() != null && getIntent().getExtras().containsKey(ListaOrdenesActivity.ARG_ORDEN)) {
            //listaOrdenes = getIntent().getParcelableExtra(ListaOrdenesActivity.ARG_ORDEN);
            listaOrdenes = getIntent().getParcelableArrayListExtra(ListaOrdenesActivity.ARG_ORDEN);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        ListaOrdenes Orden = null;

        for (int i = 0; i < listaOrdenes.size(); i++) {
            Orden = listaOrdenes.get(i);

            mGoogleMap.addMarker(new MarkerOptions()
                    .position(new LatLng(
                            Float.parseFloat(Orden.getCoordenada().split("\\|")[0]),
                            Float.parseFloat(Orden.getCoordenada().split("\\|")[1]))).title(
                            "Ord.: " + Orden.getOrden() + "\n" +
                                    "Telf: " + Orden.getTelefono() + "\n" +
                                    "Neg: " + Orden.getNegocio()
                    ));
        }

        if (Orden != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(
                    Float.parseFloat(Orden.getCoordenada().split("\\|")[0]),
                    Float.parseFloat(Orden.getCoordenada().split("\\|")[1]))));
        }
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);


        /*
        mGoogleMap.addMarker(new MarkerOptions()
                .position(new LatLng(-12.086542, -77.048923))
                .title("Marker").draggable(true));
//        mGoogleMap.addPolyline(new PolylineOptions().add(new LatLng(-12.086279, -77.049621)).add(new LatLng(-12.085272, -77.048655)).add(new LatLng(-12.088231, -77.048162)).add(new LatLng(-12.088367, -77.049299)).color(0xFFFF0033));
        mGoogleMap.addPolygon(new PolygonOptions().add(new LatLng(-12.086279, -77.049621)).add(new LatLng(-12.085272, -77.048655)).add(new LatLng(-12.088231, -77.048162)).add(new LatLng(-12.088367, -77.049299)).strokeColor(0xFFFF0033));
//        mGoogleMap.setMyLocationEnabled(true);
//        mGoogleMap.getUiSettings().setMyLocationButtonEnabled(true);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-12.086542, -77.048923)));
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
*/

    }
}
