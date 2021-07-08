package com.example.niramaiassignment.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaiassignment.NiramaiApplication
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.databinding.ActivityMainBinding
import com.example.niramaiassignment.ui.ProjectsAdapter
import com.example.niramaiassignment.ui.updatedetails.UpdateDetailsActivity
import com.example.niramaiassignment.utils.RecyclerviewClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter:ProjectsAdapter

    private val projectRecyclerView: RecyclerView
        get() = binding.recyclerView
    private val fab: FloatingActionButton
        get() = binding.floatingActionButton

    private val recyclerviewClickListener: RecyclerviewClickListener = object :
        RecyclerviewClickListener{
        override fun onItemClick(project: Project) {
            val i = Intent(this@HomeActivity, UpdateDetailsActivity::class.java)
            i.putExtra("project",project)
            startActivity(i)
        }

        override fun onLongClick(project: Project) {

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.database = (applicationContext as NiramaiApplication).database

        // setup recyclerview
        projectRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ProjectsAdapter(context = this, projects = emptyList(), listener = recyclerviewClickListener)
        projectRecyclerView.adapter = adapter


        viewModel.projects.observe(this) { list ->
            adapter.update(list)
        }

        fab.setOnClickListener(this::onFabClicked)
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateProjects()
    }

    // Listeners
    private fun onFabClicked(view: View){
        val i = Intent(this, UpdateDetailsActivity::class.java)
        startActivity(i)
    }
}