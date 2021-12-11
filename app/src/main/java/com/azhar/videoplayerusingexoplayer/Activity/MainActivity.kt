package com.azhar.videoplayerusingexoplayer.Activity

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.azhar.videoplayerusingexoplayer.Fragment.FoldersFragment
import com.azhar.videoplayerusingexoplayer.Fragment.VideosFragment
import com.azhar.videoplayerusingexoplayer.R
import com.azhar.videoplayerusingexoplayer.databinding.ActivityMainBinding
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle

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

        requestRunTimePermission()
        setFragment(VideosFragment())
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
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
            else
                ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE),  13)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true
        return super.onOptionsItemSelected(item)
    }

}