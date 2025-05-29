# VayuCast - Weather Forecast Application

## Overview

VayuCast is a **modern and intuitive** weather forecast application built using **Java** with both console and **JavaFX GUI** interfaces. It provides real-time weather information with detailed metrics and a visually appealing user interface. The application leverages the **OpenWeatherMap API** to deliver accurate weather data for cities worldwide.

## Features

- ✔ **Dual Interface:** Both **console-based** and **JavaFX GUI** applications for versatile usage.
- ✔ **Real-time Weather Data:** Fetches current weather information using **OpenWeatherMap API**.
- ✔ **Comprehensive Weather Metrics:** Temperature, feels-like temperature, humidity, pressure, wind speed, UV index, visibility, and dew point.
- ✔ **Visual Weather Icons:** Dynamic weather icons that change based on current conditions.
- ✔ **Modern UI Design:** Clean, card-based interface with attractive styling and responsive layout.
- ✔ **Smart Weather Parsing:** Advanced JSON parsing with calculated dew point using Magnus formula.
- ✔ **Configuration Management:** Secure API key handling through properties file.
- ✔ **Error Handling:** Robust exception management for network failures and invalid inputs.

## Screenshots

![image](https://github.com/user-attachments/assets/6bfcc8cd-6574-41cf-8bdc-eb889d04dac9)
![image](https://github.com/user-attachments/assets/81d922db-8a15-4032-a10b-39514cb54535)
![image](https://github.com/user-attachments/assets/3c25d99c-33b0-4042-9027-90bb914ddda6)
![image](https://github.com/user-attachments/assets/0405598d-366e-4acf-9111-4f0d1613d35c)



## Technical Details

- 📌 **Programming Language:** Java
- 📌 **GUI Framework:** JavaFX
- 📌 **API Integration:** OpenWeatherMap REST API
- 📌 **JSON Processing:** Google Gson library
- 📌 **Configuration:** Properties file for API key management
- 📌 **Architecture:** Service-oriented design with separate layers for UI, service, and data parsing
- 📌 **Weather Calculations:** Magnus formula implementation for dew point calculation

## Project Structure

```
VayuCast/
├── src/main/java/com/vayucast/
│   ├── ConfigLoader.java          # API configuration management
│   ├── Main.java                  # Application entry point
│   ├── WeatherApp.java           # Console-based weather app
│   ├── WeatherData.java          # Weather data model
│   ├── WeatherFX.java            # JavaFX GUI application
│   ├── WeatherParser.java        # JSON response parser
│   └── WeatherService.java       # Weather API service layer
├── src/main/resources/
│   ├── config.properties         # API key configuration
│   └── icons/                    # Weather condition icons
│       ├── clear.png
│       ├── cloudy.png
│       ├── rainy.png
│       └── default.png
└── lib/
    ├── gson-2.8.9.jar           # JSON processing library
    ├── jackson-annotations-2.15.0.jar
    └── jackson-databind-2.15.0.jar
```

## How to Run

### Prerequisites:

- ✔ Install **JDK 11 or higher**
- ✔ Obtain an API key from [OpenWeatherMap](https://openweathermap.org/api)
- ✔ Required libraries (Gson, Jackson - included in `lib/` directory)

### Setup:

1️⃣ **Configure API Key:**
Create `config.properties` in `src/main/resources/` with:
```properties
api_key=YOUR_OPENWEATHERMAP_API_KEY
```

2️⃣ **Add Weather Icons:**
Place weather icon images in `src/main/resources/icons/` directory:
- `clear.png` - for sunny/clear weather
- `cloudy.png` - for cloudy conditions
- `rainy.png` - for rain/drizzle
- `default.png` - fallback icon

3️⃣ **Compile and Run:**

**For JavaFX GUI Application:**
```bash
javac -cp "lib/*" src/main/java/com/vayucast/*.java
java -cp "lib/*:src/main/java" com.vayucast.WeatherFX
```

**For Console Application:**
```bash
javac -cp "lib/*" src/main/java/com/vayucast/*.java
java -cp "lib/*:src/main/java" com.vayucast.WeatherApp
```

## Usage Guide

### GUI Application:
* **Launch Application:** Run `WeatherFX.java` to open the graphical interface.
* **Enter City:** Type the name of any city in the input field.
* **Get Weather:** Click "Get Weather" button to fetch current weather data.
* **View Details:** Weather card displays comprehensive information including temperature, humidity, wind, pressure, and more.

### Console Application:
* **Run Console Version:** Execute `WeatherApp.java` for command-line interface.
* **Enter City Name:** Input the desired city when prompted.
* **View Results:** Weather information is displayed in the terminal.

### Weather Metrics Explained:
* **Temperature & Feels Like:** Current and perceived temperatures in Celsius
* **Humidity:** Relative humidity percentage
* **Pressure:** Atmospheric pressure in hPa
* **Wind:** Speed in m/s and direction
* **UV Index:** Ultraviolet radiation intensity
* **Visibility:** Atmospheric visibility in kilometers
* **Dew Point:** Temperature at which air becomes saturated

## API Integration

The application uses **OpenWeatherMap Current Weather Data API**:
- **Base URL:** `https://api.openweathermap.org/data/2.5/weather`
- **Parameters:** City name, API key, metric units
- **Response Format:** JSON with comprehensive weather data
- **Rate Limits:** Free tier allows 1,000 calls per day

## Design Highlights

### 🎨 **Modern UI Design:**
* Clean, card-based layout with rounded corners and subtle shadows
* Responsive design that adapts to content
* Color-coded elements with professional styling

### 📊 **Smart Data Processing:**
* Advanced JSON parsing with error handling
* Calculated dew point using meteorological formulas
* Wind direction conversion from degrees to compass directions

### 🔧 **Robust Architecture:**
* Separation of concerns with distinct service layers
* Secure configuration management
* Comprehensive error handling and logging

## Future Enhancements

- 🔹 **5-Day Forecast:** Extended weather predictions
- 🔹 **Location Services:** GPS-based automatic location detection
- 🔹 **Weather Alerts:** Severe weather notifications
- 🔹 **Historical Data:** Past weather information and trends
- 🔹 **Dark Mode:** Alternative UI theme options
- 🔹 **Multi-language Support:** Internationalization capabilities
- 🔹 **Weather Maps:** Integration with weather map visualizations

## Troubleshooting

**Common Issues:**
- **API Key Error:** Ensure `config.properties` contains valid OpenWeatherMap API key
- **Network Issues:** Check internet connection and firewall settings
- **Missing Icons:** Verify weather icon files are present in `resources/icons/`
- **JavaFX Runtime:** Ensure JavaFX is properly configured in your Java environment

## About the Project

VayuCast was developed as a comprehensive weather application showcasing modern Java development practices, API integration, and user interface design. The project demonstrates clean architecture principles, error handling, and responsive UI development using JavaFX.

## About the Author

Hi, I'm **Ankesh Kumar** — a passionate developer exploring Java and UI development. This project was built as part of my journey to deepen my understanding of JavaFX, API integration, and software architecture. I'm always learning and open to collaboration or feedback!

Feel free to connect with me on:
- [LinkedIn](https://www.linkedin.com/in/ankesh-kumar-38363a287/)
- [GitHub](https://github.com/AnkeshGG)
---
© 2025 Ankesh Kumar.

VayuCast Weather Application — Built with ❤️ using Java & JavaFX.