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
        suspend fun addProject(project: Project)

        @Query("SELECT * FROM project")
        suspend fun getAllProjects(): List<Project>

        @Delete
        suspend fun deleteProject(project: Project)

        @Query("SELECT * FROM project WHERE project_name LIKE :search")
        suspend fun search(search: String?): List<Project>

        @Query("SELECT * FROM project ORDER BY date_of_creation ASC, project_name ASC")
        suspend fun sortByDate(): List<Project>

        @Query("SELECT * FROM project ORDER BY project_name ASC, date_of_creation ASC")
        suspend fun sortByName(): List<Project>

        @Query("SELECT DISTINCT company_name FROM project")
        suspend fun getAllCompanyNames(): List<String>

        @Query("SELECT * FROM project WHERE company_name IS :company")
        suspend fun getAllProjectsOfCompany(company: String?): List<Project>
    }
}
