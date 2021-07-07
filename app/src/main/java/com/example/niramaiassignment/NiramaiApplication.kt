package com.example.niramaiassignment

import android.app.Application
import androidx.room.Room
import com.example.niramaiassignment.database.AppDatabase
import com.example.niramaiassignment.database.DATABASE_NAME

class NiramaiApplication : Application() {

    lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()

        database = Room
            .databaseBuilder(applicationContext, AppDatabase::class.java, DATABASE_NAME )
            .build()
    }
}