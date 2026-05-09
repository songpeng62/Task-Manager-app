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
 * AddHomeworkFragment allows users to add a new homework task and save it to internal storage.
 * It uses a pipe-separated format (Subject|HomeworkTitle|Deadline|Status) for data persistence.
 */
class AddHomeworkFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflate the layout for this fragment (connect to fragment_add_homework.xml)
        val view = inflater.inflate(R.layout.fragment_add_homework, container, false)

        // 2. Find the input fields and button by their IDs from the XML layout
        val etSubject: EditText = view.findViewById(R.id.etSubject)
        val etHomeworkTitle: EditText = view.findViewById(R.id.etHomeworkTitle)
        val etDeadline: EditText = view.findViewById(R.id.etDeadline)
        val btnSaveHomework: Button = view.findViewById(R.id.btnSaveHomework)

        // 3. Set a click listener to handle the "Save Homework" button press
        btnSaveHomework.setOnClickListener {
            
            // Get the text from the EditText fields and remove extra spaces
            val subject = etSubject.text.toString().trim()
            val title = etHomeworkTitle.text.toString().trim()
            val deadline = etDeadline.text.toString().trim()

            // 4. Validation: Ensure all fields are filled before saving
            if (subject.isNotEmpty() && title.isNotEmpty() && deadline.isNotEmpty()) {
                
                // 5. Combine the data into the requested format: Subject|HomeworkTitle|Deadline|Pending
                // We use the pipe symbol "|" as a separator
                val homeworkData = "$subject|$title|$deadline|Pending"

                // 6. Call our HomeworkStorage helper to save this data into "homework.txt"
                // The storage helper will automatically add a newline for us
                HomeworkStorage.saveHomework(requireContext(), homeworkData)

                // 7. Show a success message to the user
                Toast.makeText(requireContext(), "Homework Saved", Toast.LENGTH_SHORT).show()

                // 8. Clear the fields so the user can add another task immediately
                etSubject.text.clear()
                etHomeworkTitle.text.clear()
                etDeadline.text.clear()

            } else {
                // Show an error message if any field is empty
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
