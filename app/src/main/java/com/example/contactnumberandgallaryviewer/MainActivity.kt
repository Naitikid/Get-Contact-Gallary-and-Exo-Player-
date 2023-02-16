package com.example.contactnumberandgallaryviewer

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore.Video
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.contactnumberandgallaryviewer.databinding.ActivityMainBinding
import com.example.contactnumberandgallaryviewer.exoplayer.ExoPlayerActivity
import com.example.contactnumberandgallaryviewer.forcontacts.ContactsActivity
import com.example.contactnumberandgallaryviewer.forgallary.GallaryActivity
import com.example.contactnumberandgallaryviewer.utils.dialog
import com.example.contactnumberandgallaryviewer.videoplayer.VidioPlayerActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    companion object {
        private const val CONTACT_PERMISSION_CODE = 100
        private const val GALLARY_PERMISSION_CODE = 101
    }
    var clickbtn=true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.contactsBtn.setOnClickListener {
            clickpermission(Manifest.permission.READ_CONTACTS, CONTACT_PERMISSION_CODE)
        }

        binding.gallaryBtn.setOnClickListener {
            clickpermission(Manifest.permission.READ_EXTERNAL_STORAGE, GALLARY_PERMISSION_CODE)
        }
        binding.simplevideoBtn.setOnClickListener {
           val intent=Intent(this,VidioPlayerActivity::class.java)
            startActivity(intent)
        }
        binding.exoplayerBtn.setOnClickListener {
            val intent=Intent(this, ExoPlayerActivity::class.java)
            startActivity(intent)

        }
        binding.testcheck.setOnClickListener {

        }

    }

    private fun clickpermission(permissioon: String, permissioncode: Int) {
        binding.apply {
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    permissioon
                ) == PackageManager.PERMISSION_DENIED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(permissioon),
                    permissioncode
                )

            } else {
                Toast.makeText(this@MainActivity, R.string.alreadypermission, Toast.LENGTH_SHORT)
                    .show()

               if (permissioncode==CONTACT_PERMISSION_CODE){
                   startActivity(Intent(this@MainActivity, ContactsActivity::class.java))
                  // Toast.makeText(applicationContext, "for contact", Toast.LENGTH_SHORT).show()
               }else{
                   startActivity(Intent(this@MainActivity, GallaryActivity::class.java))
                 //  Toast.makeText(applicationContext, "for gallary", Toast.LENGTH_SHORT).show()
               }
                // opengallary()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACT_PERMISSION_CODE) {                                                      /*for Contact Permission*/
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, R.string.camerapermissiongranted, Toast.LENGTH_SHORT).show()
                startActivity(Intent(this@MainActivity, ContactsActivity::class.java))
            } else {
                val showRationale = shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                dialog(this).showPermisssionDeniedDialog(
                    getString(R.string.Contact), getString(R.string.gotosetting),
                    CONTACT_PERMISSION_CODE
                )
                Toast.makeText(this, R.string.cameraPermissionDenied, Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == GALLARY_PERMISSION_CODE) {                                                  /*for gallary Permission*/
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //    Toast.makeText(this, R.string.gallaryPermissionGranted, Toast.LENGTH_SHORT).show()
               // opengallary()
                startActivity(Intent(this@MainActivity, GallaryActivity::class.java))
            } else {
                val showRationale = shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)
                dialog(this).showPermisssionDeniedDialog(getString(R.string.gallary), getString(
                    R.string.gotosetting

                ),
                    GALLARY_PERMISSION_CODE
                )
             //   Toast.makeText(this, R.string.gallaryPermissionDenied, Toast.LENGTH_SHORT).show()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CONTACT_PERMISSION_CODE) {
            startActivity(Intent(this@MainActivity, ContactsActivity::class.java))
          /*  val pic = data?.getParcelableExtra<Bitmap>("data")   *//*for Contact *//*
            imageview.setImageBitmap(pic)*/
        } else if(requestCode == GALLARY_PERMISSION_CODE)
        {
            startActivity(Intent(this@MainActivity, GallaryActivity::class.java))
        /*    val imageUri = data?.data                                     *//*for gallary*//*
            imageview.setImageURI(imageUri)*/
        }
    }


}
