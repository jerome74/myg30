package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.handler.manage.ManageDialogHandler;
import it.wlp.android.system.bean.G30Bean;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;

public class MapNowG30Activity extends FragmentActivity implements
ConnectionCallbacks
, OnConnectionFailedListener
, LocationListener{
	
	 private static final LocationRequest REQUEST = LocationRequest.create()
		      .setInterval(5000)         // 5 seconds
		      .setFastestInterval(16)    // 16ms = 60fps
		      .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

	 private LocationClient mLocationClient;
	 private Location myLocation;

	 @Override
	    protected void onCreate(Bundle savedInstanceState) 
	 	{
		 super.onCreate(savedInstanceState);
		 setUpLocationClientIfNeeded();
	 	}
	
	@Override
	public void onLocationChanged(Location paramLocation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnectionFailed(ConnectionResult paramConnectionResult) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnected(Bundle paramBundle) 
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
			 	
				
				Geocoder geocoder = new Geocoder(this, Locale.getDefault());
				List<Address> addresses = null;
				try {
					addresses = geocoder.getFromLocation(myLocation.getLatitude(), myLocation.getLongitude(), 1);
				} 
				catch (IOException e) 
				{
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(MapNowG30Activity.this, R.string.no_service,
									Toast.LENGTH_SHORT).show();
						}
					});
				}
				
			 	if(addresses != null)
			 	{
				 	Message message = new Message();
					String strCM =String.valueOf(System.currentTimeMillis());
					
					//1) Street Address 2) City / State 3) Zip 4) Complete Address
					String address = addresses.get(0).getAddressLine(0);
					String city = addresses.get(0).getAddressLine(1);
					String country = addresses.get(0).getAddressLine(2);
					
					int ID =  Integer.parseInt(strCM.substring( strCM.length() - 9 )) ;
					G30Bean ig30 = new G30Bean(ID
																,R.drawable.marker
																,myLocation.getLongitude()
																,myLocation.getLatitude()	
																,address
																,getString(R.string.marker));
					
					message.arg1 = UTIL_GEO.NB_newGeoMarkerInsert;
					message.obj =ig30;
					NotifyBean.notifyEvent(UTIL_GEO.NB_MyGeoActivity, message);
			 	}
				finish();
				
		 }
	}

	@Override
	public void onDisconnected() {
		// TODO Auto-generated method stub
		
	}
	
	 private void setUpLocationClientIfNeeded() {
		    if (mLocationClient == null) {
		      mLocationClient = new LocationClient(
		          getApplicationContext(),
		          this,  // ConnectionCallbacks
		          this); // OnConnectionFailedListener
		    }
		  }
	 
	 @Override
	  protected void onResume() {
	    super.onResume();
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

}
