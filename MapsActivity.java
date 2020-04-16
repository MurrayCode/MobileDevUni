package com.example.murrayapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        //Check if network is available
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    double lat = location.getLatitude(); // gets latitude
                    double lng = location.getLongitude(); // gets longitude
                    LatLng latLng = new LatLng(lat,lng); // initiates class LatLng
                    Geocoder geocoder = new Geocoder(getApplicationContext()); // initiates geocoder
                    try {
                        List<Address> addressList= geocoder.getFromLocation(lat,lng, 1);
                        String str = addressList.get(0).getCountryName()+",";
                        str += addressList.get(0).getLocality()+",";
                        str += addressList.get(0).getPostalCode();
                        mMap.addMarker(new MarkerOptions().position(latLng).title(str));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));  ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                        double lat = location.getLatitude(); // gets latitude
                        double lng = location.getLongitude(); // gets longitude
                        LatLng latLng = new LatLng(lat,lng); // initiates class LatLng
                        Geocoder geocoder = new Geocoder(getApplicationContext()); // initiates geocoder
                        try {
                            List<Address> addressList= geocoder.getFromLocation(lat,lng, 1);
                            String str = addressList.get(0).getCountryName()+",";           //Shows Country name on marker
                            str += addressList.get(0).getLocality()+",";                    //Shows Town/City
                            str += addressList.get(0).getPostalCode();                      //Shows Post Code
                            mMap.addMarker(new MarkerOptions().position(latLng).title(str)); //adds the marker when position changes
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));            // moves camera to new location
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }


                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {

                }

                @Override
                public void onProviderDisabled(String provider) {

                }
            });
        }
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }
}
