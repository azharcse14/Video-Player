package com.azhar.videoplayerusingexoplayer.View.Adapter.Folder

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.azhar.videoplayerusingexoplayer.Model.Video
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.databinding.FolderViewChildBinding

class FolderAdapter(private val context: Context, private var folderList: ArrayList<Video>):
    RecyclerView.Adapter<FolderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(FolderViewChildBinding.inflate(LayoutInflater.from(context),parent, false))
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.icon.load(folderList.get(position).artUri){
            crossfade(true)
            placeholder(R.mipmap.ic_video_player_icon)
            transformations(CircleCropTransformation())
        }
        holder.folderName.text =  folderList.get(position).folderName

    }

    override fun getItemCount(): Int {
        return folderList.size
    }
}