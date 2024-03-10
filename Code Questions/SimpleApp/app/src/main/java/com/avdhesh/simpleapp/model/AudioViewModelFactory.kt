package com.avdhesh.simpleapp.model

import AudioRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.avdhesh.simpleapp.ui.AudioViewModel
import javax.inject.Inject


class AudioViewModelFactory @Inject constructor(private val repository: AudioRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AudioViewModel::class.java)) {
            return AudioViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
