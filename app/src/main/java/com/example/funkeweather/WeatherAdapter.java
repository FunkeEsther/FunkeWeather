// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Weather> weatherList;
    private Context context;

    public WeatherAdapter(List<Weather> weatherList, Context context) {
        this.weatherList = weatherList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_holder, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Weather weather = weatherList.get(position);

        holder.textViewDate.setText(weather.getDate());
        holder.textViewTemperature.setText(weather.getTemperature());
        holder.textViewCondition.setText(weather.getCondition());
        holder.textViewHumidity.setText(weather.getHumidity());
        holder.textViewWindDirection.setText(weather.getWindDirection());
        holder.textViewWindSpeed.setText(weather.getWindSpeed());
        holder.textViewPressure.setText(weather.getPressure());
        holder.cityName.setText( weather.getCityName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WeatherDetailActivity.class);
                intent.putExtra("date", weather.getDate());
                intent.putExtra("cityName", weather.getCityName());
                intent.putExtra("temperature", weather.getTemperature());
                intent.putExtra("windDirection", weather.getWindDirection());
                intent.putExtra("windSpeed", weather.getWindSpeed());
                intent.putExtra("humidity", weather.getHumidity());
                intent.putExtra("pressure", weather.getPressure());
                intent.putExtra("weatherCondition", weather.getCondition());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDate, textViewTemperature, textViewCondition, textViewHumidity, textViewWindDirection, textViewWindSpeed, textViewPressure,cityName ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTemperature = itemView.findViewById(R.id.textViewTemperature);
            textViewCondition = itemView.findViewById(R.id.textViewCondition);
            textViewHumidity = itemView.findViewById(R.id.textViewHumidity);
            textViewWindDirection = itemView.findViewById(R.id.textViewWindDirection);
            textViewWindSpeed = itemView.findViewById(R.id.textViewWindSpeed);
            textViewPressure = itemView.findViewById(R.id.textViewPressure);
            cityName  = itemView.findViewById(R.id.city);
        }
    }
}
