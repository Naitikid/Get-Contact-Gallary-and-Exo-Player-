package com.example.contactnumberandgallaryviewer.exoplayer

import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.contactnumberandgallaryviewer.R
import com.example.contactnumberandgallaryviewer.databinding.ActivityExoPlayerBinding
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource


class ExoPlayerActivity : AppCompatActivity() {

    var vidioList: ArrayList<Linkformodel> = ArrayList()

    private lateinit var binding: ActivityExoPlayerBinding
    private var exoPlayer: ExoPlayer? = null
    private var playbackPosition = 0L
    private var playWhenReady = true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_exo_player)
        preparePlayer()
        Log.d(TAG, "onCreate:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ")
    }

    override fun onResume() {
        super.onResume()
        preparePlayer()
        Log.d(TAG, "onResume: ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++")
    }

    private fun preparePlayer() {

        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer?.playWhenReady = true
        binding.playerView.player = exoPlayer
        vidioList.add(Linkformodel("https://flutter.github.io/assets-for-api-docs/assets/videos/bee.mp4"))
        vidioList.add(Linkformodel("https://flutter.github.io/assets-for-api-docs/assets/videos/bee.mp4"))
        vidioList.add(Linkformodel("https://flutter.github.io/assets-for-api-docs/assets/videos/bee.mp4"))




        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(Uri.parse("url or path")))
        val defaultHttpDataSourceFactory = DefaultHttpDataSource.Factory()
        val mediaItem = MediaItem.fromUri(URL)

        val mediaSource1 = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(Uri.parse("https://flutter.github.io/assets-for-api-docs/assets/videos/bee.mp4")))
        val mediaSource2 = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
            .createMediaSource(MediaItem.fromUri(Uri.parse("https://flutter.github.io/assets-for-api-docs/assets/videos/bee.mp4")))

        val concatenatedSource = ConcatenatingMediaSource(mediaSource1,mediaSource2)

        exoPlayer?.apply {
            setMediaSource(concatenatedSource)
            seekTo(playbackPosition)
            playWhenReady = playWhenReady
            prepare()
        }

    }

    private fun releasePlayer() {
        exoPlayer?.let { player ->
            playbackPosition = player.currentPosition
            playWhenReady = player.playWhenReady
            player.release()
            exoPlayer = null
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
        Log.d(TAG, "onStop: ////////////////////////////////////////////////////////////////////////////")
    }

    override fun onPause() {
        super.onPause()
        releasePlayer()
        Log.d(TAG, "onPause: *****************************************************************************")
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        Log.d(TAG, "onDestroy: ----------------------------------------------------------------------------")
    }

    companion object {
        const val URL = "https://bitdash-a.akamaihd.net/content/sintel/hls/playlist.m3u8"
    }
}
