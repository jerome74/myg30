package it.wlp.android.dialog.element;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;

public interface DialogElement 
{
	 Dialog getDialog();
	 void createDialog();
	 void setContext(Context context);
	 void setActivity(Activity activity);
	 void setBuilder(Builder builder);
}
