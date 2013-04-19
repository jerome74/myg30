package it.wlp.android.widgets;

import it.mygeo.project.R;
import it.mygeo.project.activities.MyGeoActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
		mBackBtn = (Button) findViewById(R.id.title_home);
		mPowerBtn = (Button) findViewById(R.id.title_power);
		
		mPowerBtn.setOnClickListener(this);
		mBackBtn.setOnClickListener(this);
	}
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.title_power:
				activity.finish();
				break;
			case R.id.title_home:
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
}
