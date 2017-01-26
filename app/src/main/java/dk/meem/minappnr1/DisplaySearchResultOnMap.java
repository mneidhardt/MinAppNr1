package dk.meem.minappnr1;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

/**
 * Created by mine on 25/01/2017.
 */

public class DisplaySearchResultOnMap extends FragmentActivity implements OnMapReadyCallback  {

    private GoogleMap mMap;
    private String coordsys;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();
        /*this.stednavn = intent.getStringExtra(MainActivity.STEDNAVN);
        this.kommunenavn = intent.getStringExtra(MainActivity.KOMMUNENAVN);
        String login = intent.getStringExtra(MainActivity.LOGIN);
        String password = intent.getStringExtra(MainActivity.PASSWORD);
        */
        this.coordsys = intent.getStringExtra(MainActivity.COORDSYS);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng bb1 = new LatLng(55.43485652, 9.93543252);
        LatLng bb2 = new LatLng(55.4357145, 9.93682074);
        mMap.addMarker(new MarkerOptions().position(bb1).title("BB1"));
        mMap.addMarker(new MarkerOptions().position(bb2).title(this.coordsys));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bb1));


// Instantiates a new Polyline object and adds points to define a rectangle
/*        PolylineOptions rectOptions = new PolylineOptions()
                .add(new LatLng(55.26, 9.56))
                .add(new LatLng(55.36, 9.56))  // North of the previous point, but at the same longitude
                .add(new LatLng(55.36, 9.66))  // Same latitude, and 30km to the west
                .add(new LatLng(55.26, 9.66))  // Same longitude, and 16km to the south
                .add(new LatLng(55.26, 9.56)); // Closes the polyline.

// Get back the mutable Polyline
        Polyline polyline = mMap.addPolyline(rectOptions);
        */
    }
}
