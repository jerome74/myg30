package it.wlp.android.widgets;

import it.mygeo.project.R;
import android.app.ListFragment;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArrayListFragment extends ListFragment 
{

	Drawable drawable;
	View view;
	String selectedValue;
	
	
	public ArrayListFragment() {
		// TODO Auto-generated constructor stub
	}
	
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Resources r = getResources();
        String[] bases = r.getStringArray(R.array.geo_obj);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, bases));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	selectedValue = (String) getListAdapter().getItem(position);
    	drawable = v.getBackground();
    	 v.setBackgroundDrawable(new ColorDrawable(0xff0000a0) );
    	 
    	 if(view != null)
    	 {
    		 view.setBackgroundDrawable(drawable);
    	 }
    	 view = v;
    }

    /**
     * 
     * @return
     */
	public String getSelectedValue() {
		return selectedValue;
	}
}
