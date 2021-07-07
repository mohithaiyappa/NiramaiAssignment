package com.example.niramaiassignment.data

import java.util.Date

data class Project(
    var projectName : String? = null,
    var shortDescription: String? = null,
    var longDescription : String? = null,
    var companyName : String? = null,
    var dateOfCreation  : Date? = null
)
