package com.example.niramaiassignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaiassignment.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val projectRecyclerView: RecyclerView
        get() = binding.recyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup recyclerview
        projectRecyclerView.layoutManager = LinearLayoutManager(this)
        projectRecyclerView.adapter = ProjectsAdapter(context = this, projects = emptyList())
    }
}