package kh.edu.rupp.homeworkmanagerapp

/**
 * WeatherResponse is a data class used to map the JSON data received from the OpenWeatherMap API.
 * It contains nested classes to match the structure of the API response.
 */
data class WeatherResponse(
    // The "main" object in the JSON contains temperature information
    val main: MainData,
    
    // The "weather" field in the JSON is an array (List), usually containing one item
    val weather: List<WeatherData>
)

/**
 * Maps the "main" part of the weather JSON.
 */
data class MainData(
    // temp is a Double value representing the current temperature
    val temp: Double
)

/**
 * Maps the items inside the "weather" array in the JSON.
 */
data class WeatherData(
    // description is a String explaining the weather (e.g., "clear sky", "light rain")
    val description: String
)
