package kh.edu.rupp.homeworkmanagerapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * NewsApiService is an interface that defines how Retrofit will fetch 
 * education news from the NewsAPI.org service.
 */
interface NewsApiService {

    /**
     * Fetches articles based on a search term, language, and sorting preference.
     * 
     * Requirement Endpoint: v2/everything?q=education&language=en&sortBy=publishedAt&apiKey=e22f8febc4f543dabc726177e0ddf035
     * 
     * @param query The topic to search for (e.g., "education")
     * @param language The language of the articles (e.g., "en")
     * @param sortBy How to sort the results (e.g., "publishedAt")
     * @param apiKey Your unique key from NewsAPI.org
     */
    @GET("v2/everything")
    fun getEducationNews(
        @Query("q") query: String,
        @Query("language") language: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>

    companion object {
        // The base URL for all NewsAPI requests
        private const val BASE_URL = "https://newsapi.org/"

        /**
         * A helper function to create and return the Retrofit service instance.
         */
        fun create(): NewsApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                // Use Gson to automatically convert the JSON response into our NewsResponse object
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(NewsApiService::class.java)
        }
    }
}
