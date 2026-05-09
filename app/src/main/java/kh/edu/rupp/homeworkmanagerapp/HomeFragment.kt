package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

/**
 * HomeFragment is the first screen shown inside MainActivity.
 * It displays a summary and allows users to navigate to the homework list.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflate the layout for this fragment (connect to fragment_home.xml)
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 2. Find the "View Homework" button by its ID
        val btnViewHomework: Button = view.findViewById(R.id.btnViewHomework)

        // 3. Set a click listener to handle the button press
        btnViewHomework.setOnClickListener {
            
            // Create a new instance of HomeworkListFragment
            val listFragment = HomeworkListFragment()

            // Use FragmentTransaction to switch from HomeFragment to HomeworkListFragment
            // We use parentFragmentManager to manage fragments within the Activity
            parentFragmentManager.beginTransaction()
                // Replace the content of fragment_container with the new fragment
                .replace(R.id.fragment_container, listFragment)
                // Add this transaction to the back stack so the user can go back to the Home screen
                .addToBackStack(null)
                // Apply the changes
                .commit()
        }

        // Return the inflated view
        return view
    }
}
