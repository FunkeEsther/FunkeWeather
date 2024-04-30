// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import java.util.List;

public class WeatherReport {
    private String title;
    private String description;
    private List<WeatherDay> forecast;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WeatherDay> getForecast() {
        return forecast;
    }

    public void setForecast(List<WeatherDay> forecast) {
        this.forecast = forecast;
    }

    public static class WeatherDay {
        private String title;
        private String description;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "WeatherDay{" +
                    "title='" + title + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
