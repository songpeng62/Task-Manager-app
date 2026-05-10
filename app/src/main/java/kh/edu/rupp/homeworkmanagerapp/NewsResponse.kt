package kh.edu.rupp.homeworkmanagerapp

/**
 * NewsResponse is a data class used to map the JSON response from a News API.
 */
data class NewsResponse(
    val articles: List<Article>
)

/**
 * Represents a single news article.
 */
data class Article(
    val title: String,
    val description: String?
)
