package com.avdhesh.simpleapp.ui

import AudioRepository
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.avdhesh.simpleapp.R
import com.avdhesh.simpleapp.databinding.ActivityMainBinding
import com.avdhesh.simpleapp.model.AudioViewModelFactory
import com.avdhesh.simpleapp.network.RetrofitModule
import com.avdhesh.simpleapp.ui.adapter.AudioAdapter
import dagger.hilt.android.AndroidEntryPoint

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
        binding.recyclerViewForecast.itemAnimator = SlideUpItemAnimator()

        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.audioData.observe(this) { audioList ->
            val adapter = AudioAdapter(audioList)
            binding.recyclerViewForecast.adapter = adapter
            binding.recyclerViewForecast.itemAnimator = SlideUpItemAnimator()
        }
        viewModel.getAudioList(this)

    }
}
