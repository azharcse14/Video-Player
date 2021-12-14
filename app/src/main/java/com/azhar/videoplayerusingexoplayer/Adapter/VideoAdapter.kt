package com.azhar.videoplayerusingexoplayer.Adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.azhar.videoplayerusingexoplayer.Model.Video
import com.azhar.videoplayerusingexoplayer.databinding.VideoViewChildBinding

class VideoAdapter(private val context: Context, private var videoList: ArrayList<Video>):
    RecyclerView.Adapter<VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(VideoViewChildBinding.inflate(LayoutInflater.from(context),parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
//        holder.videoName.text =  videoList.get(position).title
        holder.videoName.text =  videoList.get(position).title
        holder.folderName.text =  videoList.get(position).folderName
        holder.duration.text = DateUtils.formatElapsedTime(videoList.get(position).duration/1000)

    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}