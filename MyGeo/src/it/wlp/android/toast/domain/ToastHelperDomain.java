package it.wlp.android.toast.domain;

import it.wlp.android.toast.external.IToastHelper;

public class ToastHelperDomain {
	IToastHelper helper;

	public ToastHelperDomain(IToastHelper helper) 
	{
		super();
		this.helper = helper;
	}

	public void createToastMessage(int itText) 
	{
		this.helper.createToastMessage(itText);
	}

	public void createToastMessage(int itText, int iImage) 
	{
		this.helper.createToastMessage(itText, iImage);
	}
}
