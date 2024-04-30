// Student: Funke Esther Aderoju
// Student ID: s2111006

package com.example.funkeweather;
import java.util.HashMap;
import java.util.Map;

public class CountryCodeMapper {

    private static final Map<String, String> countryCodes;

    static {
        countryCodes = new HashMap<>();
        countryCodes.put("United States", "US");
        countryCodes.put("United Kingdom", "GB");
        countryCodes.put("Glasgow", "2648579");
        countryCodes.put("London", "2643743");
        countryCodes.put("New York", "5128581");
        countryCodes.put("Oman", "287286");
        countryCodes.put("Mauritius", "934154");
        countryCodes.put("Bangladesh", "1185241");
        // Add more country mappings as needed
    }

    public static String getCode(String countryName) {
        return countryCodes.get(countryName);
    }
}

