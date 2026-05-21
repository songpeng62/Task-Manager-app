package kh.edu.rupp.homeworkmanagerapp

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeworkAdapter(

    private val homeworkList: List<HomeworkModel>,

    private val editListener: (HomeworkModel) -> Unit,

    private val deleteListener: (HomeworkModel) -> Unit,

    private val completeListener: (HomeworkModel) -> Unit

) : RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {

    class HomeworkViewHolder(itemView: View)
        : RecyclerView.ViewHolder(itemView) {

        val tvSubject: TextView =
            itemView.findViewById(R.id.tvSubject)

        val tvHomeworkTitle: TextView =
            itemView.findViewById(R.id.tvHomeworkTitle)

        val tvDeadline: TextView =
            itemView.findViewById(R.id.tvDeadline)

        val tvStatus: TextView =
            itemView.findViewById(R.id.tvStatus)

        val btnEdit: Button =
            itemView.findViewById(R.id.btnEditHomework)

        val btnDelete: Button =
            itemView.findViewById(R.id.btnDeleteHomework)

        val btnComplete: Button =
            itemView.findViewById(R.id.btnCompleteHomework)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeworkViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.item_homework,
                parent,
                false
            )

        return HomeworkViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HomeworkViewHolder,
        position: Int
    ) {

        val homework = homeworkList[position]

        // Set text
        holder.tvSubject.text =
            homework.subject

        holder.tvHomeworkTitle.text =
            homework.title

        holder.tvDeadline.text =
            homework.deadline

        holder.tvStatus.text =
            "Status: ${homework.status}"

        // Status color
        if (homework.status == "Completed") {

            holder.tvStatus.setTextColor(
                android.graphics.Color.parseColor("#388E3C")
            )

            holder.btnComplete.visibility =
                View.GONE

        } else {

            holder.tvStatus.setTextColor(
                android.graphics.Color.parseColor("#6200EE")
            )

            holder.btnComplete.visibility =
                View.VISIBLE
        }

        // Edit
        holder.btnEdit.setOnClickListener {

            editListener(homework)
        }

        // Delete with dialog
        holder.btnDelete.setOnClickListener {

            AlertDialog.Builder(holder.itemView.context)

                .setTitle("Delete Homework")

                .setMessage(
                    "Are you sure you want to delete this homework?"
                )

                .setPositiveButton("Delete") { _, _ ->

                    deleteListener(homework)
                }

                .setNegativeButton("Cancel", null)

                .show()
        }

        // Complete
        holder.btnComplete.setOnClickListener {

            completeListener(homework)
        }
    }

    override fun getItemCount(): Int {

        return homeworkList.size
    }
}