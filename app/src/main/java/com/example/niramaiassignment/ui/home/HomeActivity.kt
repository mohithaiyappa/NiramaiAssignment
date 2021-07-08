package com.example.niramaiassignment.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.HorizontalScrollView
import androidx.activity.viewModels
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.niramaiassignment.NiramaiApplication
import com.example.niramaiassignment.R
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.databinding.ActivityMainBinding
import com.example.niramaiassignment.ui.ProjectsAdapter
import com.example.niramaiassignment.ui.updatedetails.UpdateDetailsActivity
import com.example.niramaiassignment.utils.RecyclerviewClickListener
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var adapter:ProjectsAdapter

    private val projectRecyclerView: RecyclerView
        get() = binding.recyclerView
    private val fab: FloatingActionButton
        get() = binding.floatingActionButton
    private val appBar: MaterialToolbar
        get() = binding.topAppBar
    private val chipScrollView: HorizontalScrollView
        get() = binding.chipScrollView
    private val chipGroup: ChipGroup
        get() = binding.chipGroup

    private var canFilter: Boolean = false

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
        viewModel.companies.observe(this) {
            addCompanyChips(it)
        }

        fab.setOnClickListener (this::onFabClicked)
        appBar.setOnMenuItemClickListener (this::onMenuItemClicked)
        chipGroup.setOnCheckedChangeListener(this::onCheckChanged)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadAllProjects()
        viewModel.loadCompanyNames()
    }

    private fun addCompanyChips(list: List<String>){
        chipGroup.removeAllViews()
        chipGroup.isSingleSelection = true
        for (companyName in list)
            chipGroup.addView(getCompanyChip(companyName))
    }
    private fun getCompanyChip(companyName: String) = Chip(this).apply {
        text = companyName
        isCheckable = true
        id = ViewCompat.generateViewId()
        setOnClickListener {
            canFilter = true
            viewModel.filterByCompany(companyName)
        }
    }

    // Listeners
    private fun onFabClicked(view: View){
        val i = Intent(this, UpdateDetailsActivity::class.java)
        startActivity(i)
    }
    private fun onCheckChanged(chipGroup: ChipGroup, id: Int){
        if (id == View.NO_ID && canFilter){
            viewModel.loadAllProjects()
        }
    }
    private fun onMenuItemClicked(menuItem: MenuItem): Boolean{
        return when (menuItem.itemId) {
            R.id.search -> {
                val searchView = menuItem.actionView as androidx.appcompat.widget.SearchView
                searchView.queryHint = "Search Projects"
                searchView.setIconifiedByDefault(false)
                searchView.isIconified = false
                searchView.isSubmitButtonEnabled = true

                searchView.setOnQueryTextListener( object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let { text ->
                            viewModel.search(text)
                        }
                        return false
                    }
                    override fun onQueryTextChange(newText: String?): Boolean {
                        return false
                    }
                })

                true
            }
            R.id.filter -> {
                //show or hide scroll view
                if(chipScrollView.isVisible)
                    chipScrollView.visibility = View.GONE
                else
                    chipScrollView.visibility = View.VISIBLE

                true
            }
            R.id.sort   -> {
                viewModel.sortByDate()
                true
            }
            else    -> false
        }
    }
}