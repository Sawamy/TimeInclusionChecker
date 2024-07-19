package com.example.timeinclusionchecker.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.timeinclusionchecker.TimeInclusionCheckerApplication
import com.example.timeinclusionchecker.ui.histories.HistoriesViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            this.createSavedStateHandle()
            CheckViewModel(timeInclusionCheckerApplication().container.historiesRepository)
        }

        initializer {
            HistoriesViewModel(timeInclusionCheckerApplication().container.historiesRepository)
        }
    }
}

fun CreationExtras.timeInclusionCheckerApplication(): TimeInclusionCheckerApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as TimeInclusionCheckerApplication)