package it.mygeo.project.activities;


import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.handler.manage.ManageDialogHandler;
import it.wlp.android.map.G30MarkerDragListener;
import it.wlp.android.map.GetMarker;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;
import it.wlp.android.util.CheckObj;
import it.wlp.android.widgets.TitleBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapStartG30Activity extends FragmentActivity implements
ConnectionCallbacks
, OnConnectionFailedListener
, LocationListener
{

	
	 private GoogleMap mMap;
	 private LocationClient mLocationClient;
	 private Location myLocation;
	 private ToastHelper iToastHelper;
	 private ToastHelperDomain toastHelperDomain;
	 private TextView mTitleView;
	 private TitleBar titleBar;
	 private double latitude;
	 private double  oldLongitude;
	 private double oldLatitude;
	 private double  longitude;
	 private boolean redefine;
	 private List<LatLng> polyz;
	 private boolean  firstTimeMarker = true;
	 private Activity activity = null;
	 
	 
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
	        bar.isGeo();
	        
	        if (CheckObj.check(getIntent().getStringExtra(UTIL_GEO.UPDATE_G30), UTIL_GEO.STRING))
	        {
	        	bar.setUpdateG30bean(getIntent().getStringExtra(UTIL_GEO.UPDATE_G30));
	        	bar.setTitle(bar.getG30bean().getTitle());
	        	redefine = true;
	        }
	        else
	        {
	        	bar.setTitle(R.string.title_mygeo_label_g30);
	        	redefine = false;
	        }
	        
	        activity = this;
	        
	        setUpMapIfNeeded();
	        setUpLocationClientIfNeeded();
	        setUpMap();
	        
	        iToastHelper 				= new ToastHelper(this);
			toastHelperDomain 	= new ToastHelperDomain(iToastHelper);
			mTitleView 					= bar.getmTitleView();
			titleBar						= bar;
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
	 
//	 private void setUpMapIfNeeded() 
//	 {
//		      mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)) .getMap();
//		        mMap.setMyLocationEnabled(true);
//		  }
	

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
				 	
				 	if(myLocation == null)
				 	{
				 		IToastHelper iToastHelper = new ToastHelper(this);
				 		ToastHelperDomain toastHelperDomain = new ToastHelperDomain(iToastHelper);
				 		toastHelperDomain.createToastMessage(R.string.geo_position_on, R.drawable.stop);
				 		startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
				 		return;
				 	}
				 		
				 
		    		
			    	
//			    	 Drawable marker=getResources().getDrawable(R.drawable.marker);
//			    	 
//			    	 marker.setBounds(0, 0, marker.getIntrinsicWidth(),
//	                            marker.getIntrinsicHeight());
			    	 
			    	 
			    	 if(!redefine)
			    	 {
			    		 CameraPosition cameraPosition = new CameraPosition.Builder()
				    	   .target(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()))      // Sets the center of the map to Mountain View
				    	   .zoom(zoomLevel(getIntent().getIntExtra(UTIL_GEO.RANGE, 0)))                   // Sets the zoom
				    	   .bearing(90)                // Sets the orientation of the camera to east
				    	   .tilt(30)                   // Sets the tilt of the camera to 30 degrees
				    	   .build();                   // Creates a CameraPosition from the builder
				    	 mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
				    	 	    		 
			    		 createGeofence(myLocation.getLatitude(),myLocation.getLongitude(), false);
			    	 }
			    	 else
			    	 {
			    		 LatLngBounds bounds = new LatLngBounds.Builder().include(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()))
			                     .include(new LatLng(titleBar.getG30bean().getLatitude(), titleBar.getG30bean().getLongitude())).build();
			    		 
			    		 
			    		 CameraPosition cameraPosition = new CameraPosition.Builder()
				    	   .target(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()))      // Sets the center of the map to Mountain View
				    	   .zoom(zoomLevel(getIntent().getIntExtra(UTIL_GEO.RANGE, 0)))                   // Sets the zoom
				    	   .bearing(90)                // Sets the orientation of the camera to east
				    	   .tilt(30)                   // Sets the tilt of the camera to 30 degrees
				    	   .build();                   // Creates a CameraPosition from the builder
				    	 mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 400,400,0));
				    	 mMap.setOnCameraChangeListener(null);
			    		 
			    		 createGeofence(titleBar.getG30bean().getLatitude(),titleBar.getG30bean().getLongitude(), false);
			    		
			    	 }
		    	} 
		}
		
		public void createGeofence(double latitude, double longitude, boolean openKeyborad) {

				  Marker stopMarker = mMap.addMarker(new MarkerOptions()
				    						.draggable(true)
				    						.position(new LatLng(latitude, longitude)));
				  
				  String type = getIntent().getStringExtra(UTIL_GEO.TYPE_MARKER);

				  stopMarker.setIcon(GetMarker.getmarker(type, this));
				  titleBar.setMarker(GetMarker.getIDmarker(type, this));
				  titleBar.setType(type);
				  


				 mMap.setOnMarkerDragListener(new G30MarkerDragListener(this, mMap));
				 

		    	 manageOtherMarkers(latitude,longitude);

				 
		if (openKeyborad) 
		{
			ManageDialogHandler dialogHandler = new ManageDialogHandler();
			DialogElement dialogElement =  dialogHandler.createDialogElement(this, this , UTIL_GEO.SELECT_G30_DESC , mTitleView);
			
			setLatitude(latitude);
			setLongitude(longitude);
			
			dialogElement.getDialog().show();
				
			}
		
			new GetDirection(String.valueOf(latitude)
				,String.valueOf(longitude)
				,String.valueOf(myLocation.getLatitude())
				,String.valueOf(myLocation.getLongitude())).execute();
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


		@Override
		protected void onDestroy() {
			// TODO Auto-generated method stub
			super.onDestroy();
		}


		public double getLatitude() {
			return latitude;
		}


		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}


		public double getLongitude() {
			return longitude;
		}


		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		
	    /**
	     * 
	     * @author jerome
	     *
	     */
		
	    class GetDirection extends AsyncTask<String, String, String> {

	    	
	    	 private String startLat;
	    	 private String startLng;
	    	 private String endLat;
	    	 private String endtLng;
	    	
	    	
	        public GetDirection(String startLat, String startLng,
					String endLat, String endtLng) {
				super();
				this.startLat = startLat;
				this.startLng = startLng;
				this.endLat = endLat;
				this.endtLng = endtLng;
			}

			@Override
	        protected void onPreExecute() 
	        {
	            super.onPreExecute();
	        }

	        protected String doInBackground(String... args) 
	        {     
	        	try
 {
				String stringUrl = "http://maps.googleapis.com/maps/api/directions/json?origin="
						+ this.startLat
						+ ","
						+ this.startLng
						+ "&destination="
						+ this.endLat + "," + this.endtLng + "&sensor=false";
				HttpPost httppost = new HttpPost(stringUrl);
				HttpClient httpclient = new DefaultHttpClient();
				HttpResponse response = httpclient.execute(httppost);
				HttpEntity entity = response.getEntity();
				InputStream is = null;
				is = entity.getContent();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				sb.append(reader.readLine() + "\n");
				String line = "0";
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();
				reader.close();
				String result = sb.toString();

				JSONObject jsonObject = new JSONObject(result);

				// routesArray contains ALL routes
				JSONArray routesArray = jsonObject.getJSONArray("routes");
				// Grab the first route
				JSONObject route = routesArray.getJSONObject(0);

				JSONObject poly = route.getJSONObject("overview_polyline");
				String polyline = poly.getString("points");
				polyz = decodePoly(polyline);

			} catch (Exception e) 
			{
				runOnUiThread(new Runnable() {
					public void run() {
						Toast.makeText(activity, R.string.no_service,
								Toast.LENGTH_SHORT).show();
						finish();
				}
				});
				
			}

	            return null;

	        }

	        protected void onPostExecute(String file_url) {

	        	if(polyz != null)
		            for (int i = 0; i < polyz.size() - 1; i++) 
		            {
		                LatLng src = polyz.get(i);
		                LatLng dest = polyz.get(i + 1);
		                Polyline line = mMap.addPolyline(new PolylineOptions()
		                        .add(new LatLng(src.latitude, src.longitude),
		                                new LatLng(dest.latitude,                dest.longitude))
		                        .width(20).color(Color.BLUE).geodesic(true));
	
		            }
	        }
	    }
	    
	    /* Method to decode polyline points */
	    private List<LatLng> decodePoly(String encoded) {

	        List<LatLng> poly = new ArrayList<LatLng>();
	        int index = 0, len = encoded.length();
	        int lat = 0, lng = 0;

	        while (index < len) {
	            int b, shift = 0, result = 0;
	            do {
	                b = encoded.charAt(index++) - 63;
	                result |= (b & 0x1f) << shift;
	                shift += 5;
	            } while (b >= 0x20);
	            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	            lat += dlat;

	            shift = 0;
	            result = 0;
	            do {
	                b = encoded.charAt(index++) - 63;
	                result |= (b & 0x1f) << shift;
	                shift += 5;
	            } while (b >= 0x20);
	            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
	            lng += dlng;

	            LatLng p = new LatLng((((double) lat / 1E5)),
	                    (((double) lng / 1E5)));
	            poly.add(p);
	        }

	        return poly;
	    }
	
	/**
	 *     
	 */
	    
	private void manageOtherMarkers(double latitude, double longitude)
	{
		ContainerG30Bean containerG30Bean = NotifyBean.getElementsC_G30Bean(UTIL_GEO.NB_GeoMarkerList);
		
		List<G30Bean> g30BeansAddMarker = new ArrayList<G30Bean>();
		List<G30Bean> g30BeansRemoveMarker = new ArrayList<G30Bean>();
		
		for (G30Bean g30Bean : containerG30Bean.g30Beans) 
			if (g30Bean.getType().equals(getString(R.string.marker))) 
			{
				g30BeansAddMarker.add(g30Bean);
				g30BeansRemoveMarker.add(g30Bean);
			}
		
		if (g30BeansAddMarker.size() > 0)
		{
			containerG30Bean.g30Beans.removeAll(g30BeansRemoveMarker);
			containerG30Bean.g30Beans.addAll(g30BeansAddMarker);
		}
		
		for (G30Bean g30Bean : containerG30Bean.g30Beans) 
		{
			
				if(oldLatitude != g30Bean.getLatitude() && oldLongitude != g30Bean.getLongitude())
				{
					Marker marker = mMap.addMarker(new MarkerOptions()
								.draggable(false)
								.position(new LatLng(g30Bean.getLatitude(), g30Bean.getLongitude())));
					marker.setIcon(GetMarker.getmarker(g30Bean.getType(), this));
				}
		}
		
		if(firstTimeMarker)
		{
			this.oldLatitude = latitude;
			this.oldLongitude = longitude;
			firstTimeMarker = false;
		}
	} 
}
