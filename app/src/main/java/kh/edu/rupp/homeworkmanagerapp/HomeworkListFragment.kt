package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * HomeworkListFragment is responsible for displaying the list of homework tasks.
 * It uses a RecyclerView to show the items in a vertical list.
 */
class HomeworkListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Connect this fragment to its XML layout file (fragment_homework_list.xml)
        return inflater.inflate(R.layout.fragment_homework_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Create a sample list of homework tasks
        val homeworkList = listOf(
            HomeworkModel("Mathematics", "Complete Algebra Worksheet", "Monday", "Pending"),
            HomeworkModel("English", "Write Essay", "Tuesday", "Completed"),
            HomeworkModel("Science", "Biology Project", "Friday", "Pending")
        )

        // 2. Find the RecyclerView by its ID (recyclerHomework)
        val recyclerHomework: RecyclerView = view.findViewById(R.id.recyclerHomework)

        // 3. Set a LayoutManager to handle how items are positioned (Vertical list)
        recyclerHomework.layoutManager = LinearLayoutManager(requireContext())

        // 4. Create the Adapter with our sample list and attach it to the RecyclerView
        val adapter = HomeworkAdapter(homeworkList)
        recyclerHomework.adapter = adapter
    }
}
