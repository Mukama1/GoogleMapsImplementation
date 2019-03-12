package com.ewalltech.apps.mapimplementation;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GeocodingLocation {

    private static final String TAG = "GeocodingLocation";

    public static ArrayList getAddressFromLocation(final String locationAddress, final Context context) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        ArrayList coordinatesArrayList=new ArrayList();
        String result = null;
        try {
            List<Address> addressList = geocoder.getFromLocationName(locationAddress, 1);
            if (addressList.size() > 0) {
                Address address = addressList.get(0);
                StringBuilder sb = new StringBuilder();
                coordinatesArrayList.add(address.getLatitude());
                coordinatesArrayList.add(address.getLongitude());
                sb.append(address.getLatitude()).append(",");
                sb.append(address.getLongitude());
                Log.e("Latitude is:", "" + address.getLatitude());
                Log.e("Longitude is:", "" + address.getLongitude());
                result = sb.toString();
            }
        } catch (IOException e) {
            Log.e(TAG, "Unable to connect to Geocoder", e);
        }
        return coordinatesArrayList;
    }

}