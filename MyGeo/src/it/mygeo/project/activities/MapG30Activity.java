package it.mygeo.project.activities;


import it.mygeo.project.R;
import it.wlp.android.widgets.TitleBar;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapG30Activity extends FragmentActivity 
{
	
	private GoogleMap mMap;

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.g30);
	        TitleBar bar = (TitleBar)findViewById(R.id.titleBar_g30);
	        bar.setActivity(this);
	        bar.setTitle(R.string.title_mygeo_label_g30);
	        setUpMapIfNeeded();
	    }
	
	 private void setUpMapIfNeeded() {
	        // Do a null check to confirm that we have not already instantiated the map.
	        if (mMap == null) {
	            // Try to obtain the map from the SupportMapFragment.
	            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
	                    .getMap();
	            // Check if we were successful in obtaining the map.
	            if (mMap != null) {
	                setUpMap();
	            }
	        }
	    }

	    /**
	     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
	     * just add a marker near Africa.
	     * <p>
	     * This should only be called once and when we are sure that {@link #mMap} is not null.
	     */
	    private void setUpMap() {
	         mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
	    	 mMap.setMyLocationEnabled(true);
	    	 LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
	    	 Criteria criteria = new Criteria();
	    	 String provider = service.getBestProvider(criteria, false);
	    	 Location location = service.getLastKnownLocation(provider);
	    	 LatLng userLocation = new LatLng(location.getLatitude(),location.getLongitude());
	    	 mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("My Location"));
	    }
	 
}
