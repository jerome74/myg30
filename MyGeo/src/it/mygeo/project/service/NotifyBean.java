package it.mygeo.project.service;

import it.mygeo.project.handler.ServiceHandler;
import it.mygeo.project.service.external.PreferenceCallBack;
import it.wlp.android.system.bean.ContainerG30Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.os.Message;
import android.util.Log;


public class NotifyBean
{
	static Map<Integer,ServiceHandler>  serviceHandlers = new HashMap<Integer,ServiceHandler>();
	static Map<Integer,PreferenceCallBack>  preferenceCallBacks = new HashMap<Integer,PreferenceCallBack>();
	static Map<Integer,ContainerG30Bean>  managedElementsC_G30Bean = new HashMap<Integer,ContainerG30Bean>();
	
	/**
	 * 
	 * @param force
	 * @param preferenceCallBack
	 * @return
	 */
	
	public static void createHandle(int idNotify, ServiceHandler serviceHandler)
	{	
		
		
		if(serviceHandlers.size() > 0)
		{
			if(serviceHandlers.get(idNotify) == null )
				serviceHandlers.put(idNotify ,serviceHandler);
		}
		else
		{
			serviceHandlers.put(idNotify ,serviceHandler);
		}
	}
	
	/**
	 * 
	 * @param idNotify
	 * @param preferenceCallBack
	 */
	
	public static void createEvent(int idNotify, PreferenceCallBack preferenceCallBack)
	{
		preferenceCallBacks.put(idNotify ,preferenceCallBack);
	}
	
	/**
	 * 
	 */
	
	public static void removeEvent(int idNotify)
	{	
		preferenceCallBacks.remove(idNotify);
	}
	
	
	/**
	 * 
	 * @param idNotify
	 * @param containerG30Bean
	 */
	
	public static void addElementsC_G30Bean(int idElements, ContainerG30Bean containerG30Bean)
	{
		managedElementsC_G30Bean.put(idElements ,containerG30Bean);
	}
	
	/**
	 * 
	 * @param idElements
	 * @return
	 */
	
	public static ContainerG30Bean getElementsC_G30Bean(int idElements)
	{	
		return managedElementsC_G30Bean.get(idElements);
	}
	
	/**
	 * 
	 * @param idElements
	 */
	
	public static void removeElementsC_G30Bean(int idElements)
	{	
		managedElementsC_G30Bean.remove(idElements);
	}
	
	/**
	 * 
	 */
	
	public static void clean()
	{
		serviceHandlers.clear();
		preferenceCallBacks.clear();
		managedElementsC_G30Bean.clear();
	}
	
	/**
	 * 
	 * @param message
	 */
	
	public static void  notifyHandler(int idHandler, Message message)
	{
		serviceHandlers.get(idHandler).sendMessage(message);
	}
	
	/**
	 * 
	 * @param idEvent
	 * @param message
	 */
	
	public static void  notifyEvent(int idEvent)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		preferenceCallBacks.get(idEvent).returnServiceResponse();
	}
	
	public static void  notifyEvent(int idEvent, Message message)
	{
		Log.d("############", preferenceCallBacks.toString());
		Log.d("############", "" +preferenceCallBacks.size());
		preferenceCallBacks.get(idEvent).returnServiceResponse(message);
	}
	
}
