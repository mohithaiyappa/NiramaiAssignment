package com.example.niramaiassignment.ui.updatedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateDetailsViewModel : ViewModel() {

    var project: Project? = null
    var database: AppDatabase? = null

    private val _isUpdateSuccessful: MutableLiveData<Boolean> = MutableLiveData(false)
    val isUpdateSuccessful: LiveData<Boolean>
        get() = _isUpdateSuccessful

    fun update(){
        viewModelScope.launch {
            writeToDb()
        }
    }
    private suspend fun writeToDb(){
        withContext(Dispatchers.IO){
            project?.let { proj ->
                database?.projectDao()?.addProject(proj)
            }
        }
    }
}