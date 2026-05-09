package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * HomeFragment is the first screen shown inside MainActivity.
 * It provides a summary and buttons to navigate to the homework list or add new homework.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflate the layout for this fragment (connect to fragment_home.xml)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 2. Find the buttons by their IDs
        val btnViewHomework: Button = view.findViewById(R.id.btnViewHomework)
        val btnAddHomework: Button = view.findViewById(R.id.btnAddHomework)

        // 3. Set a click listener for the "View Homework" button
        btnViewHomework.setOnClickListener {
            // Replace HomeFragment with HomeworkListFragment
            val listFragment = HomeworkListFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, listFragment)
                .addToBackStack(null) // Allows going back to Home
                .commit()
        }

        // 4. Set a click listener for the "Add Homework" button
        btnAddHomework.setOnClickListener {
            // Replace HomeFragment with AddHomeworkFragment
            val addFragment = AddHomeworkFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, addFragment)
                .addToBackStack(null) // Allows going back to Home
                .commit()
        }

        return view
    }
}
