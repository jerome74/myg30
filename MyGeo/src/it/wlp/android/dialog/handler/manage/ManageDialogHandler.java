package it.wlp.android.dialog.handler.manage;

import it.wlp.android.dialog.element.DialogElement;
import it.wlp.android.dialog.element.domain.DialogTextView;
import it.wlp.android.dialog.element.domain.MenuDialog;
import it.wlp.android.dialog.handler.DialogHandler;
import static it.mygeo.project.constants.UTIL_GEO.*;

public class ManageDialogHandler extends DialogHandler {

	@Override
	public DialogElement newElement(int id) 
	{
		switch (id) 
		{
		case DIALOG_CATEGORY_TEXT_STYLE_VALUE:
			return new DialogTextView();
			
		case SELECT_MENU_EXIT:
			return new MenuDialog();

		default:
			return null;

		}
	}

}
