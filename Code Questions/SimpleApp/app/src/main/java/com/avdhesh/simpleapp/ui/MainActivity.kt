package com.avdhesh.simpleapp.ui

import AudioRepository
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.avdhesh.simpleapp.R
import com.avdhesh.simpleapp.databinding.ActivityMainBinding
import com.avdhesh.simpleapp.model.AudioViewModelFactory
import com.avdhesh.simpleapp.network.RetrofitModule
import com.avdhesh.simpleapp.ui.adapter.AudioAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: AudioViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val apiService =
            RetrofitModule.getInstance()
        val repository = AudioRepository(apiService)
        viewModel = ViewModelProvider(this, AudioViewModelFactory(repository)).get(
            AudioViewModel::class.java
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerViewForecast.addItemDecoration(
            DividerItemDecoration(
                binding.recyclerViewForecast.context,
                DividerItemDecoration.VERTICAL
            )
        )
// Set up SeekBar
//        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
//                if (fromUser) {
//                   // mediaPlayer.seekTo(progress)
//                }
//            }
//
//            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
//
//            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
//        })

        binding.playPauseButton.setOnClickListener {
            if (viewModel.isPlaying) {
                viewModel.pause()
                binding.playPauseButton.setImageResource(android.R.drawable.ic_media_play)
            } else {
                viewModel.start()
                binding.playPauseButton.setImageResource(android.R.drawable.ic_media_pause)
            }
            viewModel.isPlaying = !viewModel.isPlaying
        }

        binding.previousButton.setOnClickListener {
              viewModel.playPreviousAudio()
        }

        binding.nextButton.setOnClickListener {
             viewModel.playNextAudio()
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.mediaPlayer.observe(this) {
            binding.seekBar.progress = it.currentPosition
            binding.seekBar.max = it.duration // Set max to audio duration (initially 0)

            val remainingTime = it.duration - it.currentPosition

            // Format the remaining time as mm:ss
            val minutes = remainingTime / 1000 / 60
            val seconds = remainingTime / 1000 % 60
            val formattedTime = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)

            // Update the TextView with the formatted time
            binding.tvCountdown.text = formattedTime
        }

        viewModel.audioData.observe(this) { audioList ->

            val adapter = AudioAdapter(audioList, clickListener = {
                viewModel.playAudio(it.audio_url)
            })
            binding.recyclerViewForecast.adapter = adapter
            if (audioList.isNotEmpty()) {
                Log.d("Audio",audioList[0].audio_url)
                viewModel.playAudio(audioList[0].audio_url)
            }
        }

        viewModel.getAudioList(this)

    }
//    private fun playAudio(audioUrl: String) {
//        mediaPlayer.reset() // Reset in case it's already playing
//
//        try {
//            mediaPlayer.setDataSource(audioUrl)
//            mediaPlayer.prepareAsync() // Prepare audio asynchronously
//
//            mediaPlayer.setOnPreparedListener {
//                mediaPlayer.start()
//                updateSeekBar() // Start updating SeekBar
//            }
//        } catch (e: Exception) {
//            // Handle exception (e.g., display error message)
//            e.printStackTrace()
//        }
//    }

//    private fun updateSeekBar() {
//        if (mediaPlayer.isPlaying) {
//            binding.seekBar.max = mediaPlayer.duration
//            binding.seekBar.progress = mediaPlayer.currentPosition
//            binding.seekBar.progress = mediaPlayer.currentPosition
//            runnable
//        }
//    }




    override fun onDestroy() {
        super.onDestroy()
        // Release the MediaPlayer when the activity is destroyed
        viewModel.mediaPlayer.value?.release()
    }
}
