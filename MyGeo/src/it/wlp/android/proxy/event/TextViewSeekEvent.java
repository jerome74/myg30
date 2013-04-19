package it.wlp.android.proxy.event;

import it.mygeo.project.activities.SeekActivity;
import it.wlp.android.proxy.domain.ProxyView;

import java.util.Observable;
import java.util.Observer;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

public class TextViewSeekEvent implements Observer
{
	TextView textView;
	ProxyView proxyView;
	
	View.OnClickListener dialogClickListener = new View.OnClickListener() 
   {
		public void onClick(View arg0) 
		{
			Intent intent = new Intent(proxyView.getContext(), SeekActivity.class);
			proxyView.getContext().startActivity(intent);
		}
   };

	public TextViewSeekEvent(View textView) 
	{
		super();
		this.textView = (TextView)textView;
	}

	public void update(Observable arg0, Object arg1) 
	{
		proxyView = (ProxyView)arg0;
		
		textView.setOnClickListener(dialogClickListener);
	}
}

