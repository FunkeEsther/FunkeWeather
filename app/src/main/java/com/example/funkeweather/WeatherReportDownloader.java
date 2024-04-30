// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class WeatherReportDownloader extends AsyncTask<String, Void, WeatherReport> {

    private final String BASE_URL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    private WeatherReportAdapter reportAdapter;

    public WeatherReportDownloader(WeatherReportAdapter reportAdapter) {
        this.reportAdapter = reportAdapter;
    }

    @Override
    protected WeatherReport doInBackground(String... cityNames) {
        if (cityNames.length == 0) {
            return null;
        }

        String cityName = cityNames[0];

        try {
            String areaCode = CountryCodeMapper.getCode(cityName);
            if (areaCode == null) {
                Log.e("DownloadForecast", "City not found in dictionary");
                return null;
            }

            URL url = new URL(BASE_URL + areaCode);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            WeatherReport weatherReport = WeatherReportParser.parse(inputStream);

            return weatherReport != null ? weatherReport : null;

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("DownloadForecast", "Error downloading data for " + cityName, e);
        }

        return null;
    }

    @Override
    protected void onPostExecute(WeatherReport result) {
        if (result != null) {
            Log.d("DownloadForecast", result.toString());
            Log.d("WeatherForecast", "Title: " + result.getTitle());

            List<WeatherReport.WeatherDay> forecastDays = result.getForecast();
            if (forecastDays != null) {
                for (WeatherReport.WeatherDay day : forecastDays) {
                    Log.d("WeatherData", "Day: " + day.getTitle() + ", Description: " + day.getDescription());
                }
                // Update adapter data here
                reportAdapter.setWeatherList(forecastDays);
            } else {
                Log.e("WeatherData", "Forecast list is null");
            }

            if (reportAdapter != null) {
                reportAdapter.notifyDataSetChanged();
            }
        } else {
            Log.e("DownloadForecast", "Parsing failed or data is null");
        }
    }
}
