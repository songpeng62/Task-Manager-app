package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * HomeFragment is the main dashboard for the user.
 * It displays a summary, a daily motivational quote, and current weather data.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflate the layout for this fragment (fragment_home.xml)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 2. Connect the UI elements to our code using their IDs
        val btnViewHomework: Button = view.findViewById(R.id.btnViewHomework)
        val btnAddHomework: Button = view.findViewById(R.id.btnAddHomework)
        val tvQuoteContent: TextView = view.findViewById(R.id.tvQuoteContent)
        val tvWeatherContent: TextView = view.findViewById(R.id.tvWeatherContent)

        // 3. Set up the View Homework button to switch to the list screen
        btnViewHomework.setOnClickListener {
            val listFragment = HomeworkListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, listFragment)
                .addToBackStack(null)
                .commit()
        }

        // 4. Set up the Add Homework button to switch to the add screen
        btnAddHomework.setOnClickListener {
            val addFragment = AddHomeworkFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, addFragment)
                .addToBackStack(null)
                .commit()
        }

        // 5. Load a motivational quote using Retrofit
        loadMotivationalQuote(tvQuoteContent)

        // 6. Load current weather data using Retrofit
        loadWeatherData(tvWeatherContent)

        return view
    }

    /**
     * This function fetches a random quote from the internet and displays it.
     */
    private fun loadMotivationalQuote(textView: TextView) {
        val apiService = QuoteApiService.create()

        apiService.getRandomQuote().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful) {
                    val quoteBody = response.body()
                    textView.text = "\"${quoteBody?.content}\""
                } else {
                    textView.text = "Failed to load quote"
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                textView.text = "Failed to load quote"
            }
        })
    }

    /**
     * This function fetches current weather for Phnom Penh and displays it.
     */
    private fun loadWeatherData(textView: TextView) {
        // Initialize the Weather Retrofit service
        val apiService = WeatherApiService.create()

        // Call the API (Replace 'YOUR_API_KEY' with a real key from OpenWeatherMap)
        apiService.getCurrentWeather("Phnom Penh", "metric", "YOUR_API_KEY")
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weather = response.body()
                        if (weather != null) {
                            // Extract temperature and description
                            val temp = weather.main.temp
                            val desc = weather.weather[0].description
                            // Update the UI
                            textView.text = "$temp°C, $desc"
                        }
                    } else {
                        textView.text = "Failed to load weather"
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    textView.text = "Failed to load weather"
                }
            })
    }
}
