// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class WeatherForecastParser {

    public static void main(String[] args) {
        String xml = "your XML content here";
        try {
            ArrayList

                    <String[]> forecasts = parseForecast(xml);
            for (String[] forecast : forecasts) {
                System.out.println("Date: " + forecast[0]);
                System.out.println("Description: " + forecast[1]);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String[]> parseForecast(String xml) throws XmlPullParserException, IOException {
        ArrayList<String[]> forecasts = new ArrayList<>();

        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser parser = factory.newPullParser();
        parser.setInput(new StringReader(xml));

        int eventType = parser.getEventType();
        String pubDate = null;
        String description = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    String tagName = parser.getName();
                    if (tagName != null && tagName.equals("item")) {
                        pubDate = null;
                        description = null;
                    }
                    break;
                case XmlPullParser.TEXT:
                    if (pubDate == null && parser.getText().trim().length() > 0) {
                        pubDate = parser.getText().trim();
                    } else if (description == null && parser.getText().trim().length() > 0) {
                        description = parser.getText().trim();
                    }
                    break;
                case XmlPullParser.END_TAG:
                    if (parser.getName().equals("item")) {
                        if (pubDate != null && description != null) {
                            String[] forecast = {pubDate, description};
                            forecasts.add(forecast);
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }

        return forecasts;
    }
}
