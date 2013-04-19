package it.wlp.android.dialog.handler;

import it.wlp.android.dialog.element.DialogElement;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;

public abstract class DialogHandler 
{

	public DialogElement createDialogElement(Context context, Activity activity , int id ) 
	{
		Builder builder = new AlertDialog.Builder(context);
		
		   DialogElement element = newElement( id );
		   element.setBuilder( builder );
		   element.setContext(context);
		   element.setActivity(activity);
		   element.createDialog();
		   return element;
		}
		public abstract DialogElement newElement(int id);
}
