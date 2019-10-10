package com.example.arithgo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

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

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap map;
    private Marker miUbicacion;
    private Polygon bibliotecaIcesi;
    private Polygon auditoriosIcesi;
    private Polygon edificioDIcesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
        },11);
        CRUDPoints.insertPoin();
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
}
