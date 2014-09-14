package it.mygeo.project.activities;

import static it.mygeo.project.constants.UTIL_GEO.MENU_EXIT;
import static it.mygeo.project.constants.UTIL_GEO.SELECT_MENU_EXIT;
import it.mygeo.project.R;
import it.mygeo.project.constants.SERVICES;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.handler.ServiceHandler;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.domain.ConnService;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.handler.manage.ManageDialogHandler;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.proxy.external.IProxyView;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;
import it.wlp.android.system.fwork.EnginePersistFile;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;
import it.wlp.android.util.CheckObj;

import java.util.List;
import java.util.UUID;

import com.google.android.maps.MapView.LayoutParams;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MyGeoActivity extends Activity implements PreferenceCallBack {
	
	private IProxyView iProxyView;
	public LinearLayout hiddenLinearLayout;
	private ContainerG30Bean localContainerG30Bean;

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
	
	
	@Override
	public void returnServiceResponse() 
	{
		handler.sendEmptyMessage(0);
	}
	
	private Handler handler = new Handler()
	{
		public void handleMessage(final Message msg)
		{
			try 
			{
				runOnUiThread(new Runnable() 
                {
                    public void run() 
                    {
                    	switch (msg.arg1) 
                    	{
						case UTIL_GEO.NB_newGeoMarkerList:
							localContainerG30Bean = (ContainerG30Bean)msg.obj;
							doListMarker(localContainerG30Bean);
							break;
							
						case UTIL_GEO.NB_newGeoMarkerInsert:
							addMarker((G30Bean)msg.obj);
							break;
							
						case UTIL_GEO.NB_newGeoMarkerRemove:
							removeMarker((G30Bean)msg.obj);
							break;
							
						case UTIL_GEO.NB_newGeoMarkerUpdate:
							updateMarker((G30Bean)msg.obj);
						break;
							
						}
                    }
                });
			}
			catch (Exception e) 
			{
				Log.w(UTIL_GEO.MYGEO,  e);
			}
			
		}
	};

	@Override
	public void returnServiceResponse(Message msg) {
		handler.sendMessage(msg);
	}
	
	/**
	 * 
	 */
	
	private void doListMarker(ContainerG30Bean containerG30Bean)
	{
		try 
		{	
			for (G30Bean g30Bean : containerG30Bean.g30Beans) 
			{
				LinearLayout hiddenInnerLinearLayout = new LinearLayout(MyGeoActivity.this);
				hiddenInnerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
            	LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            	hiddenInnerLinearLayout.setLayoutParams(vp); 
            	hiddenInnerLinearLayout.setBackgroundColor(Color.TRANSPARENT);
            	
            	ImageView imageViewMarker = new ImageView(this);
       
            	imageViewMarker.setImageResource(g30Bean.getMarker()); 
            	
            	Button buttonText = new Button(this);
            	
            	buttonText.setTypeface(null, Typeface.BOLD_ITALIC);
            	buttonText.setTextColor(Color.WHITE);
            	buttonText.setTextSize(TypedValue.COMPLEX_UNIT_SP,(float)23.0);
            	buttonText.setText((CharSequence)g30Bean.getTitle());
            	buttonText.setOnClickListener(displayMarket(g30Bean.getId()));
            	buttonText.setBackgroundColor(Color.TRANSPARENT);
            	
            
				Button buttonX = new Button(this);
				
            	buttonX.setBackgroundResource(R.drawable.b3close); 
            	buttonX.setOnClickListener(deleteMarket(g30Bean.getId()));
            	
            	hiddenInnerLinearLayout.setId(g30Bean.getId());
            	
            	hiddenInnerLinearLayout.addView(imageViewMarker); 
            	hiddenInnerLinearLayout.addView(buttonText); 
            	hiddenInnerLinearLayout.addView(buttonX);
            	
            	hiddenLinearLayout.addView(hiddenInnerLinearLayout);
			}
			
			NotifyBean.removeElementsC_G30Bean(UTIL_GEO.NB_GeoMarkerList);
			NotifyBean.addElementsC_G30Bean(UTIL_GEO.NB_GeoMarkerList, containerG30Bean);
			
			if(CheckObj.check(containerG30Bean.g30Beans, UTIL_GEO.LIST))
			{
				hiddenLinearLayout.setVisibility(hiddenLinearLayout.VISIBLE);
			}
			else
				hiddenLinearLayout.setVisibility(hiddenLinearLayout.GONE);
			
		} 
		catch (Exception e) 
		{
			Log.e(UTIL_GEO.MYGEO, UTIL_GEO.MYGEO_ERROR, e);
		}
	}
	  
	/**
	 * 
	 * @param g30Bean
	 */
	
	private void updateMarker(G30Bean g30Bean) 
	{
		
		cleanList();
		
		for (G30Bean g30 : localContainerG30Bean.g30Beans) 
		{
			if(g30.getId() == g30Bean.getId())
			{
				g30.setLatitude(g30Bean.getLatitude());
				g30.setLongitude(g30Bean.getLongitude());
				g30.setTitle(g30Bean.getTitle());
			}
		}
		
		try 
		{
			EnginePersistFile.execEnginePersistFile(this).persist(localContainerG30Bean);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		doListMarker(localContainerG30Bean);
	}
	
	/**
	 * 
	 * @param g30Bean
	 * @throws Exception 
	 */
	
	private void addMarker(G30Bean g30Bean) 
	{
		
		cleanList();
		
		localContainerG30Bean.g30Beans.add(g30Bean);
		
		try 
		{
			EnginePersistFile.execEnginePersistFile(this).persist(localContainerG30Bean);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		doListMarker(localContainerG30Bean);
	}
	
	/**
	 * 
	 * @param Id
	 */
	
	private void removeMarker(G30Bean g30Bean)
	{

		localContainerG30Bean.g30Beans.remove(g30Bean);
		
		try 
		{
			EnginePersistFile.execEnginePersistFile(MyGeoActivity.this).persist(localContainerG30Bean);
			LinearLayout hiddenInnerLinearLayout = (LinearLayout)findViewById(g30Bean.getId());
			hiddenLinearLayout.removeView(hiddenInnerLinearLayout);
			hiddenLinearLayout.clearDisappearingChildren();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}			
	}
	
	/**
	 * 
	 */
	
	private void cleanList()
	{
		for (G30Bean g30Bean : localContainerG30Bean.g30Beans) 
		{
			hiddenLinearLayout.removeView(findViewById(g30Bean.getId()));
			hiddenLinearLayout.clearDisappearingChildren();
		}
	} 
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	private View.OnClickListener displayMarket(final int id)
	{
		return new View.OnClickListener()
		{
			private int  localId = id;
			
			@Override
			public void onClick(View paramView) 
			{	
				for (G30Bean g30Bean : localContainerG30Bean.g30Beans) 
				{
					if(g30Bean.getId() == localId)
					{
						Intent intent = new Intent(MyGeoActivity.this, MapStartG30Activity.class);
						int range = 1000;
						
						intent.putExtra(UTIL_GEO.RANGE, range);
						intent.putExtra(UTIL_GEO.TYPE_MARKER, g30Bean.getType());
						intent.putExtra(UTIL_GEO.UPDATE_G30, 
						g30Bean.getId() 					+ "|" +
						g30Bean.getMarker()			+ "|" +
						g30Bean.getTitle()				+ "|" +
						g30Bean.getLatitude()		+ "|" +
						g30Bean.getLongitude()		+ "|" +
						g30Bean.getType());
						
						MyGeoActivity.this.startActivity(intent);
					}
				}
			}
		};
	}
	
	
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	private View.OnClickListener deleteMarket(final int id)
	{
		return new View.OnClickListener()
		{
			private int  localId = id;
			
			@Override
			public void onClick(View paramView) 
			{	
				for (G30Bean g30Bean : localContainerG30Bean.g30Beans) 
				{
					if(g30Bean.getId() == localId)
					{
						ManageDialogHandler dialogHandler = new ManageDialogHandler();
						DialogElement dialogElement =  dialogHandler.createDialogElement(MyGeoActivity.this, MyGeoActivity.this , UTIL_GEO.SELECT_G30_DEL_MARKER , (Object)g30Bean);
						
						dialogElement.getDialog().show();
						return;
					}
				}
			}
		};
	}
	
}
