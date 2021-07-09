package com.example.niramaiassignment.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _projects: MutableLiveData<List<Project>> = MutableLiveData(emptyList())
    val projects: LiveData<List<Project>>
        get() = _projects

    private val _companies: MutableLiveData<List<String>> = MutableLiveData(emptyList())
    val companies: LiveData<List<String>>
        get() = _companies

    var database: AppDatabase? = null

    fun loadAllProjects(){
        viewModelScope.launch(Dispatchers.IO) {
            val list: List<Project> = database?.projectDao()?.getAllProjects() ?: emptyList()
            _projects.postValue(list)
        }
    }
    fun deleteProject(project: Project){
        viewModelScope.launch(Dispatchers.IO) {
            database?.projectDao()?.deleteProject(project)
        }
    }
    fun loadCompanyNames(){
        viewModelScope.launch(Dispatchers.IO) {
            val list: List<String> = database?.projectDao()?.getAllCompanyNames() ?: emptyList()
            _companies.postValue(list)
        }
    }
    fun search(text: String?){
        viewModelScope.launch(Dispatchers.IO) {
            val list: List<Project> = database?.projectDao()?.search("%$text%") ?: emptyList()
            _projects.postValue(list)
        }
    }
    fun filterByCompany(company: String?){
        viewModelScope.launch(Dispatchers.IO) {
            val list: List<Project> = database?.projectDao()?.getAllProjectsOfCompany(company) ?: emptyList()
            _projects.postValue(list)
        }
    }
    fun sortByDate(){
        viewModelScope.launch(Dispatchers.IO) {
            val list: List<Project> = database?.projectDao()?.sortByDate() ?: emptyList()
            _projects.postValue(list)
        }
    }
    fun sortByName(){
        viewModelScope.launch(Dispatchers.IO) {
            val list: List<Project> = database?.projectDao()?.sortByName() ?: emptyList()
            _projects.postValue(list)
        }
    }
}