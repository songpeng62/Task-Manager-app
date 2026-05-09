package kh.edu.rupp.homeworkmanagerapp

/**
 * HomeworkModel is a data class that represents a single homework item.
 * A "data class" in Kotlin is a simple way to create a class that only holds data.
 * It automatically provides useful functions like equals(), hashCode(), and toString().
 */
data class HomeworkModel(
    val subject: String,   // The name of the subject (e.g., Mathematics, Science)
    val title: String,     // The title of the homework (e.g., Algebra Worksheet)
    val deadline: String,  // When the homework is due (e.g., Due: Monday)
    val status: String     // The current status of the task (e.g., Pending, Completed)
)
