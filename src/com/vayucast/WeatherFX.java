package com.vayucast;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WeatherFX extends Application {
    private final WeatherService weatherService = new WeatherService();
    private Label dateTimeLabel, locationLabel, temperatureLabel, feelsLikeLabel;
    private Label windLabel, pressureLabel, humidityLabel, uvLabel, dewPointLabel, visibilityLabel;
    private ImageView weatherIcon;
    private VBox weatherCard;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("VayuCast - Weather App");

        // Main container
        VBox mainContainer = new VBox();
        mainContainer.setPadding(new Insets(20));
        mainContainer.setSpacing(20);
        mainContainer.setStyle("-fx-background-color: #f5f5f5;");

        // Header section
        VBox headerSection = createHeaderSection();

        // Weather card (initially invisible)
        weatherCard = createWeatherCard();
        weatherCard.setVisible(false);
        weatherCard.setManaged(false);

        mainContainer.getChildren().addAll(headerSection, weatherCard);

        Scene scene = new Scene(mainContainer, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private VBox createHeaderSection() {
        VBox header = new VBox();
        header.setAlignment(Pos.CENTER);
        header.setSpacing(15);

        // Title
        Label titleLabel = new Label("VayuCast");
        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        // City Input
        TextField cityField = new TextField();
        cityField.setPromptText("Enter city name");
        cityField.setMaxWidth(250);
        cityField.setStyle("-fx-font-size: 14px; -fx-padding: 10px; -fx-border-radius: 8px; -fx-background-radius: 8px;");

        // Fetch Button
        Button fetchButton = new Button("Get Weather");
        fetchButton.setStyle("-fx-background-color: #007BFF; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-padding: 10px 20px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
        fetchButton.setOnAction(e -> fetchWeather(cityField.getText().trim()));

        header.getChildren().addAll(titleLabel, cityField, fetchButton);
        return header;
    }

    private VBox createWeatherCard() {
        VBox card = new VBox();
        card.setAlignment(Pos.CENTER_LEFT);
        card.setPadding(new Insets(20));
        card.setSpacing(15);
        card.setStyle("-fx-background-color: white; -fx-border-radius: 12px; -fx-background-radius: 12px; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);");
        card.setMaxWidth(350);

        // Date and time
        dateTimeLabel = new Label();
        dateTimeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #ff6b6b; -fx-font-weight: normal;");

        // Location
        locationLabel = new Label();
        locationLabel.setStyle("-fx-font-size: 20px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        // Weather icon and temperature section
        HBox tempSection = new HBox();
        tempSection.setAlignment(Pos.CENTER_LEFT);
        tempSection.setSpacing(15);

        // Weather icon
        weatherIcon = new ImageView();
        weatherIcon.setFitWidth(50);
        weatherIcon.setFitHeight(50);
        weatherIcon.setPreserveRatio(true);

        // Temperature
        temperatureLabel = new Label();
        temperatureLabel.setStyle("-fx-font-size: 36px; -fx-text-fill: #333333; -fx-font-weight: bold;");

        tempSection.getChildren().addAll(weatherIcon, temperatureLabel);

        // Feels like and description
        feelsLikeLabel = new Label();
        feelsLikeLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");

        // Separator line
        Region separator = new Region();
        separator.setStyle("-fx-background-color: #ff6b6b; -fx-pref-height: 3px; -fx-max-width: 50px;");

        // Weather details grid
        VBox detailsBox = createWeatherDetails();

        card.getChildren().addAll(dateTimeLabel, locationLabel, tempSection, feelsLikeLabel, separator, detailsBox);
        return card;
    }

    private VBox createWeatherDetails() {
        VBox detailsBox = new VBox();
        detailsBox.setSpacing(8);

        // First row: Wind and Pressure
        HBox row1 = new HBox();
        row1.setSpacing(30);
        windLabel = new Label();
        windLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
        pressureLabel = new Label();
        pressureLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
        row1.getChildren().addAll(windLabel, pressureLabel);

        // Second row: Humidity and UV
        HBox row2 = new HBox();
        row2.setSpacing(30);
        humidityLabel = new Label();
        humidityLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
        uvLabel = new Label();
        uvLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
        row2.getChildren().addAll(humidityLabel, uvLabel);

        // Third row: Dew point and Visibility
        HBox row3 = new HBox();
        row3.setSpacing(30);
        dewPointLabel = new Label();
        dewPointLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
        visibilityLabel = new Label();
        visibilityLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #666666;");
        row3.getChildren().addAll(dewPointLabel, visibilityLabel);

        detailsBox.getChildren().addAll(row1, row2, row3);
        return detailsBox;
    }

    private void fetchWeather(String city) {
        if (city.isEmpty()) {
            return;
        }

        WeatherData data = weatherService.getWeather(city);
        if (data != null) {
            updateWeatherDisplay(data, city);

            // Show the weather card
            weatherCard.setVisible(true);
            weatherCard.setManaged(true);
        } else {
            // Hide weather card on error
            weatherCard.setVisible(false);
            weatherCard.setManaged(false);
        }
    }


    private void updateWeatherDisplay(WeatherData data, String city) {
        // Update date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd, hh:mma");
        dateTimeLabel.setText(now.format(formatter));

        // Update location (you might want to get state/country from your weather service)
        locationLabel.setText(city);

        // Update temperature
        temperatureLabel.setText(Math.round((double)data.getTemperature()) + "°C");

        // Update feels like and condition
        String condition = data.getDescription();
        feelsLikeLabel.setText(String.format("Feels like %.1f°C. %s. Gentle Breeze",
                data.getFeelsLike(),
                capitalizeWords(condition)));

        // Update weather details
        windLabel.setText(String.format("💨 %.2fm/s (%s)", data.getWindSpeed(), data.getWindDirection()));
        pressureLabel.setText(String.format("🌡 %.0fhPa", (double)data.getPressure()));
        humidityLabel.setText(String.format("Humidity: %.0f%%", (double)data.getHumidity()));
        uvLabel.setText(String.format("UV Index: %d", data.getUVIndex()));
        dewPointLabel.setText(String.format("Dew point: %.1f°C", data.getDewPoint()));
        visibilityLabel.setText(String.format("Visibility: %.1f km", data.getVisibility()));

        // Update weather icon
        updateWeatherIcon(condition);
    }

    private void updateWeatherIcon(String condition) {
        String iconPath = "/icons/default.png";
        condition = condition.toLowerCase();

        if (condition.contains("clear") || condition.contains("sunny")) {
            iconPath = "/icons/clear.png";
        } else if (condition.contains("rain") || condition.contains("drizzle")) {
            iconPath = "/icons/rainy.png";
        } else if (condition.contains("cloud")) {
            iconPath = "/icons/cloudy.png";
        } else if (condition.contains("snow")) {
            iconPath = "/icons/snowy.png";
        } else if (condition.contains("thunder") || condition.contains("storm")) {
            iconPath = "/icons/stormy.png";
        }

        try {
            weatherIcon.setImage(new Image(getClass().getResourceAsStream(iconPath)));
        } catch (Exception e) {
            // Fallback to default icon if specific icon not found
            weatherIcon.setImage(new Image(getClass().getResourceAsStream("/icons/default.png")));
        }
    }

    private String capitalizeWords(String str) {
        if (str == null || str.isEmpty()) return str;

        String[] words = str.split("\\s+");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (words[i].length() > 0) {
                result.append(Character.toUpperCase(words[i].charAt(0)));
                if (words[i].length() > 1) {
                    result.append(words[i].substring(1).toLowerCase());
                }
            }
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}