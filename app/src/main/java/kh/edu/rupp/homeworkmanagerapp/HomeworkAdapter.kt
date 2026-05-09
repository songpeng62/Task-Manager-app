package kh.edu.rupp.homeworkmanagerapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * HomeworkAdapter connects the list of homework data to the RecyclerView.
 * It creates each row and fills it with information from our HomeworkModel.
 */
class HomeworkAdapter(private val homeworkList: List<HomeworkModel>) : 
    RecyclerView.Adapter<HomeworkAdapter.HomeworkViewHolder>() {

    /**
     * ViewHolder holds the visual elements (TextViews) for a single row.
     * This helps the RecyclerView reuse views and run smoothly.
     */
    class HomeworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSubject: TextView = itemView.findViewById(R.id.tvSubject)
        val tvHomeworkTitle: TextView = itemView.findViewById(R.id.tvHomeworkTitle)
        val tvDeadline: TextView = itemView.findViewById(R.id.tvDeadline)
        val tvStatus: TextView = itemView.findViewById(R.id.tvStatus)
    }

    /**
     * This function is called when the RecyclerView needs a new row to be created.
     * We load (inflate) the item_homework.xml layout here.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeworkViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_homework, parent, false)
        return HomeworkViewHolder(view)
    }

    /**
     * This function is called to fill a specific row with data.
     */
    override fun onBindViewHolder(holder: HomeworkViewHolder, position: Int) {
        val homework = homeworkList[position]
        
        // Bind the data from the model to the views
        holder.tvSubject.text = homework.subject
        holder.tvHomeworkTitle.text = homework.title
        holder.tvDeadline.text = homework.deadline
        holder.tvStatus.text = "Status: ${homework.status}"
    }

    /**
     * Returns the number of items in the list.
     */
    override fun getItemCount(): Int {
        return homeworkList.size
    }
}
