package it.mygeo.project.activities;

import static it.mygeo.project.constants.UTIL_GEO.MENU_EXIT;
import static it.mygeo.project.constants.UTIL_GEO.SELECT_MENU_EXIT;

import java.util.List;

import it.mygeo.project.R;
import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.handler.ServiceHandler;
import it.mygeo.project.service.domain.ConnService;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.handler.manage.ManageDialogHandler;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.proxy.external.IProxyView;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class MyGeoActivity extends Activity {
	
	IProxyView iProxyView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		iProxyView = new ProxyView(this, this);
		iProxyView.init();
	}

	@Override
	protected Dialog onCreateDialog(int id) 
	{
		ManageDialogHandler dialogHandler = new ManageDialogHandler();
		DialogElement dialogElement =  dialogHandler.createDialogElement(this, this , id);
		
		return dialogElement.getDialog();
	}
	
	/**
	 * @method onCreateOptionsMenu
	 * @args menu
	 * @return boolean
	 */
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        
        menu.add(0, MENU_EXIT, 0, R.string.menu_exit).setIcon(
                R.drawable.exit3);
        return true;
    }
	
	/**
	 * @method onOptionsItemSelected
	 * @args item
	 * @return boolean
	 */
	
	
	  @SuppressWarnings("deprecation")
	@Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        super.onOptionsItemSelected(item);
	        switch (item.getItemId()) {
	            case MENU_EXIT:
	               showDialog(SELECT_MENU_EXIT);
	                return true;
	        }
	        return false;
	    }
	
	/**
	 * @method onKeyDown
	 * @args keyCode, event
	 * @return boolean
	 */
	  
	  @Override
	  public boolean onKeyDown(final int keyCode, final KeyEvent event)  
	  {
	      if ( keyCode == KeyEvent.KEYCODE_BACK )
	      {
	    	  	IToastHelper iToastHelper = new ToastHelper(this);
          		ToastHelperDomain toastHelperDomain = new ToastHelperDomain(iToastHelper);
          		toastHelperDomain.createToastMessage(R.string.press_hard_key, R.drawable.stop);
          		return true;
	      }
	      
	      return super.onKeyDown(keyCode, event);
	  }

	@Override
	protected void onDestroy() 
	{
		ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		List<RunningServiceInfo> serviceInfos = activityManager.getRunningServices(Integer.MAX_VALUE);
		
		for (RunningServiceInfo runningServiceInfo : serviceInfos) 
		{
			if(runningServiceInfo.service.getClassName().equals("it.mygeo.project.service.domain.ConnService"))
			{
				 final Intent 				service 				= new Intent(this , ConnService.class);
				 final ServiceHandler 	serviceHandler 	= new ServiceHandler(this,  this);
					
				 final	Message message = new Message();
				 
				 message.arg1 = SERVICES.STOP;
				 
				 serviceHandler.handleMessage(message, service);
				 
			}
		}
		
		
		super.onDestroy();
	}
	  
	  
	  
}
