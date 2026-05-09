package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * AddHomeworkFragment allows users to add a new homework task.
 * It connects to the fragment_add_homework.xml layout.
 */
class AddHomeworkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflate the layout for this fragment (connect to fragment_add_homework.xml)
        val view = inflater.inflate(R.layout.fragment_add_homework, container, false)

        // 2. Find the input fields and button by their IDs from the XML
        val etSubject: EditText = view.findViewById(R.id.etSubject)
        val etHomeworkTitle: EditText = view.findViewById(R.id.etHomeworkTitle)
        val etDeadline: EditText = view.findViewById(R.id.etDeadline)
        val btnSaveHomework: Button = view.findViewById(R.id.btnSaveHomework)

        // 3. Set a click listener to handle saving the homework
        btnSaveHomework.setOnClickListener {
            val subject = etSubject.text.toString()
            val title = etHomeworkTitle.text.toString()
            val deadline = etDeadline.text.toString()

            // Basic validation to ensure all fields are filled
            if (subject.isNotEmpty() && title.isNotEmpty() && deadline.isNotEmpty()) {
                // In a real app, you would save this data to a database or list
                // For now, we just show a success message
                Toast.makeText(requireContext(), "Homework Saved!", Toast.LENGTH_SHORT).show()
                
                // Go back to the previous screen (e.g., Home or List)
                parentFragmentManager.popBackStack()
            } else {
                // Show a message if any field is empty
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
