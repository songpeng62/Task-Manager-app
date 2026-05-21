package kh.edu.rupp.homeworkmanagerapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeworkListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(
            R.layout.fragment_homework_list,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        // Back button
        val btnBackHome: View =
            view.findViewById(R.id.btnBackHome)

        btnBackHome.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    HomeFragment()
                )
                .commit()
        }

        // Load homework
        loadAndDisplayHomework(view)
    }

    private fun loadAndDisplayHomework(
        view: View
    ) {

        // Read saved homework
        val rawDataList =
            HomeworkStorage.readHomework(requireContext())

        val homeworkList =
            mutableListOf<HomeworkModel>()

        for (line in rawDataList) {

            val parts = line.split("|")

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

        // Connect views
        val recyclerHomework:
                RecyclerView =
            view.findViewById(R.id.recyclerHomework)

        val tvEmptyState:
                TextView =
            view.findViewById(R.id.tvEmptyState)

        recyclerHomework.layoutManager =
            LinearLayoutManager(requireContext())

        // Empty state
        if (homeworkList.isEmpty()) {

            tvEmptyState.visibility =
                View.VISIBLE

            recyclerHomework.visibility =
                View.GONE

        } else {

            tvEmptyState.visibility =
                View.GONE

            recyclerHomework.visibility =
                View.VISIBLE
        }

        // Adapter
        val adapter = HomeworkAdapter(

            homeworkList,

            // Edit
            editListener = { selectedHomework ->

                val editFragment =
                    EditHomeworkFragment
                        .newInstance(selectedHomework)

                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        editFragment
                    )
                    .addToBackStack(null)
                    .commit()
            },

            // Delete
            deleteListener = { selectedHomework ->

                HomeworkStorage.deleteHomework(
                    requireContext(),
                    selectedHomework
                )

                Toast.makeText(
                    requireContext(),
                    "Homework Deleted",
                    Toast.LENGTH_SHORT
                ).show()

                // Refresh list
                loadAndDisplayHomework(view)
            },

            // Complete
            completeListener = { selectedHomework ->

                HomeworkStorage.markHomeworkCompleted(
                    requireContext(),
                    selectedHomework
                )

                Toast.makeText(
                    requireContext(),
                    "Homework Completed",
                    Toast.LENGTH_SHORT
                ).show()

                // Refresh list
                loadAndDisplayHomework(view)
            }
        )

        recyclerHomework.adapter = adapter
    }
}