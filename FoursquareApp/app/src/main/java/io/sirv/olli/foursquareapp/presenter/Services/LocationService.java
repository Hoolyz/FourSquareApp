package io.sirv.olli.foursquareapp.presenter.Services;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.*;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

/**
 * Created by Olli-Pekka on 18/11/2017.
 */

public class LocationService {

    /*

    Gets current location of the user

     */



    LocationManager locationManager;

    LocationListener locationListener;

    String currentLocation = "";

    Location startLocation;

    public String getLocationService(Activity activity,Context context)
    {

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {


                currentLocation = "" + location.getLatitude() + "," + location.getLongitude() + "";


            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };


        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            // ask for permission

            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {

            // we have permission!

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }

        boolean network_enabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (network_enabled) {

            startLocation = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            if (startLocation != null) {
                currentLocation = "" + startLocation.getLatitude() + "," + startLocation.getLongitude() + "";
                Log.i("startLocation", currentLocation);
            }
        }

        return  currentLocation;

    }

}
