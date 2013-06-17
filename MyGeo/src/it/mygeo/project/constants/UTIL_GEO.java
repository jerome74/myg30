package it.mygeo.project.constants;

import it.mygeo.project.R;

import java.util.UUID;

import android.app.Activity;


public interface UTIL_GEO
{
	final static int MENU_EXIT 											= 0;
	final static int DIALOG_CATEGORY_TEXT_STYLE_VALUE 					= 1;
	final static int STYLE_PREFERENCE_ACTIVITY_VALUE  					= 2;
	final static int SELECT_MENU_EXIT 									= 3;
	final static int SELECT_G30_DESC 									= 4;
	
	final static String MYGEO 											= "My Geo";
	final static String IS_CONN_SERV 									= "true";
	final static String DEF_SEEK			 							= UUID.randomUUID().toString();
	final static String RANGE 											= UUID.randomUUID().toString();
	final static String PRE_LABEL_DIST 									= "in ";
	final static String SPACE				 							= " ";
	final static String TYPE_MARKER				 						= UUID.randomUUID().toString();
	final static int POST_LABEL_DIST_KM									= R.string.Km;
	final static int POST_LABEL_DIST_KMS								= R.string.Kms;
	final static int POST_LABEL_DIST_METRS 								= R.string.mts;
	final static int NB_ConnetionEvent 									= 1000;
	final static int NB_ListElementEvent 								= 1001;
	final static int NB_SeekBarDistanceEvent 							= 1002;
	final static int NB_newGeo				 							= 1003;
	
	
	
}
