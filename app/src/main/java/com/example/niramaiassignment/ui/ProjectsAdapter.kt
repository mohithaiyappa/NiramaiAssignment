package com.example.niramaiassignment.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.databinding.LayoutItemProjectBinding

class ProjectsAdapter(
    val context: Context,
    val projects: List<Project>
) : RecyclerView.Adapter<ProjectsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

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
            labelProjectName.text = "Project Name $position"
        }
    }
}