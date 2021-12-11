package com.azhar.videoplayerusingexoplayer.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azhar.videoplayerusingexoplayer.databinding.VideoViewChildBinding

class VideoAdapter(private val context: Context, private var videoList:ArrayList<String>):
    RecyclerView.Adapter<VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(VideoViewChildBinding.inflate(LayoutInflater.from(context),parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.videoName.text =  videoList.get(position)

    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}