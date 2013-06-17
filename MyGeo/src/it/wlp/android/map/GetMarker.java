package it.wlp.android.map;

import it.mygeo.project.R;

import android.app.Activity;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;

public class GetMarker 
{
	public static BitmapDescriptor getmarker(String type, Activity activity) 
	{
			if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[0]))
				return BitmapDescriptorFactory.fromResource(R.drawable.marker_car);
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[1]))
				return BitmapDescriptorFactory.fromResource(R.drawable.marker_home);
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[2]))
				return BitmapDescriptorFactory.fromResource(R.drawable.marker_scooter);
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[3]))
				return BitmapDescriptorFactory.fromResource(R.drawable.marker_gym);
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[4]))
				return BitmapDescriptorFactory.fromResource(R.drawable.marker_kies);
			else
				return BitmapDescriptorFactory.fromResource(R.drawable.marker);
			
			
	}
	
	public static int getIDmarker(String type, Activity activity) 
	{
			if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[0]))
				return R.drawable.marker_car;
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[1]))
				return R.drawable.marker_home;
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[2]))
				return R.drawable.marker_scooter;
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[3]))
				return R.drawable.marker_gym;
			else if (type.equals(activity.getResources().getStringArray(R.array.geo_obj)[4]))
				return R.drawable.marker_kies;
			else
				return R.drawable.marker;
			
			
	}
	
}
