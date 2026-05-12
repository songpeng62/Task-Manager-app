package kh.edu.rupp.homeworkmanagerapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * QuoteApiService defines the API endpoints for fetching motivational quotes.
 * It uses Retrofit to make network requests.
 */
interface QuoteApiService {

    /**
     * Fetches a random quote from the API.
     * The @GET("random") annotation specifies the endpoint.
     */
    @GET("random")
    fun getRandomQuote(): Call<QuoteResponse>

    companion object {
        // The base URL for the Quotable API
        private const val BASE_URL = "https://api.quotable.io/"

        /**
         * Creates and returns an instance of the QuoteApiService.
         * This is where Retrofit is configured with the base URL and a JSON converter.
         */
        fun create(): QuoteApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // Add GsonConverterFactory to automatically parse JSON responses into Kotlin objects
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(QuoteApiService::class.java)
        }
    }
}
