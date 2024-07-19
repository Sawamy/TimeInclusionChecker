package com.example.timeinclusionchecker

import android.app.Application
import com.example.timeinclusionchecker.data.AppContainer
import com.example.timeinclusionchecker.data.AppDataContainer

class TimeInclusionCheckerApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}