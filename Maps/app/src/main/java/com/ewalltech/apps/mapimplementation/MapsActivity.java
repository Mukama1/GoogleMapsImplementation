package com.ewalltech.apps.mapimplementation;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        GeocodingLocation locationAddress = new GeocodingLocation();
        ////TODO::: Testing static implementation
        String [] locations={"Dallas","Texas","London","Tokyo","Kampala","Newyork",
                "Washington","Oregon","Berlin","Paris","Lagos","Quebec","Nairobi","Capetown","Silicon Valley",
                "Boston","Chicago","Puerto rico","Los angeles","Las vegas","Kansas","Ohio"};

        String []  language={"Jupyter NoteBook","Java","Python","MakeFile","Python","Objective C","Python","Javascript",
                "Python","Action Script","Java Script","HTML","Java Script","Java Script","Objective C","Java Script","Ruby",
                "Jupyter Notebook","Javascript","Python","Python","MakeFile"};


        String [] usernames={"ludoa888","Glucosio","Jg-fisher","openaps","Flameeyes","Research Kit","push-things",
                "bewest","xingyuezhiji","spikeup","intellina","tedpool-org","Rytiggy","Zxweei","benrudolph",
                "jphall663","LCJHust","Diabetik","Andrewwlong","Whatduck","kthouz","jxx123"};

        for(int i=0;i<locations.length;i++) {
            ArrayList coordinatesArrayList;
            coordinatesArrayList=locationAddress.getAddressFromLocation(locations[i], getApplicationContext());
            if(coordinatesArrayList.size()>1) {
                //Toast.makeText(getApplicationContext(), "" + coordinatesArrayList.get(0), Toast.LENGTH_LONG).show();

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(Double.parseDouble(""+coordinatesArrayList.get(0)),Double.parseDouble(""+coordinatesArrayList.get(1))))
                        .title(usernames[i])
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.you))
                        .snippet(language[i]));
            }
        }
        // Add a marker in Sydney and move the camera
        LatLng Dallas  = new LatLng(32.777861, -96.794758);
        mMap.addMarker(new MarkerOptions().position(Dallas).title("Dallas, USA"));

        // mMap.moveCamera(CameraUpdateFactory.newLatLng(Dallas));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32.777861, -96.794758), 5.0f));

    }
////TODO::: Return the location coordinates based on the location name

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            //latLongTV.setText(locationAddress);
//            Toast.makeText(getApplicationContext(), locationAddress, Toast.LENGTH_LONG).show();
            showCoordinates(locationAddress);
        }
        public String showCoordinates(String mapCoordinates){
            return mapCoordinates;
        }
    }
    public ArrayList coordinatesDouble(String coordinates){

        ArrayList<Double> arrayList = new ArrayList<>();
        String[] coordinatesArray=null;
        if(coordinates!="") {
            coordinatesArray = coordinates.trim().split("\\,");
            arrayList.add(Double.parseDouble(coordinatesArray[0]));
            arrayList.add(Double.parseDouble(coordinatesArray[1]));
        }else{
            Toast.makeText(this, "Empty String", Toast.LENGTH_LONG).show();
        }
        return  arrayList;


    }
}