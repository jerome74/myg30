package it.mygeo.project.activities;

import it.mygeo.project.R;
import it.wlp.android.widgets.SeekBarFragment;
import it.wlp.android.widgets.TitleBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public class SeekActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
        showEditDialog();
	}

    private void showEditDialog() {
        FragmentManager fm = getSupportFragmentManager();
        SeekBarFragment seekBarFragment = new SeekBarFragment();
        seekBarFragment.setActivity(this);
        seekBarFragment.show(fm, "");
    }
}