package com.vayucast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WeatherParser {
    public static WeatherData parseJson(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        String city = obj.get("name").getAsString();
        String description = obj.getAsJsonArray("weather").get(0).getAsJsonObject().get("description").getAsString();

        JsonObject main = obj.getAsJsonObject("main");
        double temperature = main.get("temp").getAsDouble();
        double feelsLike = main.get("feels_like").getAsDouble(); // Get actual feels like from API
        int humidity = main.get("humidity").getAsInt();
        int pressure = main.get("pressure").getAsInt();

        JsonObject wind = obj.getAsJsonObject("wind");
        double windSpeed = wind.get("speed").getAsDouble();
        String windDirection = getWindDirection(wind.get("deg").getAsInt());

        int uvIndex = obj.has("uvi") ? obj.get("uvi").getAsInt() : 0; // UV Index from API
        double visibility = obj.has("visibility") ? obj.get("visibility").getAsDouble() / 1000 : 0; // Convert meters to km

        // Calculate dew point using Magnus formula since OpenWeatherMap basic API doesn't provide it directly
        double dewPoint = calculateDewPoint(temperature, humidity);

        return new WeatherData(city, description, temperature, feelsLike, humidity, pressure,
                windSpeed, windDirection, uvIndex, visibility, dewPoint);
    }

    private static String getWindDirection(int degree) {
        String[] directions = {"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
        return directions[(int) Math.round(((double) degree % 360) / 45) % 8];
    }

    /**
     * Calculate dew point using the Magnus formula
     * @param temperature Temperature in Celsius
     * @param humidity Relative humidity as percentage
     * @return Dew point in Celsius
     */
    private static double calculateDewPoint(double temperature, int humidity) {
        double a = 17.27;
        double b = 237.7;

        double alpha = ((a * temperature) / (b + temperature)) + Math.log(humidity / 100.0);
        double dewPoint = (b * alpha) / (a - alpha);

        return Math.round(dewPoint * 10.0) / 10.0; // Round to 1 decimal place
    }
}