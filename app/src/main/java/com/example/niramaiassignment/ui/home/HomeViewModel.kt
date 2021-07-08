package com.example.niramaiassignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.database.AppDatabase
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _projects: MutableLiveData<List<Project>> = MutableLiveData(emptyList())
    val projects: LiveData<List<Project>>
        get() = _projects

    var database: AppDatabase? = null

    fun updateProjects(){
        viewModelScope.launch {
            val list: List<Project> = database?.projectDao()?.getAllProjects() ?: emptyList()
            _projects.postValue(list)
        }
    }
}