// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;


import android.os.AsyncTask;
import android.util.Log; // Import Log class
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WeatherDataFetcher extends AsyncTask<String, Void, List<Weather>> {

    private static final String TAG = "WeatherDataFetcher"; // Tag for logging
    private static final String BASE_URL = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/";

    private WeatherAdapter weatherAdapter;

    public WeatherDataFetcher(WeatherAdapter weatherAdapter) {
        this.weatherAdapter = weatherAdapter;
    }

    @Override
    protected List<Weather> doInBackground(String... cityNames) {
        XMLPullParserHandler parserHandler = new XMLPullParserHandler();
        List<Weather> allWeatherList = new ArrayList<>(); // Accumulate weather data for all cities

        for (String cityName : cityNames) {
            String countryCode = CountryCodeMapper.getCode(cityName);
            if (countryCode != null) {
                try {
                    URL url = new URL(BASE_URL + countryCode);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    InputStream inputStream = connection.getInputStream();

                    List<Weather> parsedWeather = parserHandler.parse(inputStream,cityName);
                    inputStream.close();
                    connection.disconnect();

                    // Add the weather data for this city to the list of all weather data
                    if (parsedWeather != null) {
                        allWeatherList.addAll(parsedWeather);
                    } else {
                        Log.w(TAG, "Weather data for " + cityName + " is null");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e(TAG, "Error fetching weather data for " + cityName + ": " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Country code not found for city: " + cityName);
            }
        }
        Log.d("CFY",allWeatherList.toString());

        return allWeatherList; // Return the accumulated weather data for all cities
    }


    @Override
    protected void onPostExecute(List<Weather> weatherList) {
        super.onPostExecute(weatherList);
        if (weatherList != null && !weatherList.isEmpty()) {
            weatherAdapter.setWeatherList(weatherList);
            weatherAdapter.notifyDataSetChanged();
        } else {
            Log.w(TAG, "Received empty or null weather data");
        }
    }
}

