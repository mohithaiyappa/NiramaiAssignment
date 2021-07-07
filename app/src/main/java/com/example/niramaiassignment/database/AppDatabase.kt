package com.example.niramaiassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.niramaiassignment.data.Project

const val DATABASE_NAME = "niramai_db"

@Database(entities = [Project::class], version = 1)
@TypeConverters(RoomDateConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun projectDao():Project.ProjectDao
}