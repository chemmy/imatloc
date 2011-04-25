package imatloc.com;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;

public class MapLocTab extends MapActivity {
	private MapView mapView;
	private MyLocationOverlay whereAmI = null;

	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maptab);
        
        mapView = (MapView)findViewById(R.id.mapview);
        
        mapView.setBuiltInZoomControls(true);
        
        /*
        Drawable marker = getResources().getDrawable(R.drawable.icon);
        marker.setBounds(0, 0, marker.getIntrinsicWidth(),
        marker.getIntrinsicHeight());
        InterestingLocations funPlaces = new InterestingLocations(marker);
        mapView.getOverlays().add(funPlaces);
        GeoPoint pt = funPlaces.getCenter();
        mapView.getController().setCenter(pt);
        */
        
        Drawable marker = getResources().getDrawable(R.drawable.icon);
        marker.setBounds(0, 0, marker.getIntrinsicWidth(),
        marker.getIntrinsicHeight());
        InterestingLocations funPlaces = new InterestingLocations(marker);
        Geocoder geocoder = new Geocoder(this);
        try {
			List<Address> addressList =
				geocoder.getFromLocationName("Qin Zhou Bei Lu 1122号China Shanghai Xu Hui Qu", 5);
			
			if(addressList!=null && addressList.size()>0)
			{
			    int lat = (int)(addressList.get(0).getLatitude()*1000000);
			    int lng = (int)(addressList.get(0).getLongitude()*1000000);
			    GeoPoint pt = new GeoPoint(lat,lng);
			    
			    Log.v("Position", String.format(("lat %d, lng %d"), lat, lng));
			    
			    OverlayItem office = new OverlayItem(pt, null, null);
			    funPlaces.AddItem(office);
			    mapView.getOverlays().add(funPlaces);
			    
			    //mapView.getController().setZoom(15);
			    //mapView.getController().setCenter(pt);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


        
        mapView.getController().setZoom(15);
        
        whereAmI = new MyLocationOverlay(this, mapView);
        mapView.getOverlays().add(whereAmI);
        mapView.postInvalidate();
    }
	
	@Override
	public void onResume()
	{
	    super.onResume();
	    whereAmI.enableMyLocation();
	    whereAmI.runOnFirstFix(new Runnable() {
	        public void run() {
	        	mapView.getController().setCenter(whereAmI.getMyLocation());
	        }
	    });
	}
	
	@Override
	public void onPause()
	{
	    super.onPause();
	    whereAmI.disableMyLocation();
	}


	@Override
	protected boolean isLocationDisplayed() {
		return whereAmI.isMyLocationEnabled();
	}

	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	class InterestingLocations extends ItemizedOverlay {
		private List<OverlayItem> locations = new ArrayList<OverlayItem>();
		private Drawable marker;
		
		public InterestingLocations(Drawable marker)
		{
		    super(marker);
		    this.marker=marker;
		    // create locations of interest
		    /*
		    GeoPoint disneyMagicKingdom = new
		    GeoPoint((int)(28.418971*1000000),(int)(-81.581436*1000000));
		    GeoPoint disneySevenLagoon = new
		    GeoPoint((int)(28.410067*1000000),(int)(-81.583699*1000000));
		    locations.add(new OverlayItem(disneyMagicKingdom ,
		         "Magic Kingdom", "Magic Kingdom"));
		    locations.add(new OverlayItem(disneySevenLagoon ,
		         "Seven Lagoon", "Seven Lagoon"));
		    populate();
		    */
		}
		
		@Override
		public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		    super.draw(canvas, mapView, shadow);
		    boundCenterBottom(marker);
		}
		
		@Override
		protected OverlayItem createItem(int i) {
		    return locations.get(i);
		}
		
		@Override
		public int size() {
		    return locations.size();
		}
		
		public void AddItem(OverlayItem oi){
			locations.add(oi);
			populate();
		}
	}

}
