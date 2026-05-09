package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * MainActivity is the main screen that acts as a container for different fragments.
 * For beginners: An Activity is like a window, and Fragments are like pieces of content 
 * that can be swapped inside that window.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Connect this Kotlin file to the activity_main.xml layout
        setContentView(R.layout.activity_main)

        // We only want to load the HomeFragment the very first time the app starts.
        // If savedInstanceState is NOT null, it means the app is just recreating 
        // itself (like when you rotate the phone), so we don't need to add it again.
        if (savedInstanceState == null) {
            
            // supportFragmentManager helps us manage our fragments
            // beginTransaction() starts a series of changes (like adding or replacing fragments)
            supportFragmentManager.beginTransaction()
                // Replace the container in XML (fragment_container) with our HomeFragment class
                .replace(R.id.fragment_container, HomeFragment())
                // commit() tells Android to actually execute the changes
                .commit()
        }
    }
}
