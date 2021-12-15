package com.azhar.videoplayerusingexoplayer.View.Adapter.Folder

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.azhar.videoplayerusingexoplayer.Model.Folder
import com.azhar.videoplayerusingexoplayer.View.Activity.FoldersActivity
import com.azhar.videoplayerusingexoplayer.databinding.FolderViewChildBinding

class FolderAdapter(private val context: Context, private var folderList: ArrayList<Folder>):
    RecyclerView.Adapter<FolderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        return FolderViewHolder(FolderViewChildBinding.inflate(LayoutInflater.from(context),parent, false))
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {

        holder.folderName.text =  folderList.get(position).folderName
        holder.root.setOnClickListener {
            val intent= Intent(context, FoldersActivity::class.java)
            intent.putExtra("position", position)
            ContextCompat.startActivity(context, intent, null)
        }

    }

    override fun getItemCount(): Int {
        return folderList.size
    }
}