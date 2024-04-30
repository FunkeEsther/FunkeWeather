// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WeatherReportParser {
    public static WeatherReport parse(InputStream inputStream) throws XmlPullParserException, IOException {
        XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(inputStream, null);

        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setForecast(new ArrayList<>());

        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String tagName = parser.getName();

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("item".equals(tagName)) {
                        parseItem(parser, weatherReport);
                    }
                    break;
            }

            eventType = parser.next();
        }

        return weatherReport;
    }

    private static void parseItem(XmlPullParser parser, WeatherReport weatherForecast) throws XmlPullParserException, IOException {
        WeatherReport.WeatherDay currentWeatherDay = new WeatherReport.WeatherDay();

        int eventType = parser.next();
        while (eventType != XmlPullParser.END_TAG) {
            String tagName = parser.getName();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("title".equals(tagName)) {
                        currentWeatherDay.setTitle(parseText(parser));
                    } else if ("description".equals(tagName)) {
                        currentWeatherDay.setDescription(parseText(parser));
                    }
                    break;
            }
            eventType = parser.next();
        }

        List<WeatherReport.WeatherDay> forecast = weatherForecast.getForecast();
        if (forecast != null) {
            forecast.add(currentWeatherDay);
        }
    }

    private static String parseText(XmlPullParser parser) throws XmlPullParserException, IOException {
        StringBuilder text = new StringBuilder();
        int eventType = parser.next();
        while (eventType != XmlPullParser.END_TAG) {
            if (eventType == XmlPullParser.TEXT) {
                text.append(parser.getText());
            }
            eventType = parser.next();
        }
        return text.toString().trim();
    }
}