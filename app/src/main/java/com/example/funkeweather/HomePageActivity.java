// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    private EditText editTextSearch;
    private RecyclerView recyclerViewWeather;
    private WeatherAdapter weatherAdapter;
    private List<Weather> weatherList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        // Initialize views
        editTextSearch = findViewById(R.id.editTextSearch);
        recyclerViewWeather = findViewById(R.id.recyclerViewWeather);

        // Initialize RecyclerView
        weatherList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(weatherList, this);
        recyclerViewWeather.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewWeather.setAdapter(weatherAdapter);

        // Fetch weather data from API using WeatherDataFetcher
        WeatherDataFetcher weatherDataFetcher = new WeatherDataFetcher(weatherAdapter);
        weatherDataFetcher.execute("Glasgow", "London", "New York", "Oman", "Mauritius", "Bangladesh");
        Log.d("XCT", weatherDataFetcher.toString());
    }
}
