package kh.edu.rupp.homeworkmanagerapp

/**
 * WeatherResponse is a data class used to map the JSON data received from the weather API.
 * It contains nested classes to match the structure of the API response.
 */
data class WeatherResponse(
    val main: MainData,        // Contains temperature info
    val weather: List<WeatherData> // Contains weather description info
)

/**
 * Maps the "main" object from the JSON.
 */
data class MainData(
    val temp: Double // The current temperature
)

/**
 * Maps the "weather" array elements from the JSON.
 */
data class WeatherData(
    val description: String // Example: "clear sky", "light rain"
)
