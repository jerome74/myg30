package it.wlp.android.dialog.element.domain;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.system.bean.G30Bean;
import android.app.Activity;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DelMarkerDialog   implements DialogElement 
{ 
	Builder builder;
	Context context;
	Activity activity;
	Dialog dialog;
	G30Bean g30Bean;
	Message message;
	
	public Dialog getDialog() 
	{
		this.dialog = builder.create();
		return dialog;
	}

	public void createDialog() 
 {

		View view = View.inflate(this.context, R.layout.menu_adv_marker_del, null);
		TextView textView = (TextView)view.findViewById(R.id.geotxtext);
		
		Resources res = this.context.getResources();
		String text = String.format(res.getString(R.string.geo_del_marker), g30Bean.getTitle());
		
		textView.setText(text);

		final Button b_si = (Button) view.findViewById(R.id.ui_si);

		b_si.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				doMessage();
				NotifyBean.notifyEvent(UTIL_GEO.NB_MyGeoActivity, message);
				dialog.cancel();
			}
		});
		
		final Button b_no = (Button) view.findViewById(R.id.ui_no);

		b_no.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dialog.cancel();
			}
		});

		 builder.setView(view);
	     builder.setCancelable(false);
		
	}
	
	

	public void setBuilder(Builder builder) 
	{
		this.builder = builder;
	}

	@Override
	public void setContext(Context context) 
	{
		this.context = context;
		
	}

	@Override
	public void setActivity(Activity activity) 
	{
		this.activity = activity;
	}

	@Override
	public void createDialog(View textView) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setExtraObj(Object object) 
	{
		g30Bean = (G30Bean)object;	
	}
	
	private void  doMessage()
	{
		message = new Message();
		message.arg1 = UTIL_GEO.NB_newGeoMarkerRemove;
		message.obj =g30Bean;
	}
	
}
