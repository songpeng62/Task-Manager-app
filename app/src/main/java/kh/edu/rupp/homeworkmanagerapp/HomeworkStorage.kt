package kh.edu.rupp.homeworkmanagerapp

import android.content.Context
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

object HomeworkStorage {

    private const val FILE_NAME = "homework.txt"

    fun saveHomework(
        context: Context,
        data: String
    ) {

        var fileOutputStream: FileOutputStream? = null

        try {

            fileOutputStream =
                context.openFileOutput(
                    FILE_NAME,
                    Context.MODE_APPEND
                )

            val dataWithNewline = data + "\n"

            fileOutputStream.write(
                dataWithNewline.toByteArray()
            )

        } catch (e: IOException) {

            e.printStackTrace()

        } finally {

            try {

                fileOutputStream?.close()

            } catch (e: IOException) {

                e.printStackTrace()
            }
        }
    }

    fun readHomework(
        context: Context
    ): List<String> {

        val homeworkList =
            mutableListOf<String>()

        var fileInputStream:
                FileInputStream? = null

        try {

            fileInputStream =
                context.openFileInput(FILE_NAME)

            val inputStreamReader =
                InputStreamReader(fileInputStream)

            val bufferedReader =
                BufferedReader(inputStreamReader)

            var line:
                    String? = bufferedReader.readLine()

            while (line != null) {

                if (line.isNotBlank()) {

                    homeworkList.add(line)
                }

                line = bufferedReader.readLine()
            }

        } catch (e: IOException) {

            e.printStackTrace()

        } finally {

            try {

                fileInputStream?.close()

            } catch (e: IOException) {

                e.printStackTrace()
            }
        }

        return homeworkList
    }

    fun deleteHomework(
        context: Context,
        targetHomework: HomeworkModel
    ) {

        val allTasks = readHomework(context)

        val targetString =
            "${targetHomework.subject}|${targetHomework.title}|${targetHomework.deadline}|${targetHomework.status}"

        val updatedTasks =
            allTasks.filter {
                it != targetString
            }

        rewriteFile(context, updatedTasks)
    }

    fun updateHomework(
        context: Context,
        oldHomework: HomeworkModel,
        newHomeworkString: String
    ) {

        val allTasks =
            readHomework(context)

        val targetString =
            "${oldHomework.subject}|${oldHomework.title}|${oldHomework.deadline}|${oldHomework.status}"

        val updatedTasks =
            allTasks.map { task ->

                if (task == targetString)
                    newHomeworkString
                else
                    task
            }

        rewriteFile(context, updatedTasks)
    }

    /**
     * Mark homework as completed
     */
    fun markHomeworkCompleted(
        context: Context,
        targetHomework: HomeworkModel
    ) {

        val updatedHomework =
            "${targetHomework.subject}|${targetHomework.title}|${targetHomework.deadline}|Completed"

        updateHomework(
            context,
            targetHomework,
            updatedHomework
        )
    }

    /**
     * Rewrite entire file
     */
    private fun rewriteFile(
        context: Context,
        taskList: List<String>
    ) {

        var fileOutputStream:
                FileOutputStream? = null

        try {

            fileOutputStream =
                context.openFileOutput(
                    FILE_NAME,
                    Context.MODE_PRIVATE
                )

            for (task in taskList) {

                val line = task + "\n"

                fileOutputStream.write(
                    line.toByteArray()
                )
            }

        } catch (e: IOException) {

            e.printStackTrace()

        } finally {

            try {

                fileOutputStream?.close()

            } catch (e: IOException) {

                e.printStackTrace()
            }
        }
    }
}