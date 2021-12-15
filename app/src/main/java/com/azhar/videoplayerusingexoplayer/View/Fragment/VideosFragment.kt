package com.azhar.videoplayerusingexoplayer.View.Fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.videoplayerusingexoplayer.View.Activity.MainActivity
import com.azhar.videoplayerusingexoplayer.View.Adapter.Video.VideoAdapter
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.databinding.FragmentVideosBinding


class VideosFragment : Fragment() {


    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_videos, container, false)
        val binding = FragmentVideosBinding.bind(view)

        binding.videoRvId.setHasFixedSize(true)
        binding.videoRvId.setItemViewCacheSize(10)
        binding.videoRvId.layoutManager =  LinearLayoutManager(requireContext())
        binding.videoRvId.adapter = VideoAdapter(requireContext(), MainActivity.videoList)
        binding.totalVideosTvId.text = "Total Videos: ${MainActivity.videoList.size}"

        return view
    }

}