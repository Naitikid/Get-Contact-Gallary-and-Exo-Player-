package com.example.contactnumberandgallaryviewer.videoplayer

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.databinding.DataBindingUtil
import com.example.contactnumberandgallaryviewer.R
import com.example.contactnumberandgallaryviewer.databinding.ActivityVidioPlayerBinding

class VidioPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVidioPlayerBinding
    var simpleVideoView: VideoView? = null
    var mediaControl: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vidio_player)
        simpleVideoView = binding.vidioview
        if (mediaControl == null) {
            Toast.makeText(this, "+++++++++++++++++++++++++++++", Toast.LENGTH_SHORT).show()
            mediaControl = MediaController(this)
            mediaControl!!.setAnchorView(this.simpleVideoView)
            simpleVideoView!!.setMediaController(mediaControl)
            simpleVideoView!!.setVideoURI(
                Uri.parse(
                    "android.resource://"
                            + packageName + "/" + R.raw.bee
                )
            )
            simpleVideoView!!.requestFocus()
//            simpleVideoView!!.start()
            simpleVideoView!!.setOnCompletionListener {
                Toast.makeText(
                    applicationContext, "Video completed",
                    Toast.LENGTH_LONG
                ).show()
            }

            // display a toast message if any
            // error occurs while playing the video
            simpleVideoView!!.setOnErrorListener { mp, what, extra ->
                Toast.makeText(
                    applicationContext, "An Error Occurred " +
                            "While Playing Video !!!", Toast.LENGTH_LONG
                ).show()
                false
            }
        }
    }
}