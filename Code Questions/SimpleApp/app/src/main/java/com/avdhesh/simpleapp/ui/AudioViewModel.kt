package com.avdhesh.simpleapp.ui

import AudioRepository
import android.content.Context
import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avdhesh.simpleapp.model.AudioItem
import com.avdhesh.simpleapp.model.MediaPlayerState
import com.avdhesh.simpleapp.utils.HandleError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AudioViewModel @Inject constructor(private val repository: AudioRepository) :
    ViewModel() {

    private val _audioData = MutableLiveData<List<AudioItem>>()
    val audioData: LiveData<List<AudioItem>> get() = _audioData

    private val _mediaPlayer = MutableLiveData<MediaPlayer>()
    val mediaPlayer: LiveData<MediaPlayer> get() = _mediaPlayer
    private var mediaP: MediaPlayer = MediaPlayer()

    var isPlaying = true

    //var mediaPlayer = MediaPlayer()
    private var currentAudioIndex = 0

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            updateSeekBar()
            handler.postDelayed(this, 500) // Update SeekBar every 500 milliseconds
        }
    }

    init {
        _mediaPlayer.value = mediaP
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


    fun playAudio(audioUrl: String) {
        viewModelScope.launch {
            mediaP.reset() // Reset in case it's already playing
            try {
                mediaP.setDataSource(audioUrl)
                mediaP.prepareAsync() // Prepare audio asynchronously

                mediaP.setOnPreparedListener {
                    mediaP.start()
                    updateSeekBar() // Start updating SeekBar
                }
                _mediaPlayer.value = mediaP
            } catch (e: Exception) {
                // Handle exception (e.g., display error message)
                e.printStackTrace()
            }
        }

    }

    private fun updateSeekBar() {
        GlobalScope.launch(Dispatchers.Main) {
            while (mediaP.isPlaying) {
                // Update the LiveData with the current count
                // Delay for 1 second
                _mediaPlayer.value = mediaP
                print("updateSeekBar")
                delay(500)

                // Increment the count
            }
        }
        // Use GlobalScope for simplicity, consider using ViewModelScope for better lifecycle management
//        GlobalScope.launch(Dispatchers.Main) {
//            if (mediaP.isPlaying) {
//                _mediaPlayer.value = mediaP
//                print("updateSeekBar")
//                delay(500)
//            }else {
//                handler.removeCallbacks(runnable) // Stop updating when not playing
//            }
//        }
    }

    fun playPreviousAudio() {
        val audioList = audioData.value
        if (audioList != null && currentAudioIndex > 0) {
            currentAudioIndex--
            playAudio(audioList[currentAudioIndex].audio_url)
        }
    }

    fun playNextAudio() {
        val audioList = audioData.value
        if (audioList != null && currentAudioIndex < audioList.size - 1) {
            currentAudioIndex++
            playAudio(audioList[currentAudioIndex].audio_url)
        }
    }

    fun start() {
        mediaP.start()
    }

    fun pause() {
        mediaP.pause()
    }


}