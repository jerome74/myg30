package it.wlp.android.proxy.event;

import it.mygeo.project.R;
import it.mygeo.project.activities.MapG30Activity;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.proxy.domain.ProxyView;
import it.wlp.android.toast.domain.ToastHelperDomain;
import it.wlp.android.toast.external.IToastHelper;
import it.wlp.android.toast.model.ToastHelper;

import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ButtonSearchEvent implements Observer ,  PreferenceCallBack
{
	
	private TextView textViewSearch;
	private ProxyView proxyView;
	private IToastHelper iToastHelper;
	private ToastHelperDomain toastHelperDomain;
	
	
	public ButtonSearchEvent(View textViewSearch) 
	{
		super();
		this.textViewSearch = (TextView)textViewSearch;
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
		textViewSearch.setOnClickListener(clickListener);
		 
		iToastHelper 		= new ToastHelper(proxyView.getContext());
		toastHelperDomain 	= new ToastHelperDomain(iToastHelper);
		
	}
	
	View.OnClickListener clickListener = new View.OnClickListener() 
	   {
			public void onClick(View arg0) 
			{
				
				if(!(((ToggleButton)proxyView.getActivity().findViewById(R.id.insertButton)).getText()).equals(proxyView.getActivity().getString(R.string.insert_on))
					&&	!(((ToggleButton)proxyView.getActivity().findViewById(R.id.findButton)).getText()).equals(proxyView.getActivity().getString(R.string.find_on))	)
				 {
					 toastHelperDomain.createToastMessage(R.string.check_search_button, R.drawable.stop);
				 }
				else
				{
					Intent intent = new Intent(proxyView.getContext(), MapG30Activity.class);
					proxyView.getContext().startActivity(intent);
				}
				 
			}
	   };


	@Override
	public void returnServiceResponse(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
