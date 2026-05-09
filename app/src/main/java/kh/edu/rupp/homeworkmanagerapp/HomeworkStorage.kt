package kh.edu.rupp.homeworkmanagerapp

import android.content.Context
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStreamReader

/**
 * HomeworkStorage is a helper object for saving and reading homework data 
 * to the device's internal storage.
 */
object HomeworkStorage {

    private const val FILE_NAME = "homework.txt"

    /**
     * Saves a homework entry to the internal file.
     * Use MODE_APPEND to add new data to the end of the file.
     */
    fun saveHomework(context: Context, data: String) {
        var fileOutputStream: FileOutputStream? = null
        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_APPEND)
            val dataWithNewline = data + "\n"
            fileOutputStream.write(dataWithNewline.toByteArray())
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

    /**
     * Reads all homework entries from the internal file.
     * Returns a List of Strings, where each string is one line from the file.
     */
    fun readHomework(context: Context): List<String> {
        val homeworkList = mutableListOf<String>()
        var fileInputStream: FileInputStream? = null

        try {
            // Open the file for reading
            fileInputStream = context.openFileInput(FILE_NAME)
            
            // Use InputStreamReader and BufferedReader to read text line by line
            val inputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader = BufferedReader(inputStreamReader)

            var line: String? = bufferedReader.readLine()
            while (line != null) {
                // Add the line to our list if it's not empty
                if (line.isNotBlank()) {
                    homeworkList.add(line)
                }
                line = bufferedReader.readLine()
            }
        } catch (e: IOException) {
            // If the file is not found or cannot be read, we log the error.
            // This usually happens the very first time the app runs.
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
}
