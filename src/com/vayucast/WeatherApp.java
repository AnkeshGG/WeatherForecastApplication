package com.vayucast;

import java.util.Scanner;

public class WeatherApp {
    private final WeatherService weatherService = new WeatherService();

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();

        WeatherData data = weatherService.getWeather(city);
        if (data != null) {
            displayWeather(data);
        } else {
            System.out.println("Failed to fetch weather data.");
        }
        scanner.close();
    }

    private void displayWeather(WeatherData data) {
        System.out.println("Weather in " + data.getCity() + ": " + data.getDescription());
        System.out.println("Temperature: " + data.getTemperature() + "°C");
        System.out.println("Humidity: " + data.getHumidity() + "%");
    }
}
