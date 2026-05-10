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
 * EditHomeworkFragment allows users to update an existing homework task.
 * It uses the 'updateHomework' method to ensure the list order stays the same.
 */
class EditHomeworkFragment : Fragment() {

    private var oldSubject: String? = null
    private var oldTitle: String? = null
    private var oldDeadline: String? = null
    private var oldStatus: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            oldSubject = it.getString("subject")
            oldTitle = it.getString("title")
            oldDeadline = it.getString("deadline")
            oldStatus = it.getString("status")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_homework, container, false)

        val etSubject: EditText = view.findViewById(R.id.etEditSubject)
        val etTitle: EditText = view.findViewById(R.id.etEditHomeworkTitle)
        val etDeadline: EditText = view.findViewById(R.id.etEditDeadline)
        val btnUpdate: Button = view.findViewById(R.id.btnUpdateHomework)
        val btnBack: Button = view.findViewById(R.id.btnBackEdit)

        // Fill with existing data
        etSubject.setText(oldSubject)
        etTitle.setText(oldTitle)
        etDeadline.setText(oldDeadline)

        btnBack.setOnClickListener { parentFragmentManager.popBackStack() }

        btnUpdate.setOnClickListener {
            val newSubject = etSubject.text.toString().trim()
            val newTitle = etTitle.text.toString().trim()
            val newDeadline = etDeadline.text.toString().trim()

            if (newSubject.isNotEmpty() && newTitle.isNotEmpty() && newDeadline.isNotEmpty()) {
                val oldModel = HomeworkModel(oldSubject!!, oldTitle!!, oldDeadline!!, oldStatus!!)
                val newFormattedData = "$newSubject|$newTitle|$newDeadline|$oldStatus"

                // Use the dedicated update function to maintain list order
                HomeworkStorage.updateHomework(requireContext(), oldModel, newFormattedData)

                Toast.makeText(requireContext(), "Homework Updated", Toast.LENGTH_SHORT).show()
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }

    companion object {
        fun newInstance(model: HomeworkModel): EditHomeworkFragment {
            val fragment = EditHomeworkFragment()
            val args = Bundle()
            args.putString("subject", model.subject)
            args.putString("title", model.title)
            args.putString("deadline", model.deadline)
            args.putString("status", model.status)
            fragment.arguments = args
            return fragment
        }
    }
}
