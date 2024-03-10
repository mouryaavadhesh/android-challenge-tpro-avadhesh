package com.avdhesh.simpleapp.ui

import AudioRepository
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avdhesh.simpleapp.model.AudioModel
import com.avdhesh.simpleapp.utils.HandleError
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AudioViewModel @Inject constructor(private val repository: AudioRepository) :
    ViewModel() {
    // Your ViewModel code
//class WeatherViewModel(private val repository: WeatherRepository) : ViewModel() {
    private val _audioData = MutableLiveData<List<AudioModel>>()
    val audioData: LiveData<List<AudioModel>> get() = _audioData


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    init {
        _isLoading.value = false
    }

    fun getAudioList(context: Context, city: String, apiKey: String) {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = repository.getWeather(city, apiKey)
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