package com.azhar.videoplayerusingexoplayer.View.Activity

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.azhar.videoplayerusingexoplayer.Model.Folder
import com.azhar.videoplayerusingexoplayer.View.Fragment.FoldersFragment
import com.azhar.videoplayerusingexoplayer.View.Fragment.VideosFragment
import com.azhar.videoplayerusingexoplayer.Model.Video
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.databinding.ActivityMainBinding
import java.io.File
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

    companion object{
        lateinit var videoList: ArrayList<Video>
        lateinit var folderList: ArrayList<Folder>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setTheme(R.style.CoolTheme)
        setContentView(binding.root)

        //NavDrawer
        toggle = ActionBarDrawerToggle(this, binding.root, R.string.open, R.string.close)
        binding.root.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (requestRunTimePermission()){
            folderList = ArrayList()
            videoList = getAllVideos()
            setFragment(VideosFragment())
        }

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.video_view_id->setFragment(VideosFragment())
                R.id.folder_id->setFragment(FoldersFragment())
            }
            return@setOnItemSelectedListener true
        }

        binding.navViewId.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.feed_back_nav_id-> Toast.makeText(this, "Feedback", Toast.LENGTH_SHORT).show()
                R.id.themes_nav_id-> Toast.makeText(this, "Themes", Toast.LENGTH_SHORT).show()
                R.id.sort_order_nav_id-> Toast.makeText(this, "sort_order", Toast.LENGTH_SHORT).show()
                R.id.about_nav_id-> Toast.makeText(this, "about", Toast.LENGTH_SHORT).show()
                R.id.exit_nav_id-> exitProcess(1)

            }
            return@setNavigationItemSelectedListener true
        }

    }

    private fun setFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_fl, fragment)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }

    private fun requestRunTimePermission():Boolean {
        if (ActivityCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), 13)
            return false
        }

        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 13){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                folderList = ArrayList()
                videoList = getAllVideos()
                setFragment(VideosFragment())
            }
            else
                ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE),  13)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("InlinedApi", "Recycle", "Range")
    private fun getAllVideos():ArrayList<Video>{
        val tempList = ArrayList<Video>()
        val tempFolderList = ArrayList<String>()

        val projection = arrayOf(MediaStore.Video.Media.TITLE, MediaStore.Video.Media.SIZE,
                                MediaStore.Video.Media._ID, MediaStore.Video.Media.BUCKET_DISPLAY_NAME,
                                MediaStore.Video.Media.DATA, MediaStore.Video.Media.DATE_ADDED,
                                MediaStore.Video.Media.DURATION, MediaStore.Video.Media.BUCKET_ID
        )

        val cursor = this.contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                                                projection, null, null,
                                                 MediaStore.Video.Media.DATE_ADDED + " DESC"
        )


        if (cursor != null) {
            if (cursor.moveToNext()){
                do {
                    val titleC = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.TITLE))
                    val idC = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media._ID))
                    val folderC = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_DISPLAY_NAME))
                    val folderIdC = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.BUCKET_ID))
                    val sizeC = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.SIZE))
                    val pathC = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA))
                    val durationC = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DURATION)).toLong()

                    try {
                        val file =  File(pathC)
                        val artUriC = Uri.fromFile(file)
                        val video = Video(title = titleC, id = idC, folderName = folderC,
                            duration = durationC,  size = sizeC, path = pathC,  artUri = artUriC
                        )
                        if (file.exists()){
                            tempList.add(video)
                        }

                        if (!tempFolderList.contains(folderC)){
                            tempFolderList.add(folderC)
                            folderList.add(Folder(id = folderIdC, folderName = folderC))
                        }

                    }catch (e:Exception){}

                }while (cursor.moveToNext())
                cursor.close()
            }
        }


        return tempList
    }

}