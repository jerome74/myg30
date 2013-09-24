package it.wlp.android.widgets;

import java.util.UUID;

import it.mygeo.project.R;
import it.mygeo.project.activities.MapG30Activity;
import it.mygeo.project.activities.MyGeoActivity;
import it.mygeo.project.constants.UTIL_GEO;
import it.mygeo.project.service.NotifyBean;
import it.wlp.android.system.bean.G30Bean;
import it.wlp.android.system.external.IG30;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Message;
import android.text.Html.ImageGetter;
import android.text.TextUtils.StringSplitter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleBar extends LinearLayout implements OnClickListener {

//	private static final String TAG = "TitleBar";
	private TextView mTitleView;
	private Button mBackBtn;
	private Button mPowerBtn;
	private Activity activity;
	private String exchangeValue;
	private boolean isGeo;
	private int marker;
	private String type;
	private G30Bean g30bean;
	
	
	public TitleBar(Context context) {
		super(context);
	}
	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this, true);
	}
	
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		
		mTitleView = (TextView) findViewById(R.id.title_title);
		mBackBtn = (Button) findViewById(R.id.title_confirm);
		mPowerBtn = (Button) findViewById(R.id.title_power);
		
		mPowerBtn.setOnClickListener(this);
		mBackBtn.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.title_power:
				activity.finish();
				break;
			case R.id.title_confirm: 
				if (isGeo) 
				{
					Message message = new Message();
					String strCM =String.valueOf(System.currentTimeMillis());
					
					if(getG30bean() == null)
					{
						int ID =  Integer.parseInt(strCM.substring( strCM.length() - 9 )) ;
						G30Bean ig30 = new G30Bean(ID
																	,getMarker()
																	,((MapG30Activity)activity).getLongitude()
																	,((MapG30Activity)activity).getLatitude()
																	,mTitleView.getText().toString()
																	,getType());
						
						message.arg1 = UTIL_GEO.NB_newGeoMarkerInsert;
						message.obj =ig30;
						NotifyBean.notifyEvent(UTIL_GEO.NB_MyGeoActivity, message);
					}
					else
					{
						G30Bean ig30 = new G30Bean(getG30bean().getId()
								,getG30bean().getMarker()
								,((MapG30Activity)activity).getLongitude()
								,((MapG30Activity)activity).getLatitude()
								,mTitleView.getText().toString()
								,getType());
						
						message.arg1 = UTIL_GEO.NB_MyGeoActivity;
						message.obj =ig30;
						NotifyBean.notifyEvent(UTIL_GEO.NB_newGeoMarkerUpdate, message);
					}
					
					
				}
				activity.finish();
				break;
			default:
				break;
		}
	}
	
	public Button getBackButton(){
		return mBackBtn;
	}
	
	public Button getPowerButton(){
		return mPowerBtn;
	}
	
	public void setTitle(int resid){
		mTitleView.setText(resid);
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public String getExchangeValue() {
		return exchangeValue;
	}
	public void setExchangeValue(String exchangeValue) {
		this.exchangeValue = exchangeValue;
	}
	public TextView getmTitleView() {
		return mTitleView;
	}
	public void setmTitleView(TextView mTitleView) {
		this.mTitleView = mTitleView;
	}
	public void isGeo() {
		isGeo = true;
	}
	public int getMarker() {
		return marker;
	}
	public void setMarker(int market) {
		this.marker = market;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public G30Bean getG30bean() {
		return g30bean;
	}
	public void setUpdateG30bean(String updateG30bean) 
	{
		String[] strG30bean =  updateG30bean.split("|");
		
		g30bean = new G30Bean();
		
		g30bean.setId(Integer.parseInt(strG30bean[0]));
		g30bean.setMarker(Integer.parseInt(strG30bean[1]));
		g30bean.setTitle(strG30bean[2]);
		g30bean.setLatitude(Double.parseDouble(strG30bean[3]));
		g30bean.setLongitude(Double.parseDouble(strG30bean[4]));
		g30bean.setType(strG30bean[5]);
		
	}
}
