package com.azhar.videoplayerusingexoplayer.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.azhar.videoplayerusingexoplayer.databinding.VideoViewChildBinding

class VideoViewHolder(binding: VideoViewChildBinding): RecyclerView.ViewHolder(
    binding.root
) {
    val videoName = binding.videoNameId
}