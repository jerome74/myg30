package it.wlp.android.proxy.event;

import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_KM;
import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_KMS;
import static it.mygeo.project.constants.UTIL_GEO.POST_LABEL_DIST_METRS;
import static it.mygeo.project.constants.UTIL_GEO.PRE_LABEL_DIST;
import it.mygeo.project.R;
import it.mygeo.project.activities.MapG30Activity;
import it.mygeo.project.activities.MyGeoActivity;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
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
		NotifyBean.createEvent(UTIL_GEO.NB_MyGeoActivity, (MyGeoActivity)proxyView.getActivity());
		
	}
	
	View.OnClickListener clickListener = new View.OnClickListener() 
	   {
			public void onClick(View arg0) 
			{
				
				if(!(((ToggleButton)proxyView.getActivity().findViewById(R.id.insertButton)).getText()).equals(proxyView.getActivity().getString(R.string.insert_on))
					&&	!(((ToggleButton)proxyView.getActivity().findViewById(R.id.findButton)).getText()).equals(proxyView.getActivity().getString(R.string.insert_now_on))	)
				{
					 toastHelperDomain.createToastMessage(R.string.check_search_button, R.drawable.stop);
				}
				else if(((TextView)proxyView.getActivity().findViewById(R.id.value_seek)).getText().toString()
						.indexOf(proxyView.getActivity().getString(R.string.mygeo_label_distance)) != -1)
				{
						toastHelperDomain.createToastMessage(R.string.mygeo_label_distance, R.drawable.stop);
				}
				else if(((TextView)proxyView.getActivity().findViewById(R.id.value_style)).getText().toString()
						.indexOf(proxyView.getActivity().getString(R.string.mygeo_label_select)) != -1)
				{
						toastHelperDomain.createToastMessage(R.string.mygeo_label_select, R.drawable.stop);
				}
				else
				{
					Intent intent = new Intent(proxyView.getContext(), MapG30Activity.class);
					TextView textViewDistance = (TextView)proxyView.getActivity().findViewById(R.id.value_seek);
					CharSequence distance = textViewDistance.getText();
					int range = widthInPixels(distance.toString());
					
					intent.putExtra(UTIL_GEO.RANGE, range);
					intent.putExtra(UTIL_GEO.TYPE_MARKER, ((TextView)proxyView.getActivity().findViewById(R.id.value_style)).getText().toString());
					
					proxyView.getContext().startActivity(intent);
				}
				 
			}
			
			/**
			 * 
			 * @param valueWidth
			 * @return
			 */
			
			private int widthInPixels(String valueWidth)
			{
				try 
				{
					 if (valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_KM)) != -1)
					{
						return Integer.valueOf(1000);
					}
					else if (valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_KMS)) != -1)
					{
						String value = valueWidth.substring(PRE_LABEL_DIST.length(), valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_KMS)));
						return ((Integer.valueOf(value.trim())) * 1000);
					}
					else if (valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_METRS)) != -1)
					{
						String value = valueWidth.substring(PRE_LABEL_DIST.length(), valueWidth.indexOf(proxyView.getActivity().getString(POST_LABEL_DIST_METRS)));
						return Integer.valueOf(value.trim());
					}
					
				return 0;	
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
					return 100;
				}
			}
	   };


	@Override
	public void returnServiceResponse(Message msg) {
		// TODO Auto-generated method stub
		
	}

}
