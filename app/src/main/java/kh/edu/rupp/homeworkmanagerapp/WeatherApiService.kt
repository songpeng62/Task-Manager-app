package kh.edu.rupp.homeworkmanagerapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * WeatherApiService defines how we connect to the OpenWeatherMap API using Retrofit.
 */
interface WeatherApiService {

    /**
     * Gets the current weather for a city.
     * @param city The name of the city (e.g., "Phnom Penh")
     * @param units The units for temperature (e.g., "metric" for Celsius)
     * @param apiKey Your unique API key from OpenWeatherMap
     */
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>

    companion object {
        // The root URL for the OpenWeatherMap API
        private const val BASE_URL = "https://api.openweathermap.org/"

        /**
         * A helper function to create and return the Retrofit service instance.
         */
        fun create(): WeatherApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // Use GSON to automatically convert the JSON response into our Kotlin data class
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}
