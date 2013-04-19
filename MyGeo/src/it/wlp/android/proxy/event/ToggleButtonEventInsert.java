package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;

import java.util.Observable;
import java.util.Observer;

import android.view.View;
import android.widget.ToggleButton;

public class ToggleButtonEventInsert implements Observer ,  PreferenceCallBack
{
	
	private ToggleButton toggleButton;
	private ProxyView proxyView;
	private IToastHelper iToastHelper;
	private ToastHelperDomain toastHelperDomain;
	
	
	public ToggleButtonEventInsert(View toggleButton) 
	{
		super();
		this.toggleButton = (ToggleButton)toggleButton;
	}
	
	@Override
	public void returnServiceResponse() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		toggleButton.setOnClickListener(clickListener);
		 
		iToastHelper 		= new ToastHelper(proxyView.getContext());
		toastHelperDomain 	= new ToastHelperDomain(iToastHelper);
		
	}
	
	View.OnClickListener clickListener = new View.OnClickListener() 
	   {
			public void onClick(View arg0) 
			{
				
				 if((((ToggleButton)proxyView.getActivity().findViewById(R.id.findButton)).getText()).equals(proxyView.getActivity().getString(R.string.find_on)))
				 {
					 toastHelperDomain.createToastMessage(R.string.check_insert_on, R.drawable.stop);
					 toggleButton.setText(R.string.insert_off);
				 }
			}
	   };

}
