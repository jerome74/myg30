package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.wlp.android.widgets.ArrayListFragment;
import it.wlp.android.widgets.TitleBar;
import android.app.Activity;
import android.os.Bundle;

public class ActivityListArray extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourite_properties_list);
        
        TitleBar bar = (TitleBar)findViewById(R.id.titleBar);
        bar.setActivity(this);
        bar.setTitle(R.string.mygeo_label_select);
        
    }


}