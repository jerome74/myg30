package it.wlp.android.map;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class SitesOverlay extends ItemizedOverlay<OverlayItem>
{
	
	private GoogleMap map;
	
	public SitesOverlay(Drawable marker, double lat, double lon, String item ,GoogleMap mMap) 
	{
		super(marker);
		items.add(new OverlayItem(getPoint(lat,
				lon),
				item, "my location"));
		
		populate();
	}

	private List<OverlayItem> items=new ArrayList<OverlayItem>();
    private Drawable marker=null;
    private OverlayItem inDrag=null;
    private ImageView dragImage=null;
    private int xDragImageOffset=0;
    private int yDragImageOffset=0;
    private int xDragTouchOffset=0;
    private int yDragTouchOffset=0;

	@Override
	protected OverlayItem createItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public void draw(Canvas canvas, MapView mapView,
                      boolean shadow) {
      super.draw(canvas, mapView, shadow);
      
      boundCenterBottom(marker);
    }
	
	@Override
	public int size() {
		return(items.size());
	}
	
	 private GeoPoint getPoint(double lat, double lon) {
		    return(new GeoPoint((int)(lat*1000000.0),
		                          (int)(lon*1000000.0)));
		  }
	 
	  @Override
	    public boolean onTouchEvent(MotionEvent event, MapView mapView) {
	      final int action=event.getAction();
	      final int x=(int)event.getX();
	      final int y=(int)event.getY();
	      boolean result=false;
	      
	      if (action==MotionEvent.ACTION_DOWN) {
	        for (OverlayItem item : items) 
	        {  
	          Point p= map.getProjection().toScreenLocation(new LatLng(item.getPoint().getLatitudeE6() / 1E6
	        		  																							, item.getPoint().getLongitudeE6() / 1E6));
	          
	          if (hitTest(item, marker, x-p.x, y-p.y)) {
	            result=true;
	            inDrag=item;
	            items.remove(inDrag);
	            populate();

	            xDragTouchOffset=0;
	            yDragTouchOffset=0;
	            
	            setDragImagePosition(p.x, p.y);
	            dragImage.setVisibility(View.VISIBLE);

	            xDragTouchOffset=x-p.x;
	            yDragTouchOffset=y-p.y;
	            
	            break;
	          }
	        }
	      }
	      else if (action==MotionEvent.ACTION_MOVE && inDrag!=null) 
	      {
	        setDragImagePosition(x, y);
	        result=true;
	      }
	      else if (action==MotionEvent.ACTION_UP && inDrag!=null) 
	      {
	        dragImage.setVisibility(View.GONE);
	        
	        GeoPoint pt = new GeoPoint((int)((x-xDragTouchOffset) * 1E6) , (int)((y-yDragTouchOffset) * 1E6));
	        
	        OverlayItem toDrop=new OverlayItem(pt, inDrag.getTitle(),
	                                           inDrag.getSnippet());
	        
	        items.add(toDrop);
	        populate();
	        
	        inDrag=null;
	        result=true;
	      }
	      
	      return(result || super.onTouchEvent(event, mapView));
	    }
	    
	    private void setDragImagePosition(int x, int y) {
	      RelativeLayout.LayoutParams lp=
	        (RelativeLayout.LayoutParams)dragImage.getLayoutParams();
	            
	      lp.setMargins(x-xDragImageOffset-xDragTouchOffset,
	                      y-yDragImageOffset-yDragTouchOffset, 0, 0);
	      dragImage.setLayoutParams(lp);
	    }

}
