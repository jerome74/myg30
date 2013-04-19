package it.mygeo.project.service.alert.signal;

import it.mygeo.project.R;
import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.domain.ConnService;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Message;
import android.util.Log;

public class ConnAlarmSignal extends BroadcastReceiver {

	@Override
	public void onReceive(Context paramContext, Intent paramIntent) 
	{	 
		
		Intent connection = new Intent(paramContext , ConnService.class);
		
		 final ConnectivityManager connMgr 		= (ConnectivityManager) paramContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		 final android.net.NetworkInfo wifi 		= connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		 final android.net.NetworkInfo mobile 	= connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		 
		 final IToastHelper iToastHelper 						= new ToastHelper(paramContext);
		 final ToastHelperDomain toastHelperDomain 	= new ToastHelperDomain(iToastHelper);
			
		Log.d(UTIL_GEO.MYGEO, "post setup variables and message");
		 

		if (wifi.isAvailable() && wifi.isConnectedOrConnecting()) 
		{	
		    toastHelperDomain.createToastMessage(R.string.wifi, R.drawable.go);
			
		    paramContext.stopService(connection);
		    Log.d(UTIL_GEO.MYGEO, "post wifi isAvailable");
		}
		else if (mobile.isAvailable() && mobile.isConnectedOrConnecting()) 
		{
		    toastHelperDomain.createToastMessage(R.string.tre_g, R.drawable.go);
		    
		    paramContext.stopService(connection);
		    Log.d(UTIL_GEO.MYGEO, "post mobile isAvailable");
		} 
		else 
		{
			Log.d(UTIL_GEO.MYGEO, "post any network");
		    toastHelperDomain.createToastMessage(R.string.no_net, R.drawable.stop);
		}
	}
}
