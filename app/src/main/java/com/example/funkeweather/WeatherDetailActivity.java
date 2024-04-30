// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class WeatherDetailActivity extends AppCompatActivity {

    private TextView tvCityName, tvDate, tvTemperature, tvWindDirection, tvWindSpeed, tvHumidity, tvPressure, tvWeatherCondition;
    private Button btnNextDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);

        // Initialize views
        tvCityName = findViewById(R.id.tvCityName);
        tvDate = findViewById(R.id.tvDate);
        tvTemperature = findViewById(R.id.tvTemperature);
        tvWindDirection = findViewById(R.id.tvWindDirection);
        tvWindSpeed = findViewById(R.id.tvWindSpeed);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvPressure = findViewById(R.id.tvPressure);
        tvWeatherCondition = findViewById(R.id.tvWeatherCondition);
        btnNextDay = findViewById(R.id.btnNextDay);

        // Retrieve data from Intent
        Intent intent = getIntent();
        String cityName = intent.getStringExtra("cityName");
        String date = intent.getStringExtra("date");
        String temperature = intent.getStringExtra("temperature");
        String windDirection = intent.getStringExtra("windDirection");
        String windSpeed = intent.getStringExtra("windSpeed");
        String humidity = intent.getStringExtra("humidity");
        String pressure = intent.getStringExtra("pressure");
        String weatherCondition = intent.getStringExtra("weatherCondition");

        // Populate TextViews with the retrieved data
        tvCityName.setText(cityName);
        tvDate.setText(date);
        tvTemperature.setText(temperature);
        tvWindDirection.setText(windDirection);
        tvWindSpeed.setText(windSpeed);
        tvHumidity.setText(humidity);
        tvPressure.setText(pressure);
        tvWeatherCondition.setText(weatherCondition);

        // Handle button click
        btnNextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the next day's forecast
                Intent nextDayIntent = new Intent(WeatherDetailActivity.this,ThreeDay.class);
                nextDayIntent.putExtra("cityName", cityName);
                startActivity(nextDayIntent);
            }
        });
    }
}
