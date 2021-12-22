package com.azhar.videoplayerusingexoplayer.View.Adapter.Video

import androidx.recyclerview.widget.RecyclerView
import com.azhar.videoplayerusingexoplayer.databinding.VideoViewChildBinding

class VideoViewHolder(binding: VideoViewChildBinding): RecyclerView.ViewHolder(
    binding.root
) {
    val icon = binding.iconId
    val videoName = binding.videoNameId
    val folderName = binding.folderNameId
    val duration = binding.durationId
    val root = binding.root
}