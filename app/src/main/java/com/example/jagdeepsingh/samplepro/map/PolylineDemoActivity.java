package com.example.jagdeepsingh.samplepro.map;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.jagdeepsingh.samplepro.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.Arrays;
import java.util.List;

public class PolylineDemoActivity extends AppCompatActivity implements OnMapReadyCallback {

    MapFragment poly_map;
    GoogleMap   googleMap;

    // sample Lat long for creating Polygon
    private static final LatLng MELBOURNE = new LatLng(-37.81319, 144.96298);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng ADELAIDE = new LatLng(-34.92873, 138.59995);
    private static final LatLng PERTH = new LatLng(-31.95285, 115.85734);
    private static final LatLng LHR = new LatLng(51.471547, -0.460052);
    private static final LatLng LAX = new LatLng(33.936524, -118.377686);
    private static final LatLng JFK = new LatLng(40.641051, -73.777485);
    private static final LatLng AKL = new LatLng(-37.006254, 174.783018);

    private Polyline    polyline;
    private Polygon     mClickablePolygonWithHoles;
    private Circle      circle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polyline_demo);

        poly_map = (MapFragment) getFragmentManager().findFragmentById(R.id.poly_map);
        poly_map.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
//        setPolyline();
//        setPolygon();
        setCircle();
    }

    private void setCircle() {
        circle = googleMap.addCircle(new CircleOptions()
                .center(SYDNEY)
                .radius(100)
                .strokeWidth(10)
                .strokeColor(Color.GREEN)
                .fillColor(Color.argb(128, 255, 0, 0)));
    }

    private void setPolygon() {
        mClickablePolygonWithHoles = googleMap.addPolygon(new PolygonOptions()
                .addAll(createRectangle(new LatLng(-20, 130), 5, 5))
                .addHole(createRectangle(new LatLng(-22, 128), 1, 1))
                .addHole(createRectangle(new LatLng(-18, 133), 0.5, 1.5))
                .fillColor(Color.CYAN)
                .strokeColor(Color.BLUE)
                .strokeWidth(5));
    }

    /**
     * Creates a List of LatLngs that form a rectangle with the given dimensions.
     */
    private List<LatLng> createRectangle(LatLng center, double halfWidth, double halfHeight) {
        return Arrays.asList(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
    }

    private void setPolyline() {
        // setting map description
        googleMap.setContentDescription("Google Map with Polyline");

        // Rectangle centered at Sydney.  This polyline will be mutable.
        int radius = 5;
        PolylineOptions options = new PolylineOptions()
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius))
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude - radius))
                .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude - radius))
                .add(new LatLng(SYDNEY.latitude - radius, SYDNEY.longitude + radius))
                .add(new LatLng(SYDNEY.latitude + radius, SYDNEY.longitude + radius));

        // A simple polyline with the default options from Melbourne-Adelaide-Perth.
        googleMap.addPolyline(options
                .add(MELBOURNE, ADELAIDE, PERTH)
                .geodesic(true));

        // Move the map so that it is centered on the mutable polyline.
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SYDNEY));

        // Add a listener for polyline clicks that changes the clicked polyline's color.
        googleMap.setOnPolylineClickListener(new GoogleMap.OnPolylineClickListener() {
            @Override
            public void onPolylineClick(Polyline polyline) {
                // Flip the values of the r, g and b components of the polyline's color.
                int strokeColor = polyline.getColor() ^ 0x00ffffff;
                polyline.setColor(strokeColor);
            }
        });

    }
}
