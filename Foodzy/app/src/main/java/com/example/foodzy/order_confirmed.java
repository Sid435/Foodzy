package com.example.foodzy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class order_confirmed extends AppCompatActivity {
    private MapView mapView;
    private GoogleMap googleMap;
    Double latitude,longitude;
    TextView tvDT,tvDeliveryUpdate,tvOrderDelivered;
    long time;
    Double travelTimeInHours;
    int travelTimeInMinutes;
    ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmed);
        tvDT = findViewById(R.id.tvTimeDist);
        tvDeliveryUpdate = findViewById(R.id.tvDeliveryupdate);
        tvOrderDelivered = findViewById(R.id.orderDelivered);
        tvOrderDelivered.setVisibility(View.INVISIBLE);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar3);
        mProgressBar.setProgress(i);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("Delivery address", Context.MODE_PRIVATE);
        Boolean Delivery_location_taken = sp.getBoolean("delivery location taken",false);
        Boolean Delivery_location_taken_frm_map = sp.getBoolean("delivery location taken from map",false);

        if(Delivery_location_taken){
            String final_delivery_address = sp.getString("final delivery address","");
            LatLng point = getLocationFromAddress(order_confirmed.this, final_delivery_address);
            latitude = point.latitude;
            longitude = point.longitude;
        }
        else{
            String[] locationArray = sp.getString("final delivery point", "").split(",");
            String Lat = locationArray[0];
            String Longi = locationArray[1];// use trim() method
            latitude = Double.parseDouble(Lat);
            longitude = Double.parseDouble(Longi);
        }
        LatLng delivery_point = new LatLng(latitude, longitude);
        LatLng restaurant_marker = new LatLng(latitude + 0.03, longitude + 0.03);
//        distance = SphericalUtil.computeDistanceBetween(restaurant_marker, delivery_point);
//        Double speed = 25.0;
//
//        time = distance/speed + 14.0;
//        String travel_time = String.valueOf((travelTimeInSeconds));
        double d = distance (latitude,longitude,latitude+0.03,longitude+0.03);
        Random r = new Random();
        int speed = r.nextInt(45 - 20) + 20;
        travelTimeInHours = d/speed;
        travelTimeInMinutes = (int) (travelTimeInHours*60 + 18);
        tvDT.setText(""+travelTimeInMinutes+" minutes");
        progressBarTimer();

    }
    public LatLng getLocationFromAddress(Context context, String inputtedAddress) {

        Geocoder coder = new Geocoder(context);
        List<Address> address;
        LatLng resLatLng = null;

        try {
            // May throw an IOException
            address = coder.getFromLocationName(inputtedAddress, 5);
            if (address == null) {
                return null;
            }

            if (address.size() == 0) {
                return null;
            }

            Address location = address.get(0);
            location.getLatitude();
            location.getLongitude();

            resLatLng = new LatLng(location.getLatitude(), location.getLongitude());

        } catch (IOException ex) {

            ex.printStackTrace();
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_LONG).show();
        }

        return resLatLng;
    }

    public void track_order(View view){
//        LatLng delivery_point = new LatLng(latitude, longitude);
//        LatLng restaurant_marker = new LatLng(latitude + 0.03, longitude + 0.03);
//        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?saddr=%f,%f(%s)&daddr=%f,%f (%s)", latitude, longitude, "Delivery Location", latitude+0.03, longitude+0.03, "FOODZY Restaurant");
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
//        intent.setPackage("com.google.android.apps.maps");
//        intent.getStringExtra("duration");
//        startActivity(intent);
        Intent intent = new Intent(order_confirmed.this,track_order.class);
        startActivity(intent);
    }

    public double distance(Double latitude, Double longitude, double e, double f) {
        double d2r = Math.PI / 180;

        double dlong = (longitude - f) * d2r;
        double dlat = (latitude - e) * d2r;
        double a = Math.pow(Math.sin(dlat / 2.0), 2) + Math.cos(e * d2r) * Math.cos(latitude * d2r) * Math.pow(Math.sin(dlong / 2.0), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = 6367 * c;
        return d;

    }


    public void progressBarTimer(){
        time = (long) (travelTimeInMinutes * 60000L);
        mCountDownTimer=new CountDownTimer(time,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                mProgressBar.setProgress((int) ((int)i*1000/(time/1000)));
//                int progress = (int) (millisUntilFinished/10000);
//                mProgressBar.setProgress(progress);

            }

            @Override
            public void onFinish() {
                //Do what you want
                i++;
                mProgressBar.setProgress(1000);
                Toast.makeText(order_confirmed.this, "ORDER DELIVERED", Toast.LENGTH_SHORT).show();
                tvDeliveryUpdate.setVisibility(View.INVISIBLE);
                tvOrderDelivered.setVisibility(View.VISIBLE);
            }
        };
        mCountDownTimer.start();
    }


}
