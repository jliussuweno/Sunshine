package com.bca.sunshine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import org.json.JSONObject;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bca.sunshine.adapter.WeatherAdapter;
import com.bca.sunshine.model.WeatherModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerview = findViewById(R.id.obj_recyclerview);


        recyclerview.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)


        List<WeatherModel> tmpDummyData = new ArrayList<>();

        tmpDummyData.add(new WeatherModel("Today", "Light Rain", "18C", "13C"));
        tmpDummyData.add(new WeatherModel("Today", "Light Rain", "18C", "13C"));
        tmpDummyData.add(new WeatherModel("Today", "Light Rain", "18C", "13C"));
        tmpDummyData.add(new WeatherModel("Today", "Light Rain", "18C", "13C"));

        final WeatherAdapter tmpAdapter = new WeatherAdapter();
        tmpAdapter.setData(tmpDummyData);
//        tmpAdapter.setCallback(this);
        recyclerview.setAdapter(tmpAdapter);

        initData();
    }




    void initData(){
//        final TextView textView = (TextView) findViewById(R.id.text);

        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://andfun-weather.udacity.com/weather";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("JEJE", "Response is: " + response);


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JEJE", "That didn't work!");
            }
        });

        queue.add(stringRequest);
    }
}