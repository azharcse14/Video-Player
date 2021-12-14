package com.azhar.videoplayerusingexoplayer.View.Adapter.Video

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.azhar.videoplayerusingexoplayer.Model.Video
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.View.Adapter.Video.VideoViewHolder
import com.azhar.videoplayerusingexoplayer.databinding.VideoViewChildBinding

class VideoAdapter(private val context: Context, private var videoList: ArrayList<Video>):
    RecyclerView.Adapter<VideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder(VideoViewChildBinding.inflate(LayoutInflater.from(context),parent, false))
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.icon.load(videoList.get(position).artUri){
            crossfade(true)
            placeholder(R.mipmap.ic_video_player_icon)
            transformations(CircleCropTransformation())
        }
        holder.videoName.text =  videoList.get(position).title
        holder.folderName.text =  videoList.get(position).folderName
        holder.duration.text = DateUtils.formatElapsedTime(videoList.get(position).duration/1000)

    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}