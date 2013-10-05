package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.activities.MyGeoActivity;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.system.bean.ContainerG30Bean;
import it.wlp.android.system.bean.G30Bean;
import it.wlp.android.system.fwork.EnginePersistFile;

import java.util.Observable;
import java.util.Observer;

import com.google.android.maps.MapView.LayoutParams;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Message;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MarkerFileEvent implements Observer
{
	EnginePersistFile enginePersistFile;
	LinearLayout linearLayout;
	ProxyView proxyView;
	ContainerG30Bean containerG30Bean;
	Message message;
	
	

	public MarkerFileEvent(View linearLayout) 
	{
		super();
		this.linearLayout = (LinearLayout) linearLayout;
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;

		try 
		{	
			containerG30Bean = EnginePersistFile.execEnginePersistFile(proxyView.getActivity()).read();
			
			doMessage();
			manageHiddenLinearLayout();
			
			NotifyBean.notifyEvent(UTIL_GEO.NB_MyGeoActivity, message);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
		
	private void  doMessage()
	{
		message = new Message();
		message.arg1 = UTIL_GEO.NB_newGeoMarkerList;
		message.obj =containerG30Bean;
	}
	
	private void manageHiddenLinearLayout()
	{
		((MyGeoActivity)proxyView.getActivity()).hiddenLinearLayout = linearLayout;
		LinearLayout.LayoutParams vp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		((MyGeoActivity)proxyView.getActivity()).hiddenLinearLayout.setLayoutParams(vp); 
		((MyGeoActivity)proxyView.getActivity()).hiddenLinearLayout.setBackgroundColor(Color.TRANSPARENT);
	}
}
