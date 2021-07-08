package com.example.niramaiassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaiassignment.databinding.ActivityMainBinding
import com.example.niramaiassignment.ui.updatedetails.UpdateDetailsActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val projectRecyclerView: RecyclerView
        get() = binding.recyclerView
    private val fab: FloatingActionButton
        get() = binding.floatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // setup recyclerview
        projectRecyclerView.layoutManager = LinearLayoutManager(this)
        projectRecyclerView.adapter = ProjectsAdapter(context = this, projects = emptyList())

        fab.setOnClickListener(this::onFabClicked)
    }

    // Listeners
    private fun onFabClicked(view: View){
        val i = Intent(this, UpdateDetailsActivity::class.java)
        startActivity(i)
    }
}