package com.azhar.videoplayerusingexoplayer.View.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azhar.videoplayerusingexoplayer.Model.Video
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.databinding.ActivityPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class PlayerActivity : AppCompatActivity() {

    companion object{
        lateinit var player:ExoPlayer
        lateinit var playerList: ArrayList<Video>
        var position:Int = -1
    }

    lateinit var binding: ActivityPlayerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createPlayer()

    }

    private fun createPlayer(){
        player = ExoPlayer.Builder(this).build()
        binding.playerView.player = player
        val mediaItem = MediaItem.fromUri(MainActivity.videoList[position].artUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }


}