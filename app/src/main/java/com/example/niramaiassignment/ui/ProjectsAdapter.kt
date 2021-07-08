package com.example.niramaiassignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.databinding.LayoutItemProjectBinding
import com.example.niramaiassignment.utils.RecyclerviewClickListener
import java.text.SimpleDateFormat
import java.util.Locale

class ProjectsAdapter(
    val context: Context,
    var projects: List<Project>,
    val listener: RecyclerviewClickListener
) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    private val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    fun update( updatedList: List<Project>){
        projects = updatedList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return projects.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutItemProjectBinding.inflate(LayoutInflater.from(context),parent,false).root
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // populate data
        LayoutItemProjectBinding.bind(holder.itemView).apply {
            val project = projects[position]
            fieldProjectName.text = project.projectName
            fieldDate.text = dateFormat.format(project.dateOfCreation!!)
            fieldCompanyName.text = project.companyName
            fieldShortDesc.text = project.shortDescription
            root.setOnClickListener{
                listener.onItemClick(project)
            }
        }

    }
}