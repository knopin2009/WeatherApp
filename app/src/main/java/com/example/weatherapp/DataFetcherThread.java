package com.example.weatherapp;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetcherThread extends Thread{

    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final String  TAG = "WeatherApp";

    private Handler mHandler;

    private URL url;
    public DataFetcherThread(Handler handler, URL url){

        this.mHandler = handler;
        this.url = url;
    }

    @Override
    public void run() {

        try {
            //URL url = new URL("https://example.com");
            //URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){

                builder.append(line).append("\n");
            }
            reader.close();
            Message message = mHandler.obtainMessage(SUCCESS, builder.toString());
            mHandler.sendMessage(message);


        } catch (Exception e){

            e.printStackTrace();
            Log.e(TAG, "run: "+ e.getMessage());
            mHandler.sendEmptyMessage(ERROR);

        }
    }
}
