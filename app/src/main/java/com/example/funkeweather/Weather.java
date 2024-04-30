// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

public class Weather {
     private String cityName;
    private String date;
    private String temperature;
    private String condition;
    private String humidity;
    private String windDirection;
    private String windSpeed;
    private String pressure;

    public Weather(String date, String temperature, String condition, String humidity, String windDirection, String windSpeed, String pressure,String cityName) {
        this.date = date;
        this.temperature = temperature;
        this.condition = condition;
        this.humidity = humidity;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.pressure = pressure;
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "cityName='" + cityName + '\'' +
                ", date='" + date + '\'' +
                ", temperature='" + temperature + '\'' +
                ", condition='" + condition + '\'' +
                ", humidity='" + humidity + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", pressure='" + pressure + '\'' +
                '}';
    }
}
