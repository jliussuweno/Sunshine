package com.bca.sunshine.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bca.sunshine.R;
import com.bca.sunshine.delegate.IGeneralCallback;
import com.bca.sunshine.holder.WeatherHolder;
import com.bca.sunshine.model.WeatherModel;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter {

    private IGeneralCallback callback = null;
    private List<WeatherModel> arrWeather = new ArrayList<>();

    public void setData(List<WeatherModel> weathers) {
        arrWeather = weathers;
    }

    public void addData(WeatherModel weather) {
        arrWeather.add(weather);
    }

    public void setCallback(IGeneralCallback callbackDelegate) {
        callback = callbackDelegate;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder tmpHolder = null;

        View tmpView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather, parent, false);
        tmpHolder = new WeatherHolder(tmpView);

        return tmpHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((WeatherHolder)holder).setText(arrWeather.get(position).getDay());
        final String concateString = arrWeather.get(position).getDay() + arrWeather.get(position).getWeatherName() + arrWeather.get(position).getMaxDegrees() + arrWeather.get(position).getMinDegrees();
        ((WeatherHolder)holder).getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.itemPressedCallback(concateString);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrWeather.size();
    }
}
