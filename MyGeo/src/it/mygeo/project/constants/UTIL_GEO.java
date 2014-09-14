package it.mygeo.project.constants;

import it.mygeo.project.R;

import java.util.UUID;

import android.app.Activity;

public interface UTIL_GEO {
	final static int MENU_EXIT = 0;
	final static int DIALOG_CATEGORY_TEXT_STYLE_VALUE 	= 1;
	final static int STYLE_PREFERENCE_ACTIVITY_VALUE 	= 2;
	
	final static int SELECT_MENU_EXIT			= 3;
	final static int SELECT_G30_DESC 			= 4;
	final static int SELECT_G30_DEL_MARKER 		= 5;

	final static String MYGEO_DIR 				= "MyGeo";
	final static String MYGEO_FILE 				= "MyGeo_File.xml";
	final static String MYGEO 					= "My Geo";
	final static String IS_CONN_SERV 			= "true";
	final static String DEF_SEEK 				= UUID.randomUUID().toString();
	final static String RANGE 					= UUID.randomUUID().toString();
	final static String PRE_LABEL_DIST 			= "in ";
	final static String SPACE 					= " ";
	final static String TYPE_MARKER 			= UUID.randomUUID().toString();
	final static String UPDATE_G30 				= UUID.randomUUID().toString();
	final static int POST_LABEL_DIST_KM 		= R.string.Km;
	final static int POST_LABEL_DIST_KMS 		= R.string.Kms;
	final static int POST_LABEL_DIST_METRS 		= R.string.mts;
	final static String VALUE_SEEK 					= "VALUE_SEEK";
	
	final static int NB_ConnetionEvent 			= 1000;
	final static int NB_ListElementEvent 		= 1001;
	final static int NB_SeekBarDistanceEvent 	= 1002;
	final static int NB_MyGeoActivity 			= 1003;
	final static int NB_newGeoMarkerInsert 		= 1004;
	final static int NB_newGeoMarkerRemove 		= 1005;
	final static int NB_newGeoMarkerUpdate 		= 1006;
	final static int NB_newGeoMarkerList 		= 1007;
	final static int NB_GeoMarkerList 			= 1008;
	final static int NB_VoiceStyle 				= 1009;
	final static int NB_VoiceSeek 				= 1010;
	
	final static String EXTRA_PROMPT			= "EXTRA_PROMPT";
	final static String NB_TO_NOTIFY 			= "NB_TO_NOTIFY";
	final static String NB_VoiceStyle_NOTIFY 	= "NB_VoiceStyle_NOTIFY";
	final static String NB_VoiceSeek_NOTIFY 	= "NB_VoiceSeek_NOTIFY";
	
	final static String MYGEO_ERROR = "ERROR";
	

	final static int LIST = 2001;
	final static int ARRAY = 2002;
	final static int STRING = 2003;
	final static int INT = 2004;

}
