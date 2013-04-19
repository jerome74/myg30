package it.wlp.android.proxy.domain;

import it.mygeo.project.R;
import it.wlp.android.proxy.event.ButtonSearchEvent;
import it.wlp.android.proxy.event.ConnetionEvent;
import it.wlp.android.proxy.event.TextViewSeekEvent;
import it.wlp.android.proxy.event.TextViewSyleEvent;
import it.wlp.android.proxy.event.ToggleButtonEventFind;
import it.wlp.android.proxy.event.ToggleButtonEventInsert;
import it.wlp.android.proxy.external.IProxyView;

import java.util.Observable;

import android.app.Activity;
import android.content.Context;

public class ProxyView extends Observable implements IProxyView 
{
	Activity activity;
	Context context;
	
	
	
	public ProxyView(Activity activity, Context context) {
		super();
		this.activity = activity;
		this.context = context;
	}


	public void init() 
	{
		addObserver(new TextViewSyleEvent(activity.findViewById(R.id.value_style)));
		addObserver(new TextViewSeekEvent(activity.findViewById(R.id.value_seek)));
		addObserver(new ToggleButtonEventInsert(activity.findViewById(R.id.insertButton)));
		addObserver(new ToggleButtonEventFind(activity.findViewById(R.id.findButton)));
		addObserver(new ButtonSearchEvent(activity.findViewById(R.id.searchPropertiesButton)));
		addObserver(new ConnetionEvent());

		setChanged();
		notifyObservers();
	}


	public Activity getActivity() {
		return activity;
	}


	public Context getContext() {
		return context;
	}
}
