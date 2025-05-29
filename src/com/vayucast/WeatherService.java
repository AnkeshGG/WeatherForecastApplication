package com.vayucast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {
    private static final String API_KEY = ConfigLoader.getApiKey();
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&appid=" + API_KEY + "&units=metric";

    public WeatherData getWeather(String city) {
        try {
            URL url = new URL(String.format(BASE_URL, city));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            return WeatherParser.parseJson(response.toString());
        } catch (Exception e) {
            System.err.println("Error fetching weather: " + e.getMessage());
            return null;
        }
    }
}
