package kh.edu.rupp.homeworkmanagerapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * WeatherApiService is an interface that defines how Retrofit will fetch 
 * weather data from the OpenWeatherMap API.
 */
interface WeatherApiService {

    /**
     * This function performs a GET request to fetch current weather.
     * The parameters allow us to specify the city, units, and our API key.
     * 
     * Requirement Endpoint: data/2.5/weather?q=Phnom Penh&units=metric&appid=88fe09458dd46e0a2c0af2c5af6e94cb
     */
    @GET("data/2.5/weather")
    fun getCurrentWeather(
        @Query("q") city: String,
        @Query("units") units: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>

    companion object {
        // The base URL for the OpenWeatherMap API
        private const val BASE_URL = "https://api.openweathermap.org/"

        /**
         * A helper function to create and return the Retrofit service instance.
         */
        fun create(): WeatherApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // Add Gson converter to automatically turn the JSON response into our WeatherResponse data class
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(WeatherApiService::class.java)
        }
    }
}
