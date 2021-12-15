package com.azhar.videoplayerusingexoplayer.View.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.View.Activity.MainActivity
import com.azhar.videoplayerusingexoplayer.View.Adapter.Folder.FolderAdapter
import com.azhar.videoplayerusingexoplayer.databinding.FragmentFoldersBinding


class FoldersFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_folders, container, false)
        val binding = FragmentFoldersBinding.bind(view)

        binding.folderRvId.setHasFixedSize(true)
        binding.folderRvId.setItemViewCacheSize(10)
        binding.folderRvId.layoutManager =  LinearLayoutManager(requireContext())
        binding.folderRvId.adapter = FolderAdapter(requireContext(), MainActivity.folderList)

        return view
    }
}