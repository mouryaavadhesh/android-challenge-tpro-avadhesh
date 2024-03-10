package com.avdhesh.simpleapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.avdhesh.simpleapp.databinding.ListItemBinding
import com.avdhesh.simpleapp.model.AudioItem


class AudioAdapter(private val audioList: List<AudioItem>) :
    RecyclerView.Adapter<AudioAdapter.AudioViewHolder>() {

    class AudioViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AudioViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater, parent, false)
        return AudioViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AudioViewHolder, position: Int) {
        val audioItem = audioList[position]
        holder.binding.audioItem = audioItem
        holder.binding.executePendingBindings()
        // Add additional bindings as needed
    }

    override fun getItemCount(): Int = audioList.size
}
