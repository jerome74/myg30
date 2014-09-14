package it.wlp.android.map;

import it.mygeo.project.activities.MapStartG30Activity;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerDragListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

public class G30MarkerDragListener implements OnMarkerDragListener 
{

	private FragmentActivity activity;
	private GoogleMap mMap;
	
	
	

	
	 public G30MarkerDragListener(FragmentActivity activity, GoogleMap mMap) {
		super();
		this.activity = activity;
		this.mMap = mMap;
	}

	@Override
	 public void onMarkerDrag(Marker marker) {
	 }
	 
	 @Override
	 public void onMarkerDragEnd(Marker marker) 
	 {
	  LatLng dragPosition = marker.getPosition();
	  
	  double dragLat = dragPosition.latitude;
	  double dragLong = dragPosition.longitude;
	  mMap.clear();
	  if(activity instanceof MapStartG30Activity)
		((MapStartG30Activity)activity).createGeofence(dragLat, dragLong, true);

	 }
	 @Override
	 public void onMarkerDragStart(Marker marker) {
	 }
}
