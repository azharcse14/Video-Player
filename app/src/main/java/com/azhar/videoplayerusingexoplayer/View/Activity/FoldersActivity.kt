package com.azhar.videoplayerusingexoplayer.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.View.Adapter.Video.VideoAdapter
import com.azhar.videoplayerusingexoplayer.databinding.ActivityFolderBinding

class FoldersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityFolderBinding.inflate(layoutInflater)
        setTheme(R.style.CoolTheme)
        setContentView(binding.root)

        val position = intent.getIntExtra("position", 0)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = MainActivity.folderList[position].folderName

        binding.videoRvIdFa.setHasFixedSize(true)
        binding.videoRvIdFa.setItemViewCacheSize(10)
        binding.videoRvIdFa.layoutManager =  LinearLayoutManager(this)
        binding.videoRvIdFa.adapter = VideoAdapter(this, MainActivity.videoList)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return true
    }
}