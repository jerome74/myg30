package it.wlp.android.widgets;

import it.mygeo.project.R;
import it.mygeo.project.constants.UTIL_GEO;
import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ArrayListFragment extends ListFragment 
{

	Drawable drawable;
	View view;
	String selectedValue;
	String startSelectedValue;
	
	public ArrayListFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("deprecation")
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Resources r = getResources();
        String[] bases = r.getStringArray(R.array.geo_obj);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, bases));
        
        startSelectedValue = super.getActivity().getIntent().getStringExtra(UTIL_GEO.VALUE_SEEK);
        
        for (int i = 0; i < bases.length; i++) 
        {
        	String nowValue = (String)getListAdapter().getItem(i);
        	
        	if(nowValue.equals(startSelectedValue))
        	{
//        		TextView txView = (TextView)getListAdapter().getView(i, null, null);
//        		String value = txView.getText().toString();
//        		
//        		txView.setBackgroundColor(Color.BLUE);
//        		txView.setTextColor(Color.BLUE);
        		
        		getListView().setItemChecked(i, true);
        	}
        		
		}
    }

    @SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
	}

	@Override
    public void onListItemClick(ListView l, View v, int position, long id) 
    {
    	selectedValue = (String) getListAdapter().getItem(position);
    	drawable = v.getBackground();
    	 v.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.BLUE));
    	 
    	 if(view != null)
    	 {
    		 view.setBackgroundDrawable(drawable);
    	 }
    	 view = v;
    	 
    	 super.getActivity().finish();
    }

    /**
     * 
     * @return
     */
	public String getSelectedValue() {
		return selectedValue;
	}
}
