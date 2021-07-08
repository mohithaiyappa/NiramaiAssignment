package com.example.niramaiassignment.utils

import com.example.niramaiassignment.data.Project

interface RecyclerviewClickListener {
    fun onItemClick(project: Project)
    fun onLongClick(project: Project)
}