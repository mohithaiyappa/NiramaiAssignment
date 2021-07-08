package com.example.niramaiassignment.data

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize
import java.util.Date

@Entity
@Parcelize
data class Project(

    @PrimaryKey(autoGenerate = true)
    val uid: Long = 0,

    @ColumnInfo(name = "project_name")
    var projectName : String? = null,

    @ColumnInfo(name = "short_description")
    var shortDescription: String? = null,

    @ColumnInfo(name = "long_description")
    var longDescription : String? = null,

    @ColumnInfo(name = "company_name")
    var companyName : String? = null,

    @ColumnInfo(name = "date_of_creation")
    var dateOfCreation  : Date? = null
) : Parcelable {
    @Dao
    interface ProjectDao{

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun addProject(project: Project)

        @Query("SELECT * FROM project")
        fun getAllProjects(): List<Project>
    }
}
