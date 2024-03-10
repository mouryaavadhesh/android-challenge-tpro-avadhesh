package com.avdhesh.simpleapp.ui

import AudioRepository
import android.content.Context
import android.media.MediaPlayer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avdhesh.simpleapp.model.AudioItem
import com.avdhesh.simpleapp.utils.HandleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AudioViewModel @Inject constructor(private val repository: AudioRepository) :
    ViewModel() {

    private val _audioData = MutableLiveData<List<AudioItem>>()
    val audioData: LiveData<List<AudioItem>> get() = _audioData

    var isPlaying = false
    val mediaPlayer: MediaPlayer? = null

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = false
    }

    fun getAudioList(context: Context) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.getAudio()
                if (response.isSuccessful) {
                    _audioData.value = response.body()
                }
            } catch (e: Exception) {
                e.message?.let { HandleError.showErrorMessage(context, it) }
            } finally {
                _isLoading.value = false
            }
        }
    }


}