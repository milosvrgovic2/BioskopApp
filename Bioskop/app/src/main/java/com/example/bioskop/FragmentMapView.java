package com.example.bioskop;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class FragmentMapView extends Fragment {

    private static final int PERMISSION_ID = 44 ;
    MapView mapView;
    private GoogleMap gMap;
    FusedLocationProviderClient mFusedLocationClient;
    SharedPreferences prefLat, prefLong;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.location_fragment, container, false);
        prefLat = getContext().getSharedPreferences("latitude", Context.MODE_PRIVATE);
        prefLong = getContext().getSharedPreferences("longitude",Context.MODE_PRIVATE);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        mapView = view.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        }catch (Exception e){
            e.printStackTrace();
        }

        getLastLocation();

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                gMap = googleMap;
                gMap.setMyLocationEnabled(true);

                //arena cineplex 45.253935, 19.845298
                //cineplexx promenada 45.244787, 19.841957
                //cinestar big 45.275021, 19.828625

                LatLng lokacija1 = new LatLng(45.253935, 19.845298);
                SharedPreferences.Editor editorLong = prefLong.edit();
                SharedPreferences.Editor editorLat = prefLat.edit();
                editorLat.putString("lat", String.valueOf(lokacija1.latitude));
                editorLong.putString("long", String.valueOf(lokacija1.longitude));
                editorLat.commit();
                editorLong.commit();
                LatLng lokacija2 = new LatLng(45.244787, 19.841957);
                LatLng lokacija3 = new LatLng(45.275021, 19.828625);

                gMap.addMarker(new MarkerOptions().position(lokacija1).title("Lokacija 1").snippet("Bioskop Prvi").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_logo)));
                gMap.addMarker(new MarkerOptions().position(lokacija2).title("Lokacija 2").snippet("Bioskop Drugi").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_logo)));
                gMap.addMarker(new MarkerOptions().position(lokacija3).title("Lokacija 3").snippet("BIoskop Treci").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_logo)));



                CameraPosition cameraPosition = new CameraPosition.Builder().target(lokacija1).zoom(12).build();
                gMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });

        return view;
    }

    //da li korisnik dozvoljava pristup ACCESS_CORE_LOCATION i ACCESS_FINE_LOCATION
    private boolean checkPermissions(){
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        return false;
    }

    //trazenje odobrenja od korisnika
    private void requestPermissions(){
        ActivityCompat.requestPermissions(
                getActivity(),
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                PERMISSION_ID
        );
    }

    //poziva se kada korisnik odbije ili dozvoli trazena odobrenja
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_ID) {
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

            }
        }
    }

    //provera da li je korisnik ukljucio lokaciju
    private boolean isLocationEnabled(){
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
        );
    }

    @SuppressLint("MissingPermission")
    private void getLastLocation(){
        if (checkPermissions()) {
            if (isLocationEnabled()) {
                mFusedLocationClient.getLastLocation().addOnCompleteListener(
                        new OnCompleteListener<Location>() {
                            @Override
                            public void onComplete(@NonNull Task<Location> task) {
                                Location location = task.getResult();
                                if (location == null) {
                                    requestNewLocationData();
                                } else {

                                }
                            }
                        }
                );
            } else {
                Toast.makeText(getActivity(), "Turn on location", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            requestPermissions();
        }
    }

    //belezi informacije o lokaciji u runtime-u na svakih 5 do 10 sekundi
    @SuppressLint("MissingPermission")
    private void requestNewLocationData(){

        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        //mLocationRequest.setNumUpdates(1);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getContext());
        mFusedLocationClient.requestLocationUpdates(
                mLocationRequest, mLocationCallback,
                Looper.myLooper()
        );

    }

    private LocationCallback mLocationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            Location mLastLocation = locationResult.getLastLocation();
            String s = String.valueOf(mLastLocation.getLatitude());
            s += " , " + mLastLocation.getLongitude();
        }
    };

}
