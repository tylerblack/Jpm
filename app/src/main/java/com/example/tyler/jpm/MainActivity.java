package com.example.tyler.jpm;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();

    private ArrayAdapter<AtmJson> mAdapater;
    private ListView mListView;

    private AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            //Launched detailed information activity
            Intent i = new Intent(getApplicationContext(), DetailActivity.class);
            i.putExtra(AtmJson.class.getSimpleName(), mAdapater.getItem(position));
            startActivity(i);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapater = new ArrayAdapter<AtmJson>(this, R.layout.listview);
        mListView = (ListView) findViewById(R.id.listView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationManager locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        Location location;
        //Check if the location services are available, and if so launch with that information
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            getLocationData(location);
        }

        else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            getLocationData(location);
        }

        else{
            setErrorMessage("Sorry, we could not get your location data at this time.");
        }

    }

    private void getLocationData(Location location){

        Call<LocationJson> getLocationInfoCall = RetrofitSingleton.getInstance().getLocationInfo(location.getLatitude(), location.getLongitude());

        getLocationInfoCall.enqueue(new Callback<LocationJson>() {
            @Override
            public void onResponse(Call<LocationJson> call, retrofit2.Response<LocationJson> response) {
                if (response.isSuccessful()) {
                    mAdapater.clear();
                    if (response.body().locations.size() == 0){
                        setErrorMessage("Sorry, no ATM locations were found in your area.");
                    }
                    else {
                        mAdapater.addAll(response.body().locations);
                        mListView.setAdapter(mAdapater);
                        mListView.setOnItemClickListener(mMessageClickedHandler);
                    }
                }
                else{
                    //response failed; display error case
                    setErrorMessage("Sorry, we could not connect to m.chase.com at this time");
                }
            }

            @Override
            public void onFailure(Call<LocationJson> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                //call failed; likely a network error
                setErrorMessage("Sorry, we are unable to get this information at the moment");
            }
        });
    }

    //Just a quick way of displaying errors to the user
    //clears the adapter and adds a dummy AtmJson object to the listView with the error message
    //no click listener added
    private void setErrorMessage(String message){
        AtmJson error = new AtmJson();
        error.label = message;
        mAdapater.clear();
        mAdapater.add(error);
        mListView.setAdapter(mAdapater);
    }
}
