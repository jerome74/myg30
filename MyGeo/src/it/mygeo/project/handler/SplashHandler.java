package it.mygeo.project.handler;

import it.mygeo.project.R;
import it.mygeo.project.activities.MyGeoActivity;
import it.mygeo.project.service.external.PreferenceCallBack;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ProgressBar;

public class SplashHandler extends Handler 
{
	
	private Context context;
	private Activity activity;
	
	
	
	public SplashHandler(Context context , Activity activity) 
	{
		super();
		this.context = context;
		this.activity = activity;
	}



	public void handleMessage(Message msg) 
 {
		super.handleMessage(msg);

		Intent intent = new Intent();

		switch (msg.what) {
		case 0:
			intent.setClass(this.context, MyGeoActivity.class);
			break;

		default:
			break;
		}

		this.context.startActivity(intent);
		this.activity.finish();

	}	
}
