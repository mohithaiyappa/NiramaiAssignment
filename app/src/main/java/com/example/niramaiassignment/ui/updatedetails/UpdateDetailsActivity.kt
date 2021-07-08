package com.example.niramaiassignment.ui.updatedetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.example.niramaiassignment.NiramaiApplication
import com.example.niramaiassignment.data.Project
import com.example.niramaiassignment.databinding.ActivityUpdateDetailsBinding
import com.example.niramaiassignment.utils.removeHorizontalInsets
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UpdateDetailsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityUpdateDetailsBinding
    private val viewModel: UpdateDetailsViewModel by viewModels()

    private val projectName: EditText
        get() = binding.etProjectName
    private val companyName: EditText
        get() = binding.etCompanyName
    private val createdOnDate: TextView
        get() = binding.textDate
    private val shortDesc: EditText
        get() = binding.etShortDesc
    private val longDesc: EditText
        get() = binding.etLongDesc
    private val updateProject: Button
        get() = binding.updateProject

    private val datePicker =
        MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()
    private val dateFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.project = intent.getParcelableExtra("project") ?: Project()
        viewModel.database = (applicationContext as NiramaiApplication).database
        updateUi()

        viewModel.isUpdateSuccessful.observe(this){ updateSuccessful ->
            // finish activity on update
            if (updateSuccessful)
                this.finish()
        }

        // remove insets so text aligns to label
        projectName.removeHorizontalInsets()
        companyName.removeHorizontalInsets()
        shortDesc.removeHorizontalInsets()
        longDesc.removeHorizontalInsets()

        createdOnDate.setOnClickListener (this::onCreatedOnDateClicked)
        updateProject.setOnClickListener (this::updateProject)
        datePicker.addOnPositiveButtonClickListener (this::onDateSelected)
    }

    //region Listeners
    private fun onCreatedOnDateClicked(view: View){
        datePicker.show(supportFragmentManager,"DatePicker")
    }
    private fun onDateSelected(time: Long){
        val selected = Date(time)
        createdOnDate.text = dateFormat.format(selected)
    }
    private fun updateProject(view: View){
        viewModel.project?.let { project->
            //date is added to view model on successful date select
            //add values in edit text to project
            project.projectName = projectName.text.toString()
            project.companyName = companyName.text.toString()
            project.shortDescription = shortDesc.text.toString()
            project.longDescription = longDesc.text.toString()
            project.dateOfCreation = dateFormat.parse(createdOnDate.text.toString())
        }
        viewModel.update()
    }
    //endregion
    private fun updateUi(){
        projectName.setText(viewModel.project?.projectName)
        companyName.setText(viewModel.project?.companyName)
        shortDesc.setText(viewModel.project?.shortDescription)
        longDesc.setText(viewModel.project?.longDescription)
        createdOnDate.text = dateFormat.format(viewModel.project?.dateOfCreation ?: Date())
    }
}