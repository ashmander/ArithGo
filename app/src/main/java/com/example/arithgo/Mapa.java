package com.example.arithgo;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.arithgo.model.data.CRUDPoints;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

public class Mapa extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap map;
    private Marker miUbicacion;
    private Polygon bibliotecaIcesi;
    private Polygon auditoriosIcesi;
    private Polygon edificioDIcesi;
    private Button canjearBtn, retoBtn, exit;
    private TextView points;
    int myPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        },11);
        canjearBtn = findViewById(R.id.canjear_btn);
        canjearBtn.setVisibility(View.INVISIBLE);
        canjearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),CanjeoActivity.class);
                startActivity(i);
            }
        });
        retoBtn  = findViewById(R.id.challenge_btn);
        retoBtn.setVisibility(View.INVISIBLE);
        retoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Challenge.class);
                startActivity(intent);
            }
        });
        points = findViewById(R.id.my_pointsMapa_tv);
        myPoints = CRUDPoints.getPoints();
        points.setText(""+myPoints);
        exit = findViewById(R.id.exit_mapa);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mapa.super.onBackPressed();
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        bibliotecaIcesi = map.addPolygon(new PolygonOptions().add(
                new LatLng(3.341935, -76.530103),
                new LatLng(3.341921, -76.529808),
                new LatLng(3.341640, -76.529819),
                new LatLng(3.341654, -76.530084)
        ));
        auditoriosIcesi = map.addPolygon(new PolygonOptions().add(
                new LatLng(3.342334, -76.529618),
                new LatLng(3.342701, -76.529954),
                new LatLng(3.342848, -76.529724),
                new LatLng(3.342606, -76.529443)
        ));
        edificioDIcesi = map.addPolygon(new PolygonOptions().add(
                new LatLng(3.341001, -76.530411),
                new LatLng(3.340803, -76.530414),
                new LatLng(3.340795, -76.530009),
                new LatLng(3.340987, -76.529968)
        ));
        LatLng icesi = new LatLng(3.341552, -76.529784);
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(icesi, 15));
        miUbicacion = map.addMarker(new MarkerOptions().position(icesi).icon(BitmapDescriptorFactory.fromResource(R.drawable.ubication)).title("Mi ubicacion"));
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,this);

        } else {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION
            },11);
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
        miUbicacion.setPosition(pos);
        boolean insideAuditorios = PolyUtil.containsLocation(miUbicacion.getPosition().latitude,miUbicacion.getPosition().longitude,auditoriosIcesi.getPoints(),true);
        boolean insideLibrary = PolyUtil.containsLocation(miUbicacion.getPosition().latitude,miUbicacion.getPosition().longitude,bibliotecaIcesi.getPoints(),true);
        boolean insideD = PolyUtil.containsLocation(miUbicacion.getPosition().latitude,miUbicacion.getPosition().longitude,edificioDIcesi.getPoints(),true);
        if(insideLibrary) {
            canjearBtn.setVisibility(View.VISIBLE);
        }else if(insideAuditorios || insideD) {
            retoBtn.setVisibility(View.VISIBLE);
        } else {
            canjearBtn.setVisibility(View.INVISIBLE);
            retoBtn.setVisibility(View.INVISIBLE);
        }
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(pos, 18));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

