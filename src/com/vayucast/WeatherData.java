package com.vayucast;

public class WeatherData {
    private final String city;
    private final String description;
    private final double temperature;
    private final double feelsLike; // New field for actual feels like temperature
    private final int humidity;
    private final int pressure;
    private final double windSpeed;
    private final String windDirection;
    private final int uvIndex;
    private final double visibility;
    private final double dewPoint; // New field for actual dew point

    public WeatherData(String city, String description, double temperature, double feelsLike,
                       int humidity, int pressure, double windSpeed, String windDirection,
                       int uvIndex, double visibility, double dewPoint) {
        this.city = city;
        this.description = description;
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
        this.uvIndex = uvIndex;
        this.visibility = visibility;
        this.dewPoint = dewPoint;
    }

    public String getCity() { return city; }
    public String getDescription() { return description; }
    public double getTemperature() { return temperature; }
    public double getFeelsLike() { return feelsLike; }
    public int getHumidity() { return humidity; }
    public int getPressure() { return pressure; }
    public double getWindSpeed() { return windSpeed; }
    public String getWindDirection() { return windDirection; }
    public int getUVIndex() { return uvIndex; }
    public double getVisibility() { return visibility; }
    public double getDewPoint() { return dewPoint; }
}