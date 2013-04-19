package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;

import java.util.Observable;
import java.util.Observer;

import android.os.Message;
import android.view.View;
import android.widget.ToggleButton;

public class ToggleButtonEventFind implements Observer ,  PreferenceCallBack
{
	
	private ToggleButton toggleButton;
	private ProxyView proxyView;
	private IToastHelper iToastHelper;
	private ToastHelperDomain toastHelperDomain;
	
	
	public ToggleButtonEventFind(View toggleButton) 
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
				
				if((((ToggleButton)proxyView.getActivity().findViewById(R.id.insertButton)).getText()).equals(proxyView.getActivity().getString(R.string.insert_on)))
				 {
					 toastHelperDomain.createToastMessage(R.string.check_find_on, R.drawable.stop);
					 toggleButton.setText(R.string.find_off);
				 }
				 
			}
	   };


	@Override
	public void returnServiceResponse(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
