package com.example.jagdeepsingh.samplepro.map;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.example.jagdeepsingh.samplepro.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Jagdeep.Singh on 04-08-2016.
 */
public class MapActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        OnMapReadyCallback
{

    private static final String TAG = MapActivity.class.getSimpleName();
    private static final int REQUEST_CODE_LOCATION = 2;
    GoogleApiClient googleApiClient;
    Location location;

    List<String> permissionList = new ArrayList<>();

    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
    GoogleMap googleMap;
    private MapFragment mapFragment;
    private LatLng locallatLng;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_screen);

        // Getting Map from the XML
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        //Setting the Listener to the mapFragment on MapReady CallBack
        mapFragment.getMapAsync(this);

    }

    private void buildGoogleAPIclient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if(googleApiClient != null && googleApiClient.isConnected() )
        googleApiClient.disconnect();
    }

    /**
     * Google API client callback
     * @param bundle
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.i(TAG, "onConnected: ");
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Request missing location permission.
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);

            ActivityCompat.requestPermissions(this,
                    permissionList.toArray(new String[permissionList.size()]),
                    REQUEST_CODE_LOCATION);

        } else {
            location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        }

        // Checking if location is not null
        if(location!=null){
            Log.i(TAG, "onConnected: Latitude is" + location.getLatitude());
            Log.i(TAG, "onConnected: Longitude is" + location.getLongitude());
            locallatLng = new LatLng(location.getLatitude(), location.getLongitude());

            // With Help of GeoCoder class getting the Name of Location according to lat long given
            Geocoder geoCoder = new Geocoder(this, Locale.getDefault());
            StringBuilder builder = new StringBuilder();
            try {
                List<Address> address = geoCoder.getFromLocation(locallatLng.latitude, locallatLng.longitude, 1);
                int maxLines = address.get(0).getMaxAddressLineIndex();
                for (int i=0; i<maxLines; i++) {
                    String addressStr = address.get(0).getAddressLine(i);
                    builder.append(addressStr);
                    builder.append(" ");
                }

                String finalAddress = builder.toString(); //This is the complete address.

                // setting the marker with the place name
                googleMap.addMarker(new MarkerOptions().position(
                        locallatLng)
                        .title(finalAddress));
        }catch (Exception e){
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "onConnectionSuspended: ");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i(TAG, "onConnectionFailed: ");
    }

    /**
     * Permission call backs
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_LOCATION){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Log.i(TAG, "onRequestPermissionsResult: Permission Granted");
            }else{
                Toast.makeText(MapActivity.this, "Permission not granted", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Getting and assigning the googleMap to local variable
        this.googleMap = googleMap;

        // Adding marker to given lat long
        googleMap.addMarker(new MarkerOptions().position(
                PERTH)
                .title("PERTH"));

        // After Building the map successfully call the buildGoogleAPIClient to get the Current Location
        buildGoogleAPIclient();

    }

}
