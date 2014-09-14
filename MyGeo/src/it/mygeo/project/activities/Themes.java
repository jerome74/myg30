package it.mygeo.project.activities;

//import it.wlp.android.themes.Utils;


import it.mygeo.project.R;
import it.wlp.android.themes.Utils;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * This activity's theme is set to a custom theme in the onCreate() method.
 * By default, there is no theme, but pressing the buttons will change the
 * theme dynamically.
 *
 * @author Matt Quigley
 *
 */
public class Themes extends Activity implements OnClickListener
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Utils.onActivityCreateSetTheme(this);
		setContentView(R.layout.main);

		findViewById(R.id.button1).setOnClickListener(this);
		findViewById(R.id.button2).setOnClickListener(this);
		findViewById(R.id.button3).setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		
		if (v.getId() ==  R.id.button1)
			Utils.changeToTheme(this, Utils.THEME_DEFAULT);
		else if (v.getId() ==  R.id.button2)
			Utils.changeToTheme(this, Utils.THEME_WHITE);
		else if (v.getId() ==  R.id.button3)
			Utils.changeToTheme(this, Utils.THEME_BLUE);

	}

}