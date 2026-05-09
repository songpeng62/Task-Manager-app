package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * HomeworkListFragment displays the list of homework tasks saved in internal storage.
 * It reads data from "homework.txt" and converts it into HomeworkModel objects.
 */
class HomeworkListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment (fragment_homework_list.xml)
        return inflater.inflate(R.layout.fragment_homework_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Read raw strings from internal storage
        val rawDataList = HomeworkStorage.readHomework(requireContext())

        // 2. Convert raw strings into HomeworkModel objects
        val homeworkList = mutableListOf<HomeworkModel>()
        
        for (line in rawDataList) {
            // Split the line by the pipe "|" character
            val parts = line.split("|")
            
            // Ensure the line has exactly 4 parts (Subject, Title, Deadline, Status)
            if (parts.size == 4) {
                val model = HomeworkModel(
                    subject = parts[0],
                    title = parts[1],
                    deadline = parts[2],
                    status = parts[3]
                )
                homeworkList.add(model)
            }
        }

        // 3. Find the RecyclerView by its ID
        val recyclerHomework: RecyclerView = view.findViewById(R.id.recyclerHomework)

        // 4. Set a LayoutManager (Vertical list)
        recyclerHomework.layoutManager = LinearLayoutManager(requireContext())

        // 5. Attach the Adapter with our loaded list
        val adapter = HomeworkAdapter(homeworkList)
        recyclerHomework.adapter = adapter
    }
}
