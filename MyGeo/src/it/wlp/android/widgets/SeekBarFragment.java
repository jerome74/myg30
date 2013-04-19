package it.wlp.android.widgets;

import it.mygeo.project.R;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarFragment extends DialogFragment implements SeekBar.OnSeekBarChangeListener {

	private TitleBar bar;
    private SeekBar mSeekBar;
    private TextView textViewCurrentValue;
    
    private Activity activity;

    public SeekBarFragment() 
    {
	}
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup content, Bundle savedInstanceState) 
    {
        View view 						= inflater.inflate(R.layout.distance, content, false);
        mSeekBar 						= (SeekBar) view.findViewById(R.id.seekBar);
        textViewCurrentValue 	= (TextView)view.findViewById(R.id.curentValue);
        bar 									 = (TitleBar)view.findViewById(R.id.titleBarS);
        
		bar.setTitle(R.string.mygeo_label_distance);
	    bar.setActivity(activity);
	    mSeekBar.setOnSeekBarChangeListener(this);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return view;
    }

    /** {@inheritDoc} */
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) 
    {
    	if(progress == 1)
    		textViewCurrentValue.setText("in 1 metro");
    	else if(progress > 1 &&  progress <= 50)
    		textViewCurrentValue.setText("in "  + progress + " metri" );
    	else if(progress > 51 &&  progress <= 60)
    		textViewCurrentValue.setText("in "  + (progress - 50) + " kilometri" );
    	else if(progress > 61 &&  progress <= 70)
    		textViewCurrentValue.setText("in 15 kilometri" );
    	else if(progress > 71 &&  progress <= 80)
    		textViewCurrentValue.setText("in 20 kilometri" );
    	else if(progress > 81 &&  progress <= 90)
    		textViewCurrentValue.setText("in 25 kilometri" );
    	else if(progress > 90)
    		textViewCurrentValue.setText("in 30 kilometri" );
    }

    /** {@inheritDoc} */
    public void onStartTrackingTouch(SeekBar seekBar) 
    {
    }

    /** {@inheritDoc} */
    public void onStopTrackingTouch(SeekBar seekBar) 
    {
    }

	public void setActivity(Activity activity) {
		this.activity = activity;
	}
    
    
    
}
