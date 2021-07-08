package com.example.niramaiassignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.databinding.LayoutItemProjectBinding
import com.example.niramaiassignment.utils.RecyclerviewClickListener
import java.text.SimpleDateFormat
import java.util.Locale

class ProjectsAdapter(
    val context: Context,
    val listener: RecyclerviewClickListener
) : ListAdapter<Project,ProjectsAdapter.ViewHolder>(DIFF_UTIL) {

    companion object{
        val DIFF_UTIL = object : DiffUtil.ItemCallback<Project>() {
            override fun areItemsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: Project, newItem: Project): Boolean {
                return oldItem.uid == newItem.uid
            }
        }
    }

    private val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutItemProjectBinding.inflate(LayoutInflater.from(context),parent,false).root
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // populate data
        LayoutItemProjectBinding.bind(holder.itemView).apply {
            val project = getItem(position)
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