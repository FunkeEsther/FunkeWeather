// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherReportAdapter extends RecyclerView.Adapter<WeatherReportAdapter.WeatherViewHolder> {

    private List<WeatherReport.WeatherDay> weatherList;
    private final Context environ;

    public void setWeatherList(List<WeatherReport.WeatherDay> weatherList){
        this.weatherList.clear();
        this.weatherList.addAll(weatherList);
    }
    public WeatherReportAdapter( Context environ, List<WeatherReport.WeatherDay> weatherList) {
        this.environ=environ;
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.three_day_item, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        WeatherReport.WeatherDay weather = weatherList.get(position);

        if (weather !=null){
            String title= weather.getTitle();
            String[] titlePortions= title.split(":",2);

            String dayOfWeek;
            String date= "20/April/2024";
            String minTemp=extractAndFormatMinTemperature(title);
            String MaxTemp= extractAndFormatMaxTemperature(title);
            String weatherClassification;

        if (titlePortions != null) {
            dayOfWeek = titlePortions[0].trim();
            String[] otherPortions = titlePortions[1].split(",");
            if (otherPortions != null) {
                weatherClassification = otherPortions[0].trim();

            } else {
                weatherClassification = "Condition Unknown";
            }
        }
        else{
            dayOfWeek="unknown";
            weatherClassification= "Condition Unknown";
            }

            holder.dayOfWeek.setText(dayOfWeek);
            holder.date.setText(date);
            holder.minTemp.setText(minTemp);
            holder.maxTemp.setText( MaxTemp);
            holder.weatherClassification.setText(weatherClassification);
        }
        }



    public static String extractAndFormatMinTemperature(String title) {
        String regexMin = "Minimum Temperature: (\\d+)°C";
        Pattern patternMin = Pattern.compile(regexMin);
        Matcher matcherMin = patternMin.matcher(title);
        String minTemperature = null;
        if (matcherMin.find()) {
            minTemperature = matcherMin.group(1);
        }
        if (minTemperature != null) {
            return minTemperature + " °C";
        }
        return null;
    }

    public static String extractAndFormatMaxTemperature(String title) {
        String regexMax = "Maximum Temperature: (\\d+)°C";
        Pattern patternMax = Pattern.compile(regexMax);
        Matcher matcherMax = patternMax.matcher(title);
        String maxTemperature = null;
        if (matcherMax.find()) {
            maxTemperature = matcherMax.group(1);
        }
        if (maxTemperature != null) {
            return maxTemperature + " °C";
        }
        return null;
    }


    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public static class WeatherViewHolder extends RecyclerView.ViewHolder {
        TextView dayOfWeek, date, minTemp, maxTemp, weatherClassification;

        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeek = itemView.findViewById(R.id.dayOfWeek);
            date = itemView.findViewById(R.id.date);
            minTemp = itemView.findViewById(R.id.minTemp);
            maxTemp = itemView.findViewById(R.id.maxTemp);
            weatherClassification = itemView.findViewById(R.id.weatherClassification);
        }
    }
}

