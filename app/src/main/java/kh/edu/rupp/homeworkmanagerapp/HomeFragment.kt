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
 * It displays an updated summary of tasks, a daily motivational quote, current weather, and education news.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflate the layout for this fragment (connect to fragment_home.xml)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 2. Connect the UI elements to our code using their IDs from the XML
        val btnViewHomework: Button = view.findViewById(R.id.btnViewHomework)
        val btnAddHomework: Button = view.findViewById(R.id.btnAddHomework)
        val tvTaskSummary: TextView = view.findViewById(R.id.tvTaskSummary)
        val tvQuoteContent: TextView = view.findViewById(R.id.tvQuoteContent)
        val tvWeatherContent: TextView = view.findViewById(R.id.tvWeatherContent)
        val tvNewsContent: TextView = view.findViewById(R.id.tvNewsContent)

        // 3. Set up the View Homework button to switch to the list screen
        btnViewHomework.setOnClickListener {
            val listFragment = HomeworkListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, listFragment)
                .addToBackStack(null) // This lets the user go back to Home
                .commit()
        }

        // 4. Set up the Add Homework button to switch to the add screen
        btnAddHomework.setOnClickListener {
            val addFragment = AddHomeworkFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, addFragment)
                .addToBackStack(null) // This lets the user go back to Home
                .commit()
        }

        // 5. Update the "Pending Tasks" summary dynamically from saved data
        updateTaskSummary(tvTaskSummary)

        // 6. Load data from APIs using Retrofit
        loadMotivationalQuote(tvQuoteContent)
        loadWeatherData(tvWeatherContent)
        loadEducationNews(tvNewsContent)

        return view
    }

    /**
     * Reads saved homework from storage and updates the "pending tasks" count.
     */
    private fun updateTaskSummary(textView: TextView) {
        val rawDataList = HomeworkStorage.readHomework(requireContext())
        var pendingCount = 0
        
        for (line in rawDataList) {
            val parts = line.split("|")
            // Check if the task status is "Pending"
            if (parts.size == 4 && parts[3].trim() == "Pending") {
                pendingCount++
            }
        }
        
        textView.text = "You have $pendingCount pending tasks"
    }

    /**
     * This function fetches a random quote from the internet and displays it.
     */
    private fun loadMotivationalQuote(textView: TextView) {
        val apiService = QuoteApiService.create()
        apiService.getRandomQuote().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful) {
                    textView.text = "\"${response.body()?.content}\""
                } else {
                    textView.text = "Focus on your goals today!"
                }
            }
            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                textView.text = "Stay organized and keep moving forward!"
            }
        })
    }

    /**
     * This function fetches current weather for Phnom Penh and displays it.
     */
    private fun loadWeatherData(textView: TextView) {
        val apiService = WeatherApiService.create()
        
        // Using the real API key provided by the user
        val apiKey = "88fe09458dd46e0a2c0af2c5af6e94cb"
        
        apiService.getCurrentWeather("Phnom Penh", "metric", apiKey)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weather = response.body()
                        if (weather != null) {
                            val temp = weather.main.temp.toInt()
                            val desc = weather.weather[0].description
                            textView.text = "$temp°C, $desc"
                        }
                    } else {
                        textView.text = "Weather unavailable"
                    }
                }
                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    textView.text = "Failed to load weather"
                }
            })
    }

    /**
     * This function fetches latest education news and displays the top headline.
     */
    private fun loadEducationNews(textView: TextView) {
        val apiService = NewsApiService.create()
        
        // Using the real NewsAPI key and parameters provided
        val apiKey = "e22f8febc4f543dabc726177e0ddf035"
        
        apiService.getEducationNews("education", "en", "publishedAt", apiKey)
            .enqueue(object : Callback<NewsResponse> {
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    if (response.isSuccessful) {
                        val news = response.body()
                        if (news != null && news.articles.isNotEmpty()) {
                            // Display the title of the first news article
                            textView.text = news.articles[0].title
                        } else {
                            textView.text = "No news found at the moment"
                        }
                    } else {
                        textView.text = "News temporarily unavailable"
                    }
                }
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    textView.text = "Failed to load news"
                }
            })
    }
}
