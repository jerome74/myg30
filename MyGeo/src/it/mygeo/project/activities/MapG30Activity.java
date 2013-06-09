package it.mygeo.project.activities;


import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.wlp.android.widgets.TitleBar;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapG30Activity extends FragmentActivity implements
ConnectionCallbacks, OnConnectionFailedListener, LocationListener
{

	
	 private GoogleMap mMap;
	 private LocationClient mLocationClient;
	 private Location myLocation;
	 
	  // These settings are the same as the settings for the map. They will in fact give you updates at
	  // the maximal rates currently possible.
	  private static final LocationRequest REQUEST = LocationRequest.create()
	      .setInterval(5000)         // 5 seconds
	      .setFastestInterval(16)    // 16ms = 60fps
	      .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.g30);
	        TitleBar bar = (TitleBar)findViewById(R.id.titleBar_g30);
	        bar.setActivity(this);
	        bar.setTitle(R.string.title_mygeo_label_g30);
	        setUpMapIfNeeded();
	        setUpLocationClientIfNeeded();
	        setUpMap();
	    }
	 
	 
	 private void setUpMapIfNeeded() {
		    // Do a null check to confirm that we have not already instantiated the map.
		    if (mMap == null) {
		      // Try to obtain the map from the SupportMapFragment.
		      mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
		             .getMap();
		      // Check if we were successful in obtaining the map.
		      if (mMap != null) {
		        mMap.setMyLocationEnabled(true);
		      }
		    }
		  }
	

	 private void setUpLocationClientIfNeeded() {
		    if (mLocationClient == null) {
		      mLocationClient = new LocationClient(
		          getApplicationContext(),
		          this,  // ConnectionCallbacks
		          this); // OnConnectionFailedListener
		    }
		  }
	 
	    /**
	     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
	     * just add a marker near Africa.
	     * <p>
	     * This should only be called once and when we are sure that {@link #mMap} is not null.
	     */
	    private void setUpMap() 
	    {
	    	// TODO Auto-generated method stub
	    }

		@Override
		public void onLocationChanged(Location location) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onConnectionFailed(ConnectionResult result) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onConnected(Bundle connectionHint) 
		{
			 mLocationClient.requestLocationUpdates(
				        REQUEST,
				        this);  // LocationListener
			 
			 if (mLocationClient != null && mLocationClient.isConnected()) 
		    	{
				 
				 	myLocation = mLocationClient.getLastLocation();
				 
		    		CameraPosition cameraPosition = new CameraPosition.Builder()
			    	   .target(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()))      // Sets the center of the map to Mountain View
			    	    .zoom(zoomLevel(getIntent().getIntExtra(UTIL_GEO.RANGE, 0)))                   // Sets the zoom
			    	    .bearing(90)                // Sets the orientation of the camera to east
			    	    .tilt(30)                   // Sets the tilt of the camera to 30 degrees
			    	    .build();                   // Creates a CameraPosition from the builder
			    	 mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
			    	 
//			    	 Drawable marker=getResources().getDrawable(R.drawable.marker);
//			    	 
//			    	 marker.setBounds(0, 0, marker.getIntrinsicWidth(),
//	                            marker.getIntrinsicHeight());
			    	 
			    	 createGeofence(myLocation.getLatitude(),myLocation.getLongitude(), 100, "CIRCLE", "GEOFENCE");
			    	 
		    	} 
		}
		
		private void createGeofence(double latitude, double longitude, int radius,
				   String geofenceType, String title) {

				  Marker stopMarker = mMap.addMarker(new MarkerOptions()
				    .draggable(true)
				    .position(new LatLng(latitude, longitude))
				    .title(title)
				    .icon(BitmapDescriptorFactory
				      .fromResource(R.drawable.marker)));

				  mMap.addCircle(new CircleOptions()
				    .center(new LatLng(latitude, longitude)).radius(radius)
				    .fillColor(Color.parseColor("#B2A9F6"))); 
				 mMap.setOnMarkerDragListener(new OnMarkerDragListener() {
					
					 @Override
					 public void onMarkerDrag(Marker marker) {
					 }
					 @Override
					 public void onMarkerDragEnd(Marker marker) {
					  LatLng dragPosition = marker.getPosition();
					  double dragLat = dragPosition.latitude;
					  double dragLong = dragPosition.longitude;
					  mMap.clear();
					  createGeofence(dragLat, dragLong, 100, "CIRCLE", "GEOFENCE");
					  Toast.makeText(
							  MapG30Activity.this,
					    "onMarkerDragEnd dragLat :" + dragLat + " dragLong :"
					      + dragLong, Toast.LENGTH_SHORT).show();
					  Log.i("info", "on drag end :" + dragLat + " dragLong :" + dragLong);

					 }
					 @Override
					 public void onMarkerDragStart(Marker marker) {
					 }
				});
		}
				 

		@Override
		public void onDisconnected() {
			// TODO Auto-generated method stub
			
		}
		
		 @Override
		  protected void onResume() {
		    super.onResume();
		    setUpMapIfNeeded();
		    setUpLocationClientIfNeeded();
		    mLocationClient.connect();
		  }

		  @Override
		  public void onPause() {
		    super.onPause();
		    if (mLocationClient != null) {
		      mLocationClient.disconnect();
		    }
		  }
		  
		  /**
		   * 
		   * @param screenWidth
		   * @return
		   */
			  public static byte zoomLevel (double distance){
				    byte zoom=1;
				    double E = 40075004;
				    zoom = (byte) Math.round(Math.log(E/distance)/Math.log(2)+1);
				    // to avoid exeptions
				    if (zoom>21) zoom=21;
				    if (zoom<1) zoom =1;

				    return zoom;
			}
	 
}
