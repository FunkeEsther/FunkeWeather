// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLPullParserHandler {
    private List<Weather> weathers = new ArrayList<>();
    private Weather weather;
    private String text;

    public List<Weather> parse(InputStream is,String city) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is, null);
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            weather = new Weather(null, null, null, null, null, null, null,null);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("item")) {
                            weathers.add(weather);
                            weather = null; // Reset the weather object to null
                        } else if (tagName.equalsIgnoreCase("description")) {
                            Log.d("XMLPullParser", "Title: " + text); // Log the title text
                            if (weather != null) {
                                weather.setCityName(city);
                                parseDescription(text, weather); // Parse the description text to extract weather information
                            }// Parse the title text to extract weather information
                        }else if (tagName.equalsIgnoreCase("title")) {
                           parseTitle(text);
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        return weathers;
    }

//    private void parseTitle(String title) {
//        String[] parts = title.split(":");
//        if (parts.length >= 2) {
//            weather.setDate(parts[0].trim());
//            String[] details = parts[1].trim().split(",");
//            for (String detail : details) {
//                String[] keyValue = detail.trim().split(" "); // Split each detail into key-value pair
//                if (keyValue.length == 2) {
//                    String key = keyValue[0].trim();
//                    String value = keyValue[1].trim();
//                    switch (key) {
//                        case "Temperature":
//                            weather.setTemperature(value);
//                            break;
//                        case "Wind Direction":
//                            weather.setWindDirection(value);
//                            break;
//                        case "Wind Speed":
//                            weather.setWindSpeed(value);
//                            break;
//                        case "Pressure":
//                            weather.setPressure(value);
//                            break;
//                        case "Humidity":
//                            weather.setHumidity(value);
//                            break;
//                        case "Condition":
//                            weather.setCondition(value);
//                            break;
//                        default:
//                            Log.w("XMLPullParser", "Unknown key: " + key);
//                            break;
//                    }
//                } else {
//                    Log.w("XMLPullParser", "Unexpected detail format: " + detail);
//                }
//            }
//        } else {
//            Log.w("XMLPullParser", "Unexpected title format: " + title);
//        }
//    }

    private void parseTitle(String title) {
        String[] parts = title.split(":");
        if (parts.length >= 2) {
            weather.setDate(parts[0].trim());
            String[] details = parts[1].trim().split(",");
//            for (String detail : details) {
//                String[] keyValue = detail.trim().split(" "); // Split each detail into key-value pair
//                if (keyValue.length == 2) {
//                    String key = keyValue[0].trim();
//                    String value = keyValue[1].trim();
//                    switch (key) {
//                        case "Wind Direction":
//                            weather.setWindDirection(value);
//                            break;
//                        case "Wind Speed":
//                            weather.setWindSpeed(value);
//                            break;
//                        case "Pressure":
//                            weather.setPressure(value);
//                            break;
//                        case "Humidity":
//                            weather.setHumidity(value);
//                            break;
//                        case "Condition":
//                            weather.setCondition(value);
//                            break;
//                        default:
//                            Log.w("XMLPullParser", "Unknown key: " + key);
//                            break;
//                    }
//                } else {
//                    Log.w("XMLPullParser", "Unexpected detail format: " + detail);
//                }
//            }
        } else {
            Log.w("XMLPullParser", "Unexpected title format: " + title);
        }
    }


    private void parseDescription(String description, Weather weather) {
        // Define patterns for each piece of information
        Pattern maxTempPattern = Pattern.compile("Maximum Temperature: (\\d+)Â°C");
        Pattern minTempPattern = Pattern.compile("Minimum Temperature: (\\d+)Â°C");
        Pattern windDirPattern = Pattern.compile("Wind Direction: (\\w+\\s?\\w*)");
        Pattern windSpeedPattern = Pattern.compile("Wind Speed: (\\d+)mph");
        Pattern visibilityPattern = Pattern.compile("Visibility: (\\w+)");
        Pattern pressurePattern = Pattern.compile("Pressure: (\\d+)mb");
        Pattern humidityPattern = Pattern.compile("Humidity: (\\d+)%");
        Pattern uvRiskPattern = Pattern.compile("UV Risk: (\\d)");
        Pattern pollutionPattern = Pattern.compile("Pollution: (\\w+)");
        Pattern sunrisePattern = Pattern.compile("Sunrise: (\\d{2}:\\d{2})");
        Pattern sunsetPattern = Pattern.compile("Sunset: (\\d{2}:\\d{2})");

        // Extract information using the patterns
        Matcher maxTempMatcher = maxTempPattern.matcher(description);
        Matcher minTempMatcher = minTempPattern.matcher(description);
        Matcher windDirMatcher = windDirPattern.matcher(description);
        Matcher windSpeedMatcher = windSpeedPattern.matcher(description);
        Matcher visibilityMatcher = visibilityPattern.matcher(description);
        Matcher pressureMatcher = pressurePattern.matcher(description);
        Matcher humidityMatcher = humidityPattern.matcher(description);


        // Set the extracted information to the weather object

        if (minTempMatcher.find()) {
            weather.setTemperature(minTempMatcher.group(1));
        }
        if (windDirMatcher.find()) {
            weather.setWindDirection(windDirMatcher.group(1));
        }
        if (windSpeedMatcher.find()) {
            weather.setWindSpeed(windSpeedMatcher.group(1));
        }

        if (pressureMatcher.find()) {
            weather.setPressure(pressureMatcher.group(1));
        }
        if (humidityMatcher.find()) {
            weather.setHumidity(humidityMatcher.group(1));
        }

}}
