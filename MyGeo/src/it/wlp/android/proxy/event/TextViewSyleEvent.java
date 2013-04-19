package it.wlp.android.proxy.event;

import it.mygeo.project.activities.ActivityListArray;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;

import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class TextViewSyleEvent implements Observer  ,  PreferenceCallBack
{
	TextView textView;
	ProxyView proxyView;
	String currentValue;
	
	
	View.OnClickListener dialogClickListener = new View.OnClickListener() 
   {
		public void onClick(View arg0) 
		{
			Intent intent = new Intent(proxyView.getContext(), ActivityListArray.class);
			proxyView.getContext().startActivity(intent);
			
		}
   };

	public TextViewSyleEvent(View textView) 
	{
		super();
		this.textView = (TextView)textView;
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		
		textView.setOnClickListener(dialogClickListener);
		NotifyBean.createEvent(UTIL_GEO.NB_ListElementEvent, this);
	}
	

	@Override
	public void returnServiceResponse(Message msg) 
	{
		handler.sendMessage(msg);
	}
	
	@Override
	public void returnServiceResponse() 
	{
		handler.sendEmptyMessage(0);
	}
	
	/**
	 * 
	 */
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			final Message msg1 = msg;
			try 
			{
				proxyView.getActivity().runOnUiThread(new Runnable() 
                {
                    public void run() 
                    {
                    		textView.setText((String)msg1.obj);
                    }
                });
			}
			catch (Exception e) 
			{
				Log.w(UTIL_GEO.MYGEO,  e);
			}
		}
	};
}

