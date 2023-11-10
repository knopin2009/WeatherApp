package com.example.weatherapp.utils;

import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class NetworkUtils {


    public static URL buildUrlOpenWeatherMap (String city){

        String BASE_URL="https://api.openweathermap.org/data/2.5/weather";
        String PARAM_CITY="q";
        String PARAM_APPID="appid";
        String appid_value="c45478f684d3d8bdba2f9d9f92d27891";

        Uri builtUri=Uri.parse(BASE_URL).buildUpon().appendQueryParameter(PARAM_CITY,city).appendQueryParameter(PARAM_APPID,appid_value).build();
        URL url = null;

        try {
            url=new URL (builtUri.toString());

        } catch (MalformedURLException e){
            e.printStackTrace();
        }
        //Log.d(TAG, "buildUrl: "+url);
        return url;

    }

}
